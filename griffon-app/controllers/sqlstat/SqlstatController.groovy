package sqlstat

import sqlstat.DBAgent
import java.sql.Connection

class SqlstatController {
    def model
    def view

    def db = new DBAgent()

    void mvcGroupInit(Map args) {
        //def (m, v, c) = createMVCGroup('dblogin', 'l', name:'l(x)')
        //view.functionSection.add(v.content)    // Controller から埋め込みを実行
    }

    def login = { evt = null ->
        model.indeterminate = true

        def url  = model.hostname
        def user = model.username
        def pass = model.password

        db.getConnect(url, user, pass)

        if (db.conn == null){
            model.value = 0
            //TODO
            //raise
        } else {
            model.value = 100
        }
        model.indeterminate = false
    }

    def logout = { evt = null ->

        if (!db.conn.isClosed()) {
            db.conn.close()
        }
        model.indeterminate = false
        model.value = 0

    }

    /**
     * http://griffon.codehaus.org/guide/0.9.3/guide/9.%20Threading.html
     */
    def action1 = { evt = null ->
        model.startEnabled = false
        model.stopEnabled  = true

        def dao = new SqlstatInfoDao()
        //def db = new DBAgent()
        try {
            //db.connect()
            //db.getConnect(model.hostname, model.username, model.password)

            while(!model.startEnabled) {
                sleep(3000)
                def list = dao.selectActiveSessionList(db.conn)
                //def list = dao.selectActiveSessionList(model.conn)
                def resultList = []
                list.each {SqlstatInfoBean bean ->
                    resultList.add(
                            [UserName:bean.userName,
                             Status:  bean.status,
                             Event:   bean.event,
                             Sqlid:   bean.sqlId,
                             Commnad: bean.command,
                             SqlText: bean.sqlText]
                    )
                }
                view.searchResult.rowsModel.value = resultList
                view.searchResult.fireTableDataChanged()

                def statList = dao.selectSqlStatInfo(db.conn)
                def resultStatList = []
                statList.each {SqlstatInfoBean bean ->
                    resultStatList.add(
                        [
                                UserName:       bean.userName,
                                Status:         bean.status,
                                Event:          bean.event,
                                SqlId:          bean.sqlId,
                                SqlText:        bean.sqlText,
                                ElapsedSeconds: bean.elapsedSeconds,
                                TimeRemaining:  bean.timeRemaining,
                                Total:          bean.total,
                                Opname:         bean.opname,
                                Target:         bean.target
                        ]
                    )
                }
                view.searchStatResult.rowsModel.value = resultStatList
                view.searchStatResult.fireTableDataChanged()
            }

        } catch (Exception e) {
            e.printStackTrace()

        } finally {
            db.close()
        }
    }

    def stop = {
        evt = null ->
            println("stop")
            model.startEnabled = true
            model.stopEnabled  = false
    }
}

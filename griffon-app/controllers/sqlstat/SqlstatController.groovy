package sqlstat

import sqlstat.DBAgent
import java.sql.Connection

class SqlstatController {
    def model
    def view

    /**
     * http://griffon.codehaus.org/guide/0.9.3/guide/9.%20Threading.html
     */
    def action1 = { evt = null ->
        model.startEnabled = false
        model.stopEnabled  = true

        def date = new Date()

        def dao = new SqlstatInfoDao()
        def db = new DBAgent()
        try {
            db.connect()

            while(!model.startEnabled) {
                sleep(3000)
                def list = dao.selectActiveSessionList(db.conn)
                list.each {SqlstatInfoBean bean ->
                    println("date   username   status sqlid   commnad sqlText")
                    println(date.toString() + "\t" + bean.userName + "\t" + bean.status + "\t" + bean.sqlId  + "\t" + bean.command + "\t" + bean.sqlText.substring(0,30))
                    println("       ")
                }
            }

        } catch (Exception e) {
            e.printStackTrace()

        } finally {
            db.close()
        }

        /*
        def value = 0
        model.value = value

        def idx = 0
        while (model.value < 100) {
            idx += 1
            edt {
                sleep(1000)
                println("async")
                value = 10 * idx

                doLater {
                    model.value = value
                }
            }
        }
        */
    }

    def stop = {
        evt = null ->
            println("stop")
            model.startEnabled = true
            model.stopEnabled  = false
    }

    // void mvcGroupInit(Map args) {
    //    // this method is called after model and view are injected
    // }

    // void mvcGroupDestroy() {
    //    // this method is called when the group is destroyed
    // }

    /*
        Remember that actions will be called outside of the UI thread
        by default. You can change this setting of course.
        Please read chapter 9 of the Griffon Guide to know more.
       
    def action = { evt = null ->
    }
    */
}

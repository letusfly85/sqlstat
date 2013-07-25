package sqlstat

import java.sql.SQLException
import java.sql.Connection

import sqlstat.QueryGenerator
import java.sql.PreparedStatement

class SqlstatInfoDao extends QueryGenerator {

    /**
     *
     */
    def selectActiveSessionList(Connection conn) {
        List<SqlstatInfoBean> list = []

        PreparedStatement stmt = conn.prepareStatement(generateQuery("select_active_sql_list.sql"))

        try {
            def res = stmt.executeQuery()
            while (res.next()) {

            def bean = new SqlstatInfoBean()

                bean.sqlId = res.getString("SQL_ID")
                bean.status = res.getString("STATUS")
                bean.command = res.getString("COMMAND")
                bean.sqlText = res.getString("SQL_TEXT")
                bean.sqlFullText = res.getClob("SQL_FULLTEXT")
                bean.userName = res.getString("USERNAME")
                bean.event    = res.getString("EVENT")

                list.add(bean)
            }

        } catch (SQLException e) {
          e.printStackTrace()

        } finally {
          //if (!stmt.isClosed()) {
            stmt.close()
          //}
        }

        return list
    }

    def selectSqlStatInfo(Connection conn) {
        List<SqlstatInfoBean> list = []

        PreparedStatement stmt = conn.prepareStatement(generateQuery("select_sql_stat_info.sql"))

        try {
            def res = stmt.executeQuery()
            while (res.next()) {

                def bean = new SqlstatInfoBean()

                bean.userName       = res.getString("USERNAME")
                bean.status         = res.getString("STATUS")
                bean.event          = res.getString("EVENT")
                bean.sqlId          = res.getString("SQL_ID")
                bean.sqlText        = res.getString("SQL_TEXT")
                bean.elapsedSeconds = res.getBigDecimal("ELAPSED_SECONDS")
                bean.timeRemaining  = res.getBigDecimal("TIME_REMAINING")
                bean.total          = res.getBigDecimal("TOTAL")
                bean.opname         = res.getString("OPNAME")
                bean.target         = res.getString("TARGET")

                list.add(bean)
            }

        } catch (SQLException e) {
            e.printStackTrace()

        } finally {
            //if (!stmt.isClosed()) {
            stmt.close()
            //}
        }

        return list
    }
}

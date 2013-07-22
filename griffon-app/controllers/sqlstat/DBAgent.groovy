package sqlstat

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException

import sqlstat.Prop

class DBAgent {
    Connection conn = null

    void connect() {
        Prop prop = new Prop()

        def url
        def pass
        def user

        try {


            url =  prop.url
            pass = prop.pass
            user = prop.user

        } catch (Exception e) {
            e.printStackTrace()
        }

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance()
            this.conn = DriverManager.getConnection(url, user, pass)

        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    public int getStatInfo() {
        def value = 0

        PreparedStatement stmt = this.conn.prepareStatement("select 10 as NUM from dual")
        try {
            def res = stmt.executeQuery()
            while (res.next()) {
                value = res.getInt("NUM")
            }
        } catch (SQLException e) {
            e.printStackTrace()

        } finally {
            stmt.close()
        }

        return value
    }

    void close() {
        if (this.conn != null) {
            if (this.conn.isClosed() == false) {
                conn.close()
            }
        }

    }

}

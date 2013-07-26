package sqlstat

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException

class DBAgent {
    Connection conn = null

    void getConnect(url, user, pass) {
       try {
           def host = "jdbc:oracle:thin:@" + url.replace("/", ":")
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance()
            this.conn = DriverManager.getConnection(host, user, pass)

        } catch (Exception e) {
            println(url + "\t" + user + "\t" + pass)
            e.printStackTrace()
        }
    }

    void connect() {
        def url
        def pass
        def user
        try {
            Properties properties = new Properties()
            properties.load(getClass().getResourceAsStream("/.properties"))
            url  = properties.getProperty("HOST")
            pass = properties.getProperty("USER")
            user = properties.getProperty("PASS")

        } catch (Exception e) {
            e.printStackTrace()
        }

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance()
            this.conn = DriverManager.getConnection(url, user, pass)

        } catch (Exception e) {
            println(url + "\t" + user + "\t" + pass)
            e.printStackTrace()
        }
    }

    void close() {
        if (this.conn != null) {
            if (this.conn.isClosed() == false) {
                conn.close()
            }
        }

    }
}

package sqlstat

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException

class DBAgent {
    Connection conn = null

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

    def generateQuery(filePath) {
        def sqlPath = "/sql/" + filePath
        InputStream inputStream = getClass().getResourceAsStream(sqlPath)

        def query

        inputStream.readLines().each {
            query += it + "\n"
        }

        return query
    }

}

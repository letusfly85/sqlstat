package sqlstat

class QueryGenerator {

   def generateQuery(filePath) {
        def sqlPath = "/sql/" + filePath
        InputStream inputStream = getClass().getResourceAsStream(sqlPath)

        def query = ""
        inputStream.readLines().each {
            if (it != null) {
                query += it + "\n"
            }
        }

       //println(query)
       return query
    }

}

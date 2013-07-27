package sqlstat

import groovy.beans.Bindable
import java.sql.Connection

class DbloginModel {
   // @Bindable String

    @Bindable String username = ""
    @Bindable String password = ""
    @Bindable String hostname = ""

    @Bindable Connection conn

}
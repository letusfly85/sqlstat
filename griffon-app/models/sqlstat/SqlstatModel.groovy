package sqlstat

import java.sql.Connection

class SqlstatModel {

    @Bindable String name
    @Bindable String message = "Hello, Griffon!"

    @Bindable boolean startEnabled = true
    @Bindable boolean stopEnabled  = true

    @Bindable int progress = 0
    @Bindable boolean indeterminate = false
    @Bindable int value = 0

    @Bindable List<SqlstatInfoBean> list

    @Bindable Connection conn

}
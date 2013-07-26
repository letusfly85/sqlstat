application {
    title = 'Sqlstat'
    startupGroups = ['sqlstat']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "dblogin"
    'dblogin' {
        model      = 'sqlstat.DbloginModel'
        view       = 'sqlstat.DbloginView'
        controller = 'sqlstat.DbloginController'
    }

    // MVC Group for "sqlstat"
    'sqlstat' {
        model      = 'sqlstat.SqlstatModel'
        view       = 'sqlstat.SqlstatView'
        controller = 'sqlstat.SqlstatController'
    }

    // MVC Group for "sqlstat"
    'sqlstat' {
        model      = 'sqlstat.SqlstatModel'
        view       = 'sqlstat.SqlstatView'
        controller = 'sqlstat.SqlstatController'
    }

}

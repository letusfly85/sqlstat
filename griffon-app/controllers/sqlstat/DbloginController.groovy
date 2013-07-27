package sqlstat

class DbloginController {
    def model
    def view

    def someAction = { event = null ->
        def content =  view.content
        // content に対する処理
    }

    def login = { evt = null ->
        def agent = new DBAgent()

        def url  = model.hostname
        def user = model.username
        def pass = model.password

        agent.getConnect(url, user, pass)

        if (agent.conn == null){
            //TODO
            //raise
        }

        //SqlstatModel.setConn(agent.conn)
        //app.models.SqlstatModel.conn = agent.conn
        //app.models.SqlstatModel.conn = agent.conn
        //model.conn = agent.conn
    }

    def logout = { evt = null ->

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

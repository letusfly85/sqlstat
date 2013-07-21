package sqlstat

class SqlstatController {
    // these will be injected by Griffon
    def model
    def view

    def submit = {
        evt = null ->
            model.message = "hello " + model.name + "!"
    }

    def start = {
        evt = null ->
            model.startEnabled = false
            model.stopEnabled  = true
            def result
            try {
                result = "a"
            } finally {
                null
            }
            //TODO
            //timer.start()
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

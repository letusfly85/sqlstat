package sqlstat

import sqlstat.DBAgent

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
            //model.startEnabled = false
            //model.stopEnabled  = true
            //model.indeterminate = true
            model.value = 50

            action1

            //model.value = 90
            /*
            def result
            try {
                result = "a"
            } finally {
                null
            }
            */
            //TODO
            //timer.start()
    }
    /**
     * http://griffon.codehaus.org/guide/0.9.3/guide/9.%20Threading.html
     */
    def action1 = { evt = null ->
        def db = new DBAgent()
        db.connect()
        def value = db.getStatInfo()
        println(value)
        db.close()

        model.value = value
        //def value = model.value

        def idx = 0
        //3.times {
        while (model.value < 100) {
            idx += 1
            edt {
                sleep(1000)
                println("async")
                value = 10 * idx

                doLater {
                    model.value = value
                }
            }
        }
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

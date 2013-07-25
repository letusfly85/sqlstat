package sqlstat

import groovy.model.ValueHolder

application(title: 'sqlstat',
  preferredSize: [320, 240],
  pack: true,
  //location: [50,50],
  locationByPlatform: true,
  iconImage:   imageIcon('/griffon-icon-48x48.png').image,
  iconImages: [imageIcon('/griffon-icon-48x48.png').image,
               imageIcon('/griffon-icon-32x32.png').image,
               imageIcon('/griffon-icon-16x16.png').image]) {
    // add content here
    //label('Content Goes Here') // delete me

    borderLayout()
    panel(constraints: CENTER, border: emptyBorder(6)) {
        borderLayout()
        textField(columns: 20,
                  border:  titledBorder("name"),
                  text:    bind(target: model, targetProperty: 'name'),
                  constraints: CENTER
        )
        button('Submit',
               actionPerformed: controller.&submit,
               constraints: SOUTH
        )
    }

    /*
    label(text: bind {model.message},
          border: titledBorder("message"),
          constraints: SOUTH
    )*/


    hbox(id:'start', constraints: NORTH) {
        button('start', actionPerformed: controller.&action1,
               enabled: bind {model.startEnabled}
               //constraints: NORTH
        )
        button('stop', actionPerformed: controller.&stop,
                enabled: bind {model.stopEnabled}
                //constraints: NORTH
        )
        progressBar(id: 'progress',
            indeterminate: bind {model.indeterminate},
            minimum:0, maximum: 100,
            value: bind {model.value}
            //constraints: SOUTH
        )
    }

    scrollPane(constraints:CENTER) {
        table {
            tableModel(id:'searchResult') {
                propertyColumn header: 'UserName', propertyName: 'UserName'
                propertyColumn header: 'Status',   propertyName: 'Status'
                propertyColumn header: 'Event',    propertyName: 'Event'
                propertyColumn header: 'Sqlid',    propertyName: 'Sqlid'
                propertyColumn header: 'Commnad',  propertyName: 'Commnad'
                propertyColumn header: 'SqlText',  propertyName: 'SqlText'
            }
        }
    }

    scrollPane(constraints:SOUTH) {
        table {
            tableModel(id:'searchStatResult') {
                propertyColumn header: 'UserName', propertyName: 'UserName'
                propertyColumn header: 'Status', propertyName: 'Status'
                propertyColumn header: 'Event', propertyName: 'Event'
                propertyColumn header: 'SqlId', propertyName: 'SqlId'
                propertyColumn header: 'SqlText', propertyName: 'SqlText'
                propertyColumn header: 'ElapsedSeconds', propertyName: 'ElapsedSeconds'
                propertyColumn header: 'TimeRemaining', propertyName: 'TimeRemaining'
                propertyColumn header: 'Total', propertyName: 'Total'
                propertyColumn header: 'Opname', propertyName: 'Opname'
                propertyColumn header: 'Target', propertyName: 'Target'
            }
        }
    }

    /*
    hbox(id:'stop',constraints: SOUTH) { //, border: emptyBorder(6)) {
        button("stop", actionPerformed: controller.&stop,
                enabled: bind {model.stopEnabled})
    }
    */
}


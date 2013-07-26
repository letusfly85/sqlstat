package sqlstat

import groovy.model.ValueHolder

application(title: 'sqlstat',
  size: [640, 640],
  pack: true,
  //location: [50,50],
  locationByPlatform: true,
  iconImage:   imageIcon('/griffon-icon-48x48.png').image,
  iconImages: [imageIcon('/griffon-icon-48x48.png').image,
               imageIcon('/griffon-icon-32x32.png').image,
               imageIcon('/griffon-icon-16x16.png').image]) {

    borderLayout()
    hbox(id:'start', constraints: NORTH) {
        button('start', actionPerformed: controller.&action1,
               enabled: bind {model.startEnabled}
        )
        button('stop', actionPerformed: controller.&stop,
                enabled: bind {model.stopEnabled}
        )
        progressBar(id: 'progress',
            indeterminate: bind {model.indeterminate},
            minimum:0, maximum: 100,
            value: bind {model.value}
        )

        panel(id:'functionSection'){
            //migLayout layoutConstraints:'wrap 1'
        }
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


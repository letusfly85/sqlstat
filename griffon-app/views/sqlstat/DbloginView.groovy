package sqlstat

import net.miginfocom.swing.MigLayout
import javax.swing.BorderFactory
import java.awt.Color

application(title: 'sqlstat') {
    panel(id:'content', border:BorderFactory.createTitledBorder(''), layout:new MigLayout()){
        borderLayout()
        hbox(constraints: CENTER){
            textField '', columns: 15, text: bind (target: model, 'username')
            label '/'
            textField '', columns: 15, text: bind (target: model, 'password')
            label '@'
            textField '', columns: 15, text: bind (target: model, 'hostname')
        }
        hbox(constraints: SOUTH){
            button('ログイン',
                    actionPerformed: controller.&login)
            button('ログアウト',
                    actionPerformed: controller.&logout)
        }
    }
}

/*
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
    panel(id:'content', border:BorderFactory.createTitledBorder('Groovy'), layout:new MigLayout()){

        label text: bind(source:model, 'name')
        label '='
        textField 'sin(x)', columns:15, text: bind(target:model, 'function')
    }
}

*/
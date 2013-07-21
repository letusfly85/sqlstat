package sqlstat

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

    progressBar(id: "progress",
            indeterminate: true,
            minimum:0, maximum: 100,
            constraints: SOUTH)

    /*
    hbox(id:'start',constraints: SOUTH) { //, border: emptyBorder(6)) {
        button("start", actionPerformed: controller.&start,
               enabled: bind {model.startEnabled})
    }

    hbox(id:'stop',constraints: SOUTH) { //, border: emptyBorder(6)) {
        button("stop", actionPerformed: controller.&stop,
                enabled: bind {model.stopEnabled})
    }
    */
}

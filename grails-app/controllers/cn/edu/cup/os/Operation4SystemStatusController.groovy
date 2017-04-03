package cn.edu.cup.os

import cn.edu.cup.system.SystemMenu

class Operation4SystemStatusController {

    def index() {
        def noAction = SystemMenu.countByMenuActionIsNull()
        def controllerList = []
        grailsApplication.controllerClasses.each { e->
            controllerList.add(e.name)
        }
        println("${controllerList}")
        def toDoList = []
        SystemMenu.list().each { it->
            def p = it.menuAction.indexOf('/')
            if (p > -1) {
                def name = it.menuAction.substring(0, p)
                if (!(name in controllerList)) {
                    toDoList.add(name)
                }
            }
        }
        model:[
                noAction: noAction,
                toDo: toDoList
        ]
    }
}

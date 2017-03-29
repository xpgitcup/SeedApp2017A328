package cn.edu.cup.os

import cn.edu.cup.dictionary.JsFrame
import cn.edu.cup.system.SystemMenu
import cn.edu.cup.system.SystemMenuController
import grails.converters.JSON
import grails.transaction.Transactional

@Transactional(readOnly = true)
class Operation4SystemMenuController extends SystemMenuController{

    def treeViewService

    /*
    * 创建对象
    * */
    def createSystemMenu(SystemMenu systemMenu) {
        def newSystemMenu = new SystemMenu(upMenuItem: systemMenu)
        if (request.xhr) {
            render(template: 'editSystemMenu', model: [systemMenu: newSystemMenu])
        } else {
            respond newSystemMenu
        }
    }

    /*
    * 保存对象
    * */
    @Transactional
    def updateSystemMenu(SystemMenu systemMenu) {
        println("准备更新：${systemMenu}")
        systemMenu.save flush:true
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */
    def editSystemMenu(SystemMenu systemMenu) {
        if (request.xhr) {
            render(template: 'editSystemMenu', model: [systemMenu: systemMenu])
        } else {
            respond systemMenu
        }
    }

    /*
    * 统计根属性
    * */
    def countSystemMenu() {
        def count = SystemMenu.countByUpMenuItemIsNull()    //这是必须调整的
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
        //return count //就是不行
    }

    /*
    * 获取当前id对应的对象
    * */
    def getSystemMenu(SystemMenu systemMenu) {
        def theModel = [systemMenu: systemMenu]
        if (request.xhr) {
            render(template: "showSystemMenu", model:theModel)
        } else {
            theModel
        }
    }

    /*
    * 获取json格式的树形结构数据
    * */
    def getTreeSystemMenu() {
        def data = SystemMenu.findAllByUpMenuItemIsNull(params)     //这是必须调整的
        params.context = "menuContext"
        params.subItems = "menuItems"
        params.attributes = "id"    //
        def result = treeViewService.generateNodesString(data, params, JsFrame.EasyUI)
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() { }
}

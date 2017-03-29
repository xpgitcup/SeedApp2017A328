package cn.edu.cup.os

import cn.edu.cup.system.SystemLog
import cn.edu.cup.system.SystemLogController
import grails.converters.JSON
import grails.transaction.Transactional

@Transactional(readOnly = true)
class Operation4SystemLogController extends SystemLogController{

    /*
    * 列出对象
    * */
    def listSystemLog() {
        def systemLogList = SystemLog.list(params)
        if (request.xhr) {
            render(template: 'listSystemLog', model: [systemLogList: systemLogList])
        } else {
            respond newSystemLog
        }
    }

    /*
    * 创建对象
    * */
    def createSystemLog(SystemLog systemLog) {
        def newSystemLog = new SystemLog()
        if (request.xhr) {
            render(template: 'editSystemLog', model: [SystemLog: newSystemLog])
        } else {
            respond newSystemLog
        }
    }

    /*
    * 保存对象
    * */
    @Transactional
    def updateSystemLog(SystemLog systemLog) {
        println("准备更新：${systemLog}")
        systemLog.save flush:true
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */
    def editSystemLog(SystemLog systemLog) {
        if (request.xhr) {
            render(template: 'editSystemLog', model: [systemLog: systemLog])
        } else {
            respond SystemLog
        }
    }

    /*
    * 统计根属性
    * */
    def countSystemLog() {
        def count = SystemLog.count()    //这是必须调整的
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
    def getSystemLog(SystemLog systemLog) {
        def theModel = [systemLog: systemLog]
        println("${systemLog}")
        if (request.xhr) {
            render(template: "showSystemLog", model:theModel)
        } else {
            theModel
        }
    }

    def index() { }
}

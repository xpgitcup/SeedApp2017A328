package cn.edu.cup.os


class OperationSystemInterceptor {

    def systemCommonService
    def systemLogService

    def OperationSystemInterceptor() {
        def m = matchAll().excludes(controller: "home")
        m.excludes(controller: "systemLog")
        //m.excludes(controller: "operation4SystemLog")
        m.excludes(uri: "/")
    }

    boolean before() {
        println("${controllerName}，动作：${actionName}.之前...")
        if (!session.systemUser) {
            println("跳转...")
            redirect(controller: "home", action: "loginUI")
        } else {
            systemCommonService.updateSystemUserList(request)
            if (params.size()>0) {
                println("记录日志...")
                systemLogService.recordLog(session, request, params)
            }
        }
        //继续执行原来的命令
        true
    }

    boolean after() {
        println("控制器：${controllerName}，动作：${actionName}.之后...")
        true
    }

    void afterView() {
        // no-op
    }
}

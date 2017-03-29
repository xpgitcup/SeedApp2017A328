package cn.edu.cup.os

import cn.edu.cup.system.SystemUser

class HomeController {

    def systemCommonService
    def systemLogService

    private void listSystemMenu() {
        //根据用户的属性，设置菜单
        params.user = session.systemUser
        def systemMenuList = systemCommonService.getAllTopLevelMenus(params)
        println("${systemMenuList}")
        def subMenuItems = []
        systemMenuList.eachWithIndex { Object entry, int i ->
            def ms = []
            entry.menuItems.each() { e ->
                ms.add(e)
            }
            subMenuItems.add(ms)
        }
        //在会话中保存第二级菜单
        session.subMenuItems = subMenuItems
        //在会话中保存第一级菜单
        session.systemMenuList = systemMenuList
        println("${systemMenuList}")

    }

    /*
    * 退出登录
    * */
    def logout() {
        def pscontext = request.session.servletContext
        Map serviceMap = pscontext.getAttribute("systemUserList")
        if (session.user) {
            systemLogService.recordLog(session, request, params)
            serviceMap.remove(session.user.userName)
        }
        session.onlineCount = serviceMap.size()
        def logoutUser = session.user
        session.invalidate()
        //redirect(uri: "/")
        model: [logoutUser: logoutUser]
    }

    /*
    * 登录
    * */
    def login() {
        String userName = params.userName;
        String p = params.password.encodeAsMD5()
        def systemUser = SystemUser.findByUserNameAndPassword(userName, p)
        if (systemUser) {
            session.systemUser = systemUser
            //初始化用户菜单
            listSystemMenu()
            //在会话中登记用户
            registeUserInSession(systemUser)
            systemLogService.recordLog(session, request, params)
        }
        redirect(uri: "/")
    }

    /*
    * 显示登录界面
    * */
    def loginUI() {}

    /*
    * 登记登录用户
    * */
    def registeUserInSession(user) {
        def pscontext = request.session.servletContext
        Map serviceMap = pscontext.getAttribute("systemUserList")
        if (!serviceMap) {
            def systemUserList = new HashMap()
            pscontext.putAt("systemUserList", systemUserList)
            serviceMap = systemUserList
        }
        //登记用户
        serviceMap.putAt(user.userName, session)

        systemCommonService.updateSystemUserList(request)
    }

    def index() { }
}

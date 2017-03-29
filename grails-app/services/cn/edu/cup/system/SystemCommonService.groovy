package cn.edu.cup.system

import grails.transaction.Transactional

@Transactional
class SystemCommonService {

    def updateSystemUserList(request) {
        def pscontext = request.session.servletContext
        Map serviceMap = pscontext.getAttribute("systemUserList")
        if (serviceMap) {
            //当前在线人员列表
            def m = Math.min(5, serviceMap.size())
            def users = ""
            for (int i = 0; i < m; i++) {
                if (i != 0) {
                    users += ", "
                }
                users += serviceMap.keySet()[i]
            }
            if (serviceMap.size() > 5) {
                users += "..."
            }
            request.session.systemUserList = users
            //统计人数
            request.session.onlineCount = serviceMap.size()
            println("${users}")
        }
    }

    //获取顶级菜单---?????
    def getAllTopLevelMenus(params) {
        def q = SystemMenu.createCriteria()
        def systemMenuList = []
        if (params.user) {
            SystemUser user = params.user
            def roles = user.userRoles()
            println("${roles}")
            if (roles) {
                systemMenuList = q.list(params) {
                    isNull('upMenuItem')
                    'in'('roleAttribute', roles)
                    order('menuOrder')
                }
            }
        }
        return systemMenuList
    }

}

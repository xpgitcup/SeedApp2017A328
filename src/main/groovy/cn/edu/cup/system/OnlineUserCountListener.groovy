package cn.edu.cup.system

import javax.servlet.ServletContext
import javax.servlet.annotation.WebListener
import javax.servlet.http.HttpSession
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener

/**
 * Created by LiXiaoping on 2017/3/18.
 */
@WebListener
class OnlineUserCountListener  implements HttpSessionListener{
    @Override
    void sessionCreated(HttpSessionEvent se) {
        System.out.println("创建session......");
    }

    @Override
    void sessionDestroyed(HttpSessionEvent se) {
        HttpSession s = se.getSession();
        if (s != null) {
            ServletContext ctx = s.getServletContext();
            if (ctx != null) {
                Map serviceMap = (Map) ctx.getAttribute("systemUserList");
                if (serviceMap != null) {
                    SystemUser user = (SystemUser) serviceMap.get(s);
                    serviceMap.remove(s.id);
                    if (user != null) {
                        String userName = user.getUserName();
                        System.out.printf("remove %s\n.", userName);
                    }
                }
            }
        }
    }

}

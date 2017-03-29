package cn.edu.cup.system

import groovy.xml.MarkupBuilder

class SystemMenu {

    String menuContext
    String menuAction
    String menuDescription
    SystemMenu upMenuItem
    String  roleAttribute
    int menuOrder

    static hasMany = [menuItems: SystemMenu]

    static constraints = {
        menuContext()
        menuAction()
        menuDescription()
        menuOrder()
        upMenuItem(nullable: true)
        roleAttribute(nullable: true)
    }

    static mapping = {
        sort('menuOrder')
        sort('id')
        menuItems sort: 'menuOrder', 'id'  //这是排序的标准做法 
    }

    def beforeInsert() {
    }

    String toString() {
        return "${menuContext}()"
    }

    //------------------------------------------------------------------------------------------------------------------
    String hrefContext() {
        return "<a href=\'${menuAction}\'>${menuContext}</a>"
    }

    String menuItemRole() {
        def role = roleAttribute
        if ((role==null) && (this.upMenuItem!=null)) {
            def parentMenu = this.upMenuItem
            while ((role==null) && (parentMenu != null)) {
                role = parentMenu.roleAttribute
                parentMenu = parentMenu.upMenuItem
            }
        }
        return role
    }
}

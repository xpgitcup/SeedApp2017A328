package cn.edu.cup.system

import cn.edu.cup.dictionary.BaseDataType
import cn.edu.cup.dictionary.DataKey
import grails.core.GrailsApplication
import grails.transaction.Transactional

@Transactional
class InitService {

    def dataSource

    //加载数据库初始化脚本
    def loadScripts(String dir) {
        def File sf = new File(dir)
        println "load scripts ${dir}"
        if (sf.exists()) {
            if (sf) {
                sf.eachFile { f ->
                    if (f.isFile()) {
                        executeScript(f)
                    }
                }
            }
        }
    }

    //执行附加脚本
    def executeScript(File sf) {
        //def File sf = new File(fileName)
        println "init - ${sf}"
        if (sf) {
            def db
            def sql = sf.text
            db = new groovy.sql.Sql(dataSource)
            //println "init - ${sql}"
            def lines = sql.split(";")
            lines.each() { it ->
                //println "line: ${it}"
                it = it.trim()
                if (!it.isEmpty()) {
                    db.executeUpdate(it)
                }
            }
        }
    }

    /*
    * 初始化系统数据
    * */
    def initSystemData(domains) {
        println("初始化系统数据......")
        initSystemUsers()
        initSystemMenuItems(domains)
    }

    /*
    * 初始化系统菜单
    * */
    def initSystemMenuItems(domains) {
        if (SystemMenu.count() < 1) {
            def m0 = new SystemMenu(
                    menuContext: "底层编辑",
                    menuAction: "#",
                    menuDescription: "对系统的菜单结构进行底层维护",
                    upMenuItem: null,
                    roleAttribute: "底层管理",
                    menuOrder: 0
            )
            m0.save(true)
            //----------------------------------------------------------------------------------------------------------
            domains.each() { e ->
                def m01 = new SystemMenu(
                        menuContext: "${e.name}",
                        menuAction: "${e.name}/index",
                        menuDescription: "对${e.name}属性进行维护",
                        upMenuItem: m0,
                        roleAttribute: "底层管理",
                        menuOrder: 0
                )
                m01.save(true)
            }
            //----------------------------------------------------------------------------------------------------------
            def m1 = new SystemMenu(
                    menuContext: "系统维护",
                    menuAction: "#",
                    menuDescription: "对系统的菜单结构进行用户友好的维护",
                    upMenuItem: null,
                    roleAttribute: "系统维护",
                    menuOrder: 0
            )
            m1.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m11 = new SystemMenu(
                    menuContext: "属性维护",
                    menuAction: "operation4SystemAttribute/index",
                    menuDescription: "对系统的用户属性进行用户友好的维护",
                    upMenuItem: m1,
                    menuOrder: 0
            )
            m11.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m12 = new SystemMenu(
                    menuContext: "用户维护",
                    menuAction: "operation4SystemUser/index",
                    menuDescription: "对系统的用户进行用户友好的维护",
                    upMenuItem: m1,
                    menuOrder: 0
            )
            m12.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m13 = new SystemMenu(
                    menuContext: "菜单维护",
                    menuAction: "operation4SystemMenu/index",
                    menuDescription: "对系统的菜单用户进行用户友好的维护",
                    upMenuItem: m1,
                    menuOrder: 0
            )
            m13.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m14 = new SystemMenu(
                    menuContext: "日志维护",
                    menuAction: "operation4SystemLog/index",
                    menuDescription: "对系统的日志进行用户友好的维护",
                    upMenuItem: m1,
                    menuOrder: 0
            )
            m14.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m2 = new SystemMenu(
                    menuContext: "公共服务",
                    menuAction: "#",
                    menuDescription: "对所有用户开放的功能",
                    upMenuItem: null,
                    roleAttribute: "公共服务",
                    menuOrder: 0
            )
            m2.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m21 = new SystemMenu(
                    menuContext: "社区沟通",
                    menuAction: "operation4SystemChat/index",
                    menuDescription: "与系统中的用户对话",
                    upMenuItem: m2,
                    menuOrder: 0
            )
            m21.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m3 = new SystemMenu(
                    menuContext: "基础数据",
                    menuAction: "#",
                    menuDescription: "维护数据字典+物理单位",
                    upMenuItem: null,
                    roleAttribute: "系统维护",
                    menuOrder: 0
            )
            m3.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m31 = new SystemMenu(
                    menuContext: "数据字典",
                    menuAction: "operation4DataKey/index",
                    menuDescription: "数据字典维护",
                    upMenuItem: m3,
                    menuOrder: 0
            )
            m31.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m32 = new SystemMenu(
                    menuContext: "数据维护",
                    menuAction: "operation4DataItem/index",
                    menuDescription: "数据维护",
                    upMenuItem: m3,
                    menuOrder: 0
            )
            m32.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m33 = new SystemMenu(
                    menuContext: "单位维护",
                    menuAction: "operation4Physical/index",
                    menuDescription: "物理单位维护",
                    upMenuItem: m3,
                    menuOrder: 0
            )
            m33.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m34 = new SystemMenu(
                    menuContext: "数据字典维护",
                    menuAction: "maintain4Dictionary/index",
                    menuDescription: "维护数据字典",
                    upMenuItem: m3,
                    menuOrder: 0
            )
            m34.save(true)
            //----------------------------------------------------------------------------------------------------------
        }
    }

    /**
     * 初始化用户数据
     **/
    def initSystemUsers() {
        if (SystemUser.count() < 1) {
            def u0 = new SystemUser(userName: "root", password: "2151", roleAttribute: '底层管理 系统维护 公共服务')
            u0.save(true)
            def u1 = new SystemUser(userName: "李晓平", password: "2151", roleAttribute: '底层管理 系统维护 公共服务')
            u1.save(true)
            def u2 = new SystemUser(userName: "吴海浩", password: "3181", roleAttribute: '底层管理 系统维护 公共服务')
            u2.save(true)
            def u3 = new SystemUser(userName: "康琦", password: "527111", roleAttribute: '底层管理 系统维护 公共服务')
            u3.save(true)
            def u4 = new SystemUser(userName: "石国赟", password: "527111", roleAttribute: '底层管理 系统维护 公共服务')
            u4.save(true)
            def u5 = new SystemUser(userName: "宫敬", password: "2156", roleAttribute: '底层管理 系统维护 公共服务')
            u5.save(true)
        }
    }

    /**
     * 填充测试数据
     */
    def fillSamples() {
        println("填充测试数据......")
        //用户
        fillSampleUsers()
        //属性
        fileSampleAttributes()
        //菜单
        fillSampleMenus()
        //对话
        fillSampleChat()
        //数据字典
        fillSampleDataKey()
        //
        fillSampleTitle()
    }

    def fillSampleTitle() {
        println("初始化系统标题......")
        if (SystemTitle.count()<1) {
            def systemTitle = new SystemTitle(
                    applicationTitle: "种子程序",
                    applicationLogo: "cuplogoA.png"
            )
            systemTitle.save(true)
        }
    }

    private void fillSampleDataKey() {
        println("测试数据字典的数据...")
        for (int i=0; i<30; i++) {
            def d = new DataKey(
                    keyContext: "key${i}",
                    dataValueType: BaseDataType.project
            )
            d.save(true)
            for (int j=0; j<3; j++) {
                def dd = new DataKey(
                        keyContext: "key${i},${j}",
                        dataValueType: BaseDataType.projectCase,
                        upKey: d
                )
                dd.save(true)
                for (int k=0; k<3; k++) {
                    def ddd = new DataKey(
                            keyContext: "key${i},${j},${k}",
                            dataValueType: BaseDataType.dataModel,
                            upKey: dd
                    )
                    ddd.save(true)
                }
            }
        }
    }

    private void fillSampleChat() {
        for (int i = 0; i < 20; i++) {
            def c = new SystemChat(
                    speaker: "李晓平",
                    speakTo: "张${i}李${i + 1}",
                    message: " I speak to 张${i}李${i + 1} that ${i * i}",
                    startTime: new Date()
            )
            c.save(true)
            def d = new SystemChat(
                    speakTo: "李晓平",
                    speaker: "张${i}李${i + 1}",
                    message: "张${i}李${i + 1} tell me ${i * i}",
                    startTime: new Date()
            )
            d.save(true)
        }
    }

    private void fillSampleMenus() {
        if (SystemMenu.count() < 1) {
            println("填充测试性的菜单数据......")

            println("${GrailsApplication.simpleName}")
            println("${GrailsApplication.APPLICATION_ID}")
            println("${Application.properties.name}")
            //println("${Application.metaPropertyValues}")

            for (int i = 0; i < 10; i++) {
                def mm = new SystemMenu(
                        menuContext: "菜单${i}",
                        menuAction: "动作${i}",
                        menuDescription: "描述：${i}",
                        upMenuItem: null,
                        menuOrder: i
                )
                mm.save(true)

                for (int j = 0; j < i; j++) {
                    def mmn = new SystemMenu(
                            menuContext: "菜单${i}.${j}",
                            menuAction: "动作${i}.${j}",
                            menuDescription: "描述：${i}.${j}",
                            upMenuItem: mm,
                            menuOrder: j
                    )
                    mmn.save(true)
                }
            }
        }
    }

    private void fileSampleAttributes() {
        if (SystemAttribute.count() < 1) {
            println("测试性的属性...")
            def attributeA = new SystemAttribute(name: "系统操作权限")
            attributeA.save(true)
            for (int i = 0; i < 10; i++) {
                def aa = new SystemAttribute(name: "权限${i}", upAttribute: attributeA)
                aa.save(true)
            }
            def attributeB = new SystemAttribute(name: "读取权限")
            attributeB.save(true)
            for (int i = 0; i < 10; i++) {
                def aa = new SystemAttribute(name: "读取权限${i}", upAttribute: attributeB)
                aa.save(true)
            }
        }
    }

    private void fillSampleUsers() {
        println("测试性的用户...")
        for (int i = 0; i < 20; i++) {
            def u = new SystemUser(userName: "张${i}李${i + 1}", password: "1")
            u.save(true)
        }
    }

}

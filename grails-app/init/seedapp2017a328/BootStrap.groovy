package seedapp2017a328

class BootStrap {

    def initService
    def grailsApplication

    /**
     * 初始化...
     */
    def init = { servletContext ->
        environments {
            development {
                def domains = grailsApplication.controllerClasses
                initService.initSystemData(domains)
                initService.fillSamples()
                configureForDevelopment(servletContext);
            }
            production {
                configureForDevelopment(servletContext);
            }
        }
    }

    /**
     * 撤销、退出
     */
    def destroy = {
    }

    /**
     * 初始化代码__开发环境下的初始化代码
     */
    def configureForDevelopment(servletContext) {
        println "这是开发环境..."
        def webRootDir = servletContext.getRealPath("/")
        def scriptPath = "${webRootDir}scripts/system"
        println "BootStrap ${webRootDir}"
        initService.loadScripts(scriptPath)
    }

    /**
     * 发布后的初始化代码
     */
    def configureForProduction() {
        println "这是发布环境..."
        def webRootDir = servletContext.getRealPath("/")
        def scriptPath = "${webRootDir}scripts/system"
        println "BootStrap ${webRootDir}"
        initService.loadScripts(scriptPath)
    }

}
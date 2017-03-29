package cn.edu.cup.system

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SystemDefaultMenuController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SystemDefaultMenu.list(params), model:[systemDefaultMenuCount: SystemDefaultMenu.count()]
    }

    def show(SystemDefaultMenu systemDefaultMenu) {
        respond systemDefaultMenu
    }

    def create() {
        respond new SystemDefaultMenu(params)
    }

    @Transactional
    def save(SystemDefaultMenu systemDefaultMenu) {
        if (systemDefaultMenu == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemDefaultMenu.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemDefaultMenu.errors, view:'create'
            return
        }

        systemDefaultMenu.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemDefaultMenu.label', default: 'SystemDefaultMenu'), systemDefaultMenu.id])
                redirect systemDefaultMenu
            }
            '*' { respond systemDefaultMenu, [status: CREATED] }
        }
    }

    def edit(SystemDefaultMenu systemDefaultMenu) {
        respond systemDefaultMenu
    }

    @Transactional
    def update(SystemDefaultMenu systemDefaultMenu) {
        if (systemDefaultMenu == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemDefaultMenu.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemDefaultMenu.errors, view:'edit'
            return
        }

        systemDefaultMenu.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemDefaultMenu.label', default: 'SystemDefaultMenu'), systemDefaultMenu.id])
                redirect systemDefaultMenu
            }
            '*'{ respond systemDefaultMenu, [status: OK] }
        }
    }

    @Transactional
    def delete(SystemDefaultMenu systemDefaultMenu) {

        if (systemDefaultMenu == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        systemDefaultMenu.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemDefaultMenu.label', default: 'SystemDefaultMenu'), systemDefaultMenu.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemDefaultMenu.label', default: 'SystemDefaultMenu'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

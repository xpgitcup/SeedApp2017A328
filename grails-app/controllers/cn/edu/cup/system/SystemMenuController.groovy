package cn.edu.cup.system

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SystemMenuController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SystemMenu.list(params), model:[systemMenuCount: SystemMenu.count()]
    }

    def show(SystemMenu systemMenu) {
        respond systemMenu
    }

    def create() {
        respond new SystemMenu(params)
    }

    @Transactional
    def save(SystemMenu systemMenu) {
        if (systemMenu == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemMenu.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemMenu.errors, view:'create'
            return
        }

        systemMenu.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemMenu.label', default: 'SystemMenu'), systemMenu.id])
                redirect systemMenu
            }
            '*' { respond systemMenu, [status: CREATED] }
        }
    }

    def edit(SystemMenu systemMenu) {
        respond systemMenu
    }

    @Transactional
    def update(SystemMenu systemMenu) {
        if (systemMenu == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemMenu.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemMenu.errors, view:'edit'
            return
        }

        systemMenu.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemMenu.label', default: 'SystemMenu'), systemMenu.id])
                redirect systemMenu
            }
            '*'{ respond systemMenu, [status: OK] }
        }
    }

    @Transactional
    def delete(SystemMenu systemMenu) {

        if (systemMenu == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        systemMenu.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemMenu.label', default: 'SystemMenu'), systemMenu.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemMenu.label', default: 'SystemMenu'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

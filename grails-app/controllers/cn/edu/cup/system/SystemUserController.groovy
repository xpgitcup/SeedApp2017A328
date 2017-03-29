package cn.edu.cup.system

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SystemUserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SystemUser.list(params), model:[systemUserCount: SystemUser.count()]
    }

    def show(SystemUser systemUser) {
        respond systemUser
    }

    def create() {
        respond new SystemUser(params)
    }

    @Transactional
    def save(SystemUser systemUser) {
        if (systemUser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemUser.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemUser.errors, view:'create'
            return
        }

        systemUser.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemUser.label', default: 'SystemUser'), systemUser.id])
                redirect systemUser
            }
            '*' { respond systemUser, [status: CREATED] }
        }
    }

    def edit(SystemUser systemUser) {
        respond systemUser
    }

    @Transactional
    def update(SystemUser systemUser) {
        if (systemUser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemUser.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemUser.errors, view:'edit'
            return
        }

        systemUser.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemUser.label', default: 'SystemUser'), systemUser.id])
                redirect systemUser
            }
            '*'{ respond systemUser, [status: OK] }
        }
    }

    @Transactional
    def delete(SystemUser systemUser) {

        if (systemUser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        systemUser.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemUser.label', default: 'SystemUser'), systemUser.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemUser.label', default: 'SystemUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

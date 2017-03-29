package cn.edu.cup.system

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SystemChatController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SystemChat.list(params), model:[systemChatCount: SystemChat.count()]
    }

    def show(SystemChat systemChat) {
        respond systemChat
    }

    def create() {
        respond new SystemChat(params)
    }

    @Transactional
    def save(SystemChat systemChat) {
        if (systemChat == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemChat.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemChat.errors, view:'create'
            return
        }

        systemChat.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemChat.label', default: 'SystemChat'), systemChat.id])
                redirect systemChat
            }
            '*' { respond systemChat, [status: CREATED] }
        }
    }

    def edit(SystemChat systemChat) {
        respond systemChat
    }

    @Transactional
    def update(SystemChat systemChat) {
        if (systemChat == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemChat.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemChat.errors, view:'edit'
            return
        }

        systemChat.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemChat.label', default: 'SystemChat'), systemChat.id])
                redirect systemChat
            }
            '*'{ respond systemChat, [status: OK] }
        }
    }

    @Transactional
    def delete(SystemChat systemChat) {

        if (systemChat == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        systemChat.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemChat.label', default: 'SystemChat'), systemChat.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemChat.label', default: 'SystemChat'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

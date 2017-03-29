package cn.edu.cup.system

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SystemTitleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SystemTitle.list(params), model:[systemTitleCount: SystemTitle.count()]
    }

    def show(SystemTitle systemTitle) {
        respond systemTitle
    }

    def create() {
        respond new SystemTitle(params)
    }

    @Transactional
    def save(SystemTitle systemTitle) {
        if (systemTitle == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemTitle.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemTitle.errors, view:'create'
            return
        }

        systemTitle.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemTitle.label', default: 'SystemTitle'), systemTitle.id])
                redirect systemTitle
            }
            '*' { respond systemTitle, [status: CREATED] }
        }
    }

    def edit(SystemTitle systemTitle) {
        respond systemTitle
    }

    @Transactional
    def update(SystemTitle systemTitle) {
        if (systemTitle == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemTitle.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemTitle.errors, view:'edit'
            return
        }

        systemTitle.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemTitle.label', default: 'SystemTitle'), systemTitle.id])
                redirect systemTitle
            }
            '*'{ respond systemTitle, [status: OK] }
        }
    }

    @Transactional
    def delete(SystemTitle systemTitle) {

        if (systemTitle == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        systemTitle.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemTitle.label', default: 'SystemTitle'), systemTitle.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemTitle.label', default: 'SystemTitle'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

package cn.edu.cup.system

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SystemAttributeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SystemAttribute.list(params), model:[systemAttributeCount: SystemAttribute.count()]
    }

    def show(SystemAttribute systemAttribute) {
        respond systemAttribute
    }

    def create() {
        respond new SystemAttribute(params)
    }

    @Transactional
    def save(SystemAttribute systemAttribute) {
        if (systemAttribute == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemAttribute.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemAttribute.errors, view:'create'
            return
        }

        systemAttribute.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemAttribute.label', default: 'SystemAttribute'), systemAttribute.id])
                redirect systemAttribute
            }
            '*' { respond systemAttribute, [status: CREATED] }
        }
    }

    def edit(SystemAttribute systemAttribute) {
        respond systemAttribute
    }

    @Transactional
    def update(SystemAttribute systemAttribute) {
        if (systemAttribute == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemAttribute.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemAttribute.errors, view:'edit'
            return
        }

        systemAttribute.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemAttribute.label', default: 'SystemAttribute'), systemAttribute.id])
                redirect systemAttribute
            }
            '*'{ respond systemAttribute, [status: OK] }
        }
    }

    @Transactional
    def delete(SystemAttribute systemAttribute) {

        if (systemAttribute == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        systemAttribute.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemAttribute.label', default: 'SystemAttribute'), systemAttribute.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemAttribute.label', default: 'SystemAttribute'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

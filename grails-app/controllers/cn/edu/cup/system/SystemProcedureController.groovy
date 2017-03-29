package cn.edu.cup.system

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SystemProcedureController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SystemProcedure.list(params), model:[systemProcedureCount: SystemProcedure.count()]
    }

    def show(SystemProcedure systemProcedure) {
        respond systemProcedure
    }

    def create() {
        respond new SystemProcedure(params)
    }

    @Transactional
    def save(SystemProcedure systemProcedure) {
        if (systemProcedure == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemProcedure.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemProcedure.errors, view:'create'
            return
        }

        systemProcedure.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemProcedure.label', default: 'SystemProcedure'), systemProcedure.id])
                redirect systemProcedure
            }
            '*' { respond systemProcedure, [status: CREATED] }
        }
    }

    def edit(SystemProcedure systemProcedure) {
        respond systemProcedure
    }

    @Transactional
    def update(SystemProcedure systemProcedure) {
        if (systemProcedure == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemProcedure.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemProcedure.errors, view:'edit'
            return
        }

        systemProcedure.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemProcedure.label', default: 'SystemProcedure'), systemProcedure.id])
                redirect systemProcedure
            }
            '*'{ respond systemProcedure, [status: OK] }
        }
    }

    @Transactional
    def delete(SystemProcedure systemProcedure) {

        if (systemProcedure == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        systemProcedure.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemProcedure.label', default: 'SystemProcedure'), systemProcedure.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemProcedure.label', default: 'SystemProcedure'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

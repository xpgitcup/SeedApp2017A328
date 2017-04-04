package cn.edu.cup.physical

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PhysicalQuantityController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PhysicalQuantity.list(params), model:[physicalQuantityCount: PhysicalQuantity.count()]
    }

    def show(PhysicalQuantity physicalQuantity) {
        respond physicalQuantity
    }

    def create() {
        respond new PhysicalQuantity(params)
    }

    @Transactional
    def save(PhysicalQuantity physicalQuantity) {
        if (physicalQuantity == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (physicalQuantity.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond physicalQuantity.errors, view:'create'
            return
        }

        physicalQuantity.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'physicalQuantity.label', default: 'PhysicalQuantity'), physicalQuantity.id])
                redirect physicalQuantity
            }
            '*' { respond physicalQuantity, [status: CREATED] }
        }
    }

    def edit(PhysicalQuantity physicalQuantity) {
        respond physicalQuantity
    }

    @Transactional
    def update(PhysicalQuantity physicalQuantity) {
        if (physicalQuantity == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (physicalQuantity.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond physicalQuantity.errors, view:'edit'
            return
        }

        physicalQuantity.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'physicalQuantity.label', default: 'PhysicalQuantity'), physicalQuantity.id])
                redirect physicalQuantity
            }
            '*'{ respond physicalQuantity, [status: OK] }
        }
    }

    @Transactional
    def delete(PhysicalQuantity physicalQuantity) {

        if (physicalQuantity == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        physicalQuantity.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'physicalQuantity.label', default: 'PhysicalQuantity'), physicalQuantity.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'physicalQuantity.label', default: 'PhysicalQuantity'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

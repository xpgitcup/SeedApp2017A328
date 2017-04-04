package cn.edu.cup.physical

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class QuantityUnitController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond QuantityUnit.list(params), model:[quantityUnitCount: QuantityUnit.count()]
    }

    def show(QuantityUnit quantityUnit) {
        respond quantityUnit
    }

    def create() {
        respond new QuantityUnit(params)
    }

    @Transactional
    def save(QuantityUnit quantityUnit) {
        if (quantityUnit == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (quantityUnit.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond quantityUnit.errors, view:'create'
            return
        }

        quantityUnit.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'quantityUnit.label', default: 'QuantityUnit'), quantityUnit.id])
                redirect quantityUnit
            }
            '*' { respond quantityUnit, [status: CREATED] }
        }
    }

    def edit(QuantityUnit quantityUnit) {
        respond quantityUnit
    }

    @Transactional
    def update(QuantityUnit quantityUnit) {
        if (quantityUnit == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (quantityUnit.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond quantityUnit.errors, view:'edit'
            return
        }

        quantityUnit.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'quantityUnit.label', default: 'QuantityUnit'), quantityUnit.id])
                redirect quantityUnit
            }
            '*'{ respond quantityUnit, [status: OK] }
        }
    }

    @Transactional
    def delete(QuantityUnit quantityUnit) {

        if (quantityUnit == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        quantityUnit.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'quantityUnit.label', default: 'QuantityUnit'), quantityUnit.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'quantityUnit.label', default: 'QuantityUnit'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

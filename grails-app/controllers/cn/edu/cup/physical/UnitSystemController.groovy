package cn.edu.cup.physical

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UnitSystemController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UnitSystem.list(params), model:[unitSystemCount: UnitSystem.count()]
    }

    def show(UnitSystem unitSystem) {
        respond unitSystem
    }

    def create() {
        respond new UnitSystem(params)
    }

    @Transactional
    def save(UnitSystem unitSystem) {
        if (unitSystem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (unitSystem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond unitSystem.errors, view:'create'
            return
        }

        unitSystem.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'unitSystem.label', default: 'UnitSystem'), unitSystem.id])
                redirect unitSystem
            }
            '*' { respond unitSystem, [status: CREATED] }
        }
    }

    def edit(UnitSystem unitSystem) {
        respond unitSystem
    }

    @Transactional
    def update(UnitSystem unitSystem) {
        if (unitSystem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (unitSystem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond unitSystem.errors, view:'edit'
            return
        }

        unitSystem.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'unitSystem.label', default: 'UnitSystem'), unitSystem.id])
                redirect unitSystem
            }
            '*'{ respond unitSystem, [status: OK] }
        }
    }

    @Transactional
    def delete(UnitSystem unitSystem) {

        if (unitSystem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        unitSystem.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'unitSystem.label', default: 'UnitSystem'), unitSystem.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'unitSystem.label', default: 'UnitSystem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

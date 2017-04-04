package cn.edu.cup.dictionary

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DataKeyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DataKey.list(params), model:[dataKeyCount: DataKey.count()]
    }

    def show(DataKey dataKey) {
        respond dataKey
    }

    def create() {
        respond new DataKey(params)
    }

    @Transactional
    def save(DataKey dataKey) {
        if (dataKey == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataKey.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataKey.errors, view:'create'
            return
        }

        dataKey.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataKey.label', default: 'DataKey'), dataKey.id])
                redirect dataKey
            }
            '*' { respond dataKey, [status: CREATED] }
        }
    }

    def edit(DataKey dataKey) {
        respond dataKey
    }

    @Transactional
    def update(DataKey dataKey) {
        if (dataKey == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataKey.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataKey.errors, view:'edit'
            return
        }

        dataKey.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dataKey.label', default: 'DataKey'), dataKey.id])
                redirect dataKey
            }
            '*'{ respond dataKey, [status: OK] }
        }
    }

    @Transactional
    def delete(DataKey dataKey) {

        if (dataKey == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        dataKey.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dataKey.label', default: 'DataKey'), dataKey.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataKey.label', default: 'DataKey'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

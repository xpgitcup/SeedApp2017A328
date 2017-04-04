package cn.edu.cup.dictionary

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DataItemController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DataItem.list(params), model:[dataItemCount: DataItem.count()]
    }

    def show(DataItem dataItem) {
        respond dataItem
    }

    def create() {
        respond new DataItem(params)
    }

    @Transactional
    def save(DataItem dataItem) {
        if (dataItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataItem.errors, view:'create'
            return
        }

        dataItem.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataItem.label', default: 'DataItem'), dataItem.id])
                redirect dataItem
            }
            '*' { respond dataItem, [status: CREATED] }
        }
    }

    def edit(DataItem dataItem) {
        respond dataItem
    }

    @Transactional
    def update(DataItem dataItem) {
        if (dataItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataItem.errors, view:'edit'
            return
        }

        dataItem.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dataItem.label', default: 'DataItem'), dataItem.id])
                redirect dataItem
            }
            '*'{ respond dataItem, [status: OK] }
        }
    }

    @Transactional
    def delete(DataItem dataItem) {

        if (dataItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        dataItem.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dataItem.label', default: 'DataItem'), dataItem.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataItem.label', default: 'DataItem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

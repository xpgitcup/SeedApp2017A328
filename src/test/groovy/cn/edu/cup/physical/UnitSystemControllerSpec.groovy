package cn.edu.cup.physical

import grails.test.mixin.*
import spock.lang.*

@TestFor(UnitSystemController)
@Mock(UnitSystem)
class UnitSystemControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.unitSystemList
            model.unitSystemCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.unitSystem!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def unitSystem = new UnitSystem()
            unitSystem.validate()
            controller.save(unitSystem)

        then:"The create view is rendered again with the correct model"
            model.unitSystem!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            unitSystem = new UnitSystem(params)

            controller.save(unitSystem)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/unitSystem/show/1'
            controller.flash.message != null
            UnitSystem.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def unitSystem = new UnitSystem(params)
            controller.show(unitSystem)

        then:"A model is populated containing the domain instance"
            model.unitSystem == unitSystem
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def unitSystem = new UnitSystem(params)
            controller.edit(unitSystem)

        then:"A model is populated containing the domain instance"
            model.unitSystem == unitSystem
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/unitSystem/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def unitSystem = new UnitSystem()
            unitSystem.validate()
            controller.update(unitSystem)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.unitSystem == unitSystem

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            unitSystem = new UnitSystem(params).save(flush: true)
            controller.update(unitSystem)

        then:"A redirect is issued to the show action"
            unitSystem != null
            response.redirectedUrl == "/unitSystem/show/$unitSystem.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/unitSystem/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def unitSystem = new UnitSystem(params).save(flush: true)

        then:"It exists"
            UnitSystem.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(unitSystem)

        then:"The instance is deleted"
            UnitSystem.count() == 0
            response.redirectedUrl == '/unitSystem/index'
            flash.message != null
    }
}

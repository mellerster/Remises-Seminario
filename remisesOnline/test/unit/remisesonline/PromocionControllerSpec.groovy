package remisesonline



import grails.test.mixin.*
import spock.lang.*

@TestFor(PromocionController)
@Mock(Promocion)
class PromocionControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.promocionInstanceList
            model.promocionInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.promocionInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            def promocion = new Promocion()
            promocion.validate()
            controller.save(promocion)

        then:"The create view is rendered again with the correct model"
            model.promocionInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            promocion = new Promocion(params)

            controller.save(promocion)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/promocion/show/1'
            controller.flash.message != null
            Promocion.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def promocion = new Promocion(params)
            controller.show(promocion)

        then:"A model is populated containing the domain instance"
            model.promocionInstance == promocion
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def promocion = new Promocion(params)
            controller.edit(promocion)

        then:"A model is populated containing the domain instance"
            model.promocionInstance == promocion
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/promocion/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def promocion = new Promocion()
            promocion.validate()
            controller.update(promocion)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.promocionInstance == promocion

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            promocion = new Promocion(params).save(flush: true)
            controller.update(promocion)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/promocion/show/$promocion.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/promocion/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def promocion = new Promocion(params).save(flush: true)

        then:"It exists"
            Promocion.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(promocion)

        then:"The instance is deleted"
            Promocion.count() == 0
            response.redirectedUrl == '/promocion/index'
            flash.message != null
    }
}

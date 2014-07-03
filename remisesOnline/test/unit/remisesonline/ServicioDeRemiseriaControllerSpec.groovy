package remisesonline



import grails.test.mixin.*
import spock.lang.*

@TestFor(ServicioDeRemiseriaController)
@Mock(ServicioDeRemiseria)
class ServicioDeRemiseriaControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.servicioDeRemiseriaInstanceList
            model.servicioDeRemiseriaInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.servicioDeRemiseriaInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            def servicioDeRemiseria = new ServicioDeRemiseria()
            servicioDeRemiseria.validate()
            controller.save(servicioDeRemiseria)

        then:"The create view is rendered again with the correct model"
            model.servicioDeRemiseriaInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            servicioDeRemiseria = new ServicioDeRemiseria(params)

            controller.save(servicioDeRemiseria)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/servicioDeRemiseria/show/1'
            controller.flash.message != null
            ServicioDeRemiseria.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def servicioDeRemiseria = new ServicioDeRemiseria(params)
            controller.show(servicioDeRemiseria)

        then:"A model is populated containing the domain instance"
            model.servicioDeRemiseriaInstance == servicioDeRemiseria
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def servicioDeRemiseria = new ServicioDeRemiseria(params)
            controller.edit(servicioDeRemiseria)

        then:"A model is populated containing the domain instance"
            model.servicioDeRemiseriaInstance == servicioDeRemiseria
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/servicioDeRemiseria/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def servicioDeRemiseria = new ServicioDeRemiseria()
            servicioDeRemiseria.validate()
            controller.update(servicioDeRemiseria)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.servicioDeRemiseriaInstance == servicioDeRemiseria

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            servicioDeRemiseria = new ServicioDeRemiseria(params).save(flush: true)
            controller.update(servicioDeRemiseria)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/servicioDeRemiseria/show/$servicioDeRemiseria.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/servicioDeRemiseria/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def servicioDeRemiseria = new ServicioDeRemiseria(params).save(flush: true)

        then:"It exists"
            ServicioDeRemiseria.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(servicioDeRemiseria)

        then:"The instance is deleted"
            ServicioDeRemiseria.count() == 0
            response.redirectedUrl == '/servicioDeRemiseria/index'
            flash.message != null
    }
}

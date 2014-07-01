package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SolicitudAcompaniamientoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SolicitudAcompaniamiento.list(params), model:[solicitudAcompaniamientoInstanceCount: SolicitudAcompaniamiento.count()]
    }

    def show(SolicitudAcompaniamiento solicitudAcompaniamientoInstance) {
        respond solicitudAcompaniamientoInstance
    }

    def create() {
        respond new SolicitudAcompaniamiento(params)
    }

    @Transactional
    def save(SolicitudAcompaniamiento solicitudAcompaniamientoInstance) {
        if (solicitudAcompaniamientoInstance == null) {
            notFound()
            return
        }

        if (solicitudAcompaniamientoInstance.hasErrors()) {
            respond solicitudAcompaniamientoInstance.errors, view:'create'
            return
        }

        solicitudAcompaniamientoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'solicitudAcompaniamientoInstance.label', default: 'SolicitudAcompaniamiento'), solicitudAcompaniamientoInstance.id])
                redirect solicitudAcompaniamientoInstance
            }
            '*' { respond solicitudAcompaniamientoInstance, [status: CREATED] }
        }
    }

    def edit(SolicitudAcompaniamiento solicitudAcompaniamientoInstance) {
        respond solicitudAcompaniamientoInstance
    }

    @Transactional
    def update(SolicitudAcompaniamiento solicitudAcompaniamientoInstance) {
        if (solicitudAcompaniamientoInstance == null) {
            notFound()
            return
        }

        if (solicitudAcompaniamientoInstance.hasErrors()) {
            respond solicitudAcompaniamientoInstance.errors, view:'edit'
            return
        }

        solicitudAcompaniamientoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'SolicitudAcompaniamiento.label', default: 'SolicitudAcompaniamiento'), solicitudAcompaniamientoInstance.id])
                redirect solicitudAcompaniamientoInstance
            }
            '*'{ respond solicitudAcompaniamientoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(SolicitudAcompaniamiento solicitudAcompaniamientoInstance) {

        if (solicitudAcompaniamientoInstance == null) {
            notFound()
            return
        }

        solicitudAcompaniamientoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'SolicitudAcompaniamiento.label', default: 'SolicitudAcompaniamiento'), solicitudAcompaniamientoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudAcompaniamientoInstance.label', default: 'SolicitudAcompaniamiento'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SolicitudAmistadController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SolicitudAmistad.list(params), model:[solicitudAmistadInstanceCount: SolicitudAmistad.count()]
    }

    def show(SolicitudAmistad solicitudAmistadInstance) {
        respond solicitudAmistadInstance
    }

    def create() {
        respond new SolicitudAmistad(params)
    }

    @Transactional
    def save(SolicitudAmistad solicitudAmistadInstance) {
        if (solicitudAmistadInstance == null) {
            notFound()
            return
        }

		solicitudAmistadInstance.pasajero =	Pasajero.get(session.pasajero.id)
		solicitudAmistadInstance.fechaCreada =	new Date()
		solicitudAmistadInstance.estado = 'Pendiente'
		solicitudAmistadInstance.validate()
		
        if (solicitudAmistadInstance.hasErrors()) {
            respond solicitudAmistadInstance.errors, view:'create'
            return
        }

        solicitudAmistadInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'solicitudAmistadInstance.label', default: 'SolicitudAmistad'), solicitudAmistadInstance.id])
                redirect solicitudAmistadInstance
            }
            '*' { respond solicitudAmistadInstance, [status: CREATED] }
        }
    }

    def edit(SolicitudAmistad solicitudAmistadInstance) {
        respond solicitudAmistadInstance
    }

    @Transactional
    def update(SolicitudAmistad solicitudAmistadInstance) {
        if (solicitudAmistadInstance == null) {
            notFound()
            return
        }

        if (solicitudAmistadInstance.hasErrors()) {
            respond solicitudAmistadInstance.errors, view:'edit'
            return
        }

        solicitudAmistadInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'SolicitudAmistad.label', default: 'SolicitudAmistad'), solicitudAmistadInstance.id])
                redirect solicitudAmistadInstance
            }
            '*'{ respond solicitudAmistadInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(SolicitudAmistad solicitudAmistadInstance) {

        if (solicitudAmistadInstance == null) {
            notFound()
            return
        }

        solicitudAmistadInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'SolicitudAmistad.label', default: 'SolicitudAmistad'), solicitudAmistadInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudAmistadInstance.label', default: 'SolicitudAmistad'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
}

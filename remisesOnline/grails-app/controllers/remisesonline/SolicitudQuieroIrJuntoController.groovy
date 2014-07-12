package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class SolicitudQuieroIrJuntoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SolicitudQuieroIrJunto.list(params), model:[solicitudQuieroIrJuntoInstanceCount: SolicitudQuieroIrJunto.count()]
    }

    def show(SolicitudQuieroIrJunto solicitudQuieroIrJuntoInstance) {
        respond solicitudQuieroIrJuntoInstance
    }

    def create() {
        respond new SolicitudQuieroIrJunto(params)
    }

    @Transactional
    def save(SolicitudQuieroIrJunto solicitudQuieroIrJuntoInstance) {
        if (solicitudQuieroIrJuntoInstance == null) {
            notFound()
            return
        }

        if (solicitudQuieroIrJuntoInstance.hasErrors()) {
            respond solicitudQuieroIrJuntoInstance.errors, view: 'create'
            return
        }

        solicitudQuieroIrJuntoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'solicitudQuieroIrJuntoInstance.label', default: 'SolicitudQuieroIrJunto'), solicitudQuieroIrJuntoInstance.id])
                redirect solicitudQuieroIrJuntoInstance
            }
            '*' { respond solicitudQuieroIrJuntoInstance, [status: CREATED] }
        }
    }

    def edit(SolicitudQuieroIrJunto solicitudQuieroIrJuntoInstance) {
        respond solicitudQuieroIrJuntoInstance
    }

    @Transactional
    def update(SolicitudQuieroIrJunto solicitudQuieroIrJuntoInstance) {
        if (solicitudQuieroIrJuntoInstance == null) {
            notFound()
            return
        }

        if (solicitudQuieroIrJuntoInstance.hasErrors()) {
            respond solicitudQuieroIrJuntoInstance.errors, view: 'edit'
            return
        }

        solicitudQuieroIrJuntoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'SolicitudQuieroIrJunto.label', default: 'SolicitudQuieroIrJunto'), solicitudQuieroIrJuntoInstance.id])
                redirect solicitudQuieroIrJuntoInstance
            }
            '*'{ respond solicitudQuieroIrJuntoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(SolicitudQuieroIrJunto solicitudQuieroIrJuntoInstance) {

        if (solicitudQuieroIrJuntoInstance == null) {
            notFound()
            return
        }

        solicitudQuieroIrJuntoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'SolicitudQuieroIrJunto.label', default: 'SolicitudQuieroIrJunto'), solicitudQuieroIrJuntoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudQuieroIrJuntoInstance.label', default: 'SolicitudQuieroIrJunto'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
	def aprobarSolicitud(SolicitudQuieroIrJunto instance) {
		if (instance) {
			instance.aprobar()
			def pasajeroSolicitante = Pasajero.get(instance.pasajero.id)
			def reservaSolicitada = Reserva.get(instance.reservaSolicitada.id)
			
			redirect action: 'quieroIrJunto', controller: 'pasajero'
			}
	}
	
	
	
	def denegarSolicitud(SolicitudQuieroIrJunto instance) {
		if (instance) {
			instance.denegar()
			redirect action: 'quieroIrJunto', controller: 'pasajero'
		}
	}
}

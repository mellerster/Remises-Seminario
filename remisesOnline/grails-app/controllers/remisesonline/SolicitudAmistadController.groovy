package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class SolicitudAmistadController {
	def pasajeroService
	def solicitudAmistadService

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond SolicitudAmistad.list(params), model:[solicitudAmistadInstanceCount: SolicitudAmistad.count()]
	}

	def show(SolicitudAmistad solicitudAmistadInstance) {
		respond solicitudAmistadInstance
	}

	def create() {
		SolicitudAmistad solicitudAmistadInstance = new SolicitudAmistad(params)
		solicitudAmistadInstance.pasajero =	Pasajero.get(session.pasajero.id)
		respond solicitudAmistadInstance
	}

	@Transactional
	def save(SolicitudAmistad solicitudAmistadInstance) {
		if (solicitudAmistadInstance == null) {
			notFound()
			return
		}


		solicitudAmistadInstance.pasajero =	Pasajero.get(session.pasajero.id)
		solicitudAmistadInstance.validate()

		if(solicitudAmistadService.comprobarSolicitudPendienteExistente(solicitudAmistadInstance)) {
			flash.message = "Existe una solicitud pendiente de dicha persona"
			return [redirect(action:"listSolicitudesAmigosRecibidas", controller:"pasajero")]
		}



		if (solicitudAmistadInstance.hasErrors()) {
			respond solicitudAmistadInstance.errors, view: 'create'
			return
		}

		solicitudAmistadInstance.save flush:true
		pasajeroService.enviarSolicitudAmistad(session.pasajero, solicitudAmistadInstance.solicitado)
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
			respond solicitudAmistadInstance.errors, view: 'edit'
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

	def aprobarSolicitud(SolicitudAmistad instance) {
		if (instance) {
			instance.aprobar()
			def pasajeroSolicitante = Pasajero.get(instance.pasajero.id)
			def pasajeroSolicitado = Pasajero.get(instance.solicitado.id)
			pasajeroSolicitante.addToAmigos(pasajeroSolicitado)
			pasajeroSolicitado.addToAmigos(pasajeroSolicitante)
			redirect action: 'amigos', controller: 'pasajero'
		}
	}

	def denegarSolicitud(SolicitudAmistad instance) {
		if (instance) {
			instance.denegar()
			redirect action: 'amigos', controller: 'pasajero'
		}
	}

}

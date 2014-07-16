package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ItinerarioController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def list = {
		def itinerarios = Itinerario.list(sort: "descripcion", order: "asc")
		return [itinerarios: itinerarios]
	}

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Itinerario.list(params), model:[itinerarioInstanceCount: Itinerario.count()]
	}

	def show(Itinerario itinerarioInstance) {
		respond itinerarioInstance
	}

	def create() {
		respond new Itinerario(params)
	}

	@Transactional
	def save(Itinerario itinerarioInstance) {
		if (itinerarioInstance == null) {
			notFound()
			return
		}

		if (itinerarioInstance.hasErrors()) {
			respond itinerarioInstance.errors, view:'create'
			return
		}

		itinerarioInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'itinerarioInstance.label', default: 'Itinerario'), itinerarioInstance.id])
				redirect itinerarioInstance
			}
			'*' { respond itinerarioInstance, [status: CREATED] }
		}
	}

	def edit(Itinerario itinerarioInstance) {
		respond itinerarioInstance
	}

	@Transactional
	def update(Itinerario itinerarioInstance) {
		if (itinerarioInstance == null) {
			notFound()
			return
		}

		if (itinerarioInstance.hasErrors()) {
			respond itinerarioInstance.errors, view:'edit'
			return
		}

		itinerarioInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'Itinerario.label', default: 'Itinerario'), itinerarioInstance.id])
				redirect itinerarioInstance
			}
			'*'{ respond itinerarioInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Itinerario itinerarioInstance) {

		if (itinerarioInstance == null) {
			notFound()
			return
		}

		itinerarioInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'Itinerario.label', default: 'Itinerario'), itinerarioInstance.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'itinerarioInstance.label', default: 'Itinerario'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}

package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PasajeroController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def logout() {
		session.pasajero = null
		redirect(action: 'login')
	}
	
	def login() {
		[pasajeros: Pasajero.list()]
	}
	
	def entrar() {
		session.pasajero = Pasajero.get(params.pasajero)
		redirect action: 'listReservas', controller: 'pasajero'
	}
	
	def listReservas() {
		def pasajeroLogueado = Pasajero.get(session.pasajero.id)
		return [reservas: pasajeroLogueado.reservas]
	}
	
	def index(Integer max) {
			params.max = Math.min(max ?: 10, 100)
			respond Pasajero.list(params), model:[pasajeroInstanceCount: Pasajero.count()]
	}

	def show(Pasajero pasajeroInstance) {
			respond pasajeroInstance
	}

	def crear() {
		respond new Pasajero(params)
	}

	@Transactional
	def guardar(Pasajero pasajeroInstance) {
			if (pasajeroInstance == null) {
					notFound()
					return
			}

			if (pasajeroInstance.hasErrors()) {
					respond pasajeroInstance.errors, view:'crear'
					return
			}

			pasajeroInstance.save flush:true

			session.pasajero = pasajeroInstance
      redirect action: 'listReservas', controller: 'pasajero'
	}

	def edit(Pasajero pasajeroInstance) {
			respond pasajeroInstance
	}

	@Transactional
	def update(Pasajero pasajeroInstance) {
			if (pasajeroInstance == null) {
					notFound()
					return
			}

			if (pasajeroInstance.hasErrors()) {
					respond pasajeroInstance.errors, view:'edit'
					return
			}

			pasajeroInstance.save flush:true

			request.withFormat {
					form multipartForm {
							flash.message = message(code: 'default.updated.message', args: [message(code: 'Pasajero.label', default: 'Pasajero'), pasajeroInstance.id])
							redirect pasajeroInstance
					}
					'*'{ respond pasajeroInstance, [status: OK] }
			}
	}

	@Transactional
	def delete(Pasajero pasajeroInstance) {

			if (pasajeroInstance == null) {
					notFound()
					return
			}

			pasajeroInstance.delete flush:true
      session.pasajero = null
			redirect action: 'login', controller: 'pasajero'
				
	}

	protected void notFound() {
			request.withFormat {
					form multipartForm {
							flash.message = message(code: 'default.not.found.message', args: [message(code: 'pasajeroInstance.label', default: 'Pasajero'), params.id])
							redirect action: "index", method: "GET"
					}
					'*'{ render status: NOT_FOUND }
			}
	}
	
	def amigos(){
		def p = Pasajero.get(session.pasajero.id)
		[amigos: p.amigos]
	}
	
	def agregarAmigos(){
		def p = Pasajero.get(session.pasajero.id)
		[pasajeros: Pasajero.findAllByIdNotEqual(p.id)]
	}
	

	def enviarSolicitud(){
		def p = params.pasajeroSeleccionado
		render p
	}
	
}

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
		def pasajeroLogueado = Pasajero.get(session.pasajero?.id)
		if (pasajeroLogueado)
			return [reservas: pasajeroLogueado.reservas]
		flash.message = "Sesion invalida"
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
		def p = Pasajero.get(session.pasajero?.id)
		if (p) {
			return [amigos: p.amigos] //aca puede explotar si amigos no esta inicializado
		}
		flash.message = "Sesion invalida"
	}
	
	def agregarAmigos(){
		def p = Pasajero.get(session.pasajero?.id)
		if (p) {
			return [pasajeros: Pasajero.findAllByIdNotEqual(p.id)]
		}
		flash.message = "Sesion invalida"
	}
	

	def listSolicitudesEnviadas(){
		def p = Pasajero.get(session.pasajero?.id)
		if (p) {
			return [solicitudesEnviadas: p.solicitudes]
		}
		flash.message = "Sesion invalida"
	}
	
	def listSolicituderRecibidas(){
		def p = Pasajero.get(session.pasajero?.id)
		if (p) {
			def solicitudes = SolicitudAmistad.findAllBySolicitado(p)
			def solicitudesPendientes = solicitudes.grep{solicitud -> solicitud.pendiente}
			return [solicitudesRecibidas: solicitudesPendientes]
		}
		flash.message = "Sesion invalida"
	}
	
	def quieroIrJunto(){
		def p = Pasajero.get(session.pasajero?.id)
		if (p) {
			return [amigos: p.amigos]
		}
		flash.message = "Sesion invalida"
	}
	
	def listarReservasDeAmigo(){
		def p = Pasajero.get(session.pasajero?.id)
		if (p) {
			def reservaCompartibles = p.reservas.findAll{reserva -> reserva.compartible && reserva.pendiente}
			return [reservas: reservaCompartibles]	
		}
		flash.message = "Sesion invalida"
	}
	
	def eliminarAmigo(){
		//Eliminar el amigo de ambos lados
		redirect(action: "amigos")
	}
	
	def listPromociones(){
		//Esto lo tiene que hacer la promocion, pero me tira un error y quiero dejarlo para hacer bien las vistas
		def fechaActual = new Date()
		def promos = Promocion.findAllByFechaHastaGreaterThanEquals(fechaActual)
		[promociones: promos]
		
	}
	
}

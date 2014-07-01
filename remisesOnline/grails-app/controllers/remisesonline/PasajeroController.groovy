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
				flash.message = message(code: 'default.updated.message', args: [
					message(code: 'Pasajero.label', default: 'Pasajero'),
					pasajeroInstance.id
				])
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
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'pasajeroInstance.label', default: 'Pasajero'),
					params.id
				])
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


	def listSolicitudesAmigosEnviadas(){
		def p = Pasajero.get(session.pasajero?.id)
		if (p) {
			return [solicitudesEnviadas: p.solicitudesAmistad]
		}
		flash.message = "Sesion invalida"
	}

	def listSolicitudesAmigosRecibidas(){
		def p = Pasajero.get(session.pasajero?.id)
		if (p) {
			def solicitudes = SolicitudAmistad.findAllBySolicitado(p)
			def solicitudesPendientes = solicitudes.grep{solicitud -> solicitud.estado == 'Pendiente'}
			return [solicitudesRecibidas: solicitudesPendientes]
		}
		flash.message = "Sesion invalida"
	}

	def eliminarAmigo(){
		//Eliminar el amigo de ambos lados
		def pasajeroSesion = Pasajero.get(session.pasajero?.id)
		if (pasajeroSesion) {
			def pasajeroAEliminar = Pasajero.get(Long.parseLong(params.id))
			if (pasajeroAEliminar) {
				pasajeroSesion.removeFromAmigos(pasajeroAEliminar)
				pasajeroSesion.save flush:true
				pasajeroAEliminar.removeFromAmigos(pasajeroSesion)
				pasajeroAEliminar.save flush:true
				return [redirect(action: "amigos")]
			}
			flash.message = "Pasajero a eliminar error"
		}

		flash.message = "Sesion invalida"


	}

	def listPromociones(){
		//Esto lo tiene que hacer la promocion, pero me tira un error y quiero dejarlo para hacer bien las vistas
		def fechaActual = new Date()
		def promos = Promocion.findAllByFechaHastaGreaterThanEquals(fechaActual)
		[promociones: promos]

	}
	def quieroIrJunto(){
		def p = Pasajero.get(session.pasajero?.id)
		if (p) {
			return [amigos: p.amigos]
		}
		flash.message = "Sesion invalida"
	}

	def showReservasAmigos(){
		def p = Pasajero.get(session.pasajero?.id)
		if (p) {
			return [amigos: p.amigos]
		}
		flash.message = "Sesion invalida"
	}


	def listSolicitudesAcompaniamientoEnviadas(){
		def p = Pasajero.get(session.pasajero?.id)
		if (p) {
			return [solicitudes: p.solicitudesAcompaniamiento]
		}
		flash.message = "Sesion invalida"
	}


	def listSolicitudesAcompaniamientoRecibidas(){
		def p = Pasajero.get(session.pasajero?.id)
		if (p) {
			//return			
		}
		flash.message = "Sesion invalida"
	}
	
		
	def listReservasDeAmigo(){
		def p = Pasajero.get(params.pasajero)
		if (p) {
			def reservasCompartibles = p.reservas.findAll{reserva -> reserva.compartible && reserva.pendiente}		
			return [reservas: reservasCompartibles, pasajero: p.id]	
		}
		flash.message = "Sesion invalida"
	}
	
	//Esto es un servicio y suena mas a competencia de la solicitud que del pasajero, pero lo dejo aqui por ahora
	def unirseAReserva(){
		def pasajeroSesion = Pasajero.get(session.pasajero?.id)
		if (pasajeroSesion) {
			def re = Reserva.get(Long.parseLong(params.id))
			def pas = Pasajero.get(Long.parseLong(params.pasajero))
			
			def solicitud = new SolicitudAcompaniamiento(pasajero: pasajeroSesion, solicitado: pas, reservaSolicitada: re , fechaCreada: new Date(), estado: 'Pendiente')
			solicitud.save flush:true

			pasajeroSesion.addToSolicitudesAcompaniamiento(solicitud)
			pasajeroSesion.save flush:true
			return [redirect(action: "showReservasAmigos")]
		}
	}
}

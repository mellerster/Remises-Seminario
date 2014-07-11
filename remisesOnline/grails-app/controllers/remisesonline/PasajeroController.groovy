package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class PasajeroController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def pasajeroService
	
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
			respond pasajeroInstance.errors, view: 'crear'
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
			respond pasajeroInstance.errors, view: 'edit'
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

	def amigos() {
		def p = Pasajero.get(session.pasajero?.id)
		[amigos: p.amigos]
	}

	def agregarAmigos() {
		def noAmigos = obtenerPasajerosQueNoSonAmigos()
		println "no amigos"
		println noAmigos
		redirect (pasajerosNoAmigos: noAmigos, action: 'create', controller: 'solicitudAmistad')
	}
	
	def obtenerPasajerosQueNoSonAmigos() {
		def p = Pasajero.get(session.pasajero?.id)
		def pasajerosMenosYo = Pasajero.findAllByIdNotEqual(p.id)
		def pasajeroAmigos = p.amigos 
		def resultado = pasajerosMenosYo - pasajeroAmigos
		resultado
	}
	
	def listSolicitudesAmigosEnviadas() {
		def p = Pasajero.get(session.pasajero?.id)
		[solicitudesEnviadas: p.solicitudesAmistad]
	}

	def listSolicitudesAmigosRecibidas() {
		def pasajeroSesion = Pasajero.get(session.pasajero?.id)
		[solicitudesRecibidas: pasajeroService.solicitudesAmistadPendientesAprobacion(pasajeroSesion)]
	}

	def eliminarAmigo() {
		def pasajeroSesion = Pasajero.get(session.pasajero?.id)
		pasajeroService.eliminarAmistad(pasajeroSesion, Long.parseLong(params.id))
		[redirect(action: "amigos")] 		
	}

	def listPromociones() {
		[promociones: pasajeroService.getPromocionesVigentes()]
	}
	
	def quieroIrJunto() {
		def p = Pasajero.get(session.pasajero?.id)
		[amigos: p.amigos]	
	}

	def showReservasAmigos() {
		def p = Pasajero.get(session.pasajero?.id)
		[amigos: p.amigos]
	}


	def listSolicitudesQuieroIrJuntoEnviadas() {
		def p = Pasajero.get(session.pasajero?.id)
		[solicitudesEnviadas: p.solicitudesQuieroIrJunto]
	}


	def listSolicitudesQuieroIrJuntoRecibidas() {
		def p = Pasajero.get(session.pasajero?.id)
		[solicitudesRecibidas: pasajeroService.solicitudesQuieroIrJuntoPendientesAprobacion(p)]
	}
	
		
	def listReservasDeAmigo() {
		def p = Pasajero.get(params.pasajero)
		def reservasCompartibles = pasajeroService.getReservasCompartibles(p)		
		[reservas: reservasCompartibles, pasajero: p.id]	
	}
	
	def unirseAReserva() {
		def pasajeroSesion = Pasajero.get(session.pasajero?.id)
		pasajeroService.unirAReserva(pasajeroSesion, params)
		[redirect(action: "showReservasAmigos")]
	}
}

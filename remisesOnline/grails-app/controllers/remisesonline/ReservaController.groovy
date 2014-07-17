package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class ReservaController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def reservaService

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Reserva.list(params), model:[reservaInstanceCount: Reserva.count()]
	}

	def show(Reserva reservaInstance) {
		respond reservaInstance
	}

	def create() {
		respond new Reserva(params)
	}

	@Transactional
	def save(Reserva reservaInstance) {
		if (reservaInstance == null) {
			notFound()
			return
		}

		reservaInstance.pasajero =	Pasajero.get(session.pasajero.id)
		reservaInstance.fechaReserva =	params.date( 'fechaReserva', 'dd/MM/yy HH:mm' )
		reservaInstance.validate()

		if (reservaInstance.hasErrors()) {
			respond reservaInstance.errors, view: 'create'
			return false
		}
		if (reservaInstance.remise && reservaInstance.pendiente) {
			reservaInstance.estado = ESTADOS_RESERVA.ConRemise
		}

		reservaInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [
					message(code: 'reservaInstance.label', default: 'Reserva'),
					reservaInstance.id
				])
				redirect reservaInstance
			}
			'*' { respond reservaInstance, [status: CREATED] }
		}
	}

	def edit(Reserva reservaInstance) {
		respond reservaInstance
	}

	def update() {

		def reservaInstance = reservaService.updateRemise(params.id, params.version, params.remise.id)

		if (reservaInstance.hasErrors()) {
			respond reservaInstance.errors, view: 'show'
			return
		}

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [
					message(code: 'Reserva.label', default: 'Reserva'),
					reservaInstance.id
				])
				redirect reservaInstance
			}
			'*'{ respond reservaInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Reserva reservaInstance) {

		if (reservaInstance == null) {
			notFound()
			return
		}

		reservaInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [
					message(code: 'Reserva.label', default: 'Reserva'),
					reservaInstance.id
				])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	def eliminarParada(Reserva reservaInstance) {
		println 'Id ' + reservaInstance?.id
		println 'fechaReserva ' + reservaInstance?.fechaReserva
		println 'Agencia ' + reservaInstance?.agencia
		println 'params.id '
		println params.id

		def reserva = Reserva.get(reservaInstance?.id)
		println 'fechaReserva2 ' + reserva?.fechaReserva
		def parada1 = reserva.paradas.find{ it.id == Long.parseLong(params.id) }

		if (reserva) {
			//def parada2 = Parada.get(Long.parseLong(params.id))
			if (parada1) {
				println 'parada1 ok '
			}
			println parada1.calle + ' ' + parada1.numero

			//reservaService.eliminarParada(reservaInstance.id, params.id)

			if (parada1) {
				reserva.removeFromParadas(parada1)
				reserva.save()
			}
		}
		redirect controller: 'reserva', action: 'show', id: params.id
		respond reserva, view: 'show'
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'reservaInstance.label', default: 'Reserva'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}

	def cancelarReserva() {
		def reserva = reservaService.cancelarReserva(Long.parseLong(params.id))

		if (reserva.hasErrors()) {
			respond reserva.errors, view: 'show'
			return
		}
		flash.message = 'Reserva cancelada'
		reservaService.informarPasajeros(reserva)
		redirect controller: 'reserva', action: 'show', id: params.id
	}

	def cerrar() {
		def reserva = reservaService.cerrar(params.id, session?.agencia?.id)
		if(reserva.hasErrors()) {
			respond reserva.errors, view: 'show'
			return
		}
		flash.message = 'Reserva Cerrada'
		redirect controller: 'reserva', action: 'show', id: params.id
	}

	def pasarAEnCurso() {
		def reserva = reservaService.pasarAEnCurso(params.id,session?.agencia?.id)
		if(reserva.hasErrors()) {
			respond reserva.errors, view: 'show'
			return
		}
		flash.message = 'Reserva En Curso'
		redirect controller: 'reserva', action: 'show',id:params.id
	}

	def calificarRemise(Reserva reservaInstance) {
		if(reservaInstance.esRemiseCalificable) {
			respond reservaInstance
		} else {
			flash.message = "No se puede calificar esta reserva, no esta cerrada o ya fue calificada"
			redirect controller: 'pasajero', action: 'listReservas'
		}
	}

	@Transactional
	def guardarCalificacionRemise() {
		def reserva = Reserva.get(params.id)

		if(params.puntaje) {
			if(reserva.calificarRemise(params.puntaje)) {
				redirect controller: 'remise', action: 'show', id: reserva.remise.id
			} else {
				flash.message = "No se puede calificar esta reserva, no esta cerrada o ya fue calificada"
				redirect controller: 'pasajero', action: 'listReservas'
			}
		} else {
			flash.message = "Debe seleccionar un puntaje para asignar"
			respond reserva, view: 'calificarRemise'
		}
	}

	def calificarPasajero(Reserva reservaInstance) {
		if(reservaInstance.esPasajeroCalificable) {
			respond reservaInstance
		} else {
			flash.message = "No se puede calificar esta reserva,no esta cerrada o ya fue calificada"
			redirect controller: 'agencia' ,action: 'listReservas', params: [estadoSeleccionado: 'Cerrada']
		}
	}

	@Transactional
	def guardarCalificacionPasajero() {
		def reserva = Reserva.get(params.id)

		if(params.puntaje) {
			if(reserva.calificarPasajero(params.puntaje)) {
				redirect controller: 'pasajero', action: 'show', id: reserva.pasajero.id
			} else {
				flash.message = "No se puede calificar esta reserva,no esta cerrada o ya fue calificada"
				redirect controller: 'agencia', action: 'listReservas', params: [estadoSeleccionado: 'Cerrada']
			}
		} else {
			flash.message = "Debe seleccionar un puntaje para asignar"
			respond reserva, view: 'calificarPasajero'
		}
	}

	def asignarRemis(Reserva reservaInstance) {
		respond reservaInstance
	}

	def asignar(Reserva reservaInstance ) {
		respond reservaInstance, view: 'asignarRemis'
	}
	
	def solicitarUnirse(){
		def reserva = Reserva.get(params.id)
		def pasajeroSesion = Pasajero.get(session.pasajero?.id)
		def pasajeroSolicitado = Pasajero.get(Long.parseLong(params.pasajero))
		if (reserva.solicitarUnirseAReserva(pasajeroSesion, pasajeroSolicitado)) {
			[redirect(action: "showReservasAmigos", controller: "pasajero")]
		}
		flash.message = 'La reserva que intenta unirse ha sido cancelada.'
		redirect controller: 'pasajero', action: 'quieroIrJunto'
	}
}

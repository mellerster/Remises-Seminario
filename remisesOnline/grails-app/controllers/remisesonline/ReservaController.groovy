package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
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
			respond reservaInstance.errors, view:'create'
			return false
		}
		if (reservaInstance.remise && reservaInstance.pendiente) {
			reservaInstance.estado = Reserva.ESTADOS_VALIDOS[4]
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

	@Transactional
	def update() {
		
		def reservaInstance = reservaService.updateRemise(params.id, params.version, params.remise.id)

		if (reservaInstance.hasErrors()) {
			println reservaInstance.errors

			respond reservaInstance.errors, view: 'asignarRemis'
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
		redirect controller: 'reserva', action: 'show', id: params.id
	}
	
	def cerrar() {
		def reserva = reservaService.cerrar(params.id, session?.agencia?.id)
		if(reserva.hasErrors()){
			respond reserva.errors, view: 'show'
			return
		}
		flash.message = 'Reserva Cerrada'
		redirect controller: 'reserva', action: 'show', id: params.id
	}
	
	def pasarAEnCurso(){
		def reserva = reservaService.pasarAEnCurso(params.id,session?.agencia?.id)
		if(reserva.hasErrors()) {
			respond reserva.errors, view:'show'
			return
		}
		flash.message = 'Reserva En Curso'
		redirect controller:'reserva',action:'show',id:params.id
	}

	def calificarRemise(Reserva reservaInstance) {
		if(reservaInstance.esRemiseCalificable){
			respond reservaInstance
		}else{
			flash.message = "No se puede calificar esta reserva, no esta cerrada o ya fue calificada"
			redirect controller: 'pasajero', action: 'listReservas'

		}
	}

	@Transactional
	def guardarCalificacionRemise() {
		def reserva = Reserva.get(params.id)
		if(reserva.esRemiseCalificable) {
			if(params.puntaje) {
				reserva.calificarRemise(params.puntaje)
				redirect controller:'remise', action: 'show', id: reserva.remise.id
			}else{
				flash.message = "Debe seleccionar un puntaje para asignar"
				respond reserva, view: 'calificarRemise'
			}
		}else{
			flash.message = "No se puede calificar esta reserva, no esta cerrada o ya fue calificada"
			redirect controller:'pasajero', action: 'listReservas'
		}
	}
	
	def calificarPasajero(Reserva reservaInstance) {
		if(reservaInstance.esPasajeroCalificable) {
			respond reservaInstance
		}else{
			flash.message = "No se puede calificar esta reserva,no esta cerrada o ya fue calificada"
			redirect controller: 'agencia' ,action: 'listReservas', params: [estadoSeleccionado: 'Cerrada']
		}
	}

	@Transactional
	def guardarCalificacionPasajero() {
		def reserva = Reserva.get(params.id)
		if(reserva.esPasajeroCalificable) {
			if(params.puntaje){
				reserva.calificarPasajero(params.puntaje)
				redirect controller: 'pasajero', action: 'show', id: reserva.pasajero.id
			}else{
				flash.message = "Debe seleccionar un puntaje para asignar"
				respond reserva, view: 'calificarPasajero'
			}
		}else{
			flash.message = "No se puede calificar esta reserva,no esta cerrada o ya fue calificada"
			redirect controller: 'agencia', action: 'listReservas', params: [estadoSeleccionado:'Cerrada']
		}
	}
	
	def asignarRemis(Reserva reservaInstance) {
		respond reservaInstance
	}
	
	def asignar(Reserva reservaInstance ) {
		respond reservaInstance, view: 'asignarRemis'
	}
}

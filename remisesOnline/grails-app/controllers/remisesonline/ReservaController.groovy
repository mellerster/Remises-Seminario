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
				
				//reservaInstance.properties = params
				
				reservaInstance.pasajero =	Pasajero.get(session.pasajero.id)
				reservaInstance.fechaReserva =	params.date( 'fechaReserva', 'dd/MM/yy HH:mm' )
				reservaInstance.validate()

				if (reservaInstance.hasErrors()) {
						respond reservaInstance.errors, view:'create'
						return false
				}
				
				reservaInstance.save flush:true

				request.withFormat {
						form multipartForm {
								flash.message = message(code: 'default.created.message', args: [message(code: 'reservaInstance.label', default: 'Reserva'), reservaInstance.id])
								redirect reservaInstance
						}
						'*' { respond reservaInstance, [status: CREATED] }
				}
		}

		def edit(Reserva reservaInstance) {
				respond reservaInstance
		}

		@Transactional
		def update(Reserva reservaInstance) {
				if (reservaInstance == null) {
						notFound()
						return
				}
				
				reservaInstance.pasajero =	Pasajero.get(session.pasajero.id)
				
				reservaInstance.fechaReserva =	params.date( 'fechaReserva', 'dd/MM/yy HH:mm' )
				reservaInstance.validate()

				if (reservaInstance.hasErrors()) {
						respond reservaInstance.errors, view:'edit'
						return
				}

				reservaInstance.save flush:true

				request.withFormat {
						form multipartForm {
								flash.message = message(code: 'default.updated.message', args: [message(code: 'Reserva.label', default: 'Reserva'), reservaInstance.id])
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
								flash.message = message(code: 'default.deleted.message', args: [message(code: 'Reserva.label', default: 'Reserva'), reservaInstance.id])
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
			redirect controller:'reserva', action:'show', id:params.id
			respond reserva, view:'show'
		}
		
		def cancelarReserva() {
			println params
			def reserva = reservaService.cancelarReserva(Long.parseLong(params.id))
			redirect controller:'reserva', action:'show', id:params.id
			//respond reserva, controller: 'pasajero', view:'show'
		}

		protected void notFound() {
				request.withFormat {
						form multipartForm {
								flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservaInstance.label', default: 'Reserva'), params.id])
								redirect action: "index", method: "GET"
						}
						'*'{ render status: NOT_FOUND }
				}
		}
}

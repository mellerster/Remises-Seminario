package remisesonline

import grails.transaction.Transactional

@Transactional
class ReservaService {

	def eliminarParada(Long idReserva, String idParada) {
		println '---------eliminarParada----------------- '
		def reserva = Reserva.get(idReserva)
		def parada2 = Parada.get(Long.parseLong(idParada))
		def aux = reserva.paradas
		println 'hola '
		println aux
		println 'reserva modificada: ' + reserva.fechaReserva
		def parada1 = reserva.paradas.find{ it.calle == parada2.calle && it.numero == parada2.numero }

		if (parada2 && reserva) {
			reserva.removeFromParadas(parada2)
			reserva.paradas.remove(parada2)
			reserva.save(flush: true, failOnError: true)

			println parada1.calle + ' eliminada'
		} else {
			println 'error'
			println 'parada: ' + parada1?.calle
			println 'reserva: ' + reserva
		}
	}

	def cancelarReserva(Long idReserva) {
		def reserva = Reserva.get(idReserva)
		if (reserva.cancelar()) {
			reserva.save(flush: true)
		} else {
			reserva.errors.reject('reserva.estado.cantcancel', 'No se puede cancelar debido al estado comuniquese con la agencia')
		}
		reserva
	}

	def cerrar(def idReserva,def idAgencia){
		def reserva = Reserva.get(idReserva)
		if(reserva.esCerrablePorAgencia(idAgencia)){
			reserva.cerrar()
			reserva.save(flush:true)
		}else{
			reserva.errors.reject('reserva.estado.cantcerrar','No se puede cerrar debido al estado')
		}
		reserva
	}
	
	def pasarAEnCurso(def idReserva,def idAgencia){
		def reserva = Reserva.get(idReserva)
		if(reserva.esPasableAEnCursoPorAgencia(idAgencia)){
			reserva.pasarAEnCurso()
			reserva.save(flush:true)
		}else{
			reserva.errors.reject('reserva.estado.cantcurso','No se puede pasar a en curso debido al estado o falta de asignación de remise')
		}
		reserva
	}
	
	def updateRemise(def reservaInstance) {
		
		println 'reservaInstance.version ' + reservaInstance.version
		def reserva = Reserva.get(reservaInstance.id)
		println 'reserva.version ' + reserva.version
		
		if (reserva.version > reservaInstance.version) {
			reserva.errors.reject('reserva.actualizada','La reserva que intenta actualizar fue modificada por otro usuario')
		} else {
			reserva.remise = reservaInstance.remise
			reserva.estado = 'Remis asignado'
		}
		reserva
	}
}

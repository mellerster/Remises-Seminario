package remisesonline

import grails.transaction.Transactional

@Transactional
class ReservaService {

	def solicitudQuieroIrJuntoService

	def eliminarParada(Long idReserva, String idParada) {
		
		def reserva = Reserva.get(idReserva)
		def parada2 = Parada.get(Long.parseLong(idParada))
		def aux = reserva.paradas

		def parada1 = reserva.paradas.find{ it.calle == parada2.calle && it.numero == parada2.numero }

		if (parada2 && reserva) {
			reserva.removeFromParadas(parada2)
			reserva.paradas.remove(parada2)
			reserva.save(flush: true, failOnError: true)			
		} else {
		}
	}

	def cancelarReserva(Long idReserva) {
		def reserva = Reserva.get(idReserva)
		if (!reserva.cancelar()) {
			reserva.errors.reject('reserva.estado.cantcancel', 'No se puede cancelar debido al estado comuniquese con la agencia')
		}
		reserva
	}

	def cerrar(def idReserva, def idAgencia) {
		def reserva = Reserva.get(idReserva)
		if(reserva.esCerrablePorAgencia(idAgencia)) {
			reserva.cerrar()
		} else {
			reserva.errors.reject('reserva.estado.cantcerrar','No se puede cerrar debido al estado')
		}
		reserva
	}

	def pasarAEnCurso(def idReserva,def idAgencia) {
		def reserva = Reserva.get(idReserva)
		if(reserva.esPasableAEnCursoPorAgencia(idAgencia)) {
			reserva.pasarAEnCurso()
		} else {
			reserva.errors.reject('reserva.estado.cantcurso','No se puede pasar a en curso debido al estado o falta de asignación de remise')
		}
		reserva
	}

	def updateRemise(def reservaId, String version, def remisId) {

		def reserva = Reserva.get(reservaId)

		if (reserva.version > Integer.parseInt(version)) {
			reserva.errors.reject('reserva.actualizada','La reserva que intenta actualizar fue modificada por otro usuario')

		} else {
			reserva.remise = Remise.get(Long.parseLong(remisId))
			reserva.asignarRemise()
		}
		reserva
	}

	def informarPasajeros(Reserva reservaInstance) {
		println "Informando a pasajeros"

		/*aca se supone envia mails a cada pasajero usando el emailService.
		 */

		solicitudQuieroIrJuntoService.cancelacionDeSolicitud(reservaInstance)
	}

}

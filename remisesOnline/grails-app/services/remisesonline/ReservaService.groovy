package remisesonline

import grails.transaction.Transactional

@Transactional
class ReservaService {

    def eliminarParada(Long idReserva, String idParada) {
			def reserva = Reserva.get(idReserva)
			def parada = reserva.paradas.find{ it.id == idParada }
			
			if (parada && reserva) {
				reserva.removeFromParadas(parada)
				reserva.save()
				println 'reserva modificada: ' + reserva.fechaReserva
				println parada.calle + ' eliminada'
			} else {
				println 'error'
				println 'parada: ' + parada?.calle
				println 'reserva: ' + reserva
				
			}
			
			
			

    }
}

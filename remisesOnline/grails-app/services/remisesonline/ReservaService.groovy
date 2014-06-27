package remisesonline

import grails.transaction.Transactional

@Transactional
class ReservaService {

    def eliminarParada(Long idReserva, String idParada) {
			def parada = Parada.get(idParada)
			def reserva = Reserva.get(idReserva)
			if (parada && reserva) {
				reserva.removeFromParadas(parada)
				reserva.save()
				println 'reserva modificada: ' + reserva.fechaReserva
				println parada.calle + ' eliminada'
			} else {
				println 'error'
				println 'parada: ' + parada.calle
				println 'reserva: ' + reserva
				
			}
			
			
			

    }
}

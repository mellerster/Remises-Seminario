package remisesonline

import grails.transaction.Transactional

@Transactional
class SolicitudQuieroIrJuntoService {
	
	def emailService
	
    def cancelacionDeSolicitud(Reserva reservaInstance) {
		println "Cancelando solicitudes quiero ir junto"
		//Buscarlas todas y ponerle cancelado a cada una de ellas		
    }
}

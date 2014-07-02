package remisesonline

import grails.transaction.Transactional

@Transactional
class PasajeroService {

	def enviarSolicitudAmistad(Pasajero pasajero, Pasajero Amigo) {
		/*
		 sendMail {
		 to "mellerster@gmail.com"
		 subject "Hello Fred"
		 body 'How are you?'
		 }
		 println "mail enviado"
		 */
	}
	
	def eliminarAmistad(Pasajero pasajero, Long idAmigo) {
		def amigo = Pasajero.get(idAmigo)
		if(amigo){
			pasajero.removeFromAmigos(amigo)
			pasajero.save flush:true
			amigo.removeFromAmigos(pasajero)
			amigo.save flush:true
			} else {
				flash.message = "amigo error"
			}
    }
	
	def getPromocionesVigentes(){
		def fechaActual = new Date()
		def promocionesVigentes = Promocion.findAllByFechaHastaGreaterThanEquals(fechaActual)
		promocionesVigentes
	}
	
	def getReservasCompartibles(Pasajero p){
		def reservasCompartibles = p.reservas.findAll{reserva -> reserva.compartible && reserva.pendiente}		
		reservasCompartibles
	}
}

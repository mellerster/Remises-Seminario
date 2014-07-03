package remisesonline

import grails.transaction.Transactional

@Transactional
class PasajeroService {

	def emailService
	
	def enviarSolicitudAmistad(Pasajero pasajero, Pasajero Amigo) {
		emailService.enviarMail(Amigo.email,"Solicitud de Amistad Quererida","""El pasajero ${pasajero.nombre} te quiere agregar como amigo. Para Aceptarlo ingresa a RemisesOnline y anda a Mis Amigos y alli en Solicitudes Recibidas""")
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
	
	def solicitudesAmistadPendientesAprobacion(Pasajero p){
		def solicitudes = SolicitudAmistad.findAllBySolicitado(p)
		solicitudes.grep{solicitud -> solicitud.pendiente}
	}
	
	def solicitudesQuieroIrJuntoPendientesAprobacion(Pasajero p){
		def solicitudes = SolicitudQuieroIrJunto.findAllBySolicitado(p)
		solicitudesPendientes = solicitudes.grep{solicitud -> solicitud.pendiente}	
	}
}

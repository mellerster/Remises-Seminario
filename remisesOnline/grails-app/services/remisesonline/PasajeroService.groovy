package remisesonline

import grails.transaction.Transactional

@Transactional
class PasajeroService {

	def emailService
	
	def enviarSolicitudAmistad(Pasajero pasajero, Pasajero Amigo) {
		emailService.enviarMail(Amigo.email,"Solicitud de Amistad Requerida","""El pasajero ${pasajero.nombre} te quiere agregar como amigo.
		Para Aceptarlo ingresa a RemisesOnline y anda a Mis Amigos y allí en Solicitudes Recibidas""")
	}
	
	def eliminarAmistad(Pasajero pasajero, Long idAmigo) {
		def amigo = Pasajero.get(idAmigo)
		if(amigo) {
			pasajero.removeFromAmigos(amigo)
			pasajero.save flush:true
			amigo.removeFromAmigos(pasajero)
			amigo.save flush:true
		} else {
			flash.message = "amigo error"
		}
	}
	
	def getPromocionesVigentes() {
		def fechaActual = new Date()
		def promocionesVigentes = Promocion.findAllByFechaHastaGreaterThanEquals(fechaActual)
		promocionesVigentes
	}
	
	def getReservasCompartibles(Pasajero p) {
		def reservasCompartibles = p.reservas.findAll{reserva -> reserva.compartible && reserva.pendiente}		
		reservasCompartibles
	}	
	
	def solicitudesAmistadPendientesAprobacion(Pasajero p) {
		def solicitudes = SolicitudAmistad.findAllBySolicitado(p)
		solicitudes.grep{solicitud -> solicitud.pendiente}
	}
	
	def solicitudesQuieroIrJuntoPendientesAprobacion(Pasajero p) {
		def solicitudes = SolicitudQuieroIrJunto.findAllBySolicitado(p)
		solicitudes.grep{solicitud -> solicitud.pendiente}
	}
	
	def unirAReserva(Pasajero pasajeroSesion, Map params) {
		def re = Reserva.get(Long.parseLong(params.id))
		def pas = Pasajero.get(Long.parseLong(params.pasajero))
		if (re.pendiente) {
			def solicitud = new SolicitudQuieroIrJunto(pasajero: pasajeroSesion, solicitado: pas, reservaSolicitada: re , fechaCreada: new Date(), estado: 'Pendiente')
			solicitud.save flush:true
			pasajeroSesion.addToSolicitudesQuieroIrJunto(solicitud)
			pasajeroSesion.save flush:true
		} else {
			flash.message = 'La reserva que intenta unirse ha sido cancelada.'
		}
	}
}

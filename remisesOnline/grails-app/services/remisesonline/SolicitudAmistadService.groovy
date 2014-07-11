package remisesonline

import grails.transaction.Transactional

@Transactional
class SolicitudAmistadService {

    def comprobarSolicitudSinonimoExistente(SolicitudAmistad solicitudInstance) {
	
		def pasajeroQueSolicita = solicitudInstance.pasajero
		def pasajeroSolicitado = solicitudInstance.solicitado
		
		def solicitudesDelSolicitado = SolicitudAmistad.findAllByPasajero(pasajeroSolicitado) 
		solicitudesDelSolicitado.grep{it -> it.pendiente}.grep{it -> it.solicitado == pasajeroQueSolicita}
		if(solicitudesDelSolicitado.size != 0) {
			return true
			} else {
				return false
				}
    }
}

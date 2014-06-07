package remisesonline

class PasajeroController {

    def scaffold = true
	
	def logout() {
		session.pasajero = null
		redirect(action: 'login')
	}
	
	def login() {
		[pasajeros: Pasajero.list()]
	}
	
	def entrar() {
		session.pasajero = Pasajero.get(params.pasajero)		
		redirect action: 'index', controller: 'reserva'
	}
	/*
	def pasajeroService
	def solicitarAmistad(){
		pasajeroService.enviarSolicitudAmistad(session.pasajero, session.pasajero)
		render "email enviado"
	}*/
}

package remisesonline

class AgenciaController {
	def scaffold = true
	
	def logout() {
		session.agencia = null
		redirect(action: 'login')
	}
	
	def login() {
		[agencias: Agencia.list()]
	}
	
	def entrar() {
		session.agencia = Agencia.findByNombre(params.agencia)
		redirect action: 'index', controller: 'remise'
	}
  
  def searchReservas() {
    [estadosReservas: Reserva.ESTADOS_VALIDOS]
  }
  
  def listReservas() {
    // session.agencia.reservas?.findAll {it.estado == params.estadoSeleccionado}
    Reserva.findByEstado(params.estadoSeleccionado)
  }
}

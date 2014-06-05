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
    def agenciaLogueada = Agencia.findByNombre(session.agencia.nombre)
    def reservasEstado = agenciaLogueada?.reservas?.findAll {it.estado == params.estadoSeleccionado}
    // def reservas = Reserva.findByEstado(params.estadoSeleccionado)
    return [reservas: reservasEstado] 
  }
}

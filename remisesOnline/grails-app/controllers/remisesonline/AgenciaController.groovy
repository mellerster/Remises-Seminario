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
    //session.agencia = Agencia.findByNombre(params.agencia) //No funca: no tengo el parametro agencia
    //session.agencia.reservas?.findAll {it.estado == params.estadoSeleccionado}
    def reservas = Reserva.findByEstado(params.estadoSeleccionado) //TODO: provisorio hasta que funque lo anterior
    return [reservas: reservas] //esto no filtra por la agencia "logueada"
  }
}

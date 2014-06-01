package remisesonline

class Agencia {
	String nombre
	String telefono
	String email

	static hasMany = [remises: Remise, choferes: Chofer, reservas: Reserva]

	static constraints = {
		nombre nullable: false, unique: true
		telefono blank: false
		remises minSize: 0
		email email: true
	}

	String toString(){
		return nombre
	}
  
  def reservasPendientes() {
    return reservas.findAll { it.estado == 'Pendiente'}
  }
  
  def reservasEnCurso() {
    return reservas.findAll { it.estado == 'En Curso'}
  }
    
  def reservasCanceladas() {
    return reservas.findAll { it.estado == 'Cancelada'}
  }
    
  def reservasCerradas() {
    return reservas.findAll { it.estado == 'Cerrada'}
  }
}

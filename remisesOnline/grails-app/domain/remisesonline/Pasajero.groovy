package remisesonline

class Pasajero {
	String email
	String nombre
	String telefono
	Date fechaNacimiento
	Set destinosFrecuentes = []
	Set viajes = []
	Set calificaciones = []
	static hasMany = [solicitudesAcompaniamiento: SolicitudAcompaniamiento, solicitudesAmistad: SolicitudAmistad, amigos: Pasajero, reservas: Reserva, destinosFrecuentes: Parada, viajes: Itinerario,calificaciones: Calificacion]

	static constraints = {
		nombre nullable: false, blank: false
		email email: true, blank: false, unique: true
		telefono blank: false
		calificaciones minSize:0
	}

	String toString() {
		return "${nombre} - ${email}"
	}
	
	def getReservasCompartibles() {
		reservas.grep { reserva ->
			reserva.compartible && reserva.pendiente
		}
	}
	
	def getCalificacion(){
		if(calificaciones.size() > 0){
			return (calificaciones.inject(0) { acc, val -> acc + val.puntaje } / calificaciones.size())
		}else {
			return 5
		}
	}
}

package remisesonline

class Pasajero {
	String email
	String nombre
	String telefono
	Date fechaNacimiento
	Set destinosFrecuentes = []
	Set viajes = []

	static hasMany = [solicitudes: SolicitudAmistad, amigos: Pasajero, reservas: Reserva, destinosFrecuentes: Parada, viajes: Itinerario]

	static constraints = {
		nombre nullable: false, blank: false
		email email: true, blank: false, unique: true
		telefono blank: false
	}

	String toString() {
		return "${nombre} - ${email}"
	}
}

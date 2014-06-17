package remisesonline

class Itinerario {

	String descripcion
	Set paradas = []
	Set pasajeros = []

	static hasMany = [paradas: Parada, reserva: Reserva, pasajeros: Pasajero]
	static belongsTo = [Pasajero]

	static constraints = {
		descripcion blank: false, nullable: false
	}

	String toString() {
		return descripcion
	}
}

package remisesonline

class Itinerario {
	
	String descripcion
	
	static hasMany = [paradas: Parada, reserva: Reserva]
	static belongsTo = [pasajero: Pasajero]

	static constraints = {
		descripcion blank: false, nullable: false
	}
  
	String toString() {
		return descripcion
	}
}

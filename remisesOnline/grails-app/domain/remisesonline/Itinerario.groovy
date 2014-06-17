package remisesonline

class Itinerario {
	
	String descripcion
	
	static hasMany = [paradas: Parada]

	static constraints = {
		descripcion blank: false, nullable: false
	}
  
	String toString() {
		return descripcion
	}
}

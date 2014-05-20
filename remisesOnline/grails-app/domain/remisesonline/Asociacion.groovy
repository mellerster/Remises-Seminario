package remisesonline

class Asociacion {
	Integer orden
	static belongsTo = [itinerario: Itinerario, lugar: Lugar]

    static constraints = {
		orden min: 1
    }
	
	static mapping = {
		sort orden: "desc"
	}
	
	String toString() {
		return "${orden} - ${lugar}"
	}
}

package remisesonline

class Itinerario {
	
	String descripcion
	
	static hasMany = [lugares: Lugar]

    static constraints = {
		descripcion blank: false, nullable: false
    }
}

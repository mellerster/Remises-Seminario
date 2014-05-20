package remisesonline

class Itinerario {
	
	String descripcion
	
	static hasMany = [lugares: Asociacion]

    static constraints = {
		descripcion blank: false, nullable: false
    }
}

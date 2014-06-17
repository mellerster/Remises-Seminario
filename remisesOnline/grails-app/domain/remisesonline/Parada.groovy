package remisesonline

class Parada {
	String localidad
	String calle
	Integer numero
	String descripcion
	
	//static hasMany = [itinerarios: Asociacion]
	static belongsTo = [Itinerario]

    static constraints = {
		calle blank: false, nullable: false
		numero blank: false, nullable: false
		descripcion blank: false, nullable: true
		localidad blank: false, nullable: true
    }
	
	String toString() {
		return "${calle} ${numero} - ${localidad}"
	}
}

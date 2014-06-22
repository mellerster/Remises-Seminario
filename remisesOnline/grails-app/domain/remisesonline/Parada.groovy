package remisesonline

class Parada {
	String localidad
	String calle
	Integer numero
	String descripcion
  
  //boolean deleted
  //static transients = [ 'deleted' ]
	
	static belongsTo = [Itinerario]

	static constraints = {
		calle blank: false, nullable: false
		numero blank: false, nullable: false
		descripcion blank: true, nullable: true
		localidad blank: true, nullable: true
	}
	
	String toString() {
		return "${calle} ${numero} - ${localidad}"
	}
}

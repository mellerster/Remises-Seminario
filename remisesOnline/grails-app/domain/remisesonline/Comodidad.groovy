package remisesonline

class Comodidad {
	String descripcion
	
	static constraints = {
		descripcion unique: true, blank: false, nullable: false
	}
	
	String toString() {
		return descripcion
	}
}

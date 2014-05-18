package remisesonline

class Lugar {
	String direccion
	String descripcion

    static constraints = {
		direccion unique:true, blank:false, nullable:false
		descripcion unique:true, blank:false, nullable:false
		
    }
	
	String toString() {
		return direccion
	}
}

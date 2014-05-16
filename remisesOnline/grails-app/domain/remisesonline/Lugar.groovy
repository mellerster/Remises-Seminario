package remisesonline

class Lugar {
	String direccion

    static constraints = {
		direccion unique:true, blank:false, nullable:false
    }
	
	String toString() {
		return direccion
	}
}

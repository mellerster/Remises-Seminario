package remisesonline

class Chofer {
	String nombre
	String dni
	String telefono
	String direccion
	String licencia
	
    static constraints = {
		dni unique :true, blank : false, nullable:false , minValue:1000000
		nombre blank:false
		telefono()
		direccion()
		licencia()
    }
	
	String toString(){
		return nombre
	}
}

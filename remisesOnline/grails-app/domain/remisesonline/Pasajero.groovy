package remisesonline

class Pasajero {
	String email
	String nombre
	String telefono
	Date fechaNacimiento
	
    static constraints = {
		nombre nullable:false,blank:false
		email email:true, blank:false, unique:true
		telefono blank:false
    }
}

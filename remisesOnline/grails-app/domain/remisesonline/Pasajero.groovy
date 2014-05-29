package remisesonline

class Pasajero {
	String email
	String nombre
	String telefono
	Date fechaNacimiento
	
	static hasMany = [amigos : Pasajero, reservas : Reserva]
	
    static constraints = {
		nombre nullable:false,blank:false
		email email:true, blank:false, unique:true
		telefono blank:false
    }
	
	String toString(){
		return "${nombre} - ${email}" 
	}
}

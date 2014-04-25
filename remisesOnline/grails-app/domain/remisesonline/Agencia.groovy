package remisesonline

class Agencia {
	String nombre
	String telefono
	String codigo
	
	static hasMany = [remises : Remise]
	
    static constraints = {
		nombre nullable : false, unique : true
		telefono blank:false
		remises min:0
    }
	
	String toString(){
		return nombre
	}
}

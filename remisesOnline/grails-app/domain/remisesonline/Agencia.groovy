package remisesonline

class Agencia {
	String nombre
	String telefono
	String codigo
	
	static hasMany = [remises: Remise, choferes: Chofer]
	
    static constraints = {
		nombre nullable: false, unique: true
		telefono blank: false
		remises minSize: 0
    }
	
	String toString(){
		return nombre
	}
}

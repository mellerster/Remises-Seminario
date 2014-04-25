package remisesonline

class Remise {
	String patente
	
	
	Chofer chofer
	
	static hasMany = [comodidades : Comodidad]
	static belongsTo = [agencia : Agencia]
	
	static constraints = {
		patente unique:true, nullable : false, blank : false
		comodidades min:0 
		chofer nullable :false
    }
	
	String toString() {
		return  patente
	}
}

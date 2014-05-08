package remisesonline

class Remise {
	String patente
	byte[] foto
	Chofer chofer
	
	static hasMany = [comodidades : Comodidad]
	static belongsTo = [agencia : Agencia]
	
	static constraints = {
		patente unique:true, nullable : false, blank : false
		comodidades min:0 
		chofer nullable :false
		foto nullable:true, maxSize: 1024 * 1024 * 2 /* 2MB */
    }
	
	String toString() {
		return  patente
	}
	
	void agregarComodidad(Comodidad comodidad){
		if (!comodidades.contains(comodidad))
			comodidades.add(comodidad);
	}
}

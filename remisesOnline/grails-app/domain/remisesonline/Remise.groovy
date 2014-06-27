package remisesonline

class Remise {
	String patente
	byte[] foto
	Chofer chofer
	def estado
	static hasMany = [comodidades: Comodidad]
	static belongsTo = [agencia: Agencia]
	
	static constraints = {
		patente unique: true, nullable: false, blank: false
		agencia nullable: true
		comodidades min: 0 
		chofer nullable: false,unique:true
					 /*, validator: { param_chof, param_remi ->
								Set dniChoferesPropios = []
								dniChoferesPropios.addAll(param_remi.agencia?.choferes.collect{it.dni})
								if (!dniChoferesPropios.contains(param_chof.dni))
									return ['invalid.choferInvalido'] 
					 }*/
		foto nullable: true, maxSize: 1024 * 1024 * 2 /* 2MB */
		estado inList: ["Con Pasajeros", "Vacio", "Reservado"]
		}
	
	String toString() {
		return	patente
	}
}

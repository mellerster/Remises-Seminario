package remisesonline

class ServicioDeRemiseria {
	String nombre
	String descripcion
	Set adheridos = []
	static belongsTo = [agencia : Agencia]
	static hasMany = [adheridos:Pasajero]

	static constraints = {
		nombre nullable:false, blank:false
		descripcion blank:false
		adheridos min:0
		agencia nullable:false
	}

	String toString() {
		nombre
	}

	def getCantidadAdheridos() {
		adheridos.size()
	}
}

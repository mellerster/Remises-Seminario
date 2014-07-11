package remisesonline


class Remise {
	String patente
	byte[] foto
	Chofer chofer
	Set comodidades = []
	Set calificaciones = []
	static hasMany = [comodidades: Comodidad, calificaciones : Calificacion]
	static belongsTo = [agencia: Agencia]

	static constraints = {
		patente unique: true, nullable: false, blank: false
		agencia nullable: true
		comodidades min: 0
		chofer nullable: false,unique:true
		foto nullable: true, maxSize: 1024 * 1024 * 2 /* 2MB */
		calificaciones minSize:0
	}

	String toString() {
		return	patente
	}

	def getCalificacion() {
		if(calificaciones.size() > 0) {
			return (calificaciones.inject(0) { acc, val -> acc + val.puntaje } / calificaciones.size())			
		}else {
			return 5
		}
	}
}

package remisesonline


class Agencia {
	String nombre
	String telefono
	String email
	Set remises = []
	Set reservas = []
	Set choferes = []
	Set servicios =  []
	static hasMany = [remises: Remise, choferes: Chofer, reservas: Reserva, promociones: Promocion, servicios : ServicioDeRemiseria]

	static constraints = {
		nombre nullable: false, unique: true
		telefono blank: false
		remises minSize: 0
		email email: true
	}

	String toString() {
		return nombre
	}

	def choferesSinRemise() {
		def choferesAsignados = remises.collect{it.chofer.id}
		return choferes.findAll{!choferesAsignados.contains(it.id)}
	}

	def choferesSinRemise(Chofer chofer) {
		def choferesAsignados = remises.collect{it.chofer.id}
		return choferes.findAll{!choferesAsignados.contains(it.id)}.add(chofer);
	}

}

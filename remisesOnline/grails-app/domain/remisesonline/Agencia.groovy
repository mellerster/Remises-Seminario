package remisesonline

import com.sun.org.apache.bcel.internal.generic.RETURN;

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
		/*reservas (validator: { rsrvs, agcia ->
								// rsrv_pat contiene las patentes de todos los remises asignados a reservas
								def rsrv_pat = rsrvs.findAll{ it.remise }.collect{ it.remise.patente }.unique()
								// ag_pat contiene las patentes de todos los remises de esta agencia
								def ag_pat = agcia.remises.collect{ it.patente }.unique()
								if (rsrv_pat.removeAll(ag_pat))
									return ['invalid.remiseinvalido']				 
						 })*/
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
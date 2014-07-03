package remisesonline

class SolicitudQuieroIrJunto {
	static final ESTADOS_VALIDOS = ['Pendiente', 'Aprobada', 'Denegada']

	Pasajero solicitado
	Reserva reservaSolicitada
	Date fechaCreada = new Date()
	String estado = new String('Pendiente')
	
	
	static belongsTo = [pasajero: Pasajero]
	
	static constraints = {
		estado inList: ESTADOS_VALIDOS
	}
	
	String toString() {
		"$solicitado - $pasajero - $fechaCreada ---- $reservaSolicitada ---- $estado"
	}
	
	def getPendiente() {
		estado == 'Pendiente'
	}
}

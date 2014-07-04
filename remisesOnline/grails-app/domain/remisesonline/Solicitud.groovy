package remisesonline

class Solicitud {
	static final ESTADOS_VALIDOS = ['Pendiente', 'Aprobada', 'Denegada']

	Date fechaCreada = new Date()
	String estado = 'Pendiente'
	
	static belongsTo = [pasajero: Pasajero]

	static constraints = {
		estado inList: ESTADOS_VALIDOS
	}
	
	def getPendiente() {
		estado == 'Pendiente'
	}
}

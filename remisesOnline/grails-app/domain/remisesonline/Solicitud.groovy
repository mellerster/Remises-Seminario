package remisesonline

enum ESTADOS_SOLICITUD{
	Pendiente,
	Aprobada,
	Denegada,
	CanceladaPorElDuenio{
		@Override
		public String toString(){
			"Cancelada Por El Dueño"
		}
	}
}

class Solicitud {
	Date fechaCreada = new Date()
	ESTADOS_SOLICITUD estado = ESTADOS_SOLICITUD.Pendiente
	
	static belongsTo = [pasajero: Pasajero]

	static constraints = {
		estado blank:false
	}
	
	def getPendiente() {
		estado ==  ESTADOS_SOLICITUD.Pendiente
	}
	
	def aprobar(){
		estado = ESTADOS_SOLICITUD.Aprobada
	}
	
	def denegar(){
		estado = ESTADOS_SOLICITUD.Denegada
	}
}

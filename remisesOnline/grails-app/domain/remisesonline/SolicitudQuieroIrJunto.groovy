package remisesonline

class SolicitudQuieroIrJunto extends Solicitud {
	static final ESTADOS_VALIDOS = ['Pendiente', 'Aprobada', 'Denegada', 'Cancelada por el due�o']
	
	Pasajero solicitado
	Reserva reservaSolicitada
	
	String toString() {
		"$solicitado - $pasajero - $fechaCreada ---- $reservaSolicitada ---- $estado"
	}
	
}

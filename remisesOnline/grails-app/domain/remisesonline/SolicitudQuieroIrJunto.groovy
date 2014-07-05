package remisesonline

class SolicitudQuieroIrJunto extends Solicitud {
	static final ESTADOS_VALIDOS = ['Pendiente', 'Aprobada', 'Denegada', 'Cancelada por el dueño']
	
	Pasajero solicitado
	Reserva reservaSolicitada
	
	String toString() {
		"$solicitado - $pasajero - $fechaCreada ---- $reservaSolicitada ---- $estado"
	}
	
}

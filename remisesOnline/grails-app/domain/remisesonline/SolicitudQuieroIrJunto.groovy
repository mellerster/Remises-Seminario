package remisesonline

class SolicitudQuieroIrJunto extends Solicitud {

	Pasajero solicitado
	Reserva reservaSolicitada
	
	String toString() {
		"$solicitado - $pasajero - $fechaCreada ---- $reservaSolicitada ---- $estado"
	}
	
}

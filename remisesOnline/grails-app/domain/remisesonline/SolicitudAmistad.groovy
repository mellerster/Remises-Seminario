package remisesonline



class SolicitudAmistad extends Solicitud {
	Pasajero solicitado

	static constraints ={
		estado inList: [ESTADOS_SOLICITUD.Pendiente,ESTADOS_SOLICITUD.Aprobada, ESTADOS_SOLICITUD.Denegada]
	}

	String toString() {
		"$solicitado - $pasajero - $fechaCreada $estado"
	}

}

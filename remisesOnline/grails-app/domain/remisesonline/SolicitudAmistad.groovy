package remisesonline

class SolicitudAmistad extends Solicitud {
	Pasajero solicitado
	
	String toString() {
		"$solicitado - $pasajero - $fechaCreada $estado"
	}

}

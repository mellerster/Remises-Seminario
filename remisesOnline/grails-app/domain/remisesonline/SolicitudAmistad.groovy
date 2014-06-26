package remisesonline

class SolicitudAmistad {
	static final ESTADOS_VALIDOS = ['Pendiente', 'Aprobada', 'Denegada']

	Pasajero solicitado
	Date fechaCreada
	String estado
	
	static belongsTo = [pasajero: Pasajero]

    static constraints = {
	
	
	
		estado inList: ESTADOS_VALIDOS
    }
	
		String toString() {
		"$fechaCreada $estado"
	}
}

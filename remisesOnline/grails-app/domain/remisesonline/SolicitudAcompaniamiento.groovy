package remisesonline

class SolicitudAcompaniamiento {
	static final ESTADOS_VALIDOS = ['Pendiente', 'Aprobada', 'Denegada']

	Pasajero solicitado
	Reserva reservaSolicituada
	Date fechaCreada = new Date()
	String estado = new String('Pendiente')
	
	
	static belongsTo = [pasajero: Pasajero]
	
    static constraints = {

		estado inList: ESTADOS_VALIDOS
    }
}

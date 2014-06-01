package remisesonline

class Reserva {
	Itinerario destinos = new Itinerario()
	Remise remise
	Date fechaReserva
	String estado
	Date creado = new Date()
	
	static belongsTo = [agencia: Agencia, pasajero: Pasajero]

    static constraints = {
		// destinos nullable: true
		remise nullable: true //en la reserva puede preferir algun remise o no
		fechaReserva (validator: {
				def now = new Date()
				def calendar = now.toCalendar()
				calendar.add(Calendar.MONTH, 1)
				if  (it < now || it > calendar.time) 
					return ['invalid.rango']
		})
		estado inList: ['Pendiente', 'En curso', 'Cerrada', 'Cancelada']	
    }
}

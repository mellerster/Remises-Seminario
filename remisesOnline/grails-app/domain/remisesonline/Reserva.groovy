package remisesonline

class Reserva {
	//Lugar destino
	Remise remise
	Date fechaReserva
	String estado
	
	static belongsTo = [agencia: Agencia]

    static constraints = {
//		destino nullable:false
		remise nullable:true //en la reserva puede preferir algun remise o no
		fechaReserva (validator: {
				def now = new Date()
				def calendar = now.toCalendar()
				calendar.add(Calendar.MONTH, 1)
				if  (it < now || it > calendar.time) 
					return ['invalid.rango']
		})
		estado inList: ['Abierto', 'Cerrado']
    }
}

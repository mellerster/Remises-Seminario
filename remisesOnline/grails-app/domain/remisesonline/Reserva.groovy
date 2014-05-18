package remisesonline

class Reserva {
	//Lugar destino
	//Remise remise
	Date fechaReserva

    static constraints = {
//		destino nullable:false
//		remise nullable:false
        fechaReserva min: new Date(), max: (new Date()) + 30
    }
}

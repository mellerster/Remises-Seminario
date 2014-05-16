package remisesonline

import java.util.Date

class Reserva {
	//Lugar destino
	//Remise remise
	Date fechaReserva

    static constraints = {
//		destino nullable:false
//		remise nullable:false
        //fechaReserva min: new Date(), max: (new Date()) + 30
        fechaReserva(validator: {return (it > new Date())})
    }
}

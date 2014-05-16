package remisesonline

class Reserva {
	//Lugar destino
	//Remise remise
	Date fechaReserva

    static constraints = {
//		destino nullable:false
//		remise nullable:false
        //fechaReserva min: new Date(), max: (new Date()) + 30
        fechaReserva(
                validator: {
                    Date downLimit = new Date()
                    Date upLimit = downLimit + 30

                    return (it > downLimit && it < upLimit )
        })
    }
}

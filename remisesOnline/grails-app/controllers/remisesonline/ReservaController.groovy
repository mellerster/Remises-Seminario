package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class ReservaCommand {
	static final ESTADOS_VALIDOS = ['Pendiente', 'En curso', 'Cerrada', 'Cancelada']
	Itinerario destinos = new Itinerario()
	Remise remise
	Date fechaReserva
	String estado = ESTADOS_VALIDOS[0]
  Agencia agencia

	static constraints = {
		remise nullable: true,
					validator: { remis_param, reserva_ref ->
							if (remis_param && reserva_ref?.agencia)
								if (!(reserva_ref.agencia.remises.find{it.patente == remis_param.patente}))
									return ['invalid.remisenopertenece']
					 }

		fechaReserva validator: {
				def now = new Date()
				def calendar = now.toCalendar()
				calendar.add(Calendar.MONTH, 1)
				if	(it < now || it > calendar.time)
					return ['invalid.rango']
		}
		estado inList: ESTADOS_VALIDOS
	}
}

@Transactional(readOnly = true)
class ReservaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Reserva.list(params), model:[reservaInstanceCount: Reserva.count()]
    }

    def show(Reserva reserva) {
        respond reserva
    }

    def create() {
        respond new Reserva(params)
    }

    @Transactional
    def save(ReservaCommand reservaCommandInstance) {
        if (reservaCommandInstance == null) {
            notFound()
            return
        }

        if (reservaCommandInstance.hasErrors()) {
            respond reservaCommandInstance.errors, view:'create'
            return
        }

        Reserva reserva = new Reserva()
        reserva.properties = reservaCommandInstance;
        reserva.pasajero =  Pasajero.get(session.pasajero.id)

        reserva.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'reserva.label', default: 'Reserva'), reserva.id])
                redirect reserva
            }
            '*' { respond reserva, [status: CREATED] }
        }
    }

    def edit(Reserva reserva) {
        respond reserva
    }

    @Transactional
    def update(ReservaCommand reservaCommandInstance) {
        if (reservaCommandInstance == null) {
            notFound()
            return
        }

        if (reservaCommandInstance.hasErrors()) {
            respond reservaCommandInstance.errors, view:'create'
            return
        }

        Reserva reserva = new Reserva()
        reserva.properties = reservaCommandInstance;
        reserva.pasajero =  Pasajero.get(session.pasajero.id)

        reserva.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Reserva.label', default: 'Reserva'), reserva.id])
                redirect reserva
            }
            '*'{ respond reserva, [status: OK] }
        }
    }

    @Transactional
    def delete(Reserva reserva) {

        if (reserva == null) {
            notFound()
            return
        }

        reserva.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Reserva.label', default: 'Reserva'), reserva.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'reserva.label', default: 'Reserva'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ReservaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Reserva.list(params), model:[reservaInstanceCount: Reserva.count()]
    }

    def show(Reserva reservaInstance) {
        respond reservaInstance
    }

    def create() {
        respond new Reserva(params)
    }

    @Transactional
    def save(Reserva reservaInstance) {
        if (reservaInstance == null) {
            notFound()
            return
        }
        
				println 'grabando reserva...'
				
				println reservaInstance.paradas
				println 'params...'
				println params
				
				reservaInstance.properties = params
				
        reservaInstance.pasajero =  Pasajero.get(session.pasajero.id)
        reservaInstance.validate()

        if (reservaInstance.hasErrors()) {
            respond reservaInstance.errors, view:'create'
            return
        }

        reservaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'reservaInstance.label', default: 'Reserva'), reservaInstance.id])
                redirect reservaInstance
            }
            '*' { respond reservaInstance, [status: CREATED] }
        }
    }

    def edit(Reserva reservaInstance) {
        respond reservaInstance
    }

    @Transactional
    def update(Reserva reservaInstance) {
        if (reservaInstance == null) {
            notFound()
            return
        }
        
        reservaInstance.pasajero =  Pasajero.get(session.pasajero.id)
        reservaInstance.validate()

        if (reservaInstance.hasErrors()) {
            respond reservaInstance.errors, view:'edit'
            return
        }

        reservaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Reserva.label', default: 'Reserva'), reservaInstance.id])
                redirect reservaInstance
            }
            '*'{ respond reservaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Reserva reservaInstance) {

        if (reservaInstance == null) {
            notFound()
            return
        }

        reservaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Reserva.label', default: 'Reserva'), reservaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservaInstance.label', default: 'Reserva'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

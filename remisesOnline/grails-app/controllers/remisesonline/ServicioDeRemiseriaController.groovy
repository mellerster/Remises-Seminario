package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ServicioDeRemiseriaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
		def agencia = Agencia.get(session.agencia.id)
		def listado = agencia.servicios.toList()
        respond listado, model:[servicioDeRemiseriaInstanceCount: listado.size()]
    }

    def show(ServicioDeRemiseria servicioDeRemiseriaInstance) {
        respond servicioDeRemiseriaInstance
    }

    def create() {
        respond new ServicioDeRemiseria(params)
    }

    @Transactional
    def save(ServicioDeRemiseria servicioDeRemiseriaInstance) {
        if (servicioDeRemiseriaInstance == null) {
            notFound()
            return
        }
		servicioDeRemiseriaInstance.agencia = session.agencia
		servicioDeRemiseriaInstance.validate()
        if (servicioDeRemiseriaInstance.hasErrors()) {
            respond servicioDeRemiseriaInstance.errors, view: 'create'
            return
        }

        servicioDeRemiseriaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'servicioDeRemiseriaInstance.label', default: 'Servicio De Remiseria'), servicioDeRemiseriaInstance])
                redirect servicioDeRemiseriaInstance
            }
            '*' { respond servicioDeRemiseriaInstance, [status: CREATED] }
        }
    }

    def edit(ServicioDeRemiseria servicioDeRemiseriaInstance) {
        respond servicioDeRemiseriaInstance
    }

    @Transactional
    def update(ServicioDeRemiseria servicioDeRemiseriaInstance) {
        if (servicioDeRemiseriaInstance == null) {
            notFound()
            return
        }

        if (servicioDeRemiseriaInstance.hasErrors()) {
            respond servicioDeRemiseriaInstance.errors, view: 'edit'
            return
        }

        servicioDeRemiseriaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ServicioDeRemiseria.label', default: 'ServicioDeRemiseria'), servicioDeRemiseriaInstance.id])
                redirect servicioDeRemiseriaInstance
            }
            '*'{ respond servicioDeRemiseriaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ServicioDeRemiseria servicioDeRemiseriaInstance) {

        if (servicioDeRemiseriaInstance == null) {
            notFound()
            return
        }
		
        servicioDeRemiseriaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ServicioDeRemiseria.label', default: 'ServicioDeRemiseria'), servicioDeRemiseriaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'servicioDeRemiseriaInstance.label', default: 'ServicioDeRemiseria'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
	def listServicios() {
		respond ServicioDeRemiseria.list(params), model:[servicioDeRemiseriaInstanceCount: ServicioDeRemiseria.count()]
	}
	
	def misAdhesiones() {
		respond ServicioDeRemiseria.list(params).grep{it.adheridos.id.contains(session.pasajero.id)}
	}
	
	def adherirse(ServicioDeRemiseria servicioDeRemiseriaInstance) {
		servicioDeRemiseriaInstance.adheridos.add(Pasajero.get(session.pasajero.id))
		servicioDeRemiseriaInstance.save flush:true
		redirect action:"show", id: servicioDeRemiseriaInstance.id
	}
	
	def desadherirse(ServicioDeRemiseria servicioDeRemiseriaInstance) {
		servicioDeRemiseriaInstance.adheridos.remove(Pasajero.get(session.pasajero.id))
		servicioDeRemiseriaInstance.save flush:true
		redirect action:"show", id: servicioDeRemiseriaInstance.id
	}
}

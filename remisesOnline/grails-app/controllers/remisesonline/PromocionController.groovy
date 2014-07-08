package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PromocionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
		def agencia = Agencia.get(session.agencia.id)
		def listado = agencia.promociones.toList()
		respond listado, model:[promocionInstanceCount: listado.size()]
    }

    def show(Promocion promocionInstance) {
        respond promocionInstance
    }

    def create() {
        respond new Promocion(params)
    }

    @Transactional
    def save(Promocion promocionInstance) {
        if (promocionInstance == null) {
            notFound()
            return
        }

		promocionInstance.agencia = Agencia.get(session.agencia.id)
		promocionInstance.validate()
		
        if (promocionInstance.hasErrors()) {
            respond promocionInstance.errors, view:'create'
            return
        }

        promocionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'promocionInstance.label', default: 'Promocion'), promocionInstance.id])
                redirect promocionInstance
            }
            '*' { respond promocionInstance, [status: CREATED] }
        }
    }

    def edit(Promocion promocionInstance) {
        respond promocionInstance
    }

    @Transactional
    def update(Promocion promocionInstance) {
        if (promocionInstance == null) {
            notFound()
            return
        }

        if (promocionInstance.hasErrors()) {
            respond promocionInstance.errors, view:'edit'
            return
        }

        promocionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Promocion.label', default: 'Promocion'), promocionInstance.id])
                redirect promocionInstance
            }
            '*'{ respond promocionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Promocion promocionInstance) {

        if (promocionInstance == null) {
            notFound()
            return
        }

        promocionInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Promocion.label', default: 'Promocion'), promocionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
	
	def eliminar(Promocion promocionInstance) {
		if (promocionInstance == null) {
            notFound()
            return
        }
        promocionInstance.delete flush:true
		flash.message = "La promocion fue eliminada correctamente"
		redirect action: 'index', controller: 'promocion'
	}
	
	
}
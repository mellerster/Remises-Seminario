package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ChoferController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		def agencia = Agencia.findByNombre(session.agencia.nombre)
		def lista = agencia.choferes.toList()
		respond lista, model: [choferInstanceCount: lista.size()]
    }

    def show(Chofer choferInstance) {
        respond choferInstance
    }

    def create() {
		def agencia = Agencia.get(session.agencia.id)
		println agencia
		params.agencia = agencia;
		respond new Chofer(params)
		
    }

    @Transactional
    def save(Chofer choferInstance) {
        if (choferInstance == null) {
            notFound()
            return
        }
		
        if (choferInstance.hasErrors()) {
            respond choferInstance.errors, view:'create'
            return
        }

        choferInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'choferInstance.label', default: 'Chofer'), choferInstance])
                redirect choferInstance
            }
            '*' { respond choferInstance, [status: CREATED] }
        }
    }

    def edit(Chofer choferInstance) {
        respond choferInstance
    }

    @Transactional
    def update(Chofer choferInstance) {
        if (choferInstance == null) {
            notFound()
            return
        }

        if (choferInstance.hasErrors()) {
            respond choferInstance.errors, view:'edit'
            return
        }

        choferInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Chofer.label', default: 'Chofer'), choferInstance])
                redirect choferInstance
            }
            '*'{ respond choferInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Chofer choferInstance) {

        if (choferInstance == null) {
            notFound()
            return
        }

        choferInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Chofer.label', default: 'Chofer'), choferInstance])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'choferInstance.label', default: 'Chofer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

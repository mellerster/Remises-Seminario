package remisesonline

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RemiseController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def agencia = Agencia.findByNombre(session.agencia.nombre)
		def lista = agencia.remises.toList()
		respond lista, model: [remiseInstanceCount: lista.size()]
	}
	
    def show(Remise remiseInstance) {
        respond remiseInstance
    }

    def create() {
		def agencia = Agencia.get(session.agencia.id)
		params.agencia = agencia;
		[remiseInstance: new Remise(params), choferes: agencia.choferesSinRemise()]
    }

    @Transactional
    def save(Remise remiseInstance) {
        if (remiseInstance == null) {
            notFound()
            return
        }

        if (remiseInstance.hasErrors()) {
            respond remiseInstance.errors, view:'create'
            return
        }

        remiseInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'remiseInstance.label', default: 'Remise'), remiseInstance.id])
                redirect remiseInstance
            }
            '*' { respond remiseInstance, [status: CREATED] }
        }
    }

    def edit(Remise remiseInstance) {
		def agencia = Agencia.get(session.agencia.id)
		def choferesSinRemise = agencia.choferesSinRemise()
		println choferesSinRemise
		choferesSinRemise.add(remiseInstance.chofer)
		println choferesSinRemise
		[remiseInstance: remiseInstance, choferes: choferesSinRemise]
    }

    @Transactional
    def update(Remise remiseInstance) {
        if (remiseInstance == null) {
            notFound()
            return
        }

        if (remiseInstance.hasErrors()) {
            respond remiseInstance.errors, view:'edit'
            return
        }

        remiseInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Remise.label', default: 'Remise'), remiseInstance.id])
                redirect remiseInstance
            }
            '*'{ respond remiseInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Remise remiseInstance) {

        if (remiseInstance == null) {
            notFound()
            return
        }

        remiseInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Remise.label', default: 'Remise'), remiseInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'remiseInstance.label', default: 'Remise'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

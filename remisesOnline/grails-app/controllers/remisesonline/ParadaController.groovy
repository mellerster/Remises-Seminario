package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ParadaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Parada.list(params), model:[paradaInstanceCount: Parada.count()]
    }

    def show(Parada paradaInstance) {
        respond paradaInstance
    }

    def create() {
        respond new Parada(params)
    }

    @Transactional
    def save(Parada paradaInstance) {
        if (paradaInstance == null) {
            notFound()
            return
        }

        if (paradaInstance.hasErrors()) {
            respond paradaInstance.errors, view:'create'
            return
        }

        paradaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'paradaInstance.label', default: 'Parada'), paradaInstance.id])
                redirect paradaInstance
            }
            '*' { respond paradaInstance, [status: CREATED] }
        }
    }

    def edit(Parada paradaInstance) {
        respond paradaInstance
    }

    @Transactional
    def update(Parada paradaInstance) {
        if (paradaInstance == null) {
            notFound()
            return
        }

        if (paradaInstance.hasErrors()) {
            respond paradaInstance.errors, view:'edit'
            return
        }

        paradaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Parada.label', default: 'Parada'), paradaInstance.id])
                redirect paradaInstance
            }
            '*'{ respond paradaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Parada paradaInstance) {

        if (paradaInstance == null) {
            notFound()
            return
        }

        paradaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Parada.label', default: 'Parada'), paradaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'paradaInstance.label', default: 'Parada'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

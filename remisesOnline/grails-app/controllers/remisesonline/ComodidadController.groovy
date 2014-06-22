package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ComodidadController {


	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", actualizar:"PUT"]

	def modificar(Remise remiseInstance){
		respond remiseInstance
	}

	def actualizar(Remise remiseInstance){
		Remise remise = Remise.get(remiseInstance.id)
		remise.comodidades.clear()
		params.each {
			if (it.key.startsWith("comodidad_"))
				remise.comodidades << Comodidad.get((it.key - "comodidad_") as Integer)
		}
		remise.save flush:true
		redirect action:'show', controller:'remise',id:remiseInstance.id
	}
	

	def create() {
		respond new Comodidad(params)
	}

	@Transactional
	def save(Comodidad comodidadInstance) {
		if (comodidadInstance == null) {
			notFound()
			return
		}

		if (comodidadInstance.hasErrors()) {
			respond comodidadInstance.errors, view:'create'
			return
		}

		comodidadInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [
					message(code: 'comodidadInstance.label', default: 'Comodidad'),
					comodidadInstance
				])
				redirect controller:'remise',action:'index'
			}
			'*' { respond comodidadInstance, [status: CREATED] }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'comodidadInstance.label', default: 'Comodidad'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}

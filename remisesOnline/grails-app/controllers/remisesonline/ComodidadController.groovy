package remisesonline



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ComodidadController {


	static allowedMethods = [ actualizar:"PUT"]

	def modificar(Remise remiseInstance) {
		respond remiseInstance
	}

	def actualizar(Remise remiseInstance) {
		Remise remise = Remise.get(remiseInstance.id)
		remise.comodidades.clear()
		params.grep{it.key.startsWith("comodidad_")}.each {
				remise.comodidades << Comodidad.get((it.key - "comodidad_") as Integer)
		}
		remise.save flush:true
		redirect action: 'show', controller: 'remise',id:remiseInstance.id
	}
	@Transactional
	def agregar() {
		Comodidad comodidad = new Comodidad()
		comodidad.descripcion = params.nuevacomodidad
		comodidad.validate()
		if(comodidad.hasErrors()) {
			flash.message = "Error,no se pudo crear la comodidad ${params.nuevacomodidad}"
			redirect controller: 'comodidad', action: 'modificar',id:params.id
			return
		}
		
		comodidad.save flush:true
		
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [
					message(code: 'comodidadInstance.label', default: 'Comodidad'),
					comodidad
				])
				redirect controller: 'comodidad',action: 'modificar',id:params.id
			}
			'*' { respond comodidad, [status: CREATED] }
		}
	}
}

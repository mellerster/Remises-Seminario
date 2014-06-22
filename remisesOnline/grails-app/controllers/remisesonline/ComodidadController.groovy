package remisesonline

class ComodidadController {

	def scaffold = true
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
}

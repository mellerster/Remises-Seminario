package remisesonline

class RemiseController {

    def scaffold = true
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def agencia = Agencia.findByNombre(session.agencia.nombre)
		def lista =agencia.remises.toList()
		respond lista, model:[remisesCount : lista.size()]
	}
}

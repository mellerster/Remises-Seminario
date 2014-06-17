package remisesonline

class ChoferController {

	def scaffold = true
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def agencia = Agencia.findByNombre(session.agencia.nombre)
		def lista = agencia.choferes.toList()
		respond lista, model: [choferCount: lista.size()]
	}
}

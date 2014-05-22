package remisesonline

class AgenciaController {
	def scaffold = true
	
	def logout(){
		session.agencia = null
		redirect(action:"login")
	}
	
	def login(){
		[agencias: Agencia.list()]
	}
	
	def entrar(){
		session.agencia = Agencia.findByNombre(params.agencia)
		redirect action:'index' ,controller:'remise'
	}
}

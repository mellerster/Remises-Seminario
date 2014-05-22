package remisesonline

class AgenciaController {
	def scaffold = true
	
	def logout(){
		session.agencia = null
		redirect(action:"index")
	}
}

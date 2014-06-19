package remisesonline

class AgenciaFilters {
	def filters = {
		agenciaOnly(controller:'(remise|chofer)',
		action:"(index|create|edit)") {
			before = {
				if(!session?.agencia){
					flash.message = "Solo pueden ingresar agencias que han iniciado sesión"
					redirect(controller:"agencia", action:"login")
					return false
				}
			}
		}
	}
}

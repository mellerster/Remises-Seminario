package remisesonline

class AgenciaFilters {
	def filters = {
		agenciaOnly(controller:'(remise|chofer)',
		action:"(index|create|edit|save|update)") {
			before = {
				if(!session?.agencia){
					flash.message = "Solo pueden ingresar agencias que han iniciado sesi�n"
					redirect(controller:"agencia", action:"login")
					return false
				}
			}
		}
	}
}

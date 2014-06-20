package remisesonline

class AgenciaFilters {
	def filters = {
		agenciaOnly(controller:'(remise|chofer)',
		action:"(index|create|edit|save|update)") {
			before = {
				if(!session?.agencia){
					flash.message = "Solo pueden ingresar agencias que han iniciado sesión"
					redirect(controller:"agencia", action:"login")
					return false
				}
			}
		}
		
		agenciaListadoReservas(controller:"agencia", action:"(searchReservas|listReservas)"){
			before = {
				if(!session?.agencia){
					flash.message = "Solo pueden ingresar agencias que han iniciado sesión"
					redirect(controller:"agencia", action:"login")
					return false
				}
			}
		}
		
		agenciaAdmin(controller:'agencia',
		action:"edit|show|delete|update") {
			before={
				if(!session?.agencia){
					flash.message = "Solo pueden ingresar agencias que han iniciado sesión"
					redirect(controller:"agencia", action:"login")
					return false
				}else{
					if(!session?.agencia.id.toString().equals( params.id.toString())){
						flash.message = "Solo puede ver y modificar sus datos"
						redirect(action:"show", id:session?.agencia.id,controller:"agencia")
						return false
					}
				}
			}
		}
	}
}

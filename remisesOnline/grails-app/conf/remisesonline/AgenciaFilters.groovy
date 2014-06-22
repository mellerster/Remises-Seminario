package remisesonline

class AgenciaFilters {
	def filters = {
		agenciaOnly(controller:'(remise|chofer|comodidad)',
		action:"(index|create|edit|save|update|delete|modificar|actualizar)") {
			before = {
				if(!session?.agencia){
					flash.message = "Solo pueden ingresar agencias que han iniciado sesión"
					redirect(controller:"agencia", action:"login")
					return false
				}
				if(params.id){
					switch (params.controller){
						case "remise":
						case "comodidad":
							if(!Agencia.get(session.agencia.id).remises.id.toString().contains(params.id.toString())){
								flash.message = "Solo puede modificar sus remises"
								redirect(action:"index", controller:"remise")
								return false
							}
							break;
						case "chofer":
							if(!Agencia.get(session.agencia.id).choferes.id.toString().contains(params.id.toString())){
								flash.message = "Solo puede modificar sus choferes"
								redirect(action:"index", controller:"chofer")
								return false
							}
							break;
					}
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

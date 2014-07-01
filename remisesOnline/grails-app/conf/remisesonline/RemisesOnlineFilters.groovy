package remisesonline

class RemisesOnlineFilters {
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

		agenciaCalificarPasajero(controller:"reserva",action:"(calificarPasajero|guardarCalificacionPasajero)"){
			before = {
				if(!session?.agencia){
					flash.message = "Solo pueden ingresar agencias que han iniciado sesión"
					redirect(controller:"agencia", action:"login")
					return false
				}
				def reserva = Reserva.get(params.id)
				if(reserva.agencia.id != session?.agencia?.id){
					flash.message = "No se puede calificar esta reserva ya que no pertenece a esta agencia"
					redirect controller:'agencia',action:'listReservas',params:[estadoSeleccionado:'Cerrada']
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

		pasajeroOnly(controller:'pasajero',
		action:"(listReservas|edit|update|delete|amigos|listSolicitudesAmigosEnviadas|listSolicitudesAmigosRecibidas|quieroIrJunto|showReservasAmigos|listReservasDeAmigo|listSolicitudesAcompaniamientoEnviadas|listSolicitudesAcompaniamientoRecibidas|listPromociones)"){
			before={
				if(!session?.pasajero){
					flash.message = "Solo pueden ingresar pasajeros que han iniciado sesión"
					redirect(controller:"pasajero", action:"login")
					return false
				}
			}
		}

		pasajeroCrearReserva(controller:'reserva',action:"(create|save)"){
			before={
				if(!session?.pasajero){
					flash.message = "Solo pueden ingresar pasajeros que han iniciado sesión"
					redirect(controller:"pasajero", action:"login")
					return false
				}
			}
		}
	}
}

package remisesonline

class RemisesOnlineFilters {
	def filters = {
		agenciaOnly(controller: '(remise|chofer|comodidad|servicioDeRemiseria)',
		action:"(index|create|edit|save|update|delete|modificar|actualizar)") {
			before = {
				if(!session?.agencia) {
					flash.message = "Solo pueden ingresar agencias que han iniciado sesi�n"
					redirect(controller:"agencia", action:"login")
					return false
				}
				if(params.id) {
					switch (params.controller) {
						case "remise":
						case "comodidad":
							if(!Agencia.get(session.agencia.id).remises.id.toString().contains(params.id.toString())) {
								flash.message = "Solo puede modificar sus remises"
								redirect(action:"index", controller:"remise")
								return false
							}
							break;
						case "chofer":
							if(!Agencia.get(session.agencia.id).choferes.id.toString().contains(params.id.toString())) {
								flash.message = "Solo puede modificar sus choferes"
								redirect(action:"index", controller:"chofer")
								return false
							}
							break;
						case "servicioDeRemiseria":
							if(!Agencia.get(session.agencia.id).servicios.id.toString().contains(params.id.toString())) {
								flash.message = "Solo puede modificar sus servicios"
								redirect(action:"index", controller:"serivicioderemiseria")
								return false
							}
							break
					}
				}
			}
		}

		agenciaListadoReservas(controller:"agencia", action:"(searchReservas|listReservas)") {
			before = {
				if(!session?.agencia) {
					flash.message = "Solo pueden ingresar agencias que han iniciado sesi�n"
					redirect(controller:"agencia", action:"login")
					return false
				}
			}
		}

		agenciaCalificarPasajero(controller:"reserva",action:"(calificarPasajero|guardarCalificacionPasajero)") {
			before = {
				if(!session?.agencia) {
					flash.message = "Solo pueden ingresar agencias que han iniciado sesi�n"
					redirect(controller:"agencia", action:"login")
					return false
				}
				def reserva = Reserva.get(params.id)
				if(reserva.agencia.id != session?.agencia?.id) {
					flash.message = "No se puede calificar esta reserva ya que no pertenece a esta agencia"
					redirect controller: 'agencia',action: 'listReservas',params:[estadoSeleccionado: 'Cerrada']
					return false
				}
			}
		}

		agenciaAdmin(controller: 'agencia',
		action:"edit|show|delete|update") {
			before={
				if(!session?.agencia) {
					flash.message = "Solo pueden ingresar agencias que han iniciado sesi�n"
					redirect(controller:"agencia", action:"login")
					return false
				} else {
					if(!session?.agencia.id.toString().equals( params.id.toString())) {
						flash.message = "Solo puede ver y modificar sus datos"
						redirect(action:"show", id:session?.agencia.id,controller:"agencia")
						return false
					}
				}
			}
		}

		pasajeroOnly(controller: 'pasajero',
		action:"(listReservas|edit|update|delete|amigos|listSolicitudesAmigosEnviadas|listSolicitudesAmigosRecibidas|eliminarAmigo|listPromociones|quieroIrJunto|showReservasAmigos|listReservasDeAmigo|listSolicitudesQuieroIrJuntoEnviadas|listSolicitudesQuieroIrJuntoRecibidas|unirseAReserva|listPromociones)") {
			before={
				if(!session?.pasajero) {
					flash.message = "Solo pueden ingresar pasajeros que han iniciado sesi�n"
					redirect(controller:"pasajero", action:"login")
					return false
				} else {
					if(params?.id) {
						switch(params.action) {
							case "edit":
							case "update":
							case "delete":
								if(!session.pasajero.id.toString().equals(params.id.toString())) {
									flash.message = "Solo puede modificar sus datos"
									redirect(controller:"pasajero",action:"show",id:session.pasajero.id)
									return false
								}
								break
						}
					}
				}
			}
		}

		pasajeroCrearReserva(controller: 'reserva',action:"(create|save)") {
			before={
				if(!session?.pasajero) {
					flash.message = "Solo pueden ingresar pasajeros que han iniciado sesi�n"
					redirect(controller:"pasajero", action:"login")
					return false
				}
			}
		}

		pasajeroCalificarRemise(controller: 'reserva', action: '(calificarRemise|guardarCalificacionRemise)') {
			before={
				if(!session?.pasajero) {
					flash.message = "Solo pueden ingresar pasajeros que han iniciado sesi�n"
					redirect(controller:"pasajero", action:"login")
					return false
				} else {
					def reserva = Reserva.get(params.id)
					if(reserva.pasajero.id != session?.pasajero?.id) {
						flash.message = "No se puede calificar esta reserva ya que no le pertenece"
						redirect controller: 'pasajero',action: 'listReservas'
						return false
					}
				}
			}
		}
		
		pasajeroServicioDeRemiseria(controller: 'servicioDeRemiseria', action: 'adherirse|desadherirse|listServicios|misAdhesiones') {
			before={
				if(!session?.pasajero) {
					flash.message = "Solo pueden ingresar pasajeros que han iniciado sesi�n"
					redirect(controller:"pasajero", action:"login")
					return false
				}
			}
		}
	}
}

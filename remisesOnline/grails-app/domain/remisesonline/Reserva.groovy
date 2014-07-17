package remisesonline

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;

enum ESTADOS_RESERVA{
	Pendiente,
	EnCurso{
		@Override
		public String toString() {
			return "En Curso";
		}
	},
	Cerrada,
	Cancelada,
	ConRemise{
		@Override
		public String toString() {
			return "Con Remise";
		}
	},
}

class Reserva {
	Remise remise
	Date fechaReserva
	ESTADOS_RESERVA estado = ESTADOS_RESERVA.Pendiente;
	Date creado = new Date()
	Boolean compartible = false
	List<Parada> paradas =  ListUtils.lazyList(new ArrayList(), {new Parada()} as Factory)
	Calificacion calificacionRemise = null
	Calificacion calificacionPasajero = null
	static belongsTo = [agencia: Agencia, pasajero: Pasajero]
	static hasMany = [paradas: Parada]

	static constraints = {
		remise nullable: true, //en la reserva puede preferir algun remise o no
		validator: { remis_param, reserva_ref ->
			if (remis_param && reserva_ref?.agencia)
				if (!(reserva_ref.agencia.remises.find{it.patente == remis_param.patente}))
					return ['invalid.remisenopertenece']
		}

		fechaReserva validator: { fecha_res, reserva_ref ->
			def now = new Date()
			def calendar = now.toCalendar()
			calendar.add(Calendar.MONTH, 1)
			if	((fecha_res < now || fecha_res > calendar.time) && reserva_ref.estado != ESTADOS_RESERVA.Cerrada)
				return ['invalid.rango']
		}
		estado blank:false
		calificacionRemise nullable:true
		calificacionPasajero nullable:true
	}

	String toString() {
		"Fecha: $fechaReserva - Estado: $estado"
	}

	def getPendiente() {
		estado == ESTADOS_RESERVA.Pendiente
	}

	def getEnCurso() {
		estado == ESTADOS_RESERVA.EnCurso
	}

	def getRemisAsignado() {
		estado == ESTADOS_RESERVA.ConRemise
	}

	def esCancelablePorPasajero(def pasajeroId) {
		(pendiente && pasajero.id == pasajeroId)
	}

	def esCerrablePorAgencia(def agenciaId) {
		(estado == ESTADOS_RESERVA.EnCurso && agencia.id == agenciaId)
	}

	def esPasableAEnCursoPorAgencia(def agenciaId) {
		(remisAsignado && agencia.id == agenciaId && remise != null)
	}

	def cancelar() {
		if (pendiente) {
			def limite = new Date().toCalendar()
			limite.add(Calendar.MINUTE, -10)
			if (limite.before(fechaReserva.toCalendar())) {
				estado = ESTADOS_RESERVA.Cancelada
				return true
			}
		}
		false
	}

	def cerrar() {
		if(enCurso) {
			estado = ESTADOS_RESERVA.Cerrada
		}
	}

	def pasarAEnCurso() {
		if(remisAsignado) {
			estado = ESTADOS_RESERVA.EnCurso
		}
	}

	def asignarRemise(){
		estado = ESTADOS_RESERVA.ConRemise
	}

	static mapping = {
		paradas cascade: "all-delete-orphan"
	}

	def calificarRemise(def puntaje) {
		if(esRemiseCalificable) {
			calificacionRemise = new Calificacion(puntaje:puntaje)
			calificacionRemise.save(flush:true)
			remise.addToCalificaciones(calificacionRemise)
			true
		}else {
			false
		}
	}

	def calificarPasajero(def puntaje) {
		if(esPasajeroCalificable) {
			calificacionPasajero = new Calificacion(puntaje:puntaje)
			calificacionPasajero.save(flush:true)
			pasajero.addToCalificaciones(calificacionPasajero)
			true
		} else {
			false
		}
	}

	def getEsRemiseCalificable() {
		(estaCerrada && calificacionRemise == null)
	}

	def getEsPasajeroCalificable() {
		(estaCerrada && calificacionPasajero == null)
	}

	def getEstaCerrada() {
		(estado == ESTADOS_RESERVA.Cerrada)
	}
	
	def solicitarUnirseAReserva(Pasajero pasajeroSesion, Pasajero pas){
		if (this.pendiente){
			def solicitud = new SolicitudQuieroIrJunto(pasajero: pasajeroSesion, solicitado: pas, reservaSolicitada: this)
			pasajeroSesion.addToSolicitudesQuieroIrJunto(solicitud)
		} else {
			flash.message = 'La reserva que intenta unirse ha sido cancelada.'
		}
	}
}

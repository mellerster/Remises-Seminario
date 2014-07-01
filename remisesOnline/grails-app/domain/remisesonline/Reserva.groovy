package remisesonline

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;

class Reserva {
	static final ESTADOS_VALIDOS = ['Pendiente', 'En curso', 'Cerrada', 'Cancelada']
	//Itinerario destinos = new Itinerario()
	Remise remise
	Date fechaReserva
	String estado = ESTADOS_VALIDOS[0]
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

		fechaReserva validator: {
				def now = new Date()
				def calendar = now.toCalendar()
				calendar.add(Calendar.MONTH, 1)
				if	(it < now || it > calendar.time)
					return ['invalid.rango']
		}
		estado inList: ESTADOS_VALIDOS
		calificacionRemise nullable:true
		calificacionPasajero nullable:true
	}

	String toString() {
		"$fechaReserva $estado"
	}
	
	def getPendiente() {
		estado == ESTADOS_VALIDOS[0]
	}
	
	def getEnCurso(){
		estado == ESTADOS_VALIDOS[1]
	}
	
	def esCancelablePorPasajero(def pasajeroId){
		(pendiente && pasajero.id == pasajeroId)
	}
	
	def esCerrablePorAgencia(def agenciaId){
		(estado == ESTADOS_VALIDOS[1] && agencia.id == agenciaId)
	}
	
	def esPasableAEnCursoPorAgencia(def agenciaId){
		(pendiente && agencia.id == agenciaId)
	}
	
	def cancelar() {
		if (pendiente) {
			def limite = new Date().toCalendar()
			limite.add(Calendar.MINUTE, -10)
			if (limite.before(fechaReserva.toCalendar())) {
				estado = ESTADOS_VALIDOS[3]
				return true
			}
		}
		false
	}
	
	def cerrar(){
		if(enCurso){
			estado = ESTADOS_VALIDOS[2]
		}
	}
  
	static mapping = {
		paradas cascade: "all-delete-orphan"
	}
	
	/*def getParadasList() {
		return LazyList.decorate(paradas,	FactoryUtils.instantiateFactory(Parada.class))
		//[].withLazyDefault {new Parada()}
	}*/
	
	def calificarRemise(def puntaje){
		calificacionRemise = new Calificacion(puntaje:puntaje)
		calificacionRemise.save(flush:true)
		remise.addToCalificaciones(calificacionRemise)
	}
	
	def calificarPasajero(def puntaje){
		calificacionPasajero = new Calificacion(puntaje:puntaje)
		calificacionPasajero.save(flush:true)
		pasajero.addToCalificaciones(calificacionPasajero)
	}
	
	def getEsRemiseCalificable(){
		(estaCerrada && calificacionRemise == null)
	}
	
	def getEsPasajeroCalificable(){
		(estaCerrada && calificacionPasajero == null)
	}
	
	def getEstaCerrada(){
		(estado == 'Cerrada')
	}
}

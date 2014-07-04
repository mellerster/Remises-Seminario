package remisesonline

class Pasajero {
	String email
	String nombre
	String telefono
	Date fechaNacimiento
	Set destinosFrecuentes = []
	Set viajes = []
	Set calificaciones = []
	Set amigos = []
	Set solicitudesAmistad = []
	Set solicitudesQuieroIrJunto = []
	
	static hasMany = [solicitudesQuieroIrJunto: SolicitudQuieroIrJunto, solicitudesAmistad: SolicitudAmistad, amigos: Pasajero, reservas: Reserva, destinosFrecuentes: Parada,calificaciones: Calificacion]

	static constraints = {
		nombre nullable: false, blank: false
		email email: true, blank: false, unique: true
		telefono blank: false
		calificaciones minSize:0
	}

	String toString() {
		return "${nombre} - ${email}"
	}
	
	def getCalificacion(){
		if(calificaciones.size() > 0){
			return (calificaciones.inject(0) { acc, val -> acc + val.puntaje } / calificaciones.size())
		}else {
			return 5
		}
	}
	
	def getNoAmigos() {
		def amigosyo = amigos
		amigosyo.add(this)
		println 'Antes:'
		println amigosyo
		amigosyo.addAll(solicitudesAmistad.grep{it.pendiente}.collect { it.solicitado })
		println 'despues:'
		println amigosyo
		Pasajero.list() - amigosyo
	}
}

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
	
	def getCalificacion() {
		if (!calificaciones) { return 5 }
		calificaciones.inject(0) { acc, val -> acc + val.puntaje } / calificaciones.size()
	}
	
	def getNoAmigos() {
		def todosMenosYo = Pasajero.findAllByIdNotEqual(this.id)
		def misAmigos = amigos
		misAmigos.addAll(solicitudesAmistad.grep{it.pendiente}.collect { it.solicitado })
		
		todosMenosYo - misAmigos
	}
}

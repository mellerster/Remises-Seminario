import remisesonline.Comodidad
import remisesonline.Itinerario
import remisesonline.Parada
import remisesonline.Agencia
import remisesonline.Chofer
import remisesonline.Remise
import remisesonline.Pasajero
import remisesonline.Reserva

class BootStrap {

	def init = { servletContext ->
		def comodidad = new Comodidad(descripcion:'Aire Acondicionado')
		comodidad.save()

		comodidad = new Comodidad()
		comodidad.descripcion = "No Fumador"
		comodidad.save()

		comodidad = new Comodidad()
		comodidad.descripcion = 'Baul Grande'
		comodidad.save()

		def agencia = new Agencia(nombre: 'El remis loco', telefono: '4313-3565',\
								email: 'remises@remisloco.com')
		agencia.save(failOnError: true)

		def chofer = new Chofer(dni: '55.666.777', nombre: 'Gonzalo',\
					licencia: '23ftt', telefono: '15356',\
					direccion: 'Rotonda 67', agencia: agencia)

		agencia.addToChoferes(chofer)

		chofer = new Chofer(dni: '99.393.595', nombre: 'Alfredo',\
				licencia: 'vhf890', telefono: '4299-3500',\
				direccion: 'copacabana 980', agencia: agencia)
		def remisDKP123 = new Remise(patente:'DKP123', chofer:chofer,\
							 estado: 'Vacio', agencia: agencia)

		agencia.addToRemises(remisDKP123)

		agencia.addToChoferes(chofer)

		agencia = new Agencia(nombre: 'No sé si llego', telefono: '153-364-0077',\
							email: 'nose@comollegar.com')

		agencia.save(failOnError: true)

		chofer = new Chofer(dni: '33.333.333', nombre: 'Hugo',\
					licencia: '23f', telefono: '153',\
					direccion: 'grt 67', agencia: agencia)

		agencia.addToChoferes(chofer)

		chofer = new Chofer(dni: '55.333.555', nombre: 'Diego',\
									licencia: 'aaa514', telefono: '4313-3565',\
									direccion: 'xx 152', agencia: agencia)

		agencia.addToChoferes(chofer)

		def remisAAA000 = new Remise(patente:'AAA000', chofer:chofer,\
															estado: 'Vacio', agencia: agencia)
		agencia.addToRemises(remisAAA000)

		/*def itinerario = new Itinerario(descripcion: 'Un paseo por CABA')

		def parada = new Parada(calle: 'H. Yrigoyen', numero: 370, descripcion: 'en plaza de mayo')
		parada.localidad = 'CABA'

		itinerario.addToParadas(parada)

		parada = new Parada(calle: 'Corrientes', localidad: 'CABA',\
			descripcion: 'no se donde es')
		parada.numero = 1400

		itinerario.addToParadas(parada)*/

		def pasajero = new Pasajero(nombre: 'Hugo, the client', email: 'h@gmail.com',\
								telefono: '54', fechaNacimiento: new Date(80,5,5))
		pasajero.save(failOnError: true)
		
		def pasajero2 = new Pasajero(nombre: 'Seba el cliente', email: 's@gmail.com',\
								telefono: '54', fechaNacimiento: new Date(80,5,5))
		pasajero2.save(failOnError: true)
		
		pasajero.addToAmigos(pasajero2)
		pasajero2.addToAmigos(pasajero)
		def pasajero3 = new Pasajero(nombre: 'Jose el cliente', email: 'j@gmail.com',\
								telefono: '54', fechaNacimiento: new Date(80,5,5))
		pasajero3.save(failOnError: true)
		
		def pasajero4 = new Pasajero(nombre: 'Pepe el cliente', email: 'p@gmail.com',\
								telefono: '54', fechaNacimiento: new Date(80,5,5))
		pasajero4.save(failOnError: true)


		def pasajero5 = new Pasajero(nombre: 'Diego el cliente', email: 'm@gmail.com',\
							telefono: '54', fechaNacimiento: new Date(90,5,5))
		pasajero5.save(failOnError: true)
		pasajero.addToAmigos(pasajero5)
		pasajero5.addToAmigos(pasajero)
		
		

		
		def reserva = new Reserva(remise: remisAAA000, agencia: agencia,\
								pasajero: pasajero, fechaReserva: new Date() + 1, compartible: true)
		def parada1 = new Parada(calle: 'H. Yrigoyen', numero: 370, descripcion: 'en plaza de mayo')
		parada1.localidad = 'CABA'
		reserva.paradas[0] = parada1
		
		def parada2 = new Parada(calle: 'Corrientes', localidad: 'CABA',\
			descripcion: 'no se donde es')
		parada2.numero = 1400
		reserva.paradas[1] = parada2

		agencia.addToReservas(reserva)

		agencia = Agencia.findByNombre('El remis loco')

		reserva = new Reserva( fechaReserva: new Date() + 1)
		reserva.pasajero = Pasajero.findByNombre('Hugo, the client')
		reserva.agencia = Agencia.findByEmail('remises@remisloco.com')
		agencia.addToReservas(reserva)
		reserva.estado = Reserva.ESTADOS_VALIDOS[1]
		def parada3 = new Parada(calle: 'Ucrania', localidad: 'Posadas',\
		descripcion: 'esta es lejos')
		parada3.numero = 756
		reserva.paradas[0] = parada1
		reserva.paradas[1] = parada3
		
		reserva = new Reserva(fechaReserva: new Date() + 6)
		reserva.pasajero = Pasajero.findByNombre('Hugo, the client')
		reserva.agencia = Agencia.findByEmail('remises@remisloco.com')
		agencia.addToReservas(reserva)
		reserva.estado = Reserva.ESTADOS_VALIDOS[1]
		parada3 = new Parada(calle: 'Ucrania', localidad: 'Posadas',\
		descripcion: 'esta es lejos')
		parada3.numero = 777
		reserva.paradas[0] = parada1
		reserva.paradas[1] = parada3

	}

	def destroy = {
	}
}

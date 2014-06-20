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

		def itinerario = new Itinerario(descripcion: 'Un paseo por CABA')

		def parada = new Parada(calle: 'H. Yrigoyen', numero: 370, descripcion: 'en plaza de mayo')
		parada.localidad = 'CABA'

		itinerario.addToParadas(parada)

		parada = new Parada(calle: 'Corrientes', localidad: 'CABA',\
			descripcion: 'no se donde es')
		parada.numero = 1400

		itinerario.addToParadas(parada)

		def pasajero = new Pasajero(nombre: 'Hugo el cliente', email: 'h@gmail.com',\
								telefono: '54', fechaNacimiento: new Date(80,5,5))
		pasajero.save(failOnError: true)

		itinerario.addToPasajeros(pasajero)
		itinerario.save(failOnError: true)
		pasajero.addToViajes(itinerario)


		pasajero = new Pasajero(nombre: 'Diego el cliente', email: 'm@gmail.com',\
							telefono: '54', fechaNacimiento: new Date(90,5,5))
		pasajero.save(failOnError: true)

		def reserva = new Reserva(remise: remisAAA000, agencia: agencia, destinos: itinerario,\
								pasajero: pasajero, fechaReserva: new Date() + 1)

		agencia.addToReservas(reserva)

		agencia = Agencia.findByNombre('El remis loco')

		reserva = new Reserva(remise: remisDKP123, destinos: itinerario,\
							fechaReserva: new Date() + 1)
		reserva.pasajero = Pasajero.findByNombre('Hugo el cliente')
		agencia = Agencia.findByEmail('remises@remisloco.com')
		reserva.agencia = agencia
		agencia.addToReservas(reserva)
		reserva.estado = Reserva.ESTADOS_VALIDOS[1]

	}

	def destroy = {
	}
}

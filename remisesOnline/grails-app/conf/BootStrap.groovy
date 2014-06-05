import remisesonline.Comodidad
import remisesonline.Itinerario
import remisesonline.Lugar
import remisesonline.Agencia
import remisesonline.Chofer
import remisesonline.Asociacion
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
      comodidad.descripcion = "Baul Grande"
      comodidad.save()
      
      def itinerario = new Itinerario(descripcion: 'Un paseo por CABA')
      
      itinerario.save(failOnError: true)
      
      def lugar = new Lugar(direccion: 'H. Yrigoyen 370, CABA', descripcion: 'en plaza de mayo')
      lugar.save()
      
      def asociacion = new Asociacion(itinerario: itinerario, lugar: lugar, orden: 1)
      
      itinerario.addToLugares(asociacion)
      
      lugar = new Lugar(direccion: 'Corrientes 1400, CABA',\
              descripcion: 'no se donde es').save(failOnError: true)
              
      asociacion = new Asociacion(itinerario: itinerario, lugar: lugar, orden: 2)
      itinerario.addToLugares(asociacion)
      
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
      
      def remis = new Remise(patente:'DKP123', chofer:chofer,\
                             estado: 'Vacio', agencia: agencia)
      agencia.addToRemises(remis)
      
      def pasajero = new Pasajero(nombre: 'Hugo el cliente', email: 'h@gmail.com',\
                              telefono: '54', fechaNacimiento: new Date())
      pasajero.save(failOnError: true)
      
      pasajero = new Pasajero(nombre: 'Diego el cliente', email: 'm@gmail.com',\
                              telefono: '54', fechaNacimiento: new Date())
      pasajero.save(failOnError: true)
      
      def reserva = new Reserva(remise: remis, agencia: agencia, destinos: itinerario,\
                                pasajero: pasajero, fechaReserva: new Date() + 1)
      // reserva.save(failOnError: true)
      
      agencia.addToReservas(reserva)
      
      reserva = new Reserva(remise: remis, destinos: itinerario,\
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

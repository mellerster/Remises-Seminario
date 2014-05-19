import remisesonline.Comodidad
import remisesonline.Itinerario
import remisesonline.Lugar
import remisesonline.Agencia

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
		
		def lugar = new Lugar(direccion: 'H. Yrigoyen 370, CABA', descripcion: 'en plaza de mayo')
		lugar.save()
		
		itinerario.addToLugares(lugar)
		
		lugar = new Lugar(direccion: 'Corrientes 1400, CABA', descripcion: 'no se donde es').save(failOnError: true)
		
		itinerario.addToLugares(lugar)
		
		itinerario.save(failOnError: true)
		
		def agencia = new Agencia(nombre: 'El remis loco', telefono: '4313-3565', codigo: 'que demonios es esto?')
		agencia.save(failOnError: true)
    }
    def destroy = {
    }
}

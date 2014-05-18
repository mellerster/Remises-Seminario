import remisesonline.Comodidad
import remisesonline.Itinerario
import remisesonline.Lugar

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
		
		def lugar = new Lugar(direccion: 'H. Yrigoyen 370, CABA')
		lugar.save()
		
		itinerario.addToLugares(lugar)
		
		lugar = new Lugar(direccion: 'Corrientes 1400, CABA').save(failOnError: true)
		
		itinerario.addToLugares(lugar)
		
		itinerario.save(failOnError: true)
    }
    def destroy = {
    }
}

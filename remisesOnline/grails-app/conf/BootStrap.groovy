import remisesonline.Comodidad
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
		
		def lugar = new Lugar(direccion : 'H. Yrigoyen 370, CABA')
		lugar.save()
    }
    def destroy = {
    }
}

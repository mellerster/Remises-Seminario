import remisesonline.Comodidad


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
    }
    def destroy = {
    }
}

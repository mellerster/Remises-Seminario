package remisesonline

class ItinerarioController {

    def scaffold = true
	
	def index = {
		def itinerarios = Itinerario.list(sort: "descripcion", order: "asc")
		
		return [itinerarios: itinerarios]
	}
}

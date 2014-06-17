package remisesonline

class ItinerarioController {

	def scaffold = true
	
	//def index = {
		//redirect(action: list)
	//}
	
	def list = {
		def itinerarios = Itinerario.list(sort: "descripcion", order: "asc")
		return [itinerarios: itinerarios]
	}
}

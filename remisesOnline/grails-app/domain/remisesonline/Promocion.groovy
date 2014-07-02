package remisesonline

class Promocion {
	Date fechaDesde
	Date fechaHasta
	String descripcion
	
	static belongsTo = [agencia: Agencia]
	
    static constraints = {
		descripcion(nullable:false, maxSize:140)
		fechaDesde validator: {
				if ( it < new Date()) { 
					return 'fechaDesde menor que dia actual'
				}
		}
		//Aca quise poner que la fechaHasta sea mayor 
		//que la fechaDesde y me tira errores, debe ser mas facil de lo que pienso y no lo encuentro en ningun lado.
    }
	
	String toString() {
		return "Desde: $fechaDesde - Hasta: $fechaHasta - Descripcion: $descripcion"
	}
}

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


		fechaDesde validator: { fecha, obj ->
			if (fecha > obj.fechaHasta) {
				return 'fechaDesde menor que dia actual'
			}
		}
	}

	String toString() {
		return "Desde: $fechaDesde - Hasta: $fechaHasta - Descripcion: $descripcion"
	}
}

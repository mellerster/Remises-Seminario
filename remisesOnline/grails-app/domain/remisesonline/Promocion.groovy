package remisesonline

class Promocion {
	Date fechaDesde
	Date fechaHasta
	String descripcion
	
	static belongsTo = [agencia: Agencia]
	
    static constraints = {
	
		//verificar que desde es mas chico que hasta y que ambas son mayores a la actual
		
    }
}

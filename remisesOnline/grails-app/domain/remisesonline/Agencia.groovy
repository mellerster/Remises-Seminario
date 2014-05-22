package remisesonline

class Agencia {
	String nombre
	String telefono
	int codigo

	static hasMany = [remises: Remise, choferes: Chofer]

	static constraints = {
		nombre nullable: false, unique: true
		telefono blank: false
		remises minSize: 0
	}

	String toString(){
		return nombre
	}

	def beforeInsert(){
		def ultimaAgencia = Agencia.last()
		if(ultimaAgencia){
			codigo = ultimaAgencia.codigo + 1;
		}else {
			codigo = 1;
		}
	}
}

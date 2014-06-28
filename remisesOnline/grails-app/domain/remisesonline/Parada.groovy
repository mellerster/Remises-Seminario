package remisesonline

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode()
class Parada {
	String localidad
	String calle
	Integer numero
	String descripcion
	
	//boolean deleted
	//static transients = [ 'deleted' ]
	
	static belongsTo = [Itinerario]

	static constraints = {
		calle blank: false, nullable: false
		numero blank: false, nullable: false
		descripcion blank: true, nullable: true
		localidad blank: true, nullable: true
	}
	
	String toString() {
		return "${calle} ${numero} - ${localidad}"
	}
	
	/*@Override
	boolean equals(o) {
		if(this.is(o)) return true
		if(o == null) return false
		// hibernate creates dynamic subclasses, so 
		// checking o.class == class would fail most of the time
		if(!o.getClass().isAssignableFrom(getClass()) && 
				!getClass().isAssignableFrom(o.getClass())) return false
		
		return this.toString() == o.toString()
	}

	@Override
	int hashCode() {
		int prime = 31
		int result = 1
		result = prime + calle?.hashCode()
		result = prime*result + numero?.hashCode()
		result = prime*result + localidad?.hashCode()
		result = prime*result + descripcion?.hashCode()
		result
	}*/
}

package remisesonline

import org.apache.commons.collections.list.LazyList;
import org.apache.commons.collections.FactoryUtils;

class Itinerario {

	String descripcion
	List paradas = new ArrayList()
	Set pasajeros = []

	static hasMany = [paradas: Parada, reserva: Reserva, pasajeros: Pasajero]
	static belongsTo = [Pasajero]

	static constraints = {
		descripcion blank: false, nullable: false
	}

	String toString() {
		return descripcion
	}
	
	static mapping = {
		paradas cascade:"all-delete-orphan"
	}
	
	def getParadasList() {
		return LazyList.decorate(
							paradas,
							FactoryUtils.instantiateFactory(Parada.class))
		}
}

package remisesonline



import spock.lang.*

/**
 *
 */
class LugarIntegrationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "save is successfull"() {
		def lugar
		def result
			
		
		given:
		lugar = new Lugar(direccion: 'prueba5',\
						descripcion: 'prueba5')
			result = lugar.save()
		expect:
			result
			
			
    }
}

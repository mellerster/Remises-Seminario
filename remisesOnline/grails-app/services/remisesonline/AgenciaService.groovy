package remisesonline

import grails.transaction.Transactional

@Transactional
class AgenciaService {
	def getRemisesParaCombo(def agenciaId){
		def remises = Agencia.get(agenciaId).remises
		def lista = []
		lista.add("""<option value="null"></option>""")
		lista.addAll(remises.collect{remise-> """<option value="${remise.id}">${remise.patente}</option>""" })
		lista
	}
}

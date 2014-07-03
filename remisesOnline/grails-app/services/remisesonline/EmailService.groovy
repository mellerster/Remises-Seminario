package remisesonline

import grails.transaction.Transactional

@Transactional
class EmailService {

    def enviarMail(def para, def asunto, def cuerpo) {
		println "Enviando Mail..."
		println "Para: ${para}"
		println "Asunto: ${asunto}"
		println "${cuerpo}"

    }
}

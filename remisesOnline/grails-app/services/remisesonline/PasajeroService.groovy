package remisesonline

import grails.plugin.mail.MailService
import grails.transaction.Transactional

@Transactional
class PasajeroService {

	def enviarSolicitudAmistad(Pasajero pasajero, Pasajero Amigo) {
		MailService mailService = new MailService()
		//println pasajero
		//println Amigo

		mailService.sendMail {
			to "mellerster@gmail.com"
			subject "Hello Fred"
			body 'How are you?'
		}
		println "mail enviado"
	}
}

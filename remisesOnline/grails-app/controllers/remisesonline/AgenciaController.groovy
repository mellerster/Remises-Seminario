package remisesonline

import static org.springframework.http.HttpStatus.*

import java.awt.TexturePaintContext.Int;

import grails.transaction.Transactional

class AgenciaCommand{
	String nombre
	String telefono
	String email
	static constraints = {
		nombre nullable: false, unique: true
		telefono blank: false
		email email: true
	}
}

@Transactional(readOnly = true)
class AgenciaController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE",getRemises: "POST"]
	def agenciaService 
	
	def logout() {
		session.agencia = null
		redirect(action: 'login')
	}

	def login() {
		[agencias: Agencia.list()]
	}

	def entrar() {
		session.agencia = Agencia.findByNombre(params.agencia)
		redirect action: 'index', controller: 'remise'
	}

	def searchReservas() {
		[estadosReservas: Reserva.ESTADOS_VALIDOS]
	}

	def listReservas() {
		def agenciaLogueada = Agencia.get(session.agencia.id)
		def reservasEstado = agenciaLogueada?.reservas?.findAll {it.estado == params.estadoSeleccionado}
		return [reservas: reservasEstado]
	}

	def show(Agencia agenciaInstance) {
		respond agenciaInstance
	}

	def create() {
		respond new Agencia(params)
	}

	@Transactional
	def save(AgenciaCommand agenciaInstance) {
		if (agenciaInstance == null) {
			notFound()
			return
		}

		if (agenciaInstance.hasErrors()) {
			respond agenciaInstance.errors, view: 'create'
			return
		}
		Agencia agencia = new Agencia()
		agencia.properties = agenciaInstance
		
		agencia.save flush:true,  failOnError:true
		session.agencia = agencia
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [
					message(code: 'agencia.label', default: 'Agencia'),
					agencia
				])
				redirect agencia
			}
			'*' { respond agencia, [status: CREATED] }
		}
	}

	def edit(Agencia agenciaInstance) {
		respond agenciaInstance
	}

	@Transactional
	def update(AgenciaCommand agenciaInstance) {
		if (agenciaInstance == null) {
			notFound()
			return
		}

		if (agenciaInstance.hasErrors()) {
			respond agenciaInstance.errors, view: 'edit'
			return
		}
		Agencia agencia = Agencia.get(params.id)
		agencia.properties = agenciaInstance
		
		agencia.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [
					message(code: 'Agencia.label', default: 'Agencia'),
					agencia
				])
				redirect agencia
			}
			'*'{ respond agencia, [status: OK] }
		}
	}

	@Transactional
	def delete(Agencia agenciaInstance) {

		if (agenciaInstance == null) {
			notFound()
			return
		}

		agenciaInstance.delete flush:true
		session.agencia = null
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [
					message(code: 'Agencia.label', default: 'Agencia'),
					agenciaInstance
				])
				redirect action:"login", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'agenciaInstance.label', default: 'Agencia'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
	
	def getRemises()  {
		render agenciaService.getRemisesParaCombo(params.agenciaid)
  } 
}

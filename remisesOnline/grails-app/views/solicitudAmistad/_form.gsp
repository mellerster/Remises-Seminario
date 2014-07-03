<%@ page import="remisesonline.SolicitudAmistad" %>


<div class="fieldcontain ${hasErrors(bean: solicitudAmistadInstance, field: 'solicitado', 'error')} required">
	<label for="solicitado">
		<g:message code="solicitudAmistad.solicitado.label" default="Nuevo amigo" />
		<span class="required-indicator">*</span>
	</label>
<<<<<<< HEAD
	<g:select id="solicitado" name="solicitado.id" from="${remisesonline.Pasajero.findAllByIdNotEqual(session.pasajero?.id)}" optionKey="id" required="" value="${solicitudAmistadInstance?.solicitado?.id}" class="many-to-one"/>
=======
	<g:select id="solicitado" name="solicitado.id" from="${resultado}" optionKey="id" required="" value="${solicitudAmistadInstance?.solicitado?.id}" class="many-to-one"/>
>>>>>>> 48cec5393d3a65de33ceec1cd18e385b5a9739a8

</div>


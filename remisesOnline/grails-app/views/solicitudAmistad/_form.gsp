<%@ page import="remisesonline.SolicitudAmistad" %>


<div class="fieldcontain ${hasErrors(bean: solicitudAmistadInstance, field: 'solicitado', 'error')} required">
	<label for="solicitado">
		<g:message code="solicitudAmistad.solicitado.label" default="Nuevo amigo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="solicitado" name="solicitado.id" from="${solicitudAmistadInstance?.pasajero?.noAmigos}" optionKey="id" required="" value="${solicitudAmistadInstance?.solicitado?.id}" class="many-to-one"/>

</div>

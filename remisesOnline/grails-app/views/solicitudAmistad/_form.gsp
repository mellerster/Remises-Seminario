<%@ page import="remisesonline.SolicitudAmistad" %>



<div class="fieldcontain ${hasErrors(bean: solicitudAmistadInstance, field: 'estado', 'error')} ">
	<label for="estado">
		<g:message code="solicitudAmistad.estado.label" default="Estado" />
		
	</label>
	<g:select name="estado" from="${solicitudAmistadInstance.constraints.estado.inList}" value="${solicitudAmistadInstance?.estado}" valueMessagePrefix="solicitudAmistad.estado" noSelection="['': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: solicitudAmistadInstance, field: 'fechaCreada', 'error')} required">
	<label for="fechaCreada">
		<g:message code="solicitudAmistad.fechaCreada.label" default="Fecha Creada" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaCreada" precision="day"  value="${solicitudAmistadInstance?.fechaCreada}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: solicitudAmistadInstance, field: 'pasajero', 'error')} required">
	<label for="pasajero">
		<g:message code="solicitudAmistad.pasajero.label" default="Pasajero" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="pasajero" name="pasajero.id" from="${remisesonline.Pasajero.list()}" optionKey="id" required="" value="${solicitudAmistadInstance?.pasajero?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: solicitudAmistadInstance, field: 'solicitado', 'error')} required">
	<label for="solicitado">
		<g:message code="solicitudAmistad.solicitado.label" default="Solicitado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="solicitado" name="solicitado.id" from="${remisesonline.Pasajero.list()}" optionKey="id" required="" value="${solicitudAmistadInstance?.solicitado?.id}" class="many-to-one"/>

</div>


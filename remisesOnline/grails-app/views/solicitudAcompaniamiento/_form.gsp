<%@ page import="remisesonline.SolicitudAcompaniamiento" %>



<div class="fieldcontain ${hasErrors(bean: solicitudAcompaniamientoInstance, field: 'estado', 'error')} ">
	<label for="estado">
		<g:message code="solicitudAcompaniamiento.estado.label" default="Estado" />
		
	</label>
	<g:select name="estado" from="${solicitudAcompaniamientoInstance.constraints.estado.inList}" value="${solicitudAcompaniamientoInstance?.estado}" valueMessagePrefix="solicitudAcompaniamiento.estado" noSelection="['': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: solicitudAcompaniamientoInstance, field: 'fechaCreada', 'error')} required">
	<label for="fechaCreada">
		<g:message code="solicitudAcompaniamiento.fechaCreada.label" default="Fecha Creada" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaCreada" precision="day"  value="${solicitudAcompaniamientoInstance?.fechaCreada}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: solicitudAcompaniamientoInstance, field: 'pasajero', 'error')} required">
	<label for="pasajero">
		<g:message code="solicitudAcompaniamiento.pasajero.label" default="Pasajero" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="pasajero" name="pasajero.id" from="${remisesonline.Pasajero.list()}" optionKey="id" required="" value="${solicitudAcompaniamientoInstance?.pasajero?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: solicitudAcompaniamientoInstance, field: 'reservaSolicitada', 'error')} required">
	<label for="reservaSolicitada">
		<g:message code="solicitudAcompaniamiento.reservaSolicitada.label" default="Reserva Solicitada" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reservaSolicitada" name="reservaSolicitada.id" from="${remisesonline.Reserva.list()}" optionKey="id" required="" value="${solicitudAcompaniamientoInstance?.reservaSolicitada?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: solicitudAcompaniamientoInstance, field: 'solicitado', 'error')} required">
	<label for="solicitado">
		<g:message code="solicitudAcompaniamiento.solicitado.label" default="Solicitado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="solicitado" name="solicitado.id" from="${remisesonline.Pasajero.list()}" optionKey="id" required="" value="${solicitudAcompaniamientoInstance?.solicitado?.id}" class="many-to-one"/>

</div>


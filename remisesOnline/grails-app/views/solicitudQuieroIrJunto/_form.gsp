<%@ page import="remisesonline.SolicitudQuieroIrJunto" %>



<div class="fieldcontain ${hasErrors(bean: solicitudQuieroIrJuntoInstance, field: 'estado', 'error')} ">
	<label for="estado">
		<g:message code="solicitudQuieroIrJunto.estado.label" default="Estado" />
		
	</label>
	<g:select name="estado" from="${solicitudQuieroIrJuntoInstance.constraints.estado.inList}" value="${solicitudQuieroIrJuntoInstance?.estado}" valueMessagePrefix="solicitudQuieroIrJunto.estado" noSelection="['': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: solicitudQuieroIrJuntoInstance, field: 'fechaCreada', 'error')} required">
	<label for="fechaCreada">
		<g:message code="solicitudQuieroIrJunto.fechaCreada.label" default="Fecha Creada" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaCreada" precision="day"  value="${solicitudQuieroIrJuntoInstance?.fechaCreada}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: solicitudQuieroIrJuntoInstance, field: 'pasajero', 'error')} required">
	<label for="pasajero">
		<g:message code="solicitudQuieroIrJunto.pasajero.label" default="Pasajero" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="pasajero" name="pasajero.id" from="${remisesonline.Pasajero.list()}" optionKey="id" required="" value="${solicitudQuieroIrJuntoInstance?.pasajero?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: solicitudQuieroIrJuntoInstance, field: 'reservaSolicitada', 'error')} required">
	<label for="reservaSolicitada">
		<g:message code="solicitudQuieroIrJunto.reservaSolicitada.label" default="Reserva Solicitada" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reservaSolicitada" name="reservaSolicitada.id" from="${remisesonline.Reserva.list()}" optionKey="id" required="" value="${solicitudQuieroIrJuntoInstance?.reservaSolicitada?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: solicitudQuieroIrJuntoInstance, field: 'solicitado', 'error')} required">
	<label for="solicitado">
		<g:message code="solicitudQuieroIrJunto.solicitado.label" default="Solicitado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="solicitado" name="solicitado.id" from="${remisesonline.Pasajero.list()}" optionKey="id" required="" value="${solicitudQuieroIrJuntoInstance?.solicitado?.id}" class="many-to-one"/>

</div>


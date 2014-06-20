<%@ page import="remisesonline.Reserva" %>



<div class="fieldcontain ${hasErrors(bean: reservaInstance, field: 'remise', 'error')} ">
	<label for="remise">
		<g:message code="reserva.remise.label" default="Remise" />
		
	</label>
	<g:select id="remise" name="remise.id" from="${remisesonline.Remise.list()}" optionKey="id" value="${reservaInstance?.remise?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reservaInstance, field: 'fechaReserva', 'error')} required">
	<label for="fechaReserva">
		<g:message code="reserva.fechaReserva.label" default="Fecha Reserva" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaReserva" precision="day"  value="${reservaInstance?.fechaReserva}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: reservaInstance, field: 'estado', 'error')} ">
	<label for="estado">
		<g:message code="reserva.estado.label" default="Estado" />
		
	</label>
	<g:select name="estado" from="${reservaInstance.constraints.estado.inList}" value="${reservaInstance?.estado}" valueMessagePrefix="reserva.estado" noSelection="['': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reservaInstance, field: 'agencia', 'error')} required">
	<label for="agencia">
		<g:message code="reserva.agencia.label" default="Agencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="agencia" name="agencia.id" from="${remisesonline.Agencia.list()}" optionKey="id" required="" value="${reservaInstance?.agencia?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reservaInstance, field: 'creado', 'error')} required">
	<label for="creado">
		<g:message code="reserva.creado.label" default="Creado" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="creado" precision="day"  value="${reservaInstance?.creado}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: reservaInstance, field: 'destinos', 'error')} required">
	<label for="destinos">
		<g:message code="reserva.destinos.label" default="Destinos" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="destinos" name="destinos.id" from="${remisesonline.Itinerario.list()}" optionKey="id" required="" value="${reservaInstance?.destinos?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reservaInstance, field: 'pasajero', 'error')} required">
	<label for="pasajero">
		<g:message code="reserva.pasajero.label" default="Pasajero" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="pasajero" name="pasajero.id" from="${remisesonline.Pasajero.list()}" optionKey="id" required="" value="${reservaInstance?.pasajero?.id}" class="many-to-one"/>

</div>


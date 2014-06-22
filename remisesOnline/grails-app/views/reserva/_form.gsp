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

  <calendar:datePicker name="fechaReserva" value="${reservaInstance?.fechaReserva}" dateFormat=" %d/%m/%Y %H:%M" showTime="true"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reservaInstance, field: 'agencia', 'error')} required">
	<label for="agencia">
		<g:message code="reserva.agencia.label" default="Agencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="agencia" name="agencia.id" from="${remisesonline.Agencia.list()}" optionKey="id" required="" value="${reservaInstance?.agencia?.id}" class="many-to-one"/>

</div>


    
<div class="fieldcontain ${hasErrors(bean: reservaInstance, field: 'destinos', 'error')} required">
	<label for="destinos">
		<g:message code="reserva.destinos.label" default="Destinos" />
		<span class="required-indicator">*</span>
	</label>
	<g:render template="paradas" model="['reservaInstance':reservaInstance]" />

</div>



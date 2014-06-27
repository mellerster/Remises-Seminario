<%@ page import="remisesonline.Reserva" %>


<div class="fieldcontain ${hasErrors(bean: reservaInstance, field: 'fechaReserva', 'error')} required">
	<label for="fechaReserva">
		<g:message code="reserva.fechaReserva.label" default="Fecha Reserva" />
		<span class="required-indicator">*</span>
	</label>

	<g:textField  class="datetimepicker" size="14" type="datetime" name="fechaReserva" value="${formatDate(format:"dd/MM/yyyy HH:mm", date:reservaInstance?.fechaReserva)}" />

</div>

<div class="fieldcontain ${hasErrors(bean: reservaInstance, field: 'agencia', 'error')} required">
	<label for="agencia">
		<g:message code="reserva.agencia.label" default="Agencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="agencia" name="agencia.id" from="${remisesonline.Agencia.list()}" optionKey="id"
		onchange=" ${remoteFunction( controller:'agencia', action:'getRemises', update:'remise',  params: '\'agenciaid=\' + this.value')} " 
		required="" value="${reservaInstance?.agencia?.id}" noSelection="['null': 'Seleccione una agencia']" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reservaInstance, field: 'remise', 'error')} ">
	<label for="remise">
		<g:message code="reserva.remise.label" default="Remise" />
	</label>
	<g:select id="remise" name="remise.id" from="" optionKey="id" value="${reservaInstance?.remise?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reservaInstance, field: 'compartible', 'error')} ">
	<label for="compartible">
		Compartible
	</label>
	<g:checkBox name="compartible" value="${reservaInstance?.compartible}" />

</div>


    
<div class="fieldcontain ${hasErrors(bean: reservaInstance, field: 'destinos', 'error')} required">
	<h3>Paradas</h3>
	<table width="50%" >
	<tr>
		<th width="21%">Calle</th>
		<th width="22%">Numero</th>
		<th width="21.5%">Localidad</th>
		<th width="40%">Descripcion</th>
	</tr>
	</table>
	<g:render template="paradas" model="['reservaInstance':reservaInstance]" />

</div>



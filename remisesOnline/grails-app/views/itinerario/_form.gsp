<%@ page import="remisesonline.Itinerario" %>



<div class="fieldcontain ${hasErrors(bean: itinerarioInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="itinerario.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${itinerarioInstance?.descripcion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: itinerarioInstance, field: 'paradas', 'error')} ">
	<label for="paradas">
		<g:message code="itinerario.paradas.label" default="Paradas" />
		
	</label>
	<g:select name="paradas" from="${remisesonline.Parada.list()}" multiple="multiple" optionKey="id" size="5" value="${itinerarioInstance?.paradas*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: itinerarioInstance, field: 'pasajeros', 'error')} ">
	<label for="pasajeros">
		<g:message code="itinerario.pasajeros.label" default="Pasajeros" />
		
	</label>
	

</div>

<div class="fieldcontain ${hasErrors(bean: itinerarioInstance, field: 'reserva', 'error')} ">
	<label for="reserva">
		<g:message code="itinerario.reserva.label" default="Reserva" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${itinerarioInstance?.reserva?}" var="r">
    <li><g:link controller="reserva" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="reserva" action="create" params="['itinerario.id': itinerarioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'reserva.label', default: 'Reserva')])}</g:link>
</li>
</ul>


</div>


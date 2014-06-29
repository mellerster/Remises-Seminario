<%@ page import="remisesonline.Promocion" %>



<div class="fieldcontain ${hasErrors(bean: promocionInstance, field: 'agencia', 'error')} required">
	<label for="agencia">
		<g:message code="promocion.agencia.label" default="Agencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="agencia" name="agencia.id" from="${remisesonline.Agencia.list()}" optionKey="id" required="" value="${promocionInstance?.agencia?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: promocionInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="promocion.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${promocionInstance?.descripcion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: promocionInstance, field: 'fechaDesde', 'error')} required">
	<label for="fechaDesde">
		<g:message code="promocion.fechaDesde.label" default="Fecha Desde" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaDesde" precision="day"  value="${promocionInstance?.fechaDesde}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: promocionInstance, field: 'fechaHasta', 'error')} required">
	<label for="fechaHasta">
		<g:message code="promocion.fechaHasta.label" default="Fecha Hasta" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaHasta" precision="day"  value="${promocionInstance?.fechaHasta}"  />

</div>


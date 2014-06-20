<%@ page import="remisesonline.Parada" %>



<div class="fieldcontain ${hasErrors(bean: paradaInstance, field: 'calle', 'error')} required">
	<label for="calle">
		<g:message code="parada.calle.label" default="Calle" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="calle" required="" value="${paradaInstance?.calle}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: paradaInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="parada.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numero" type="number" value="${paradaInstance.numero}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: paradaInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="parada.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${paradaInstance?.descripcion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: paradaInstance, field: 'localidad', 'error')} ">
	<label for="localidad">
		<g:message code="parada.localidad.label" default="Localidad" />
		
	</label>
	<g:textField name="localidad" value="${paradaInstance?.localidad}"/>

</div>


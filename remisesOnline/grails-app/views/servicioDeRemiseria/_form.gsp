<%@ page import="remisesonline.ServicioDeRemiseria" %>



<div class="fieldcontain ${hasErrors(bean: servicioDeRemiseriaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="servicioDeRemiseria.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${servicioDeRemiseriaInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: servicioDeRemiseriaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="servicioDeRemiseria.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descripcion" required="" value="${servicioDeRemiseriaInstance?.descripcion}"/>

</div>
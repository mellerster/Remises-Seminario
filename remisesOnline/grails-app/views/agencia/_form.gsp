<%@ page import="remisesonline.Agencia" %>



<div class="fieldcontain ${hasErrors(bean: agenciaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="agencia.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${agenciaInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: agenciaInstance, field: 'telefono', 'error')} required">
	<label for="telefono">
		<g:message code="agencia.telefono.label" default="Telefono" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telefono" required="" value="${agenciaInstance?.telefono}"/>

</div>


<div class="fieldcontain ${hasErrors(bean: agenciaInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="agencia.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${agenciaInstance?.email}"/>

</div>
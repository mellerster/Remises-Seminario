<%@ page import="remisesonline.Pasajero" %>	

<div class="fieldcontain ${hasErrors(bean: pasajeroInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="pasajero.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${pasajeroInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: pasajeroInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="pasajero.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${pasajeroInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: pasajeroInstance, field: 'telefono', 'error')} required">
	<label for="telefono">
		<g:message code="pasajero.telefono.label" default="Telefono" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telefono" required="" value="${pasajeroInstance?.telefono}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: pasajeroInstance, field: 'fechaNacimiento', 'error')} required">
	<label for="fechaNacimiento">
		<g:message code="pasajero.fechaNacimiento.label" default="Fecha Nacimiento" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaNacimiento" precision="day"  value="${pasajeroInstance?.fechaNacimiento}"  />

</div>
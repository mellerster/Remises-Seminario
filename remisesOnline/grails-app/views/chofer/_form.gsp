<%@ page import="remisesonline.Chofer" %>



<div class="fieldcontain ${hasErrors(bean: choferInstance, field: 'dni', 'error')} required">
	<label for="dni">
		<g:message code="chofer.dni.label" default="Dni" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="dni" required="" value="${choferInstance?.dni}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: choferInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="chofer.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${choferInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: choferInstance, field: 'telefono', 'error')} ">
	<label for="telefono">
		<g:message code="chofer.telefono.label" default="Telefono" />
		
	</label>
	<g:textField name="telefono" value="${choferInstance?.telefono}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: choferInstance, field: 'direccion', 'error')} ">
	<label for="direccion">
		<g:message code="chofer.direccion.label" default="Direccion" />
		
	</label>
	<g:textField name="direccion" value="${choferInstance?.direccion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: choferInstance, field: 'licencia', 'error')} ">
	<label for="licencia">
		<g:message code="chofer.licencia.label" default="Licencia" />
		
	</label>
	<g:textField name="licencia" value="${choferInstance?.licencia}"/>

</div>

<g:textField name="agencia.id" value="${choferInstance?.agencia.id }" style="display:none;" />


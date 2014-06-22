<%@ page import="remisesonline.Remise" %>



<div class="fieldcontain ${hasErrors(bean: remiseInstance, field: 'patente', 'error')} required">
	<label for="patente">
		<g:message code="remise.patente.label" default="Patente" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="patente" required="" value="${remiseInstance?.patente}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: remiseInstance, field: 'chofer', 'error')} required">
	<label for="chofer">
		<g:message code="remise.chofer.label" default="Chofer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="chofer" name="chofer.id" from="${choferes}" optionKey="id" required="" value="${remiseInstance?.chofer?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: remiseInstance, field: 'foto', 'error')} ">
	<label for="foto">
		<g:message code="remise.foto.label" default="Foto" />
		
	</label>
	<input type="file" id="foto" name="foto" />

</div>


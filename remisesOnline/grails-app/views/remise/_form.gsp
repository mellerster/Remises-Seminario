<%@ page import="remisesonline.Remise" %>



<div class="fieldcontain ${hasErrors(bean: remiseInstance, field: 'patente', 'error')} required">
	<label for="patente">
		<g:message code="remise.patente.label" default="Patente" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="patente" required="" value="${remiseInstance?.patente}"/>

</div>


<g:select id="agencia" name="agencia.id" from="${remisesonline.Agencia.list()}" optionKey="id" value="${remiseInstance?.agencia?.id}" style="display:none;" noSelection="['null': '']"/>

<div class="fieldcontain ${hasErrors(bean: remiseInstance, field: 'comodidades', 'error')} ">
	<label for="comodidades">
		<g:message code="remise.comodidades.label" default="Comodidades" />
		
	</label>
	<g:select name="comodidades" from="${remisesonline.Comodidad.list()}" multiple="multiple" optionKey="id" size="5" value="${remiseInstance?.comodidades*.id}" class="many-to-many"/>

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


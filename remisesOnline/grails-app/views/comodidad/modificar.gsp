
<%@ page import="remisesonline.Comodidad"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'remise.label', default: 'Remise')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#show-remise" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div id="show-remise" class="content scaffold-show" role="main">
		<h1>Comodidades</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<g:form url="[action:'actualizar', controller:'comodidad']" method="PUT">
		<g:hiddenField name="version" value="${remiseInstance?.version}" />
		<g:hiddenField name="id" value="${remiseInstance?.id}" />
		<g:each in="${Comodidad.list() }" var="comodidad">
			<div class="fieldcontain">
				<g:set var="checked" value="" />
				<g:if test="${remiseInstance?.comodidades?.contains(comodidad)}">
					<g:set var="checked" value="checked" />
				</g:if>
				<label for="${comodidad?.descripcion}"> <g:message
						code="${comodidad?.descripcion}.label"
						default="${comodidad?.descripcion}" />
				</label> 
					<g:checkBox name="comodidad_${comodidad.id }" value="${checked }"/>
					
			</div>
		</g:each>
			<fieldset class="buttons">
				<g:actionSubmit class="save" action="actualizar"
					value="${message(code: 'default.button.update.label', default: 'Update')}" />
				<g:link class="nuevacomodidad" action="create" controller="comodidad" onclick="return confirm('Se perderan los cambios realizados, ¿Esta Seguro de continuar?');">Agregar Comodidad</g:link>
			</fieldset>
		</g:form>
	</div>
</body>
</html>
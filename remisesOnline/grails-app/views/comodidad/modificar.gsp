<%@ page import="remisesonline.Comodidad"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'remise.label', default: 'Remise')}" />
<title>Comodidades</title>
</head>
<body>
	<div id="modificar-comodidades" class="content scaffold-show" role="main">
		<h1>Comodidades</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<g:form url="[action:'actualizar', controller:'comodidad']"
			method="PUT">
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
					<g:checkBox name="comodidad_${comodidad.id }" value="${checked }" />

				</div>
			</g:each>
			<div class="fieldcontain">
				<label for="${comodidad?.descripcion}"> Nueva Comodidad:
					</label>
					<g:textField name="nuevacomodidad"/>
					<g:actionSubmit class="nuevacomodidad" value="Agregar Comodidad" action="agregar"/>
			</div>
			<fieldset class="buttons">
				<g:actionSubmit class="save" action="actualizar"
					value="${message(code: 'default.button.update.label', default: 'Update')}" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>
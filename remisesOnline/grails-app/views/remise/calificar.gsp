<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<title>Calificar Remise</title>
</head>
<body>
	<a href="#calificar-remise" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div id="calificar-remise" class="content scaffold-create" role="main">
		<h1>Calificar Remise</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<g:hasErrors bean="${remiseInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${remiseInstance}" var="error">
					<li
						<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
							error="${error}" /></li>
				</g:eachError>
			</ul>
		</g:hasErrors>
		<g:form action='guardarCalificacion' id="${remiseInstance?.id }" enctype="multipart/form-data">
			<ol class="property-list remise">

				<g:if test="${remiseInstance?.patente}">
					<li class="fieldcontain"><span id="patente-label"
						class="property-label"><g:message
								code="remise.patente.label" default="Patente" /></span> <span
						class="property-value" aria-labelledby="patente-label"><g:fieldValue
								bean="${remiseInstance}" field="patente" /></span></li>
				</g:if>

				<g:if test="${remiseInstance?.calificacion}">
					<li class="fieldcontain"><span id="patente-label"
						class="property-label">Calificación</span> <span
						class="property-value" aria-labelledby="calificacion-label"><g:fieldValue
								bean="${remiseInstance}" field="calificacion" /></span></li>
				</g:if>

			</ol>
<fieldset class="form">
			<div
				class="fieldcontain ${hasErrors(bean: remiseInstance, field: 'puntaje')} required">
				<label for="puntaje"> <g:message code="remise.puntaje.label"
						default="Puntaje" /> <span class="required-indicator">*</span>
				</label>
				<g:select name="puntaje" required="" from="${1..5 }"/>
			</div>
			</fieldset>
			<fieldset class="buttons">
					<g:submitButton name="puntuar" class="save" value="Puntuar" />
				</fieldset>
		</g:form>
	</div>
</body>
</html>
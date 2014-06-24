<g:javascript>
    var childCount = ${reservaInstance?.paradas?.size()} + 0;
 
    function addParada(){
        var htmlId = "parada" + childCount;
        var deleteIcon = "${resource(dir:'images/skin', file:'database_delete.png')}";
        var templateHtml = "<div id='" + htmlId + "' name='" + htmlId + "'>\n";
        templateHtml += "<input type='text' id='paradas[" + childCount + "].calle' name='paradas[" + childCount + "].calle' />\n";
        templateHtml += "<input type='text' id='paradas[" + childCount + "].numero' name='paradas[" + childCount + "].numero' />\n";
				templateHtml += "<input type='text' id='paradas[" + childCount + "].localidad' name='paradas[" + childCount + "].localidad' />\n";
				templateHtml += "<input type='text' id='paradas[" + childCount + "].descripcion' name='paradas[" + childCount + "].descripcion' />\n";
        templateHtml += "<span onClick='$(\"#" + htmlId + "\").remove();'><img src='" + deleteIcon + "' /></span>\n";
        templateHtml += "<br\> </div>\n";
        $("#childList").append(templateHtml);
        childCount++;
    }
 
</g:javascript>
 
<div id="childList">
    <g:each var="parada" in="${reservaInstance?.paradas}" status="i">
        <!-- Render the parada template (_parada.gsp) here -->
        <g:render template='parada' model="['parada':parada,'i':i]"/>

    </g:each>
</div>
<div style="padding: 20px;">
	<img src="${createLinkTo(dir:'images/skin',file:'database_add.png')}" />
	<a href="#" onclick="addParada();" class="middle">Agregar Parada</a>
</div>
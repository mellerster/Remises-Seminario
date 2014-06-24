<div id="parada${i}" class="parada-div" <g:if test="${hidden}">style="display:none;"</g:if>>
    <g:hiddenField name='paradasList[${i}].id' value='${parada?.id}'/>
 
    <g:textField name='paradasList[${i}].calle' value='${parada?.calle}' />
    <g:textField name='paradasList[${i}].numero' value='${parada?.numero}' />
		<g:textField name='paradasList[${i}].localidad' value='${parada?.localidad}' />
    <g:textField name='paradasList[${i}].descripcion' value='${parada?.descripcion}' />
 
    <span class="del-parada">
        <img src="${resource(dir:'images/skin', file:'database_delete.png')}"
            style="vertical-align:middle;"/>
    </span>
</div>

<g:javascript>
    //bind click event on delete buttons using jquery live
    $(".del-parada").click( function() {
        //find the parent div
        var prnt = $(this).parents(".parada-div");
				prnt.remove();
        //find the deleted hidden input
        /*var delInput = prnt.find("input[id$=deleted]");
        //check if this is still not persisted
        var newValue = prnt.find("input[id$=new]").attr('value');
        //if it is new then i can safely remove from dom
        if(newValue == 'true'){
            prnt.remove();
        }else{
            //set the deletedFlag to true
            delInput.attr('value','true');
            //hide the div
            prnt.hide();
        }     */   
    });
</g:javascript>
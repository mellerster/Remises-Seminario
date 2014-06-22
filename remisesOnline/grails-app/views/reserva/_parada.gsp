<div id="parada${i}" class="parada-div" <g:if test="${hidden}">style="display:none;"</g:if>>
    <g:hiddenField name='paradasList[${i}].id' value='${parada?.id}'/>
 

    <g:textField name='paradasList[${i}].calle' value='${parada?.calle}' />
    <g:textField name='paradasList[${i}].numero' value='${parada?.numero}' />

 
    <span class="del-parada">
        <img src="${resource(dir:'images/skin', file:'database_delete.png')}"
            style="vertical-align:middle;"/>
    </span>
</div>
<script type="text/javascript">
    var childCount = ${reservaInstance?.paradas?.size()} + 0;
 
    function addParada(){
    
        var htmlId = "parada" + childCount;
        var deleteIcon = "${resource(dir:'images/skin', file:'database_delete.png')}";
        var templateHtml = "<div id='" + htmlId + "' name='" + htmlId + "'>\n";
        templateHtml += "<input type='text' id='paradas[" + childCount + "].calle' name='paradas[" + childCount + "].calle' />\n";
        templateHtml += "<input type='text' id='paradas[" + childCount + "].numero' name='paradas[" + childCount + "].numero' />\n";
        templateHtml += "<span onClick='$(\"#" + htmlId + "\").remove();'><img src='" + deleteIcon + "' /></span>\n";
        templateHtml += "</div>\n";
        $("#childList").append(templateHtml);
        childCount++;
    }
 
    //bind click event on delete buttons using jquery live
    $('.del-parada').live('click', function() {
        //find the parent div
        var prnt = $(this).parents(".parada-div");
        //find the deleted hidden input
        //var delInput = prnt.find("input[id$=deleted]");
        //check if this is still not persisted
        //var newValue = prnt.find("input[id$=new]").attr('value');
        //if it is new then i can safely remove from dom
        //if(newValue == 'true'){
        //    prnt.remove();
        //}else{
            //set the deletedFlag to true
        //    delInput.attr('value','true');
            //hide the div
        //    prnt.hide();
       // }        
    });
 
</script>
 
<div id="childList">
    <g:each var="parada" in="${reservaInstance?.paradas}" status="i">
        <!-- Render the parada template (_parada.gsp) here -->
        <g:render template='parada' model="['parada':parada,'i':i]"/>

    </g:each>
</div>
<input type="button" value="Add Parada" onclick="addParada();" />
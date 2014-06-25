$(document).ready(function() { 

  $(".datepicker").datepicker({ 
    dateFormat: 'dd/mm/yy', 
    firstDay: 1, 
  }); 
	
	$(".datetimepicker").datetimepicker({
		dateFormat: 'dd/mm/yy',
		stepMinute: 5
	});

})
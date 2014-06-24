modules = {
    application {
        resource url:'js/application.js'
    }
		
		datepick { 
			dependsOn 'jquery-ui' 
			resource url: 'js/datepick.js' 
		} 
}
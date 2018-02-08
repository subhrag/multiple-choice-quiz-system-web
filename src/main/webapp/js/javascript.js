//using jquery
$(document).ready(function() {

	
	$('.btn-submit-answers').on('click', function(event){
		event.preventDefault();
		
		
	    var names = []
	    $('input:radio').each(function () {
	        var rname = $(this).attr('name');
	        if ($.inArray(rname, names) == -1) names.push(rname);
	    });

	    //do validation for each group
	    var isSubmitRequired=true;
	    $.each(names, function (i, name) {
	        if ($('input[name="' + name + '"]:checked').length == 0) {
	            alert('All Questions should be answered !');
	            isSubmitRequired=false;
	            return false;
	        }
	       
	    });
	    
		if(isSubmitRequired){
			$('.main-form').submit();
		}		
		
	});
	

	$('.back-button').click(function(event) {
		$(".back-button-form").submit();
	});
	
});

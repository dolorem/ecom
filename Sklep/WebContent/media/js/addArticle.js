$(document).ready(function() {
	tinymce.init({
	    selector: "textarea",
	    theme: "modern",
	    plugins: [
	        "advlist autolink lists link image charmap print preview hr anchor pagebreak",
	        "searchreplace wordcount visualblocks visualchars code fullscreen",
	        "insertdatetime media nonbreaking save table contextmenu directionality",
	        "emoticons template paste textcolor"
	    ],
	    language: "pl",
	    entity_encoding: "raw",
	    
	    toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
	    toolbar2: "print preview media | forecolor backcolor emoticons",
	    image_advtab: true,
	    templates: [
	        {title: 'Test template 1', content: 'Test 1'},
	        {title: 'Test template 2', content: 'Test 2'}
	    ]
	});
	
	var amountOfImages = 0;
	$("#addAdditionalImageButton").click(function() {
		amountOfImages++;
		$("#additionalImages").append('<div class="control-group">' +
				'<label class="control-label" for="additionalImage' + amountOfImages + 
				'" id="removeAdditionalImageLabel' + amountOfImages + '"><button class="btn btn-danger removeAdditionalImage" type="button" id="removeAdditionalImage' + amountOfImages + 
				'">Usuń</button></label><div class="controls"><input type="file" name="additionalImage' + 
				amountOfImages + '" id="additionalImage' + amountOfImages + '" /></div></div>');
		$('#amountOfAdditionalImages').val(amountOfImages);
	});
	
	$(document).delegate(".removeAdditionalImage", "click", function(event) {
		var id = parseInt($(this).attr('id').replace("removeAdditionalImage", ""));
		$(this).parent().parent().remove();
		for (var i = id + 1; i <= amountOfImages; i++)
		{
			$('#removeAdditionalImage' + i).attr("id", "removeAdditionalImage" + (i - 1));
			$('#removeAdditionalImageLabel' + i).attr('for', 'additionalImage' + (i - 1));
			$('#removeAdditionalImageLabel' + i).attr("id", "removeAdditionalImageLabel" + (i - 1));
			$('#additionalImage' + i).attr('name', 'additionalImage' + (i - 1));
			$('#additionalImage' + i).attr("id", "additionalImage" + (i - 1));
		}
		amountOfImages--;
		$('#amountOfAdditionalImages').val(amountOfImages);
	});
});
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
	
	$('#removeMainImage').click(function() {
		$('#mainImage').remove();
		$('#newMainImage').css("visibility", "visible");
		$('#removeMainImage').remove();
		$('#deletedMainImage').val('true');
	});
	
	$('.removeOriginalImage').click(function() {
		//var id = $(this).parent().find("img").attr("id").replace("originalImg", "");
		var id = $(this).attr('id');
		$('#deletedImagesId').append('<option value="' + id +'" selected="selected"></option>');
		$(this).parent().parent().remove();
	});	
	
	var amountOfAdditionalImages = 0;
	
	$(document).on('click', '.removeAdditionalImageButton', function() {
		$(this).parent().parent().remove();
	});
	
	$('#addAdditionalImageButton').click(function() {
		var foo = '<div class="control-group">' + 
			'<label class="control-label">' +
			'<button class="removeAdditionalImageButton btn btn-danger" type="button">Usuń</button>' +
			'</label><div class="controls">' + 
			'<input type="file" name="newAdditionalImages[' + (amountOfAdditionalImages++) + ']"/></div></div>';
		$('.form-actions').prev().after(foo);
	});
});
$(document).ready(function() {
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
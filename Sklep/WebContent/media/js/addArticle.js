$(document).ready(function() {
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
$(document).ready(function() {
	$('#checkAll').click(function() {
		var allChecked = true;
		
		$(':checkbox').each(function() {
			if (!$(this).prop('checked'))
				allChecked = false;
		});
		var check = true;
		if (allChecked)
			check = false;
		$(':checkbox').each(function() {
			$(this).prop('checked', check);
		});
		return false;
	});	
	
	$('#deleteAllChecked').click(function() {
		var arr = new Array();
		counter = 0;
		$(':checkbox').each(function() {
			if ($(this).prop('checked'))
				arr[counter++] = $(this).attr('id').replace('item', '');
		});
		if (counter != 0 && confirm("Czy na pewno usunąć zaznaczone elementy?"))
		{
			$.ajax({
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				url: '/administrator/articles/delete.json',
				type: 'DELETE',
				data: JSON.stringify(arr),
				success: function(data) {
					location.reload(true);
				}				
			});			
		}
	});
	
	$('.deleteSingleItem').click(function() {
		var id = $(this).attr('id').replace('singleItem', '');
		if (confirm("Czy na pewno usunąć element?"))
		{
			$.ajax({
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				url: '/administrator/articles/delete.json',
				type: 'DELETE',
				data: JSON.stringify([id]),
				success: function(data) {
					location.reload(true);
				}				
			});
		}
	});
});
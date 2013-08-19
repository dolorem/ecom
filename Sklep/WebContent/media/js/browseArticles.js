$(document).ready(function() {
	var ids = [];
	
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
		if (counter != 0)
		{
			$('#myModal>.modal-body').html('Czy na pewno usunąć zaznaczone elementy?');
			ids = arr;
			$('#myModal').modal('toggle');
		}
	});
	
	$('.deleteSingleItem').click(function() {
		ids = [$(this).attr('id').replace('singleItem', '')];
		$('#myModal>.modal-body').html('Czy na pewno usunąć element?');
		$('#myModal').modal('toggle');
		
	});
	
	$('#accept').click(function() {
		$.ajax({
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			url: '/administrator/articles/delete.json',
			type: 'DELETE',
			data: JSON.stringify(ids),
			success: function(data) {
				location.reload(true);
			}				
		});	
		$('#myModal').modal('hide');
	});
	
	$('#reject').click(function() {
		$('#myModal').modal('hide');
		ids = [];
	});
});
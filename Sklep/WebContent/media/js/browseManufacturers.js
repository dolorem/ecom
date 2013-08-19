$(document).ready(function() {
	var id = -1;
	
	$('.removeSingleItem').click(function() {
		id = $(this).attr('id').replace('item', '');
		$('#myModal').modal();
	});
	
	$('#accept').click(function() {
		$.ajax({
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			url: '/administrator/manufacturers/delete.json',
			type: 'DELETE',
			data: JSON.stringify([id]),
			success: function(data) {
				location.reload(true);
			}				
		});	
		$('#myModal').modal('toggle');
	});
	
	$('#reject').click(function() {
		$('#myModal').modal('hide');
		id = -1;
	});
});
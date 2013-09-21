$(document).ready(function(){
	var id = -1;
	
	$('.delete').click(function()
	{
		id = $(this).attr('id').replace('delete', '');
		$('#myModal').modal('toggle');
	});
	
	$('#accept').click(function() {
		$.ajax({
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			url: '/administrator/menu/delete.json',
			type: 'DELETE',
			data: JSON.stringify(id),
			success: function(data)
			{
				location.reload(true);
			}
		});
	});
	
	$('#reject').click(function() {
		$('#myModal').modal('hide');
		id = -1;
	});
});
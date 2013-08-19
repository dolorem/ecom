$(document).ready(function() {
	$('.removeSingleItem').click(function() {
		var id = $(this).attr('id').replace('item', '');
		if (confirm("Czy na pewno usunąć tego producenta?"))
		{
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
		}
	});
});
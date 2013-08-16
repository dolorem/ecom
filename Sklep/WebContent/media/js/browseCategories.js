$(document).ready(function() {
	$('.removeSingleItem').click(function() {
		var id = $(this).attr('id').replace('delete', '');
		if (confirm("Czy na pewno chcesz usunąć tę kategorię?\n Wszystkie podkategorie także zostaną usunięte."))
		{
			$.ajax({
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				url: '/administrator/categories/delete.json',
				type: 'DELETE',
				data: JSON.stringify([id]),
				success: function(data) {
					location.reload(true);
				}				
			});	
		}
	});
});

function obtenerJson() {
		var form = $('#form');
		var json = "";
		$
				.ajax({
					url : 'ServGetJson',
					data : form.serialize(),
					type : 'post',
					success : function(data) {
						$("#result").show();
						$('svg').remove();
						obj=JSON.parse(data);
						crearGrafo(obj);
					}
				});

	}
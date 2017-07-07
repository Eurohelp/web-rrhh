
function obtenerJson() {
	var json = "";
	var form = $('#form');
	$.ajax({
		url : 'ServGetJson',
		data : form.serialize(),
		type : 'post',
		success : function(data) {
			$("#result").show();
			$('svg').remove();
			obj = JSON.parse(data);
			crearGrafo(obj);
		}
	});
}

function createIndex() {
	var form = $('#form');
	$.ajax({
		url : 'ServGeneradorIndex',
		data : form.serialize(),
		type : 'post',
		success : function(data) {
			$('#form').html("");
			$('#form').html(data);
			$("#result").remove();
		}
	});
}
function generateAll(){
	createIndex()
	obtenerJson();
}
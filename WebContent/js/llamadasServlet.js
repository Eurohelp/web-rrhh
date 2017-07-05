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
			obj = JSON.parse(JSON.stringify(data));
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
			// var a = $('input[name="Categoria"]:checked').val();
			// console.log($('input[name="locationthemes"]:checked').serialize());
			// console.log(a);
			// alert($("input[name='Categoria']:checked").map(function() {
			// return this.value;
			// }).get().join(","));
			var catSel = saveSelected();
			$('#form').html("");
			$('#form').html(data);
			$("#result").remove();
			putSelected(catSel);
		}
	});
}

function saveSelected() {
	var form = $('#form');
	var categoriaSelec = [];
	$("input[name='Categoria']:checked").each(function(i) {
		categoriaSelec.push(this.value);
	}).get();
	$("input[name='Habilidades']:checked").each(function(i) {
		categoriaSelec.push(this.value);
	}).get();
	$("input[name='Certificaciones']:checked").each(function(i) {
		categoriaSelec.push(this.value);
	}).get();
	$("input[name='Idioma']:checked").each(function(i) {
		categoriaSelec.push(this.value);
	}).get();
	$("input[name='Experiencia']:checked").each(function(i) {
		categoriaSelec.push(this.value);
	}).get();
	$("input[name='Universidad']:checked").each(function(i) {
		categoriaSelec.push(this.value);
	}).get();
	return categoriaSelec;
}

function changeIcon(element) {
	var elemento = element.slice(0, element.length - 1);
	var etiqueta = $("[id=span" + elemento + "]");
	var labelEtiqueta = $("[name=" + element + "]");
	if (element.includes(elemento+"C")) {
		etiqueta.attr("class", "glyphicon glyphicon-chevron-up pull-right");
		labelEtiqueta.attr("name", elemento+"A");
	} else {
		etiqueta.attr("class", "glyphicon glyphicon-chevron-down pull-right");
		labelEtiqueta.attr("name", elemento+"C");
	}
}
function putSelected(arraySelect) {
	arraySelect.forEach(function(entry) {
		$("#" + entry).attr("checked", "checked");
	});
}
function generateAll() {
	createIndex()
	obtenerJson();
}
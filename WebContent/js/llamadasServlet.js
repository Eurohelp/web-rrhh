var boolCat = false;
var boolHab = false;
var boolCert = false;
var boolIdi = false;
var boolExp = false;
var boolUniv = false;

function obtenerJson() {
	var json = "";
	var form = $('#form');
	$.ajax({
		url : 'ServGetJson',
		data : form.serialize(),
		type : 'post',
		success : function(data) {
			$("#search").css("visibility", "visible");
			$('svg').remove();
			// obj = JSON.parse(JSON.stringify(data));
			crearGrafo(data);
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
			var catSel = saveSelected();
			$('#pageLeftMenu').html("");
			$('#pageLeftMenu').html(data);
			$("#result").remove();
			putSelected(catSel);
		}
	});
}

function saveSelected() {
	var form = $('#form');
	var categoriaSelec = [];
	$("input[name='Categoria']:checked").each(function(i) {
		categoriaSelec.push(this.id);
		boolCat = true;
		console.log("lo debe");
	}).get();
	$("input[name='Habilidades']:checked").each(function(i) {
		categoriaSelec.push(this.id);
		boolHab = true;
	}).get();
	$("input[name='Certificaciones']:checked").each(function(i) {
		categoriaSelec.push(this.id);
		boolCert = true;
	}).get();
	$("input[name='Idioma']:checked").each(function(i) {
		categoriaSelec.push(this.id);
		boolIdi = true;
	}).get();
	$("input[name='Experiencia']:checked").each(function(i) {
		categoriaSelec.push(this.id);
		boolExp = true;
	}).get();
	$("input[name='Universidad']:checked").each(function(i) {
		categoriaSelec.push(this.id);
		boolUniv = true;
	}).get();
	return categoriaSelec;
}

function changeIcon(element) {
	var elemento = element.slice(0, element.length - 1);
	var span = $("[id=span" + elemento + "]");
	var cuadrado = $("[name=" + element + "]");
	if (element.includes(elemento + "C")) {
		span.attr("class", "glyphicon glyphicon-chevron-up pull-right");
		cuadrado.attr("name", elemento + "S");
	} else {
		span.attr("class", "glyphicon glyphicon-chevron-down pull-right");
		cuadrado.attr("name", elemento + "C");
	}
}
function putSelected(arraySelect) {
	arraySelect.forEach(function(entry) {
		var element = entry.replace(/\s/g, "");
		var element = element.replace("+", "");
		var element = element.replace("+", "");
		changeCboxIcon(element + "N")
	});
}
function generateAll() {
	createIndex()
	obtenerJson();
}

function changeCboxIcon(element) {
	var elemento = element.slice(0, element.length - 1);
	var labelEtiqueta = $("[id=" + element + "]");
	var etiqueta = $("[id=" + elemento + "cb]");
	if (element.includes(elemento + "N")) {
		etiqueta.attr("class", "glyphicon glyphicon-check");
		labelEtiqueta.attr("id", elemento + "S");
		labelEtiqueta.attr("class",
				"list-group-item list-group-item-info large active");
		$("[id=" + elemento + "]").prop("checked", true);
	} else {
		etiqueta.attr("class", "glyphicon glyphicon-unchecked");
		labelEtiqueta.attr("id", elemento + "N");
		labelEtiqueta.attr("class", "list-group-item large");
		$("[id=" + elemento + "]").prop("checked", false);
	}
}

function validar() {
	saveSelected();
	var ckboxCategoria = document.getElementsByName('Categoria');
	var ckboxHabilidades = document.getElementsByName('Habilidades');
	var ckboxCertificaciones = document.getElementsByName('Certificaciones');
	var ckboxIdioma = document.getElementsByName('Idioma');
	var ckboxExperiencia = document.getElementsByName('Experiencia');
	var ckboxUniversidad = document.getElementsByName('Universidad');
	if (boolCat == false && boolCert == false && boolExp == false
			&& boolHab == false && boolIdi == false && boolUniv == false) {
		alertify.notify('Debes seleccionar al menos una categoria', 'error', 5,
				function() {
				});
	} else if (boolCat == true
			&& ckboxHabilidades.length == 0
			&& ckboxCertificaciones.length == 0
			&& ckboxIdioma.length == 0 && ckboxExperiencia.length == 0 && ckboxUniversidad.length == 0) {
		generateAll();
	} else if (boolCat == true && (boolCert == false || boolHab == false)) {
		alertify.notify(
				'Debes seleccionar al menos una habilidad y una certificacion',
				'error', 5, function() {
				});
	}else if(boolCat == true && boolCert == true && boolHab == true && ckboxIdioma.length == 0 &&
			ckboxExperiencia.length == 0 && ckboxUniversidad.length == 0){
		generateAll();
	}
	else if(boolCat == true && boolCert == true && boolHab == true && (boolIdi== false || boolExp ==false || boolUniv==false) ){
		alertify.notify(
				'Debes seleccionar al menos un idioma, una universidad y algun tipo de experiencia',
				'error', 5, function() {
				});	}
	else if(boolCat == true && boolCert == true && boolHab == true && boolIdi== true && boolExp ==true && boolUniv==true){
		generateAll()
	}

}
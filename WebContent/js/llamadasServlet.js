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
	console.log(form);
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
		//	putSelected(catSel);
		}
	});
}

function saveSelected() {
	$('#getData').on('click', function(event) {
        event.preventDefault(); 
        var checkedItems = {}, counter = 0;
        $("#check-list-box li.active").each(function(idx, li) {
            checkedItems[counter] = $(li).text();
            counter++;
        });
        $('#display-json').html(JSON.stringify(checkedItems, null, '\t'));
    });
}

function changeIcon(element) {
	var elemento = element.slice(0, element.length - 1);
	var span = $("[id=span" + elemento + "]");
	console.log(span);
	var cuadrado = $("[name=" + element + "]");
	console.log(cuadrado);
	if (element.includes(elemento+"N")) {
		span.attr("class", "glyphicon glyphicon-chevron-up pull-right");
		cuadrado.attr("name", elemento+"S");
	} else {
		span.attr("class", "glyphicon glyphicon-chevron-down pull-right");
		cuadrado.attr("name", elemento+"N");
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

function changeCboxIcon(element){
	var elemento = element.slice(0, element.length - 1);
	var labelEtiqueta = $("[id=" + element + "]");
	var etiqueta= $("[id=" + elemento + "cb]");
	console.log(etiqueta);
	if (element.includes(elemento+"N")) {
		etiqueta.attr("class", "glyphicon glyphicon-check");
		labelEtiqueta.attr("id", elemento+"S");
		labelEtiqueta.attr("class", "list-group-item list-group-item-info large active");
	} else {
		etiqueta.attr("class", "glyphicon glyphicon-unchecked");
		labelEtiqueta.attr("id", elemento+"N");
		labelEtiqueta.attr("class", "list-group-item large");
	}
}



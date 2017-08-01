var boolCat = false;
var boolHab = false;
var boolCert = false;
var boolIdi = false;
var boolExp = false;
var boolUniv = false;
var fin = false;

/**
 * Metodo ejecuta la llamada al servlet que obtiene el json para la creacion del
 * grafo
 * 
 * @returns
 */
function obtenerJson() {
	var form = $("#form");
	$
			.ajax({
				url : "ServGetJson",
				data : form.serialize(),
				type : "post",
				success : function(data) {
					$("#search").css("visibility", "visible");
					$("svg").remove();
					if (data.includes("vacio")) {
						$("svg").remove();
						swal(
								"Oops...",
								"No se encontraron resultados con esos parametros de busqueda",
								"info");
					} else {
						crearGrafo(data);
						if (!fin) {
							crearIndex();
						} else {
							fin = false;
						}
					}
				}
			});
}
/**
 * Metodo ejecuta llamada al servlet que generará el index
 * 
 * @returns
 */
function crearIndex() {
	var form = $("#form");
	$.ajax({
		url : "ServGeneradorIndex",
		data : form.serialize(),
		type : "post",
		success : function(data) {
			var catSel = guardarSelecciones();
			$("#pageLeftMenu").html("");
			$("#pageLeftMenu").html(data);
			ponerSelecciones(catSel);
		}
	});
}
/**
 * Metodo comprueba las opciones del menú seleccionadas
 * 
 * @returns
 */
function comprobarSelecciones() {
	boolCat = false;
	boolHab = false;
	boolCert = false;
	boolIdi = false;
	boolExp = false;
	boolUniv = false;
	var ckboxCategoria = document.getElementsByName("Categoria");
	var ckboxHabilidades = document.getElementsByName("Habilidades");
	var ckboxCertificaciones = document.getElementsByName("Certificaciones");
	var ckboxIdioma = document.getElementsByName("Idioma");
	var ckboxExperiencia = document.getElementsByName("Experiencia");
	var ckboxUniversidad = document.getElementsByName("Universidad");
	for (i = 0; i < ckboxCategoria.length; i++) {
		if (ckboxCategoria[i].checked == true) {
			boolCat = true;
		}
	}
	for (i = 0; i < ckboxHabilidades.length; i++) {
		if (ckboxHabilidades[i].checked == true) {
			boolHab = true;
			break;
		}
	}
	for (i = 0; i < ckboxCertificaciones.length; i++) {
		if (ckboxCertificaciones[i].checked == true) {
			boolCert = true;
			break;
		}
	}
	for (i = 0; i < ckboxIdioma.length; i++) {
		if (ckboxIdioma[i].checked == true) {
			boolIdi = true;
			break;
		}
	}
	for (i = 0; i < ckboxExperiencia.length; i++) {
		if (ckboxExperiencia[i].checked == true) {
			boolExp = true;
			break;
		}
	}
	for (i = 0; i < ckboxUniversidad.length; i++) {
		if (ckboxUniversidad[i].checked == true) {
			boolUniv = true;
			break;
		}
	}
}

/**
 * Metodo almacena opciones seleccionadas para su recolocación al volverse a
 * generar el menú
 * 
 * @returns
 */
function guardarSelecciones() {
	var form = $("#form");
	var categoriaSelec = [];
	$("input[name='Categoria']:checked").each(function(i) {
		categoriaSelec.push(this.id);
	}).get();
	$("input[name='Habilidades']:checked").each(function(i) {
		categoriaSelec.push(this.id);
	}).get();
	$("input[name='Certificaciones']:checked").each(function(i) {
		categoriaSelec.push(this.id);
	}).get();
	$("input[name='Idioma']:checked").each(function(i) {
		categoriaSelec.push(this.id);
	}).get();
	$("input[name='Experiencia']:checked").each(function(i) {
		categoriaSelec.push(this.id);
	}).get();
	$("input[name='Universidad']:checked").each(function(i) {
		categoriaSelec.push(this.id);
	}).get();
	return categoriaSelec;
}
/**
 * Metodo cambia icono "subida" "bajada" de las categorias del menú.
 * 
 * @param element
 * @returns
 */
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

/**
 * Metodo recoloca opciones elegidas previamente al regenererse el menú
 * 
 * @param arraySelect
 * @returns
 */
function ponerSelecciones(arraySelect) {
	arraySelect.forEach(function(entry) {
		var element = entry.replace(/\s/g, "");
		var element = element.replace("+", "");
		var element = element.replace("+", "");
		changeCboxIcon(element + "N")
	});
}

/**
 * Marca como seleccionada una opción determinada
 * del menú
 *  
 * @param element
 * @returns
 */
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

/**
 * Controlador del menú
 * 
 * @param clase
 * @returns
 */
function validar(clase) {
	comprobarSelecciones();
	var ckboxCategoria = document.getElementsByName("Categoria");
	var ckboxHabilidades = document.getElementsByName("Habilidades");
	var ckboxCertificaciones = document.getElementsByName("Certificaciones");
	var ckboxIdioma = document.getElementsByName("Idioma");
	var ckboxExperiencia = document.getElementsByName("Experiencia");
	var ckboxUniversidad = document.getElementsByName("Universidad");

	// Si solo esta seleccionada la categoria
	if (boolCat == true && ckboxHabilidades.length == 0
			&& ckboxCertificaciones.length == 0 && ckboxIdioma.length == 0
			&& ckboxExperiencia.length == 0 && ckboxUniversidad.length == 0) {
		obtenerJson();
		// Si hay una categoria seleccionada pero no se han seleccionado
		// una certificacion o una habilidad
	} else if (boolCat == true && (boolCert == false || boolHab == false)
			&& ckboxIdioma.length == 0 && ckboxExperiencia.length == 0
			&& ckboxUniversidad.length == 0) {
		if (clase != "Certificaciones" && clase != "Habilidades") {
			eliminarOpcionesMenu(1);
			obtenerJson();
		} else {
			alertify
					.notify(
							"Debes seleccionar al menos una habilidad y una certificacion",
							"error", 5, function() {
							});
		}
		// Si se ha seleccionado una categoria, habilidad y certificacion
		// y aun no se ha generado aun menu para las demas
	} else if (boolCat == true && boolCert == true && boolHab == true
			&& ckboxIdioma.length == 0 && ckboxExperiencia.length == 0
			&& ckboxUniversidad.length == 0) {
		if (clase != "Certificaciones" && clase != "Habilidades") {
			eliminarOpcionesMenu(1);
			obtenerJson();
		} else {
			obtenerJson();
		}
		// Si se ha seleccionado habilidad, categoria y certificacion pero
		// o no se ha seleccionado idioma, universidad o algun tipo de
		// experiencia
	} else if (boolCat == true && boolCert == true && boolHab == true
			&& (boolIdi == false || boolExp == false || boolUniv == false)) {
		if (clase != "Idioma" && clase != "Experiencia"
				&& clase != "Universidad") {
			if (clase == "Categoria") {
				eliminarOpcionesMenu(1);
			}
			eliminarOpcionesMenu(2);
			obtenerJson();
		} else {
			alertify
					.notify(
							"Debes seleccionar al menos un idioma, una universidad y algun tipo de experiencia",
							"error", 5, function() {
							});
		} // Si se ha seleccionado todo correctamente
	} else if (boolCat == true && boolCert == true && boolHab == true
			&& boolIdi == true && boolExp == true && boolUniv == true) {
		if (clase != "Idioma" && clase != "Experiencia"
				&& clase != "Universidad") {
			if (clase == "Categoria") {
				eliminarOpcionesMenu(1);
			}
			eliminarOpcionesMenu(2);
			obtenerJson();
		} else {
			fin = true;
			obtenerJson();
		}
	}
	// si no esta seleccionada la categoria y si otra opcion
	else if (boolCat == false
			&& (boolCert == true || boolHab == true || boolIdi == true
					|| boolExp == true || boolUniv == true)) {
		eliminarOpcionesMenu(1);
		eliminarOpcionesMenu(2);
		alertify.notify("Debes seleccionar al menos una categoria", "error", 5,
				function() {
				});
	}
	// si esta seleccionado el idioma, la experiencia o la universidad
	// pero alguna de las anteriores no
	else if ((boolIdi == true || boolExp == true || boolUniv == true)
			&& (boolCat == false || boolCert == false || boolHab == false)) {
		if (boolCat == false) {
			eliminarOpcionesMenu(1);
		}
		eliminarOpcionesMenu(2);
		alertify
				.notify(
						"Debes seleccionar al menos una categoria, una habilidad y una certificacion",
						"error", 5, function() {
						});
	}
}
/**
 * Elimina opciones del menú
 * @param pCase
 * @returns
 */
function eliminarOpcionesMenu(pCase) {
	switch (pCase) {
	case 1:
		$("#CertificacionesE").remove();
		$("#HabilidadesE").remove();
		$("#sm").remove();
		$("#sg").remove();
		$("svg").remove();
		break;
	case 2:
		$("#IdiomaE").remove();
		$("#UniversidadE").remove();
		$("#ExperienciaE").remove();
		$("#st").remove();
		$("#sr").remove();
		$("#sh").remove();
		$("svg").remove();
		break;
	}
}

function generarGrafo(element, clase) {
	changeCboxIcon(element);
	validar(clase);
}
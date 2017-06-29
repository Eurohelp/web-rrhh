package eurohelp.recursoshumanos.pagegenerator;

public class Json {
	private String json = "";

	public Json(String pJson) {
		json = pJson;
		System.out.println(json);
	}

	public String[] obtenerFilas() {
		json = json.replace("(", "");
		json = json.replace("\"", "");
		json = json.replace("\"\"", "");
		String[] elementos = json.split("\\)");
		return elementos;
	}

	public String parsearJSON() {
		String result = "{\"head\" : {\"vars\" : [ \"s\", \"p\", \"o\" ]},\"results\" : {\"bindings\" : [{";
		String[] filas = obtenerFilas();
		for (int i = filas.length - 1; i > -1; i--) {
			String[] porElemento = filas[i].split(",");
			if (i == filas.length - 1) {
				result = result.replace("{\"bindings\" : [{",
						"{\"bindings\" : [{\"o\" : {\"type\" : \"uri\", \"value\":\"" + porElemento[2] + "\" }}]}}");
			} else {
				result = result.replace("{\"bindings\" : [{",
						"{\"bindings\" : [{\"o\" : {\"type\" : \"uri\", \"value\":\"" + porElemento[2] + "\" }},{");
			}
			result = result.replace("{\"bindings\" : [{",
					"{\"bindings\" : [{\"p\" : {\"type\" : \"uri\", \"value\":\"" + porElemento[1] + "\" },");
			result = result.replace("{\"bindings\" : [{",
					"{\"bindings\" : [{\"s\" : {\"type\" : \"uri\", \"value\":\"" + porElemento[0] + "\" },");
		}
		return result;
	}

	public String parsearJSON2() {
		String a = "";
		String[] filas = obtenerFilas();
		for (int i = filas.length - 1; i > -1; i--) {
			String[] porElemento = filas[i].split(",");
			if (i == 0) {
				a = a.concat("{source:\"" + porElemento[0] + "\",target:\"" + porElemento[2] + "\",type:\""
						+ porElemento[1] + "\"}");
			} else {
				a = a.concat("{source:\"" + porElemento[0] + "\",target:\"" + porElemento[2] + "\",type:\""
						+ porElemento[1] + "\"},");
			}
		}
		a = a.concat("");
		return a;
	}

}

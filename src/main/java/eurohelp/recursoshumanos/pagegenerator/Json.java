package eurohelp.recursoshumanos.pagegenerator;

public class Json {
	private String json = "";

	public Json(String pJson) {
		json = pJson;
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

}

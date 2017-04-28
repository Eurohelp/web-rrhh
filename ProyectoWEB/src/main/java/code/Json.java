package code;

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

	// public String parsearJSON(){
	// String cabecera = "{\"nodes\":[";
	// String[] filas=obtenerFilas();
	// for (int i = 0; i < filas.length; i++) {
	// String[] porElemento = filas[i].split(",");
	// if(i+1==filas.length-1){
	// cabecera=cabecera.replace("{\"nodes\":[","{\"nodes\":["+"\"value\":\""+
	// porElemento[1]+"\"}]");
	// }
	// else{
	// cabecera=cabecera.replace("{\"nodes\":[","{\"nodes\":["+"\"value\":\""+
	// porElemento[1]+"\"},");
	// }
	// cabecera=cabecera.replace("{\"nodes\":[","{\"nodes\":["+"\"label\":\""+
	// porElemento[2]+"\",");
	// cabecera=cabecera.replace("{\"nodes\":[","{\"nodes\":[{"+"\"key\":\""+
	// porElemento[0]+"\",");
	// }
	// return cabecera;
	// }

	public String parsearJSON() {
		String result = "{\"head\" : {\"vars\" : [ \"s\", \"p\", \"o\" ]},\"results\" : {\"bindings\" : [{";
		String[] filas = obtenerFilas();
		for (int i = 0; i < filas.length; i++) {
			String[] porElemento = filas[i].split(",");
			if (i ==0) {
				result = result.replace("{\"bindings\" : [{", "{\"bindings\" : [{\"o\" : { \"value\":\"" + porElemento[2] + "\" }}]}}");
			} else {
				result = result.replace("{\"bindings\" : [{", "{\"bindings\" : [{\"o\" : { \"value\":\"" + porElemento[2] + "\" }},{");
			}
			result = result.replace("{\"bindings\" : [{", "{\"bindings\" : [{\"p\" : { \"value\":\"" + porElemento[1] + "\" },");
			result = result.replace("{\"bindings\" : [{", "{\"bindings\" : [{\"s\" : { \"value\":\"" + porElemento[0] + "\" },");
		}
		return result;
	}

	public int getNumeroNodo(String pElemento) {
		int numNodo = -1;
		return numNodo;
	}
}

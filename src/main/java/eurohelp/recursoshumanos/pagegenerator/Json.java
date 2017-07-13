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
		String json = "";
		String[] filas = obtenerFilas();
		for (int i = filas.length - 1; i > -1; i--) {
			String[] porElemento = filas[i].split(",");
			if (i == 0) {
				json = json.concat("" + porElemento[0] +","+ porElemento[2] + ","
						+ porElemento[1] + "");
			} else {
				json = json.concat("" + porElemento[0] + "," + porElemento[2] + ","
						+ porElemento[1] + ";");
			}
		}
		json = json.concat("");
		System.out.println(json);
		return json;
	}

}

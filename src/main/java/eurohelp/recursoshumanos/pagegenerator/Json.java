package eurohelp.recursoshumanos.pagegenerator;

/**
 * 
 * @author Mishel Uchuari, 28 jul. 2017
 */

public class Json {

	/**
	 * Devuelve las filas del resultado sparql
	 * 
	 * @param pResultados
	 * @return
	 */
	public String[] getFilas(String pResultados) {
		pResultados = pResultados.replace("(", "");
		pResultados = pResultados.replace("\"", "");
		pResultados = pResultados.replace("\"\"", "");
		String[] elementos = pResultados.split("\\)");
		return elementos;
	}

	/**
	 * Convierte el resultado sparql a un formato adecuado para convertirse en
	 * json posteriormente
	 * 
	 * @param pResultados
	 * @return
	 */
	public String parsearJSON(String pResultados) {
		String json = "";
		String[] filas = getFilas(pResultados);
		int i = filas.length - 1;
		while (i > -1) {
			String[] porElemento = filas[i].split(",");
			if (i == 0) {
				json = json.concat("" + porElemento[0] + "," + porElemento[2] + "," + porElemento[1] + "");
			} else {
				json = json.concat("" + porElemento[0] + "," + porElemento[2] + "," + porElemento[1] + ";");
			}
			i--;
		}
		return json;
	}
}

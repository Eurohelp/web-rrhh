package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openrdf.repository.RepositoryException;

import stardog.Stardog;

/**
 * Servlet implementation class ServGetJson
 */
public class ServGetJson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServGetJson() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	// Categoria, Experiencia, Habilidades, Certificaciones, Idiomas,
	// Universidad
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] categoria = request.getParameterValues("Categoria");
		String[] experiencia = request.getParameterValues("Experiencia");
		String[] habilidades = request.getParameterValues("Habilidades");
		String[] certificaciones = request.getParameterValues("Certificaciones");
		String[] idiomas = request.getParameterValues("Idiomas");
		String[] universidad = request.getParameterValues("Universidad");
		try {
			Stardog stardog = new Stardog();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Gson json= stardog.getGraphData(categoria, experiencia, habilidades,
		// certificaciones, idiomas, universidad);
	}

	public String rellenarQuery(String[] pCategoria, String[] pExperiencia, String[] pHabilidades,
			String[] pCertificaciones, String[] pIdiomas, String[] pUniversidad) {
		String query = "CONSTRUCT{" + "?persona ?tipo ?prefPersona.?persona ?tipo ?Categoria."
				+ "?Categoria ?id ?nombreCategoria. ?Categoria ?tipo ?prefPuesto."
				+ "?persona ?prefExperiencia ?Experiencia. " + "?Experiencia ?id ?nombreExperiencia."
				+ "?persona ?habilidades ?lenguajeProg. " + "?lenguajeProg ?id ?nombreLenguajeProg."
				+ "?persona ?prefCertif ?certificacion. " + "?certificacion ?id ?nombreCertificacion."
				+ "?persona ?prefIdioma ?idioma. " + "?idioma ?id ?nombreIdioma." + "}"
				+ "where{ GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {"
				+ "?persona ?tipo ?prefPersona." + "?persona ?tipo ?Categoria." + "?Categoria ?id ?nombreCategoria."
				+ "?Categoria ?tipo ?prefPuesto." + "?persona ?prefExperiencia ?Experiencia."
				+ "?Experiencia ?id ?nombreExperiencia." + "?persona ?habilidades ?lenguajeProg."
				+ "?lenguajeProg ?id ?nombreLenguajeProg." + "?persona ?prefCertif ?certificacion."
				+ "?certificacion ?id ?nombreCertificacion." + "?persona ?prefIdioma ?idioma."
				+ "?idioma ?id ?nombreIdioma." + "FILTER (?nombreCategoria IN ())" + "FILTER (?nombreExperiencia IN ())"
				+ "FILTER (?nombreLenguajeProg IN ())" + "FILTER (?nombreCertificacion IN ())"
				+ "FILTER (?nombreIdioma IN ())" + "FILTER (?nombreIdioma IN ())" + "FILTER (?tipo = rdf:type )"
				+ "FILTER (?id = schema:name )" + "FILTER (?prefIdioma = <http://opendata.euskadi.eus/idioma> )"
				+ "FILTER (?habilidades = <http://opendata.euskadi.eus/skill> )"
				+ "FILTER (?prefExperiencia = <http://opendata.euskadi.eus/experience> )"
				+ "FILTER (?prefPuesto = <http://opendata.euskadi.eus/puesto> )"
				+ "FILTER (?prefCertif = <http://opendata.euskadi.eus/certification> )"
				+ "FILTER (?prefPersona = <http://schema.org/Person> )" + "}" + "}";
		query = completarFila("?nombreCategoria IN (", pCategoria, query);
		query = completarFila("?nombreExperiencia IN (", pExperiencia, query);
		query = completarFila("?nombreLenguajeProg IN", pHabilidades, query);
		query = completarFila("?nombreCertificacion IN (", pCertificaciones, query);
		query = completarFila("?nombreIdioma IN (", pIdiomas, query);
		System.out.println(query);
		return query;
	}

	public String completarFila(String pPatron, String[] pConjunto, String pQuery) {
		for (int i = 0; i < pConjunto.length; i++) {
			if (i + 1 >= pConjunto.length) {
				pQuery = pQuery.replace(pPatron, pPatron + pConjunto[i]);
			} else {
				pQuery = pQuery.replace(pPatron, pPatron + pConjunto[i] + ",");
			}
		}
		return pQuery;
	}

}
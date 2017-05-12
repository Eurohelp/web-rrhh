package stardog;


import java.util.ArrayList;
import java.util.Iterator;

import org.openrdf.model.Model;
import org.openrdf.model.Statement;
import org.openrdf.query.GraphQuery;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;


import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.sesame.StardogRepository;

import code.GeneradorIndex;
import code.Json;

public class Stardog {
	private RepositoryConnection repository;
	private String serverURL;

	/**
	 * @throws RepositoryException
	 * 
	 */
	public Stardog() throws RepositoryException {
		serverURL = "http://ckan.eurohelp.es:5820";
		Repository stardogRepository = new StardogRepository(
				ConnectionConfiguration.to("LODgenAppTurismo").server(serverURL).credentials("admin", "ctxakurra"));
		stardogRepository.initialize();
		repository = stardogRepository.getConnection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.eurohelp.opendata.aldapa.api.storage.TripleStoreService#loadRDF4JModel
	 * (org.eclipse.rdf4j.model.Model)
	 */
	public void loadRDF4JModel(final Model pModel) {

		System.out.println(pModel.iterator().next());
		try {
			Iterable<? extends Statement> it = new Iterable<Statement>() {

				public Iterator<Statement> iterator() {
					return pModel.iterator();
				}
			};
			System.out.println(pModel.iterator().next());
			repository.add(it);
			// repository.add(statement);

			repository.commit();
			System.out.println("subido");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo obtiene las categorias disponibles en los datos existentes
	 * 
	 * @return
	 */
	public String getDatosCategoria() {
		String queryCat = "select distinct ?Categoria { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?s rdf:type <http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria}}";
		ArrayList<String> resultadosParciales = new ArrayList<String>();
		String resultado = "";
		try {
			TupleQuery query = repository.prepareTupleQuery(QueryLanguage.SPARQL, queryCat);
			TupleQueryResult resultadoQuery = query.evaluate();
			String identificador = "";
			while (resultadoQuery.hasNext()) {
				resultado = resultadoQuery.next().toString();
				resultado = resultado.replace("\"", "");
				resultado = resultado.replace("^^<http://www.w3.org/2001/XMLSchema#string>", "");
				resultado = resultado.replace("[", "");
				resultado = resultado.replace("]", "");
				String[] result = resultado.toString().split("=");
				identificador = result[0];
				resultadosParciales.add(result[1]);
			}
			resultadosParciales.add(identificador);
			resultado = new GeneradorIndex().generarIndex2(resultadosParciales);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	// Categoria, Experiencia, Habilidades, Certificaciones,Idiomas, Universidad
	public String getPageData() {
		String[] querys = new String[6];
		querys[0] = "select distinct ?Categoria { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?s rdf:type <http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria}}";
		querys[1] = "select distinct ?Experiencia { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?persona <http://opendata.euskadi.eus/experience> ?s.?s <http://schema.org/name> ?Experiencia}}";
		querys[2] = "select distinct ?Habilidades { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?persona <http://opendata.euskadi.eus/skill> ?s.?s <http://schema.org/name> ?Habilidades}}";
		querys[3] = "select distinct ?Certificaciones { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?persona <http://opendata.euskadi.eus/certification> ?s.?s <http://schema.org/name> ?Certificaciones}}";
		querys[4] = "select distinct ?Idiomas { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?persona  <http://opendata.euskadi.eus/idioma> ?s.?s <http://schema.org/name> ?Idiomas}}";
		querys[5] = "select distinct ?Universidad { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?persona <http://opendata.euskadi.eus/education> ?s. ?s <http://schema.org/name> ?Universidad}}";
		ArrayList<ArrayList<String>> resultadosTotales = new ArrayList<ArrayList<String>>();
		ArrayList<String> resultadosParciales = new ArrayList<String>();
		try {
			for (int i = 0; i < querys.length; i++) {
				TupleQuery query = repository.prepareTupleQuery(QueryLanguage.SPARQL, querys[i]);
				TupleQueryResult resultadosQuery = query.evaluate();
				String identificador = "";
				while (resultadosQuery.hasNext()) {
					String resultado = resultadosQuery.next().toString();
					resultado = resultado.replace("\"", "");
					resultado = resultado.replace("^^<http://www.w3.org/2001/XMLSchema#string>", "");
					resultado = resultado.replace("[", "");
					resultado = resultado.replace("]", "");
					String[] result = resultado.toString().split("=");
					identificador = result[0];
					resultadosParciales.add(result[1]);
				}
				resultadosParciales.add(identificador);
				resultadosTotales.add(resultadosParciales);
				resultadosParciales = new ArrayList<String>();
				resultadosQuery.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String resultado = new GeneradorIndex().generarIndex(resultadosTotales);
		return resultado;
	}

	/**
	 * Metodo que a partir de las caracteristicas de los elementos que se le
	 * pasen por parametro obtiene el JSON adecuado de la base de datos y lo
	 * adecua al formato correcto
	 * 
	 * @param pCategoria
	 * @param pExperiencia
	 * @param pHabilidades
	 * @param pCertificaciones
	 * @param pIdiomas
	 * @param pUniversidad
	 * @return
	 */
	public String getGraphData(String[] pCategoria, String[] pExperiencia, String[] pHabilidades,
			String[] pCertificaciones, String[] pIdiomas, String[] pUniversidad) {
		String result = "";
		String query = "CONSTRUCT{" + "?persona ?tipo ?Categoria." + "?Categoria ?id ?nombreCategoria."
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
				+ "FILTER (?nombreIdioma IN ())" + "FILTER (?tipo = rdf:type )"
				+ "FILTER (?prefIdioma = <http://opendata.euskadi.eus/idioma> )"
				+ "FILTER (?habilidades = <http://opendata.euskadi.eus/skill> )"
				+ "FILTER (?prefExperiencia = <http://opendata.euskadi.eus/experience> )"
				+ "FILTER (?prefPuesto = <http://opendata.euskadi.eus/puesto> )"
				+ "FILTER (?prefCertif = <http://opendata.euskadi.eus/certification> )"
				+ "FILTER (?prefPersona = <http://schema.org/Person> )" + "}" + "}";

		query = completarFila("?nombreCategoria IN (", pCategoria, query);
		query = completarFila("?nombreExperiencia IN (", pExperiencia, query);
		query = completarFila("?nombreLenguajeProg IN (", pHabilidades, query);
		query = completarFila("?nombreCertificacion IN (", pCertificaciones, query);
		query = completarFila("?nombreIdioma IN (", pIdiomas, query);
		System.out.println(query);
		try {
			GraphQuery tupleQuery = repository.prepareGraphQuery(QueryLanguage.SPARQL, query);
			GraphQueryResult results = tupleQuery.evaluate();
			while (results.hasNext()) {
				result = result + results.next();
				result = result.replace(" ", "");
			}
			System.out.println(result);
			results.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Json json = new Json(result);
		result = json.parsearJSON();
		System.out.println(result);
		return result;
	}

	/**
	 * Obtiene las habilidades y certificaciones asociadas a la categoria
	 * 
	 * @param pCategoria
	 * @return
	 */
	public String getCategoriaCalCert(String[] pCategoria) {
		String query = "CONSTRUCT  {" + "?uriCategoria ?nombre ?nomCategoria."
				+ "?uriCategoria ?habilidades ?uriHabilidad." + " ?uriHabilidad ?nombre ?nomHabilidad."
				+ " ?uriCategoria ?certificaciones ?uriCertificacion." + " ?uriCertificacion ?nombre ?nomCertificacion."
				+ "}WHERE" + "	{" + "GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {"
				+ " ?uriCategoria ?tipo ?puesto." + "?uriCategoria ?nombre ?nomCategoria."
				+ "?uriCategoria ?habilidades ?uriHabilidad." + "?uriHabilidad ?nombre ?nomHabilidad."
				+ "?uriCategoria ?certificaciones ?uriCertificacion." + "?uriCertificacion ?nombre ?nomCertificacion."
				+ "FILTER(?" + "nomCategoria IN ())" + "FILTER (?puesto = <http://opendata.euskadi.eus/puesto> )"
				+ "FILTER (?habilidades = <http://opendata.euskadi.eus/skill> )"
				+ "FILTER (?certificaciones = <http://opendata.euskadi.eus/certification>)" + "}" + "}";
		String result = "";
		query = completarFila("?nomCategoria IN (", pCategoria, query);
		System.out.println(query);
		try {
			GraphQuery tupleQuery = repository.prepareGraphQuery(QueryLanguage.SPARQL, query);
			GraphQueryResult results = tupleQuery.evaluate();
			while (results.hasNext()) {
				result = result + results.next();
				result = result.replace(", ", ",");
			}
			results.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Json json = new Json(result);
		result = json.parsearJSON();
		return result;
	} 

	public String completarFila(String pPatron, String[] pConjunto, String pQuery) {
		for (int i = 0; i < pConjunto.length; i++) {
			if (i==pConjunto.length-1) {
				pQuery = pQuery.replace(pPatron, pPatron + "\"" + pConjunto[i] + "\"");

			} else {
				pQuery = pQuery.replace(pPatron, pPatron + ",\""  + pConjunto[i] + "\"" );
			}
		}
		return pQuery;
	}

}
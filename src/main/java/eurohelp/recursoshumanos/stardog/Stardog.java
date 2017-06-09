package eurohelp.recursoshumanos.stardog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import eurohelp.recursoshumanos.pagegenerator.GeneradorIndex;
import eurohelp.recursoshumanos.pagegenerator.Json;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

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

		try {
			Iterable<? extends Statement> it = new Iterable<Statement>() {

				public Iterator<Statement> iterator() {
					return pModel.iterator();
				}
			};
			repository.add(it);
			// repository.add(statement);

			repository.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// // Categoria, Experiencia, Habilidades, Certificaciones,Idiomas,
	// Universidad
	// public String getPageData() {
	// String[] querys = new String[6];
	// querys[0] = "select distinct ?Categoria { GRAPH
	// <http://opendata.eurohelp.es/dataset/recursos-humanos> {?s rdf:type
	// <http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name>
	// ?Categoria}}";
	// querys[1] = "select distinct ?Experiencia { GRAPH
	// <http://opendata.eurohelp.es/dataset/recursos-humanos> {?persona
	// <http://opendata.euskadi.eus/experience> ?s.?s <http://schema.org/name>
	// ?Experiencia}}";
	// querys[2] = "select distinct ?Habilidades { GRAPH
	// <http://opendata.eurohelp.es/dataset/recursos-humanos> {?persona
	// <http://opendata.euskadi.eus/skill> ?s.?s <http://schema.org/name>
	// ?Habilidades}}";
	// querys[3] = "select distinct ?Certificaciones { GRAPH
	// <http://opendata.eurohelp.es/dataset/recursos-humanos> {?persona
	// <http://opendata.euskadi.eus/certification> ?s.?s
	// <http://schema.org/name> ?Certificaciones}}";
	// querys[4] = "select distinct ?Idiomas { GRAPH
	// <http://opendata.eurohelp.es/dataset/recursos-humanos> {?persona
	// <http://opendata.euskadi.eus/idioma> ?s.?s <http://schema.org/name>
	// ?Idiomas}}";
	// querys[5] = "select distinct ?Universidad { GRAPH
	// <http://opendata.eurohelp.es/dataset/recursos-humanos> {?persona
	// <http://opendata.euskadi.eus/education> ?s. ?s <http://schema.org/name>
	// ?Universidad}}";
	// ArrayList<ArrayList<String>> resultadosTotales = new
	// ArrayList<ArrayList<String>>();
	// ArrayList<String> resultadosParciales = new ArrayList<String>();
	// try {
	// for (int i = 0; i < querys.length; i++) {
	// TupleQuery query = repository.prepareTupleQuery(QueryLanguage.SPARQL,
	// querys[i]);
	// TupleQueryResult resultadosQuery = query.evaluate();
	// String identificador = "";
	// while (resultadosQuery.hasNext()) {
	// String resultado = resultadosQuery.next().toString();
	// resultado = resultado.replace("\"", "");
	// resultado =
	// resultado.replace("^^<http://www.w3.org/2001/XMLSchema#string>", "");
	// resultado = resultado.replace("[", "");
	// resultado = resultado.replace("]", "");
	// String[] result = resultado.toString().split("=");
	// identificador = result[0];
	// resultadosParciales.add(result[1]);
	// }
	// resultadosParciales.add(identificador);
	// resultadosTotales.add(resultadosParciales);
	// resultadosParciales = new ArrayList<String>();
	// resultadosQuery.close();
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// String resultado = new GeneradorIndex().generarIndex(resultadosTotales);
	// return resultado;
	// }
	//
	// /**
	// * Metodo que a partir de las caracteristicas de los elementos que se le
	// * pasen por parametro obtiene el JSON adecuado de la base de datos y lo
	// * adecua al formato correcto
	// *
	// * @param pCategoria
	// * @param pExperiencia
	// * @param pHabilidades
	// * @param pCertificaciones
	// * @param pIdiomas
	// * @param pUniversidad
	// * @return
	// */
	// public String getGraphData(String[] pCategoria, String[] pExperiencia,
	// String[] pHabilidades,
	// String[] pCertificaciones, String[] pIdiomas, String[] pUniversidad) {
	// String result = "";
	// String query = "CONSTRUCT{" + "?persona ?tipo ?Categoria." + "?Categoria
	// ?id ?nombreCategoria."
	// + "?persona ?prefExperiencia ?Experiencia. " + "?Experiencia ?id
	// ?nombreExperiencia."
	// + "?persona ?habilidades ?lenguajeProg. " + "?lenguajeProg ?id
	// ?nombreLenguajeProg."
	// + "?persona ?prefCertif ?certificacion. " + "?certificacion ?id
	// ?nombreCertificacion."
	// + "?persona ?prefIdioma ?idioma. " + "?idioma ?id ?nombreIdioma." + "}"
	// + "where{ GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {"
	// + "?persona ?tipo ?prefPersona." + "?persona ?tipo ?Categoria." +
	// "?Categoria ?id ?nombreCategoria."
	// + "?Categoria ?tipo ?prefPuesto." + "?persona ?prefExperiencia
	// ?Experiencia."
	// + "?Experiencia ?id ?nombreExperiencia." + "?persona ?habilidades
	// ?lenguajeProg."
	// + "?lenguajeProg ?id ?nombreLenguajeProg." + "?persona ?prefCertif
	// ?certificacion."
	// + "?certificacion ?id ?nombreCertificacion." + "?persona ?prefIdioma
	// ?idioma."
	// + "?idioma ?id ?nombreIdioma." + "FILTER (?nombreCategoria IN ())" +
	// "FILTER (?nombreExperiencia IN ())"
	// + "FILTER (?nombreLenguajeProg IN ())" + "FILTER (?nombreCertificacion IN
	// ())"
	// + "FILTER (?nombreIdioma IN ())" + "FILTER (?tipo = rdf:type )"
	// + "FILTER (?prefIdioma = <http://opendata.euskadi.eus/idioma> )"
	// + "FILTER (?habilidades = <http://opendata.euskadi.eus/skill> )"
	// + "FILTER (?prefExperiencia = <http://opendata.euskadi.eus/experience> )"
	// + "FILTER (?prefPuesto = <http://opendata.euskadi.eus/puesto> )"
	// + "FILTER (?prefCertif = <http://opendata.euskadi.eus/certification> )"
	// + "FILTER (?prefPersona = <http://schema.org/Person> )" + "}" + "}";
	//
	// query = completarFila("?nombreCategoria IN (", pCategoria, query);
	// query = completarFila("?nombreExperiencia IN (", pExperiencia, query);
	// query = completarFila("?nombreLenguajeProg IN (", pHabilidades, query);
	// query = completarFila("?nombreCertificacion IN (", pCertificaciones,
	// query);
	// query = completarFila("?nombreIdioma IN (", pIdiomas, query);
	// try {
	// GraphQuery tupleQuery =
	// repository.prepareGraphQuery(QueryLanguage.SPARQL, query);
	// GraphQueryResult results = tupleQuery.evaluate();
	// while (results.hasNext()) {
	// result = result + results.next();
	// result = result.replace(" ", "");
	// }
	// System.out.println("----------------" + result);
	// results.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// System.out.println(result);
	// Json json = new Json(result);
	// result = json.parsearJSON();
	// System.out.println(result);
	// return result;
	// }

	/**
	 * Metodo obtiene las categorias disponibles en los datos existentes
	 * 
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 * @throws ParseException
	 * @throws MalformedTemplateNameException
	 * @throws TemplateNotFoundException
	 */
	public String getIndexData() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException,
			IOException, TemplateException {
		String queryCat = "select distinct ?Categoria { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?s rdf:type <http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria}}";
		String result = new GeneradorIndex().generarIndex(getArrayListByData(queryCat));
		return result;
	}


	/**
	 * Recoge los datos referentes a las habilidades y las certificaciones dada
	 * una o varias categorias
	 * 
	 * @param pCategoria
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 * @throws ParseException
	 * @throws MalformedTemplateNameException
	 * @throws TemplateNotFoundException
	 */
	public String getIndexData(String[] pCategoria, String[] pHabilidades, String[] pCertificaciones)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		String queryCat = "select distinct ?Categoria { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> "
				+ "{?s rdf:type <http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria}}";
		String queryHabil = fillQuery("?Categoria IN (", pCategoria,
				"select distinct ?Habilidad { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?s "
						+ "rdf:type <http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria. ?persona "
						+ "<http://opendata.euskadi.eus/skill> ?habilidad. ?habilidad <http://schema.org/name> ?Habilidad.  "
						+ "FILTER(?Categoria IN ()}}");
		String queryCert = fillQuery("?Certificacion IN (", pCategoria,
				"select distinct ?Certificacion{ GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?s rdf:type "
						+ "<http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria. ?persona "
						+ "<http://opendata.euskadi.eus/certification> ?certificacion. ?certificacion <http://schema.org/name> "
						+ "?Certificacion FILTER(?Categoria IN ())}}");
		String result = new GeneradorIndex().generarIndex(getArrayListByData(queryCat),
				getArrayListByData(queryHabil), getArrayListByData(queryCert));
		return result;
	}
	
	/**
	 * Recoge los idiomas, las universidades en las que ha estudiado y la
	 * experiencia dada una categoria, unas habilidades y unas certificaciones
	 * 
	 * @param pCategoria
	 * @param pHabilidades
	 * @param pCertificaciones
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 * @throws ParseException
	 * @throws MalformedTemplateNameException
	 * @throws TemplateNotFoundException
	 */
	public String getIndexData(String[] pCategoria) throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		// TODO pendientisimooooooooooooooooo
		ArrayList<String> querys = new ArrayList<String>();
		querys.add("select distinct ?Categoria { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> "
				+ "{?s rdf:type <http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria}}");
		querys.add(fillQuery("?Categoria IN (", pCategoria,
				"select distinct ?Habilidad { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?s "
						+ "rdf:type <http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria. ?persona "
						+ "<http://opendata.euskadi.eus/skill> ?habilidad. ?habilidad <http://schema.org/name> ?Habilidad.  "
						+ "FILTER(?Categoria IN ()}}"));
		querys.add(fillQuery("?Certificacion IN (", pCategoria,
				"select distinct ?Certificacion{ GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?s rdf:type "
						+ "<http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria. ?persona "
						+ "<http://opendata.euskadi.eus/certification> ?certificacion. ?certificacion <http://schema.org/name> "
						+ "?Certificacion FILTER(?Categoria IN ())}}"));
		String result = new GeneradorIndex().generarIndex(getArrayListByData(querys.get(0)),
				getArrayListByData(querys.get(1)), getArrayListByData(querys.get(2)));
		return result;
	}

	

	public List<String> getArrayListByData(String pQuery) {
		List<String> results = new ArrayList<String>();
		String resultado = "";
		try {
			TupleQuery query = repository.prepareTupleQuery(QueryLanguage.SPARQL, pQuery);
			TupleQueryResult resultadoQuery = query.evaluate();
			while (resultadoQuery.hasNext()) {
				resultado = resultadoQuery.next().toString();
				resultado = resultado.replace("\"", "");
				resultado = resultado.replace("^^<http://www.w3.org/2001/XMLSchema#string>", "");
				resultado = resultado.replace("[", "");
				resultado = resultado.replace("]", "");
				String[] result = resultado.toString().split("=");
				results.add(result[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * Obtiene las habilidades y certificaciones asociadas a la categoria
	 * seleccionada por el usuario
	 * 
	 * @param pCategoria
	 * @return json para la representacion en forma de grafo de la informacion
	 */
	public String getJson(String[] pCategoria) {
		String query = "CONSTRUCT  { ?uriCategoria ?nombre ?nomCategoria."
				+ "?uriCategoria ?habilidades ?uriHabilidad. ?uriHabilidad ?nombre ?nomHabilidad."
				+ " ?uriCategoria ?certificaciones ?uriCertificacion. ?uriCertificacion ?nombre ?nomCertificacion."
				+ "}WHERE{GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {"
				+ " ?uriCategoria ?tipo ?puesto. ?uriCategoria ?nombre ?nomCategoria."
				+ "?uriCategoria ?habilidades ?uriHabilidad. ?uriHabilidad ?nombre ?nomHabilidad."
				+ "?uriCategoria ?certificaciones ?uriCertificacion. ?uriCertificacion ?nombre ?nomCertificacion."
				+ "FILTER(?nomCategoria IN ()) FILTER (?puesto = <http://opendata.euskadi.eus/puesto> )"
				+ "FILTER (?habilidades = <http://opendata.euskadi.eus/skill> )"
				+ "FILTER (?certificaciones = <http://opendata.euskadi.eus/certification>)}}";
		String result = "";
		query = fillQuery("?nomCategoria IN (", pCategoria, query);
		System.out.println(query);
		try {
			GraphQuery tupleQuery = repository.prepareGraphQuery(QueryLanguage.SPARQL, query);
			GraphQueryResult results = tupleQuery.evaluate();
			while (results.hasNext()) {
				Statement statement = results.next();
				String e = statement.toString().replace(", ", ",");
				if (!result.contains(e)) {
					result = result + e;
				}
			}
			results.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Json json = new Json(result);
		result = json.parsearJSON();
		return result;
	}

	/**
	 * 
	 * 
	 * @param pCategoria
	 * @return
	 */
	public String getJson(String[] pCategoria, String[] pHabilidades, String[] pCertificaciones) {
		// TODO CONSTRUIR JSON
		ArrayList<ArrayList<String>> resultadosGlobales = new ArrayList<ArrayList<String>>();
		ArrayList<String> resultadosParciales = new ArrayList<String>();
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
		query = fillQuery("?nomCategoria IN (", pCategoria, query);
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

	public String getJson(String[] pCategoria, String[] pHabilidades, String[] pCertificaciones, String[] pIdiomas,
			String[] pUniversidad, String pExperiencia) {
		// TODO

		ArrayList<ArrayList<String>> resultadosGlobales = new ArrayList<ArrayList<String>>();
		ArrayList<String> resultadosParciales = new ArrayList<String>();
		String query = "CONSTRUCT  {" + "?uriCategoria ?nombre ?nomCategoria."
				+ "?uriCategoria ?habilidades ?uriHabilidad." + " ?uriHabilidad ?nombre ?nomHabilidad."
				+ " ?uriCategoria ?certificaciones ?uriCertificacion." + " ?uriCertificacion ?nombre ?nomCertificacion."
				+ "}WHERE" + "	{" + "GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {"
				+ " ?uriCategoria ?tipo ?puesto." + "?uriCategoria ?nombre ?nomCategoria."
				+ "?uriCategoria ?habilidades ?uriHabilidad." + "?uriHabilidad ?nombre ?nomHabilidad."
				+ "?uriCategoria ?certificaciones ?uriCertificacion." + "?uriCertificacion ?nombre ?nomCertificacion."
				+ "FILTER(?nomCategoria IN ())" + "FILTER (?puesto = <http://opendata.euskadi.eus/puesto> )"
				+ "FILTER (?habilidades = <http://opendata.euskadi.eus/skill> )"
				+ "FILTER (?certificaciones = <http://opendata.euskadi.eus/certification>)" + "}" + "}";
		String result = "";
		query = fillQuery("?nomCategoria IN (", pCategoria, query);
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

	public String fillQuery(String pPatron, String[] pConjunto, String pQuery) {
		for (int i = 0; i < pConjunto.length; i++) {
			if (i == pConjunto.length - 1) {
				pQuery = pQuery.replace(pPatron, pPatron + "\"" + pConjunto[i] + "\"");

			} else {
				pQuery = pQuery.replace(pPatron, pPatron + ",\"" + pConjunto[i] + "\"");
			}
		}
		return pQuery;
	}

}
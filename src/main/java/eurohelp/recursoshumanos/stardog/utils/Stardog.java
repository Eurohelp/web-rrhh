package eurohelp.recursoshumanos.stardog.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

/**
 * 
 * @author Mishel Uchuari, 28 jul. 2017
 */
public class Stardog {
	private RepositoryConnection repository;
	private String serverURL;

	/**
	 * @throws RepositoryException
	 * @throws IOException
	 * 
	 */
	public Stardog()  {
		serverURL = "http://ckan.eurohelp.es:5820";
		// Repository stardogRepository = new StardogRepository(
		// ConnectionConfiguration.to("LODgenAppTurismo").server(serverURL).credentials(PropertiesManager.getINSTANCE().getProperty("user"),PropertiesManager.getINSTANCE().getProperty("password")));

		Repository stardogRepository = new StardogRepository(ConnectionConfiguration.to("LODgenAppTurismo")
				.server(serverURL).credentials("admin", "ctxakurra"));
		try {
			stardogRepository.initialize();
			repository = stardogRepository.getConnection();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

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
		String result = new GeneradorIndex().generarIndex(getListByData(queryCat));
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
	public String getIndexData(String[] pCategoria) throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		String queryCat = "select distinct ?Categoria { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> "
				+ "{?s rdf:type <http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria}}";
		String queryHabil = fillQuery("?Categoria IN (", pCategoria,
				"select distinct ?Habilidad { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?s "
						+ "rdf:type <http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria. ?s "
						+ "<http://opendata.euskadi.eus/skill> ?habilidad. ?habilidad <http://schema.org/name> ?Habilidad.  "
						+ "FILTER(?Categoria IN ())}}");
		String queryCert = fillQuery("?Categoria IN (", pCategoria,
				"select distinct ?Certificacion{ GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?s rdf:type "
						+ "<http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria. ?s "
						+ "<http://opendata.euskadi.eus/certification> ?certificacion. ?certificacion <http://schema.org/name> "
						+ "?Certificacion FILTER(?Categoria IN ())}}");

		String result = new GeneradorIndex().generarIndex(getListByData(queryCat), getListByData(queryHabil),
				getListByData(queryCert));

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
	public String getIndexData(String[] pCategoria, String[] pHabilidades, String[] pCertificaciones)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		String queryCat = "select distinct ?Categoria { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> "
				+ "{?s rdf:type <http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria}}";
		String queryHabil = fillQuery("?Categoria IN (", pCategoria,
				"select distinct ?Habilidad { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?s "
						+ "rdf:type <http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria. ?s "
						+ "<http://opendata.euskadi.eus/skill> ?habilidad. ?habilidad <http://schema.org/name> ?Habilidad.  "
						+ "FILTER(?Categoria IN ())}}");
		String queryCert = fillQuery("?Categoria IN (", pCategoria,
				"select distinct ?Certificacion{ GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?s rdf:type "
						+ "<http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria. ?s "
						+ "<http://opendata.euskadi.eus/certification> ?certificacion. ?certificacion <http://schema.org/name> "
						+ "?Certificacion FILTER(?Categoria IN ())}}");
		String queryIdioma = fillQuery("?Categoria IN (", pCategoria,
				"select distinct ?Idioma{ GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?s rdf:type "
						+ "<http://opendata.euskadi.eus/puesto>. ?person rdf:type ?s. ?s <http://schema.org/name> ?Categoria."
						+ "?person <http://opendata.euskadi.eus/idioma> ?idioma.?idioma <http://schema.org/name> "
						+ "?Idioma FILTER(?Categoria IN ())}}");

		String queryUniversidad = fillQuery("?Categoria IN (", pCategoria,
				"select distinct ?Estudios{ GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?s rdf:type"
						+ "<http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria. "
						+ "?person rdf:type ?s."
						+ "?person <http://opendata.euskadi.eus/education> ?estudios.?estudios <http://schema.org/name> "
						+ "?Estudios FILTER(?Categoria IN ())}}");
		String queryExperiencia = fillQuery("?Categoria IN (", pCategoria,
				"select distinct ?Experiencia{ GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {?s rdf:type "
						+ "<http://opendata.euskadi.eus/puesto>. ?s <http://schema.org/name> ?Categoria. "
						+ "?person rdf:type ?s."
						+ "?person <http://opendata.euskadi.eus/experience> ?experiencia.?experiencia <http://schema.org/name>"
						+ "?Experiencia FILTER(?Categoria IN ())}}");
		String result = new GeneradorIndex().generarIndex(getListByData(queryCat), getListByData(queryHabil),
				getListByData(queryCert), getListByData(queryIdioma), getListByData(queryUniversidad),
				getListByData(queryExperiencia));
		return result;
	}

	public List<String> getListByData(String pQuery) {
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
	 * Obtiene el json de acuerdo a las caracteristicas seleccionadas por el
	 * usuario
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
		Json json = new Json();
		result = json.parsearJSON(result);

		return result;
	}
	
	public String getResourceJson(String pRecurso) {
		String query = "construct{?nomRecurso ?p ?o} where{graph<http://opendata.eurohelp.es/dataset/recursos-humanos>{?nomRecurso ?p ?o}FILTER(?nomRecurso = <recursoPeticion>)}";
		String result = "";
		query=query.replace("recursoPeticion", pRecurso);
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
		if (result == "") {
			result = "json vacio";
		} else {
			Json json = new Json();
			result = json.parsearJSON(result);
		}
		return result;
	}

	/**
	 * Obtiene el json de acuerdo a las caracteristicas seleccionadas por el
	 * usuario
	 * 
	 * @param pCategoria
	 * @param pHabilidades
	 * @param pCertificaciones
	 * @return json para la representacion en forma de grafo de la informacion
	 */
	public String getJson(String[] pCategoria, String[] pHabilidades, String[] pCertificaciones) {
		String query = "CONSTRUCT  {" + "?person ?idiomas ?uriIdioma. ?uriIdioma ?nombre ?nomIdioma."
				+ "?person ?universidades ?uriUniversidades. ?uriUniversidades ?nombre ?nomUniversidades."
				+ "?person ?experiencia ?uriExperiencia. ?uriExperiencia ?nombre ?nomExperiencia."

				+ "}WHERE { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {"
				+ "?uriCategoria ?tipo ?puesto. ?uriCategoria ?nombre ?nomCategoria."
				+ "?uriCategoria ?habilidades ?uriHabilidad. ?uriHabilidad ?nombre ?nomHabilidad."
				+ "?uriCategoria ?certificaciones ?uriCertificacion. ?uriCertificacion ?nombre ?nomCertificacion."
				+ "?person ?tipo ?persona." + "?person ?idiomas ?uriIdioma. ?uriIdioma ?nombre ?nomIdioma."
				+ "?person ?universidades ?uriUniversidades. ?uriUniversidades ?nombre ?nomUniversidades."
				+ "?person ?experiencia ?uriExperiencia. ?uriExperiencia ?nombre ?nomExperiencia. ?person rdf:type ?uriCategoria."

				+ "FILTER(?nomCategoria IN ()) FILTER(?nomHabilidad IN ())" + "FILTER(?nomCertificacion IN ())"

				+ "FILTER (?puesto = <http://opendata.euskadi.eus/puesto> )"
				+ "FILTER (?persona = <http://schema.org/Person> )"
				+ "FILTER (?habilidades = <http://opendata.euskadi.eus/skill> )  "
				+ "FILTER (?certificaciones = <http://opendata.euskadi.eus/certification>)"
				+ "FILTER (?idiomas = <http://opendata.euskadi.eus/idioma>)		"
				+ "FILTER (?universidades = <http://opendata.euskadi.eus/education>)		"
				+ "FILTER (?experiencia = <http://opendata.euskadi.eus/experience>)" + "}}";
		query = fillQuery("?nomCategoria IN (", pCategoria, query);
		query = fillQuery("?nomHabilidad IN (", pHabilidades, query);
		query = fillQuery("?nomCertificacion IN (", pCertificaciones, query);
		String result = getStringByData(query);
		return result;
	}

	/**
	 * Obtiene el json de acuerdo a las caracteristicas seleccionadas por el
	 * usuario
	 * 
	 * @param pCategoria
	 * @param pHabilidades
	 * @param pCertificaciones
	 * @param pIdiomas
	 * @param pUniversidad
	 * @param pExperiencia
	 * @return json para la representacion en forma de grafo de la informacion
	 */
	public String getJson(String[] pCategoria, String[] pHabilidades, String[] pCertificaciones, String[] pIdiomas,
			String[] pUniversidad, String[] pExperiencia) {
		String query = "CONSTRUCT  {"
				+ "?person ?universidades ?uriUniversidades. ?uriUniversidades ?nombre ?nomUniversidades."
				+ "?person ?experiencia ?uriExperiencia. ?uriExperiencia ?nombre ?nomExperiencia."
				+ "?person ?nombre ?nombrePersona."
				+ "}WHERE { GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {"
				+ "?uriCategoria ?tipo ?puesto. ?uriCategoria ?nombre ?nomCategoria."
				+ "?uriCategoria ?habilidades ?uriHabilidad. ?uriHabilidad ?nombre ?nomHabilidad."
				+ "?uriCategoria ?certificaciones ?uriCertificacion. ?uriCertificacion ?nombre ?nomCertificacion."
				+ "?person ?tipo ?persona. ?person ?nombre ?nombrePersona."
				+ "?person ?idiomas ?uriIdioma. ?uriIdioma ?nombre ?nomIdioma."
				+ "?person ?universidades ?uriUniversidades. ?uriUniversidades ?nombre ?nomUniversidades."
				+ "?person ?experiencia ?uriExperiencia. ?uriExperiencia ?nombre ?nomExperiencia."
				+ "FILTER(?nomCategoria IN ()) " + "FILTER(?nomHabilidad IN ())" + "FILTER(?nomCertificacion IN ())"
				+ "FILTER(?nomIdioma IN ())" + "FILTER(?nomUniversidades IN ())" + "FILTER(?nomExperiencia IN ())"
				+ "FILTER (?puesto = <http://opendata.euskadi.eus/puesto> )"
				+ "FILTER (?persona = <http://schema.org/Person> )"
				+ "FILTER (?habilidades = <http://opendata.euskadi.eus/skill>) "
				+ "FILTER (?certificaciones = <http://opendata.euskadi.eus/certification>)"
				+ "FILTER (?idiomas = <http://opendata.euskadi.eus/idioma>)"
				+ "FILTER (?universidades = <http://opendata.euskadi.eus/education>)"
				+ "FILTER (?experiencia = <http://opendata.euskadi.eus/experience>)" + "}}";
		query = fillQuery("?nomCategoria IN (", pCategoria, query);
		query = fillQuery("?nomHabilidad IN (", pHabilidades, query);
		query = fillQuery("?nomCertificacion IN (", pCertificaciones, query);
		query = fillQuery("?nomIdioma IN (", pIdiomas, query);
		query = fillQuery("?nomUniversidades IN (", pUniversidad, query);
		query = fillQuery("?nomExperiencia IN (", pExperiencia, query);
		String result = getStringByData(query);
		return result;
	}

	/**
	 * Ejecuta la consulta
	 * 
	 * @param pQuery
	 * @return resultado de la consulta
	 */
	public String getStringByData(String pQuery) {
		String result = "";
		try {
			GraphQuery tupleQuery = repository.prepareGraphQuery(QueryLanguage.SPARQL, pQuery);
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
		if (result == "") {
			result = "json vacio";
		} else {
			Json json = new Json();
			result = json.parsearJSON(result);
		}
		return result;
	}

	/**
	 * Rellena la query para realizar la consulta
	 * 
	 * @param pPatron
	 * @param pConjunto
	 * @param pQuery
	 */
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

package stardog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.openrdf.model.Model;
import org.openrdf.model.Statement;
import org.openrdf.query.GraphQuery;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.resultio.QueryResultIO;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.jsonld.JSONLDWriter;
import org.openrdf.rio.n3.N3Writer;
import org.openrdf.rio.ntriples.NTriplesWriter;

import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.sesame.StardogRepository;

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

	// Categoria, Experiencia, Habilidades, Certificaciones,Idiomas, Universidad
	public ArrayList<ArrayList<String>> getPageData() {
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
		return resultadosTotales;
	}

	// Categoria, Experiencia, Habilidades, Certificaciones, Idiomas,
	// Universidad
	public String getGraphData(String[] pCategoria, String[] pExperiencia, String[] pHabilidades,
			String[] pCertificaciones, String[] pIdiomas, String[] pUniversidad) {
		String result="";
		String query = "CONSTRUCT{" + "?persona ?tipo ?prefPersona. "
				+ "?persona ?tipo ?Categoria."
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
		File file = null;
		try {
			file = new File("./JSON/archivoJSON.json");
			OutputStream os = new FileOutputStream(file);
			GraphQuery tupleQuery = repository.prepareGraphQuery(QueryLanguage.SPARQL, query);
			GraphQueryResult results = tupleQuery.evaluate();
//			N3Writer  nWritter = new N3Writer(os);
//			nWritter.startRDF();
//			while (results.hasNext()) {
//				nWritter.handleStatement(results.next());
//			}
//			nWritter.endRDF();
			while (results.hasNext()){	
			result=result+results.next();
			result=result.replace(" ","");
			}
			System.out.println(result);
			results.close();
			file.deleteOnExit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Json json = new Json(result); 
		result = json.parsearJSON();
		System.out.println(result);
		return result;
	}

	public String completarFila(String pPatron, String[] pConjunto, String pQuery) {
		for (int i = 0; i < pConjunto.length; i++) {
			if (i + 1 >= pConjunto.length) {
				pQuery = pQuery.replace(pPatron, pPatron + "\"" + pConjunto[i] + "\"");
			} else {
				pQuery = pQuery.replace(pPatron, pPatron + "\"" + pConjunto[i] + "\"" + ",");
			}
		}
		return pQuery;
	}

}

package stardog;

import java.util.ArrayList;
import java.util.Iterator;

import org.openrdf.model.Model;
import org.openrdf.model.Statement;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.sesame.StardogRepository;

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
				String identificador="";
				while (resultadosQuery.hasNext()) {
					String resultado = resultadosQuery.next().toString();
					resultado = resultado.replace("\"", "");
					resultado = resultado.replace("^^<http://www.w3.org/2001/XMLSchema#string>", "");
					resultado = resultado.replace("[", "");
					resultado = resultado.replace("]", "");
					String[] result = resultado.toString().split("=");
					identificador=result[0];
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
}

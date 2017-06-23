package eurohelp.recursoshumanos.stardog;


import org.openrdf.repository.RepositoryException;



public class main {
	
	public static void main(String[] args) throws RepositoryException {
		Stardog e = new Stardog();
		String[] es = {"Analista"};
		System.out.println(e.getJson(es));
	}
}
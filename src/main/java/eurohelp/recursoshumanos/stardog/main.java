package eurohelp.recursoshumanos.stardog;

import org.openrdf.repository.RepositoryException;

public class main {

	public static void main(String[] args) throws RepositoryException {
		// TODO Auto-generated method stub
		Stardog st = new Stardog();
		String [] categoria = {"Analista"};
		st.getJson(categoria);
		//System.out.println(		st.getJson(categoria));
	}

}

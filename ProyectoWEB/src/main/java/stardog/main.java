package stardog;

import org.openrdf.repository.RepositoryException;

import code.GeneradorIndex;

public class main {
	public static void main(String[] args) throws RepositoryException {
//		GeneradorIndex gi = new GeneradorIndex();
//		gi.generarIndex();
		Stardog st = new Stardog();
		String[] pCategoria ={"Jefe de proyecto"};
		st.getDatosCategoria();
		String[] pExperiencia ={"Eurohelp Consulting"};
		String[] pHabilidades ={"HTML"};
		String[] pCertificaciones ={"ISTQB"};
		String[] pIdiomas ={"Spanish"};
		String[] pUniversidad ={"UPV-EHU"};
		//System.out.println(st.getGraphData(pCategoria, pExperiencia, pHabilidades, pCertificaciones, pIdiomas, pUniversidad));	
	}
}
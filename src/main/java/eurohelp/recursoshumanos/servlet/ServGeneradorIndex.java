package eurohelp.recursoshumanos.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openrdf.repository.RepositoryException;

import eurohelp.recursoshumanos.stardog.Stardog;
import freemarker.template.TemplateException;

public class ServGeneradorIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServGeneradorIndex() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Stardog stardog = null;
		String[] categoria = request.getParameterValues("Categoria");
		String[] habilidades = request.getParameterValues("Habilidades");
		String[] certificaciones = request.getParameterValues("Certificaciones");
		String datos = "";
		try {
			stardog = new Stardog();
			if (categoria == null) {
				datos = stardog.getIndexData();
			} else if (categoria != null && habilidades == null && certificaciones == null) {
				datos = stardog.getIndexData(categoria);
			} else if (categoria != null && habilidades != null && certificaciones != null) {
				datos = stardog.getIndexData(categoria, habilidades, certificaciones);
			}
		} catch (RepositoryException | TemplateException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(datos);
	}
}

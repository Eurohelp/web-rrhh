package eurohelp.recursoshumanos.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openrdf.repository.RepositoryException;

import eurohelp.recursoshumanos.stardog.Stardog;

/**
 * Servlet implementation class ServGetJson
 */
public class ServGetJson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServGetJson() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] categoria = request.getParameterValues("Categoria");
		String[] habilidades = request.getParameterValues("Habilidades");
		String[] certificaciones = request.getParameterValues("Certificaciones");
		String[] idiomas = request.getParameterValues("Idioma");
		String[] universidad = request.getParameterValues("Universidad");
		String[] experiencia = request.getParameterValues("Experiencia");
		System.out.println("Cambio minimo");
		try {
			Stardog stardog = new Stardog();
			String json = "";
			// si todos estan llenos
			if (categoria != null && habilidades != null && certificaciones != null && idiomas != null
					&& universidad != null && experiencia != null) {
				json = stardog.getJson(categoria, habilidades, certificaciones, idiomas, universidad, experiencia);
			} // si estan llenos la categoria la experiencia y las habilidades
			else if (categoria != null && certificaciones != null && habilidades != null) {
				json = stardog.getJson(categoria, habilidades, certificaciones);
			} else if (categoria != null) {
				json = stardog.getJson(categoria);
			}
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}
}
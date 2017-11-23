package eurohelp.recursoshumanos.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openrdf.repository.RepositoryException;

import eurohelp.recursoshumanos.stardog.utils.Stardog;

/**
 * 
 * @author Mishel Uchuari, 28 jul. 2017
 */
public class ServGetJson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServGetJson() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * Obtiene el json con los resultados de la consulta para general el grafo
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Stardog stardog = new Stardog();
		String json = "";
		if (request.getParameter("accion") == null) {
			String[] categoria = request.getParameterValues("Categoria");
			String[] habilidades = request.getParameterValues("Habilidades");
			String[] certificaciones = request.getParameterValues("Certificaciones");
			String[] idiomas = request.getParameterValues("Idioma");
			String[] universidad = request.getParameterValues("Universidad");
			String[] experiencia = request.getParameterValues("Experiencia");
			if (categoria != null && habilidades != null && certificaciones != null && idiomas != null
					&& universidad != null && experiencia != null) {
				json = stardog.getJson(categoria, habilidades, certificaciones, idiomas, universidad, experiencia);
			} else if (categoria != null && certificaciones != null && habilidades != null) {
				json = stardog.getJson(categoria, habilidades, certificaciones);
			} else if (categoria != null) {
				json = stardog.getJson(categoria);
			}
			
		} else {
			String nombreRecurso = request.getParameter("recurso");
			json = stardog.getResourceJson(nombreRecurso);
		}
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
	}
}
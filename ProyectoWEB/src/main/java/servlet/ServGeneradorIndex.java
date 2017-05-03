package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openrdf.repository.RepositoryException;

import code.GeneradorIndex;
import stardog.Stardog;

public class ServGeneradorIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServGeneradorIndex() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Stardog stardog = null;
		try {
			stardog = new Stardog();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String datos = stardog.getPageData();
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(datos);
	}

	private void response(HttpServletResponse response, String datos) throws IOException {
		PrintWriter out = response.getWriter();
		out.println(datos);
		System.out.println(datos);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import code.GeneradorIndex;
import stardog.Stardog;

public class ServGeneradorIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServGeneradorIndex() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		Stardog stardog = new Stardog();
//		ArrayList<ArrayList<String>> datos = stardog.getPageData();
		GeneradorIndex gen = new GeneradorIndex();
		String data = "Hello World!";
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(data);
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

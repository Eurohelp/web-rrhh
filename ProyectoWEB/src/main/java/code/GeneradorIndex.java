package code;

import java.util.ArrayList;

public class GeneradorIndex {
	
	// Categoria, Experiencia, Habilidades, Certificaciones,Idiomas, Universidad
	public String generarIndex(ArrayList<ArrayList<String>> pLista){

		String html="<!DOCTYPE html>\n"
			+"<html>\n"
			+"<meta charset=\"UTF-8\">"
			+"<head>\n"
			+"<script src=\"http://code.jquery.com/jquery-latest.min.js\"></script>"
			+"<script src=\"http://d3js.org/d3.v3.min.js\"></script>\n"
		    +"<script src=\"<%=request.getContextPath()%>/js/d3sparql.js\"></script>\n"
			+"<script src=\"<%=request.getContextPath()%>/js/script.js\"></script>\n"
		    +"<script>$.get(\'myservlet\', function(data) {alert(data);});</script>"
			+"<meta charset=UTF-8 />\n"
			+"<title>Insert title here</title>\n"
			+"</head>\n"
			+"<body>\n"
			+"<form name=\"form\" style=\"display:block\">\n"
			+"<center>\n"
			+"<table border>\n"
			+ "<TR>"
			+"<TH>Pulse aquí:</TH>\n"
			+"<TD ALIGN=CENTER>\n"
			+"<input type=\"submit\" onclick=\"d3sparql.ocultarFormulario()\" value=\"Ocultar tabla\">\n"
			+"<input type=\"reset\" value=\"Buscar\"></TD>\n"
			+"</table>\n"
			+"</center>\n"
			+"</form>\n"
			+"<div id=\"result\"></div>\n"
			+"</body>\n"
			+"</html>\n";
		String aAnadir="\n<TD>";
		for (int i=pLista.size()-1;i>-1;i--) {
		int inicio=html.indexOf("<table border>\n");
		String cabecera="<TR>\n"
				+"   <TD>"+pLista.get(i).get(pLista.get(i).size()-1)+":</TD>\n";
		html=html.substring(0,inicio+"<table border>\n".length())+cabecera+html.substring(inicio+"<table border>\n".length(), html.length());
			for (int j=pLista.get(i).size()-2;j>-1;j--) {
			inicio=html.indexOf("</TD>\n");
			aAnadir	=aAnadir.substring(0, aAnadir.length())+"\n<INPUT TYPE=\"checkbox\" name=\""+pLista.get(i).get(j)+"\">" + pLista.get(i).get(j)  ;
		}
		html = html.substring(0,inicio+"</TD>\n".length()) + aAnadir +  "</TD>\n"+ html.substring(inicio+"</TD>\n".length(), html.length());
		aAnadir="\n<TD>";
	}
		return html;
	}
}

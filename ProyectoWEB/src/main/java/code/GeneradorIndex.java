package code;

import java.util.ArrayList;

public class GeneradorIndex {
	
	// Categoria, Experiencia, Habilidades, Certificaciones,Idiomas, Universidad
	public String generarIndex(ArrayList<ArrayList<String>> pLista){

		String html=
			"<form name=\"form\" id=\"form\" method=\"post\" >\n"
			+"<center>\n"
			+"<table border>\n"
			+ "<TR>"
			+"<TH>Pulse aquí:</TH>\n"
			+"<TD ALIGN=CENTER>\n"
	        +"<button class=\"btn\" type=\"button\" onclick=\"mishelle()\">Hide</button>"
			+"</table>\n"
			+"</center>\n"
			+"</form>\n"
			+"<div id=\"result\" align=center></div>\n";
		String aAnadir="\n<TD>";
		for (int i=pLista.size()-1;i>-1;i--) {
		int inicio=html.indexOf("<table border>\n");
		String cabecera="<TR>\n"
				+"   <TD>"+pLista.get(i).get(pLista.get(i).size()-1)+":</TD>\n";
		html=html.substring(0,inicio+"<table border>\n".length())+cabecera+html.substring(inicio+"<table border>\n".length(), html.length());
			for (int j=pLista.get(i).size()-2;j>-1;j--) {
			inicio=html.indexOf("</TD>\n");
			aAnadir	=aAnadir.substring(0, aAnadir.length())+"\n<INPUT TYPE=\"checkbox\" name=\""+pLista.get(i).get(pLista.get(i).size()-1)+"\" value=\""+pLista.get(i).get(j)+ "\">" + pLista.get(i).get(j)  ;
		}
		html = html.substring(0,inicio+"</TD>\n".length()) + aAnadir +  "</TD>\n"+ html.substring(inicio+"</TD>\n".length(), html.length());
		aAnadir="\n<TD>";
	}
		return html;
	}
	
	// Categoria, Experiencia, Habilidades, Certificaciones,Idiomas, Universidad
	public String generarIndex2(ArrayList<String> pLista){
		String html=
				"<form name=\"form\" id=\"form\" method=\"post\" >\n"
				+"<center>\n"
				+"<table border>\n";
			String aAnadir="\n<TD>";
			String cabecera="<TR>\n"
					+"   <TD>"+pLista.get(pLista.size()-1)+":</TD>\n";
			html=html+cabecera;
			for (int i=pLista.size()-2;i>-1;i--) {
				int inicio=html.indexOf("</TD>\n");
				aAnadir	=aAnadir+"\n<INPUT TYPE=\"checkbox\" name=\""+pLista.get(pLista.size()-1)+"\" value=\""+pLista.get(i)+ "\">"+pLista.get(i) +"\n";
				html = html.substring(0,inicio+"</TD>\n".length()) + aAnadir ;
		}
			html=html+"\n</TD>"
					+"\n<TR>"
					+"<TH>Pulse aquí:</TH>\n"
					+"<TD ALIGN=CENTER>\n"
			        +"<button class=\"btn\" type=\"button\" onclick=\"mishelle()\">Hide</button>"
					+"</table>\n"
					+"</center>\n"
					+"</form>\n"
					+"<div id=\"result\" align=center></div>\n";
			System.out.println(html);
			return html;
	}
}

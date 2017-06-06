package eurohelp.recursoshumanos.stardog;

public class segundoMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String query = "CONSTRUCT{" + "?persona ?tipo ?Categoria." + "?Categoria ?id ?nombreCategoria."
				+ "?persona ?prefExperiencia ?Experiencia. " + "?Experiencia ?id ?nombreExperiencia."
				+ "?persona ?habilidades ?lenguajeProg. " + "?lenguajeProg ?id ?nombreLenguajeProg."
				+ "?persona ?prefCertif ?certificacion. " + "?certificacion ?id ?nombreCertificacion."
				+ "?persona ?prefIdioma ?idioma. " + "?idioma ?id ?nombreIdioma." + "}"
				+ "where{ GRAPH <http://opendata.eurohelp.es/dataset/recursos-humanos> {"
				+ "?persona ?tipo ?prefPersona." + "?persona ?tipo ?Categoria." + "?Categoria ?id ?nombreCategoria."
				+ "?Categoria ?tipo ?prefPuesto." + "?persona ?prefExperiencia ?Experiencia."
				+ "?Experiencia ?id ?nombreExperiencia." + "?persona ?habilidades ?lenguajeProg."
				+ "?lenguajeProg ?id ?nombreLenguajeProg." + "?persona ?prefCertif ?certificacion."
				+ "?certificacion ?id ?nombreCertificacion." + "?persona ?prefIdioma ?idioma."
				+ "?idioma ?id ?nombreIdioma." + "FILTER (?nombreCategoria IN ())" + "FILTER (?nombreExperiencia IN ())"
				+ "FILTER (?nombreLenguajeProg IN ())" + "FILTER (?nombreCertificacion IN ())"
				+ "FILTER (?nombreIdioma IN ())" + "FILTER (?tipo = rdf:type )"
				+ "FILTER (?prefIdioma = <http://opendata.euskadi.eus/idioma> )"
				+ "FILTER (?habilidades = <http://opendata.euskadi.eus/skill> )"
				+ "FILTER (?prefExperiencia = <http://opendata.euskadi.eus/experience> )"
				+ "FILTER (?prefPuesto = <http://opendata.euskadi.eus/puesto> )"
				+ "FILTER (?prefCertif = <http://opendata.euskadi.eus/certification> )"
				+ "FILTER (?prefPersona = <http://schema.org/Person> )" + "}" + "}";
		System.out.println(query);
	}

}

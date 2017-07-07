package eurohelp.recursoshumanos.stardog;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.openrdf.repository.RepositoryException;

import eurohelp.recursoshumanos.pagegenerator.Opciones;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

public class main {
	public static void main(String[] args) throws RepositoryException, TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Configuration cfg = new Configuration(new Version(2, 3, 20));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.US);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		String[] pCategoria = { "Jefe de proyecto", "Analista" };
		String[] pExperiencia = { "Eurohelp Consulting" };
		String[] pHabilidades = { "HTML", "JAVA" };
		String[] pCertificaciones = { "ISTQB", "UDA" };
		String[] pIdiomas = { "Spanish" };
		String[] pUniversidad = {"UPV"};
		Map<String, Object> listaGlobal = new HashMap<String, Object>();
		listaGlobal.put("categoria", Opciones.Categoria);
		listaGlobal.put("certificacion", Opciones.Certificaciones);
		listaGlobal.put("habilidad", Opciones.Habilidades);
		listaGlobal.put("idioma", Opciones.Idioma);
		listaGlobal.put("experiencia", Opciones.Experiencia);
		listaGlobal.put("universidad", Opciones.Universidad);
		listaGlobal.put("listaCategoria", Arrays.asList(pCategoria));
		listaGlobal.put("listaHabilidades", Arrays.asList(pHabilidades));
		listaGlobal.put("listaCertificaciones", Arrays.asList(pCertificaciones));
		listaGlobal.put("listaIdiomas", Arrays.asList(pIdiomas));
		listaGlobal.put("listaExperiencia", Arrays.asList(pExperiencia));
		listaGlobal.put("listaUniversidades", Arrays.asList(pUniversidad));
		Template template = cfg.getTemplate("/src/main/resous/index.ftl");
		StringWriter stringWriter = new StringWriter();
		template.process(listaGlobal, stringWriter);
		System.out.println(stringWriter);
	}
}
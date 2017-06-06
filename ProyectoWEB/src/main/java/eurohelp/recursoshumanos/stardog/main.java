package eurohelp.recursoshumanos.stardog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.openrdf.repository.RepositoryException;

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
		// GeneradorIndex gi = new GeneradorIndex();
		// gi.generarIndex();

		Configuration cfg = new Configuration(new Version(2, 3, 20));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.US);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		String[] pCategoria = { "Jefe de proyecto", "Analista" };
		String[] pExperiencia = { "Eurohelp Consulting" };
		String[] pHabilidades = { "HTML" };
		String[] pCertificaciones = { "ISTQB" };
		String[] pIdiomas = { "Spanish" };
		String[] pUniversidad = { "UPV-EHU" };
		Map<String, Object> listaGlobal = new HashMap<String, Object>();
		listaGlobal.put("categoria", "categoria");

		Template template = cfg.getTemplate("/src/main/resources/index.ftl");
		Writer consoleWriter = new OutputStreamWriter(System.out);
		template.process(listaGlobal, consoleWriter);

		// For the sake of example, also write output into a file:
		Writer fileWriter = new FileWriter(new File("/templates/output.html"));
		try {
			template.process(listaGlobal, fileWriter);
		} finally {
			fileWriter.close();
		}

	}
}
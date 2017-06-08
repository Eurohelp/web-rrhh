package eurohelp.recursoshumanos.pagegenerator;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

public class GeneradorIndex {
	Configuration cfg;
	Template template;

	public GeneradorIndex() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
		cfg = new Configuration(new Version(2, 3, 20));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.US);
		cfg.setClassForTemplateLoading(GeneradorIndex.class, "/");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	public String generarIndex(List<String> pCategoria) throws TemplateException, IOException  {
		Map<String, Object> listaGlobal = new HashMap<String, Object>();
		listaGlobal.put("categoria", "Categoria");
		listaGlobal.put("listaCategoria", pCategoria);
		template = cfg.getTemplate("index.ftl");
		StringWriter stringWriter = new StringWriter();
		template.process(listaGlobal, stringWriter);
		System.out.println(stringWriter);
		return stringWriter.toString();
	}

	public String generarIndex(ArrayList<String> pCategoria, ArrayList<String> pHabilidades,
			ArrayList<String> pCertificaciones) throws TemplateException, IOException {
		Map<String, Object> listaGlobal = new HashMap<String, Object>();
		listaGlobal.put("categoria", "Categoria");
		listaGlobal.put("listaCategoria", Arrays.asList(pCategoria));
		listaGlobal.put("habilidad", "Habilidades");
		listaGlobal.put("listaHabilidades", Arrays.asList(pHabilidades));
		listaGlobal.put("certificacion", "Certificaciones");
		listaGlobal.put("listaCertificaciones", Arrays.asList(pCertificaciones));
		StringWriter stringWriter = new StringWriter();
		template = cfg.getTemplate("index.ftl");
		template.process(listaGlobal, stringWriter);
		return stringWriter.toString();
	}

	public String generarIndex(ArrayList<String> pCategoria, ArrayList<String> pHabilidades,
			ArrayList<String> pCertificaciones, ArrayList<String> pIdiomas, ArrayList<String> pUniversidad,
			ArrayList<String> pExperiencia) throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		Map<String, Object> listaGlobal = new HashMap<String, Object>();
		listaGlobal.put("categoria", "Categoria");
		listaGlobal.put("listaCategoria", Arrays.asList(pCategoria));
		listaGlobal.put("habilidad", "Habilidades");
		listaGlobal.put("listaHabilidades", Arrays.asList(pHabilidades));
		listaGlobal.put("certificacion", "Certificaciones");
		listaGlobal.put("listaCertificaciones", Arrays.asList(pCertificaciones));
		listaGlobal.put("idioma", "Certificaciones");
		listaGlobal.put("listaIdiomas", Arrays.asList(pIdiomas));
		listaGlobal.put("universidad", "Certificaciones");
		listaGlobal.put("listaUniversidades", Arrays.asList(pUniversidad));
		listaGlobal.put("experiencia", "Certificaciones");
		listaGlobal.put("listaExperiencia", Arrays.asList(pExperiencia));
		template = cfg.getTemplate("index.ftl");
		StringWriter stringWriter = new StringWriter();
		template.process(listaGlobal, stringWriter);
		return stringWriter.toString();
	}

}

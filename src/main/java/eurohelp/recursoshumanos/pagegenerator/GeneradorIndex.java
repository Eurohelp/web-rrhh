package eurohelp.recursoshumanos.pagegenerator;

import java.io.IOException;
import java.io.StringWriter;
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

/**
 * 
 * @author Mishel Uchuari, 28 jul. 2017
 */

public class GeneradorIndex {
	Configuration cfg;
	Template template;

	/**
	 * Constructor carga el template que genera el index
	 * 
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 */
	public GeneradorIndex()
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
		cfg = new Configuration(new Version(2, 3, 20));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.US);
		cfg.setClassForTemplateLoading(GeneradorIndex.class, "/");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	/**
	 * Genera el index en el que el usuario tendr� como �nica opci�n la
	 * categor�a
	 * 
	 * @param pCategoria
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	public String generarIndex(List<String> pCategoria) throws TemplateException, IOException {
		Map<String, Object> listaGlobal = new HashMap<String, Object>();
		listaGlobal.put("categoria", Opciones.Categoria);
		listaGlobal.put("listaCategoria", pCategoria);
		template = cfg.getTemplate("index.ftl");
		StringWriter stringWriter = new StringWriter();
		template.process(listaGlobal, stringWriter);
		return stringWriter.toString();
	}

	/**
	 * Genera el index en el que el usuario tendr� como opciones de filtrado la
	 * categoria, las habilidades y las certificaciones.
	 * 
	 * @param pCategoria
	 * @param pHabilidades
	 * @param pCertificaciones
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	public String generarIndex(List<String> pCategoria, List<String> pHabilidades, List<String> pCertificaciones)
			throws TemplateException, IOException {
		Map<String, Object> listaGlobal = new HashMap<String, Object>();
		listaGlobal.put("categoria", Opciones.Categoria);
		listaGlobal.put("listaCategoria", pCategoria);
		listaGlobal.put("habilidad", Opciones.Habilidades);
		listaGlobal.put("listaHabilidades", pHabilidades);
		listaGlobal.put("certificacion", Opciones.Certificaciones);
		listaGlobal.put("listaCertificaciones", pCertificaciones);
		StringWriter stringWriter = new StringWriter();
		template = cfg.getTemplate("index.ftl");
		template.process(listaGlobal, stringWriter);
		return stringWriter.toString();
	}

	/**
	 * Genera el indice final donde el usuario tendr� todas las opciones de
	 * filtrado
	 * 
	 * @param pCategoria
	 * @param pHabilidades
	 * @param pCertificaciones
	 * @param pIdiomas
	 * @param pUniversidad
	 * @param pExperiencia
	 * @return
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public String generarIndex(List<String> pCategoria, List<String> pHabilidades, List<String> pCertificaciones,
			List<String> pIdiomas, List<String> pUniversidad, List<String> pExperiencia)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		Map<String, Object> listaGlobal = new HashMap<String, Object>();
		listaGlobal.put("categoria", Opciones.Categoria);
		listaGlobal.put("listaCategoria", pCategoria);
		listaGlobal.put("habilidad", Opciones.Habilidades);
		listaGlobal.put("listaHabilidades", pHabilidades);
		listaGlobal.put("certificacion", Opciones.Certificaciones);
		listaGlobal.put("listaCertificaciones", pCertificaciones);
		listaGlobal.put("idioma", Opciones.Idioma);
		listaGlobal.put("listaIdiomas", pIdiomas);
		listaGlobal.put("universidad", Opciones.Universidad);
		listaGlobal.put("listaUniversidades", pUniversidad);
		listaGlobal.put("experiencia", Opciones.Experiencia);
		listaGlobal.put("listaExperiencia", pExperiencia);
		template = cfg.getTemplate("index.ftl");
		StringWriter stringWriter = new StringWriter();
		template.process(listaGlobal, stringWriter);
		return stringWriter.toString();
	}
}
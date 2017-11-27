package eurohelp.recursoshumanos.stardog.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 
 * @author dmuchuari
 * @23/11/2017
 */
public class PropertiesManager {
	private static PropertiesManager INSTANCE = null;
	private Properties properties;

	public synchronized static PropertiesManager getINSTANCE() throws IOException {
		if (INSTANCE == null) {
			INSTANCE = new PropertiesManager();
		}
		return INSTANCE;
	}

	private PropertiesManager() throws IOException {
		properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("stardog-conf.properties");
		properties = new Properties();
		properties.load(input);
	}

	public String getProperty(String pPropertyName) {
		return properties.getProperty(pPropertyName);
	}
}

package de.citunius.develop;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;
import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class Utilities {
	
	static Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	/**
	 * Read property from file system
	 * 
	 * Read a property file which is stored on in file system
	 * 
	 * @param key	key
	 * @return the <code>value</code> on success; otherwise <code>null</code>
	 */	
	public static String getAppProperty(ServletContext servletContext, String key) {
		if (key == null || key.length()== 0) {
			logger.error("Properties key can't be empty");
			return null;
		} else {
			if (servletContext.getAttribute(ConstantsUtil.DEVELOPERUI_SESSION_CONFIGPROPS) == null) {
				logger.info("Config parameters not loaded yet, loading it...");
				loadConfigFile(servletContext);
			} else {
				logger.info("Config parameters already loaded");
			}
			HashMap<String, String> configProps = (HashMap<String, String>) servletContext.getAttribute(ConstantsUtil.DEVELOPERUI_SESSION_CONFIGPROPS);
			if (configProps != null && configProps.size() != 0) {
				if (configProps.containsKey(key)) {
					logger.info("Property key ["+key+"] found");
					return (configProps.get(key));
				} else {
					logger.error("Property key ["+key+"] not found");
					return null;
				}
			} else {
				logger.warn("No config parameters found");
				return null;
			}
		}
	}
	
	/**
	 * Read property from file system
	 * 
	 * Read a property file which is stored on in file system
	 * 
	 * @param key	key
	 * @return the <code>value</code> on success; otherwise <code>null</code>
	 */	
	public static HashMap<String, String> listAppProperties(ServletContext servletContext) {
		if (servletContext.getAttribute(ConstantsUtil.DEVELOPERUI_SESSION_CONFIGPROPS) == null) {
			logger.info("Config parameters not loaded yet, loading it...");
			loadConfigFile(servletContext);
		} else {
			logger.info("Config parameters already loaded");
		}
		HashMap<String, String> configProps = (HashMap<String, String>) servletContext.getAttribute(ConstantsUtil.DEVELOPERUI_SESSION_CONFIGPROPS);
		if (configProps != null && configProps.size() != 0) {
			return configProps;
		} else {
			logger.warn("No config parameters found");
			return null;
		}
	}
	
	public static String getConfigFilePath() {
		return ("/WEB-INF/classes/"+ConstantsUtil.DEVELOPERUI_PROP_FILENAME);
	}
	
	/**
	 * Load configuration file
	 * 
	 * @param servletContext  	the ServletContext object
	 * @return <code>true</code> on success; otherwise <code>false</code>
	 */
	public static boolean loadConfigFile(ServletContext servletContext) {		
		HashMap<String, String> configProps = new HashMap<String, String>();
		Properties  properties  = new Properties();
		try {			
			InputStream is = servletContext.getResourceAsStream(getConfigFilePath());
			properties.load(is);
	        Enumeration<?> em = properties.keys();
	        
	        while(em.hasMoreElements()){
	        	String str = (String)em.nextElement();
	        	logger.trace("Key:Value > [" + str + "]: [" + properties.get(str)+"]");	        	
	        	configProps.put(str, properties.get(str).toString() );
	        }				
			is.close();
			servletContext.setAttribute(ConstantsUtil.DEVELOPERUI_SESSION_CONFIGPROPS, configProps);
			return true;
		} catch (IOException e) { 			
			logger.fatal("File not found: ["+getConfigFilePath()+"]");
			logger.fatal("Exception: [" + e.getMessage()+"]");
			logger.fatal("IOException", e);
			return false;
		}
	}
	
	/**
	 * Save parameter to bbp.properties file
	 * 
	 * @param servletContext  	the servlet context
	 * @param paramMap  		the map of parameter names and parameter values
	 * @return <code>true</code> on success; otherwise <code>false</code>
	 */
	public static boolean saveParametersToBBPConfigFile(ServletContext servletContext, HashMap<String, String> paramMap) {	
		HashMap<String, String> configProps = new HashMap<String, String>();
		try {
			InputStream is = servletContext.getResourceAsStream(getConfigFilePath());
			
			PropertiesConfiguration config = new PropertiesConfiguration();
	        PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
	        layout.load(new InputStreamReader(is));

	        // Set parameter to be changed
	        Set<Map.Entry<String, String>> entries = paramMap.entrySet();
	        Iterator<Map.Entry<String, String>> paramIterator = entries.iterator();
	        while (paramIterator.hasNext()) {
	            Map.Entry<String, String> entry = paramIterator.next();
	            logger.info("Updatng properties: Entry: ["+entry.getKey()+"] => ["+entry.getValue()+"]");
	            config.setProperty(entry.getKey(), entry.getValue());	            
	            // Update ApplicationProperties
	            configProps.put(entry.getKey(), entry.getValue());
	        }
	        
	        String filePath = servletContext.getRealPath("WEB-INF") + "/classes/"+ConstantsUtil.DEVELOPERUI_PROP_FILENAME;
	        layout.save(new FileWriter(filePath, false));
	        is.close();	
	        
	        // Reload parameterMap stored as session attribute
	        loadConfigFile(servletContext);
	        
	        return true;
		} catch (IOException e) { 			
			logger.fatal("File not found: ["+getConfigFilePath()+"]");
			logger.fatal("Exception: [" + e.getMessage()+"]");
			logger.fatal("IOException", e);
			return false;
		} catch (ConfigurationException e) { 			
			logger.fatal("Failed to save parameters to properties file ["+getConfigFilePath()+"]");
			logger.fatal("ConfigurationException: [" + e.getMessage()+"]");
			logger.fatal("ConfigurationException", e);
			return false;
		} catch (Exception e) { 			
			logger.fatal("Failed to save parameters to properties file ["+getConfigFilePath()+"]");
			logger.fatal("Exception: [" + e.getMessage()+"]");
			logger.fatal("Exception", e);
			return false;
		}
	}
	
	/**
	 * Save parameter to bbp.properties file
	 * 
	 * @param servletContext  	the servlet context
	 * @param parameter  		the parameter name
	 * @param value  			the parameter value
	 * @return <code>true</code> on success; otherwise <code>false</code>
	 */
	public static boolean saveParameterToConfigFile(ServletContext servletContext, String parameter, String value) {	
		try {
			InputStream is = servletContext.getResourceAsStream(getConfigFilePath());
			
			PropertiesConfiguration config = new PropertiesConfiguration();
	        PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
	        layout.load(new InputStreamReader(is));

	        config.setProperty(parameter, value);
	        //config.save();
	        String filePath = servletContext.getRealPath("WEB-INF") + "/classes/"+ConstantsUtil.DEVELOPERUI_PROP_FILENAME;
	        logger.info("Updating properties file ["+filePath+"] -> param: ["+parameter+"] value: ["+value+"]");
	        layout.save(new FileWriter(filePath, false));
	        is.close();
	        
			// Reload parameterMap stored as session attribute
	        loadConfigFile(servletContext);   
	        
	        return true;
		} catch (IOException e) { 			
			logger.fatal("File not found: ["+getConfigFilePath()+"]");
			logger.fatal("Exception: [" + e.getMessage()+"]");
			logger.fatal("IOException", e);
			return false;
		} catch (ConfigurationException e) { 			
			logger.fatal("Failed to save parameter ["+parameter+"] to properties file ["+getConfigFilePath()+"]");
			logger.fatal("ConfigurationException: [" + e.getMessage()+"]");
			logger.fatal("ConfigurationException", e);
			return false;
		} catch (Exception e) { 			
			logger.fatal("Failed to save parameter ["+parameter+"] to properties file ["+getConfigFilePath()+"]");
			logger.fatal("Exception: [" + e.getMessage()+"]");
			logger.fatal("Exception", e);
			return false;
		}
	}
	
	/**
	 * Configure Freemarker to load template files
	 * 
	 * @param servletContext  the ServletContext object
	 * @return the <code>Configuration</code> object of Freemarker
	 * @throws IOException  the IO exception
	 * @throws TemplateException the Template exception
	 */
	public Configuration configureFreemarker(ServletContext servletContext) throws IOException, TemplateException {	
    	Configuration configuration = new Configuration();
        //freemarker.template.Configuration configuration = configurer.createConfiguration();

        // Make sure all freemarker files go in /WEB-INF/templates/
        // This helps keep the code organized
        configuration.setServletContextForTemplateLoading(servletContext, "/WEB-INF/templates/");

        // When starting a new FreeMarker project, always set the incompatible improvements to the version you are using.
        configuration.setIncompatibleImprovements(freemarker.template.Configuration.VERSION_2_3_23);

        // Use this for local development. When a template exception occurs,
        // it will format the error using HTML so it can be easily read
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

        // Make sure everything is UTF-8 from the beginning to avoid headaches
        configuration.setDefaultEncoding("UTF-8");
        configuration.setOutputEncoding("UTF-8");
        configuration.setURLEscapingCharset("UTF-8");        
        return configuration;
    }
}

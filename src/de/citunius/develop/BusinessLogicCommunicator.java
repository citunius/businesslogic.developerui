package de.citunius.develop;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;


public class BusinessLogicCommunicator {
	static Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	private Class<?> c = null;
	private static final String BL_METHOD_GET_BOTBUILDERFUNCTIONS = "getBotBuilderPluginFunctions";
	private static final String BL_METHOD_CALL_BOTBUILDERFUNCTION = "callBotBuilderFunction";
	private static final String BL_METHOD_HANDLE_INCOMING_CALLBACK = "handleIncomingCallback";
	private static final String BL_METHOD_HANDLE_INCOMING_MESSAGE = "handleIncomingMessage";
	private static final String BL_METHOD_SEND_MESSAGE = "sendMessage";
	private static final String BL_METHOD_PREPARE = "prepare";
	private static final String BL_METHOD_GET_WEBUIPAGE = "getWebUIPage";
	private static final String BL_METHOD_IS_WEBUI_SUPPORTED = "isWebUISupported";
	private static final String BL_METHOD_IS_LICENSE_REQUIRED = "isLicenseRequired";
	private static final String BL_METHOD_IS_BOTBUILDERMODEL_SUPPORTED = "isBotBuilderModelSupported";
	
	public BusinessLogicCommunicator(ServletContext sc) throws ClassNotFoundException {
		this.c = getBusinessLogicClass(sc);
	}
	
	public HashMap<String, String> getBotBuilderPluginFunctions(String pluginResourcesPath) {
		try {
			Class<?>[] parameterTypes = {String.class};
			logger.info("parameterTypes to invoke: ["+parameterTypes.toString()+"]");
			Method  method = c.getDeclaredMethod (BL_METHOD_GET_BOTBUILDERFUNCTIONS, parameterTypes);
			return (HashMap<String, String>) method.invoke (c.newInstance(), pluginResourcesPath);			
		} catch (NoSuchMethodException e) {
    		logger.error(e);
    	} catch (InvocationTargetException e) {
    		logger.error(e);
    	} catch (IllegalAccessException e) {
    		logger.error(e);
    	} catch (InstantiationException e) {
    		logger.error(e);
    	}
		return null;
	}
	
	public String callBotBuilderFunction(String tenantId, String accountId, HashMap<String, String> pluginMap, String jsonMessage, boolean anonymousUserAccountExists, String jsonAnonymousUserAccount, boolean mobileUserAccountExists, String jsonMobileUserAccount) {
		try {
			Class<?>[] parameterTypes = {String.class, String.class, HashMap.class, String.class, boolean.class, String.class, boolean.class, String.class};
			logger.info("parameterTypes to invoke: ["+parameterTypes.toString()+"]");
			Method  method = c.getDeclaredMethod (BL_METHOD_CALL_BOTBUILDERFUNCTION, parameterTypes);
			return (String) method.invoke (c.newInstance(), tenantId, accountId, pluginMap, jsonMessage, anonymousUserAccountExists, jsonAnonymousUserAccount, mobileUserAccountExists, jsonMobileUserAccount);		
		} catch (NoSuchMethodException e) {
    		logger.error(e);
    	} catch (InvocationTargetException e) {
    		logger.error(e);
    	} catch (IllegalAccessException e) {
    		logger.error(e);
    	} catch (InstantiationException e) {
    		logger.error(e);
    	}
		return null;
	}
	
	public String handleIncomingCallback(String tenantId, String accountId, HashMap<String, String> pluginMap, String message, boolean anonymousUserAccountExists, String jsonAnonymousUserAccount, boolean mobileUserAccountExists, String jsonMobileUserAccount) {
		try {
			Class<?>[] parameterTypes = {String.class, String.class, HashMap.class, String.class, boolean.class, String.class, boolean.class, String.class};
			logger.info("parameterTypes to invoke: ["+parameterTypes.toString()+"]");
			Method  method = c.getDeclaredMethod (BL_METHOD_HANDLE_INCOMING_CALLBACK, parameterTypes);
			return (String) method.invoke (c.newInstance(), tenantId, accountId, pluginMap, message, anonymousUserAccountExists, jsonAnonymousUserAccount, mobileUserAccountExists, jsonMobileUserAccount);			
		} catch (NoSuchMethodException e) {
    		logger.error(e);
    	} catch (InvocationTargetException e) {
    		logger.error(e);
    	} catch (IllegalAccessException e) {
    		logger.error(e);
    	} catch (InstantiationException e) {
    		logger.error(e);
    	}
		return null;
	}
	
	public String handleIncomingMessage(String tenantId, String accountId, HashMap<String, String> pluginMap, String jsonMessage, boolean anonymousUserAccountExists, String jsonAnonymousUserAccount, boolean mobileUserAccountExists, String jsonMobileUserAccount) {
		try {
			Class<?>[] parameterTypes = {String.class, String.class, HashMap.class, String.class, boolean.class, String.class, boolean.class, String.class};
			logger.info("parameterTypes to invoke: ["+parameterTypes.toString()+"]");
			Method  method = c.getDeclaredMethod (BL_METHOD_HANDLE_INCOMING_MESSAGE, parameterTypes);
			return (String) method.invoke (c.newInstance(), tenantId, accountId, pluginMap, jsonMessage, anonymousUserAccountExists, jsonAnonymousUserAccount, mobileUserAccountExists, jsonMobileUserAccount);		
		} catch (NoSuchMethodException e) {
    		logger.error(e);
    	} catch (InvocationTargetException e) {
    		logger.error(e);
    	} catch (IllegalAccessException e) {
    		logger.error(e);
    	} catch (InstantiationException e) {
    		logger.error(e);
    	}
		return null;
	}
	
	public String sendMessage(String tenantId, String accountId, HashMap<String, String> pluginMap, String jsonMessage, String jsonFilter, boolean anonymousUserAccountExists, String jsonAnonymousUserAccount, boolean mobileUserAccountExists, String jsonMobileUserAccount) {
		try {
			Class<?>[] parameterTypes = {String.class, String.class, HashMap.class, String.class, String.class, boolean.class, String.class, boolean.class, String.class};
			logger.info("parameterTypes to invoke: ["+parameterTypes.toString()+"]");
			Method  method = c.getDeclaredMethod (BL_METHOD_SEND_MESSAGE, parameterTypes);
			return (String) method.invoke (c.newInstance(), tenantId, accountId, pluginMap, jsonMessage, jsonFilter, anonymousUserAccountExists, jsonAnonymousUserAccount, mobileUserAccountExists, jsonMobileUserAccount);				
		} catch (NoSuchMethodException e) {
    		logger.error(e);
    	} catch (InvocationTargetException e) {
    		logger.error(e);
    	} catch (IllegalAccessException e) {
    		logger.error(e);
    	} catch (InstantiationException e) {
    		logger.error(e);
    	}
		return null;
	}
	
	public String prepare(String tenantId, String accountId, HashMap<String, String> pluginMap, String jsonMessage) {
		try {
			Class<?>[] parameterTypes = {String.class, String.class, HashMap.class, String.class};
			logger.info("parameterTypes to invoke: ["+parameterTypes.toString()+"]");
			Method  method = c.getDeclaredMethod (BL_METHOD_PREPARE, parameterTypes);
			return (String) method.invoke (c.newInstance(), tenantId, accountId, pluginMap, jsonMessage);		
		} catch (NoSuchMethodException e) {
    		logger.error(e);
    	} catch (InvocationTargetException e) {
    		logger.error(e);
    	} catch (IllegalAccessException e) {
    		logger.error(e);
    	} catch (InstantiationException e) {
    		logger.error(e);
    	}
		return null;
	}
	
	public String getWebUIPage(String tenantId, String accountId, HashMap<String, String> pluginMap, Map<String, String[]> parameterMap) {
		try {
			Class<?>[] parameterTypes = {String.class, String.class, HashMap.class, Map.class};
			logger.info("parameterTypes to invoke: ["+parameterTypes.toString()+"]");
			Method  method = c.getDeclaredMethod (BL_METHOD_GET_WEBUIPAGE, parameterTypes);
			return (String) method.invoke (c.newInstance(), tenantId, accountId, pluginMap, parameterMap);	
		} catch (NoSuchMethodException e) {
    		logger.error(e);
    	} catch (InvocationTargetException e) {
    		logger.error(e);
    	} catch (IllegalAccessException e) {
    		logger.error(e);
    	} catch (InstantiationException e) {
    		logger.error(e);
    	}
		return null;
	}
	
	public boolean isWebUISupported() {
		try {
			Method  method = c.getDeclaredMethod(BL_METHOD_IS_WEBUI_SUPPORTED);
			return (boolean) method.invoke(c.newInstance());			
		} catch (NoSuchMethodException e) {
    		logger.error(e);
    	} catch (InvocationTargetException e) {
    		logger.error(e);
    	} catch (IllegalAccessException e) {
    		logger.error(e);
    	} catch (InstantiationException e) {
    		logger.error(e);
    	}
		return false;
	}
	
	public boolean isLicenseRequired() {
		try {
			Method  method = c.getDeclaredMethod(BL_METHOD_IS_LICENSE_REQUIRED);
			return (boolean) method.invoke(c.newInstance());			
		} catch (NoSuchMethodException e) {
    		logger.error(e);
    	} catch (InvocationTargetException e) {
    		logger.error(e);
    	} catch (IllegalAccessException e) {
    		logger.error(e);
    	} catch (InstantiationException e) {
    		logger.error(e);
    	}
		return false;
	}
	
	public boolean isBotBuilderModelSupported() {
		try {
			Method  method = c.getDeclaredMethod(BL_METHOD_IS_BOTBUILDERMODEL_SUPPORTED);
			return (boolean) method.invoke(c.newInstance());			
		} catch (NoSuchMethodException e) {
    		logger.error(e);
    	} catch (InvocationTargetException e) {
    		logger.error(e);
    	} catch (IllegalAccessException e) {
    		logger.error(e);
    	} catch (InstantiationException e) {
    		logger.error(e);
    	}
		return false;
	}
	
	public Class<?> getBusinessLogicClass(ServletContext sc) throws ClassNotFoundException {
		String className = Utilities.getAppProperty(sc, ConstantsPlugin.BUSINESSLOGIC_CLASSNAME);
		logger.info("Class to invoke: ["+className+"]");
		Class<?> c = Class.forName(className);
		return c;
	}
}

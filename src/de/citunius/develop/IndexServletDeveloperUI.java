package de.citunius.develop;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.invoke.MethodHandles;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import de.citunius.bbp.im.gm.objects.Data;
import de.citunius.bbp.im.gm.objects.Filter;
import de.citunius.bbp.im.gm.objects.Message;
import de.citunius.bbp.objects.AnonymousUserAccount;
import de.citunius.bbp.objects.MobileAppProvider;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class TestWebUI
 * 
 * <p>This servlet is the developer UI will all buttons to invoke the business logic such as prepareBL() and incomingMessage()</p> 
 */
@WebServlet("/IndexServletDeveloperUI")
public class IndexServletDeveloperUI extends HttpServlet {
	static Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass());
	private static final long serialVersionUID = 2111348979089418870L;
	private HttpSession session = null;
	private String tenantId = "demo";
	private String accountId = "ALL_ACCOUNTS";
	private boolean isAuthenticated = false;
	public static String encoding = "UTF-8";
	public static Configuration freemarkerConfig = null;
	public static int businessBotPluginIdDummy = 1; // for business logic: demo.ALL_ACCOUNTS.de.citunius.gm.calculator
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServletDeveloperUI() {
        super();        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Call this url: http://localhost:9090/BusinessLogic.GM.DeveloperUI/IndexServlet?businessBotPluginId=8
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		logger.trace("doGet character encoding is: "+request.getCharacterEncoding());
		
		// If download request, then do not call getWriter because download will do that
		if (request.getParameter("dl") != null && request.getParameter("dl").equalsIgnoreCase("yes")) {
			manageRequest(request, response, ConstantsUI.UI_PAGE_DOWNLOAD_REQUEST);
		} else if(request.getParameter("format") != null && request.getParameter("format").equalsIgnoreCase("json")) { // Check if page request is a json request 
			String businessLogicJsonContent = manageRequest(request, response, ConstantsUI.UI_PAGE_JSON_REQUEST);
			// Change the response header to 'application/json'
			manageJsonRequest(request, response);
			// Print output
			PrintWriter out = response.getWriter();	
			out.print(businessLogicJsonContent);
		} else {
			String businessLogicPageContentTabWebUI = manageRequest(request, response, ConstantsUI.UI_PAGE_BL_WEBUI);
			logger.trace("Business logic content page (Tab WebUI) size: ["+businessLogicPageContentTabWebUI.length()+"]");
			
			String businessLogicPageContentTabSettings = manageRequest(request, response, ConstantsUI.UI_PAGE_BL_SETTINGS);
			logger.trace("Business logic content page (Tab Settings) size: ["+businessLogicPageContentTabSettings.length()+"]");
			
			String businessLogicPageContentTabAbout = manageRequest(request, response, ConstantsUI.UI_PAGE_BL_ABOUT);
			logger.trace("Business logic content page (Tab About) size: ["+businessLogicPageContentTabAbout.length()+"]");
			
			// Put page content into developer view
			String pageOutput = "";
			try {
				Template template = freemarkerConfig.getTemplate("page_construct.ftl");
				Writer out = new StringWriter(); 
				Map<String, Object> data = new HashMap<String, Object>();					
				data.put("number", businessBotPluginIdDummy);
				data.put("webui", businessLogicPageContentTabWebUI);
				data.put("settings", businessLogicPageContentTabSettings);
				data.put("about", businessLogicPageContentTabAbout);
				
				template.process(data, out);
				pageOutput = out.toString();
			} catch (IOException e) {
				logger.error("IOException: ", e);
			} catch (TemplateException e) {
				logger.error("TemplateException: ", e);
			}
			
			PrintWriter out = response.getWriter();	
			out.print(pageOutput);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		logger.trace("doPost character encoding is: "+request.getCharacterEncoding());
		
		// If download request, then do not call getWriter because download will do that
		if (request.getParameter("dl") != null && request.getParameter("dl").equalsIgnoreCase("yes")) {
			manageRequest(request, response, ConstantsUI.UI_PAGE_DOWNLOAD_REQUEST);
			
		} else if(request.getParameter("format") != null && request.getParameter("format").equalsIgnoreCase("json")) { // Check if page request is a json request 
			String businessLogicJsonContent = manageRequest(request, response, ConstantsUI.UI_PAGE_JSON_REQUEST);
			// Change the response header to 'application/json'
			manageJsonRequest(request, response);
			// Print output
			PrintWriter out = response.getWriter();	
			out.print(businessLogicJsonContent);
		} else {
			// Put page content into developer view
			String pageOutput = "";
			try {
				Template template = freemarkerConfig.getTemplate("page_construct.ftl");
				Writer out = new StringWriter(); 
				Map<String, Object> data = new HashMap<String, Object>();					
				data.put("number", businessBotPluginIdDummy);
				data.put("webui", manageRequest(request, response, ConstantsUI.UI_PAGE_BL_WEBUI));
				data.put("settings", manageRequest(request, response, ConstantsUI.UI_PAGE_BL_SETTINGS));
				data.put("about", manageRequest(request, response, ConstantsUI.UI_PAGE_BL_ABOUT));
				template.process(data, out);
				pageOutput = out.toString();
			} catch (IOException e) {
				logger.error("IOException: ", e);
			} catch (TemplateException e) {
				logger.error("TemplateException: ", e);
			}
			
			PrintWriter out = response.getWriter();	
			out.print(pageOutput);		
		}	
	}
	
	/**
	 * Manage all types of page requests (e.g. webpage, JSON request, file download)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public String manageRequest(HttpServletRequest request, HttpServletResponse response, String page) throws IOException {
		// Configure Freemarker
    	if (freemarkerConfig == null) {		
    		try {
				Utilities iutil = new Utilities();
    			freemarkerConfig = iutil.configureFreemarker(request.getServletContext());
			} catch (TemplateException e) {
				logger.error("TemplateException: Can not configure template engine");
				logger.error(e);
			}			
		}
		
		if (request.getSession(false) != null && request.getSession().getAttribute(ConstantsUI.UA_TENANTID) != null) {
			this.session = request.getSession(false);
			this.tenantId = request.getSession().getAttribute(ConstantsUI.UA_TENANTID).toString();
			this.accountId = request.getSession().getAttribute(ConstantsUI.UA_ACCOUNTID).toString();
			/*
			if (request.getSession().getAttribute(ConstantsUI.UA_ISUSERAUTHENTICATED) != null) {
				this.isAuthenticated = Boolean.parseBoolean(request.getSession().getAttribute(ConstantsUI.UA_ISUSERAUTHENTICATED).toString());
			} else {
				return "USER_NOT_AUTHENTICATED";
			}
			*/
		}
		
		// Create a new session
		session = request.getSession(true);
		// Simulate session parameter (normally defined by BBP)
		session.setAttribute(ConstantsUI.UA_LANGUAGE_BROWSER, "en");
		
		String username = request.getParameter("username");
		/*
		if (!isAuthenticated) {
			logger.info("User ["+username+"] is not authenticated. Terminated.");
			return "USER_NOT_AUTHENTICATED";
		}
		*/
		
		String pageContent = "";
		HashMap<String, String> pluginMap = getPluginMap(session.getServletContext());
		BusinessLogicCommunicator bl;
		try {
			bl = new BusinessLogicCommunicator(request.getSession().getServletContext());
			// Manage business logic requests expects BL Webpage requests
			if (request.getParameter("action") != null && request.getParameter("action").equals(ConstantsActions.ACTION_BUSINESSLOGIC_IS_LICENSE_REQUIRED)) {
				return actionOnBusinessLogicIsLicenseRequired(bl);				
			} else if (request.getParameter("action") != null && request.getParameter("action").equals(ConstantsActions.ACTION_BUSINESSLOGIC_IS_BOTBUILDERMODEL_SUPPORTED)) {
				return actionOnBusinessLogicIsBotBuilderModelSupported(bl);						
			} else if (request.getParameter("action") != null && request.getParameter("action").equals(ConstantsActions.ACTION_BUSINESSLOGIC_IS_WEBUI_SUPPORTED)) {
				return actionOnBusinessLogicIsWebUISupported(bl);						
			} else if (request.getParameter("action") != null && request.getParameter("action").equals(ConstantsActions.ACTION_BUSINESSLOGIC_PREPARE_BL)) {
				return actionOnBusinessLogicPrepare(session.getServletContext(), tenantId, accountId, bl, pluginMap);						
			} else if (request.getParameter("action") != null && request.getParameter("action").equals(ConstantsActions.ACTION_BUSINESSLOGIC_HANDLE_INCOMING_MESSAGE)) {
				return actionOnBusinessLogicIncomingMessage(tenantId, accountId, username, bl, pluginMap, request.getParameter("consoleMessage"));				
			} else if (request.getParameter("action") != null && request.getParameter("action").equals(ConstantsActions.ACTION_BUSINESSLOGIC_HANDLE_INCOMING_CALLBACK)) {
				return actionOnBusinessLogicIncomingCallback(tenantId, accountId, username, bl, pluginMap, request.getParameter("consoleMessage"));				
			} else if (request.getParameter("action") != null && request.getParameter("action").equals(ConstantsActions.ACTION_BUSINESSLOGIC_GET_BOTBUILDERFUNCTIONS)) {
				return actionOnBusinessLogicGetBotBuilderPluginFunctions(session.getServletContext(), tenantId, accountId, bl, pluginMap);					
			} else if (request.getParameter("action") != null && request.getParameter("action").equals(ConstantsActions.ACTION_BUSINESSLOGIC_SEND_MESSAGE)) {
				return actionOnBusinessLogicSendMessage(tenantId, accountId, bl, pluginMap, request.getParameter("consoleMessage"));					
			}
			
			boolean isWebUISupported = bl.isWebUISupported();		
			logger.trace("isWebUISupported: ["+isWebUISupported+"]");
			
			// Call function in WebpageService directly
			String pageContentBusinessLogic = bl.getWebUIPage(tenantId, accountId, pluginMap, request.getParameterMap());
			logger.info("Business logic WebUI content page size: ["+(pageContentBusinessLogic != null ? pageContentBusinessLogic.length() : "0")+"]");
			pageContentBusinessLogic = modifyBusinessLogicPageContent(pageContentBusinessLogic);
			
			// Check if page request is a file download request, if yes, then change the response header for file downloads
			if (request.getParameter("dl") != null && request.getParameter("dl").equalsIgnoreCase("yes")) {				
				manageFileDownloadRequest(request, response, pageContent);								
			} else {
				// Default webpage
			}
			
			if (page != null) {
				if (page.equals(ConstantsUI.UI_PAGE_BL_WEBUI)) {
					// Get page - Business Logic - Tab WebUI
					pageContent = getPageBusinessLogicTabWebUI(session.getServletContext());
				} else if (page.equals(ConstantsUI.UI_PAGE_BL_SETTINGS)) {
					// Get page - Business Logic - Tab Settings
					pageContent = getPageBusinessLogicTabSettings();
				} else if (page.equals(ConstantsUI.UI_PAGE_BL_ABOUT)) {
					// Get page - Business Logic - Tab About
					pageContent = getPageBusinessLogicTabAbout();
				} else if (page.equals("json_request")) {
					return "{\"message\": \"Choose a business logic function\"}";
				} else {
					logger.error("Unknown page name ["+page+"]");
					return "INTERNAL ERROR: Unknown page name ["+page+"]";
				}
			} else {
				logger.error("Page name not defined");
				return "INTERNAL ERROR: Page name not defined";
			}
			
			logger.info("Business logic WebUI embedded content page size: ["+pageContent.length()+"]");
			
			return pageContent;
		} catch (ClassNotFoundException e) {
			return ("ClassNotFoundException: "+e.getMessage());
		}
	}
	
	/**
	 * Manage action - Business Logic handleIncomingMessage() function
	 * 
	 * @param pageContent
	 * @param username
	 * @param bl
	 * @param pluginMap
	 * @return
	 */
	public static String actionOnBusinessLogicIncomingMessage(String tenantId, String accountId, String username, BusinessLogicCommunicator bl, HashMap<String, String> pluginMap, String consoleMessage) {
		logger.info("Request to call Business Logic function handleIncomingMessage()");
		logger.info("User's chat msg: ["+(consoleMessage != null ? consoleMessage : "NOT_DEFINED")+"]");
		
		de.citunius.bbp.im.gm.objects.User fromUser = new de.citunius.bbp.im.gm.objects.User();
		fromUser.setUserName("john");
		fromUser.setFirstName("John");
		fromUser.setLastName("Doe");
		
		de.citunius.bbp.im.gm.objects.User toUser = new de.citunius.bbp.im.gm.objects.User();
		toUser.setUserName("jane");
		toUser.setFirstName("jane");
		toUser.setLastName("Doe");
		Data data = new Data();
		
		Message message = new Message();
		message.setFrom(fromUser);
		message.setTo(toUser);
		message.setData(data);				
		message.setText((consoleMessage != null ? consoleMessage : "NOT_DEFINED"));

		boolean anonymousUserAccountExists = true;
		MobileAppProvider mobileAppProvider = new MobileAppProvider(tenantId, accountId, 0, "Telegram", "Telegram", "Enabled");
		AnonymousUserAccount anonymousUserAccount = new AnonymousUserAccount(tenantId, accountId, 0, "john", "John", "Doe", mobileAppProvider, new Date());
		boolean mobileUserAccountExists = false;
		String jsonMobileUserAccount = null;
		String result = bl.handleIncomingMessage(tenantId, accountId, pluginMap, message.toJson().toString(), anonymousUserAccountExists, anonymousUserAccount.toJson().toString(), mobileUserAccountExists, jsonMobileUserAccount);
		return result;
	}
	
	/**
	 * Manage action - Business Logic handleIncomingCallback() function
	 * 
	 * @param pageContent
	 * @param username
	 * @param bl
	 * @param pluginMap
	 * @return
	 */
	public static String actionOnBusinessLogicIncomingCallback(String tenantId, String accountId, String username, BusinessLogicCommunicator bl, HashMap<String, String> pluginMap, String consoleMessage) {
		logger.info("Request to call Business Logic function handleIncomingCallback()");
		logger.info("User's chat msg: ["+(consoleMessage != null ? consoleMessage : "NOT_DEFINED")+"]");
		
		de.citunius.bbp.im.gm.objects.User fromUser = new de.citunius.bbp.im.gm.objects.User();
		fromUser.setUserName("john");
		fromUser.setFirstName("John");
		fromUser.setLastName("Doe");
		
		de.citunius.bbp.im.gm.objects.User toUser = new de.citunius.bbp.im.gm.objects.User();
		toUser.setUserName("jane");
		toUser.setFirstName("jane");
		toUser.setLastName("Doe");
		Data data = new Data();
		
		Message message = new Message();
		message.setFrom(fromUser);
		message.setTo(toUser);
		message.setData(data);				
		message.setText((consoleMessage != null ? consoleMessage : "NOT_DEFINED"));

		boolean anonymousUserAccountExists = true;
		MobileAppProvider mobileAppProvider = new MobileAppProvider(tenantId, accountId, 0, "Telegram", "Telegram", "Enabled");
		AnonymousUserAccount anonymousUserAccount = new AnonymousUserAccount(tenantId, accountId, 0, "john", "John", "Doe", mobileAppProvider, new Date());
		boolean mobileUserAccountExists = false;
		String jsonMobileUserAccount = null;
		String result = bl.handleIncomingCallback(tenantId, accountId, pluginMap, message.toJson().toString(), anonymousUserAccountExists, anonymousUserAccount.toJson().toString(), mobileUserAccountExists, jsonMobileUserAccount);
		return result;
	}
	
	/**
	 * Manage action - Business Logic sendMessage() function
	 * 
	 * @param bl
	 * @param pluginMap
	 * @return
	 */
	public static String actionOnBusinessLogicSendMessage(String tenantId, String accountId, BusinessLogicCommunicator bl, HashMap<String, String> pluginMap, String consoleMessage) {
		logger.info("Request to call Business Logic function sendMessage()");
		logger.info("User's console msg: ["+(consoleMessage != null ? consoleMessage : "NOT_DEFINED")+"]");
		
		de.citunius.bbp.im.gm.objects.User fromUser = new de.citunius.bbp.im.gm.objects.User();
		fromUser.setUserName("john");
		fromUser.setFirstName("John");
		fromUser.setLastName("Doe");
		
		de.citunius.bbp.im.gm.objects.User toUser = new de.citunius.bbp.im.gm.objects.User();
		toUser.setUserName("jane");
		toUser.setFirstName("jane");
		toUser.setLastName("Doe");
		Data data = new Data();
		data.setResult(consoleMessage);
		
		Message message = new Message();
		message.setFrom(fromUser);
		message.setTo(toUser);
		message.setData(data);				
		message.setText((consoleMessage != null ? consoleMessage : "NOT_DEFINED"));

		boolean anonymousUserAccountExists = true;
		MobileAppProvider mobileAppProvider = new MobileAppProvider(tenantId, accountId, 0, "Telegram", "Telegram", "Enabled");
		AnonymousUserAccount anonymousUserAccount = new AnonymousUserAccount(tenantId, accountId, 0, "john", "John", "Doe", mobileAppProvider, new Date());
		boolean mobileUserAccountExists = false;
		String jsonMobileUserAccount = null;
		
		Filter filter = new Filter();
		
		String result = bl.sendMessage(tenantId, accountId, pluginMap,  message.toJson().toString(), filter.toJson().toString(), anonymousUserAccountExists, anonymousUserAccount.toJson().toString(), mobileUserAccountExists, jsonMobileUserAccount);
		return result;
	}
	
	/**
	 * Manage action - Business Logic isLicenseRequired() function
	 * 
	 * @param bl
	 * @return
	 */
	public static String actionOnBusinessLogicIsLicenseRequired(BusinessLogicCommunicator bl) {
		logger.info("Request to call Business Logic function isLicenseRequired()");
		boolean isLicenseRequired = bl.isLicenseRequired();
		return "Function isLicenseRequired() has been called. Business logic replied:\n\n"+(isLicenseRequired ? "true" : "false")+"";
	}
	
	/**
	 * Manage action - Business Logic isBotBuilderModelSupported() function
	 * 
	 * @param bl
	 * @return
	 */
	public static String actionOnBusinessLogicIsBotBuilderModelSupported(BusinessLogicCommunicator bl) {
		logger.info("Request to call Business Logic function isBotBuilderModelSupported()");
		boolean isBotBuilderModelSupported = bl.isBotBuilderModelSupported();
		return "Function isBotBuilderModelSupported() has been called. Business logic replied:\n\n"+(isBotBuilderModelSupported ? "true" : "false")+"";
	}
	
	/**
	 * Manage action - Business Logic isWebUISupported() function
	 * 
	 * @param bl
	 * @return
	 */
	public static String actionOnBusinessLogicIsWebUISupported(BusinessLogicCommunicator bl) {
		logger.info("Request to call Business Logic function isWebUISupported()");
		boolean isWebUISupported = bl.isWebUISupported();
		return "Function isWebUISupported() has been called. Business logic replied:\n\n"+(isWebUISupported ? "true" : "false")+"";
	}
	
	/**
	 * Manage action - Business Logic prepare() function
	 * 
	 * @param pageContent
	 * @param username
	 * @param bl
	 * @param pluginMap
	 * @return
	 */
	public static String actionOnBusinessLogicPrepare(ServletContext sc, String tenantId, String accountId, BusinessLogicCommunicator bl, HashMap<String, String> pluginMap) {
		logger.info("Request to call Business Logic function prepare()");
		String result = bl.prepare(tenantId, accountId, pluginMap, "");
		result = "Function prepare() has been called. Business logic replied:\n\n"+result+"";
		return result;
	}
	
	/**
	 * Manage action - Business Logic getBotBuilderPluginFunctions() function
	 * 
	 * @param pageContent
	 * @param username
	 * @param bl
	 * @param pluginMap
	 * @return
	 */
	public static String actionOnBusinessLogicGetBotBuilderPluginFunctions(ServletContext sc, String tenantId, String accountId, BusinessLogicCommunicator bl, HashMap<String, String> pluginMap) {
		logger.info("Request to call Business Logic function getBotBuilderPluginFunctions()");
		HashMap<String, String> map = bl.getBotBuilderPluginFunctions(Utilities.getAppProperty(sc, ConstantsPlugin.PLUGIN_SYSTEMDIR) + File.separator + Utilities.getAppProperty(sc, ConstantsPlugin.PLUGIN_RESOURCE_PATH));
		String result = "Function getBotBuilderPluginFunctions() has been called:\n\n";
		if (map != null && map.size() != 0) {
			for (Object objectName : map.keySet()) {
				result += "Param:\n["+objectName+"]\n\nValue:\n["+map.get(objectName)+"]";
				result += "\n\nPretty print:\n";
				JSONObject jsonObject = new JSONObject(map.get(objectName));
				String prettyJson = jsonObject.toString(4);
				result += prettyJson;
			}
		} else {
			result += "Map is empty. No result from business logic.";			
		}		
		return result;
	}
	
	/**
	 * Compose plugin map
	 * 
	 * @return
	 */
	public HashMap<String, String> getPluginMap(ServletContext sc) {
		HashMap<String, String> pluginMap = new HashMap<>();
		pluginMap.put(ConstantsPlugin.MESSENGER_NAME, Utilities.getAppProperty(sc, ConstantsPlugin.MESSENGER_NAME));
		pluginMap.put(ConstantsPlugin.PLUGIN_ID, Utilities.getAppProperty(sc, ConstantsPlugin.PLUGIN_ID));
		pluginMap.put(ConstantsPlugin.PLUGIN_NAME, Utilities.getAppProperty(sc, ConstantsPlugin.PLUGIN_NAME));
		
		pluginMap.put(ConstantsPlugin.TENANTID, Utilities.getAppProperty(sc, ConstantsPlugin.TENANTID));
		pluginMap.put(ConstantsPlugin.ACCOUNTID, Utilities.getAppProperty(sc, ConstantsPlugin.ACCOUNTID));
		pluginMap.put(ConstantsPlugin.PLUGIN_WEBUI_LANGUAGE, Utilities.getAppProperty(sc, ConstantsPlugin.PLUGIN_WEBUI_LANGUAGE));
		pluginMap.put(ConstantsPlugin.PLUGIN_RESOURCE_PATH, Utilities.getAppProperty(sc, ConstantsPlugin.PLUGIN_RESOURCE_PATH));
		pluginMap.put(ConstantsPlugin.BBP_WEBSERVICES_URL, Utilities.getAppProperty(sc, ConstantsPlugin.BBP_WEBSERVICES_URL));
		pluginMap.put(ConstantsPlugin.PLUGIN_WORKDIR, Utilities.getAppProperty(sc, ConstantsPlugin.PLUGIN_WORKDIR));
		pluginMap.put(ConstantsPlugin.PLUGIN_SYSTEMDIR, Utilities.getAppProperty(sc, ConstantsPlugin.PLUGIN_SYSTEMDIR));
		pluginMap.put(ConstantsPlugin.INSTANTMESSENGER_FILESTORE_PATH, Utilities.getAppProperty(sc, ConstantsPlugin.INSTANTMESSENGER_FILESTORE_PATH));
		pluginMap.put(ConstantsPlugin.BBP_APIACCOUNT_APIID, Utilities.getAppProperty(sc, ConstantsPlugin.BBP_APIACCOUNT_APIID));
		pluginMap.put(ConstantsPlugin.BBP_APIACCOUNT_APIKEY, Utilities.getAppProperty(sc, ConstantsPlugin.BBP_APIACCOUNT_APIKEY));
		return pluginMap;
	}
	
	/**
	 * Manage JSON request
	 * 
	 * @param request
	 * @param response
	 */
	public static void manageJsonRequest(HttpServletRequest request, HttpServletResponse response) {
		// Set content type
		String contentType = request.getParameter("ct");
		if (contentType != null && contentType.length() != 0) {
			response.setContentType(contentType);
		} else {
			logger.debug("Unknown content type for file download. Using content type [application/json]");
			response.setContentType("application/json");
		}
	}
	
	/**
	 * Get page - Business Logic - tab WebUI
	 * @return
	 */
	public String getPageBusinessLogicTabWebUI(ServletContext servletContext) {
		String pageContent = "";
		// Put page content into developer view
		String pageOutputBusinessLogicTabUI = "";
		try {
			Template template = freemarkerConfig.getTemplate("part_businesslogic_tab_webui.ftl");
			Writer out = new StringWriter(); 
			Map<String, Object> data = new HashMap<String, Object>();
			
			String[] parts = Utilities.getAppProperty(servletContext, ConstantsPlugin.PLUGIN_NAME).split("\\\\"); // String array, each element is text between dots
			String beforeFirstBackslash = parts[0]; 
			data.put("plugin_name", beforeFirstBackslash);
			
			// Console buttons
			data.put("button_bl_isLicenseRequired_url", "IndexServletDeveloperUI?id="+businessBotPluginIdDummy+"&action="+ConstantsActions.ACTION_BUSINESSLOGIC_IS_WEBUI_SUPPORTED+"&format=json");
			data.put("button_bl_isBotBuilderModelSupported_url", "IndexServletDeveloperUI?id="+businessBotPluginIdDummy+"&action="+ConstantsActions.ACTION_BUSINESSLOGIC_IS_BOTBUILDERMODEL_SUPPORTED+"&format=json");
			data.put("button_bl_isWebUISupported_url", "IndexServletDeveloperUI?id="+businessBotPluginIdDummy+"&action="+ConstantsActions.ACTION_BUSINESSLOGIC_IS_WEBUI_SUPPORTED+"&format=json");
			
			data.put("button_bl_prepare_url", "IndexServletDeveloperUI?id="+businessBotPluginIdDummy+"&action="+ConstantsActions.ACTION_BUSINESSLOGIC_PREPARE_BL+"&format=json");
			data.put("button_bl_getbotbuilderfunctions_url", "IndexServletDeveloperUI?id="+businessBotPluginIdDummy+"&action="+ConstantsActions.ACTION_BUSINESSLOGIC_GET_BOTBUILDERFUNCTIONS+"&format=json");
			
			data.put("button_bl_webui_external_webui_url", "IndexServlet?id="+businessBotPluginIdDummy);
			data.put("number", ""+businessBotPluginIdDummy);
			data.put("bl_webui_url", "IndexServlet?id="+businessBotPluginIdDummy);
			
			data.put("bl_handleIncomingMessage_url", "IndexServletDeveloperUI?id="+businessBotPluginIdDummy+"&action="+ConstantsActions.ACTION_BUSINESSLOGIC_HANDLE_INCOMING_MESSAGE+"&format=json");
			data.put("bl_action_url", "IndexServletDeveloperUI?id="+businessBotPluginIdDummy+"&format=json");
			data.put("bl_smallview_webui_url", "IndexServlet?id="+businessBotPluginIdDummy);
			template.process(data, out);
			pageOutputBusinessLogicTabUI = out.toString();
		} catch (IOException e) {
			logger.error("IOException: ", e);
		} catch (TemplateException e) {
			logger.error("TemplateException: ", e);
		}				
		pageContent = pageOutputBusinessLogicTabUI;
		return pageContent;
	}
	
	/**
	 * Get page - Business Logic - tab Settings
	 * @return
	 */
	public String getPageBusinessLogicTabSettings() {
		String pageContent = "";
		// Put page content into developer view
		String pageOutputBusinessLogicTabUI = "";
		try {
			Template template = freemarkerConfig.getTemplate("part_businesslogic_tab_settings.ftl");
			Writer out = new StringWriter(); 
			Map<String, Object> data = new HashMap<String, Object>();
			
			String tableHeader ="<thead>" +
			        "  <tr>" +
			        "    <th>Name</th>" +
			        "    <th>Value</th>" +		
			        "  </tr>" +
			        "</thead>";

			HashMap<String, String> appProps = Utilities.listAppProperties(session.getServletContext());
			String tableOutput = "";
			String tableData = "";
			if ( appProps != null && appProps.size() != 0 ) {	
				boolean itemsetIsColored = false;
				tableData += "<tbody>";
				for (Object obj : appProps.entrySet()) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) obj;
					if (itemsetIsColored) {
						tableData += "<tr class=\"odd gradeX\">";
						itemsetIsColored = false;
					} else {
						tableData += "<tr class=\"even gradeC\">";
						itemsetIsColored = true;
					}
					tableData += "<td style=\"overflow: hidden;white-space: nowrap;text-overflow: ellipsis;display: block;\">"+entry.getKey()+"</td>";
					tableData += "<td style=\"overflow: hidden;white-space: nowrap;text-overflow: ellipsis;display: block;\">"+entry.getValue()+"</td>";
					tableData += "</tr>";					
				}
				tableData += "</tbody>";			
			}
			tableOutput = tableHeader + "\n" + tableData;
			
			data.put("config_file", Utilities.getConfigFilePath());
			data.put("tablecontent", tableOutput);			
			template.process(data, out);
			pageOutputBusinessLogicTabUI = out.toString();
		} catch (IOException e) {
			logger.error("IOException: ", e);
		} catch (TemplateException e) {
			logger.error("TemplateException: ", e);
		}				
		pageContent = pageOutputBusinessLogicTabUI;
		return pageContent;
		
	}
	
	/**
	 * Get page - Business Logic - tab About
	 * @return
	 */
	public String getPageBusinessLogicTabAbout() {
		String pageContent = "";
		// Put page content into developer view
		String pageOutputBusinessLogicTabUI = "";
		try {
			Template template = freemarkerConfig.getTemplate("part_businesslogic_tab_about.ftl");
			Writer out = new StringWriter(); 
			Map<String, Object> data = new HashMap<String, Object>();
			
			String tableHeader ="<thead>" +
			        "  <tr>" +
			        "    <th>Name</th>" +
			        "    <th>Value</th>" +					        
			        "  </tr>" +
			        "</thead>";
			
			String tableOutput = "";
			String tableData = "";
			boolean itemsetIsColored = false;
			tableData += "<tbody>";
			
			Properties p = System.getProperties();
			Enumeration keys = p.keys();
			while (keys.hasMoreElements()) {
			    String key = (String)keys.nextElement();
			    String value = (String)p.get(key);
			    
			    if (itemsetIsColored) {
					tableData += "<tr class=\"odd gradeX\">";
					itemsetIsColored = false;
				} else {
					tableData += "<tr class=\"even gradeC\">";
					itemsetIsColored = true;
				}
				tableData += "<td style=\"overflow: hidden;white-space: nowrap;text-overflow: ellipsis;display: block;\">"+key+"</td>";
				tableData += "<td style=\"overflow: hidden;white-space: nowrap;text-overflow: ellipsis;display: block;\">"+value+"</td>";
				
				tableData += "</tr>";	
			}
			
			tableData += "</tbody>";
			tableOutput = tableHeader + "\n" + tableData;
			
			data.put("config_file", Utilities.getConfigFilePath());
			data.put("tablecontent", tableOutput);			
			template.process(data, out);
			pageOutputBusinessLogicTabUI = out.toString();
		} catch (IOException e) {
			logger.error("IOException: ", e);
		} catch (TemplateException e) {
			logger.error("TemplateException: ", e);
		}				
		pageContent = pageOutputBusinessLogicTabUI;
		return pageContent;
		
	}
	
	/**
	 * Manage file download request
	 * 
	 * @param request
	 * @param response
	 * @param pageContent
	 * @throws IOException
	 */
	public void manageFileDownloadRequest(HttpServletRequest request, HttpServletResponse response, String pageContent) throws IOException {
		// Set content type
		String contentType = request.getParameter("ct");
		if (contentType != null && contentType.length() != 0) {
			response.setContentType(contentType);
		} else {
			logger.warn("Unknown content type for file download. Using content type [application/octet-stream]");
			response.setContentType("application/octet-stream");
		}
		
		// Set download filename
		String filename = request.getParameter("filename");
		if (filename != null && filename.length() != 0) {
			logger.info("Using defined filename ["+filename+"]");
		} else {
			logger.warn("Unknown filename for download not defined. Using default filename [file.csv]");
			filename = "file.csv";
		}
		filename = URLEncoder.encode(filename, "UTF-8");
		response.setHeader("Content-disposition", "attachment; filename="+ filename);
		
		ServletOutputStream out = null;
		out = response.getOutputStream();
		out.write(pageContent.getBytes());
		out.close();
	}
	
	/**
	 * Modify page content of business logic page content
	 * 
	 * @param pageContentBusinessLogic
	 * @return
	 */
	public static String modifyBusinessLogicPageContent(String pageContentBusinessLogic) {
		// Replace the relative web resource paths from HTML page to match the paths of this project  
		pageContentBusinessLogic = StringUtils.replace(pageContentBusinessLogic, "../common/", "common/");
		pageContentBusinessLogic = StringUtils.replace(pageContentBusinessLogic, "../bower_components/", "bower_components/");
		pageContentBusinessLogic = StringUtils.replace(pageContentBusinessLogic, "../dist/", "dist/");
		pageContentBusinessLogic = StringUtils.replace(pageContentBusinessLogic, "../plugins/", "plugins/");
		pageContentBusinessLogic = StringUtils.replace(pageContentBusinessLogic, businessBotPluginIdDummy+"?p=", "IndexServlet?p="); // GET method
		pageContentBusinessLogic = StringUtils.replace(pageContentBusinessLogic, "action=\""+businessBotPluginIdDummy+"?p=", "action=\"IndexServlet?p="); // form POST method
		return pageContentBusinessLogic;
	}
	
	
}

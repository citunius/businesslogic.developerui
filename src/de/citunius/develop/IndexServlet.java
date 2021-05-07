package de.citunius.develop;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class TestWebUI
 * 
 * <p>This servlet calls the Webpage interface of the business logic and provides the HTML result as output</p>
 */
public class IndexServlet extends HttpServlet {
	static Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass());
	private static final long serialVersionUID = 2111348979089418870L;
	private HttpSession session = null;
	private String tenantId = "demo";
	private String accountId = "ALL_ACCOUNTS";
	private boolean isAuthenticated = false;
	public static String encoding = "UTF-8";
	public static Configuration freemarkerConfig = null;
	public static int businessBotPluginIdDummy = 1;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
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
			manageRequest(request, response);
		} else {
			String pageOutput = manageRequest(request, response);
			logger.info("Business logic content page size: ["+pageOutput.length()+"]");
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
			manageRequest(request, response);
		} else {
			String pageOutput = manageRequest(request, response);
			logger.info("Business logic content page size: ["+pageOutput.length()+"]");
			PrintWriter out = response.getWriter();	
			out.print(pageOutput);	
		}	
	}
	
	public String manageRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		
		// Invoke plugin
		BusinessLogicCommunicator bl;
		try {
			bl = new BusinessLogicCommunicator(request.getSession().getServletContext());
			boolean isWebUISupported = bl.isWebUISupported();
			logger.trace("isWebUISupported: ["+isWebUISupported+"]");
			if (isWebUISupported) {
				HashMap<String, String> pluginMap = getPluginMap(request.getServletContext());
				pageContent = bl.getWebUIPage(tenantId, accountId, pluginMap, request.getParameterMap());
				logger.info("Business logic WebUI content page size: ["+pageContent.length()+"]");
				
				// Replace the relative web resource paths from HTML page to match the paths of this project  
				pageContent = StringUtils.replace(pageContent, "../common/", "common/");
				pageContent = StringUtils.replace(pageContent, "../bower_components/", "bower_components/");
				pageContent = StringUtils.replace(pageContent, "../dist/", "dist/");
				pageContent = StringUtils.replace(pageContent, "../plugins/", "plugins/");
				pageContent = StringUtils.replace(pageContent, businessBotPluginIdDummy+"?p=", "IndexServlet?p="); // GET method
				pageContent = StringUtils.replace(pageContent, "action=\"0?p=", "action=\"IndexServlet?p="); // form POST method
				
				// Check if page request is a json request, if yes, then change the response header to 'application/json'
				if (request.getParameter("format") != null && request.getParameter("format").equalsIgnoreCase("json")) {
					// Set content type
					String contentType = request.getParameter("ct");
					if (contentType != null && contentType.length() != 0) {
						response.setContentType(contentType);
					} else {
						logger.warn("Unknown content type for file download. Using content type [application/json]");
						response.setContentType("application/json");
					}
					
				} else if (request.getParameter("dl") != null && request.getParameter("dl").equalsIgnoreCase("yes")) {
					// Check if page request is a file download request, if yes, then change the response header for file downloads
					
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
				
				logger.info("Business logic WebUI embedded content page size: ["+pageContent.length()+"]");
			} else {
				pageContent += "This business chatbot logic does not offer a WebUI.";
			}
			return pageContent;
		} catch (ClassNotFoundException e) {
			return ("ClassNotFoundException: "+e.getMessage());
		}
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
	
}

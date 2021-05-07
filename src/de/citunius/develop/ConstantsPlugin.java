/*
 * Copyright: (c) 2015-2017, Citunius GmbH. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Licence: This program contains proprietary and trade secret information of Citunius GmbH.
 *          Copyright notice is precautionary only and does not evidence any actual or intended 
 *          publication of such program
 *          See: https://www.citunius.de/en/legal
 *
 * Requires: JDK 1.8+
 *
 */
package de.citunius.develop;

public class ConstantsPlugin {

	public static String BUSINESSLOGIC_CLASSNAME = "BusinessLogic.ClassName";
	
	public static String MESSENGER_NAME = "messengerName";
	public static String PLUGIN_ID = "pluginId";
	public static String PLUGIN_NAME = "pluginName";
	public static String BOTID_NOTUSED = "botId";
	public static String MOBILE_APP_PROVIDER_NOTUSED = "mobileAppProvider";
	public static String PLUGIN_RESOURCE_PATH = "pluginResourcesPath";
	public static String INSTANTMESSENGER_FILESTORE_PATH = "instantMessengerFileStorePath";
	public static String TENANTID = "tenantId";
	public static String ACCOUNTID = "accountId";
	public static String BBP_WEBSERVICES_URL = "bbpWebservicesUrl";
	public static String PLUGIN_WORKDIR = "pluginWorkDir";
	public static String PLUGIN_SYSTEMDIR = "pluginSystemDir";
	public static String PLUGIN_WEBUI_LANGUAGE = "pluginWebUILanguage";
	
	// API account credentials (if business logic want to call the BBP webservices)
	public static String BBP_APIACCOUNT_APIID = "BusinessBotPlatform.Account.ApiId";
	public static String BBP_APIACCOUNT_APIKEY = "BusinessBotPlatform.Account.ApiKey";
		
	public static String BOTBUILDER_FUNCTION_NAME = "botBuilderFunctionName";
	public static String BOTBUILDER_FUNCTION_PARAM = "botBuilderFunctionParameter";
	
	// System Integrations
	public static String NUMBER_OF_SYSTEMINTEGRATIONS = "numberOfSystemIntegrations";
	/*
	 * Note: Variable name for System Integrations as follows (increasing number):
	 *  	SystemIntegration.0.SystemIdentifier
	 *  	SystemIntegration.0.SystemName
	 *  	SystemIntegration.0.SystemType
	 *  	SystemIntegration.0.SystemRelease
	 *  	SystemIntegration.0.WebhookAddress
	 *  	SystemIntegration.0.WebhookToken
	 */
	
	// Constants of returned plugin preparation status
	public static String PLUGIN_PREPARATIONSTATUS_SUCCESS = "OK";
	public static String PLUGIN_PREPARATIONSTATUS_FAILED = "FAILED";
	
	// Constants for logging.properties in resource folder
	public static String PLUGIN_LOGGING_FILEPATH = "Logging.FilePath";
	public static String PLUGIN_LOGGING_NAME = "Logging.Name";
	public static String PLUGIN_LOGGING_ENCODING = "Logging.Encoding";
	public static String PLUGIN_LOGGING_MAXFILESIZE = "Logging.MaxFileSize";
	public static String PLUGIN_LOGGING_MAXBACKUPINDEX = "Logging.MaxBackupIndex";
	public static String PLUGIN_LOGGING_THRESHOLD = "Logging.Threshold";
	public static String PLUGIN_LOGGING_LEVEL = "Logging.Level";
	public static String PLUGIN_LOGGING_ADDITIVITY = "Logging.Additivity";
	
	// Default logging value in case that logging.properties file in business bot resource folder is not defined
	public static String DEFAULT_VALUE_PLUGIN_LOGGING_FILEPATH = "${catalina.home}/logs/"; // filename is added by function
	public static String DEFAULT_VALUE_PLUGIN_LOGGING_NAME = "de.citunius.businessbot";
	public static String DEFAULT_VALUE_PLUGIN_LOGGING_ENCODING = "UTF-8";
	public static String DEFAULT_VALUE_PLUGIN_LOGGING_MAXFILESIZE = "5000KB";
	public static int DEFAULT_VALUE_PLUGIN_LOGGING_MAXBACKUPINDEX = 25;
	public static String DEFAULT_VALUE_PLUGIN_LOGGING_THRESHOLD = "WARN";
	public static String DEFAULT_VALUE_PLUGIN_LOGGING_LEVEL = "WARN";
	public static boolean DEFAULT_VALUE_PLUGIN_LOGGING_ADDITIVITY = false;
	
	// Business Logic config.properties settings
	public static String BL_PROP_LICENSE_PUBLICKEY = "License.PublicKey";
	public static String BL_PROP_LICENSE_PRODUCTID = "License.ProductId";
	public static String BL_PROP_LICENSE_PRODUCTEDITION = "License.ProductEdition";
	public static String BL_PROP_LICENSE_PRODUCTVERSION = "License.ProductVersion";
	
	
}

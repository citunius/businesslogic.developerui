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
 * $Id: ConstantsUtil.java 35 2020-01-23 14:47:07Z  $
 *
 */
package de.citunius.develop;

/**
 * This class holds all filesystem related constants of the 
 * Mobile Message Gateway such as mail properties. The constants hold the
 * parameter names defined in the mmg.properties file. 
 *
 * @author me
 * @version $Rev: 35 $, $Date: 2020-01-23 15:47:07 +0100 (Thu, 23 Jan 2020) $ - Copyright: (c) 2017-2020, Citunius GmbH
 * @since   1.0
*/
public class ConstantsUtil {
    // bbp.properties
	public static final String DEVELOPERUI_PROP_FILENAME = "config.properties";
	public static final String LOG4J_PROP_FILENAME = "log4j.properties";
	
	// Session parameters
	public static final String DEVELOPERUI_SESSION_CONFIGPROPS = "config_props";
}

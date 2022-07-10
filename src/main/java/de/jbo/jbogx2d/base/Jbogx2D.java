//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: JBogx2D.java
// Created: 28.02.2004 - 20:56:14
//

package de.jbo.jbogx2d.base;

import java.io.InputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;

import de.jbo.jbogx2d.base.attrib.AttributeHandler;
import de.jbo.jbogx2d.base.error.ErrorHandler;
import de.jbo.jbogx2d.base.error.ErrorHandlerBase;

/**
 * Implements the base-module for this API. <br>
 * Basic functionalities can be triggered: <br>
 * <ul>
 * <li>Initialization of the API</li>
 * <li>Access to ErrorHandler</li>
 * <li>Access to AttributeHandler</li>
 * <li>Loading of Properties</li>
 * <li>Getting and Setting of Properties</li>
 * </ul>
 * Before functions of the API can be accessed, this base module has to be
 * initialized first.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 28.02.2004: jbo created <br>
 */
public final class Jbogx2D {

    /** The major-version. */
    private static final int VERSION_MAJOR = 1;

    /** The minor-version. */
    private static final int VERSION_MINOR = 0;

    /** The bugfix-version. */
    private static final int VERSION_BUGFIX = 0;

    /** The current version. */
    private static final Version VERSION;

    /** Current locale for internationalization. */
    private static Locale locale = Locale.getDefault();

    /** Indicates if the module was initialized correctly. */
    private static boolean isInitialized = false;

    /** The properties of this module. */
    private static Properties properties = null;

    /** The currently registered error-handler. */
    private static ErrorHandler errorHandler = null;

    /** The main attribute handler. */
    private static AttributeHandler attributeHandler = null;

    static {
        Version version = Version.getVersionFromBuild(Jbogx2D.class);

        if (version == null) {
            GregorianCalendar buildDate = new GregorianCalendar();
            buildDate.setTime(new Date());
            version = new Version(VERSION_MAJOR, VERSION_MINOR, VERSION_BUGFIX, buildDate);
        }

        VERSION = version;
    }

    /**
     * Constructor.
     */
    private Jbogx2D() {

    }

    /**
     * Initializes with the given local for internationalization.
     * 
     * @param loc
     *            The locale to be set.
     * @return True if successful, False otherwise.
     * @see #getLocale()
     */
    public static boolean init(Locale loc) {
        locale = loc;
        return init();
    }

    /**
     * Initializes the base module. <br>
     * This has to be done at start-up in order to activate the base
     * functionality, before any further operation can be processed.
     * 
     * @return True if initialization was successful, False otherwise. In the
     *         second case an error occured and the module can not be handled
     *         correctly. If so, the ErrorHandler can be checked to analyse the
     *         cause of the failure.
     */
    public static boolean init() {
        boolean state = false;

        errorHandler = new ErrorHandlerBase();

        try {
            /*
             * Add initialization here...
             */
            attributeHandler = new AttributeHandler();
            properties = new Properties();
            /*
             * End of initialization
             */
            state = true;
            isInitialized = state;
        } catch (Throwable ex) {
            getErrorHandler().handleError(ex, true, true);
        }

        return state;
    }

    /**
     * @return The current locale used for internationalization.
     */
    public static Locale getLocale() {
        return locale;
    }

    /**
     * Closes this module. After this call the main-functionality provided by
     * this module is not available.
     * 
     * @return True if the module was closed successfully, False otherwise. In
     *         this case the ErrorHandler can be checked.
     */
    public static boolean close() {
        boolean state = false;

        try {
            /*
             * add un-initialization here...
             */

            /*
             * end of un-initialization
             */
            state = true;
            isInitialized = false;
        } catch (Throwable ex) {
            getErrorHandler().handleError(ex, true, true);
        }

        return state;
    }

    /**
     * Registers the given ErrorHandler to handle errors and exceptions.
     * 
     * @param errHandler
     *            The handler to be registered.
     */
    public static void setErrorHandler(final ErrorHandler errHandler) {
        if (errHandler == null) {
            throw new IllegalArgumentException("You MUST provide a valid ErrorHandler instance!");
        }
        errorHandler = errHandler;
    }

    /**
     * Returns the currently set error handler.
     * 
     * @return The error handler currently registered.
     */
    public static ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    /**
     * Returns the attribute handler.
     * 
     * @return The attribute handler.
     */
    public static AttributeHandler getAttributeHandler() {
        return attributeHandler;
    }

    /**
     * Loads application properties from the given input-stream.
     * 
     * @param is
     *            The stream to load a property file from.
     * @return True if successful. If False, check ErrorHandler.
     * @see #getErrorHandler()
     */
    public static boolean loadProperties(final InputStream is) {
        boolean state = false;

        try {
            properties.load(is);
            state = true;
        } catch (Exception e) {
            getErrorHandler().handleError(e, true, true);
        }

        return state;
    }

    /**
     * Sets the given property.
     * 
     * @param key
     *            The key identifying the property to be set.
     * @param value
     *            The value to be stored.
     */
    public static void setProperty(final String key, final String value) {
        properties.setProperty(key, value);
    }

    /**
     * Returns the property currently stored under the given key.
     * 
     * @param key
     *            The key for which the stored property shall be returned.
     * @return The property value stored under the given key.
     */
    public static String getProperty(final String key) {
        return properties.getProperty(key);
    }

    /**
     * @return the isInitialized
     */
    public static boolean isInitialized() {
        return isInitialized;
    }

    /**
     * @return The current version.
     */
    public static Version getVersion() {
        return VERSION;
    }
}
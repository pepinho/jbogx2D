//
// Copyright (c) 2013 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    Jbogx2DTest.java
// Created: 03.11.2013 - 10:36:01
//
package de.jbo.jbogx2d.test.junit.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import de.jbo.jbogx2d.base.Jbogx2D;
import de.jbo.jbogx2d.base.error.ErrorHandler;

/**
 * @author Josef Baro (jbo)
 * @version 03.11.2013 jbo - created
 */
public class Jbogx2DTest {

    /**
     * Test error-handler.
     * 
     * @author Josef Baro (jbo)
     * @version 03.11.2013 jbo - created
     */
    private class MyErrorHandler extends ErrorHandler {

        /*
         * (non-Javadoc)
         * 
         * @see
         * de.jbo.jbogx2d.base.error.ErrorHandler#handleError(java.lang.Throwable
         * )
         */
        @Override
        protected void handleError(Throwable ex) {

        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * de.jbo.jbogx2d.base.error.ErrorHandler#handleFatalError(java.lang
         * .Throwable)
         */
        @Override
        protected void handleFatalError(Throwable ex) {

        }

    }

    /** Invalid properties file. */
    private static final String INVALID_PROPERTIES_FILE = "invalid_properties.properties";

    /** Test property-name. */
    private static final String PROPERTY_NAME = "myProperty";

    /** Test property-value. */
    private static final String PROPERTY_VALUE = "myValue";

    /** Test properties. */
    private static Properties properties = new Properties();

    /**
     * Properties content.
     */
    private static byte[] propertiesContent = null;

    /**
     * Static set-up.
     */
    @BeforeClass
    public static void setUpClass() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            properties.setProperty(PROPERTY_NAME, PROPERTY_VALUE);
            properties.store(bos, "My test properties...");
            propertiesContent = bos.toByteArray();
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.Jbogx2D#init(java.util.Locale)}.
     */
    @Test
    public void testInitLocale() {
        Locale locale = new Locale("sr", "RS");
        assertTrue(Jbogx2D.init(locale));
        assertEquals("The locale doesn't match.", locale, Jbogx2D.getLocale());
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.Jbogx2D#init()}.
     */
    @Test
    public void testInit() {
        assertTrue(Jbogx2D.init());
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.Jbogx2D#getLocale()}.
     */
    @Test
    public void testGetLocale() {
        Locale locale = new Locale("sr", "RS");
        assertTrue(Jbogx2D.init(locale));
        assertEquals("The locale doesn't match.", locale, Jbogx2D.getLocale());
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.Jbogx2D#close()}.
     */
    @Test
    public void testClose() {
        assertTrue(Jbogx2D.close());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.Jbogx2D#setErrorHandler(de.jbo.jbogx2d.base.error.ErrorHandler)}
     * .
     */
    @Test
    public void testSetErrorHandler() {
        MyErrorHandler eh = new MyErrorHandler();
        Jbogx2D.setErrorHandler(eh);
        assertNotNull("The error-handler was not set correctly.", Jbogx2D.getErrorHandler());
        assertEquals("The error-handler was not set correctly.", eh, Jbogx2D.getErrorHandler());

        try {
            Jbogx2D.setErrorHandler(null);
            fail("This should trigger a RuntimeException!");
        } catch (Exception e) {
            assertTrue(true);
        }

    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.Jbogx2D#getErrorHandler()}.
     */
    @Test
    public void testGetErrorHandler() {
        MyErrorHandler eh = new MyErrorHandler();
        Jbogx2D.setErrorHandler(eh);
        assertNotNull("The error-handler was not set correctly.", Jbogx2D.getErrorHandler());
        assertEquals("The error-handler was not set correctly.", eh, Jbogx2D.getErrorHandler());
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.Jbogx2D#getAttributeHandler()}
     * .
     */
    @Test
    public void testGetAttributeHandler() {
        assertNotNull("The attribute-handler was not set correctly.", Jbogx2D.getAttributeHandler());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.Jbogx2D#loadProperties(java.io.InputStream)}.
     */
    @Test
    public void testLoadProperties() {
        InputStream bis = new ByteArrayInputStream(propertiesContent);
        assertTrue("Properties could not be loaded.", Jbogx2D.loadProperties(bis));
        try {
            bis.close();
        } catch (IOException e) {
            fail(e.getMessage());
        }
        try {
            bis = getClass().getResourceAsStream(INVALID_PROPERTIES_FILE);
            assertFalse("Properties should not be loaded.", Jbogx2D.loadProperties(bis));
            assertTrue("There should be an error in the handler's stack.", Jbogx2D.getErrorHandler().isErrorAvailable());
            Throwable ex = Jbogx2D.getErrorHandler().getLastError(false);
            assertNotNull("There should be an error in the handler's stack.", ex);
            bis.close();
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.Jbogx2D#setProperty(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testSetProperty() {
        Jbogx2D.setProperty("myKey", "myValue");
        String value = Jbogx2D.getProperty("myKey");
        assertNotNull("The property 'myKey' could not be found.", value);
        assertEquals("The property 'myKey' has the wrong value.", "myValue", value);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.Jbogx2D#getProperty(java.lang.String)}.
     */
    @Test
    public void testGetProperty() {
        testLoadProperties();
        String value = Jbogx2D.getProperty(PROPERTY_NAME);
        assertNotNull("The property '" + PROPERTY_NAME + "' could not be found.", value);
        assertEquals("The property '" + PROPERTY_NAME + "' has the wrong value.", PROPERTY_VALUE, value);
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.Jbogx2D#isInitialized()}.
     */
    @Test
    public void testIsInitialized() {
        Jbogx2D.init();
        assertTrue("Jbogx2D is NOT initialized.", Jbogx2D.isInitialized());
        Jbogx2D.close();
        assertFalse("Jbogx2D should NOT be initialized.", Jbogx2D.isInitialized());
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.Jbogx2D#getVersion()}.
     */
    @Test
    public void testGetVersion() {
        assertNotNull(Jbogx2D.getVersion());
    }

}

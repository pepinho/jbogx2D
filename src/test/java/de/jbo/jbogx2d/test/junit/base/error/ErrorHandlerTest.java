//
// Copyright (c) 2013 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    ErrorHandlerTest.java
// Created: 22.12.2013 - 16:55:05
//
package de.jbo.jbogx2d.test.junit.base.error;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.jbo.jbogx2d.base.error.ErrorHandler;

/**
 * Tests ErrorHandler classes.
 * 
 * @author Josef Baro (jbo)
 * @version 22.12.2013 jbo - created
 */
public class ErrorHandlerTest {
    /** Instance to be tested. */
    private static MyErrorHandler errorHandler = null;

    /**
     * Set-up test-class.
     * 
     * @throws Exception
     *             Possible exception.
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        errorHandler = new MyErrorHandler();
    }

    /**
     * Set-up before each test.
     */
    @Before
    public void beforeEachTest() {
        errorHandler.clearFlags();
        errorHandler.reset();
    }

    /**
     * Tests availability of errors in the handler.
     */
    @Test
    public void testIsErrorAvailable() {
        assertFalse(errorHandler.isErrorAvailable());
        Exception ex = new Exception();
        errorHandler.handleError(ex, true, true);
        assertTrue(errorHandler.isErrorAvailable());
    }

    /**
     * Tests retrieval of errors-stack.
     */
    @Test
    public void testGetErrorStack() {
        Exception ex = new Exception();
        errorHandler.handleError(ex, true, true);
        Throwable[] errors = errorHandler.getErrorStack(false);
        assertNotNull(errors);
        assertEquals(1, errors.length);
        assertEquals(ex, errors[0]);
        assertTrue(errorHandler.isErrorAvailable());

        errors = errorHandler.getErrorStack(true);
        assertNotNull(errors);
        assertEquals(1, errors.length);
        assertEquals(ex, errors[0]);
        assertFalse(errorHandler.isErrorAvailable());
    }

    /**
     * Tests storage of errors.
     */
    @Test
    public void testStoreError() {
        Exception ex = new Exception();
        errorHandler.handleError(ex, true, true);
        Throwable[] errors = errorHandler.getErrorStack(false);
        assertNotNull(errors);
        assertEquals(1, errors.length);
        assertEquals(ex, errors[0]);
        assertTrue(errorHandler.isErrorAvailable());
    }

    /**
     * Tests reset of instance.
     */
    @Test
    public void testReset() {
        Exception ex = new Exception();
        errorHandler.handleError(ex, true, true);
        Throwable[] errors = errorHandler.getErrorStack(false);
        assertNotNull(errors);
        assertEquals(1, errors.length);
        assertEquals(ex, errors[0]);
        assertTrue(errorHandler.isErrorAvailable());
        errorHandler.reset();
        assertFalse(errorHandler.isErrorAvailable());
    }

    /**
     * Tests error-handling.
     */
    @Test
    public void testHandleErrorThrowableBooleanBoolean() {
        Exception ex = new Exception();
        errorHandler.handleError(ex, true, true);
        Throwable[] errors = errorHandler.getErrorStack(false);
        assertNotNull(errors);
        assertEquals(1, errors.length);
        assertEquals(ex, errors[0]);
        assertTrue(errorHandler.isErrorAvailable());
        errorHandler.clearFlags();
        Exception ex2 = new Exception();
        errorHandler.handleError(ex2, false, false);
        assertTrue(errorHandler.hasHandledError);
        errors = errorHandler.getErrorStack(false);
        assertNotNull(errors);
        assertEquals(1, errors.length);
        assertEquals(ex, errors[0]);
        errorHandler.clearFlags();
        errorHandler.handleError(ex2, false, true);
        assertTrue(errorHandler.hasHandledError);
        errors = errorHandler.getErrorStack(false);
        assertNull(errors);
        assertFalse(errorHandler.isErrorAvailable());
    }

    /**
     * Tests fatal-handling.
     */
    @Test
    public void testHandleFatalErrorThrowableBooleanBoolean() {
        Exception ex = new Exception();
        errorHandler.handleFatalError(ex, true, true);
        Throwable[] errors = errorHandler.getErrorStack(false);
        assertNotNull(errors);
        assertEquals(1, errors.length);
        assertEquals(ex, errors[0]);
        assertTrue(errorHandler.isErrorAvailable());
        errorHandler.clearFlags();
        Exception ex2 = new Exception();
        errorHandler.handleFatalError(ex2, false, false);
        assertTrue(errorHandler.hasHandledFatal);
        errors = errorHandler.getErrorStack(false);
        assertNotNull(errors);
        assertEquals(1, errors.length);
        assertEquals(ex, errors[0]);
        errorHandler.clearFlags();
        errorHandler.handleFatalError(ex2, false, true);
        assertTrue(errorHandler.hasHandledFatal);
        errors = errorHandler.getErrorStack(false);
        assertNull(errors);
        assertFalse(errorHandler.isErrorAvailable());
    }

    /**
     * Tests retrieval of last-error.
     */
    @Test
    public void testGetLastError() {
        Exception ex = new Exception();
        errorHandler.handleError(ex, true, true);
        Exception ex2 = new Exception();
        errorHandler.handleError(ex2, true, false);
        assertEquals(ex2, errorHandler.getLastError(false));
        assertTrue(errorHandler.isErrorAvailable());
        assertEquals(ex2, errorHandler.getLastError(true));
        assertFalse(errorHandler.isErrorAvailable());
    }

    /**
     * Test instance.
     * 
     * @author Josef Baro (jbo)
     * @version 22.12.2013 jbo - created
     */
    private static class MyErrorHandler extends ErrorHandler {
        /** Indicates if error was handled. */
        private boolean hasHandledError = false;

        /** Indicates if fatal was handled. */
        private boolean hasHandledFatal = false;

        /**
         * Clears handling indicator flags.
         */
        public void clearFlags() {
            hasHandledError = false;
            hasHandledFatal = false;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * de.jbo.jbogx2d.base.error.ErrorHandler#handleError(java.lang.Throwable
         * )
         */
        @Override
        protected void handleError(Throwable ex) {
            hasHandledError = true;
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
            hasHandledFatal = true;
        }

    }

}

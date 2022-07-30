//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ErrorHandlerBase.java
// Created: 28.02.2004 - 21:41:39
//

package de.jbo.jbogx2d.base.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements the default error-handler for the base module of jbogx2D. <br>
 * Errors stacktraces are printed to the standard err-console and fatal-error
 * exit the application.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 28.02.2004: jbo created <br>
 */
public class ErrorHandlerBase extends ErrorHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerBase.class);
    
    /*
     * @see
     * de.jbo.jbogx2d.base.error.ErrorHandler#handleError(java.lang.Throwable)
     */
    @Override
    protected void handleError(Throwable ex) {
        LOGGER.error(ex.getMessage(), ex);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.error.ErrorHandler#handleFatalError(java.lang.Throwable
     * )
     */
    @Override
    protected void handleFatalError(Throwable ex) {
        LOGGER.error(ex.getMessage(), ex);
        System.exit(-1);
    }

}
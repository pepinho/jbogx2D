//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File:    ErrorHandler.java
// Created: 28.02.2004 - 21:07:36
//

package de.jbo.jbogx2d.base.error;

import java.util.Vector;

/**
 * Implements the abstract base-class for the error-handling.
 * 
 * @author Josef Baro (jbo) <br>
 * 
 * @version 1.0 28.02.2004: jbo created <br>
 */
public abstract class ErrorHandler {
    /** The vector storing stacked non-fatal Exceptions during an operation. */
    protected final Vector<Throwable> exceptionStack = new Vector<Throwable>();

    /**
     * Returns the count of exceptions currently stored in the exception stack.
     * 
     * @return The count of exceptions currently stored.
     */
    public final boolean isErrorAvailable() {
        return (exceptionStack.size() > 0);
    }

    /**
     * Returns all Exception currently stored in the stack-trace within an
     * array.
     * 
     * @param resetStack
     *            True to resest the stack after collecting the stored errors.
     * 
     * @return The array with all exception currently stored in the stack.
     */
    public final Throwable[] getErrorStack(boolean resetStack) {
        int exCount = exceptionStack.size();
        Throwable[] stackTrace = null;

        if (exCount > 0) {
            stackTrace = new Throwable[exCount];
            exceptionStack.toArray(stackTrace);

            if (resetStack) {
                reset();
            }
        }

        return stackTrace;
    }

    /**
     * Stores the given exception in the stack.
     * 
     * @param ex
     *            The exception or error to be stored in the stack.
     */
    protected final void storeError(Throwable ex) {
        exceptionStack.add(ex);
    }

    /**
     * Resets the exception stack. All currently stored exceptions are cleared.
     */
    public final void reset() {
        exceptionStack.clear();
    }

    /**
     * Handles a non-fatal error. Non-fatal means that the application can
     * continue running and no inconsitency or other critical state has occured.
     * 
     * @param ex
     *            The Exception or Error to be handled.
     */
    protected abstract void handleError(Throwable ex);

    /**
     * Handles a non-fatal error. Non-fatal means that the application can
     * continue running and no inconsitency or other critical state has occured.
     * 
     * @param ex
     *            The Exception or Error to be handled.
     * @param stackError
     *            True to store the error.
     * @param resetStack
     *            True to reset the stack before processing this error.
     */
    public final void handleError(Throwable ex, boolean stackError, boolean resetStack) {
        if (resetStack) {
            reset();
        }
        if (stackError) {
            storeError(ex);
        }
        handleError(ex);
    }

    /**
     * Handles a fatal error. Fatal means that the application can not continue
     * running and an inconsitency or other critical state has occured.
     * 
     * @param ex
     *            The Exception or Error to be handled.
     */
    protected abstract void handleFatalError(Throwable ex);

    /**
     * Handles a fatal error. Fatal means that the application can not continue
     * running and an inconsitency or other critical state has occured.
     * 
     * @param ex
     *            The Exception or Error to be handled.
     * @param stackError
     *            True to store the error.
     * @param resetStack
     *            True to reset the stack before processing this error.
     */
    public final void handleFatalError(Throwable ex, boolean stackError, boolean resetStack) {
        if (resetStack) {
            reset();
        }
        if (stackError) {
            storeError(ex);
        }

        handleFatalError(ex);
    }

    /**
     * Returns the last error currently stored, or null if no error is currently
     * in the stack.
     * 
     * @param resetStack
     *            True if stack shall be resetted.
     * 
     * @return The last exception occured is returned, or null if no error is
     *         currently stored.
     */
    public final Throwable getLastError(boolean resetStack) {
        Throwable ex = null;

        if (isErrorAvailable()) {
            ex = exceptionStack.get(exceptionStack.size() - 1);
        }

        if (resetStack) {
            reset();
        }

        return ex;
    }
}
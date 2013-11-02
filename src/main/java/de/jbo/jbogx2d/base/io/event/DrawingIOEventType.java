//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: DrawingIOEventType.java
// Created: 21.02.2010 - 14:14:52
//
package de.jbo.jbogx2d.base.io.event;

/**
 * Drawing IO event types.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 21.02.2010 jbo - created <br>
 */
public enum DrawingIOEventType {
    /** Bytes processed (data = percentage). */
    PERCENTAGE_PROCESSED(),
    /** Operation was started (data = <code>DrawingInput</code> instance). */
    INPUT_START(),
    /** Operation ended (data = <code>DrawingInput</code> instance). */
    INPUT_END(),
    /** Operation interrupted (data = <code>DrawingInput</code> instance). */
    INPUT_INTERRUPT(),
    /** Operation was started (data = <code>DrawingOutput</code> instance). */
    OUTPUT_START(),
    /** Operation ended (data = <code>DrawingOutput</code> instance). */
    OUTPUT_END(),
    /** Operation interrupted (data = <code>DrawingOutput</code> instance. */
    OUTPUT_INTERRUPT();
}

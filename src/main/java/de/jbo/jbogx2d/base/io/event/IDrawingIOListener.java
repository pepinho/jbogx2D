//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: IDrawingIOListener.java
// Created: 21.02.2010 - 14:05:08
//
package de.jbo.jbogx2d.base.io.event;

/**
 * Listener for Drawing IO events.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 21.02.2010 jbo - created <br>
 */
public interface IDrawingIOListener {
    /**
     * Handles the given event.
     * 
     * @param event
     *            The event to be handled.
     */
    void onIOEvent(DrawingIOEvent event);
}

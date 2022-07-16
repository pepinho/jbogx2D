//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: DrawingIO.java
// Created: 21.02.2010 - 14:31:24
//
package de.jbo.jbogx2d.base.io;

import java.util.Collection;
import java.util.LinkedList;

import de.jbo.jbogx2d.base.io.event.DrawingIOEvent;
import de.jbo.jbogx2d.base.io.event.IDrawingIOListener;

/**
 * Drawing IO base handler.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 21.02.2010 jbo - created <br>
 */
public abstract class DrawingIO {

    /** List of registered IO listeners. */
    private Collection<IDrawingIOListener> drawingIoListeners = new LinkedList<>();

    /**
     * Creates a new instance.
     */
    protected DrawingIO() {

    }

    /**
     * Registers the given listener.
     * 
     * @param listener
     *            The listener to be registered.
     */
    public void addIOListener(IDrawingIOListener listener) {
        synchronized (drawingIoListeners) {
            if (!drawingIoListeners.contains(listener)) {
                drawingIoListeners.add(listener);
            }
        }
    }

    /**
     * Removes the given listener.
     * 
     * @param listener
     *            The listener to be removed.
     */
    public void removeIOListener(IDrawingIOListener listener) {
        synchronized (drawingIoListeners) {
            drawingIoListeners.remove(listener);
        }
    }

    /**
     * Fires an io-event.
     * 
     * @param event
     *            Event to be fired to all registered listeners.
     * @see #addIOListener(IDrawingIOListener)
     * @see #removeIOListener(IDrawingIOListener)
     */
    public void fireIOEvent(DrawingIOEvent event) {
        synchronized (drawingIoListeners) {
            for (IDrawingIOListener listener : drawingIoListeners) {
                listener.onIOEvent(event);
            }
        }
    }
}

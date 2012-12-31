//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: DrawingIOEvent.java
// Created: 21.02.2010 - 14:06:02
//
package de.jbo.jbogx2d.base.io.event;

/**
 * Implements a IO event object.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 21.02.2010 jbo - created <br>
 */
public class DrawingIOEvent {
    /** The event-type. */
    private DrawingIOEventType type = null;

    /** Event data. */
    private Object data = null;

    /**
     * Creates a new event instance.
     * 
     * @param type
     *            The event-type.
     * @param data
     *            The event-data.
     */
    public DrawingIOEvent(DrawingIOEventType type, Object data) {
        this.type = type;
        this.data = data;
    }

    /**
     * @return the type
     */
    public final DrawingIOEventType getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public final void setType(DrawingIOEventType type) {
        this.type = type;
    }

    /**
     * @return the data
     */
    public final Object getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public final void setData(Object data) {
        this.data = data;
    }

}

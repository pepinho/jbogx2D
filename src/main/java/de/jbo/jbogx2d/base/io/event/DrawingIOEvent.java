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
     * @param newType
     *            The event-type.
     * @param newData
     *            The event-data.
     */
    public DrawingIOEvent(final DrawingIOEventType newType, final Object newData) {
        this.type = newType;
        this.data = newData;
    }

    /**
     * @return the type
     */
    public final DrawingIOEventType getType() {
        return type;
    }

    /**
     * @param newType
     *            the type to set
     */
    public final void setType(final DrawingIOEventType newType) {
        this.type = newType;
    }

    /**
     * @return the data
     */
    public final Object getData() {
        return data;
    }

    /**
     * @param newData
     *            the data to set
     */
    public final void setData(final Object newData) {
        this.data = newData;
    }

}

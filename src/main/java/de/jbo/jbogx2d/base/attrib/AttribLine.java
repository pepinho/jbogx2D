//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: AttribPen.java
// Created: 29.02.2004 - 07:17:28
//

package de.jbo.jbogx2d.base.attrib;

import java.awt.BasicStroke;
import java.awt.Color;

import de.jbo.jbogx2d.base.Jbogx2D;
import de.jbo.jbogx2d.base.elements.model.ElemModel;

/**
 * Implements the line-attribute for an element. <br>
 * The following values can be set: <br>
 * <ul>
 * <li>The line width in world-coordinates.</li>
 * <li>The color-index of the line.</li>
 * <li>The stroke id based on the attribute handler stroke map.</li>
 * </ul>
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 29.02.2004: jbo created <br>
 */
public class AttribLine implements AttribBase {
    /** The width of the pen. */
    private float width = 1.0f;

    /** The stroke-id of the pen. */
    private short stroke = 1;

    /** The color-index of the pen. */
    private short color = 1;

    /**
     * The true system-color mapped to the color-index. The value has to be set
     * by calling <code>update</code> i.o. to mapping changes take effect
     */
    private Color systemColor = null;

    /**
     * The stroke for this line attribute. The value has to be set by calling
     * <code>update</code> i.o. to mapping changes take effect
     */
    private BasicStroke systemStroke = null;

    /**
     * Creates a new instance. The given default values are: <br>
     * <ul>
     * <li>width: 1.0</li>
     * <li>stroke-id: 1</li>
     * <li>color: 1 (default black)</li>
     * </ul>
     */
    public AttribLine() {
        super();
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.attrib.AttribBase#update(de.jbo.jbogx2d.base.elements
     * .model.ElemModel)
     */
    @Override
    public void update(ElemModel model) {
        systemColor = Jbogx2D.getAttributeHandler().getColor(color);
        systemStroke = Jbogx2D.getAttributeHandler().getStroke(stroke, width);
    }

    /**
     * Returns the currently set color-index of the line.
     * 
     * @return The color-index of the line.
     */
    public short getColor() {
        return color;
    }

    /**
     * Returns the currently set stroke index of the line based on the
     * AttributeHandler's mapping of strokes.
     * 
     * @return The current stroke-index.
     */
    public short getStroke() {
        return stroke;
    }

    /**
     * Returns the system color to the corresponding color-index. <br>
     * To ensure the correct state of this value, a call of
     * <code>update(...)</code> can be necessary.
     * 
     * @return The system color currently set.
     */
    public Color getSystemColor() {
        return systemColor;
    }

    /**
     * Returns the system stroke to the corresponding stroke-index. <br>
     * To ensure the correct state of this value, a call of
     * <code>update(...)</code> can be necessary.
     * 
     * @return The system stroke currently set.
     */
    public BasicStroke getSystemStroke() {
        return systemStroke;
    }

    /**
     * Returns the width of the line in world-coordinates.
     * 
     * @return The width currently set in world-coordinates.
     */
    public float getWidth() {
        return width;
    }

    /**
     * Sets the color-index to be used for this line. The index is based on the
     * mapping managed by the AttributeHandler.
     * 
     * @param l
     *            The color-index to be used by this instance.
     */
    public void setColor(short l) {
        color = l;
    }

    /**
     * Sets the stroke-index to be used for this line. The index is based on the
     * mapping managed by the AttributeHandler.
     * 
     * @param s
     *            The stroke-index to be used by this instance.
     */
    public void setStroke(short s) {
        stroke = s;
    }

    /**
     * Sets the width for this line in world-coordinates.
     * 
     * @param i
     *            The width to be set.
     */
    public void setWidth(float i) {
        width = i;
    }

}
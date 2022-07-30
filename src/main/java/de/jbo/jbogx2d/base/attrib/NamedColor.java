//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: NamedColor.java
// Created: 28.02.2004 - 19:00:13
//

package de.jbo.jbogx2d.base.attrib;

import java.awt.Color;
import java.util.Objects;

/**
 * Implements a color instance with a specific name defined by the user, e.g.
 * via a colors-property file.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 28.02.2004: jbo created <br>
 */
public class NamedColor extends Color {
    /**
     * serial id.
     */
    private static final long serialVersionUID = 1252002889557894044L;

    /**
     * No alpha constant.
     */
    private static final int ALPHA_NONE = 255;

    /** The name of the instance. */
    private String colorName = null;

    /**
     * Creates a new instance with the given parameters.
     * 
     * @param name
     *            The name of the color-instance.
     * @param r
     *            The red value from 0 to 255.
     * @param g
     *            The green value from 0 to 255.
     * @param b
     *            The blue value from 0 to 255.
     * @param a
     *            The alpha value for transparency from 0 to 255.
     */
    public NamedColor(final String name, final int r, final int g, final int b, final int a) {
        super(r, g, b, a);
        colorName = name;
    }

    /**
     * Creates a new instance with the given parameters. <br>
     * The transparency is set to 255, this means that no transparency is set.
     * 
     * @param name
     *            The name of the color-instance.
     * @param r
     *            The red value from 0 to 255.
     * @param g
     *            The green value from 0 to 255.
     * @param b
     *            The blue value from 0 to 255.
     */
    public NamedColor(final String name, final int r, final int g, final int b) {
        this(name, r, g, b, ALPHA_NONE);
    }

    /**
     * Returns the string representation of this instance. <br>
     * In this case the color's name is returned.
     * 
     * @return The string representation of this instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {
        return colorName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(colorName);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        NamedColor other = (NamedColor) obj;
        return Objects.equals(colorName, other.colorName);
    }
    
    
}
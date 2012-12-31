//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: AttribGradientType.java
// Created: 04.03.2004 - 17:46:10
//

package de.jbo.jbogx2d.base.attrib;

/**
 * Defines the gradient-types supported by the fill-attribute.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 04.03.2004: jbo created <br>
 */
public final class AttribGradientType {
    /** Start x-coordinate of the gradient. */
    private double x1 = 0.0;

    /** Start y-coordinate of the gradient. */
    private double y1 = 0.0;

    /** End x-coordinate of the gradient. */
    private double x2 = 0.0;

    /** End y-coordinate of the gradient. */
    private double y2 = 0.0;

    /** Gradient type horizontal. */
    public static final AttribGradientType GRADIENT_HORIZONTAL = new AttribGradientType(0.0, 0.0, 20.0, 0.0);

    /** Gradient type vertical. */
    public static final AttribGradientType GRADIENT_VERTICAL = new AttribGradientType(0.0, 0.0, 0.0, 50.0);

    /** Radial gradient. */
    public static final AttribGradientType GRADIENT_RADIAL = new AttribGradientType();

    /**
     * Creates a new default type.
     */
    private AttribGradientType() {
        super();
    }

    /**
     * Creates a type with the given values.
     * 
     * @param myX1
     *            Start point of the gradient in world-coordinates.
     * @param myY1
     *            Start point of the gradient in world-coordinates.
     * @param myX2
     *            End point of the gradient in world-coordinates.
     * @param myY2
     *            End point of the gradient in world-coordinates.
     */
    private AttribGradientType(final double myX1, final double myY1, final double myX2, final double myY2) {
        this.x1 = myX1;
        this.x2 = myX2;
        this.y1 = myY1;
        this.y2 = myY2;
    }

    /**
     * @return the x1
     */
    public double getX1() {
        return x1;
    }

    /**
     * @param x
     *            the x1 to set
     */
    public void setX1(final double x) {
        this.x1 = x;
    }

    /**
     * @return the y1
     */
    public double getY1() {
        return y1;
    }

    /**
     * @param y
     *            the y1 to set
     */
    public void setY1(final double y) {
        this.y1 = y;
    }

    /**
     * @return the x2
     */
    public double getX2() {
        return x2;
    }

    /**
     * @param x
     *            the x2 to set
     */
    public void setX2(final double x) {
        this.x2 = x;
    }

    /**
     * @return the y2
     */
    public double getY2() {
        return y2;
    }

    /**
     * @param y
     *            the y2 to set
     */
    public void setY2(final double y) {
        this.y2 = y;
    }

}
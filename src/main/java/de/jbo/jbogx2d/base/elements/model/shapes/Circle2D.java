//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: Circle2D.java
// Created: 14.03.2004 - 10:48:34
//

package de.jbo.jbogx2d.base.elements.model.shapes;

import java.awt.geom.Ellipse2D;

import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements the shape for an circle defined by a middle-point and a radius in
 * user-space coordinates.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 14.03.2004: jbo created <br>
 */
public class Circle2D extends Ellipse2D.Double {

    /**
     * Serial id.
     */
    private static final long serialVersionUID = 4527294414333994607L;

    /**
     * Creates a new instance with the given values.
     * 
     * @param middle
     *            The middle-point of the circle.
     * @param radius
     *            The radius of the circle.
     */
    public Circle2D(PointUserSpace middle, double radius) {
        this(middle.x, middle.y, radius);
    }

    /**
     * Creates a new instance with the given values.
     * 
     * @param middleX
     *            The x-coordinate of the middle-point.
     * @param middleY
     *            The y-coordinate of the middle-point.
     * @param radius
     *            The radius of the circle.
     */
    public Circle2D(double middleX, double middleY, double radius) {
        super();

        setRadius(radius);
        setCenter(middleX, middleY);
    }

    /**
     * Returns the radius of the circle.
     * 
     * @return The radius of the circle.
     */
    public double getRadius() {
        return width / 2;
    }

    /**
     * Sets the middle-point coordinates.
     * 
     * @param middleX
     *            The x-coordinate of the circle.
     * @param middleY
     *            The y-coordinate of the circle.
     */
    public void setCenter(double middleX, double middleY) {
        double radius = getRadius();
        x = middleX - radius;
        y = middleY - radius;
    }

    /**
     * Sets the radius of the circle.
     * 
     * @param radius
     *            The radius to be set.
     */
    public void setRadius(double radius) {
        double radiusOld = getRadius();
        double middleX = x + radiusOld;
        double middleY = y + radiusOld;

        x = middleX - radius;
        y = middleY - radius;
        width = radius * 2;
        height = radius * 2;
    }
}
//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemModelCircle.java
// Created: 14.03.2004 - 11:11:11
//

package de.jbo.jbogx2d.base.elements.model;

import java.awt.Shape;

import de.jbo.jbogx2d.base.attrib.AttribFill;
import de.jbo.jbogx2d.base.attrib.AttribLine;
import de.jbo.jbogx2d.base.attrib.AttribText;
import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.elements.model.shapes.Circle2D;
import de.jbo.jbogx2d.base.geom.AffineTransformX;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements the element-model for a circle element.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 14.03.2004: jbo created <br>
 */
public class ElemModelCircle extends ElemModel {
    /** Shape used to store geometry values. */
    private Circle2D shape = null;

    /** Line attributes. */
    private final AttribLine attribLine = new AttribLine();

    /** Fill attributes. */
    private final AttribFill attribFill = new AttribFill();

    /**
     * Creates a new instance with the given values.
     * 
     * @param element
     *            The referenced parent element.
     * @param middleX
     *            The x-coordinate of the middle-point.
     * @param middleY
     *            The y-coordinate of the middle-point.
     * @param radius
     *            The radius of the circle.
     */
    public ElemModelCircle(ElemBase element, double middleX, double middleY, double radius) {
        super(element);
        shape = new Circle2D(middleX, middleY, radius);
    }

    /*
     * @see
     * 
     * de.jbo.jbogx2d.base.elements.model.ElemModel#calculateBounds(de.jbo.jbogx2d
     * .base.geom.BoundsUserSpace)
     */
    @Override
    protected void calculateBounds(BoundsUserSpace bounds) {
        bounds.setFrame(shape.getBounds2D());
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.ElemModel#transform(de.jbo.jbogx2d
     * .base.geom.AffineTransformX)
     */
    @Override
    public void transform(AffineTransformX transformation) {
        PointUserSpace center = new PointUserSpace();
        double radius = shape.getRadius();

        shape.setRadius(transformation.scalarMul(radius));

        center.x = shape.getCenterX();
        center.y = shape.getCenterY();

        transformation.transform(center, center);

        shape.setCenter(center.x, center.y);

        setBoundsDirty(true);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.ElemModel#getDistanceTo(de.jbo.jbogx2d
     * .base.geom.PointUserSpace)
     */
    @Override
    public double getDistanceTo(PointUserSpace point) {
        double distance = 0.0;
        BoundsUserSpace bounds = new BoundsUserSpace();

        getBounds(bounds);
        distance = bounds.getDistanceTo(point);

        return distance;
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.ElemModel#isPointInside(de.jbo.jbogx2d
     * .base.geom.PointUserSpace)
     */
    @Override
    public boolean isPointInside(PointUserSpace point) {
        return shape.contains(point);
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModel#intersects(double,
     * double, double, double)
     */
    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return shape.intersects(x, y, w, h);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.ElemModel#intersects(de.jbo.jbogx2d
     * .base.geom.BoundsUserSpace)
     */
    @Override
    public boolean intersects(BoundsUserSpace bounds) {
        return shape.intersects(bounds);
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModel#getAttribLine()
     */
    @Override
    public AttribLine getAttribLine() {
        return attribLine;
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModel#getAttribFill()
     */
    @Override
    public AttribFill getAttribFill() {
        return attribFill;
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModel#getAttribText()
     */
    @Override
    public AttribText getAttribText() {
        return null;
    }

    /**
     * @return The center x-coordinate.
     */
    public double getCenterX() {
        return shape.getCenterX();
    }

    /**
     * @return The center y-coordinate.
     */
    public double getCenterY() {
        return shape.getCenterY();
    }

    /**
     * @return The radius.
     */
    public double getRadius() {
        return shape.getRadius();
    }

    /**
     * Sets the center coordinates.
     * 
     * @param middleX
     *            The center x-coordinate.
     * @param middleY
     *            The center y-coordinate.
     */
    public void setCenter(double middleX, double middleY) {
        shape.setCenter(middleX, middleY);
    }

    /**
     * Sets the radius.
     * 
     * @param radius
     *            The radius.
     */
    public void setRadius(double radius) {
        shape.setRadius(radius);
    }

    /**
     * Returns the shape used for rendering.
     * 
     * @return The render shape.
     */
    public Shape getShape() {
        return shape;
    }
}
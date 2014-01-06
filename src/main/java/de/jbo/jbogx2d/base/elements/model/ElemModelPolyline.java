//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemModelPolyline.java
// Created: 01.03.2004 - 01:51:53
//

package de.jbo.jbogx2d.base.elements.model;

import java.awt.Shape;

import de.jbo.jbogx2d.base.attrib.AttribFill;
import de.jbo.jbogx2d.base.attrib.AttribLine;
import de.jbo.jbogx2d.base.attrib.AttribText;
import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D;
import de.jbo.jbogx2d.base.geom.AffineTransformX;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements the model for a polyline.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 01.03.2004: jbo created <br>
 */
public class ElemModelPolyline extends ElemModel {
    /** The shape used for rendering and storing the coordinates. */
    private Polyline2D shape = null;

    /** The line attributes. */
    private final AttribLine attribLine = new AttribLine();

    /**
     * Creates a new instance.
     * 
     * @param element
     *            The referenced parent element.
     */
    public ElemModelPolyline(ElemBase element) {
        super(element);
        initShape();
    }

    /**
     * Initializes the renering shape.
     */
    protected void initShape() {
        setShape(new Polyline2D());
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
        return shape.contains(point.x, point.y);
    }

    /**
     * @return The poly-point count.
     */
    public int getPointCount() {
        return shape.getPointCount();
    }

    /**
     * Sets the poly-point for the given index.
     * 
     * @param index
     *            The index of the point to be set.
     * @param point
     *            The point coordinates to be set.
     * @return True if successful.
     */
    public boolean setPoint(int index, PointUserSpace point) {
        return setPoint(index, point.x, point.y);
    }

    /**
     * Sets the poly-point for the given index.
     * 
     * @param index
     *            The index of the point to be set.
     * @param x
     *            The x-coordinate to be set.
     * @param y
     *            The y-coordinate to be set.
     * @return True if successful.
     */
    public boolean setPoint(int index, double x, double y) {
        setBoundsDirty(true);
        return shape.setPoint(index, x, y);
    }

    /**
     * Returns the point coordinates for the given index.
     * 
     * @param index
     *            The index to get the coordinates for.
     * @param point
     *            Out-parameter storing the coordinates for the given index.
     * @return True if successful.
     */
    public boolean getPoint(int index, PointUserSpace point) {
        return shape.getPoint(index, point);
    }

    /**
     * Sets the given poly-points. This overwrites are points stored before.
     * 
     * @param points
     *            The points to be set.
     */
    public void setPoints(PointUserSpace[] points) {
        shape.setPoints(points);
        setBoundsDirty(true);
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModel#getAttribFill()
     */
    @Override
    public AttribFill getAttribFill() {

        return null;
    }

    /**
     * @return The poly-points of this element.
     */
    public PointUserSpace[] getPoints() {
        return shape.getPoints();
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModel#getAttribLine()
     */
    @Override
    public AttribLine getAttribLine() {
        return attribLine;
    }

    /**
     * @return The shape used for rendering.
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * 
     * @param s
     *            The shape to be set.
     */
    protected void setShape(Polyline2D s) {
        shape = s;
    }

    /**
     * @param pointCount
     *            The point count to be set.
     */
    public void setPointCount(int pointCount) {
        shape.setPointCount(pointCount);
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
     * @see de.jbo.jbogx2d.base.elements.model.ElemModel#intersects(double,
     * double, double, double)
     */
    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return shape.intersects(x, y, w, h);
    }

    /**
     * Inserts the point at the given index.
     * 
     * @param index
     *            The index to insert the new point at.
     * @param x
     *            The x-coordinate to be inserted.
     * @param y
     *            The y-coordinate to be inserted.
     * @return True if successful.
     */
    public boolean insertPoint(int index, double x, double y) {
        setBoundsDirty(true);
        return shape.insertPoint(index, x, y);
    }

    /**
     * Removes the point at the given index.
     * 
     * @param index
     *            The index to remove the point for.
     * @return True if successful.
     */
    public boolean removePoint(int index) {
        setBoundsDirty(true);
        return shape.removePoint(index);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.ElemModel#transform(de.jbo.jbogx2d
     * .base.geom.AffineTransformX)
     */
    @Override
    public void transform(AffineTransformX transformation) {
        shape.transform(transformation);
        setBoundsDirty(true);
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModel#getAttribText()
     */
    @Override
    public AttribText getAttribText() {
        return null;
    }
}
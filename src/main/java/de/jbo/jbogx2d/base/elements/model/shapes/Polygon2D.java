//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ShapePolyline.java
// Created: 05.03.2004 - 21:19:31
//

package de.jbo.jbogx2d.base.elements.model.shapes;

import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import sun.awt.geom.Crossings;
import sun.awt.geom.Curve;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements a polygon shape.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 05.03.2004: jbo created <br>
 */
public class Polygon2D extends Polyline2D {
    /**
     * Creates a new instance.
     * 
     * @param points
     *            The poly-endpoints to be used.
     */
    public Polygon2D(PointUserSpace[] points) {
        super(points);
    }

    /**
     * Creates a new empty instance.
     */
    public Polygon2D() {
        super();
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#contains(double,
     * double)
     */
    @Override
    public boolean contains(double x, double y) {
        boolean contains = false;

        if (getPointCount() > 2) {
            int cross = Curve.pointCrossingsForPath(getPathIterator(null), x, y);
            contains = (cross != 0);
        }
        return contains;
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#contains(double,
     * double, double, double)
     */
    @Override
    public boolean contains(double x, double y, double w, double h) {
        Crossings c = Crossings.findCrossings(getPathIterator(null), x, y, x + w, y + h);
        return ((c != null) && c.covers(y, y + h));
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#contains(java.awt
     * .geom.Point2D)
     */
    @Override
    public boolean contains(Point2D p) {
        return contains(p.getX(), p.getY());
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#contains(java.awt
     * .geom.Rectangle2D)
     */
    @Override
    public boolean contains(Rectangle2D r) {
        return contains(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#getPathIterator(
     * java.awt.geom.AffineTransform)
     */
    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return new PathIteratorPolygon(this, at);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#getPathIterator(
     * java.awt.geom.AffineTransform, double)
     */
    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return getPathIterator(at);
    }

}
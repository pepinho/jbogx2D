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

import de.jbo.jbogx2d.base.geom.PointUserSpace;
import de.jbo.jbogx2d.base.util.lang.WrapperDouble;
import de.jbo.jbogx2d.base.util.lang.WrapperInteger;

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

    private boolean containsCalculateLeftX(double curx, double lastx, double x, WrapperDouble leftxOut) {
        if (curx < lastx) {
            if (x >= lastx) {
                return false;
            }
            leftxOut.setValue(curx);
        } else {
            if (x >= curx) {
                return false;
            }
            leftxOut.setValue(lastx);
        }
        return true;
    }

    private boolean containsCalculateTestHits(PointUserSpace current, PointUserSpace point, double leftx, PointUserSpace yGreaterSmaller, WrapperInteger hits, PointUserSpace test) {
        if (point.y < yGreaterSmaller.x || point.y >= yGreaterSmaller.y) {
            return false;
        }
        if (point.x < leftx) {
            hits.setValue(hits.getValue() + 1);
            return false;
        }
        test.x = (point.x - current.x);
        test.y = (point.y - current.y);
        return true;
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#contains(double,
     * double)
     */
    @Override
    public boolean contains(double x, double y) {
        PointUserSpace[] points = getPoints();
        PointUserSpace point = new PointUserSpace(x, y);

        if (isOutOfBoundsOrNoArea(x, y, points)) {
            return false;
        }
        int npoints = points.length;
        WrapperInteger hits = new WrapperInteger(0);

        PointUserSpace current = new PointUserSpace(x, y);
        double lastx = points[npoints - 1].x;
        double lasty = points[npoints - 1].y;

        // Walk the edges of the polygon
        for (int i = 0; i < npoints; lastx = current.x, lasty = current.y, i++) {
            current.set(points[i]);

            if (current.y == lasty) {
                continue;
            }

            WrapperDouble leftxOut = new WrapperDouble();
            if (containsCalculateLeftX(current.x, lastx, current.x, leftxOut)) {
                PointUserSpace test = new PointUserSpace();

                if (current.y < lasty && containsCalculateTestHits(current, point, leftxOut.getValue(), new PointUserSpace(current.y, lasty), hits, test)
                        && containsCalculateTestHits(current, point, leftxOut.getValue(), new PointUserSpace(lasty, current.y), hits, test) && test.x < (test.y / (lasty - current.y) * (lastx - current.x))) {
                    hits.setValue(hits.getValue() + 1);
                }
            }
        }

        return ((hits.getValue() & 1) != 0);

    }

    private boolean isOutOfBoundsOrNoArea(double x, double y, PointUserSpace[] points) {
        return points.length <= 2 || !getBounds2D().contains(x, y);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#contains(double,
     * double, double, double)
     */
    @Override
    public boolean contains(double x, double y, double w, double h) {
        double x1 = x;
        double y1 = y;

        // upper-left
        if (contains(x1, y1)) {
            return true;
        }

        // upper-right
        x1 = x + w;
        if (contains(x1, y1)) {
            return true;
        }

        // lower-right
        x1 = x + w;
        y1 = y + h;
        if (contains(x1, y1)) {
            return true;
        }

        // lower-left
        x1 = x;
        y1 = y + h;
        return contains(x1, y1);
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
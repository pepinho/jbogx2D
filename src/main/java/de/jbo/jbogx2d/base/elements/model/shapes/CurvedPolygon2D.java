//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: CurvedPolygon2D.java
// Created: 07.11.2004 - 19:07:34
//

package de.jbo.jbogx2d.base.elements.model.shapes;

import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;

import de.jbo.jbogx2d.base.elements.model.shapes.geom.PointUserSpaceCurved;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements a curved polygon shape.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 07.11.2004: jbo created <br>
 */
public class CurvedPolygon2D extends Polygon2D {
    /** Factor for distance of controlpoints to the poly-endpoints. */
    protected static final double CONTROL_POINT_DIST_FACTOR = 0.5;

    /**
     * Creates a new instance.
     * 
     * @param points
     *            Poly-endpoints to be used.
     */
    public CurvedPolygon2D(PointUserSpace[] points) {
        super(points);
    }

    /**
     * Creates a new instance.
     */
    public CurvedPolygon2D() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#createPoint()
     */
    @Override
    protected PointUserSpace createPoint() {
        return new PointUserSpaceCurved();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#createPoints(int)
     */
    @Override
    protected PointUserSpace[] createPoints(int size) {
        return new PointUserSpaceCurved[size];
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.shapes.Polygon2D#getPathIterator(java
     * .awt.geom.AffineTransform)
     */
    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return new PathIteratorCurvedPolygon(this, at);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#insertPoint(int,
     * double, double)
     */
    @Override
    public boolean insertPoint(int index, double x, double y) {
        boolean ret = super.insertPoint(index, x, y);

        if (ret) {
            ret = updateControlPoints(Math.max(0, index - 1), Math.min(index + 1, getPointCount() - 1));
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#removePoint(int)
     */
    @Override
    public boolean removePoint(int index) {
        boolean ret = super.removePoint(index);

        if (ret) {
            ret = updateControlPoints(Math.max(0, index - 1), index);
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#setPoint(int,
     * double, double)
     */
    @Override
    public boolean setPoint(int index, double x, double y) {
        boolean ret = super.setPoint(index, x, y);

        if (ret) {
            ret = updateControlPoints(Math.max(0, index - 1), Math.min(index + 1, getPointCount() - 1));
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#setPoints(de.jbo
     * .jbogx2d.base.geom.PointUserSpace[])
     */
    @Override
    public void setPoints(PointUserSpace[] p) {
        super.setPoints(p);

        updateControlPoints(0, p.length - 1);
    }

    /**
     * Updates the control-points.
     * 
     * @param startIndex
     *            Start-index to update for.
     * @param stopIndex
     *            Stop-index to update for.
     * @return True if updated successfully.
     */
    private boolean updateControlPoints(int startIndex, int stopIndex) {
        boolean ret = false;
        int index = startIndex;
        int count = getPointCount();
        int newStopIndex = (stopIndex >= count) ? count - 1 : stopIndex;

        for (; index <= newStopIndex; index++) {
            updateControlPoint(index);
        }

        ret = true;

        return ret;
    }

    /**
     * Updates a specific control-point.
     * 
     * @param index
     *            The index of the point to be updated.
     */
    private void updateControlPoint(int index) {
        PointUserSpace[] points = getPoints();
        PointUserSpaceCurved p1 = (PointUserSpaceCurved) points[index];
        PointUserSpace p2 = null;
        int count = getPointCount();
        double angle = 0.0;
        double distance = 0.0;

        if (index < (count - 1)) {
            p2 = points[index + 1];
        } else {
            p2 = points[0];
        }
        distance = p1.distance(p2);
        angle = p1.angle(p2);
        PointUserSpace controlPoint = p1.getControlPoint();
        if (controlPoint == null) {
            controlPoint = new PointUserSpace(p1.x, p1.y);
            p1.setControlPoint(controlPoint);
        } else {
            controlPoint.set(p1.x, p1.y);
        }
        controlPoint.addPolar(distance * CONTROL_POINT_DIST_FACTOR, angle);
    }
}
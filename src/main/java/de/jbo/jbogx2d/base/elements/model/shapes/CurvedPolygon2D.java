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
            ret = CurvedPolyUtil.updateControlPoints(Math.max(0, index - 1), Math.min(index + 1, getPointCount() - 1), getPoints(), getPointCount());
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
            ret = CurvedPolyUtil.updateControlPoints(Math.max(0, index - 1), index, getPoints(), getPointCount());
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
            ret = CurvedPolyUtil.updateControlPoints(Math.max(0, index - 1), Math.min(index + 1, getPointCount() - 1), getPoints(), getPointCount());
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

        CurvedPolyUtil.updateControlPoints(0, p.length - 1, getPoints(), getPointCount());
    }

   
}
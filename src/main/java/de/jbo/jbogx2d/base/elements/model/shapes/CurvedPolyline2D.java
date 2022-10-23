//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: CurvedPolyline2D.java
// Created: 14.03.2004 - 13:07:34
//

package de.jbo.jbogx2d.base.elements.model.shapes;

import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;

import de.jbo.jbogx2d.base.elements.model.shapes.geom.PointUserSpaceCurved;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements a cured polyline shape.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 14.03.2004: jbo created <br>
 */
public class CurvedPolyline2D extends Polyline2D {
   
    /**
     * Creates a new instance.
     * 
     * @param points
     *            Poly-endpoints to be used.
     */
    public CurvedPolyline2D(PointUserSpace[] points) {
        super(points);
    }

    /**
     * Creates a new instance.
     */
    public CurvedPolyline2D() {
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
     * de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#getPathIterator(
     * java.awt.geom.AffineTransform)
     */
    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return new PathIteratorCurvedPolyline(this, at);
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
        boolean wasInserted = super.insertPoint(index, x, y);

        if (wasInserted) {
            wasInserted = CurvedPolyUtil.updateControlPointsOnInsert(this, index, x, y);
        }

        return wasInserted;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#removePoint(int)
     */
    @Override
    public boolean removePoint(int index) {
        boolean wasRemoved = super.removePoint(index);
        if (wasRemoved) {
            wasRemoved = CurvedPolyUtil.updateControlPointsOnRemove(this, index);
        }

        return wasRemoved;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D#setPoint(int,
     * double, double)
     */
    @Override
    public boolean setPoint(int index, double x, double y) {
        boolean wasSet = super.setPoint(index, x, y); 
        if(wasSet) {
            wasSet = CurvedPolyUtil.updateControlPointsOnSet(this, index, x, y);
        }

        return wasSet;
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

        CurvedPolyUtil.updateControlPointsOnSet(this, p);
    }

  
}
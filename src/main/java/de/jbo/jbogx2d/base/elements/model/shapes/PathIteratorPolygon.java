//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: PathIteratorPolyline.java
// Created: 05.03.2004 - 22:19:47
//

package de.jbo.jbogx2d.base.elements.model.shapes;

import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.util.NoSuchElementException;

import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements the path-iterator used for rendering polygons.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 05.03.2004: jbo created <br>
 */
public class PathIteratorPolygon implements PathIterator {
    /** The polygon being handled. */
    private Polygon2D polygon;

    /** The transformation being used. */
    private AffineTransform affine;

    /** Index used for iteration. */
    private int index;

    /** Segment type used for iteration. */
    private int segmentLineType = SEG_LINETO;

    /**
     * Creates a new instance.
     * 
     * @param poly
     *            The polygon being handled.
     * @param transform
     *            The transformation to be used.
     */
    public PathIteratorPolygon(Polygon2D poly, AffineTransform transform) {
        super();
        this.polygon = poly;
        affine = transform;
    }

    /*
     * @see java.awt.geom.PathIterator#currentSegment(double[])
     */
    @Override
    public int currentSegment(double[] coords) {
        int type = 0;

        if (isDone()) {
            throw new NoSuchElementException("polyline iterator out of bounds");
        }
        PointUserSpace[] points = polygon.getPoints();
        if (index == 0) {
            type = SEG_MOVETO;
            coords[0] = points[index].x;
            coords[1] = points[index].y;

            if (affine != null) {
                affine.transform(coords, 0, coords, 0, 1);
            }
        } else if (index < (points.length)) {
            type = segmentLineType;
            coords[0] = points[index].x;
            coords[1] = points[index].y;

            if (affine != null) {
                affine.transform(coords, 0, coords, 0, 1);
            }
        } else {
            type = SEG_CLOSE;
        }

        return type;
    }

    /*
     * @see java.awt.geom.PathIterator#currentSegment(float[])
     */
    @Override
    public int currentSegment(float[] coords) {
        int type = 0;

        if (isDone()) {
            throw new NoSuchElementException("polyline iterator out of bounds");
        }
        PointUserSpace[] points = polygon.getPoints();

        if (index == 0) {
            type = SEG_MOVETO;
            coords[0] = (float) points[index].x;
            coords[1] = (float) points[index].y;

            if (affine != null) {
                affine.transform(coords, 0, coords, 0, 1);
            }
        } else if (index <= (points.length - 1)) {
            type = segmentLineType;
            coords[0] = (float) points[index].x;
            coords[1] = (float) points[index].y;

            if (affine != null) {
                affine.transform(coords, 0, coords, 0, 1);
            }
        } else {
            type = SEG_CLOSE;
        }

        return type;
    }

    /*
     * @see java.awt.geom.PathIterator#getWindingRule()
     */
    @Override
    public int getWindingRule() {
        return WIND_NON_ZERO;
    }

    /*
     * @see java.awt.geom.PathIterator#isDone()
     */
    @Override
    public boolean isDone() {
        return (index > polygon.getPointCount());
    }

    /*
     * @see java.awt.geom.PathIterator#next()
     */
    @Override
    public void next() {
        index++;
    }

}
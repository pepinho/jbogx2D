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

/**
 * Implements the path-iterator used to render polylines.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 05.03.2004: jbo created <br>
 */
public class PathIteratorPolyline implements PathIterator {
    /** The line to be handled. */
    Polyline2D line;

    /** The transformation to be used. */
    AffineTransform affine;

    /** Index used for iteration. */
    int index;

    /** Segment-type to be used for iteration. */
    int segmentLineType = SEG_LINETO;

    /**
     * Creates a new instance.
     * 
     * @param line
     *            The line to be handled.
     * @param transform
     *            The transformation to be used.
     */
    public PathIteratorPolyline(Polyline2D line, AffineTransform transform) {
        super();
        this.line = line;
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

        coords[0] = line.points[index].x;
        coords[1] = line.points[index].y;

        if (index == 0) {
            type = SEG_MOVETO;
        } else {
            type = segmentLineType;
        }

        if (affine != null) {
            affine.transform(coords, 0, coords, 0, 1);
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

        coords[0] = (float) line.points[index].x;
        coords[1] = (float) line.points[index].y;

        if (index == 0) {
            type = SEG_MOVETO;
        } else {
            type = segmentLineType;
        }

        if (affine != null) {
            affine.transform(coords, 0, coords, 0, 1);
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
        return (index >= line.getPointCount());
    }

    /*
     * @see java.awt.geom.PathIterator#next()
     */
    @Override
    public void next() {
        index++;
    }

}
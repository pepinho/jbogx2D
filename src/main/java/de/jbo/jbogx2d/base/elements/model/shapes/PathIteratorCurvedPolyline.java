//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: PathIteratorCurvedPolyline.java
// Created: 14.03.2004 - 13:09:10
//

package de.jbo.jbogx2d.base.elements.model.shapes;

import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.util.NoSuchElementException;

import de.jbo.jbogx2d.base.elements.model.shapes.geom.PointUserSpaceCurved;

/**
 * Implements the path-iterator used for rendering a curved polyline.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 14.03.2004: jbo created <br>
 */
public class PathIteratorCurvedPolyline implements PathIterator {
    /** The polyline being rendered. */
    CurvedPolyline2D line;

    /** Transformation used for rendering. */
    AffineTransform affine;

    /** End-index used for iteration. */
    int endIndex = 0;

    /** Index used for iteration. */
    int index = -1;

    /**
     * Creates a new instance.
     * 
     * @param line
     *            The line being handled.
     * @param transform
     *            The transformation being used.
     */
    public PathIteratorCurvedPolyline(CurvedPolyline2D line, AffineTransform transform) {
        super();
        this.line = line;
        endIndex = line.getPointCount();
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

        if (index == 0) {
            type = SEG_MOVETO;
            coords[0] = (float) line.points[index].x;
            coords[1] = (float) line.points[index].y;
            // GeneralPathIterator
        } else if (index < line.getPointCount()) {
            type = SEG_QUADTO;
            coords[0] = (float) line.points[index - 1].x;
            coords[1] = (float) line.points[index - 1].y;
            coords[2] = (float) line.points[index].x;
            coords[3] = (float) line.points[index].y;
            coords[4] = (float) line.points[index].x;
            coords[5] = (float) line.points[index].y;
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
        PointUserSpaceCurved point = null;

        int type = -1;
        if (isDone()) {
            throw new NoSuchElementException("polyline iterator out of bounds");
        }

        if (index == -1) {
            point = (PointUserSpaceCurved) line.points[index + 1];
            type = SEG_MOVETO;
            coords[0] = (float) point.x;
            coords[1] = (float) point.y;
        } else if (index == 0) {
            point = (PointUserSpaceCurved) line.points[index];
            type = SEG_LINETO;
            coords[0] = (float) point.controlPoint.x;
            coords[1] = (float) point.controlPoint.y;
            // GeneralPathIterator
        } else if (index < (endIndex - 1)) {
            type = SEG_QUADTO;
            point = (PointUserSpaceCurved) line.points[index];
            coords[0] = (float) point.x;
            coords[1] = (float) point.y;
            coords[2] = (float) point.controlPoint.x;
            coords[3] = (float) point.controlPoint.y;
        } else {
            point = (PointUserSpaceCurved) line.points[index];
            type = SEG_LINETO;
            coords[0] = (float) point.x;
            coords[1] = (float) point.y;
        }

        if (affine != null) {
            affine.transform(coords, 0, coords, 0, index == 0 ? 1 : 2);
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
        return (index >= endIndex);
    }

    /*
     * @see java.awt.geom.PathIterator#next()
     */
    @Override
    public void next() {
        index++;
    }
}
//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: PathIteratorCurvedPolygon.java
// Created: 07.11.2004 - 19:13:10
//

package de.jbo.jbogx2d.base.elements.model.shapes;

import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.util.NoSuchElementException;

import de.jbo.jbogx2d.base.elements.model.shapes.geom.PointUserSpaceCurved;

/**
 * Implements the path-iterator used for rendering a curved polygon.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 07.11.2004: jbo created <br>
 */
public class PathIteratorCurvedPolygon implements PathIterator {
    /** The parent-element being handled. */
    CurvedPolygon2D polygon;

    /** The transformation being used. */
    AffineTransform affine;

    /** End-index used for iteration. */
    int endIndex = 0;

    /** Index used for iteration. */
    int index = 0;

    /**
     * Creates a new instance.
     * 
     * @param polygon
     *            The polygon to be handled.
     * @param transform
     *            The transformation to be used.
     */
    public PathIteratorCurvedPolygon(CurvedPolygon2D polygon, AffineTransform transform) {
        super();
        this.polygon = polygon;
        endIndex = polygon.getPointCount();
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
            coords[0] = (float) polygon.points[index].x;
            coords[1] = (float) polygon.points[index].y;
            // GeneralPathIterator
        } else if (index < polygon.getPointCount()) {
            type = SEG_QUADTO;
            coords[0] = (float) polygon.points[index - 1].x;
            coords[1] = (float) polygon.points[index - 1].y;
            coords[2] = (float) polygon.points[index].x;
            coords[3] = (float) polygon.points[index].y;
            coords[4] = (float) polygon.points[index].x;
            coords[5] = (float) polygon.points[index].y;
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

        if (index == 0) {
            point = (PointUserSpaceCurved) polygon.points[index];
            type = SEG_MOVETO;
            coords[0] = (float) point.controlPoint.x;
            coords[1] = (float) point.controlPoint.y;
        } else if (index < endIndex) {
            type = SEG_QUADTO;
            point = (PointUserSpaceCurved) polygon.points[index];
            coords[0] = (float) point.x;
            coords[1] = (float) point.y;
            coords[2] = (float) point.controlPoint.x;
            coords[3] = (float) point.controlPoint.y;
        } else {
            type = SEG_QUADTO;
            point = (PointUserSpaceCurved) polygon.points[0];
            coords[0] = (float) point.x;
            coords[1] = (float) point.y;
            coords[2] = (float) point.controlPoint.x;
            coords[3] = (float) point.controlPoint.y;
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
        return (index > endIndex);
    }

    /*
     * @see java.awt.geom.PathIterator#next()
     */
    @Override
    public void next() {
        index++;
    }
}
//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: PathIteratorCurvedPolygon.java
// Created: 07.11.2004 - 19:13:10
//

package de.jbo.jbogx2d.base.elements.model.shapes;

import java.awt.geom.AffineTransform;

/**
 * Implements the path-iterator used for rendering a curved polygon.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 07.11.2004: jbo created <br>
 */
public class PathIteratorCurvedPolygon extends PathIteratorCurvedPolyline {

    /**
     * Creates a new instance.
     * 
     * @param p
     *            The polygon to be handled.
     * @param transform
     *            The transformation to be used.
     */
    public PathIteratorCurvedPolygon(CurvedPolygon2D p, AffineTransform transform) {
        super(p, transform);
    }

    /*
     * @see java.awt.geom.PathIterator#isDone()
     */
    @Override
    public boolean isDone() {
        return (index > endIndex);
    }

}
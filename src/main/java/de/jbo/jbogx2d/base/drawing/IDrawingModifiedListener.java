//
// Copyright (c) 2010 by jbo - Josef Baro
//
// Project: jbogx2D
// File: IDrawingModifiedListener.java
// Created: 14.02.2010 - 20:57:31
//
package de.jbo.jbogx2d.base.drawing;

import de.jbo.jbogx2d.base.geom.BoundsUserSpace;

/**
 * Interface for drawing modifications.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 14.02.2010 jbo - created <br>
 */
public interface IDrawingModifiedListener {
    /**
     * Fired when a drawing was modified.
     * 
     * @param source
     *            The source drawing.
     * @param modifiedBounds
     *            The modified area.
     */
    void onDrawingModified(Drawing source, BoundsUserSpace modifiedBounds);
}

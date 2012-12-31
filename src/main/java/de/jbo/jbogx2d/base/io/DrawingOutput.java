//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: IGraphicsInput.java
// Created: 20.02.2010 - 15:32:55
//
package de.jbo.jbogx2d.base.io;

import de.jbo.jbogx2d.base.drawing.Drawing;

/**
 * Abstract base-class for an output handler to write drawing-data to.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 20.02.2010 jbo - created <br>
 */
public abstract class DrawingOutput extends DrawingIO {
    /**
     * Writes the output for the given drawing. <br>
     * 
     * @param drawing
     *            The drawing to write the output for.
     * @return True if successful, False otherwise.
     * @see de.jbo.jbogx2d.base.error.ErrorHandler#isErrorAvailable()
     */
    public abstract boolean writeDrawing(Drawing drawing);
}

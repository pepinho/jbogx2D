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
 * Abstract base class for an input handler to read drawing-data from.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 20.02.2010 jbo - created <br>
 */
public abstract class DrawingInput extends DrawingIO {
    /**
     * Reads the input for the given drawing. <br>
     * <b>Note:</b><br>
     * The drawing will be resetted. Any previous content will be deleted.
     * 
     * @param drawing
     *            The drawing to read the input for.
     * @return True if successful, False otherwise.
     * @see de.jbo.jbogx2d.base.error.ErrorHandler#isErrorAvailable()
     */
    public abstract boolean readDrawing(Drawing drawing);
}

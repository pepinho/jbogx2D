//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: StreamedDrawingOutput.java
// Created: 20.02.2010 - 15:48:08
//
package de.jbo.jbogx2d.base.io;

import java.io.IOException;
import java.io.OutputStream;

import de.jbo.jbogx2d.base.Jbogx2D;
import de.jbo.jbogx2d.base.drawing.Drawing;
import de.jbo.jbogx2d.base.io.event.DrawingIOEvent;
import de.jbo.jbogx2d.base.io.event.DrawingIOEventType;

/**
 * Stream-based output handler.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 20.02.2010 jbo - created <br>
 */
public abstract class StreamedDrawingOutput extends DrawingOutput {
    /** The outputstream. */
    private OutputStream outputStream = null;

    /**
     * Creates a new instance.
     * 
     * @param ostream
     *            The outputstream to write the drawing data to.
     */
    public StreamedDrawingOutput(OutputStream ostream) {
        super();
        Jbogx2D.getErrorHandler().reset();
        setOutputStream(ostream);
    }

    /**
     * Sets the outputstream to be used.
     * 
     * @param ostream
     *            The stream to be set.
     */
    protected void setOutputStream(OutputStream ostream) {

        outputStream = ostream;
    }

    /**
     * Returns the current outputstream.
     * 
     * @return The current outputstream.
     */
    protected OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Closes the stream.
     */
    protected void closeStream() {
        try {
            getOutputStream().close();
        } catch (IOException e) {
            Jbogx2D.getErrorHandler().handleError(e, true, false);
        }
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.io.IDrawingOutput#writeDrawing(de.jbo.jbogx2d.base
     * .drawing.Drawing)
     */
    @Override
    public boolean writeDrawing(Drawing drawing) {
        fireIOEvent(new DrawingIOEvent(DrawingIOEventType.OUTPUT_START, this));
        boolean ret = writeDrawingExec(drawing);
        closeStream();
        fireIOEvent(new DrawingIOEvent(DrawingIOEventType.OUTPUT_END, this));
        return ret;
    }

    /**
     * Writes the drawing's contents.
     * 
     * @param drawing
     *            The drawing to be written.
     * @return True if successful, False otherwise.
     */
    protected abstract boolean writeDrawingExec(Drawing drawing);

}

//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: JbogxDrawingOutput.java
// Created: 21.02.2010 - 10:41:57
//
package de.jbo.jbogx2d.base.io.formats.jbogx;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import de.jbo.jbogx2d.base.Jbogx2D;
import de.jbo.jbogx2d.base.drawing.Drawing;
import de.jbo.jbogx2d.base.drawing.DrawingLayer;
import de.jbo.jbogx2d.base.io.StreamedDrawingOutput;

/**
 * Drawing output generating "jbogx" format: <br>
 * <ul>
 * <li>ZIP compressed base-file containing:
 * <ul>
 * <li>Metadata-file containing metainformation (version,
 * drawing-information,...)
 * <li>Drawing-data file
 * </ul>
 * </ul>
 * 
 * @author Josef Baro (jbo) <br>
 * @version 21.02.2010 jbo - created <br>
 */
public class JbogxDrawingOutput extends StreamedDrawingOutput {
    /** The zip output. */
    private ZipOutputStream zipOutputStream = null;

    /** The metadata file. */
    private MetadataFile metaData = null;

    /**
     * Creates a new instance.
     * 
     * @param ostream
     *            Stream to write output to.
     */
    public JbogxDrawingOutput(OutputStream ostream) {
        super(ostream);
        metaData = new MetadataFile();
        metaData.setFileVersion(Jbogx2D.getVersion());
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.io.StreamedDrawingOutput#setOutputStream(java.io.
     * OutputStream)
     */
    @Override
    protected void setOutputStream(OutputStream ostream) {
        zipOutputStream = new ZipOutputStream(ostream);

        super.setOutputStream(zipOutputStream);
    }

    /**
     * Writes the drawing content data.
     * 
     * @param drawing
     *            The drawing to be written.
     * @return True if successful, False otherwise.
     */
    private boolean writeDrawingData(Drawing drawing) {
        boolean ret = false;
        ZipEntry zipEntryDrawing = new ZipEntry("drawing");
        try {
            zipOutputStream.putNextEntry(zipEntryDrawing);
            PositionedObjectOutputStream ooStream = new PositionedObjectOutputStream(zipOutputStream);
            ret = writeDrawingLayers(drawing, ooStream);
            zipOutputStream.closeEntry();
        } catch (IOException e) {
            Jbogx2D.getErrorHandler().handleError(e, true, false);
        }
        return ret;
    }

    /**
     * Writes the drawing's layers and their elements to the given stream.
     * 
     * @param drawing
     *            The drawing to be handled.
     * @param stream
     *            The stream to write the data to.
     * @return True if successful, False otherwise.
     */
    private boolean writeDrawingLayers(Drawing drawing, PositionedObjectOutputStream stream) {
        boolean ret = false;
        Collection<DrawingLayer> layers = drawing.getLayers();

        for (DrawingLayer layer : layers) {
            long filePos = stream.getPos() + 1;
            String name = layer.getName();
            metaData.addLayer(new MetadataLayer(name, filePos));
            // XXX TODO write quad-tree with elements for each layer...
        }
        return ret;
    }

    /**
     * Writes the metadata content.
     * 
     * @return True if successful, False otherwise.
     */
    private boolean writeMetaData() {
        boolean ret = false;
        ZipEntry zipEntryDrawing = new ZipEntry("metadata");
        try {
            zipOutputStream.putNextEntry(zipEntryDrawing);
            ObjectOutputStream ooStream = new ObjectOutputStream(zipOutputStream);
            ret = metaData.write(ooStream);
            zipOutputStream.closeEntry();
        } catch (IOException e) {
            Jbogx2D.getErrorHandler().handleError(e, true, false);
        }
        return ret;
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.io.StreamedDrawingOutput#writeDrawingExec(de.jbo.
     * jbogx2d.base.drawing.Drawing)
     */
    @Override
    protected boolean writeDrawingExec(Drawing drawing) {
        boolean ret = false;
        ret = writeDrawingData(drawing);
        if (ret) {
            ret = writeMetaData();
        }
        return ret;
    }

}

//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: MetadataLayer.java
// Created: 22.02.2010 - 20:45:01
//
package de.jbo.jbogx2d.base.io.formats.jbogx;

/**
 * Layer metadata.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 22.02.2010 jbo - created <br>
 */
public class MetadataLayer {
    /** The layer name. */
    private String name = null;

    /** The file-position of the layer. */
    private long filePos = 0;

    /**
     * @param newName
     *            The layer's name.
     * @param newFilePos
     *            The fileposition.
     */
    public MetadataLayer(final String newName, final long newFilePos) {
        this.name = newName;
        this.filePos = newFilePos;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @return the filePos
     */
    public final long getFilePos() {
        return filePos;
    }

}

//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: MetadataFile.java
// Created: 21.02.2010 - 10:56:10
//
package de.jbo.jbogx2d.base.io.formats.jbogx;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.LinkedList;

import de.jbo.jbogx2d.base.Jbogx2D;
import de.jbo.jbogx2d.base.Version;

/**
 * Metadata file for jbogx-graphics files.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 21.02.2010 jbo - created <br>
 */
public final class MetadataFile {

    /** The version of the file. */
    private Version fileVersion = null;

    /** Drawing layers. */
    private Collection<MetadataLayer> layers = new LinkedList<MetadataLayer>();

    /**
     * Creates a new instance.
     */
    public MetadataFile() {

    }

    /**
     * @param fileVersion
     *            the fileVersion to set
     */
    public void setFileVersion(Version fileVersion) {
        this.fileVersion = fileVersion;
    }

    /**
     * @return the fileVersion
     */
    public Version getFileVersion() {
        return fileVersion;
    }

    /**
     * Adds the given layer.
     * 
     * @param layer
     *            The layer to be added.
     */
    public void addLayer(MetadataLayer layer) {
        layers.add(layer);
    }

    /**
     * @return The drawing layers.
     */
    public Collection<MetadataLayer> getLayers() {
        return layers;
    }

    /**
     * Writes the content.
     * 
     * @param stream
     *            The stream to write to.
     * @return True if successful, False otherwise.
     */
    public boolean write(ObjectOutputStream stream) {
        boolean ret = false;
        try {
            stream.write(fileVersion.getVersionMajor());
            stream.write(fileVersion.getVersionMinor());
            stream.write(fileVersion.getVersionBugfix());
            // layers count
            stream.write(layers.size());

            for (MetadataLayer layer : layers) {
                stream.writeUTF(layer.getName());
                stream.writeLong(layer.getFilePos());
            }
            ret = true;
        } catch (IOException e) {
            Jbogx2D.getErrorHandler().handleError(e, true, false);
        }

        return ret;
    }

    /**
     * Reads the content.
     * 
     * @param stream
     *            The stream to read from.
     * @return True if successful, False otherwise.
     */
    public boolean read(InputStream stream) {
        // TODO implement read...
        return false;
    }
}

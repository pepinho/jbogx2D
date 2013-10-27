//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: PositionedObjectOutputStream.java
// Created: 22.02.2010 - 21:20:31
//
package de.jbo.jbogx2d.base.io.formats.jbogx;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Implements an ObjectOutputStream that provides the current byte
 * file-position.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 22.02.2010 jbo - created <br>
 */
public class PositionedObjectOutputStream extends ObjectOutputStream {
    /** Byte position. */
    private long pos = 0;

    /**
     * @throws IOException
     *             Possible exception.
     */
    public PositionedObjectOutputStream() throws IOException {
        super();
    }

    /**
     * @param out
     *            Outputstream to be wrapped.
     * @throws IOException
     *             Possible exception.
     */
    public PositionedObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    /**
     * @return The current position.
     */
    public long getPos() {
        return pos;
    }

    /*
     * @see java.io.ObjectOutputStream#write(byte[], int, int)
     */
    @Override
    public void write(byte[] buf, int off, int len) throws IOException {
        pos += len;
        super.write(buf, off, len);
    }

    /*
     * @see java.io.ObjectOutputStream#write(byte[])
     */
    @Override
    public void write(byte[] buf) throws IOException {
        pos += buf.length;
        super.write(buf);
    }

    /*
     * @see java.io.ObjectOutputStream#write(int)
     */
    @Override
    public void write(int val) throws IOException {
        pos++;
        super.write(val);
    }

}

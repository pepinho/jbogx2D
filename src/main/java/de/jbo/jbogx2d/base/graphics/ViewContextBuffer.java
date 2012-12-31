//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ViewBuffer.java
// Created: 29.02.2004 - 15:52:44
//

package de.jbo.jbogx2d.base.graphics;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;

import de.jbo.jbogx2d.base.geom.BoundsScreen;

/**
 * Implements a buffer view context used for flicker-free double-buffering.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 29.02.2004: jbo created <br>
 */
public class ViewContextBuffer extends ViewContext {
    /** The buffer-image used for rendering. */
    private Image image = null;

    /** Width of the buffer image. */
    private int width = 0;

    /** Height of the buffer image. */
    private int height = 0;

    /** Reference view-component used by a window-view. */
    private Component viewComponent = null;

    /**
     * Creates a new instance.
     * 
     * @param w
     *            Width of the buffer.
     * @param h
     *            Height of the buffer.
     */
    public ViewContextBuffer(int w, int h) {
        super();
        setSize(w, h);
    }

    /**
     * Creates a new instance.
     * 
     * @param viewComponent
     *            Referenced window component. The new buffer will have the
     *            exact same dimension as the referenced component.
     */
    public ViewContextBuffer(Component viewComponent) {
        this.viewComponent = viewComponent;
        Dimension size = viewComponent.getSize();
        setSize(size.width, size.height);
    }

    /*
     * @see de.jbo.jbogx2d.base.graphics.ViewContext#createGraphicsContext()
     */
    @Override
    protected Graphics2D createGraphicsContext() {
        Graphics2D gc = null;

        if (image == null) {
            if (viewComponent == null) {
                image = createImage(width, height);
            } else {
                image = createImage(viewComponent);
            }
        }
        if (image != null) {
            gc = (Graphics2D) image.getGraphics();
            if (gc != null) {
                gc.setRenderingHints(mapRenderingHints);
            }
        }
        return gc;
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.graphics.ViewContext#getBounds(de.jbo.jbogx2d.base
     * .geom.BoundsScreen)
     */
    @Override
    public void getBounds(BoundsScreen bounds) {
        Image img = getImage();
        if (img != null) {
            bounds.x = 0;
            bounds.y = 0;
            bounds.width = img.getWidth(this);
            bounds.height = img.getHeight(this);
        }
    }

    /*
     * @see de.jbo.jbogx2d.base.graphics.ViewContext#getImage()
     */
    @Override
    protected Image getImage() {
        if (image == null) {
            if (viewComponent == null) {
                image = createImage(width, height);
            } else {
                image = createImage(viewComponent);
            }
        }
        return image;
    }

    /**
     * Sets the dimension of the buffer.
     * 
     * @param w
     *            Width to be set.
     * @param h
     *            Height to be set.
     */
    public void setSize(int w, int h) {
        width = w;
        height = h;
        if (image != null) {
            image.flush();
            image = null;
        }
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.graphics.ViewContext#updateUserSpaceCoordinates(boolean
     * )
     */
    @Override
    public void updateUserSpaceCoordinates(boolean sizeChanged) {
        if (sizeChanged) {
            dispose();
            if (image != null) {
                image.flush();
            }
            image = null;
        }

        super.updateUserSpaceCoordinates(sizeChanged);
    }

}
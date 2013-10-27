//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ViewScreen.java
// Created: 28.02.2004 - 22:39:30
//

package de.jbo.jbogx2d.base.graphics;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;

import de.jbo.jbogx2d.base.geom.BoundsScreen;

/**
 * Implements the View rendering output to a component on the screen.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 28.02.2004: jbo created <br>
 */
public class ViewContextScreen extends ViewContext {
    /** The base component serving as output source for rendering. */
    private Component screenComponent = null;

    /**
     * Creates a new view instance rendering its output to the given screen
     * component.
     * 
     * @param screen
     *            The component used for rendering output.
     */
    public ViewContextScreen(Component screen) {
        super();
        screenComponent = screen;
    }

    /*
     * @see de.jbo.jbogx2d.base.graphics.ViewContext#createGraphicsContext()
     */
    @Override
    protected Graphics2D createGraphicsContext() {
        Graphics2D gc = (Graphics2D) screenComponent.getGraphics();
        if (gc != null) {
            gc.setRenderingHints(mapRenderingHints);
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
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = screenComponent.getWidth();
        bounds.height = screenComponent.getHeight();
    }

    /**
     * Sets a new external graphics-context to be used for rendering.
     * 
     * @param gc
     *            The rendering context to be set.
     */
    public void setGraphicsContext(Graphics2D gc) {
        graphicsContext = gc;
        isExternalGraphicsSet = true;
    }

    /*
     * @see de.jbo.jbogx2d.base.graphics.ViewContext#getImage()
     */
    @Override
    protected Image getImage() {
        return createImage(screenComponent.getWidth(), screenComponent.getHeight());
    }

    /**
     * @return the screenComponent
     */
    public final Component getScreenComponent() {
        return screenComponent;
    }

}
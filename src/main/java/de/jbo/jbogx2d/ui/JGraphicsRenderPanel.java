//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: JGraphicsRenderPanel.java
// Created: 06.02.2010 - 14:10:39
//
package de.jbo.jbogx2d.ui;

import java.awt.Graphics;

import javax.swing.JPanel;

import de.jbo.jbogx2d.base.drawing.Drawing;
import de.jbo.jbogx2d.base.geom.BoundsScreen;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointScreen;
import de.jbo.jbogx2d.base.geom.PointUserSpace;
import de.jbo.jbogx2d.base.graphics.IScrollListener;
import de.jbo.jbogx2d.base.graphics.IZoomListener;
import de.jbo.jbogx2d.base.graphics.ViewHandler;
import de.jbo.jbogx2d.ui.interaction.IMouseInteraction;

/**
 * Implements a panel rendering a Drawing instance.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 06.02.2010 jbo - created <br>
 */
final class JGraphicsRenderPanel extends JPanel {

    /**
     * Serial id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The view rendering handler.
     */
    private ViewHandler viewHandler = null;

    /**
     * Creates a new instance.
     */
    JGraphicsRenderPanel() {
        super();
        viewHandler = new ViewHandler(this);
    }

    /*
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        viewHandler.paint(g);
    }

    /**
     * @param bounds
     *            Output bounds.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#getBounds(de.jbo.jbogx2d.base.geom.BoundsScreen)
     */
    public void getBounds(BoundsScreen bounds) {
        viewHandler.getBounds(bounds);
    }

    /**
     * @return The current grid-color.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#getGridColor()
     */
    public short getGridColor() {
        return viewHandler.getGridColor();
    }

    /**
     * @return The current grid-line stroke.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#getGridLineStroke()
     */
    public short getGridLineStroke() {
        return viewHandler.getGridLineStroke();
    }

    /**
     * @return The current grid-spacing in x-direction.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#getGridSpacingX()
     */
    public double getGridSpacingX() {
        return viewHandler.getGridSpacingX();
    }

    /**
     * @return The current grid-spacing in y-direction.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#getGridSpacingY()
     */
    public double getGridSpacingY() {
        return viewHandler.getGridSpacingY();
    }

    /**
     * @return The current mouse-interaction.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#getMouseInteraction()
     */
    public IMouseInteraction getMouseInteraction() {
        return viewHandler.getMouseInteraction();
    }

    /**
     * @param visibleBounds
     *            The visible-area to be displayed.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#getVisibleUserSpace(de.jbo.jbogx2d.base.geom.BoundsUserSpace)
     */
    public void getVisibleUserSpace(BoundsUserSpace visibleBounds) {
        viewHandler.getVisibleUserSpace(visibleBounds);
    }

    /**
     * @param d
     *            The drawing to initialize with.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#init(de.jbo.jbogx2d.base.drawing.Drawing)
     */
    public void init(Drawing d) {
        viewHandler.init(d);
    }

    /**
     * @return The current drawing being rendered.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#getDrawing()
     */
    public Drawing getDrawing() {
        return viewHandler.getDrawing();
    }

    /**
     * @param screen
     *            Input screen-coordinates.
     * @param userSpace
     *            Output user-space-coordinates.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#transformScreenToUserSpace(de.jbo.jbogx2d.base.geom.BoundsScreen,
     *      de.jbo.jbogx2d.base.geom.BoundsUserSpace)
     */
    public void transformScreenToUserSpace(BoundsScreen screen, BoundsUserSpace userSpace) {
        viewHandler.transformScreenToUserSpace(screen, userSpace);
    }

    /**
     * @param screen
     *            Input screen-coordinates.
     * @param userSpace
     *            Output user-space-coordinates.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#transformScreenToUserSpace(de.jbo.jbogx2d.base.geom.PointScreen,
     *      de.jbo.jbogx2d.base.geom.PointUserSpace)
     */
    public void transformScreenToUserSpace(PointScreen screen, PointUserSpace userSpace) {
        viewHandler.transformScreenToUserSpace(screen, userSpace);
    }

    /**
     * @param userSpace
     *            Output user-space-coordinates.
     * @param screen
     *            Input screen-coordinates.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#transformUserSpaceToScreen(de.jbo.jbogx2d.base.geom.BoundsUserSpace,
     *      de.jbo.jbogx2d.base.geom.BoundsScreen)
     */
    public void transformUserSpaceToScreen(BoundsUserSpace userSpace, BoundsScreen screen) {
        viewHandler.transformUserSpaceToScreen(userSpace, screen);
    }

    /**
     * @param userSpace
     *            Output user-space-coordinates.
     * @param screen
     *            Input screen-coordinates.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#transformUserSpaceToScreen(de.jbo.jbogx2d.base.geom.PointUserSpace,
     *      de.jbo.jbogx2d.base.geom.PointScreen)
     */
    public void transformUserSpaceToScreen(PointUserSpace userSpace, PointScreen screen) {
        viewHandler.transformUserSpaceToScreen(userSpace, screen);
    }

    /**
     * @return True if anti-aliasing is set, False otherwise.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#isAntiAliasing()
     */
    public boolean isAntiAliasing() {
        return viewHandler.isAntiAliasing();
    }

    /**
     * @return True if initialized, False otherwise.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#isInitialized()
     */
    public boolean isInitialized() {
        return viewHandler.isInitialized();
    }

    /**
     * @param set
     *            True to activate anti-aliasing, False otherwise.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#setAntiAliasing(boolean)
     */
    public void setAntiAliasing(boolean set) {
        viewHandler.setAntiAliasing(set);
    }

    /**
     * @param color
     *            The grid-color index to be set.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#setGridColor(short)
     */
    public void setGridColor(short color) {
        viewHandler.setGridColor(color);
    }

    /**
     * @param stroke
     *            The grid line-stroke to be set.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#setGridLineStroke(short)
     */
    public void setGridLineStroke(short stroke) {
        viewHandler.setGridLineStroke(stroke);
    }

    /**
     * @param mode
     *            The grid-mode to be set.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#setGridMode(int)
     */
    public void setGridMode(int mode) {
        viewHandler.setGridMode(mode);
    }

    /**
     * @param spacingX
     *            The grid-spacing in x-direction.
     * @param spacingY
     *            The grid-spacing in y-dicection.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#setGridSpacings(double,
     *      double)
     */
    public void setGridSpacings(double spacingX, double spacingY) {
        viewHandler.setGridSpacings(spacingX, spacingY);
    }

    /**
     * @param visible
     *            True to make grid visible, False otherwise.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#setGridVisible(boolean)
     */
    public void setGridVisible(boolean visible) {
        viewHandler.setGridVisible(visible);
    }

    /**
     * @param mouseInteract
     *            The mouse-interaction to be set.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#setMouseInteraction(de.jbo.jbogx2d.ui.interaction.IMouseInteraction)
     */
    public void setMouseInteraction(IMouseInteraction mouseInteract) {
        viewHandler.setMouseInteraction(mouseInteract);
    }

    /**
     * @param visibleBounds
     *            The visible area to be set.
     * @param sizeChanged
     *            True if the size has changed, False otherwise.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#setVisibleUserSpace(de.jbo.jbogx2d.base.geom.BoundsUserSpace,
     *      boolean)
     */
    public void setVisibleUserSpace(BoundsUserSpace visibleBounds, boolean sizeChanged) {
        viewHandler.setVisibleUserSpace(visibleBounds, sizeChanged);
    }

    /**
     * @param uninitInteraction
     *            True to uninitialize interaction, False otherwise.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#uninit(boolean)
     */
    public void uninit(boolean uninitInteraction) {
        viewHandler.uninit(uninitInteraction);
    }

    /**
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#updateAttributes()
     */
    public void updateAttributes() {
        viewHandler.updateAttributes();
    }

    /*
     * @see java.awt.Component#repaint()
     */
    @Override
    public void repaint() {
        super.repaint();
        if (viewHandler != null) {
            viewHandler.repaint();
        }
    }

    /**
     * @param listener
     *            The listener to be added.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#addScrollListener(de.jbo.jbogx2d.base.graphics.IScrollListener)
     */
    public void addScrollListener(IScrollListener listener) {
        viewHandler.addScrollListener(listener);
    }

    /**
     * @param listener
     *            The listener to be added.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#addZoomListener(de.jbo.jbogx2d.base.graphics.IZoomListener)
     */
    public void addZoomListener(IZoomListener listener) {
        viewHandler.addZoomListener(listener);
    }

    /**
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#removeAllScrollListeners()
     */
    public void removeAllScrollListeners() {
        viewHandler.removeAllScrollListeners();
    }

    /**
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#removeAllZoomListeners()
     */
    public void removeAllZoomListeners() {
        viewHandler.removeAllZoomListeners();
    }

    /**
     * @param listener
     *            The listener to be removed.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#removeScrollListener(de.jbo.jbogx2d.base.graphics.IScrollListener)
     */
    public void removeScrollListener(IScrollListener listener) {
        viewHandler.removeScrollListener(listener);
    }

    /**
     * @param listener
     *            The listener to be removed.
     * @see de.jbo.jbogx2d.base.graphics.ViewHandler#removeZoomListener(de.jbo.jbogx2d.base.graphics.IZoomListener)
     */
    public void removeZoomListener(IZoomListener listener) {
        viewHandler.removeZoomListener(listener);
    }

}

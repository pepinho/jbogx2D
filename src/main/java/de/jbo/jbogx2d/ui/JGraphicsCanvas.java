//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: JGraphicsCanvas.java
// Created: 06.02.2010 - 14:00:10
//
package de.jbo.jbogx2d.ui;

import javax.swing.JPanel;

import de.jbo.jbogx2d.base.drawing.Drawing;
import de.jbo.jbogx2d.base.geom.BoundsScreen;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointScreen;
import de.jbo.jbogx2d.base.geom.PointUserSpace;
import de.jbo.jbogx2d.base.graphics.IScrollListener;
import de.jbo.jbogx2d.base.graphics.IZoomListener;
import de.jbo.jbogx2d.ui.interaction.IMouseInteraction;

/**
 * Implements a graphics canvas that provides the following features: <br>
 * <ul>
 * <li>Rendering of a Drawing instance</li>
 * <li>Double-buffer for flicker-free rendering</li>
 * <li>Zooming/Scrolling of visible area via API</li>
 * <li>Integration of MouseInteractions</li>
 * <li>Integration of coordinate-rulers displays for x- and y-axis.</li>
 * </ul>
 * 
 * @author Josef Baro (jbo) <br>
 * @version 06.02.2010 jbo - created <br>
 */
public class JGraphicsCanvas extends JPanel {

    /**
     * Serial id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The main graphics render panel.
     */
    private JGraphicsRenderPanel renderPanel = null;

    /**
     * Ruler for x-axis.
     */
    private JGraphicsCanvasRuler rulerX = null;

    /**
     * Ruler for y-axis.
     */
    private JGraphicsCanvasRuler rulerY = null;

    /**
     * Creates a new canvas.
     */
    public JGraphicsCanvas() {
        super(new CanvasBorderLayout(0, 0));
        initUI();
    }

    /**
     * Initializes the UI of this control.
     */
    private void initUI() {
        renderPanel = new JGraphicsRenderPanel();
        add(renderPanel, CanvasBorderLayout.CENTER);
        rulerX = new JGraphicsCanvasRuler(this, CanvasRulerMode.X_AXIS);
        rulerY = new JGraphicsCanvasRuler(this, CanvasRulerMode.Y_AXIS);
        add(rulerX, CanvasBorderLayout.NORTH);
        add(rulerY, CanvasBorderLayout.WEST);
    }

    /**
     * @param bounds
     *            Output bounds.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#getBounds(de.jbo.jbogx2d.base.geom.BoundsScreen)
     */
    public void getBounds(BoundsScreen bounds) {
        renderPanel.getBounds(bounds);
    }

    /**
     * @return The grid color index.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#getGridColor()
     */
    public short getGridColor() {
        return renderPanel.getGridColor();
    }

    /**
     * @return The grid line stroke.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#getGridLineStroke()
     */
    public short getGridLineStroke() {
        return renderPanel.getGridLineStroke();
    }

    /**
     * @return The grid spacing in x-direction.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#getGridSpacingX()
     */
    public double getGridSpacingX() {
        return renderPanel.getGridSpacingX();
    }

    /**
     * @return The grid spacing in y-direction.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#getGridSpacingY()
     */
    public double getGridSpacingY() {
        return renderPanel.getGridSpacingY();
    }

    /**
     * @return The current mouse-interaction.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#getMouseInteraction()
     */
    public IMouseInteraction getMouseInteraction() {
        return renderPanel.getMouseInteraction();
    }

    /**
     * @param visibleBounds
     *            The visible area to be set.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#getVisibielUserSpace(de.jbo.jbogx2d.base.geom.BoundsUserSpace)
     */
    public void getVisibielUserSpace(BoundsUserSpace visibleBounds) {
        renderPanel.getVisibielUserSpace(visibleBounds);
    }

    /**
     * @param d
     *            The drawing to initialize with.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#init(de.jbo.jbogx2d.base.drawing.Drawing)
     */
    public void init(Drawing d) {
        renderPanel.init(d);
    }

    /**
     * @return True if anti-aliasing is set, False otherwise.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#isAntiAliasing()
     */
    public boolean isAntiAliasing() {
        return renderPanel.isAntiAliasing();
    }

    /**
     * @return The current drawing.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#getDrawing()
     */
    public Drawing getDrawing() {
        return renderPanel.getDrawing();
    }

    /**
     * @param screen
     *            Input screen-coordinates.
     * @param userSpace
     *            Output user-space-coordinates.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#transformScreenToUserSpace(de.jbo.jbogx2d.base.geom.BoundsScreen,
     *      de.jbo.jbogx2d.base.geom.BoundsUserSpace)
     */
    public void transformScreenToUserSpace(BoundsScreen screen, BoundsUserSpace userSpace) {
        renderPanel.transformScreenToUserSpace(screen, userSpace);
    }

    /**
     * @param screen
     *            Input screen-coordinates.
     * @param userSpace
     *            Output user-space-coordinates.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#transformScreenToUserSpace(de.jbo.jbogx2d.base.geom.PointScreen,
     *      de.jbo.jbogx2d.base.geom.PointUserSpace)
     */
    public void transformScreenToUserSpace(PointScreen screen, PointUserSpace userSpace) {
        renderPanel.transformScreenToUserSpace(screen, userSpace);
    }

    /**
     * @param userSpace
     *            Output user-space-coordinates.
     * @param screen
     *            Input screen-coordinates.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#transformUserSpaceToScreen(de.jbo.jbogx2d.base.geom.BoundsUserSpace,
     *      de.jbo.jbogx2d.base.geom.BoundsScreen)
     */
    public void transformUserSpaceToScreen(BoundsUserSpace userSpace, BoundsScreen screen) {
        renderPanel.transformUserSpaceToScreen(userSpace, screen);
    }

    /**
     * @param userSpace
     *            Output user-space-coordinates.
     * @param screen
     *            Input screen-coordinates.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#transformUserSpaceToScreen(de.jbo.jbogx2d.base.geom.PointUserSpace,
     *      de.jbo.jbogx2d.base.geom.PointScreen)
     */
    public void transformUserSpaceToScreen(PointUserSpace userSpace, PointScreen screen) {
        renderPanel.transformUserSpaceToScreen(userSpace, screen);
    }

    /**
     * @return True if initialized, False otherwise.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#isInitialized()
     */
    public boolean isInitialized() {
        return renderPanel.isInitialized();
    }

    /**
     * @param set
     *            True to activate anti-aliasing, False otherwise.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#setAntiAliasing(boolean)
     */
    public void setAntiAliasing(boolean set) {
        renderPanel.setAntiAliasing(set);
    }

    /**
     * @param color
     *            The color to be set for the grid.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#setGridColor(short)
     */
    public void setGridColor(short color) {
        renderPanel.setGridColor(color);
    }

    /**
     * @param stroke
     *            The line-stroke to be set for the grid.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#setGridLineStroke(short)
     */
    public void setGridLineStroke(short stroke) {
        renderPanel.setGridLineStroke(stroke);
    }

    /**
     * @param mode
     *            The grid-mode to be set.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#setGridMode(int)
     */
    public void setGridMode(int mode) {
        renderPanel.setGridMode(mode);
    }

    /**
     * @param spacingX
     *            The grid spacing in x-direction.
     * @param spacingY
     *            The grid spacing in y-direction.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#setGridSpacings(double,
     *      double)
     */
    public void setGridSpacings(double spacingX, double spacingY) {
        renderPanel.setGridSpacings(spacingX, spacingY);
    }

    /**
     * @param visible
     *            True to make grid visible, False otherwise.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#setGridVisible(boolean)
     */
    public void setGridVisible(boolean visible) {
        renderPanel.setGridVisible(visible);
    }

    /**
     * @param mouseInteract
     *            The mouse-interaction to be set.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#setMouseInteraction(de.jbo.jbogx2d.ui.interaction.IMouseInteraction)
     */
    public void setMouseInteraction(IMouseInteraction mouseInteract) {
        renderPanel.setMouseInteraction(mouseInteract);
    }

    /**
     * @param visibleBounds
     *            The visible area to be set.
     * @param sizeChanged
     *            True if the size of the canvas has changed, False otherwise.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#setVisibleUserSpace(de.jbo.jbogx2d.base.geom.BoundsUserSpace,
     *      boolean)
     */
    public void setVisibleUserSpace(BoundsUserSpace visibleBounds, boolean sizeChanged) {
        renderPanel.setVisibleUserSpace(visibleBounds, sizeChanged);
    }

    /**
     * @param uninitInteraction
     *            True to uninit the interaction, False otherwise.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#uninit(boolean)
     */
    public void uninit(boolean uninitInteraction) {
        renderPanel.uninit(uninitInteraction);
    }

    /*
     * @see java.awt.Component#repaint()
     */
    @Override
    public void repaint() {
        super.repaint();
        if (renderPanel != null) {
            renderPanel.repaint();
        }
    }

    /**
     * @param listener
     *            The listener to be added.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#addScrollListener(de.jbo.jbogx2d.base.graphics.IScrollListener)
     */
    public void addScrollListener(IScrollListener listener) {
        renderPanel.addScrollListener(listener);
    }

    /**
     * @param listener
     *            The listener to be added.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#addZoomListener(de.jbo.jbogx2d.base.graphics.IZoomListener)
     */
    public void addZoomListener(IZoomListener listener) {
        renderPanel.addZoomListener(listener);
    }

    /**
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#removeAllScrollListeners()
     */
    public void removeAllScrollListeners() {
        renderPanel.removeAllScrollListeners();
    }

    /**
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#removeAllZoomListeners()
     */
    public void removeAllZoomListeners() {
        renderPanel.removeAllZoomListeners();
    }

    /**
     * @param listener
     *            The listener to be removed.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#removeScrollListener(de.jbo.jbogx2d.base.graphics.IScrollListener)
     */
    public void removeScrollListener(IScrollListener listener) {
        renderPanel.removeScrollListener(listener);
    }

    /**
     * @param listener
     *            The listener to be removed.
     * @see de.jbo.jbogx2d.ui.JGraphicsRenderPanel#removeZoomListener(de.jbo.jbogx2d.base.graphics.IZoomListener)
     */
    public void removeZoomListener(IZoomListener listener) {
        renderPanel.removeZoomListener(listener);
    }

}

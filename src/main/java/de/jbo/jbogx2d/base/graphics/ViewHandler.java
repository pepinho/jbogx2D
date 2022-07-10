//
// Copyright (c) 2004 by jbo - Josef Baro
//
// Project: jbogx2D
// File: ViewHandler.java
// Created: 01.03.2004 - 18:19:12
//

package de.jbo.jbogx2d.base.graphics;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import de.jbo.jbogx2d.base.drawing.Drawing;
import de.jbo.jbogx2d.base.drawing.IDrawingModifiedListener;
import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.geom.BoundsScreen;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointScreen;
import de.jbo.jbogx2d.base.geom.PointUserSpace;
import de.jbo.jbogx2d.base.util.StopTimer;
import de.jbo.jbogx2d.ui.interaction.IMouseInteraction;
import de.jbo.jbogx2d.ui.interaction.MouseInteractionHandler;

/**
 * Implements an instance handling rendering to a screen-component.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 01.03.2004: jbo created <br>
 */
public class ViewHandler implements IDrawingModifiedListener {
    /** The component to render to. */
    private Component screenComponent = null;

    /** The buffer-view rendering to. */
    private ViewContextBuffer viewBuffer = null;

    /** The buffer-view for rendering mouse-interaction XOR elements to. */
    private ViewContextBuffer viewXor = null;

    /** The screen-view rendering to the screen-component. */
    private ViewContextScreen viewScreen = null;

    /** The drawing being rendered. */
    private Drawing drawing = null;

    /** Current visible area (scrolled or zoomed to). */
    private final BoundsUserSpace visibleUserSpace = new BoundsUserSpace();

    /** Size of the screen-component being rendered to. */
    private final Dimension screenSize = new Dimension();

    /** View-elements being rendered to the current visible area. */
    private Collection<ElemBase> viewElements = null;

    /** The drawing grid being rendered in the drawings background. */
    private final ViewDrawingGrid viewDrawingGrid = new ViewDrawingGrid();

    /** The mouse-interaction handler for this instance. */
    private MouseInteractionHandler mouseInteractionHandler = null;

    /** Collection of zoom-listeners. */
    private Collection<IZoomListener> zoomListeners = null;

    /** Collection of scroll-listeners. */
    private Collection<IScrollListener> scrollListeners = null;

    /** Modified drawing-bounds that need to be repainted. */
    private BoundsUserSpace modifiedDrawingBounds = null;

    /** Indicates if buffer needs refresh. */
    private boolean isBufferNeedRefresh = true;

    /**
     * Creates a new instance.
     * 
     * @param screenComp
     *            The screen-component to render to.
     */
    public ViewHandler(Component screenComp) {
        super();
        screenComponent = screenComp;
        viewBuffer = new ViewContextBuffer(screenComponent);
        viewXor = new ViewContextBuffer(screenComponent);
        viewScreen = new ViewContextScreen(screenComponent);
        mouseInteractionHandler = new MouseInteractionHandler(viewScreen, this);

        zoomListeners = new LinkedList<>();
        scrollListeners = new LinkedList<>();
    }

    /**
     * @return True if initialized, False otherwise.
     */
    public boolean isInitialized() {
        return (drawing != null);
    }

    /**
     * @return the drawing
     */
    public final Drawing getDrawing() {
        return drawing;
    }

    /**
     * @param screen
     *            Input screen-coordinates.
     * @param userSpace
     *            Output user-space-coordinates.
     * @see de.jbo.jbogx2d.base.graphics.ViewContext#transformScreenToUserSpace(de.jbo.jbogx2d.base.geom.BoundsScreen,
     *      de.jbo.jbogx2d.base.geom.BoundsUserSpace)
     */
    public void transformScreenToUserSpace(BoundsScreen screen, BoundsUserSpace userSpace) {
        viewScreen.transformScreenToUserSpace(screen, userSpace);
    }

    /**
     * @param screen
     *            Input screen-coordinates.
     * @param userSpace
     *            Output user-space-coordinates.
     * @see de.jbo.jbogx2d.base.graphics.ViewContext#transformScreenToUserSpace(de.jbo.jbogx2d.base.geom.PointScreen,
     *      de.jbo.jbogx2d.base.geom.PointUserSpace)
     */
    public void transformScreenToUserSpace(PointScreen screen, PointUserSpace userSpace) {
        viewScreen.transformScreenToUserSpace(screen, userSpace);
    }

    /**
     * @param userSpace
     *            Output user-space-coordinates.
     * @param screen
     *            Input screen-coordinates.
     * @see de.jbo.jbogx2d.base.graphics.ViewContext#transformUserSpaceToScreen(de.jbo.jbogx2d.base.geom.BoundsUserSpace,
     *      de.jbo.jbogx2d.base.geom.BoundsScreen)
     */
    public void transformUserSpaceToScreen(BoundsUserSpace userSpace, BoundsScreen screen) {
        viewScreen.transformUserSpaceToScreen(userSpace, screen);
    }

    /**
     * @param userSpace
     *            Output user-space-coordinates.
     * @param screen
     *            Input screen-coordinates.
     * @see de.jbo.jbogx2d.base.graphics.ViewContext#transformUserSpaceToScreen(de.jbo.jbogx2d.base.geom.PointUserSpace,
     *      de.jbo.jbogx2d.base.geom.PointScreen)
     */
    public void transformUserSpaceToScreen(PointUserSpace userSpace, PointScreen screen) {
        viewScreen.transformUserSpaceToScreen(userSpace, screen);
    }

    /**
     * The current visible area being rendered.
     * 
     * @param visibleBounds
     *            Out-parameter for the visible area.
     */
    public void getVisibleUserSpace(BoundsUserSpace visibleBounds) {
        getVisibleUserSpace(visibleBounds, false);
    }

    /**
     * The visible area being rendered.
     * 
     * @param visibleBounds
     *            Out-parameter for the visible area.
     * @param matchedRatio
     *            True if matched to the view's ratio, otherwise only the actual
     *            part is returned.
     */
    public void getVisibleUserSpace(BoundsUserSpace visibleBounds, boolean matchedRatio) {
        if (!matchedRatio) {
            visibleBounds.set(visibleUserSpace);
        } else {
            viewBuffer.getVisibleUserSpace(visibleBounds);
        }
    }

    /**
     * Sets the new visible-area to be rendered.
     * 
     * @param visibleBounds
     *            The area to be set.
     * @param sizeChanged
     *            True if the size of the render-component has changed, False
     *            otherwise.
     */
    public synchronized void setVisibleUserSpace(BoundsUserSpace visibleBounds, boolean sizeChanged) {
        boolean isZoom = true;
        BoundsUserSpace boundsBefore = new BoundsUserSpace(visibleUserSpace.x, visibleUserSpace.y, visibleUserSpace.width, visibleUserSpace.height);

        /*
         * Zoom or scroll?
         */
        if ((boundsBefore.width == visibleBounds.width) && (boundsBefore.height == visibleBounds.height)) {
            /*
             * Scroll...
             */
            isZoom = false;
            fireScrollStartEvent(boundsBefore, visibleBounds);
        } else {
            /*
             * Zoom...
             */
            fireZoomStartEvent(boundsBefore, visibleBounds);
        }

        viewBuffer.setVisibleUserSpace(visibleBounds, sizeChanged);
        viewXor.setVisibleUserSpace(visibleBounds, sizeChanged);
        viewScreen.setVisibleUserSpace(visibleBounds, sizeChanged);
        visibleUserSpace.set(visibleBounds);

        /*
         * VisibleBounds matchen mit den Seitenverh�ltnissen des sichtbaren
         * views. So stellen wir sicher dass der Quadtree alle sichtbaren
         * Elemente liefert, f�r den FAll dass die �bergebenen bounds nicht dem
         * Seitenverh�ltnis des Views entsprechen.
         */
        BoundsUserSpace vbMatched = new BoundsUserSpace();
        viewBuffer.getVisibleUserSpace(vbMatched);
        initElemViewList(vbMatched);

        viewDrawingGrid.updateVisibleUserSpace(viewBuffer);

        if (isZoom) {
            fireZoomEndEvent(boundsBefore, visibleUserSpace);
        } else {
            fireScrollEndEvent(boundsBefore, visibleUserSpace);
        }

        isBufferNeedRefresh = true;
    }

    /**
     * Initializes this instance with the given drawing.
     * 
     * @param d
     *            The drawing to initialize with.
     */
    public void init(Drawing d) {
        isBufferNeedRefresh = true;
        BoundsUserSpace drawingUserSpace = new BoundsUserSpace();
        drawing = d;

        drawing.getUserSpaceBounds(drawingUserSpace);

        viewDrawingGrid.setDrawingBounds(drawingUserSpace, viewBuffer);

        drawing.initLayerQuadTrees();

        setVisibleUserSpace(drawingUserSpace, true);
        drawing.addModificationListener(this);
    }

    /**
     * Initializes the list of the render-elements for the given
     * coordinate-area.
     * 
     * @param vb
     *            The area to initialize the render-elements for.
     */
    private void initElemViewList(BoundsUserSpace vb) {
        StopTimer timer = new StopTimer();
        timer.start();
        viewElements = drawing.getElementsByBounds(vb, true);
        timer.stop("initElementViewList()");
    }

    private boolean isModifiedDrawingBoundsIntersectsVisibleUserSpace() {
        return (modifiedDrawingBounds != null && modifiedDrawingBounds.intersects(visibleUserSpace));
    }

    /**
     * Renders this instance to the given graphics-context.
     * 
     * @param g
     *            The graphics-context to render to.
     */
    public void paint(Graphics g) {
        if (!isInitialized()) {
            return;
        }
        BoundsUserSpace flushBounds = null;
        Dimension currentScreenSize = screenComponent.getSize();
        updateAttributes();

        if (!currentScreenSize.equals(screenSize)) {
            paintResize(currentScreenSize);
        } else if (isBufferNeedRefresh || isModifiedDrawingBoundsIntersectsVisibleUserSpace()) {
            Collection<ElemBase> elems = null;

            if (modifiedDrawingBounds != null) {
                elems = drawing.getElementsByBounds(modifiedDrawingBounds, true);
            } else {
                elems = viewElements;
            }
            /*
             * Clear with background-color
             */
            viewBuffer.clearView(modifiedDrawingBounds);

            /*
             * Grid?
             */
            if (viewDrawingGrid.isVisible()) {
                viewDrawingGrid.updateAttributes(viewBuffer);
                viewDrawingGrid.paint(viewBuffer);
            }

            /*
             * Paint the relevant quadrants of the view...
             */
            draw(elems, viewBuffer);

            flushBounds = modifiedDrawingBounds;
            modifiedDrawingBounds = null;
            isBufferNeedRefresh = false;
        }
        viewXor.flushView(flushBounds, viewBuffer);
        mouseInteractionHandler.paintXorElemens(viewXor);
        viewScreen.setGraphicsContext((Graphics2D) g, true);
        viewScreen.flushView(flushBounds, viewXor);
        viewScreen.resetGraphicsContext();
    }

    private void paintResize(Dimension currentScreenSize) {
        setVisibleUserSpace(visibleUserSpace, true);

        /*
         * Clear with background-color
         */
        viewBuffer.clearView();

        /*
         * Grid?
         */
        if (viewDrawingGrid.isVisible()) {
            viewDrawingGrid.updateAttributes(viewBuffer);
            viewDrawingGrid.paint(viewBuffer);
        }

        /*
         * Paint the relevant quadrants of the view...
         */
        draw(viewElements, viewBuffer);

        screenSize.setSize(currentScreenSize);

        modifiedDrawingBounds = null;
        isBufferNeedRefresh = false;
    }

    /**
     * Renders a list of elements to a given view-context.
     * 
     * @param elemViews
     *            Elements to be rendered.
     * @param viewContext
     *            Context to render to.
     */
    private void draw(Collection<ElemBase> elemViews, ViewContext viewContext) {
        Iterator<ElemBase> iterator = elemViews.iterator();
        ElemBase elem = null;

        System.out.println("Drawing " + elemViews.size() + " elements...");

        while (iterator.hasNext()) {
            elem = iterator.next();
            if ((elem != null) && elem.isVisible()) {
                elem.getView().paint(viewContext);
            }
        }
    }

    /**
     * Renders this instance.
     */
    public void repaint() {
        StopTimer timer = new StopTimer();
        timer.start();

        if ((modifiedDrawingBounds != null) || isBufferNeedRefresh) {
            Collection<ElemBase> elems = null;
            if (modifiedDrawingBounds != null) {
                elems = drawing.getElementsByBounds(modifiedDrawingBounds, true);
            } else {
                elems = viewElements;
            }
            /*
             * Clear with background-color
             */
            viewBuffer.clearView(isBufferNeedRefresh ? null : modifiedDrawingBounds);
            timer.split("viewBuffer.clearView(BoundsUserSpace)");
            /*
             * Grid?
             */
            if (viewDrawingGrid.isVisible()) {
                viewDrawingGrid.paint(viewBuffer);
                timer.split("viewDrawingGrid.paint()");
            }
            /*
             * Paint the relevant quadrants of the view...
             */
            draw(elems, viewBuffer);
            timer.split("draw()");
        }
        viewXor.flushView(isBufferNeedRefresh ? null : modifiedDrawingBounds, viewBuffer);
        mouseInteractionHandler.paintXorElemens(viewXor);
        viewScreen.flushView(isBufferNeedRefresh ? null : modifiedDrawingBounds, viewXor);
        timer.stop("ViewHandler.repaint()");
        modifiedDrawingBounds = null;
        isBufferNeedRefresh = false;
    }

    /**
     * Uninitializes this instance.
     * 
     * @param uninitInteraction
     *            True to also uninit the mouse-interaction handling.
     */
    public void uninit(boolean uninitInteraction) {
        if (isInitialized()) {
            drawing.removeModificationListener(this);
            drawing = null;
            viewElements.clear();
        }
        if (uninitInteraction) {
            mouseInteractionHandler.uninit();
        }
    }

    /**
     * @return The current mouse-interation. Can be null.
     * @see de.jbo.jbogx2d.ui.interaction.MouseInteractionHandler#getMouseInteraction()
     */
    public IMouseInteraction getMouseInteraction() {
        return mouseInteractionHandler.getMouseInteraction();
    }

    /**
     * @param mouseInteract
     *            The mouse-interaction to be set. Can be null.
     * @see de.jbo.jbogx2d.ui.interaction.MouseInteractionHandler#setMouseInteraction(de.jbo.jbogx2d.ui.interaction.IMouseInteraction)
     */
    public void setMouseInteraction(IMouseInteraction mouseInteract) {
        mouseInteractionHandler.setMouseInteraction(mouseInteract);
    }

    /**
     * Updates this instance in case that view-attributes have been changed
     * (e.g. background-color of the drawing).
     */
    public void updateAttributes() {
        if (isInitialized()) {
            viewBuffer.updateAttributes(drawing);
            viewXor.updateAttributes(drawing);
            isBufferNeedRefresh = true;
        }
    }

    /**
     * @return True if anti-aliasing is activated.
     */
    public boolean isAntiAliasing() {
        return viewBuffer.isAntiAliasing();
    }

    /**
     * @param set
     *            True to activate anti-aliasing.
     */
    public void setAntiAliasing(boolean set) {
        viewBuffer.setAntiAliasing(set);
        viewXor.setAntiAliasing(set);
        isBufferNeedRefresh = true;
    }

    /**
     * Activates or deactivates the view grid.
     * 
     * @param visible
     *            True to activate the grid, otherwise False.
     */
    public void setGridVisible(boolean visible) {
        if (isInitialized() && visible) {
            BoundsUserSpace b = new BoundsUserSpace();
            drawing.getUserSpaceBounds(b);
            viewDrawingGrid.setDrawingBounds(b, viewBuffer);
            viewDrawingGrid.updateVisibleUserSpace(viewBuffer);
        }
        viewDrawingGrid.setVisible(visible);
        isBufferNeedRefresh = true;
    }

    /**
     * Sets the grid-mode for this instance.
     * 
     * @param mode
     *            The mode to be set.
     * @see ViewDrawingGrid#MODE_LINES
     * @see ViewDrawingGrid#MODE_POINTS
     */
    public void setGridMode(int mode) {
        viewDrawingGrid.setMode(mode);
        isBufferNeedRefresh = true;
    }

    /**
     * Sets the grid's spacing.
     * 
     * @param spacingX
     *            The spacing in x-direction.
     * @param spacingY
     *            The spacing in y-direction.
     */
    public void setGridSpacings(double spacingX, double spacingY) {
        viewDrawingGrid.setGridSpacings(spacingX, spacingY);
        isBufferNeedRefresh = true;
    }

    /**
     * @return The grid's spacing in x-direction.
     */
    public double getGridSpacingX() {
        return viewDrawingGrid.getSpacingX();
    }

    /**
     * @return The grid's spacing in y-direction.
     */
    public double getGridSpacingY() {
        return viewDrawingGrid.getSpacingY();
    }

    /**
     * The grid's line stroke.
     * 
     * @return The stroke currently set.
     */
    public short getGridLineStroke() {
        return viewDrawingGrid.getAttribsLine().getStroke();
    }

    /**
     * Sets the grid's line stroke.
     * 
     * @param stroke
     *            The line stroke to be set.
     */
    public void setGridLineStroke(short stroke) {
        viewDrawingGrid.getAttribsLine().setStroke(stroke);
        viewDrawingGrid.updateAttributes(viewBuffer);
        isBufferNeedRefresh = true;
    }

    /**
     * Sets the grid's color.
     * 
     * @param color
     *            The color-index to be set.
     */
    public void setGridColor(short color) {
        viewDrawingGrid.getAttribsLine().setColor(color);
        viewDrawingGrid.updateAttributes(viewBuffer);
        isBufferNeedRefresh = true;
    }

    /**
     * @return The grid's color-index.
     */
    public short getGridColor() {
        return viewDrawingGrid.getAttribsLine().getColor();
    }

    /**
     * Fires a zoom-start event to all registered listeners.
     * 
     * @param boundsBefore
     *            The bounds before the zoom-operation.
     * @param boundsAfter
     *            The bounds the zoom-operation is performed to.
     */
    private void fireZoomStartEvent(BoundsUserSpace boundsBefore, BoundsUserSpace boundsAfter) {
        for (IZoomListener listener : zoomListeners) {
            listener.onZoomStart(this, boundsBefore, boundsAfter);
        }
    }

    /**
     * Fires a zoom-end event to all registered listeners.
     * 
     * @param boundsBefore
     *            The bounds before the zoom-operation.
     * @param boundsAfter
     *            The bounds the zoom-operation is performed to.
     */
    private void fireZoomEndEvent(BoundsUserSpace boundsBefore, BoundsUserSpace boundsAfter) {
        for (IZoomListener listener : zoomListeners) {
            listener.onZoomEnd(this, boundsBefore, boundsAfter);
        }
    }

    /**
     * Fires a scroll-start event to all registered listeners.
     * 
     * @param boundsBefore
     *            The bounds before the scroll-operation.
     * @param boundsAfter
     *            The bounds the scroll-operation is performed to.
     */
    private void fireScrollStartEvent(BoundsUserSpace boundsBefore, BoundsUserSpace boundsAfter) {
        for (IScrollListener listener : scrollListeners) {
            listener.onScrollStart(this, boundsBefore, boundsAfter);
        }
    }

    /**
     * Fires a scroll-end event to all registered listeners.
     * 
     * @param boundsBefore
     *            The bounds before the scroll-operation.
     * @param boundsAfter
     *            The bounds the scroll-operation is performed to.
     */
    private void fireScrollEndEvent(BoundsUserSpace boundsBefore, BoundsUserSpace boundsAfter) {
        for (IScrollListener listener : scrollListeners) {
            listener.onScrollEnd(this, boundsBefore, boundsAfter);
        }
    }

    /**
     * Registers the given zoom-listener.
     * 
     * @param listener
     *            The listener to be added.
     */
    public synchronized void addZoomListener(IZoomListener listener) {
        if (!zoomListeners.contains(listener)) {
            zoomListeners.add(listener);
        }
    }

    /**
     * Registers the given scroll-listener.
     * 
     * @param listener
     *            The listener to be added.
     */
    public synchronized void addScrollListener(IScrollListener listener) {
        if (!scrollListeners.contains(listener)) {
            scrollListeners.add(listener);
        }
    }

    /**
     * Removes the given listener.
     * 
     * @param listener
     *            The listener to be removed.
     */
    public synchronized void removeZoomListener(IZoomListener listener) {
        zoomListeners.remove(listener);
    }

    /**
     * Removes the given listener.
     * 
     * @param listener
     *            The listener to be removed.
     */
    public synchronized void removeScrollListener(IScrollListener listener) {
        scrollListeners.remove(listener);
    }

    /**
     * Removes all zoom-listeners.
     */
    public synchronized void removeAllZoomListeners() {
        zoomListeners.clear();
    }

    /**
     * Removes all scroll-listeners.
     */
    public synchronized void removeAllScrollListeners() {
        scrollListeners.clear();
    }

    /**
     * @param bounds
     *            Output bounds.
     * @see de.jbo.jbogx2d.base.graphics.ViewContextScreen#getBounds(de.jbo.jbogx2d.base.geom.BoundsScreen)
     */
    public void getBounds(BoundsScreen bounds) {
        viewScreen.getBounds(bounds);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.drawing.IDrawingModifiedListener#onDrawingModified
     * (de.jbo.jbogx2d.base.drawing.Drawing,
     * de.jbo.jbogx2d.base.geom.BoundsUserSpace)
     */
    @Override
    public void onDrawingModified(Drawing source, BoundsUserSpace modifiedBounds) {
        if (isInitialized() && source.equals(drawing)) {
            if (modifiedDrawingBounds == null) {
                modifiedDrawingBounds = new BoundsUserSpace(modifiedBounds.x, modifiedBounds.y, modifiedBounds.width, modifiedBounds.height);
            } else {
                modifiedDrawingBounds.merge(modifiedBounds);
            }
        }

    }

}
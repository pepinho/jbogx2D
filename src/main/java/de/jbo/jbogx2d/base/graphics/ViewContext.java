//
// Copyright (c) 2004 by jbo - Josef Baro
//
// Project: jbogx2D
// File: ViewBase.java
// Created: 28.02.2004 - 15:02:23
//

package de.jbo.jbogx2d.base.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.HashMap;
import java.util.Map;

import de.jbo.jbogx2d.base.Jbogx2D;
import de.jbo.jbogx2d.base.attrib.AttribFill;
import de.jbo.jbogx2d.base.attrib.AttribFillType;
import de.jbo.jbogx2d.base.attrib.AttribLine;
import de.jbo.jbogx2d.base.attrib.AttribText;
import de.jbo.jbogx2d.base.drawing.Drawing;
import de.jbo.jbogx2d.base.geom.AffineTransformX;
import de.jbo.jbogx2d.base.geom.BoundsScreen;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointScreen;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements the abstract base-class of a view-instance. This class is in
 * charge of rendering elements of a drawing.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 28.02.2004: jbo created <br>
 */
public abstract class ViewContext implements ImageObserver {
    /** Indicates if contexts create volatile images or not. */
    private static boolean isCreateVolatileImage = false;

    /** Image-observer state OK. */
    protected static final int IMAGE_OBSERVER_STATE_OK = 0;

    /** Image-observer state ERROR. */
    protected static final int IMAGE_OBSERVER_STATE_ERROR = 1;

    /** Empty transformation. **/
    protected static final AffineTransform TRANSFORMATION_NONE = new AffineTransform();

    /** The graphics-context this views renders on. */
    private Graphics2D graphicsContext = null;

    /** The visible rectangle in user-space coordinates for this instance. */
    private final BoundsUserSpace userSpaceVisible = new BoundsUserSpace();

    /**
     * Matrix for transformation of user-space coordinates to
     * screen-coordinates.
     */
    private AffineTransformX matrixUserSpace2Screen = new AffineTransformX();

    /**
     * Matrix for transformation of screen-coordinates to user-space
     * coordinates.
     */
    private AffineTransformX matrixScreen2UserSpace = new AffineTransformX();

    /** State of an image-observer process during rendering of images. */
    private int imageObserverState = IMAGE_OBSERVER_STATE_OK;

    /** The background color for this view. */
    private Color backgroundColorSystem = Color.white;

    /** Map storing the renderingHints for the graphics context. */
    private final HashMap<RenderingHints.Key, Object> mapRenderingHints = new HashMap<>();

    /** Indicates if the graphics context was set from external source. */
    private boolean isExternalGraphicsSet = false;

    /**
     * Creates a new instance.
     */
    protected ViewContext() {
        super();
        mapRenderingHints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    }

    /**
     * Activates or deactivates anti-aliasing for rendering-operations.
     * 
     * @param set
     *            The value to be set.
     */
    public void setAntiAliasing(final boolean set) {
        if (set) {
            mapRenderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            mapRenderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        } else {
            mapRenderingHints.remove(RenderingHints.KEY_ANTIALIASING);
            mapRenderingHints.remove(RenderingHints.KEY_TEXT_ANTIALIASING);
        }
        dispose();
    }

    /**
     * Indicates if anti-aliasing is set for rendering-operations.
     * 
     * @return True or False.
     * @see #setAntiAliasing(boolean)
     */
    public boolean isAntiAliasing() {
        return (mapRenderingHints.get(RenderingHints.KEY_ANTIALIASING) != null);
    }

    /**
     * Updates the attributes of the current-instance based on the given
     * drawing.
     * 
     * @param d
     *            The drawing to update the attributes for.
     */
    public void updateAttributes(final Drawing d) {
        backgroundColorSystem = Jbogx2D.getAttributeHandler().getColor(d.getColorBackground());
    }

    /**
     * Sets the visible area in user-space coordinates for this instance.
     * 
     * @param visibleUserSpace
     *            The visible area for this instance.
     */
    public void setVisibleUserSpace(final BoundsUserSpace visibleUserSpace) {
        setVisibleUserSpace(visibleUserSpace, false);
    }

    /**
     * Sets the visible area in user-space coordinates for this instance.
     * 
     * @param visibleUserSpace
     *            The visible area for this instance.
     * @param sizeChanged
     *            True if the size of the view has changed, False otherwise.
     */
    public void setVisibleUserSpace(final BoundsUserSpace visibleUserSpace, final boolean sizeChanged) {
        userSpaceVisible.set(visibleUserSpace);
        updateUserSpaceCoordinates(sizeChanged);
    }

    /**
     * Returns the currently set visible user-space coordinates of this
     * instance.
     * 
     * @param visibleUserSpace
     *            Out-param returning the user space coordinates.
     */
    public void getVisibleUserSpace(final BoundsUserSpace visibleUserSpace) {
        visibleUserSpace.set(userSpaceVisible);
    }

    /**
     * Initializes the transformations for the coordinate calculations from view
     * to user-space of the drawing and vice versa. The calculation is based on
     * the current visible bounds and the referenced drawing's user-space
     * coordinate system.
     * 
     * @param sizeChanged
     *            True if size has changed, False otherwise.
     */
    public void updateUserSpaceCoordinates(final boolean sizeChanged) {
        BoundsUserSpace viewBoundsDouble = new BoundsUserSpace();
        BoundsScreen viewBounds = new BoundsScreen();

        getBounds(viewBounds);
        viewBoundsDouble.set(viewBounds.x, viewBounds.y, viewBounds.width, viewBounds.height);

        userSpaceVisible.matchRatio(viewBoundsDouble);

        matrixUserSpace2Screen.windowTransform(userSpaceVisible, viewBounds);
        matrixScreen2UserSpace.windowTransform(viewBounds, userSpaceVisible);
        dispose();
        resetGraphicsContext();
    }

    /**
     * This method creates the graphics-context used for render-operations.
     * 
     * @return The graphics-context for rendering-operations.
     */
    protected abstract Graphics2D createGraphicsContext();

    /**
     * Returns the currently set graphics context of this instance.
     * 
     * @return The currently used graphics context.
     */
    public Graphics2D getGraphicsContext() {
        if (graphicsContext == null) {
            graphicsContext = createGraphicsContext();
            if (graphicsContext != null) {
                setUserSpaceTransformEnabled(true);
                graphicsContext.setRenderingHints(mapRenderingHints);
            }
        }

        return graphicsContext;
    }

    /**
     * Returns the bounds in pixels for the current instance.
     * 
     * @param bounds
     *            The bounds of the current instance are returned in this
     *            out-param.
     */
    public abstract void getBounds(BoundsScreen bounds);

    /**
     * Calculates this view's bounds.
     * 
     * @param bounds
     *            Out-parameter receiving the calculated bounds.
     */
    public void getBounds(final BoundsUserSpace bounds) {
        BoundsScreen b = new BoundsScreen();

        getBounds(b);
        bounds.set(b.x, b.y, b.width, b.height);
    }

    /**
     * This method is called when information about an image which was
     * previously requested using an asynchronous interface becomes available.
     * Asynchronous interfaces are method calls such as getWidth(ImageObserver)
     * and drawImage(img, x, y, ImageObserver) which take an ImageObserver
     * object as an argument. Those methods register the caller as interested
     * either in information about the overall image itself (in the case of
     * getWidth(ImageObserver)) or about an output version of an image (in the
     * case of the drawImage(img, x, y, [w, h,] ImageObserver) call).
     * <p>
     * This method should return true if further updates are needed or false if
     * the required information has been acquired. The image which was being
     * tracked is passed in using the img argument. Various constants are
     * combined to form the infoflags argument which indicates what information
     * about the image is now available. The interpretation of the x, y, width,
     * and height arguments depends on the contents of the infoflags argument.
     * <p>
     * The <code>infoflags</code> argument should be the bitwise inclusive
     * <b>OR</b> of the following flags: <code>WIDTH</code>, <code>HEIGHT</code>, <code>PROPERTIES</code>, <code>SOMEBITS</code>, <code>FRAMEBITS</code>,
     * <code>ALLBITS</code>, <code>ERROR</code>, <code>ABORT</code>.
     * 
     * @param img
     *            the image being observed.
     * @param infoflags
     *            the bitwise inclusive OR of the following flags:
     *            <code>WIDTH</code>, <code>HEIGHT</code>,
     *            <code>PROPERTIES</code>, <code>SOMEBITS</code>,
     *            <code>FRAMEBITS</code>, <code>ALLBITS</code>,
     *            <code>ERROR</code>, <code>ABORT</code>.
     * @param x
     *            the <i>x</i> coordinate.
     * @param y
     *            the <i>y</i> coordinate.
     * @param width
     *            the width.
     * @param height
     *            the height.
     * @return <code>false</code> if the infoflags indicate that the image is
     *         completely loaded; <code>true</code> otherwise.
     * @see java.awt.image.ImageObserver#imageUpdate(java.awt.Image, int, int,
     *      int, int, int)
     */
    public boolean imageUpdate(final Image img, final int infoflags, final int x, final int y, final int width, final int height) {
        boolean ret = true;

        /* Wenn alle Bits gezeichnet wurden, gilt der Vorgang als beendet. */
        if ((infoflags & ImageObserver.ALLBITS) != 0) {
            imageObserverState = IMAGE_OBSERVER_STATE_OK;
            ret = false;
        } else if (((infoflags & ImageObserver.ERROR) != 0) || ((infoflags & ImageObserver.ABORT) != 0)) {
            imageObserverState = IMAGE_OBSERVER_STATE_ERROR;
            ret = false; // nicht l�nger informieren lassen
        }

        return ret;
    }

    /**
     * Indicates if the image-update currently running is finished or not.
     * 
     * @return True if finished, False otherwise.
     * @see ImageObserver
     */
    public boolean isImageUpdateFinished() {
        return (imageObserverState == IMAGE_OBSERVER_STATE_OK);
    }

    /**
     * Disposes this instance. The underlying graphics context is disposed
     * accordingly.
     */
    public synchronized void dispose() {
        if (graphicsContext != null) {
            graphicsContext.dispose();
            graphicsContext = null;
        }
    }

    /**
     * Flushes the source-view's pixel-content to this instance.
     * 
     * @param bounds
     *            The area to be flushed.
     * @param sourceView
     *            The source-view to flush the pixels from.
     */
    public synchronized void flushView(final BoundsScreen bounds, final ViewContext sourceView) {
        BoundsScreen b = new BoundsScreen();
        Graphics2D gc = getGraphicsContext();

        if (gc != null) {
            if (bounds == null) {
                getBounds(b);
            } else {
                b.set(bounds);
            }
            bitBlt(b.x, b.y, b.width, b.height, sourceView);
            sourceView.dispose();
        }
    }

    /**
     * Clears the contents of this view based on the given bounds. The contents
     * cleared by a rectangle filled with the current drawing's background
     * color.
     * 
     * @param bounds
     *            The bounds to be cleared.
     */
    public synchronized void clearView(final BoundsScreen bounds) {
        BoundsScreen b = new BoundsScreen();
        Graphics2D gc = getGraphicsContext();

        if (gc != null) {
            if (bounds == null) {
                getBounds(b);
            } else {
                b.set(bounds);
            }

            gc.setColor(backgroundColorSystem);
            setUserSpaceTransformEnabled(false);
            gc.fill(b);
            setUserSpaceTransformEnabled(true);
        }
    }

    /**
     * Clears the complete contents of this view. The contents cleared by a
     * rectangle filled with the current drawing's background color.
     */
    public void clearView() {
        clearView((BoundsScreen) null);
    }

    /**
     * Clears the contents of this view based on the given bounds. The contents
     * cleared by a rectangle filled with the current drawing's background
     * color.
     * 
     * @param bounds
     *            The bounds to be cleared.
     */
    public synchronized void clearView(final BoundsUserSpace bounds) {
        if (bounds == null) {
            clearView();
        } else {
            Graphics2D gc = getGraphicsContext();
            if (gc != null) {
                gc.setColor(backgroundColorSystem);
                gc.fill(bounds);
            }
        }
    }

    /**
     * Flushes the complete pixel content of the given source-view to this
     * instance.
     * 
     * @param sourceView
     *            The source-view to copy the pixel-content from.
     */
    public void flushView(final ViewContext sourceView) {
        flushView((BoundsScreen) null, sourceView);
    }

    /**
     * Flushes the source-view's pixel-content to this instance.
     * 
     * @param bounds
     *            The area to be flushed.
     * @param sourceView
     *            The source-view to flush the pixels from.
     */
    public synchronized void flushView(final BoundsUserSpace bounds, final ViewContext sourceView) {
        Graphics2D gc = getGraphicsContext();

        if (gc != null) {
            if (bounds == null) {
                flushView(sourceView);
            } else {
                BoundsScreen b = new BoundsScreen();
                transformUserSpaceToScreen(bounds, b);
                bitBlt(b.x, b.y, b.width, b.height, sourceView);
            }
        }
    }

    /**
     * Creates an image based on the given width and height.
     * 
     * @param width
     *            The width of the created image.
     * @param height
     *            The height of the created image.
     * @return The created image.
     */
    protected Image createImage(final int width, final int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);        
    }

    /**
     * Creates an image based on the given component.
     * 
     * @param imageProducer
     *            The component to create the image of.
     * @return The created image.
     */
    protected Image createImage(final Component imageProducer) {
        Dimension size = imageProducer.getSize();
        Image img = null;
        if (imageProducer.getGraphicsConfiguration() != null) {
            if (isCreateVolatileImage) {
                img = imageProducer.getGraphicsConfiguration().createCompatibleVolatileImage(size.width, size.height);
            } else {
                img = imageProducer.getGraphicsConfiguration().createCompatibleImage(size.width, size.height);
            }

        }
        return img;
    }

    /**
     * Returns the current rendering image.
     * 
     * @return The rendering image.
     */
    protected abstract Image getImage();

    /**
     * This method copies the graphic content of the given source-view to the
     * current view instance within the given area dimensions.
     * 
     * @param xDest
     *            The upper-left x-position where the image area is to be copied
     *            to.
     * @param yDest
     *            The upper-left y-position where the image area is to be copied
     *            to.
     * @param wDest
     *            The width of the image area to be copied.
     * @param hDest
     *            The width of the image area to be copied.
     * @param src
     *            The source-view to copy the image from.
     * @param xSrc
     *            The upper-left x-position of the area on the source view.
     * @param ySrc
     *            The upper-left y-position of the area on the source view.
     */
    private void bitBlt(final int xDest, final int yDest, final int wDest, final int hDest, final ViewContext src) {
        Image img = src.getImage();

        if (img != null) {
            setUserSpaceTransformEnabled(false);
            graphicsContext.drawImage(img, xDest, yDest, xDest + wDest, yDest + hDest, xDest, yDest, xDest + wDest, yDest + hDest, null);
            setUserSpaceTransformEnabled(true);
            graphicsContext.dispose();
            graphicsContext = null;
        }
    }

    /**
     * Resets the graphics-context of this instance.
     */
    public void resetGraphicsContext() {
        dispose();
        graphicsContext = createGraphicsContext();
        isExternalGraphicsSet = false;
        setUserSpaceTransformEnabled(true);
    }

    /**
     * Renders the given shape based on the given attributes.
     * 
     * @param shape
     *            The shape to be rendered.
     * @param attribLine
     *            The line-attributes to be used.
     */
    public synchronized void draw(final Shape shape, final AttribLine attribLine) {
        Graphics2D gc = getGraphicsContext();

        if (gc != null) {
            gc.setColor(attribLine.getSystemColor());
            gc.setStroke(attribLine.getSystemStroke());
            gc.draw(shape);
        }
    }

    /**
     * Renders the given shape based on the given attributes.
     * 
     * @param shape
     *            The shape to be rendered.
     * @param attribLine
     *            The line-attributes to be used.
     * @param attribFill
     *            The fill-attributes to be used.
     */
    public synchronized void draw(final Shape shape, final AttribLine attribLine, final AttribFill attribFill) {
        Graphics2D gc = getGraphicsContext();

        if (gc != null) {
            if (!attribFill.getFillType().equals(AttribFillType.TYPE_HOLLOW)) {
                gc.setColor(attribFill.getSystemColorForeground());

                if (attribFill.getFillType().equals(AttribFillType.TYPE_TEXTURE)) {
                    gc.setPaint(attribFill.getSystemTexture());
                } else if (attribFill.getFillType().equals(AttribFillType.TYPE_GRADIENT)) {
                    gc.setPaint(attribFill.getSystemGradient());
                }
                gc.fill(shape);
            }
            gc.setColor(attribLine.getSystemColor());
            gc.setStroke(attribLine.getSystemStroke());
            gc.draw(shape);
        }
    }

    /**
     * Renders the given text.
     * 
     * @param basePoint
     *            The base position to render the text to.
     * @param text
     *            The text-lines to be rendered.
     * @param attribText
     *            The text-attributes to be used.
     * @param transformation
     *            The transformation to be used.
     */
    public synchronized void drawText(final PointUserSpace basePoint, final String[] text, final AttribText attribText, final AffineTransformX transformation) {
        Graphics2D gc = getGraphicsContext();
        PointUserSpace p = new PointUserSpace();
        String subString = null;

        try {
            if (gc != null) {
                double fontSize = attribText.getSystemHeight();
                double lineFeed = attribText.getLineFeed();

                transformation.inverseTransform(basePoint, p);

                gc.transform(transformation);
                gc.setColor(attribText.getSystemColor());

                for (String element : text) {
                    subString = element;
                    gc.drawString(new AttributedString(subString, attribText.getSystemAttributes()).getIterator(), (float) p.x, (float) p.y);
                    p.y += (fontSize * lineFeed);
                }
            }
        } catch (NoninvertibleTransformException e) {
            Jbogx2D.getErrorHandler().handleError(e, false, false);
        } finally {
            setUserSpaceTransformEnabled(true);
        }
    }

    /**
     * Transforms the given coordinates from screen- to user-space.
     * 
     * @param screen
     *            Screen-coordinates to be transformed.
     * @param userSpace
     *            Out-parameter to transform to.
     */
    public void transformScreenToUserSpace(final PointScreen screen, final PointUserSpace userSpace) {
        matrixScreen2UserSpace.transform(screen, userSpace);
    }

    /**
     * Transforms the given bounds from screen- to user-space.
     * 
     * @param screen
     *            Screen-bounds to be transformed.
     * @param userSpace
     *            Out-parameter to transform to.
     */
    public void transformScreenToUserSpace(final BoundsScreen screen, final BoundsUserSpace userSpace) {
        PointScreen screenPoint = new PointScreen(screen.x, screen.y);
        PointUserSpace userSpacePoint = new PointUserSpace();
        matrixScreen2UserSpace.transform(screenPoint, userSpacePoint);
        userSpace.x = userSpacePoint.x;
        userSpace.y = userSpacePoint.y;

        screenPoint.x = screen.x + screen.width;
        screenPoint.y = screen.y + screen.height;
        matrixScreen2UserSpace.transform(screenPoint, userSpacePoint);
        userSpace.width = userSpacePoint.x - userSpace.x;
        userSpace.height = userSpacePoint.y - userSpace.y;
    }

    /**
     * Transforms the given bounds from user- to screen-space.
     * 
     * @param userSpace
     *            The coordinates to be transformed.
     * @param screen
     *            Out-parameter to transform to.
     */
    public void transformUserSpaceToScreen(final BoundsUserSpace userSpace, final BoundsScreen screen) {
        PointScreen screenPoint = new PointScreen(0, 0);
        PointUserSpace userSpacePoint = new PointUserSpace(userSpace.x, userSpace.y);
        matrixUserSpace2Screen.transform(userSpacePoint, screenPoint);
        screen.x = screenPoint.x;
        screen.y = screenPoint.y;

        userSpacePoint.x = userSpace.x + userSpace.width;
        userSpacePoint.y = userSpace.y + userSpace.height;
        matrixUserSpace2Screen.transform(userSpacePoint, screenPoint);
        screen.width = screenPoint.x - screen.x;
        screen.height = screenPoint.y - screen.y;
    }

    /**
     * Transforms the given coordinates from user- to screen-space.
     * 
     * @param userSpace
     *            The coordinates to be transformed.
     * @param screen
     *            Out-parameter to transform to.
     */
    public void transformUserSpaceToScreen(final PointUserSpace userSpace, final PointScreen screen) {
        matrixUserSpace2Screen.transform(userSpace, screen);
    }

    /***
     * Enables or disables user-space transformation e.g. during rendering
     * operations.
     * 
     * @param enable
     *            True to enable or False to disable transformation.
     */
    public void setUserSpaceTransformEnabled(final boolean enable) {
        if (graphicsContext == null) {
            graphicsContext = createGraphicsContext();
        }
        if (graphicsContext != null) {
            if (enable) {
                graphicsContext.setTransform(matrixUserSpace2Screen);
            } else {
                AffineTransform transform = new AffineTransform(TRANSFORMATION_NONE);
                if (isExternalGraphicsSet) {
                    transform.concatenate(graphicsContext.getTransform());
                }
                graphicsContext.setTransform(transform);
            }
        }
    }

    /**
     * @return the backgroundColorSystem
     */
    public Color getBackgroundColorSystem() {
        return backgroundColorSystem;
    }

    /**
     * Activates or deactivates the XOR handling.
     * 
     * @param set
     *            Value to be set.
     */
    public void setXOR(boolean set) {
        if (graphicsContext == null) {
            graphicsContext = createGraphicsContext();
        }
        if (graphicsContext != null) {
            if (set) {
                graphicsContext.setXORMode(backgroundColorSystem);
            } else {
                graphicsContext.setXORMode(graphicsContext.getColor());
            }
        }
    }

    /**
     * @return the isCreateVolatileImage
     */
    public static boolean isCreateVolatileImage() {
        return isCreateVolatileImage;
    }

    /**
     * @param value
     *            the isCreateVolatileImage to set
     */
    public static void setCreateVolatileImage(final boolean value) {
        ViewContext.isCreateVolatileImage = value;
    }

    /**
     * Sets a new external graphics-context to be used for rendering.
     * 
     * @param gc
     *            The rendering context to be set.
     * @param isExternal
     *            True if external context (not created within a view-context).
     */
    public void setGraphicsContext(Graphics2D gc, boolean isExternal) {
        graphicsContext = gc;
        isExternalGraphicsSet = isExternal;
    }

    /**
     * @return the mapRenderingHints
     */
    public final Map<RenderingHints.Key, Object> getRenderingHints() {
        return mapRenderingHints;
    }

    /**
     * @return the matrixUserSpace2Screen
     */
    protected final AffineTransformX getMatrixUserSpace2Screen() {
        return matrixUserSpace2Screen;
    }

}
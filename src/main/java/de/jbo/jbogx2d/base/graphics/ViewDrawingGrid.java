//
// Copyright (c) 2007 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: DrawingGrid.java
// Created: 02.06.2007 - 23:30:37
//
package de.jbo.jbogx2d.base.graphics;

import java.awt.Graphics2D;

import de.jbo.jbogx2d.base.attrib.AttribLine;
import de.jbo.jbogx2d.base.geom.BoundsScreen;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointScreen;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements the background grid for a drawing instance. It can be switched
 * between 2 modes: <br>
 * - point-mode <br>
 * - line-mode <br>
 * 
 * @author Josef Baro (jbo)
 * @version 02.06.2007 jbo - created
 */
public class ViewDrawingGrid {
    /**
     * Default spacing in y-direction.
     */
    private static final double DEFAULT_SPACING_Y = 10.0;

    /**
     * Default spacing in x-direction.
     */
    private static final double DEFAULT_SPACING_X = 10.0;

    /**
     * The default pen-width.
     */
    private static final float DEFAULT_PEN_WIDTH = 0.1f;

    /**
     * Minimum screen-spacing in pixels.
     */
    private static final int MINIMUM_SCREEN_SPACING = 20;

    /**
     * Line mode.
     */
    public static final int MODE_LINES = 0;

    /**
     * Points mode.
     */
    public static final int MODE_POINTS = 1;

    /**
     * The mode currently set.
     * 
     * @see #MODE_LINES
     * @see #MODE_POINTS
     */
    private int mode = MODE_LINES;

    /**
     * The grid spacing currently set.
     */
    private final PointUserSpace spacingUserSpace = new PointUserSpace(DEFAULT_SPACING_X, DEFAULT_SPACING_Y);

    /**
     * The grid spacing in screen pixels.
     */
    private final PointScreen spacingScreen = new PointScreen();

    /**
     * Line attribute set.
     */
    private final AttribLine attribsLine = new AttribLine();

    /**
     * Indicates if the grid is currently visible.
     */
    private boolean isVisible = true;

    /**
     * The current bounds of the drawing. The grid is drawn withing those
     * bounds.
     */
    private final BoundsUserSpace drawingBounds = new BoundsUserSpace();

    /**
     * Start point in user-space coordinates.
     */
    private final PointUserSpace startUserSpace = new PointUserSpace();

    /**
     * Stop point in user-space coordinates.
     */
    private final PointUserSpace stopUserSpace = new PointUserSpace();

    /**
     * Start point in screen coordinates.
     */
    private final PointScreen startScreen = new PointScreen();

    /**
     * Stop point in screen coordinates.
     */
    private final PointScreen stopScreen = new PointScreen();

    /**
     * Creates a new grid instance.
     */
    public ViewDrawingGrid() {
        attribsLine.setColor((short) 1);
        attribsLine.setStroke((short) 2);
        attribsLine.setWidth(DEFAULT_PEN_WIDTH);
        attribsLine.update(null);
    }

    /**
     * Sets the bounds of the grid.
     * 
     * @param b
     *            The bounds to be set.
     * @param view
     *            The view to be handled.
     */
    public final void setDrawingBounds(final BoundsUserSpace b, final ViewContext view) {
        drawingBounds.set(b);
        initPoints(view);
    }

    /**
     * Triggered when the visible-user-space of the given view was changed.
     * 
     * @param view
     *            The view to be handled.
     */
    public final void updateVisibleUserSpace(final ViewContext view) {
        initPoints(view);
    }

    /**
     * Reinitializes the grid-points for the given view.
     * 
     * @param view
     *            The view to be handled.
     */
    private void initPoints(final ViewContext view) {
        BoundsUserSpace viewBounds = new BoundsUserSpace();
        view.getVisibleUserSpace(viewBounds);
        startUserSpace.x = viewBounds.x - ((viewBounds.x - drawingBounds.x) % spacingUserSpace.x);
        startUserSpace.y = viewBounds.y - ((viewBounds.y - drawingBounds.y) % spacingUserSpace.y);
        stopUserSpace.x = viewBounds.x + viewBounds.width;
        stopUserSpace.y = viewBounds.y + viewBounds.height;

        /*
         * Pr�fung �ber die drawingBounds, fall weniger als 100% Zoomlevel
         * sichtbar sind, sollen die drawingBounds die Grenze f�r das Gitter
         * sein
         */
        startUserSpace.x = Math.max(drawingBounds.x, startUserSpace.x);
        startUserSpace.y = Math.max(drawingBounds.y, startUserSpace.y);
        stopUserSpace.x = Math.min(drawingBounds.x + drawingBounds.width, stopUserSpace.x);
        stopUserSpace.y = Math.min(drawingBounds.y + drawingBounds.height, stopUserSpace.y);

        view.transformUserSpaceToScreen(startUserSpace, startScreen);
        view.transformUserSpaceToScreen(stopUserSpace, stopScreen);

        BoundsUserSpace bus = new BoundsUserSpace(0.0, 0.0, spacingUserSpace.x, spacingUserSpace.y);
        BoundsScreen bscr = new BoundsScreen();

        view.transformUserSpaceToScreen(bus, bscr);
        spacingScreen.x = bscr.width;
        spacingScreen.y = bscr.height;
    }

    /**
     * Paints this grid instance to the given view context.
     * 
     * @param view
     *            The context to paint to.
     */
    public final void paint(final ViewContext view) {

        /*
         * Ausserhalb der Zeichnungsbreite oder -h�he? Dann k�nnen wir
         * aussteigen und m�ssen nichts zeichnen...
         */
        if ((startUserSpace.x > stopUserSpace.x) || (startUserSpace.y > stopUserSpace.y)) {
            return;
        }

        Graphics2D gc = view.getGraphicsContext();

        gc.setTransform(ViewContext.TRANSFORMATION_NONE);
        int x = startScreen.x;
        int y = startScreen.y;
        gc.setColor(attribsLine.getSystemColor());

        /*
         * Wir pr�fen, ob der Pixelabstand des Gitters unter ein bestimmtes
         * Limit f�llt. Ist das der Fall, zeichnen wir nur noch jeden n-ten
         * Punkt
         */
        int correctionFactor = checkForMinimumScreenSpacing(spacingScreen.x, spacingScreen.y);

        if (mode == MODE_LINES) {
            gc.setStroke(attribsLine.getSystemStroke());
            /*
             * horizontale Linien
             */
            while (y <= stopScreen.y) {
                gc.drawLine(startScreen.x, y, stopScreen.x, y);
                y += spacingScreen.y * correctionFactor;
            }
            /*
             * vertikale Linien
             */
            while (x <= stopScreen.x) {
                gc.drawLine(x, startScreen.y, x, stopScreen.y);
                x += spacingScreen.x * correctionFactor;
            }
        } else if (mode == MODE_POINTS) {
            while (x <= stopScreen.x) {
                while (y <= stopScreen.y) {
                    gc.drawLine(x, y, x, y);
                    y += spacingScreen.y * correctionFactor;
                }
                x += spacingScreen.x * correctionFactor;
                y = startScreen.y;
            }

        }
        gc.setTransform(view.getMatrixUserSpace2Screen());
    }

    /**
     * Checks if minimum screen-spacing is reached. We don't want to display all
     * grid-points if the distance is to small in pixels.
     * 
     * @param spacingX
     *            The spacing in pixel x-direction.
     * @param spacingY
     *            The spacing in pixel y-direction.
     * @return The factor for point display optimization.
     */
    public static int checkForMinimumScreenSpacing(int spacingX, int spacingY) {
        int factor = 1;
        int value = 0;
        if (spacingX < MINIMUM_SCREEN_SPACING) {
            value = spacingX;
        } else if (spacingY < MINIMUM_SCREEN_SPACING) {
            value = spacingY;
        }

        if (value > 0) {
            while (value < MINIMUM_SCREEN_SPACING) {
                factor *= 2;
                value *= factor;
            }
        }
        return factor;
    }

    /**
     * Updates the grid's attributes for the given view.
     * 
     * @param view
     *            The view to be handled.
     */
    public final void updateAttributes(final ViewContext view) {
        attribsLine.update(null);
        initPoints(view);
    }

    /**
     * @return the attribsLine
     */
    public final AttribLine getAttribsLine() {
        return attribsLine;
    }

    /**
     * @return the isVisible
     */
    public final boolean isVisible() {
        return isVisible;
    }

    /**
     * @param visible
     *            the isVisible to set
     */
    public final void setVisible(final boolean visible) {
        this.isVisible = visible;
    }

    /**
     * @return the mode
     */
    public final int getMode() {
        return mode;
    }

    /**
     * @param myMode
     *            the mode to set
     */
    public final void setMode(final int myMode) {
        this.mode = myMode;
    }

    /**
     * Sets the grid-spacings.
     * 
     * @param spacingX
     *            Spacing in x-direction.
     * @param spacingY
     *            Spacing in y-direction.
     */
    public final void setGridSpacings(final double spacingX, final double spacingY) {
        spacingUserSpace.x = spacingX;
        spacingUserSpace.y = spacingY;
    }

    /**
     * @return the spacingX
     */
    public final double getSpacingX() {
        return spacingUserSpace.x;
    }

    /**
     * @return the spacingY
     */
    public final double getSpacingY() {
        return spacingUserSpace.y;
    }
}

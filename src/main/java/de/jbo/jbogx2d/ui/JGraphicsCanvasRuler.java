//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: JGraphicsCanvasRuler.java
// Created: 07.02.2010 - 18:46:18
//
package de.jbo.jbogx2d.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;

import de.jbo.jbogx2d.base.Jbogx2D;
import de.jbo.jbogx2d.base.geom.BoundsScreen;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.GeometryToolbox;
import de.jbo.jbogx2d.base.graphics.IScrollListener;
import de.jbo.jbogx2d.base.graphics.IZoomListener;
import de.jbo.jbogx2d.base.graphics.ViewDrawingGrid;
import de.jbo.jbogx2d.base.graphics.ViewHandler;
import de.jbo.jbogx2d.base.util.lang.WrapperDouble;
import de.jbo.jbogx2d.base.util.math.MathUtils;

/**
 * Implements a ruler panel for the graphics canvas.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 07.02.2010 jbo - created <br>
 */
final class JGraphicsCanvasRuler extends JPanel implements IZoomListener, IScrollListener {

    /**
     * Serial id.
     */
    private static final long serialVersionUID = 1L;

    /** Spacing for texts. */
    private static final int TEXT_WIDTH_SPACING = 10;

    /**
     * My parent canvas.
     */
    private JGraphicsCanvas myCanvas = null;

    /**
     * The ruler mode.
     */
    private CanvasRulerMode rulerMode = null;

    /**
     * Scale tick size in pixels.
     */
    private int scaleTickSize = 10;

    /** Indicates if 1st update was done or not. */
    private boolean isFirstUpdate = true;

    /** Current visible bounds of the view. */
    private final BoundsUserSpace visibleBounds = new BoundsUserSpace();

    /** Ruler units in x-direction. */
    private final LinkedList<RulerUnit> rulerUnitsX = new LinkedList<RulerUnit>();

    /** Ruler units in y-direction. */
    private final LinkedList<RulerUnit> rulerUnitsY = new LinkedList<RulerUnit>();

    /**
     * Creates a new instance.
     * 
     * @param canvas
     *            The parent canvas.
     * @param mode
     *            The ruler-mode to be set.
     */
    JGraphicsCanvasRuler(JGraphicsCanvas canvas, CanvasRulerMode mode) {
        super();
        myCanvas = canvas;
        rulerMode = mode;
        setOpaque(true);
        setBackground(Color.white);
        setForeground(Color.black);
        setBorder(BorderFactory.createLineBorder(getForeground()));
        setFont(UIManager.getFont("Label.font"));

        canvas.addZoomListener(this);
        canvas.addScrollListener(this);
        updateValues();
    }

    /**
     * @return the scaleTickSize
     */
    public int getScaleTickSize() {
        return scaleTickSize;
    }

    /**
     * @param scaleTickSize
     *            the scaleTickSize to set
     */
    public void setScaleTickSize(int scaleTickSize) {
        this.scaleTickSize = scaleTickSize;
    }

    /**
     * Updates the ruler-values based on the parent canvas's grid and visible
     * area.
     * 
     * @return True if valid update, False otherwise.
     */
    private boolean updateValues() {
        boolean ret = false;
        int gridSpacingX = 0;
        if (myCanvas.isInitialized()) {
            BoundsScreen viewBounds = new BoundsScreen();
            BoundsUserSpace viewBoundsUser = new BoundsUserSpace();
            BoundsUserSpace drawingBounds = new BoundsUserSpace();

            double gridUserSpaceX = myCanvas.getGridSpacingX();
            double gridUserSpaceY = myCanvas.getGridSpacingY();

            myCanvas.getDrawing().getUserSpaceBounds(drawingBounds);
            myCanvas.getVisibleUserSpace(visibleBounds);
            myCanvas.getBounds(viewBounds);
            viewBoundsUser.set(viewBounds.x, viewBounds.y, viewBounds.width, viewBounds.height);

            visibleBounds.matchRatio(viewBoundsUser);

            if (viewBounds.width > 0) {

                /*
                 * Calculate grid-spacings in pixels.
                 */
                viewBoundsUser.set(0, 0, gridUserSpaceX, gridUserSpaceY);
                myCanvas.transformUserSpaceToScreen(viewBoundsUser, viewBounds);
                gridSpacingX = viewBounds.width;
                int gridSpacingY = viewBounds.height;
                if (gridSpacingX > 0) {
                    if (rulerMode.equals(CanvasRulerMode.X_AXIS)) {
                        updateValuesX(gridSpacingX, gridSpacingY, gridUserSpaceX, visibleBounds, drawingBounds);
                    } else {
                        updateValuesY(gridSpacingX, gridSpacingY, gridUserSpaceY, visibleBounds, drawingBounds);
                    }
                }

            }
            ret = (gridSpacingX > 0);
        }
        return ret;
    }

    /**
     * Updates the y-values.
     * 
     * @param gridSpacingPixelX
     *            Pixel spacing.
     * @param gridSpacingPixelY
     *            Pixel spacing.
     * @param gridSpacingUserSpace
     *            User spacing.
     * @param visibleBounds
     *            Visible bounds.
     * @param drawingBounds
     *            Drawing bounds.
     */
    private void updateValuesY(int gridSpacingPixelX, int gridSpacingPixelY, double gridSpacingUserSpace, BoundsUserSpace visibleBounds, BoundsUserSpace drawingBounds) {
        Dimension size = getSize();
        if (size.height > 0) {
            Insets insets = getInsets();
            WrapperDouble firstUnitY = new WrapperDouble();
            int firstTickY = calculateFirstGridTickY(gridSpacingUserSpace, visibleBounds, drawingBounds, firstUnitY);

            /*
             * Calculate rounding scales
             */
            BigDecimal decimal = new BigDecimal(gridSpacingUserSpace);
            int scaleGrid = decimal.scale();
            decimal = new BigDecimal(drawingBounds.x);
            int scaleOrigin = decimal.scale();
            int roundingScaleY = Math.max(scaleGrid, scaleOrigin);

            int correctionFactor = ViewDrawingGrid.checkForMinimumScreenSpacing(gridSpacingPixelX, gridSpacingPixelY);

            int tick = firstTickY;
            double unit = firstUnitY.value;
            String widestUnit = "";

            rulerUnitsY.clear();

            Graphics2D g2D = (Graphics2D) getGraphics();
            if (g2D != null) {
                while (tick <= size.height) {
                    RulerUnit rulerUnit = new RulerUnit();
                    int x1 = size.width - insets.right - scaleTickSize;
                    int x2 = size.width - insets.right;

                    rulerUnit.positionTickX1 = x1;
                    rulerUnit.positionTickX2 = x2;
                    rulerUnit.positionTickY1 = tick;
                    rulerUnit.positionTickY2 = tick;

                    rulerUnit.unitText = MathUtils.roundToString(unit, roundingScaleY);

                    rulerUnit.positionUnitX = x1 - 1;
                    rulerUnit.positionUnitY = tick;

                    rulerUnitsY.add(rulerUnit);

                    if (rulerUnit.unitText.length() > widestUnit.length()) {
                        widestUnit = rulerUnit.unitText;
                    }

                    tick += gridSpacingPixelY * correctionFactor;
                    unit += gridSpacingUserSpace * correctionFactor;
                    if (gridSpacingPixelY == 0 || correctionFactor == 0) {
                        return;
                    }
                }
                /*
                 * Check widest unit text. Is it too wide to be displayed
                 * without overlapping its neighbour, we skip each 2nd unit-text
                 * to avoid overlapping...
                 */
                int skips = 0;

                Rectangle2D textBounds = getFont().getStringBounds(widestUnit, g2D.getFontRenderContext());
                double maxWidth = textBounds.getWidth() + TEXT_WIDTH_SPACING;
                skips = (int) maxWidth / (gridSpacingPixelY * correctionFactor);
                if (skips > 0) {
                    skipTexts(skips, rulerUnitsY);
                }
            }
        }
    }

    /**
     * Updates the x-values.
     * 
     * @param gridSpacingPixelX
     *            Pixel spacing.
     * @param gridSpacingPixelY
     *            Pixel spacing.
     * @param gridSpacingUserSpace
     *            User spacing.
     * @param visibleBounds
     *            Visible bounds.
     * @param drawingBounds
     *            Drawing bounds.
     */
    private void updateValuesX(int gridSpacingPixelX, int gridSpacingPixelY, double gridSpacingUserSpace, BoundsUserSpace visibleBounds, BoundsUserSpace drawingBounds) {
        Dimension size = getSize();
        if (size.width > 0) {
            Insets insets = getInsets();
            WrapperDouble firstUnitX = new WrapperDouble();
            int firstTickX = calculateFirstGridTickX(gridSpacingUserSpace, visibleBounds, drawingBounds, firstUnitX);

            /*
             * Calculate rounding scales
             */
            BigDecimal decimal = new BigDecimal(gridSpacingUserSpace);
            int scaleGrid = decimal.scale();
            decimal = new BigDecimal(drawingBounds.x);
            int scaleOrigin = decimal.scale();
            int roundingScaleX = Math.max(scaleGrid, scaleOrigin);

            int correctionFactor = ViewDrawingGrid.checkForMinimumScreenSpacing(gridSpacingPixelX, gridSpacingPixelY);

            int tick = firstTickX;
            double unit = firstUnitX.value;
            String widestUnit = "";

            rulerUnitsX.clear();

            Graphics2D g2D = (Graphics2D) getGraphics();
            if (g2D != null) {

                while (tick <= size.width) {
                    RulerUnit rulerUnit = new RulerUnit();
                    int y1 = size.height - insets.bottom - scaleTickSize;
                    int y2 = size.height - insets.bottom;

                    rulerUnit.positionTickX1 = tick;
                    rulerUnit.positionTickX2 = tick;
                    rulerUnit.positionTickY1 = y1;
                    rulerUnit.positionTickY2 = y2;

                    String text = MathUtils.roundToString(unit, roundingScaleX);
                    Rectangle2D textBounds = getFont().getStringBounds(text, g2D.getFontRenderContext());

                    rulerUnit.positionUnitX = tick - (int) (textBounds.getWidth() / 2.0);
                    rulerUnit.positionUnitY = y1 - 1;

                    rulerUnit.unitText = new String(text);

                    rulerUnitsX.add(rulerUnit);

                    if (rulerUnit.unitText.length() > widestUnit.length()) {
                        widestUnit = rulerUnit.unitText;
                    }

                    tick += gridSpacingPixelX * correctionFactor;
                    unit += gridSpacingUserSpace * correctionFactor;
                    if (gridSpacingPixelX == 0 || correctionFactor == 0) {
                        return;
                    }
                }
                /*
                 * Check widest unit text. Is it too wide to be displayed
                 * without overlapping its neighbour, we skip each 2nd unit-text
                 * to avoid overlapping...
                 */
                int skips = 0;

                Rectangle2D textBounds = getFont().getStringBounds(widestUnit, g2D.getFontRenderContext());
                double maxWidth = textBounds.getWidth() + TEXT_WIDTH_SPACING;
                skips = (int) maxWidth / (gridSpacingPixelX * correctionFactor);
                if (skips > 0) {
                    skipTexts(skips, rulerUnitsX);
                }
            }
        }
    }

    /**
     * Skips texts for the units.
     * 
     * @param skips
     *            Amount of skipped texts.
     * @param units
     *            The units to be handled.
     */
    private void skipTexts(int skips, LinkedList<RulerUnit> units) {
        int currentSkip = 0;
        Iterator<RulerUnit> it = units.iterator();
        while (it.hasNext()) {
            RulerUnit unit = it.next();
            if (currentSkip > 0) {
                unit.unitText = "";
                currentSkip--;
            } else {
                currentSkip = skips;
            }

        }
    }

    /**
     * Calculates the first scale tick in x-direction for rendering.
     * 
     * @param gridSpacingX
     *            The spacing in x-direction.
     * @param visibleBounds
     *            The current visible area.
     * @param drawingBounds
     *            The complete drawing area.
     * @param firstUnitX
     *            Out-param for the calculated first x-unit value.
     * @return The first tick in x-direction
     */
    private int calculateFirstGridTickX(double gridSpacingX, BoundsUserSpace visibleBounds, BoundsUserSpace drawingBounds, WrapperDouble firstUnitX) {
        int tick = 0;

        double diffX = visibleBounds.x - drawingBounds.x;
        double tickUserSpace = diffX % gridSpacingX;
        tickUserSpace = -tickUserSpace;

        BoundsUserSpace b = new BoundsUserSpace(0.0, 0.0, tickUserSpace, tickUserSpace);
        BoundsScreen s = new BoundsScreen();
        myCanvas.transformUserSpaceToScreen(b, s);
        tick = s.width;

        firstUnitX.value = visibleBounds.x % gridSpacingX;
        firstUnitX.value = visibleBounds.x - firstUnitX.value;

        return tick;
    }

    /**
     * Calculates the first scale tick in y-direction for rendering.
     * 
     * @param gridSpacingY
     *            The spacing in y-direction.
     * @param visibleBounds
     *            The current visible area.
     * @param drawingBounds
     *            The complete drawing area.
     * @param firstUnitY
     *            Out-param for the calculated first y-unit value.
     * @return The first tick in x-direction
     */
    private int calculateFirstGridTickY(double gridSpacingY, BoundsUserSpace visibleBounds, BoundsUserSpace drawingBounds, WrapperDouble firstUnitY) {
        int tick = 0;

        double diffY = visibleBounds.y - drawingBounds.y;
        double tickUserSpace = diffY % gridSpacingY;
        tickUserSpace = -tickUserSpace;

        BoundsUserSpace b = new BoundsUserSpace(0.0, 0.0, tickUserSpace, tickUserSpace);
        BoundsScreen s = new BoundsScreen();
        myCanvas.transformUserSpaceToScreen(b, s);
        tick = s.height;

        firstUnitY.value = visibleBounds.y % gridSpacingY;
        firstUnitY.value = visibleBounds.y - firstUnitY.value;

        return tick;
    }

    /*
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isFirstUpdate) {
            isFirstUpdate = !updateValues();
        }
        if (!isFirstUpdate) {

            if (rulerMode.equals(CanvasRulerMode.X_AXIS)) {
                for (RulerUnit unit : rulerUnitsX) {
                    g.drawLine(unit.positionTickX1, unit.positionTickY1, unit.positionTickX2, unit.positionTickY2);
                    g.drawString(unit.unitText, unit.positionUnitX, unit.positionUnitY);
                }
            } else {
                for (RulerUnit unit : rulerUnitsY) {
                    g.drawLine(unit.positionTickX1, unit.positionTickY1, unit.positionTickX2, unit.positionTickY2);
                    paintScaleTextY(g, unit.unitText, unit.positionUnitX, unit.positionUnitY);
                }
            }
        }
    }

    /**
     * Paints the unit-texts in y-direction.
     * 
     * @param g
     *            The graphics-context to render on.
     * @param value
     *            The unit-value to be rendered.
     * @param x
     *            The x-position.
     * @param y
     *            The y-position.
     */
    private void paintScaleTextY(Graphics g, String value, int x, int y) {
        Graphics2D g2D = (Graphics2D) g;

        Rectangle2D textBounds = getFont().getStringBounds(value, g2D.getFontRenderContext());
        AffineTransform rotation = new AffineTransform();
        rotation.rotate(-GeometryToolbox.PI1_2, x, y);
        g2D.transform(rotation);
        g2D.drawString(value, x - (int) (textBounds.getWidth() / 2.0), y);
        try {
            rotation.invert();
        } catch (NoninvertibleTransformException e) {
            Jbogx2D.getErrorHandler().handleError(e, false, true);
        }
        g2D.transform(rotation);

    }

    /*
     * @see javax.swing.JComponent#getPreferredSize()
     */
    @Override
    public Dimension getPreferredSize() {
        Dimension prefSize = super.getPreferredSize();
        Dimension size = getSize();
        GraphicsConfiguration gc = getGraphicsConfiguration();
        Graphics2D g = (Graphics2D) getGraphics();
        Font font = getFont();

        if (gc != null) {
            prefSize.setSize(size);

            Rectangle2D textBounds = font.getMaxCharBounds(g.getFontRenderContext());
            Insets insets = getInsets();
            if (rulerMode.equals(CanvasRulerMode.X_AXIS)) {
                prefSize.height = (int) textBounds.getHeight() + scaleTickSize + insets.bottom + insets.top;
            } else {
                prefSize.width = (int) textBounds.getWidth() + scaleTickSize + insets.left + insets.right;
            }
        }
        return prefSize;
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.graphics.IZoomListener#onZoomEnd(de.jbo.jbogx2d.base
     * .graphics.ViewHandler, de.jbo.jbogx2d.base.geom.BoundsUserSpace,
     * de.jbo.jbogx2d.base.geom.BoundsUserSpace)
     */
    @Override
    public void onZoomEnd(ViewHandler source, BoundsUserSpace boundsBefore, BoundsUserSpace boundsAfter) {
        updateValues();
        repaint();
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.graphics.IZoomListener#onZoomStart(de.jbo.jbogx2d
     * .base.graphics.ViewHandler, de.jbo.jbogx2d.base.geom.BoundsUserSpace,
     * de.jbo.jbogx2d.base.geom.BoundsUserSpace)
     */
    @Override
    public void onZoomStart(ViewHandler source, BoundsUserSpace boundsBefore, BoundsUserSpace boundsAfter) {
        // nothing to do

    }

    /*
     * @see
     * de.jbo.jbogx2d.base.graphics.IScrollListener#onScrollEnd(de.jbo.jbogx2d
     * .base.graphics.ViewHandler, de.jbo.jbogx2d.base.geom.BoundsUserSpace,
     * de.jbo.jbogx2d.base.geom.BoundsUserSpace)
     */
    @Override
    public void onScrollEnd(ViewHandler source, BoundsUserSpace boundsBefore, BoundsUserSpace boundsAfter) {
        updateValues();
        repaint();
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.graphics.IScrollListener#onScrollStart(de.jbo.jbogx2d
     * .base.graphics.ViewHandler, de.jbo.jbogx2d.base.geom.BoundsUserSpace,
     * de.jbo.jbogx2d.base.geom.BoundsUserSpace)
     */
    @Override
    public void onScrollStart(ViewHandler source, BoundsUserSpace boundsBefore, BoundsUserSpace boundsAfter) {
        // nothing to do

    }

    /**
     * Implement a single ruler-unit to be rendered.
     * 
     * @author Josef Baro (jbo) <br>
     * @version 13.02.2010 jbo - created <br>
     */
    private class RulerUnit {
        /** Tick position. */
        int positionTickX1 = 0;

        /** Tick position. */
        int positionTickY1 = 0;

        /** Tick position. */
        int positionTickX2 = 0;

        /** Tick position. */
        int positionTickY2 = 0;

        /** Unit position. */
        int positionUnitX = 0;

        /** Unit position. */
        int positionUnitY = 0;

        /** Unit text. */
        String unitText = null;
    }
}

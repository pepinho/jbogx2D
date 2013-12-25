//
// Copyright (c) 2004 by jbo - Josef Baro
//
// Project: jbogx2D
// File: JGraphicsPanel.java
// Created: 29.02.2004 - 17:30:30
//

package de.jbo.jbogx2d.test.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import de.jbo.jbogx2d.base.Jbogx2D;
import de.jbo.jbogx2d.base.attrib.AttribFill;
import de.jbo.jbogx2d.base.attrib.AttribFillType;
import de.jbo.jbogx2d.base.attrib.AttribLine;
import de.jbo.jbogx2d.base.drawing.Drawing;
import de.jbo.jbogx2d.base.drawing.DrawingLayer;
import de.jbo.jbogx2d.base.elements.ElemCircle;
import de.jbo.jbogx2d.base.elements.ElemPolygon;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointUserSpace;
import de.jbo.jbogx2d.base.graphics.ViewContext;
import de.jbo.jbogx2d.base.graphics.ViewDrawingGrid;
import de.jbo.jbogx2d.test.ui.interaction.MouseInteractionZoom;
import de.jbo.jbogx2d.ui.JGraphicsCanvas;

/**
 * @author Josef Baro (jbo) <br>
 * @version 1.0 29.02.2004: jbo created <br>
 */
public class JGraphicsPanel extends JPanel {

    /**
     * Serial id.
     */
    private static final long serialVersionUID = -1354359952030701699L;

    /** The drawing. */
    private final Drawing drawing = new Drawing();

    /** The view canvas for display. */
    private JGraphicsCanvas canvas = null;

    /**
     * Constructor.
     */
    public JGraphicsPanel() {
        super(new BorderLayout(0, 0));

        canvas = new JGraphicsCanvas();
        add(canvas, BorderLayout.CENTER);

        ViewContext.setCreateVolatileImage(false);

        Jbogx2D.getAttributeHandler().setFont((short) 1, "Arial");
        Jbogx2D.getAttributeHandler().setFont((short) 2, "Courier New");

        /*
         * The the background color of the drawing...
         */
        drawing.setColorBackground((short) 0);

        final double gridX = 10;
        final double gridY = 10;

        /*
         * The view is initialized with the drawing...
         */
        initElements();
        canvas.init(drawing);
        canvas.setGridMode(ViewDrawingGrid.MODE_POINTS);
        canvas.setGridSpacings(gridX, gridY);
        canvas.setGridVisible(true);

        canvas.setMouseInteraction(new MouseInteractionZoom());

    }

    /**
     * 
     */
    private void initElements() {
        /*
         * Create test-elements...
         */
        int width = 2;
        int spacing = 1;
        final float lineWidth = 0.1f;
        final double alpha = 0.5;
        AttribFill fill = new AttribFill();
        fill.setFillType(AttribFillType.TYPE_SOLID);
        fill.setColorBackground((short) 2);
        fill.setColorForeground((short) 3);
        AttribLine line = new AttribLine();
        line.setColor((short) 1);
        line.setWidth(lineWidth);
        DrawingLayer layer = drawing.getLayer(DrawingLayer.NAME_DEFAULT);
        layer.setFiltered(false);
        BoundsUserSpace bounds = new BoundsUserSpace();
        drawing.getUserSpaceBounds(bounds);
        for (int x = (int) bounds.x; x < bounds.width; x += width + spacing) {
            for (int y = (int) bounds.y; y < bounds.height; y += width + spacing) {
                ElemPolygon polygon = new ElemPolygon();
                polygon.setPointCount(4);
                polygon.setPoint(0, new PointUserSpace(x, y));
                polygon.setPoint(1, new PointUserSpace(x + width, y));
                polygon.setPoint(2, new PointUserSpace(x + width, y + width));
                polygon.setPoint(3, new PointUserSpace(x, y + width));
                polygon.getAttribFill().setColorBackground(fill.getColorBackground());
                polygon.getAttribFill().setColorForeground(fill.getColorForeground());
                polygon.getAttribFill().setFillType(fill.getFillType());
                polygon.getAttribLine().setColor(line.getColor());
                polygon.getAttribLine().setWidth(line.getWidth());
                polygon.updateAttributes();
                layer.addElemLast(polygon);
            }
        }

        ElemCircle circle = new ElemCircle(bounds.getCenterX(), bounds.getCenterY(), bounds.height / 2.0);
        circle.getAttribFill().setFillType(AttribFillType.TYPE_SOLID);
        circle.getAttribFill().setColorBackground((short) 4);
        circle.getAttribFill().setColorForeground((short) 4);
        circle.getAttribFill().setFillType(fill.getFillType());
        circle.getAttribFill().setAlpha(alpha);
        circle.getAttribLine().setColor(line.getColor());
        circle.getAttribLine().setWidth(line.getWidth());
        circle.updateAttributes();
        layer.addElemLast(circle);

    }

    /**
     * Zoom-in by fixed percentage.
     */
    public final void zoomPlus() {
        final double percent01 = 0.1;
        final double percent08 = 0.8;
        BoundsUserSpace visibleBounds = new BoundsUserSpace();
        canvas.getVisibleUserSpace(visibleBounds);
        visibleBounds.x += (visibleBounds.width * percent01);
        visibleBounds.y += (visibleBounds.height * percent01);
        visibleBounds.width *= percent08;
        visibleBounds.height *= percent08;
        canvas.setVisibleUserSpace(visibleBounds, false);
        canvas.repaint();
    }

    /**
     * Zoom-out by fixed percentage.
     */
    public final void zoomMinus() {
        final double percent01 = 0.1;
        final double percent12 = 1.2;
        BoundsUserSpace visibleBounds = new BoundsUserSpace();
        canvas.getVisibleUserSpace(visibleBounds);
        visibleBounds.x -= (visibleBounds.width * percent01);
        visibleBounds.y -= (visibleBounds.height * percent01);
        visibleBounds.width *= percent12;
        visibleBounds.height *= percent12;
        canvas.setVisibleUserSpace(visibleBounds, false);
        canvas.repaint();
    }

    /**
     * Zoom to 100%.
     */
    public final void zoomOriginal() {
        BoundsUserSpace b = new BoundsUserSpace();
        drawing.getUserSpaceBounds(b);
        canvas.setVisibleUserSpace(b, false);
        canvas.repaint();
    }

    /**
     * Activates or deactivates anti-aliasing of the view.
     * 
     * @param antiAliasing
     *            True or False.
     */
    public final void setAntiAliasing(final boolean antiAliasing) {
        canvas.setAntiAliasing(antiAliasing);
        canvas.repaint();
    }

}
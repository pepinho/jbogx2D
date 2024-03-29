//
// Copyright (c) 2007 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemHandle.java
// Created: 07.06.2007 - 17:35:25
//
package de.jbo.jbogx2d.base.elements.view;

import java.awt.Graphics2D;
import java.awt.Shape;

import de.jbo.jbogx2d.base.attrib.AttribLine;
import de.jbo.jbogx2d.base.elements.model.ElemModel;
import de.jbo.jbogx2d.base.geom.BoundsScreen;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointUserSpace;
import de.jbo.jbogx2d.base.graphics.ViewContext;

/**
 * Implements the abstract base class for element-handles.
 * 
 * @author Josef Baro (jbo)
 * @version 07.06.2007 jbo - created
 */
public abstract class ElemHandles {
    /** Point-array storing the handles's positions. */
    private PointUserSpace[] points = null;

    /** True if handles are visible, False otherwise. */
    private boolean isVisible = false;

    /** View being handled. */
    private ElemView elemView = null;

    /** Line attributes for this instance. */
    private AttribLine attribsLine = new AttribLine();

    /** True if rendering in Xor-mode, False otherwise. */
    private boolean isXor = true;

    /**
     * Creates a new instance.
     */
    protected ElemHandles() {
        super();
    }

    /**
     * Uninitializes this instance.
     */
    public void uninit() {
        elemView = null;
    }

    /**
     * Init this instance.
     * 
     * @param ev
     *            The view to be handled.
     */
    public void init(final ElemView ev) {
        elemView = ev;
    }

    /**
     * Updates the handles for the given model.
     * 
     * @param model
     *            The model to be handled.
     * @return The udpate handle-points.
     */
    protected abstract PointUserSpace[] performUpdate(ElemModel model);

    /**
     * Returns the id of the handle lying under the given point.
     * 
     * @param point
     *            The point (e.g. mouse-position) to look for a handle.
     * @param radius
     *            The radius to look for a handle in.
     * @return The handle id (0 to n) or -1 if no handle found under the given
     *         position.
     */
    public int getHandleId(final PointUserSpace point, final double radius) {
        int handleId = -1;
        double halfRadius = radius / 2;
        BoundsUserSpace radiusBounds = new BoundsUserSpace(point.x - halfRadius, point.y - halfRadius, radius, radius);

        if (points != null) {

            for (int i = 0; i < points.length; i++) {
                PointUserSpace p = points[i];
                if (radiusBounds.contains(p)) {
                    handleId = i;
                    break;
                }
            }
        }
        return handleId;
    }

    /**
     * @return the isVisible
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * @param set
     *            the isVisible to set
     */
    public void setVisible(final boolean set) {
        this.isVisible = set;
    }

    /**
     * Updates this instance. Needs to be called whenever the handled element
     * changes its geometry or the line-attributes have changed.
     */
    public void update() {
        ElemModel model = elemView.getModel();
        points = performUpdate(model);
        attribsLine.update(model);
    }

    /**
     * Renders this instance to the given view-context.
     * 
     * @param view
     *            The context to render to.
     */
    public void paint(final ViewContext view) {
        Graphics2D gc = view.getGraphicsContext();
        gc.setColor(attribsLine.getSystemColor());
        gc.setStroke(attribsLine.getSystemStroke());

        int pixelWidth = 5;
        BoundsScreen bscr = new BoundsScreen(0, 0, pixelWidth, pixelWidth);
        BoundsUserSpace bus = new BoundsUserSpace();
        view.transformScreenToUserSpace(bscr, bus);
        Shape shape = null;

        for (PointUserSpace handle : points) {
            shape = createHandleShape(handle, bus.width, bus.height);
            if (isXor) {
                gc.setXORMode(view.getBackgroundColorSystem());
            }
            gc.fill(shape);
            if (isXor) {
                gc.setXORMode(attribsLine.getSystemColor());
                gc.fill(shape);
                gc.setPaintMode();
            }
        }
    }

    /**
     * Creates the handles-shape for rendering.
     * 
     * @param handlePoint
     *            The point to create the shape for.
     * @param width
     *            The width for the shape.
     * @param height
     *            The height for the shape.
     * @return The shape created.
     */
    protected abstract Shape createHandleShape(PointUserSpace handlePoint, double width, double height);

    /**
     * @return the color
     */
    public short getColor() {
        return attribsLine.getColor();
    }

    /**
     * @param color
     *            the color to set
     */
    public void setColor(final short color) {
        attribsLine.setColor(color);
    }

    /**
     * @return the isXor
     */
    public boolean isXor() {
        return isXor;
    }

    /**
     * @param set
     *            the isXor to set
     */
    public void setXor(final boolean set) {
        this.isXor = set;
    }

    /**
     * @return the attribsLine
     */
    public final AttribLine getAttribsLine() {
        return attribsLine;
    }

    /**
     * @param set
     *            the attribsLine to set
     */
    public final void setAttribsLine(final AttribLine set) {
        this.attribsLine = set;
    }
}

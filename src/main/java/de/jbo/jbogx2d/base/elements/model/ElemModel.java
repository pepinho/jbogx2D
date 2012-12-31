//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemModelBoundsCache.java
// Created: 01.03.2004 - 00:14:54
//

package de.jbo.jbogx2d.base.elements.model;

import de.jbo.jbogx2d.base.attrib.AttribFill;
import de.jbo.jbogx2d.base.attrib.AttribLine;
import de.jbo.jbogx2d.base.attrib.AttribText;
import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.elements.view.ElemView;
import de.jbo.jbogx2d.base.geom.AffineTransformX;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * @author Josef Baro (jbo) <br>
 * @version 1.0 01.03.2004: jbo created <br>
 */
public abstract class ElemModel {
    /** Base element reference. */
    private ElemBase element = null;

    /** The name of the element. */
    private String name = null;

    /** The bounds of the element. */
    protected BoundsUserSpace boundsCache = new BoundsUserSpace();

    /** Indicates if the bounds are dirty and have to be re-calculated. */
    private boolean isBoundsDirty = true;

    /**
     * Creates a new instance.
     * 
     * @param elem
     *            The element to create the model for.
     */
    public ElemModel(final ElemBase elem) {
        super();
        this.element = elem;

    }

    /**
     * @param bounds
     *            Out-param that receives the calulcated bounds.
     * @see de.jbo.jbogx2d.base.elements.view.ElemModel#getBounds(de.jbo.jbogx2d.base.geom.BoundsUserSpace)
     */
    public void getBounds(final BoundsUserSpace bounds) {
        if (isBoundsDirty) {
            calculateBounds(boundsCache);
            isBoundsDirty = !boundsCache.isValid();
        }
        bounds.set(boundsCache);
    }

    /**
     * Calculates the bounds of the element.
     * 
     * @param bounds
     *            The out-param to calculate the bounds into.
     * @see #isBoundsDirty
     */
    protected abstract void calculateBounds(BoundsUserSpace bounds);

    /**
     * @return Indicates if the elements bounds-cache is dirty and thus has to
     *         be re-calculated.
     */
    public boolean isBoundsDirty() {
        return isBoundsDirty;
    }

    /**
     * @param b
     *            True to flag the bounds-cache as dirty, False otherwise.
     */
    public void setBoundsDirty(final boolean b) {
        isBoundsDirty = b;
        ElemView view = element.getView();
        if (view != null) {
            if (view.isHandlesVisible()) {
                view.updateHandles();
            }
        }
    }

    /**
     * Transforms the element model with the given transformation.
     * 
     * @param transformation
     *            The transformation to be performed on the element.
     */
    public abstract void transform(AffineTransformX transformation);

    /**
     * Calculates the distance from the element to the given point.
     * 
     * @param point
     *            The point to calculate the distance to.
     * @return The distance in user-space coordinates.
     */
    public abstract double getDistanceTo(PointUserSpace point);

    /**
     * Indicates if the given point is inside the current element's area.
     * 
     * @param point
     *            The point to be checked.
     * @return True if the point is inside the element's area, False otherwise.
     */
    public abstract boolean isPointInside(PointUserSpace point);

    /**
     * Checks if the current element intersects the given rectangle area.
     * 
     * @param x
     *            Lower-left x coordinate of the rectangle.
     * @param y
     *            Lower-left y coordinate of the renctangle.
     * @param w
     *            Width of the rectangle.
     * @param h
     *            Height of the rectangle.
     * @return True if the rectangle intersects with the currentl element, False
     *         otherwise.
     */
    public abstract boolean intersects(double x, double y, double w, double h);

    /**
     * Checks if the current element intersects the given bounds.
     * 
     * @param bounds
     *            The bounds to be checked.
     * @return True if the bounds intersects the current element, False
     *         otherwise.
     */
    public abstract boolean intersects(BoundsUserSpace bounds);

    /**
     * @return The element's line attributes.
     */
    public abstract AttribLine getAttribLine();

    /**
     * @return The element's fill attributes.
     */
    public abstract AttribFill getAttribFill();

    /**
     * @return The element's text attributes.
     */
    public abstract AttribText getAttribText();

    /**
     * Updates the element's attributes. This has to be called, if any of the
     * element's graphical attributes have been changed before the element is
     * being rendered.
     * 
     * @see #getAttribFill()
     * @see #getAttribLine()
     * @see #getAttribText()
     */
    public void updateAttributes() {
        AttribLine line = getAttribLine();
        if (line != null) {
            line.update(this);
        }
        AttribFill fill = getAttribFill();
        if (fill != null) {
            fill.update(this);
        }
        AttribText text = getAttribText();
        if (text != null) {
            text.update(this);
        }
    }

    /**
     * @return The name of the element.
     */
    public String getName() {
        return name;
    }

    /**
     * @param string
     *            The name to be set.
     */
    public void setName(String string) {
        name = string;
    }

    /**
     * @return Returns the element.
     */
    public ElemBase getElement() {
        return element;
    }

    /**
     * @param elem
     *            The element to set.
     */
    public void setElement(final ElemBase elem) {
        this.element = elem;
    }
}
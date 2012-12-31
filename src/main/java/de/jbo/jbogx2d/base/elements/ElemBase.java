//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemBase.java
// Created: 28.02.2004 - 14:08:37
//

package de.jbo.jbogx2d.base.elements;

import de.jbo.jbogx2d.base.attrib.AttribFill;
import de.jbo.jbogx2d.base.attrib.AttribLine;
import de.jbo.jbogx2d.base.drawing.Drawing;
import de.jbo.jbogx2d.base.drawing.traversing.ElementTraverser;
import de.jbo.jbogx2d.base.elements.model.ElemModel;
import de.jbo.jbogx2d.base.elements.view.ElemHandles;
import de.jbo.jbogx2d.base.elements.view.ElemView;
import de.jbo.jbogx2d.base.geom.AffineTransformX;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointUserSpace;
import de.jbo.jbogx2d.base.graphics.ViewContext;

/**
 * Implements the abstract base class for all graphical elements that can be
 * used by the jbogx2D Framework.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 28.02.2004: jbo created <br>
 */
public abstract class ElemBase {
    /** The underlying element model. */
    protected ElemModel model = null;

    /** The view used for rendering this element. */
    protected ElemView view = null;

    /** The element's parent container. */
    protected ElemContainer parent = null;

    /**
     * Creates a new instance.
     */
    public ElemBase() {
        setModel(createModel());
    }

    /**
     * Creates the model used by this element.
     * 
     * @return The created model.
     */
    protected abstract ElemModel createModel();

    /**
     * Creates the view for rendering of this element.
     * 
     * @param myModel
     *            The model to be used.
     * @return The created view.
     */
    protected abstract ElemView createView(ElemModel myModel);

    /**
     * @return The element's model.
     */
    public ElemModel getModel() {
        return model;
    }

    /**
     * @return The element's view.
     */
    public ElemView getView() {

        if (view == null) {
            view = createView(getModel());
        }

        return view;
    }

    /**
     * @return The element's parent.
     */
    public ElemBase getParent() {
        return parent;
    }

    /**
     * Returns the parent on the given hierarchy level in the element-tree of
     * the drawing. <br>
     * If the given level equals our current level, no parent is returned,
     * otherwise the parent on the given level is returned.
     * 
     * @param level
     *            The level in the element hierarchy to return the parent for.
     * @return The parent at the given level, or null if we have no parent or we
     *         are on the same level.
     */
    public ElemBase getParent(short level) {
        ElemBase p = null;

        if (level >= Drawing.LEVEL_ROOT) {
            if (getLevel() != level) {
                p = parent;
                if (p != null) {
                    if (p.getLevel() != level) {
                        p = p.getParent(level);
                    }
                }
            }
        }

        return p;
    }

    /**
     * @return The drawing this element is currently stored in (can be null).
     */
    public Drawing getDrawing() {
        Drawing d = null;
        ElemBase myParent = getParent(Drawing.LEVEL_ROOT);
        if (myParent != null) {
            d = myParent.getDrawing();
        }

        return d;
    }

    /**
     * Sets the given parent for this instance.
     * 
     * @param p
     *            The parent to be set.
     */
    protected void setParent(ElemContainer p) {
        parent = p;
    }

    /**
     * Returns the current level of the element within the element-tree of the
     * element's drawing.
     * 
     * @return The level of the element. 1 means the element is at top-level and
     *         has no further parent.
     */
    public short getLevel() {
        short level = 1;

        if (parent != null) {
            level += parent.getLevel();
        }

        return level;
    }

    /**
     * Removes this element from its parent. Afterwards the element isn't part
     * of a drawing anymore and therefore cannot be rendered.
     */
    public void removeFromParent() {
        if (parent != null) {
            parent.removeElem(this);
        }
    }

    /**
     * Calculates this element's area bounds.
     * 
     * @param bounds
     *            Out-parameter receiving the calculated bounds.
     * @see de.jbo.jbogx2d.base.elements.ElemBase#getBounds(de.jbo.jbogx2d.base.geom.RectUserSpace)
     */
    public void getBounds(BoundsUserSpace bounds) {
        model.getBounds(bounds);
    }

    /**
     * @return True if the element's bounds are currently dirty and need to be
     *         recalculated.
     * @see de.jbo.jbogx2d.base.elements.ElemBaseBoundsCaching#isBoundsDirty()
     */
    public boolean isBoundsDirty() {
        return model.isBoundsDirty();
    }

    /**
     * @param b
     *            True to flag the element's bounds as dirty, e.g. after any
     *            geometric modification.
     * @see de.jbo.jbogx2d.base.elements.ElemBaseBoundsCaching#setBoundsDirty(boolean)
     */
    public void setBoundsDirty(boolean b) {
        model.setBoundsDirty(b);
    }

    /**
     * Handles this element via the given element-traverser.
     * 
     * @param traverser
     *            The element-traverser to handle this element.
     * @return The state of the traversion (depends of the
     *         traverser-implementation).
     */
    public short traverse(ElementTraverser traverser) {
        return traverser.handleElement(this);
    }

    /**
     * @return The element's fill-attributes (can be null if element has no
     *         area).
     */
    public AttribFill getAttribFill() {
        return model.getAttribFill();
    }

    /**
     * @return The element's line-attribute (can be null if the element has no
     *         outline).
     */
    public AttribLine getAttribLine() {
        return model.getAttribLine();
    }

    /**
     * Calculates the distance of this element to a given point.
     * 
     * @param point
     *            The point to calculate the distance to.
     * @return The distance in the user-space system.
     */
    public double getDistanceTo(PointUserSpace point) {
        return model.getDistanceTo(point);
    }

    /**
     * Checks if the given bounds intersect this element's area.
     * 
     * @param bounds
     *            The bounds to be checked.
     * @return True if the bounds intersect, False otherwise.
     */
    public boolean intersects(BoundsUserSpace bounds) {
        return model.intersects(bounds);
    }

    /**
     * Checks if the given rectangular area intersects this element's area.
     * 
     * @param x
     *            The upper-left x-coordinate.
     * @param y
     *            The upper-left y-coordinate.
     * @param w
     *            The width of the rectangle.
     * @param h
     *            The height of the rectangle.
     * @return True if the rectangle intersects this element.
     */
    public boolean intersects(double x, double y, double w, double h) {
        return model.intersects(x, y, w, h);
    }

    /**
     * Checks if the given points is inside this element's area.
     * 
     * @param point
     *            The point to be checked.
     * @return True if the point is inside this element's area.
     */
    public boolean isPointInside(PointUserSpace point) {
        return model.isPointInside(point);
    }

    /**
     * Updates this elements visual attributes (line, fill, text).
     */
    public void updateAttributes() {
        model.updateAttributes();
        Drawing d = getDrawing();
        if (d != null) {
            BoundsUserSpace bounds = new BoundsUserSpace();
            getBounds(bounds);
            d.fireModificationEvent(bounds);
        }
    }

    /**
     * @return True if the element is curently visible when rendered.
     */
    public boolean isVisible() {
        return view.isVisible();
    }

    /**
     * Renders this element on the given view-context.
     * 
     * @param viewContext
     *            The context to render on.
     */
    public void paint(ViewContext viewContext) {
        this.view.performPaint(viewContext);
    }

    /**
     * Sets this element visible or invisible. If invisible, it is not rendered.
     * 
     * @param visible
     *            True to set the element visible, False otherwise.
     */
    public void setVisible(boolean visible) {
        view.setVisible(visible);
        Drawing d = getDrawing();
        if (d != null) {
            BoundsUserSpace bounds = new BoundsUserSpace();
            getBounds(bounds);
            d.fireModificationEvent(bounds);
        }
    }

    /**
     * Transforms this element.
     * 
     * @param transformation
     *            The transformation to be performed.
     */
    public void transform(AffineTransformX transformation) {
        model.transform(transformation);
        Drawing d = getDrawing();
        if (d != null) {
            BoundsUserSpace bounds = new BoundsUserSpace();
            getBounds(bounds);
            d.fireModificationEvent(bounds);
        }
    }

    /**
     * Sets the model to be used by this element.
     * 
     * @param model
     *            The model to be set.
     */
    public void setModel(ElemModel model) {
        if (this.model != null) {
            this.model.setElement(null);
        }
        this.model = model;

        ElemView myView = getView();

        if (myView != null) {
            myView.setModel(model);
        }
    }

    /**
     * @return True if the element's handles are visible, False otherwise.
     * @see de.jbo.jbogx2d.base.elements.view.ElemView#isHandlesVisible()
     */
    public boolean isHandlesVisible() {
        return getView().isHandlesVisible();
    }

    /**
     * Sets the element-handles-class to be used for generic handles-creation at
     * runtime.
     * 
     * @param ehc
     *            The class to be set.
     * @see de.jbo.jbogx2d.base.elements.view.ElemView#setElemHandlesClass(java.lang.Class)
     */
    public void setElemHandlesClass(Class<ElemHandles> ehc) {
        getView().setHandlesClass(ehc);
    }

    /**
     * Sets the handles visible or invisible.
     * 
     * @param visible
     *            True to make handles visible, False otherwise.
     * @see de.jbo.jbogx2d.base.elements.view.ElemView#setHandlesVisible(boolean)
     */
    public void setHandlesVisible(boolean visible) {
        getView().setHandlesVisible(visible);
    }
}
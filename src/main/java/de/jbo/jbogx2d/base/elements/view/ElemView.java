//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemView.java
// Created: 01.03.2004 - 00:07:20
//

package de.jbo.jbogx2d.base.elements.view;

import de.jbo.jbogx2d.base.Jbogx2D;
import de.jbo.jbogx2d.base.elements.model.ElemModel;
import de.jbo.jbogx2d.base.graphics.ViewContext;

/**
 * Implements the base class for an element's view used for rendering.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 01.03.2004: jbo created <br>
 */
public abstract class ElemView {
    /** Flag indicating that this view is invisible. */
    private static final short INVISIBLE_FLAG = 0x02;

    /** Defines the visibilty mode for this instance. */
    private short visibleMode = 0x00;

    /** The z-order of this view within the drawing's element-structure. */
    private int zorder = 0;

    /** The view handles class. */
    private Class<? extends ElemHandles> elemHandleClass = ElemHandlesBase.class;

    /** The view's handles. */
    private ElemHandles handles = null;

    /** The model being rendered. */
    private ElemModel model = null;

    /**
     * Creates a new instance.
     * 
     * @param m
     *            The model to be rendered.
     */
    protected ElemView(ElemModel m) {
        super();
        setModel(m);
    }

    /**
     * @return True if the view's handles are visible, False otherwise.
     */
    public boolean isHandlesVisible() {
        boolean visible = false;

        if (handles != null) {
            visible = handles.isVisible();
        }

        return visible;
    }

    /**
     * Sets the visibility of the view's handles.
     * 
     * @param visible
     *            True to make the handles visible, False otherwise.
     */
    public void setHandlesVisible(boolean visible) {
        if (visible) {
            if (handles == null) {
                initHandles();
                if (handles != null) {
                    handles.setVisible(true);
                }
            }
        } else {
            if (handles != null) {
                handles.uninit();
                handles = null;
            }
        }
    }

    /**
     * Sets the class for the handles.
     * 
     * @param ehc
     *            Class to be used for instanciating the handles.
     */
    public void setHandlesClass(Class<? extends ElemHandles> ehc) {
        elemHandleClass = ehc;
        handles = null;
    }

    /**
     * Initializes the handles.
     */
    private void initHandles() {
        try {
            handles = elemHandleClass.getConstructor().newInstance();
            handles.init(this);
            handles.update();
        } catch (Exception e) {
            Jbogx2D.getErrorHandler().handleError(e, true, false);
        }
    }

    /**
     * @return The model being rendered.
     */
    public ElemModel getModel() {
        return model;
    }

    /**
     * @return True if the view is visible, False otherwise.
     */
    public boolean isVisible() {
        return ((visibleMode & INVISIBLE_FLAG) != INVISIBLE_FLAG);
    }

    /**
     * Sets the visibility of this isntance.
     * 
     * @param visible
     *            True to make the view visible, False otherwise.
     */
    public void setVisible(boolean visible) {
        if (visible) {
            visibleMode &= (~INVISIBLE_FLAG);
        } else {
            visibleMode |= INVISIBLE_FLAG;
        }
    }

    /**
     * Renders the view to the given view-context.
     * 
     * @param view
     *            The context to render the view on.
     */
    public void paint(ViewContext view) {
        performPaint(view);
        if (isHandlesVisible()) {
            handles.paint(view);
        }
    }

    /**
     * Updates the view's handles.
     */
    public void updateHandles() {
        if (handles != null) {
            handles.update();
        }
    }

    /**
     * Implements the rendering for this instance.
     * 
     * @param view
     *            View-context to be used for rendering.
     */
    public abstract void performPaint(ViewContext view);

    /**
     * @return Returns the orderId.
     */
    public int getZorder() {
        return zorder;
    }

    /**
     * @param orderId
     *            The orderId to set.
     */
    public void setZorder(int orderId) {
        this.zorder = orderId;
    }

    /**
     * @param m
     *            The model to set.
     */
    public void setModel(ElemModel m) {
        this.model = m;
    }
}
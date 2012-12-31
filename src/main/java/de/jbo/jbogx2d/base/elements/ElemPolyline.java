//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemPolyline.java
// Created: 03.03.2004 - 20:41:21
//

package de.jbo.jbogx2d.base.elements;

import de.jbo.jbogx2d.base.elements.model.ElemModel;
import de.jbo.jbogx2d.base.elements.model.ElemModelPolyline;
import de.jbo.jbogx2d.base.elements.view.ElemView;
import de.jbo.jbogx2d.base.elements.view.ElemViewPolyline;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements a polyline.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 03.03.2004: jbo created <br>
 */
public class ElemPolyline extends ElemBase {
    /**
     * Creates a new instance.
     */
    public ElemPolyline() {
        super();
    }

    /**
     * Creates a new instance.
     * 
     * @param points
     *            The poly-points to be set.
     */
    public ElemPolyline(PointUserSpace[] points) {
        this();

        ElemModelPolyline polyModel = (ElemModelPolyline) getModel();
        polyModel.setPoints(points);
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.ElemBase#createModel()
     */
    @Override
    protected ElemModel createModel() {
        return new ElemModelPolyline(this);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.ElemBase#createView(de.jbo.jbogx2d.base.
     * elements.model.ElemModel)
     */
    @Override
    protected ElemView createView(ElemModel myModel) {
        return new ElemViewPolyline(myModel);
    }

    /**
     * Returns the point at the given index.
     * 
     * @param index
     *            The index to return the point for.
     * @param point
     *            Out-parameter to store the point.
     * @return True if successful.
     */
    public boolean getPoint(int index, PointUserSpace point) {
        ElemModelPolyline mod = (ElemModelPolyline) getModel();
        return mod.getPoint(index, point);
    }

    /**
     * @return The count of poly-points.
     */
    public int getPointCount() {
        ElemModelPolyline mod = (ElemModelPolyline) getModel();
        return mod.getPointCount();
    }

    /**
     * Inserts the point at the given index.
     * 
     * @param index
     *            The index to insert the new point at.
     * @param x
     *            The x-coordinate to be inserted.
     * @param y
     *            The y-coordinate to be inserted.
     * @return True if successful.
     */
    public boolean insertPoint(int index, double x, double y) {
        ElemModelPolyline mod = (ElemModelPolyline) getModel();
        return mod.insertPoint(index, x, y);
    }

    /**
     * Removes the point at the given index.
     * 
     * @param index
     *            The index to remove the point for.
     * @return True if successful.
     */
    public boolean removePoint(int index) {
        ElemModelPolyline mod = (ElemModelPolyline) getModel();
        return mod.removePoint(index);
    }

    /**
     * Sets the poly-point for the given index.
     * 
     * @param index
     *            The index of the point to be set.
     * @param x
     *            The x-coordinate to be set.
     * @param y
     *            The y-coordinate to be set.
     * @return True if successful.
     */
    public boolean setPoint(int index, double x, double y) {
        ElemModelPolyline mod = (ElemModelPolyline) getModel();
        return mod.setPoint(index, x, y);
    }

    /**
     * Sets the poly-point for the given index.
     * 
     * @param index
     *            The index of the point to be set.
     * @param point
     *            The point coordinates to be set.
     * @return True if successful.
     */
    public boolean setPoint(int index, PointUserSpace point) {
        ElemModelPolyline mod = (ElemModelPolyline) getModel();
        return mod.setPoint(index, point);
    }

    /**
     * @param pointCount
     *            The point count to be set.
     */
    public void setPointCount(int pointCount) {
        ElemModelPolyline mod = (ElemModelPolyline) getModel();
        mod.setPointCount(pointCount);
    }

    /**
     * Sets the given poly-points. This overwrites are points stored before.
     * 
     * @param points
     *            The points to be set.
     */
    public void setPoints(PointUserSpace[] points) {
        ElemModelPolyline mod = (ElemModelPolyline) getModel();
        mod.setPoints(points);
    }

}
//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: GraphicsContainerModel.java
// Created: 29.02.2004 - 11:15:58
//

package de.jbo.jbogx2d.base.elements.model;

import java.util.LinkedList;
import java.util.ListIterator;

import de.jbo.jbogx2d.base.attrib.AttribFill;
import de.jbo.jbogx2d.base.attrib.AttribLine;
import de.jbo.jbogx2d.base.attrib.AttribText;
import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.geom.AffineTransformX;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements the model for an element container.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 29.02.2004: jbo created <br>
 */
public class ElemModelContainer extends ElemModel {
    /** List of child elements. */
    private final LinkedList<ElemBase> childList = new LinkedList<ElemBase>();

    /**
     * Creates a new instance.
     * 
     * @param element
     *            The referenced parent element.
     */
    public ElemModelContainer(ElemBase element) {
        super(element);
    }

    /**
     * @return The list of child elements.
     */
    public LinkedList<ElemBase> getChildList() {
        return childList;
    }

    /*
     * @see
     * 
     * de.jbo.jbogx2d.base.elements.model.ElemModel#calculateBounds(de.jbo.jbogx2d
     * .base.geom.BoundsUserSpace)
     */
    @Override
    protected synchronized void calculateBounds(BoundsUserSpace bounds) {
        BoundsUserSpace b = new BoundsUserSpace();
        ElemBase child = null;
        boolean firstSet = false;
        ListIterator<ElemBase> iterator = childList.listIterator();

        while (iterator.hasNext()) {
            child = iterator.next();
            child.getBounds(b);

            if (b.isValid()) {
                if (!firstSet) {
                    bounds.set(b);
                    firstSet = true;
                } else {
                    bounds.merge(b);
                }
            }
        }
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.ElemModel#getDistanceTo(de.jbo.jbogx2d
     * .base.geom.PointUserSpace)
     */
    @Override
    public double getDistanceTo(PointUserSpace point) {
        double distance = 0.0;
        BoundsUserSpace bounds = new BoundsUserSpace();
        getBounds(bounds);
        distance = bounds.getDistanceTo(point);
        return distance;
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.ElemModel#isPointInside(de.jbo.jbogx2d
     * .base.geom.PointUserSpace)
     */
    @Override
    public boolean isPointInside(PointUserSpace point) {
        boolean isInside = false;
        BoundsUserSpace bounds = new BoundsUserSpace();
        getBounds(bounds);
        isInside = bounds.contains(point);
        return isInside;
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModel#getAttribFill()
     */
    @Override
    public AttribFill getAttribFill() {
        return null;
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModel#getAttribLine()
     */
    @Override
    public AttribLine getAttribLine() {
        return null;
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModel#intersects(double,
     * double, double, double)
     */
    @Override
    public boolean intersects(double x, double y, double w, double h) {
        boolean intersects = false;
        BoundsUserSpace bounds = new BoundsUserSpace();
        getBounds(bounds);
        intersects = bounds.intersects(x, y, w, h);
        return intersects;
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.ElemModel#intersects(de.jbo.jbogx2d
     * .base.geom.BoundsUserSpace)
     */
    @Override
    public boolean intersects(BoundsUserSpace bounds) {
        return intersects(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.ElemModel#transform(de.jbo.jbogx2d
     * .base.geom.AffineTransformX)
     */
    @Override
    public void transform(AffineTransformX transformation) {
        ListIterator<ElemBase> it = childList.listIterator();
        ElemModel model = null;

        while (it.hasNext()) {
            model = it.next().getModel();
            model.transform(transformation);
        }

        setBoundsDirty(true);
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModel#getAttribText()
     */
    @Override
    public AttribText getAttribText() {
        return null;
    }

}
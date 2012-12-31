//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemPolyline.java
// Created: 03.03.2004 - 20:41:21
//

package de.jbo.jbogx2d.base.elements;

import de.jbo.jbogx2d.base.elements.model.ElemModel;
import de.jbo.jbogx2d.base.elements.model.ElemModelPolygon;
import de.jbo.jbogx2d.base.elements.view.ElemView;
import de.jbo.jbogx2d.base.elements.view.ElemViewPolygon;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements a polygon.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 03.03.2004: jbo created <br>
 */
public class ElemPolygon extends ElemPolyline {
    /**
     * Creates a new instance.
     */
    public ElemPolygon() {
        super();
    }

    /**
     * Creates a new instance.
     * 
     * @param points
     *            The poly-points to be set.
     */
    public ElemPolygon(PointUserSpace[] points) {
        super(points);
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.ElemPolyline#createModel()
     */
    @Override
    protected ElemModel createModel() {
        return new ElemModelPolygon(this);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.ElemPolyline#createView(de.jbo.jbogx2d.base
     * .elements.model.ElemModel)
     */
    @Override
    protected ElemView createView(ElemModel myModel) {
        return new ElemViewPolygon(myModel);
    }
}
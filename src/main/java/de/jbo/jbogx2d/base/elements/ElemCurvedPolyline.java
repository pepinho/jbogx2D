//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemCurvedPolyline.java
// Created: 14.03.2004 - 13:19:12
//

package de.jbo.jbogx2d.base.elements;

import de.jbo.jbogx2d.base.elements.model.ElemModel;
import de.jbo.jbogx2d.base.elements.model.ElemModelCurvedPolyline;
import de.jbo.jbogx2d.base.elements.view.ElemView;
import de.jbo.jbogx2d.base.elements.view.ElemViewCurvedPolyline;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements a curved polyline.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 14.03.2004: jbo created <br>
 */
public class ElemCurvedPolyline extends ElemPolyline {

    /**
     * Creates a new instance.
     */
    public ElemCurvedPolyline() {
        super();
    }

    /**
     * Creates a new instance.
     * 
     * @param points
     *            The poly-points to be set.
     */
    public ElemCurvedPolyline(PointUserSpace[] points) {
        super(points);
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.ElemPolyline#createModel()
     */
    @Override
    protected ElemModel createModel() {
        return new ElemModelCurvedPolyline(this);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.ElemPolyline#createView(de.jbo.jbogx2d.base
     * .elements.model.ElemModel)
     */
    @Override
    protected ElemView createView(ElemModel myModel) {
        return new ElemViewCurvedPolyline(myModel);
    }

}
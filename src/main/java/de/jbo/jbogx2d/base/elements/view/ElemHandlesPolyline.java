//
// Copyright (c) 2007 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemHandlesPolyline.java
// Created: 07.06.2007 - 22:57:08
//
package de.jbo.jbogx2d.base.elements.view;

import de.jbo.jbogx2d.base.elements.model.ElemModel;
import de.jbo.jbogx2d.base.elements.model.ElemModelPolyline;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements specific handles for poly-elements.
 * 
 * @author Josef Baro (jbo)
 * @version 07.06.2007 jbo - created
 */
public class ElemHandlesPolyline extends ElemHandlesBase {

    /**
     * Creates a new instance.
     */
    public ElemHandlesPolyline() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.jbo.jbogx2d.base.elements.view.ElemHandlesBase#performUpdate(de.jbo
     * .jbogx2d.base.elements.model.ElemModel)
     */
    @Override
    protected final PointUserSpace[] performUpdate(final ElemModel model) {
        ElemModelPolyline m = (ElemModelPolyline) model;
        return m.getPoints();
    }
}

//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemViewCurvedPolyline.java
// Created: 14.03.2004 - 13:18:38
//

package de.jbo.jbogx2d.base.elements.view;

import de.jbo.jbogx2d.base.elements.model.ElemModel;

/**
 * Implements a view rendering curved polylines.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 14.03.2004: jbo created <br>
 */
public class ElemViewCurvedPolyline extends ElemViewPolyline {
    /**
     * Creates a new instance.
     * 
     * @param model
     *            The model to be handled.
     */
    public ElemViewCurvedPolyline(ElemModel model) {
        super(model);
        setHandlesClass(ElemHandlesPolyline.class);
    }

}
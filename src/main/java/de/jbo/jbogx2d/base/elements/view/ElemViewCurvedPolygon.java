//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemViewCurvedPolygon.java
// Created: 07.11.2004 - 19:26:38
//

package de.jbo.jbogx2d.base.elements.view;

import de.jbo.jbogx2d.base.elements.model.ElemModel;

/**
 * Implements a view rendering curved polygons.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 07.11.2004: jbo created <br>
 */
public class ElemViewCurvedPolygon extends ElemViewPolygon {
    /**
     * Creates a new instance.
     * 
     * @param model
     *            The model to be handled.
     */
    public ElemViewCurvedPolygon(ElemModel model) {
        super(model);
        setHandlesClass(ElemHandlesPolyline.class);
    }

}
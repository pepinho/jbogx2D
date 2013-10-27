//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemModelCurvedPolyline.java
// Created: 14.03.2004 - 13:17:12
//

package de.jbo.jbogx2d.base.elements.model;

import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.elements.model.shapes.CurvedPolyline2D;

/**
 * Implements the model for a curved polyline.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 14.03.2004: jbo created <br>
 */
public class ElemModelCurvedPolyline extends ElemModelPolyline {

    /**
     * Creates a new instance.
     * 
     * @param element
     *            The referenced parent element.
     */
    public ElemModelCurvedPolyline(ElemBase element) {
        super(element);
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#initShape()
     */
    @Override
    protected void initShape() {
        shape = new CurvedPolyline2D();
    }

}
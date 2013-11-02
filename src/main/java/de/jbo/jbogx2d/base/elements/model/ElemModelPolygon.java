//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ModelElemPolyline.java
// Created: 01.03.2004 - 01:51:53
//

package de.jbo.jbogx2d.base.elements.model;

import de.jbo.jbogx2d.base.attrib.AttribFill;
import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.elements.model.shapes.Polygon2D;

/**
 * Implements the model for a polygon.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 01.03.2004: jbo created <br>
 */
public class ElemModelPolygon extends ElemModelPolyline {
    /** The fill atributes. */
    private final AttribFill attribFill = new AttribFill();

    /**
     * Creates a new instance.
     * 
     * @param element
     *            The referenced parent element.
     */
    public ElemModelPolygon(ElemBase element) {
        super(element);
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#getAttribFill()
     */
    @Override
    public AttribFill getAttribFill() {
        return attribFill;
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#initShape()
     */
    @Override
    protected void initShape() {
        setShape(new Polygon2D());
    }
}
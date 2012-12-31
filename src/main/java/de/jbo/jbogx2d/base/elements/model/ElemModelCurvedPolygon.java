/*
 * ElemModelCurvedPolygon.java
 * Created on 07.11.2004
 * Copyright 2004 by jbo
 */
package de.jbo.jbogx2d.base.elements.model;

import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.elements.model.shapes.CurvedPolygon2D;

/**
 * Implements the model for curved polygons.
 * 
 * @author Josef Baro (jbo)
 * @version 07.11.2004 Josef Baro - created
 */
public class ElemModelCurvedPolygon extends ElemModelPolygon {

    /*
     * (non-Javadoc)
     * 
     * @see de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#initShape()
     */
    @Override
    protected void initShape() {
        shape = new CurvedPolygon2D();
    }

    /**
     * Creates a new instance.
     * 
     * @param element
     *            The element we are referencing.
     */
    public ElemModelCurvedPolygon(ElemBase element) {
        super(element);
    }

}

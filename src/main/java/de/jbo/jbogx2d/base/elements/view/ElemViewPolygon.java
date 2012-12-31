//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemViewPolyline.java
// Created: 03.03.2004 - 19:59:44
//

package de.jbo.jbogx2d.base.elements.view;

import java.awt.Shape;

import de.jbo.jbogx2d.base.elements.model.ElemModel;
import de.jbo.jbogx2d.base.elements.model.ElemModelPolygon;
import de.jbo.jbogx2d.base.graphics.ViewContext;

/**
 * The view for polygon elements.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 03.03.2004: jbo created <br>
 */
public class ElemViewPolygon extends ElemView {
    /**
     * Creates a new instance.
     * 
     * @param model
     *            The model we are rendering.
     */
    public ElemViewPolygon(ElemModel model) {
        super(model);
        setHandlesClass(ElemHandlesPolyline.class);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.view.ElemView#performPaint(de.jbo.jbogx2d
     * .base.graphics.ViewContext)
     */
    @Override
    public void performPaint(ViewContext view) {
        ElemModelPolygon model = (ElemModelPolygon) getModel();
        Shape shape = model.getShape();

        view.draw(shape, model.getAttribLine(), model.getAttribFill());
    }
}
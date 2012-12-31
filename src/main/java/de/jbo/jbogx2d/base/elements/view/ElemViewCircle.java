//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemViewCircle.java
// Created: 14.03.2004 - 11:27:19
//

package de.jbo.jbogx2d.base.elements.view;

import de.jbo.jbogx2d.base.elements.model.ElemModel;
import de.jbo.jbogx2d.base.elements.model.ElemModelCircle;
import de.jbo.jbogx2d.base.graphics.ViewContext;

/**
 * The view for circle-elements.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 14.03.2004: jbo created <br>
 */
public class ElemViewCircle extends ElemView {

    /**
     * Creates a new instance.
     * 
     * @param model
     *            The model we are rendering.
     */
    public ElemViewCircle(ElemModel model) {
        super(model);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.view.ElemView#performPaint(de.jbo.jbogx2d
     * .base.graphics.ViewContext)
     */
    @Override
    public void performPaint(ViewContext view) {
        ElemModelCircle m = (ElemModelCircle) getModel();
        view.draw(m.getShape(), m.getAttribLine(), m.getAttribFill());
    }

}
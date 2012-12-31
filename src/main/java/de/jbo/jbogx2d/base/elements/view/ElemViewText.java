//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemViewText.java
// Created: 11.03.2004 - 19:13:11
//

package de.jbo.jbogx2d.base.elements.view;

import de.jbo.jbogx2d.base.elements.model.ElemModel;
import de.jbo.jbogx2d.base.elements.model.ElemModelText;
import de.jbo.jbogx2d.base.graphics.ViewContext;

/**
 * Implements the view for text-elements.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 11.03.2004: jbo created <br>
 */
public class ElemViewText extends ElemView {
    /**
     * Creates a new instance referencing the given element model.
     * 
     * @param model
     *            The element model to be referenced.
     */
    public ElemViewText(ElemModel model) {
        super(model);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.view.ElemView#performPaint(de.jbo.jbogx2d
     * .base.graphics.ViewContext)
     */
    @Override
    public void performPaint(ViewContext view) {
        ElemModelText modelText = (ElemModelText) getModel();
        view.drawText(modelText.getBasePoint(), modelText.getText(), modelText.getAttribText(), modelText.getTransformation());
    }

}
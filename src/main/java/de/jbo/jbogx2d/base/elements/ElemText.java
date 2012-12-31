//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemText.java
// Created: 11.03.2004 - 19:43:49
//

package de.jbo.jbogx2d.base.elements;

import de.jbo.jbogx2d.base.attrib.AttribText;
import de.jbo.jbogx2d.base.elements.model.ElemModel;
import de.jbo.jbogx2d.base.elements.model.ElemModelText;
import de.jbo.jbogx2d.base.elements.view.ElemView;
import de.jbo.jbogx2d.base.elements.view.ElemViewText;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements a text-element.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 11.03.2004: jbo created <br>
 */
public class ElemText extends ElemBase {

    /**
     * Creates a new instance.
     */
    public ElemText() {
        super();
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.ElemBase#createModel()
     */
    @Override
    protected ElemModel createModel() {
        return new ElemModelText(this);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.ElemBase#createView(de.jbo.jbogx2d.base.
     * elements.model.ElemModel)
     */
    @Override
    protected ElemView createView(ElemModel myModel) {
        return new ElemViewText(myModel);
    }

    /**
     * Sets the position of the texts base-point.
     * 
     * @param p
     *            The point to be set.
     */
    public void setBasePoint(PointUserSpace p) {
        setBasePoint(p.x, p.y);
    }

    /**
     * Sets the position of the texts base-point.
     * 
     * @param x
     *            The x-coordinate to be set.
     * @param y
     *            The y-coordinate to be set.
     */
    public void setBasePoint(double x, double y) {
        ElemModelText myModel = (ElemModelText) getModel();
        myModel.setBasePoint(x, y);
    }

    /**
     * Sets the text-string to be displayed by this element.
     * 
     * @param text
     *            The string to be set.
     */
    public void setText(String[] text) {
        ElemModelText myModel = (ElemModelText) getModel();
        myModel.setText(text);
    }

    /**
     * Returns the text-attributes of this element.
     * 
     * @return The attributes of the text are returned.
     */
    public AttribText getAttribText() {
        ElemModelText myModel = (ElemModelText) getModel();
        return myModel.getAttribText();
    }

}
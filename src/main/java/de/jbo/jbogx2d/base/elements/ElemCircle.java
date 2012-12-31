//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemCircle.java
// Created: 14.03.2004 - 11:24:59
//

package de.jbo.jbogx2d.base.elements;

import de.jbo.jbogx2d.base.elements.model.ElemModel;
import de.jbo.jbogx2d.base.elements.model.ElemModelCircle;
import de.jbo.jbogx2d.base.elements.view.ElemView;
import de.jbo.jbogx2d.base.elements.view.ElemViewCircle;

/**
 * Implements the element-class for circles.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 14.03.2004: jbo created <br>
 */
public class ElemCircle extends ElemBase {
    /**
     * The default radius.
     */
    private static final double DEFAULT_RADIUS = 10.0;

    /**
     * Creates a new instance with the given values.
     * 
     * @param middleX
     *            The x-coordinate of the middle-point.
     * @param middleY
     *            The y-coordinate of the middle-point.
     * @param radius
     *            The radius of the circle.
     */
    public ElemCircle(final double middleX, final double middleY, final double radius) {
        super();
        ElemModelCircle m = (ElemModelCircle) getModel();

        m.setRadius(radius);
        m.setCenter(middleX, middleY);
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.ElemBase#createModel()
     */
    @Override
    protected final ElemModel createModel() {
        return new ElemModelCircle(this, 0.0, 0.0, DEFAULT_RADIUS);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.ElemBase#createView(de.jbo.jbogx2d.base.
     * elements.model.ElemModel)
     */
    @Override
    protected final ElemView createView(final ElemModel myModel) {
        return new ElemViewCircle(myModel);
    }

}
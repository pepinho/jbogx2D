/*
 * ElemCurvedPolygon.java
 * Created on 07.11.2004
 * Copyright 2004 by jbo
 */
package de.jbo.jbogx2d.base.elements;

import de.jbo.jbogx2d.base.elements.model.ElemModel;
import de.jbo.jbogx2d.base.elements.model.ElemModelCurvedPolygon;
import de.jbo.jbogx2d.base.elements.view.ElemView;
import de.jbo.jbogx2d.base.elements.view.ElemViewCurvedPolygon;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements a curved polygon.
 * 
 * @author Josef Baro (jbo)
 * @version 07.11.2004 Josef Baro - created
 */
public class ElemCurvedPolygon extends ElemPolygon {

    /*
     * (non-Javadoc)
     * @see de.jbo.jbogx2d.base.elements.ElemBase#createModel()
     */
    @Override
    protected ElemModel createModel() {
        return new ElemModelCurvedPolygon(this);
    }

    /*
     * (non-Javadoc)
     * @see
     * de.jbo.jbogx2d.base.elements.ElemBase#createView(de.jbo.jbogx2d.base.
     * elements.model.ElemModel)
     */
    @Override
    protected ElemView createView(ElemModel myModel) {
        return new ElemViewCurvedPolygon(myModel);
    }

    /**
     * Creates a new instance.
     */
    public ElemCurvedPolygon() {
        super();
    }

    /**
     * Creates a new instance.
     * 
     * @param points
     *            The poly-points to be set.
     */
    public ElemCurvedPolygon(PointUserSpace[] points) {
        super(points);
    }

}

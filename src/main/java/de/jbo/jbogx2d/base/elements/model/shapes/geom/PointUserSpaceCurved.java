/*
 * PointUserSpaceCurved.java
 * Created on 07.11.2004
 * Copyright 2004 by jbo
 */
package de.jbo.jbogx2d.base.elements.model.shapes.geom;

import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements specific coordinates used for curved elements.
 * 
 * @author Josef Baro (jbo)
 * @version 07.11.2004 Josef Baro - created
 */
public class PointUserSpaceCurved extends PointUserSpace {
    /**
     * Serial id.
     */
    private static final long serialVersionUID = 4461917183153663746L;

    /** Control point for the corresponding poly-point. */
    private PointUserSpace controlPoint = null;

    /**
     * Creates a new instance.
     * 
     * @param x
     *            The x-coordinate to be set.
     * @param y
     *            The y-coordinate to be set.
     */
    public PointUserSpaceCurved(double x, double y) {
        super(x, y);
    }

    /**
     * Creates a new instance.
     */
    public PointUserSpaceCurved() {
        super();
    }

    /**
     * @return the controlPoint
     */
    public final PointUserSpace getControlPoint() {
        return controlPoint;
    }

    /**
     * @param cp
     *            the controlPoint to set
     */
    public final void setControlPoint(PointUserSpace cp) {
        this.controlPoint = cp;
    }

}
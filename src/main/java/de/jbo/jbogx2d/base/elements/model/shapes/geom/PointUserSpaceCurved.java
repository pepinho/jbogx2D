/*
 * PointUserSpaceCurved.java
 * Created on 07.11.2004
 * Copyright 2004 by jbo
 */
package de.jbo.jbogx2d.base.elements.model.shapes.geom;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(controlPoint);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        PointUserSpaceCurved other = (PointUserSpaceCurved) obj;
        return Objects.equals(controlPoint, other.controlPoint);
    }
    
    

}
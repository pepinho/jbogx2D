//
// Copyright (c) 2007 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemHandlesBase.java
// Created: 07.06.2007 - 18:08:07
//
package de.jbo.jbogx2d.base.elements.view;

import java.awt.Shape;

import de.jbo.jbogx2d.base.elements.model.ElemModel;
import de.jbo.jbogx2d.base.elements.model.shapes.Circle2D;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Elements the base class for rendered element-handles.
 * 
 * @author Josef Baro (jbo)
 * @version 07.06.2007 jbo - created
 */
public class ElemHandlesBase extends ElemHandles {
    /** Default handles count. */
    public static final int HANDLES_COUNT = 8;

    /** Handle north-west. */
    public static final int NORTHWEST = 0;

    /** Handle north. */
    public static final int NORTH = 1;

    /** Handle north-east. */
    public static final int NORTH_EAST = 2;

    /** Handle east. */
    public static final int EAST = 3;

    /** Handle south-east. */
    public static final int SOUTH_EAST = 4;

    /** Handle south. */
    public static final int SOUTH = 5;

    /** Handle south-west. */
    public static final int SOUTH_WEST = 6;

    /** Handle west. */
    public static final int WEST = 7;

    /**
     * Creates a new instance.
     */
    public ElemHandlesBase() {
        super();
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.view.ElemHandles#performUpdate(de.jbo.jbogx2d
     * .base.elements.model.ElemModel)
     */
    @Override
    protected PointUserSpace[] performUpdate(final ElemModel model) {
        BoundsUserSpace bounds = new BoundsUserSpace();
        model.getBounds(bounds);

        double halfWidth = bounds.width / 2.0;
        double halfHeight = bounds.height / 2.0;
        double leftX = bounds.x;
        double topY = bounds.y;
        double rightX = leftX + bounds.width;
        double bottomY = topY + bounds.height;

        PointUserSpace[] points = new PointUserSpace[HANDLES_COUNT];
        points[NORTHWEST] = new PointUserSpace(leftX, topY);
        points[NORTH] = new PointUserSpace(leftX + halfWidth, topY);
        points[NORTH_EAST] = new PointUserSpace(rightX, topY);
        points[EAST] = new PointUserSpace(rightX, topY + halfHeight);
        points[SOUTH_EAST] = new PointUserSpace(rightX, bottomY);
        points[SOUTH] = new PointUserSpace(rightX - halfWidth, bottomY);
        points[SOUTH_WEST] = new PointUserSpace(leftX, bottomY);
        points[WEST] = new PointUserSpace(leftX, bottomY - halfHeight);

        return points;
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.view.ElemHandles#createHandleShape(de.jbo
     * .jbogx2d.base.geom.PointUserSpace, double, double)
     */
    @Override
    protected Shape createHandleShape(final PointUserSpace handlePoint, final double width, final double height) {
        return new Circle2D(handlePoint, width);        
    }

}

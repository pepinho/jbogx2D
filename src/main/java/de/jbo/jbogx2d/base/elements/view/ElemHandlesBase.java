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

        PointUserSpace[] points = new PointUserSpace[8];
        points[0] = new PointUserSpace(leftX, topY);
        points[1] = new PointUserSpace(leftX + halfWidth, topY);
        points[2] = new PointUserSpace(rightX, topY);
        points[3] = new PointUserSpace(rightX, topY + halfHeight);
        points[4] = new PointUserSpace(rightX, bottomY);
        points[5] = new PointUserSpace(rightX - halfWidth, bottomY);
        points[6] = new PointUserSpace(leftX, bottomY);
        points[7] = new PointUserSpace(leftX, bottomY - halfHeight);

        return points;
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.view.ElemHandles#createHandleShape(de.jbo
     * .jbogx2d.base.geom.PointUserSpace, double, double)
     */
    @Override
    protected Shape createHandleShape(final PointUserSpace handlePoint, final double width, final double height) {
        Circle2D shape = new Circle2D(handlePoint, width);
        return shape;
    }

}

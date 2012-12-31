//
// Copyright (c) 2010 by jbo - Josef Baro
//
// Project: jbogx2D
// File: MouseInteractionXorPolygon.java
// Created: 17.02.2010 - 22:32:12
//
package de.jbo.jbogx2d.ui.interaction.xor;

import de.jbo.jbogx2d.base.elements.ElemPolygon;
import de.jbo.jbogx2d.base.geom.PointScreen;
import de.jbo.jbogx2d.base.geom.PointUserSpace;
import de.jbo.jbogx2d.base.graphics.ViewContext;
import de.jbo.jbogx2d.base.graphics.ViewHandler;
import de.jbo.jbogx2d.ui.interaction.IMouseInteractionXorElement;

/**
 * XOR polygon.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 17.02.2010 jbo - created <br>
 */
public class MouseInteractionXorPolygon extends ElemPolygon implements IMouseInteractionXorElement {

    /**
     * @param points
     *            Points to be set.
     */
    public MouseInteractionXorPolygon(PointUserSpace[] points) {
        super(points);
    }

    /**
     * Creates a new instance.
     */
    public MouseInteractionXorPolygon() {
        super();
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.ElemBase#updateAttributes()
     */
    @Override
    public void updateAttributes() {
        getAttribLine().setStroke((short) 2);
        super.updateAttributes();
    }

    /**
     * Adds the given point.
     * 
     * @param index
     *            Index of the point to be set.
     * @param x
     *            x-coordinate to be set.
     * @param y
     *            y-coordinate to be set.
     * @param handler
     *            View-handler for coordinate transformation.
     */
    public void setPoint(int index, double x, double y, ViewHandler handler) {
        PointUserSpace user = new PointUserSpace(x, y);
        PointScreen screen = new PointScreen();
        handler.transformUserSpaceToScreen(user, screen);
        setPoint(index, screen.x, screen.y);
    }

    /*
     * @see
     * de.jbo.jbogx2d.ui.interaction.IMouseInteractionXorElement#paint(de.jbo
     * .jbogx2d.base.graphics.ViewContext)
     */
    @Override
    public void paint(ViewContext viewContext) {
        viewContext.setUserSpaceTransformEnabled(false);
        viewContext.setXOR(true);
        super.paint(viewContext);
        viewContext.setXOR(false);
        viewContext.setUserSpaceTransformEnabled(true);
    }

    /*
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(IMouseInteractionXorElement o) {
        return o.hashCode() - hashCode();
    }

}

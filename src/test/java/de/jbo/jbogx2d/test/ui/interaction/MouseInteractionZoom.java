//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: MouseInteractionZoom.java
// Created: 17.02.2010 - 22:24:07
//
package de.jbo.jbogx2d.test.ui.interaction;

import de.jbo.jbogx2d.base.attrib.AttribFillType;
import de.jbo.jbogx2d.base.geom.BoundsScreen;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointUserSpace;
import de.jbo.jbogx2d.base.graphics.ViewHandler;
import de.jbo.jbogx2d.ui.interaction.MouseInteractionAdapter;
import de.jbo.jbogx2d.ui.interaction.MouseInteractionHandler;
import de.jbo.jbogx2d.ui.interaction.PaintRequest;
import de.jbo.jbogx2d.ui.interaction.xor.MouseInteractionXorPolygon;

/**
 * Implements a zoom mouse-interaction that offers: <br>
 * <ul>
 * <li>Zoom via rubberband-rectangle.</li>
 * </ul>
 * 
 * @author Josef Baro (jbo) <br>
 * @version 17.02.2010 jbo - created <br>
 */
public class MouseInteractionZoom extends MouseInteractionAdapter {
    /** Paint request. */
    private final PaintRequest paintRequest = new PaintRequest();

    /** The xor-rectangle. */
    private final MouseInteractionXorPolygon xorRectangle = new MouseInteractionXorPolygon();

    /** The view-handler. */
    private ViewHandler viewHandler = null;

    /** Indicates if drag was processed. */
    private boolean isDragged = false;

    /** Mouse-pressed point. */
    private final PointUserSpace pointPressed = new PointUserSpace();

    /*
     * @see
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#init(de.jbo.jbogx2d.ui
     * .interaction.MouseInteractionHandler,
     * de.jbo.jbogx2d.base.graphics.ViewHandler)
     */
    @Override
    public void init(MouseInteractionHandler interactionHandler, ViewHandler view) {
        this.viewHandler = view;
        xorRectangle.setPointCount(4);
        xorRectangle.getAttribFill().setFillType(AttribFillType.TYPE_HOLLOW);
        xorRectangle.getAttribLine().setWidth(3);
        xorRectangle.getAttribLine().setStroke((short) 3);
        xorRectangle.updateAttributes();
        paintRequest.addXorElement(xorRectangle);
        paintRequest.setResetXor(false);
    }

    /*
     * @see de.jbo.jbogx2d.ui.interaction.IMouseInteraction#uninit()
     */
    @Override
    public void uninit() {
        viewHandler = null;
        paintRequest.getXorElements().clear();
        paintRequest.setResetXor(false);
    }

    /*
     * @see
     * de.jbo.jbogx2d.ui.interaction.MouseInteractionAdapter#handleMouseDragLeft
     * (de.jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMouseDragLeft(PointUserSpace mousePos, int keyMask) {

        xorRectangle.setPoint(2, mousePos.x, mousePos.y, viewHandler);
        xorRectangle.setPoint(1, mousePos.x, pointPressed.y, viewHandler);
        xorRectangle.setPoint(3, pointPressed.x, mousePos.y, viewHandler);

        paintRequest.setRepaintRequested(false);
        paintRequest.setBoundsModified(null);

        isDragged = true;

        return paintRequest;

    }

    /*
     * @see
     * 
     * de.jbo.jbogx2d.ui.interaction.MouseInteractionAdapter#handleMousePressedLeft
     * (de.jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMousePressedLeft(PointUserSpace mousePos, int keyMask) {
        isDragged = false;
        pointPressed.set(mousePos);
        xorRectangle.setPoint(0, mousePos.x, mousePos.y, viewHandler);
        paintRequest.setResetXor(false);
        paintRequest.addXorElement(xorRectangle);
        return null;
    }

    /*
     * @see
     * 
     * de.jbo.jbogx2d.ui.interaction.MouseInteractionAdapter#handleMouseReleasedLeft
     * (de.jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMouseReleasedLeft(PointUserSpace mousePos, int keyMask) {
        paintRequest.setBoundsModified(null);
        paintRequest.setRepaintRequested(true);
        paintRequest.setResetXor(true);
        BoundsUserSpace zoomBounds = new BoundsUserSpace();

        xorRectangle.getBounds(zoomBounds);
        BoundsScreen screen = new BoundsScreen((int) Math.round(zoomBounds.x), (int) Math.round(zoomBounds.y), (int) Math.round(zoomBounds.width), (int) Math.round(zoomBounds.height));
        viewHandler.transformScreenToUserSpace(screen, zoomBounds);
        if (isDragged) {
            viewHandler.setVisibleUserSpace(zoomBounds, false);
        } else {
            paintRequest.setRepaintRequested(false);
        }

        isDragged = false;
        return paintRequest;
    }
}

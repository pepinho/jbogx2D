package de.jbo.jbogx2d.ui.interaction;

import de.jbo.jbogx2d.base.geom.PointUserSpace;

//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: MouseInteractionAdapter.java
// Created: 17.02.2010 - 22:21:16
//

/**
 * @author Josef Baro (jbo) <br>
 * @version 17.02.2010 jbo - created <br>
 */
public abstract class MouseInteractionAdapter implements IMouseInteraction {

    /*
     * @see
     * 
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#handleMouseDoubleclickLeft
     * (de.jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMouseDoubleclickLeft(PointUserSpace mousePos, int keyMask) {

        return null;
    }

    /*
     * @see
     * 
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#handleMouseDoubleclickMiddle
     * (de.jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMouseDoubleclickMiddle(PointUserSpace mousePos, int keyMask) {

        return null;
    }

    /*
     * @see
     * 
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#handleMouseDoubleclickRight
     * (de.jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMouseDoubleclickRight(PointUserSpace mousePos, int keyMask) {

        return null;
    }

    /*
     * @see
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#handleMouseDragLeft(de
     * .jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMouseDragLeft(PointUserSpace mousePos, int keyMask) {

        return null;
    }

    /*
     * @see
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#handleMouseDragMiddle
     * (de.jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMouseDragMiddle(PointUserSpace mousePos, int keyMask) {

        return null;
    }

    /*
     * @see
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#handleMouseDragRight(
     * de.jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMouseDragRight(PointUserSpace mousePos, int keyMask) {

        return null;
    }

    /*
     * @see
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#handleMouseEntered(de
     * .jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMouseEntered(PointUserSpace mousePos, int keyMask) {

        return null;
    }

    /*
     * @see
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#handleMouseExited(de.
     * jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMouseExited(PointUserSpace mousePos, int keyMask) {

        return null;
    }

    /*
     * @see
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#handleMouseMoved(de.jbo
     * .jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMouseMoved(PointUserSpace mousePos, int keyMask) {

        return null;
    }

    /*
     * @see
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#handleMousePressedLeft
     * (de.jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMousePressedLeft(PointUserSpace mousePos, int keyMask) {

        return null;
    }

    /*
     * @see
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#handleMousePressedMiddle
     * (de.jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMousePressedMiddle(PointUserSpace mousePos, int keyMask) {

        return null;
    }

    /*
     * @see
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#handleMousePressedRight
     * (de.jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMousePressedRight(PointUserSpace mousePos, int keyMask) {

        return null;
    }

    /*
     * @see
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#handleMouseReleasedLeft
     * (de.jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMouseReleasedLeft(PointUserSpace mousePos, int keyMask) {

        return null;
    }

    /*
     * @see
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#handleMouseReleasedMiddle
     * (de.jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMouseReleasedMiddle(PointUserSpace mousePos, int keyMask) {

        return null;
    }

    /*
     * @see
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#handleMouseReleasedRight
     * (de.jbo.jbogx2d.base.geom.PointUserSpace, int)
     */
    @Override
    public PaintRequest handleMouseReleasedRight(PointUserSpace mousePos, int keyMask) {

        return null;
    }

    /*
     * @see
     * de.jbo.jbogx2d.ui.interaction.IMouseInteraction#handleMouseWheel(de.jbo
     * .jbogx2d.base.geom.PointUserSpace, int, int, int, int, int)
     */
    @Override
    public PaintRequest handleMouseWheel(PointUserSpace mousePos, int keyMask, int wheelRotation, int scrollAmount, int scrollType, int unitsToScroll) {

        return null;
    }

}

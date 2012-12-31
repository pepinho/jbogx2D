//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: IMouseInteraction.java
// Created: 25.01.2010 - 19:40:46
//
package de.jbo.jbogx2d.ui.interaction;

import de.jbo.jbogx2d.base.geom.PointUserSpace;
import de.jbo.jbogx2d.base.graphics.ViewHandler;

/**
 * Interface for a basic mouse-interaction
 * 
 * @author Josef Baro (jbo) <br>
 * @version 25.01.2010 jbo - created <br>
 */
public interface IMouseInteraction {
    /**
     * Initializes a mouse-interaction.
     * 
     * @param interactionHandler
     *            The interaction handler.
     * @param viewHandler
     *            The view-handler.
     */
    void init(MouseInteractionHandler interactionHandler, ViewHandler viewHandler);

    /**
     * Uninitializes this instance.
     */
    void uninit();

    /**
     * Handles the corresponding mouse-event.
     * 
     * @param mousePos
     *            The corresponding mouse-position.
     * @param keyMask
     *            The key-mask for the corresponding mouse-event.
     * @return PaintRequest.
     * @see MouseInteractionUtils#isAltGrPressed(int)
     * @see MouseInteractionUtils#isAltPressed(int)
     * @see MouseInteractionUtils#isCtrlPressed(int)
     * @see MouseInteractionUtils#isShiftPressed(int)
     */
    PaintRequest handleMousePressedLeft(PointUserSpace mousePos, int keyMask);

    /**
     * Handles the corresponding mouse-event.
     * 
     * @param mousePos
     *            The corresponding mouse-position.
     * @param keyMask
     *            The key-mask for the corresponding mouse-event.
     * @return PaintRequest.
     * @see MouseInteractionUtils#isAltGrPressed(int)
     * @see MouseInteractionUtils#isAltPressed(int)
     * @see MouseInteractionUtils#isCtrlPressed(int)
     * @see MouseInteractionUtils#isShiftPressed(int)
     */
    PaintRequest handleMousePressedMiddle(PointUserSpace mousePos, int keyMask);

    /**
     * Handles the corresponding mouse-event.
     * 
     * @param mousePos
     *            The corresponding mouse-position.
     * @param keyMask
     *            The key-mask for the corresponding mouse-event.
     * @return PaintRequest.
     * @see MouseInteractionUtils#isAltGrPressed(int)
     * @see MouseInteractionUtils#isAltPressed(int)
     * @see MouseInteractionUtils#isCtrlPressed(int)
     * @see MouseInteractionUtils#isShiftPressed(int)
     */
    PaintRequest handleMousePressedRight(PointUserSpace mousePos, int keyMask);

    /**
     * Handles the corresponding mouse-event.
     * 
     * @param mousePos
     *            The corresponding mouse-position.
     * @param keyMask
     *            The key-mask for the corresponding mouse-event.
     * @return PaintRequest.
     * @see MouseInteractionUtils#isAltGrPressed(int)
     * @see MouseInteractionUtils#isAltPressed(int)
     * @see MouseInteractionUtils#isCtrlPressed(int)
     * @see MouseInteractionUtils#isShiftPressed(int)
     */
    PaintRequest handleMouseReleasedLeft(PointUserSpace mousePos, int keyMask);

    /**
     * Handles the corresponding mouse-event.
     * 
     * @param mousePos
     *            The corresponding mouse-position.
     * @param keyMask
     *            The key-mask for the corresponding mouse-event.
     * @return PaintRequest.
     * @see MouseInteractionUtils#isAltGrPressed(int)
     * @see MouseInteractionUtils#isAltPressed(int)
     * @see MouseInteractionUtils#isCtrlPressed(int)
     * @see MouseInteractionUtils#isShiftPressed(int)
     */
    PaintRequest handleMouseReleasedMiddle(PointUserSpace mousePos, int keyMask);

    /**
     * Handles the corresponding mouse-event.
     * 
     * @param mousePos
     *            The corresponding mouse-position.
     * @param keyMask
     *            The key-mask for the corresponding mouse-event.
     * @return PaintRequest.
     * @see MouseInteractionUtils#isAltGrPressed(int)
     * @see MouseInteractionUtils#isAltPressed(int)
     * @see MouseInteractionUtils#isCtrlPressed(int)
     * @see MouseInteractionUtils#isShiftPressed(int)
     */
    PaintRequest handleMouseReleasedRight(PointUserSpace mousePos, int keyMask);

    /**
     * Handles the corresponding mouse-event.
     * 
     * @param mousePos
     *            The corresponding mouse-position.
     * @param keyMask
     *            The key-mask for the corresponding mouse-event.
     * @return PaintRequest.
     * @see MouseInteractionUtils#isAltGrPressed(int)
     * @see MouseInteractionUtils#isAltPressed(int)
     * @see MouseInteractionUtils#isCtrlPressed(int)
     * @see MouseInteractionUtils#isShiftPressed(int)
     */
    PaintRequest handleMouseDoubleclickLeft(PointUserSpace mousePos, int keyMask);

    /**
     * Handles the corresponding mouse-event.
     * 
     * @param mousePos
     *            The corresponding mouse-position.
     * @param keyMask
     *            The key-mask for the corresponding mouse-event.
     * @return PaintRequest.
     * @see MouseInteractionUtils#isAltGrPressed(int)
     * @see MouseInteractionUtils#isAltPressed(int)
     * @see MouseInteractionUtils#isCtrlPressed(int)
     * @see MouseInteractionUtils#isShiftPressed(int)
     */
    PaintRequest handleMouseDoubleclickMiddle(PointUserSpace mousePos, int keyMask);

    /**
     * Handles the corresponding mouse-event.
     * 
     * @param mousePos
     *            The corresponding mouse-position.
     * @param keyMask
     *            The key-mask for the corresponding mouse-event.
     * @return PaintRequest.
     * @see MouseInteractionUtils#isAltGrPressed(int)
     * @see MouseInteractionUtils#isAltPressed(int)
     * @see MouseInteractionUtils#isCtrlPressed(int)
     * @see MouseInteractionUtils#isShiftPressed(int)
     */
    PaintRequest handleMouseDoubleclickRight(PointUserSpace mousePos, int keyMask);

    /**
     * Handles the corresponding mouse-event.
     * 
     * @param mousePos
     *            The corresponding mouse-position.
     * @param keyMask
     *            The key-mask for the corresponding mouse-event.
     * @return PaintRequest.
     * @see MouseInteractionUtils#isAltGrPressed(int)
     * @see MouseInteractionUtils#isAltPressed(int)
     * @see MouseInteractionUtils#isCtrlPressed(int)
     * @see MouseInteractionUtils#isShiftPressed(int)
     */
    PaintRequest handleMouseDragLeft(PointUserSpace mousePos, int keyMask);

    /**
     * Handles the corresponding mouse-event.
     * 
     * @param mousePos
     *            The corresponding mouse-position.
     * @param keyMask
     *            The key-mask for the corresponding mouse-event.
     * @return PaintRequest.
     * @see MouseInteractionUtils#isAltGrPressed(int)
     * @see MouseInteractionUtils#isAltPressed(int)
     * @see MouseInteractionUtils#isCtrlPressed(int)
     * @see MouseInteractionUtils#isShiftPressed(int)
     */
    PaintRequest handleMouseDragMiddle(PointUserSpace mousePos, int keyMask);

    /**
     * Handles the corresponding mouse-event.
     * 
     * @param mousePos
     *            The corresponding mouse-position.
     * @param keyMask
     *            The key-mask for the corresponding mouse-event.
     * @return PaintRequest.
     * @see MouseInteractionUtils#isAltGrPressed(int)
     * @see MouseInteractionUtils#isAltPressed(int)
     * @see MouseInteractionUtils#isCtrlPressed(int)
     * @see MouseInteractionUtils#isShiftPressed(int)
     */
    PaintRequest handleMouseDragRight(PointUserSpace mousePos, int keyMask);

    /**
     * Handles the corresponding mouse-event.
     * 
     * @param mousePos
     *            The corresponding mouse-position.
     * @param keyMask
     *            The key-mask for the corresponding mouse-event.
     * @return PaintRequest.
     * @see MouseInteractionUtils#isAltGrPressed(int)
     * @see MouseInteractionUtils#isAltPressed(int)
     * @see MouseInteractionUtils#isCtrlPressed(int)
     * @see MouseInteractionUtils#isShiftPressed(int)
     */
    PaintRequest handleMouseMoved(PointUserSpace mousePos, int keyMask);

    /**
     * Handles the corresponding mouse-event.
     * 
     * @param mousePos
     *            The corresponding mouse-position.
     * @param keyMask
     *            The key-mask for the corresponding mouse-event.
     * @param wheelRotation
     *            The number of "clicks" the mouse wheel was rotated. Negative
     *            values if the mouse wheel was rotated up/away from the user,
     *            and positive values if the mouse wheel was rotated
     *            down/towards the user.
     * @param scrollAmount
     *            The number of units that should be scrolled per click of mouse
     *            wheel rotation. Only valid if <code>scrollType</code> returns
     *            <code>MouseWheelEvent.WHEEL_UNIT_SCROLL</code>
     * @param scrollType
     *            the type of scrolling that should take place in response to
     *            this event. This is determined by the native platform. Legal
     *            values are:
     *            <ul>
     *            <li>MouseWheelEvent.WHEEL_UNIT_SCROLL
     *            <li>MouseWheelEvent.WHEEL_BLOCK_SCROLL
     *            </ul>
     * @param unitsToScroll
     *            The number of units to scroll based on the direction and
     *            amount of mouse wheel rotation, and on the wheel scrolling
     *            settings of the native platform.
     * @return PaintRequest.
     * @see MouseInteractionUtils#isAltGrPressed(int)
     * @see MouseInteractionUtils#isAltPressed(int)
     * @see MouseInteractionUtils#isCtrlPressed(int)
     * @see MouseInteractionUtils#isShiftPressed(int)
     */
    PaintRequest handleMouseWheel(PointUserSpace mousePos, int keyMask, int wheelRotation, int scrollAmount, int scrollType, int unitsToScroll);

    /**
     * Handles the corresponding mouse-event.
     * 
     * @param mousePos
     *            The corresponding mouse-position.
     * @param keyMask
     *            The key-mask for the corresponding mouse-event.
     * @return PaintRequest.
     * @see MouseInteractionUtils#isAltGrPressed(int)
     * @see MouseInteractionUtils#isAltPressed(int)
     * @see MouseInteractionUtils#isCtrlPressed(int)
     * @see MouseInteractionUtils#isShiftPressed(int)
     */
    PaintRequest handleMouseEntered(PointUserSpace mousePos, int keyMask);

    /**
     * Handles the corresponding mouse-event.
     * 
     * @param mousePos
     *            The corresponding mouse-position.
     * @param keyMask
     *            The key-mask for the corresponding mouse-event.
     * @return PaintRequest.
     * @see MouseInteractionUtils#isAltGrPressed(int)
     * @see MouseInteractionUtils#isAltPressed(int)
     * @see MouseInteractionUtils#isCtrlPressed(int)
     * @see MouseInteractionUtils#isShiftPressed(int)
     */
    PaintRequest handleMouseExited(PointUserSpace mousePos, int keyMask);
}

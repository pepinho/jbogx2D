//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: MouseInteractionUtils.java
// Created: 25.01.2010 - 20:48:23
//
package de.jbo.jbogx2d.ui.interaction;

import java.awt.event.MouseEvent;

/**
 * Utility methods and constants for mouse-interaction handling.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 25.01.2010 jbo - created <br>
 */
public final class MouseInteractionUtils {

    /** Mask for CTRL key pressed. */
    public static final int MASK_CTRL = 0x1;

    /** Mask for ALT key pressed. */
    public static final int MASK_ALT = 0x2;

    /** Mask for SHIFT key pressed. */
    public static final int MASK_SHIFT = 0x4;

    /** Mask for ALT-GR key pressed. */
    public static final int MASK_ALT_GR = 0x8;

    /**
     * Constructor.
     */
    private MouseInteractionUtils() {

    }

    /**
     * @param evt
     *            The native mouse event to be handled.
     * @return The corresponding mask for the mouse-event.
     * @see #MASK_ALT
     * @see #MASK_ALT_GR
     * @see #MASK_CTRL
     * @see #MASK_SHIFT
     */
    public static int getKeyMask(MouseEvent evt) {
        int mask = 0;
        if (evt.isAltDown()) {
            mask |= MASK_ALT;
        }
        if (evt.isAltGraphDown()) {
            mask |= MASK_ALT_GR;
        }
        if (evt.isControlDown()) {
            mask |= MASK_CTRL;
        }
        if (evt.isShiftDown()) {
            mask |= MASK_SHIFT;
        }
        return mask;
    }

    /**
     * Checks if the given bit is set in the given bit-mask.
     * 
     * @param mask
     *            The bit-mask to be checked.
     * @param bit
     *            The bit to be checked.
     * @return True if the bit is set, False otherwise.
     */
    private static boolean isMaskSet(int mask, int bit) {
        return ((mask & bit) == bit);
    }

    /**
     * Checks if the corresponding key is pressed.
     * 
     * @param mask
     *            The key-mask to be checked.
     * @return True if the key is pressed, False otherwise.
     * @see #getKeyMask(MouseEvent)
     */
    public static boolean isShiftPressed(int mask) {
        return isMaskSet(mask, MASK_SHIFT);
    }

    /**
     * Checks if the corresponding key is pressed.
     * 
     * @param mask
     *            The key-mask to be checked.
     * @return True if the key is pressed, False otherwise.
     * @see #getKeyMask(MouseEvent)
     */
    public static boolean isAltPressed(int mask) {
        return isMaskSet(mask, MASK_ALT);
    }

    /**
     * Checks if the corresponding key is pressed.
     * 
     * @param mask
     *            The key-mask to be checked.
     * @return True if the key is pressed, False otherwise.
     * @see #getKeyMask(MouseEvent)
     */
    public static boolean isAltGrPressed(int mask) {
        return isMaskSet(mask, MASK_ALT_GR);
    }

    /**
     * Checks if the corresponding key is pressed.
     * 
     * @param mask
     *            The key-mask to be checked.
     * @return True if the key is pressed, False otherwise.
     * @see #getKeyMask(MouseEvent)
     */
    public static boolean isCtrlPressed(int mask) {
        return isMaskSet(mask, MASK_CTRL);
    }

}

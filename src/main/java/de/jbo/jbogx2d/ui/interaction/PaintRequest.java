//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: PaintRequest.java
// Created: 17.02.2010 - 21:47:36
//
package de.jbo.jbogx2d.ui.interaction;

import java.util.Collection;
import java.util.TreeSet;

import de.jbo.jbogx2d.base.geom.BoundsUserSpace;

/**
 * Defines paint-request state for mouse-interaction handlers.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 17.02.2010 jbo - created <br>
 */
public class PaintRequest {
    /**
     * True if repaint is requested, False otherwise.
     */
    private boolean isRepaintRequested = false;

    /**
     * Modified bounds for repaint. If null, the complete view area is to be
     * repainted.
     */
    private BoundsUserSpace boundsModified = null;

    /**
     * XOR elements to be rendered.
     */
    private final Collection<IMouseInteractionXorElement> xorElements = new TreeSet<IMouseInteractionXorElement>();

    /**
     * True if xor-element is to re resetted again after paint.
     */
    private boolean isResetXor = false;

    /**
     * Creates a new instance.
     */
    public PaintRequest() {

    }

    /**
     * @return the isResetXor
     */
    public final boolean isResetXor() {
        return isResetXor;
    }

    /**
     * @param isResetXor
     *            the isResetXor to set
     */
    public final void setResetXor(boolean isResetXor) {
        this.isResetXor = isResetXor;
    }

    /**
     * @return the isRepaintRequested
     */
    public final boolean isRepaintRequested() {
        return isRepaintRequested;
    }

    /**
     * @return the boundsModified
     */
    public final BoundsUserSpace getBoundsModified() {
        return boundsModified;
    }

    /**
     * @param isRepaintRequested
     *            the isRepaintRequested to set
     */
    public final void setRepaintRequested(boolean isRepaintRequested) {
        this.isRepaintRequested = isRepaintRequested;
    }

    /**
     * @param boundsModified
     *            the boundsModified to set
     */
    public final void setBoundsModified(BoundsUserSpace boundsModified) {
        if (boundsModified != null) {
            this.boundsModified = new BoundsUserSpace(boundsModified.x, boundsModified.y, boundsModified.width, boundsModified.height);
        } else {
            this.boundsModified = null;
        }
    }

    /**
     * Adds the given xor-element.
     * 
     * @param xor
     *            Element to be added.
     */
    public final void addXorElement(IMouseInteractionXorElement xor) {
        xorElements.add(xor);
    }

    /**
     * Removes the given xor-element.
     * 
     * @param xor
     *            Element to be removed.
     */
    public final void removeXorElement(IMouseInteractionXorElement xor) {
        xorElements.remove(xor);
    }

    /**
     * @return the xorElements
     */
    public final Collection<IMouseInteractionXorElement> getXorElements() {
        return xorElements;
    }

}

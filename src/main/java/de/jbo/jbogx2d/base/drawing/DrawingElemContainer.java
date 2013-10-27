//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: DrawingElemContainer.java
// Created: 29.02.2004 - 15:26:33
//

package de.jbo.jbogx2d.base.drawing;

import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.elements.ElemContainer;

/**
 * Implements the element container that is used as root element for a drawing.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 29.02.2004: jbo created <br>
 */
public class DrawingElemContainer extends ElemContainer {
    /** The drawing we are stored in. */
    private Drawing drawing = null;

    /**
     * Creates a new instance.
     * 
     * @param d
     *            The drawing we are stored in.
     */
    protected DrawingElemContainer(Drawing d) {
        super();
        drawing = d;
    }

    /**
     * Uninitializes this instance. The reference to the drawing is deleted and
     * all child elements are removed.
     */
    public void uninit() {
        drawing = null;
        removeAllElements();
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.ElemBase#getLevel()
     */
    @Override
    public short getLevel() {
        return Drawing.LEVEL_ROOT;
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.ElemBase#getParent()
     */
    @Override
    public ElemBase getParent() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.jbo.jbogx2d.base.elements.ElemBase#getDrawing()
     */
    @Override
    public Drawing getDrawing() {
        return drawing;
    }

}
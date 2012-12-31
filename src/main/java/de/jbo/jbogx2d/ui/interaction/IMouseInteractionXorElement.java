//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: IMouseInteractionXorElement.java
// Created: 14.02.2010 - 21:47:34
//
package de.jbo.jbogx2d.ui.interaction;

import de.jbo.jbogx2d.base.graphics.ViewContext;

/**
 * Interface for XOR elements rendered via the mouse-interaction framework.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 14.02.2010 jbo - created <br>
 */
public interface IMouseInteractionXorElement extends Comparable<IMouseInteractionXorElement> {
    /**
     * Paints the element to the given view-context.
     * 
     * @param viewContext
     *            Context to paint to.
     */
    void paint(ViewContext viewContext);
}

//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: AttribBase.java
// Created: 28.02.2004 - 18:46:29
//

package de.jbo.jbogx2d.base.attrib;

import de.jbo.jbogx2d.base.elements.model.ElemModel;

/**
 * Implements the abstract base class for the graphical attributes of the
 * available elements.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 28.02.2004: jbo created <br>
 */
interface AttribBase {
    /**
     * Updates the attributes from logical values to system values used for
     * drawing. <br>
     * This method has to be triggered everytime attributes of an element have
     * been changed in order to the changes to be shown during repaint.
     * 
     * @param model
     *            The model to be updated.
     */
    public abstract void update(ElemModel model);

}
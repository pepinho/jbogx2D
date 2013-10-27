//
// Copyright (c) 2010 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: IZoomListener.java
// Created: 07.02.2010 - 18:11:45
//
package de.jbo.jbogx2d.base.graphics;

import de.jbo.jbogx2d.base.geom.BoundsUserSpace;

/**
 * Defines the interface for a zoom-event listener.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 07.02.2010 jbo - created <br>
 */
public interface IZoomListener {
    /**
     * Triggered before a zoom is performed.
     * 
     * @param source
     *            The event-source view-handler.
     * @param boundsBefore
     *            The visible bounds before the zoom-operation.
     * @param boundsAfter
     *            The visible bounds the zoom is performed to.
     */
    void onZoomStart(ViewHandler source, BoundsUserSpace boundsBefore, BoundsUserSpace boundsAfter);

    /**
     * Triggered after a zoom was performed.
     * 
     * @param source
     *            The event-source view-handler.
     * @param boundsBefore
     *            The visible bounds before the zoom-operation.
     * @param boundsAfter
     *            The visible bounds the zoom is performed to.
     */
    void onZoomEnd(ViewHandler source, BoundsUserSpace boundsBefore, BoundsUserSpace boundsAfter);
}

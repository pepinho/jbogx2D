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
 * Defines the interface for a scroll-event listener.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 07.02.2010 jbo - created <br>
 */
public interface IScrollListener {
    /**
     * Triggered before a scroll is performed.
     * 
     * @param source
     *            The event-source view-handler.
     * @param boundsBefore
     *            The visible bounds before the scroll-operation.
     * @param boundsAfter
     *            The visible bounds the scroll is performed to.
     */
    void onScrollStart(ViewHandler source, BoundsUserSpace boundsBefore, BoundsUserSpace boundsAfter);

    /**
     * Triggered after a scroll was performed.
     * 
     * @param source
     *            The event-source view-handler.
     * @param boundsBefore
     *            The visible bounds before the scroll-operation.
     * @param boundsAfter
     *            The visible bounds the scroll is performed to.
     */
    void onScrollEnd(ViewHandler source, BoundsUserSpace boundsBefore, BoundsUserSpace boundsAfter);
}

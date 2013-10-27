//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElementTraverserCollectByBounds.java
// Created: 06.03.2004 - 22:40:57
//

package de.jbo.jbogx2d.base.drawing.traversing;

import java.util.LinkedList;

import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.elements.ElemContainer;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;

/**
 * Implements an element traverser that collects all elements matching a given
 * bounds-area.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 06.03.2004: jbo created <br>
 */
public class ElementTraverserCollectByBounds extends ElementTraverserCollectElements {
    /** The bounds we are looking for matching element for. */
    private final BoundsUserSpace bounds = new BoundsUserSpace();

    /**
     * True if container are to collected, False if only leaf-elements are to be
     * collected.
     */
    private boolean isCollectContainers = false;

    /**
     * True is intersecting elements are to be found, otherwise only those
     * elements are found that reside completely within the given bounds.
     */
    private boolean isCollectIntersections = false;

    /**
     * Initializes this instance.
     * 
     * @param elementsCollection
     *            The destination list to store collected elements.
     * @param b
     *            The bounds to check elements for.
     * @param collectContainers
     *            Indicates if element-containers should also be collected.
     * @param collectIntersections
     *            Indicates if elements should be collected, that intersect the
     *            given bounds or only elements that reside completely within
     *            the given bounds.
     */
    public ElementTraverserCollectByBounds(final LinkedList<ElemBase> elementsCollection, final BoundsUserSpace b, final boolean collectContainers, final boolean collectIntersections) {
        super(elementsCollection);
        bounds.set(b);
        this.isCollectContainers = collectContainers;
        this.isCollectIntersections = collectIntersections;
        setVisitContainerChilds(true);
        setDirectionFirstToLast(true);
        setHandleContainerBeforeChilds(false);
    }

    /*
     * @see de.jbo.jbogx2d.base.drawing.traversing.ElementTraverser#init()
     */
    @Override
    public boolean init() {
        return true;
    }

    /*
     * @see de.jbo.jbogx2d.base.drawing.traversing.ElementTraverser#close()
     */
    @Override
    public boolean close() {
        return true;
    }

    /*
     * @seede.jbo.jbogx2d.base.drawing.traversing.ElementTraverser#
     * handleBeforeContainerChilds(de.jbo.jbogx2d.base.elements.ElemContainer)
     */
    @Override
    public short handleBeforeContainerChilds(ElemContainer container) {
        return ElementTraverser.CONTINUE;
    }

    /*
     * @seede.jbo.jbogx2d.base.drawing.traversing.ElementTraverser#
     * handleAfterContainerChilds(de.jbo.jbogx2d.base.elements.ElemContainer)
     */
    @Override
    public short handleAfterContainerChilds(ElemContainer container) {
        return (isCollectContainers) ? ElementTraverser.CONTINUE : ElementTraverser.STOP_CONTAINER;
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.drawing.traversing.ElementTraverser#handleElement
     * (de.jbo.jbogx2d.base.elements.ElemBase)
     */
    @Override
    public short handleElement(ElemBase elem) {
        BoundsUserSpace elemBounds = new BoundsUserSpace();

        elem.getBounds(elemBounds);

        if (isCollectIntersections) {
            if (elemBounds.intersects(bounds)) {
                storeElement(elem);
            }
        } else {
            if (bounds.contains(elemBounds)) {
                storeElement(elem);
            }
        }

        return ElementTraverser.CONTINUE;
    }

}
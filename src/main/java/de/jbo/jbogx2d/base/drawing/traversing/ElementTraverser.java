//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElementTraverser.java
// Created: 03.03.2004 - 18:53:30
//

package de.jbo.jbogx2d.base.drawing.traversing;

import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.elements.ElemContainer;

/**
 * Implements the base class for an element traverser that basically works as a
 * treewalker traversing en element-tree hierarchy. On each element the
 * handle-method is triggered, i.o. to perform specific actions on elements,
 * e.g. collecting elements based on searched values.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 03.03.2004: jbo created <br>
 */
public abstract class ElementTraverser {
    /** Signalizing a stop of traversing after handling an element. */
    public static final short STOP = 0;

    /** Signalizing the continue state after handling an element. */
    public static final short CONTINUE = 1;

    /** Signalizing that the traversing is continued after handling an element. */
    public static final short STOP_CONTAINER = 2;

    /**
     * Indicates if recursive traversing is to be performed on container
     * elements (default).
     */
    private boolean isVisitContainerChilds = true;

    /**
     * Indicates if a container element is being handled before its child
     * elements are being handled or vice-versa (default).
     */
    private boolean isHandleContainerBeforeChilds = false;

    /**
     * Indicates if the traversing is done from first to last element or
     * backwards (default).
     */
    private boolean isDirectionFirstToLast = false;

    /**
     * Creates a new instance.
     */
    protected ElementTraverser() {
        super();
    }

    /**
     * Initializes the traverser at startup.
     * 
     * @return True if successfully initialized.
     */
    public abstract boolean init();

    /**
     * Uninitializes the traverser at the end.
     * 
     * @return True if successfully uninitialized.
     */
    public abstract boolean close();

    /**
     * Handles the given element container before its childs are visited.
     * 
     * @param container
     *            The container to be handled.
     * @return The state of the action. Depending on this, the following
     *         traversing is being handled.
     * @see #CONTINUE Continues the traversing.
     * @see #STOP Stops the complete traversing after this method.
     * @see #STOP_CONTAINER Stops handling this container, so that its childs
     *      are not being handled recursively. The traversing will be continued
     *      on the next element on the containers level.
     */
    public abstract short handleBeforeContainerChilds(ElemContainer container);

    /**
     * Handles the given element container after its childs were visited.
     * 
     * @param container
     *            The container to be handled.
     * @return The state of the action. Depending on this, the following
     *         traversing is being handled.
     * @see #CONTINUE Continues the traversing.
     * @see #STOP Stops the complete traversing after this method.
     * @see #STOP_CONTAINER Stops handling this container. The traversing will
     *      be continued on the next element on the containers level.
     */
    public abstract short handleAfterContainerChilds(ElemContainer container);

    /**
     * Handles the given element.
     * 
     * @param elem
     *            The element to be handled.
     * @return The state of the action. Depending on this, the following
     *         traversing is being handled.
     * @see #CONTINUE Continues the traversing on the next element on this
     *      level.
     * @see #STOP Stops the complete traversing after this method.
     */
    public abstract short handleElement(ElemBase elem);

    /**
     * @return True if traversing from first to last element, False otherwise.
     */
    public boolean isDirectionFirstToLast() {
        return isDirectionFirstToLast;
    }

    /**
     * @return True if a container is handled before its child elements, False
     *         otherwise.
     */
    public boolean isHandleContainerBeforeChilds() {
        return isHandleContainerBeforeChilds;
    }

    /**
     * @return True if container children are visited recursively, False
     *         otherwise.
     */
    public boolean isVisitContainerChilds() {
        return isVisitContainerChilds;
    }

    /**
     * @param b
     *            The the traversing direction.
     * @see #isDirectionFirstToLast()
     */
    public void setDirectionFirstToLast(boolean b) {
        isDirectionFirstToLast = b;
    }

    /**
     * @param b
     *            The container handling method.
     * @see #isHandleContainerBeforeChilds()
     */
    public void setHandleContainerBeforeChilds(boolean b) {
        isHandleContainerBeforeChilds = b;
    }

    /**
     * @param b
     *            True if children are to be traversed recursively, False
     *            otherwise.
     * @see #isVisitContainerChilds()
     */
    public void setVisitContainerChilds(boolean b) {
        isVisitContainerChilds = b;
    }

}
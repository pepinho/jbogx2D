//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElementTraverserCollectElements.java
// Created: 03.03.2004 - 19:15:55
//

package de.jbo.jbogx2d.base.drawing.traversing;

import java.util.LinkedList;
import java.util.List;

import de.jbo.jbogx2d.base.elements.ElemBase;

/**
 * Implements an element traverser that can collect elements based on specific
 * attributes.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 03.03.2004: jbo created <br>
 */
public abstract class ElementTraverserCollectElements extends ElementTraverser {
    /**
     * True if elements are store at the end of the result-list or at the
     * beginning.
     */
    private boolean isStoreElementsAtEnd = true;

    /** The result list where collected elements are being stored. */
    private LinkedList<ElemBase> elementsList = null;

    /**
     * Creates a new instance.
     * 
     * @param elementsCollection
     *            The result list for collected elements.
     */
    protected ElementTraverserCollectElements(List<ElemBase> elementsCollection) {
        super();
        setElementsList(elementsCollection);
    }

    /**
     * Stores the given element in the result list.
     * 
     * @param element
     *            The element to be stored.
     * @see #isStoreElementsAtEnd()
     */
    protected void storeElement(ElemBase element) {
        if (isStoreElementsAtEnd) {
            elementsList.addLast(element);
        } else {
            elementsList.addFirst(element);
        }
    }

    /**
     * @return The list with collected elements.
     */
    public List<ElemBase> getElementsList() {
        return elementsList;
    }

    /**
     * @return True if collected elements are stored at the end of the result
     *         list (FIFO), False if stored at the beginning (LIFO).
     */
    public boolean isStoreElementsAtEnd() {
        return isStoreElementsAtEnd;
    }

    /**
     * @param list
     *            Sets the result list to store collected elements in.
     */
    public void setElementsList(List<ElemBase> list) {
        if (! (list instanceof LinkedList)) {
            throw new IllegalArgumentException("Please provide a list of type 'LinkedList'");
        }
        elementsList = (LinkedList<ElemBase>)list;
    }

    /**
     * @param b
     *            True if collected elements are stored at the end of the result
     *            list (FIFO), False if stored at the beginning (LIFO).
     */
    public void setStoreElementsAtEnd(boolean b) {
        isStoreElementsAtEnd = b;
    }

}
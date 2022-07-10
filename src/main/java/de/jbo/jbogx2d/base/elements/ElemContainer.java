//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemContainer.java
// Created: 29.02.2004 - 15:05:56
//

package de.jbo.jbogx2d.base.elements;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import de.jbo.jbogx2d.base.drawing.Drawing;
import de.jbo.jbogx2d.base.drawing.traversing.ElementTraverser;
import de.jbo.jbogx2d.base.elements.model.ElemModel;
import de.jbo.jbogx2d.base.elements.model.ElemModelContainer;
import de.jbo.jbogx2d.base.elements.view.ElemView;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;

/**
 * Implements an element container.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 29.02.2004: jbo created <br>
 */
public class ElemContainer extends ElemBase {
    /** List of child elements. */
    private LinkedList<ElemBase> childList = null;

    /**
     * Creates a new instance.
     */
    public ElemContainer() {
        super();
        childList = ((ElemModelContainer) getModel()).getChildList();
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.ElemBase#createModel()
     */
    @Override
    protected ElemModel createModel() {
        return new ElemModelContainer(this);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.ElemBase#createView(de.jbo.jbogx2d.base.
     * elements.model.ElemModel)
     */
    @Override
    protected ElemView createView(ElemModel myModel) {
        return null;
    }

    /**
     * Adds the given element at the end of the child-list.
     * 
     * @param elem
     *            The element to be added.
     */
    public synchronized void addElemLast(ElemBase elem) {
        elem.removeFromParent();
        childList.add(elem);
        elem.setParent(this);
        Drawing d = getDrawing();
        if (d != null) {
            BoundsUserSpace bounds = new BoundsUserSpace();
            elem.getBounds(bounds);
            d.fireModificationEvent(bounds);
        }
    }

    /**
     * Adds the list of elements at the end of the child-list.
     * 
     * @param elems
     *            The list of elements to be added.
     */
    public void addElemsLast(List<ElemBase> elems) {
        ListIterator<ElemBase> iterator = elems.listIterator();
        ElemBase elem = null;

        while (iterator.hasNext()) {
            elem = iterator.next();
            addElemLast(elem);
        }
    }

    /**
     * Adds the given element at the beginning of the child-list.
     * 
     * @param elem
     *            The element to be added.
     */
    public synchronized void addElemFirst(ElemBase elem) {
        elem.removeFromParent();
        childList.addFirst(elem);
        elem.setParent(this);
        Drawing d = getDrawing();
        if (d != null) {
            BoundsUserSpace bounds = new BoundsUserSpace();
            elem.getBounds(bounds);
            d.fireModificationEvent(bounds);
        }
    }

    /**
     * Adds the list of elements at the beginning of the child-list.
     * 
     * @param elems
     *            The list of elements to be added.
     */
    public void addElemsFirst(List<ElemBase> elems) {
        ListIterator<ElemBase> iterator = elems.listIterator();
        ElemBase elem = null;

        // In order to add the given list in the correct order at the first
        // position
        // we have to traverse the list backwards...
        while (iterator.hasPrevious()) {
            elem = iterator.previous();
            addElemFirst(elem);
        }
    }

    /**
     * Adds the given element at the given position in the child-list.
     * 
     * @param elem
     *            The element to be added.
     * @param position
     *            The position to add the element at.
     */
    public synchronized void addElemAt(ElemBase elem, int position) {
        elem.removeFromParent();
        childList.add(position, elem);
        elem.setParent(this);
        Drawing d = getDrawing();
        if (d != null) {
            BoundsUserSpace bounds = new BoundsUserSpace();
            elem.getBounds(bounds);
            d.fireModificationEvent(bounds);
        }
    }

    /**
     * Adds the given list elements at the given position in the child-list.
     * 
     * @param elems
     *            The list of element to be added.
     * @param position
     *            The position to add the elements at.
     */
    public void addElemsAt(List<ElemBase> elems, int position) {
        ListIterator<ElemBase> iterator = elems.listIterator();
        ElemBase elem = null;

        // In order to add the given list in the correct order at the first
        // position
        // we have to traverse the list backwards...
        while (iterator.hasPrevious()) {
            elem = iterator.previous();
            addElemAt(elem, position);
        }
    }

    /**
     * Removes the given element.
     * 
     * @param elem
     *            The element to be removed.
     */
    public synchronized void removeElem(ElemBase elem) {
        childList.remove(elem);
        elem.setParent(null);
        Drawing d = getDrawing();
        if (d != null) {
            BoundsUserSpace bounds = new BoundsUserSpace();
            elem.getBounds(bounds);
            d.fireModificationEvent(bounds);
        }
    }

    /**
     * Removes the given elements.
     * 
     * @param elems
     *            The elements to be removed.
     */
    public synchronized void removeElems(List<ElemBase> elems) {
        ListIterator<ElemBase> iterator = elems.listIterator();
        ElemBase elem = null;

        while (iterator.hasNext()) {
            elem = iterator.next();
            removeElem(elem);
        }
    }

    /**
     * Removes all child elements.
     */
    public synchronized void removeAllElements() {
        removeElems(childList);
    }

    private boolean canIterate(ListIterator<ElemBase> iterator, boolean isFirstToLast) {
        return (isFirstToLast) ? iterator.hasNext() : iterator.hasPrevious();
    }

    private short iterate(ElementTraverser traverser) {
        boolean isFirstToLast = traverser.isDirectionFirstToLast();
        short state = ElementTraverser.CONTINUE;
        ListIterator<ElemBase> iterator = childList.listIterator();

        while (canIterate(iterator, isFirstToLast) && (state != ElementTraverser.STOP)) {
            ElemBase elem = (isFirstToLast) ? iterator.next() : iterator.previous();
            state = elem.traverse(traverser);
        }
        return state;
    }

    /*
     * @see
     * 
     * de.jbo.jbogx2d.base.elements.ElemBase#traverse(de.jbo.jbogx2d.base.
     * drawing .traversing.ElementTraverser)
     */
    @Override
    public short traverse(ElementTraverser traverser) {
        short state;

        if (traverser.isHandleContainerBeforeChilds()) {
            state = traverser.handleElement(this);
            if (state != ElementTraverser.CONTINUE) {
                return evaluateTraversionState(state);
            }
        }

        state = traverser.handleBeforeContainerChilds(this);
        if (state != ElementTraverser.CONTINUE) {
            return evaluateTraversionState(state);
        }

        // Shall we visit our childs?
        if (traverser.isVisitContainerChilds()) {
            state = iterate(traverser);
        }
        if (state == ElementTraverser.CONTINUE) {
            state = traverser.handleAfterContainerChilds(this);
        }

        if ((state == ElementTraverser.CONTINUE) && !traverser.isHandleContainerBeforeChilds()) {
            state = traverser.handleElement(this);
        }

        return evaluateTraversionState(state);
    }

    private short evaluateTraversionState(short state) {
        return (state == ElementTraverser.STOP_CONTAINER) ? ElementTraverser.CONTINUE : state;
    }

    /**
     * @return The child list.
     */
    public List<ElemBase> getElems() {
        return childList;
    }
}
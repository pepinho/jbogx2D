//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ViewLayer.java
// Created: 01.03.2004 - 17:55:24
//

package de.jbo.jbogx2d.base.drawing;

import java.util.Collection;
import java.util.LinkedList;

import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.geom.AffineTransformX;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.graphics.ViewElem2QuadTreeTraverser;
import de.jbo.jbogx2d.base.graphics.ViewQuadTreeNode;

/**
 * Implements a drawing layer. A drawing can consist of multiple layers. All
 * layers can contain elements. Rendering is accomplished in z-order from lowest
 * to upper-most layer.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 01.03.2004: jbo created <br>
 */
public class DrawingLayer {
    /** Default name of a newly created layer. */
    public static final String NAME_DEFAULT = "Default";

    /**
     * Indicates if this layer is currently filtered. Filtered layers and its
     * elements are not rendered and thus are invisible.
     */
    private boolean isFiltered = false;

    /** The name of this layer instance. */
    private String name = null;

    /** The element container. */
    private DrawingElemContainer elements = null;

    /** The quad-tree root handling the elements. */
    private ViewQuadTreeNode quadTreeRoot = null;

    /**
     * Defines the maximum quadrant-tree depth, e.g. depth of 8 defines 64
     * Quadrants in total.
     */
    private final int maxQuadrantTreeDepth = 8;

    /**
     * Creates a new layer.
     * 
     * @param n
     *            The name to be set.
     * @param d
     *            The corresponding drawing instance.
     */
    protected DrawingLayer(final String n, final Drawing d) {
        super();
        name = n;
        elements = new DrawingElemContainer(d);
    }

    /**
     * Uninitializes this instance.
     */
    public void uninit() {
        elements.uninit();
        elements = null;
    }

    /**
     * Initializes the underlying quad-tree handling the elements.
     */
    public void initQuadTree() {
        BoundsUserSpace drawingUserSpace = new BoundsUserSpace();
        Drawing drawing = elements.getDrawing();
        drawing.getUserSpaceBounds(drawingUserSpace);

        if (quadTreeRoot == null) {
            quadTreeRoot = new ViewQuadTreeNode(maxQuadrantTreeDepth, null, drawingUserSpace);
        } else {
            quadTreeRoot.reset(drawingUserSpace);
        }

        ViewElem2QuadTreeTraverser traverser = new ViewElem2QuadTreeTraverser(quadTreeRoot);
        drawing.traverse(traverser, this);
    }

    /**
     * Returns the collection of elements lying in the given bounds.
     * 
     * @param bounds
     *            The bounds to collect the elements for.
     * @return The elements found for the given bounds.
     */
    public Collection<ElemBase> getElementsByBounds(final BoundsUserSpace bounds) {
        return quadTreeRoot.getElems(bounds);
    }

    /**
     * @return True if currently filtered, False otherwise.
     */
    public boolean isFiltered() {
        return isFiltered;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param value
     *            the isFiltered to set
     */
    public void setFiltered(final boolean value) {
        this.isFiltered = value;
    }

    /**
     * Adds an element to the given position.
     * 
     * @param elem
     *            The element to be added.
     * @param position
     *            The position to add the element to.
     * @see de.jbo.jbogx2d.base.elements.ElemContainer#addElemAt(de.jbo.jbogx2d.base.elements.ElemBase,
     *      int)
     */
    public void addElemAt(final ElemBase elem, final int position) {
        elements.addElemAt(elem, position);
    }

    /**
     * Add the given element to the 1st positon.
     * 
     * @param elem
     *            The element to be added.
     * @see de.jbo.jbogx2d.base.elements.ElemContainer#addElemFirst(de.jbo.jbogx2d.base.elements.ElemBase)
     */
    public void addElemFirst(final ElemBase elem) {
        elements.addElemFirst(elem);
    }

    /**
     * Add the given element at the last position.
     * 
     * @param elem
     *            The element to be added.
     * @see de.jbo.jbogx2d.base.elements.ElemContainer#addElemLast(de.jbo.jbogx2d.base.elements.ElemBase)
     */
    public void addElemLast(final ElemBase elem) {
        elements.addElemLast(elem);
    }

    /**
     * Adds the given elements at the given position.
     * 
     * @param elems
     *            The elements to be added.
     * @param position
     *            The position to add the elements at.
     * @see de.jbo.jbogx2d.base.elements.ElemContainer#addElemsAt(java.util.LinkedList,
     *      int)
     */
    public void addElemsAt(final LinkedList<ElemBase> elems, final int position) {
        elements.addElemsAt(elems, position);
    }

    /**
     * Adds the given elements at the 1st position.
     * 
     * @param elems
     *            The elements to be added.
     * @see de.jbo.jbogx2d.base.elements.ElemContainer#addElemsFirst(java.util.LinkedList)
     */
    public void addElemsFirst(final LinkedList<ElemBase> elems) {
        elements.addElemsFirst(elems);
    }

    /**
     * Adds the given elements at the last position.
     * 
     * @param elems
     *            The elements to be added.
     * @see de.jbo.jbogx2d.base.elements.ElemContainer#addElemsLast(java.util.LinkedList)
     */
    public void addElemsLast(final LinkedList<ElemBase> elems) {
        elements.addElemsLast(elems);
    }

    /**
     * Removes the given element.
     * 
     * @param elem
     *            The element to be removed.
     * @see de.jbo.jbogx2d.base.elements.ElemContainer#removeElem(de.jbo.jbogx2d.base.elements.ElemBase)
     */
    public void removeElem(final ElemBase elem) {
        elements.removeElem(elem);
    }

    /**
     * Removes the given elements.
     * 
     * @param elems
     *            The elements to be removed.
     * @see de.jbo.jbogx2d.base.elements.ElemContainer#removeElems(java.util.LinkedList)
     */
    public void removeElems(final LinkedList<ElemBase> elems) {
        elements.removeElems(elems);
    }

    /**
     * Transforms the layer's elements.
     * 
     * @param transformation
     *            The transformation to be applied.
     * @see de.jbo.jbogx2d.base.elements.ElemBase#transform(de.jbo.jbogx2d.base.geom.AffineTransformX)
     */
    public void transform(final AffineTransformX transformation) {
        elements.transform(transformation);
    }

    /**
     * @return The layer's elements
     * @see de.jbo.jbogx2d.base.elements.ElemContainer#getElems()
     */
    public LinkedList<ElemBase> getElems() {
        return elements.getElems();
    }

    /**
     * The layer's quad-tree root.
     * 
     * @return The quad-tree root node.
     */
    public ViewQuadTreeNode getQuadTree() {
        return quadTreeRoot;
    }
}
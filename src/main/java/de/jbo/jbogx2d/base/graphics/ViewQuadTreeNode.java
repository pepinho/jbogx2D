//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ViewQuadrant.java
// Created: 01.03.2004 - 18:25:31
//

package de.jbo.jbogx2d.base.graphics;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.util.collection.ArrayListX;

/**
 * @author Josef Baro (jbo) <br>
 * @version 1.0 01.03.2004: jbo created <br>
 */
public class ViewQuadTreeNode {
    /** The user-space bounds of the node's quadrant. */
    private final BoundsUserSpace quadrantBounds = new BoundsUserSpace();

    /** List of elements stored directly under this node. */
    protected LinkedList<ElemBase> elemList = new LinkedList<ElemBase>();

    /** The parent tree-node. */
    private ViewQuadTreeNode parent = null;

    /** North-eastern quadrant. */
    private ViewQuadTreeNode quadrantNE = null;

    /** North-western quadrant. */
    private ViewQuadTreeNode quadrantNW = null;

    /** South-western quadrant. */
    private ViewQuadTreeNode quadrantSW = null;

    /** South-eastern quadrant. */
    private ViewQuadTreeNode quadrantSE = null;

    /**
     * Creates a new instance.
     * 
     * @param maxDepth
     *            Maximum-depth of the quadrant child-nodes.
     * @param parent
     *            The parent node.
     * @param boundsUserSpace
     *            The node's user-space bounds.
     */
    public ViewQuadTreeNode(int maxDepth, ViewQuadTreeNode parent, BoundsUserSpace boundsUserSpace) {
        super();
        this.parent = parent;
        quadrantBounds.set(boundsUserSpace);

        if (getDepth() <= maxDepth) {
            // init child-quadrant nodes
            initQuadrantNodes(maxDepth);
        }
    }

    /**
     * Initializes the quadrant child-nodes.
     * 
     * @param maxDepth
     *            The max-depth to be initialized.
     */
    protected void initQuadrantNodes(int maxDepth) {
        BoundsUserSpace b = new BoundsUserSpace();

        // north-east
        quadrantBounds.calcQuadrantBounds(BoundsUserSpace.QUADRANT_NE, b);
        quadrantNE = new ViewQuadTreeNode(maxDepth, this, b);
        // north-west
        quadrantBounds.calcQuadrantBounds(BoundsUserSpace.QUADRANT_NW, b);
        quadrantNW = new ViewQuadTreeNode(maxDepth, this, b);
        // south-west
        quadrantBounds.calcQuadrantBounds(BoundsUserSpace.QUADRANT_SW, b);
        quadrantSW = new ViewQuadTreeNode(maxDepth, this, b);
        // south-east
        quadrantBounds.calcQuadrantBounds(BoundsUserSpace.QUADRANT_SE, b);
        quadrantSE = new ViewQuadTreeNode(maxDepth, this, b);
    }

    /**
     * Resets the node's quadrants.
     */
    private void resetQuadrants() {
        BoundsUserSpace b = new BoundsUserSpace();

        // north-east
        quadrantBounds.calcQuadrantBounds(BoundsUserSpace.QUADRANT_NE, b);
        quadrantNE.reset(b);
        // north-west
        quadrantBounds.calcQuadrantBounds(BoundsUserSpace.QUADRANT_NW, b);
        quadrantNW.reset(b);
        // south-west
        quadrantBounds.calcQuadrantBounds(BoundsUserSpace.QUADRANT_SW, b);
        quadrantSW.reset(b);
        // south-east
        quadrantBounds.calcQuadrantBounds(BoundsUserSpace.QUADRANT_SE, b);
        quadrantSE.reset(b);
    }

    /**
     * @return The node's depth.
     */
    protected int getDepth() {
        int depth = 1;

        if (parent != null) {
            depth += parent.getDepth();
        }

        return depth;
    }

    /**
     * Returns the child quadrant representing the given user-space bounds.
     * 
     * @param bounds
     *            The bounds to get the quadrant tree-node for.
     * @return The tree-node found.
     */
    protected ViewQuadTreeNode getChildQuadrant(BoundsUserSpace bounds) {
        ViewQuadTreeNode child = null;
        int quadrantId = quadrantBounds.testBoundsQuadrants(bounds);
        switch (quadrantId) {
        case BoundsUserSpace.QUADRANT_NE:
            child = quadrantNE;
            break;
        case BoundsUserSpace.QUADRANT_NW:
            child = quadrantNW;
            break;
        case BoundsUserSpace.QUADRANT_SW:
            child = quadrantSW;
            break;
        case BoundsUserSpace.QUADRANT_SE:
            child = quadrantSE;
            break;
        default:
            // nothing to to
            break;
        }

        if (child != null) {
            child = child.getChildQuadrant(bounds);
        } else {
            child = this;
        }

        return child;
    }

    /**
     * Resets this instance with the given bounds.
     * 
     * @param b
     *            The bounds to reset this instance with.
     */
    public void reset(BoundsUserSpace b) {
        elemList.clear();
        quadrantBounds.set(b);

        if (!isLeaf()) {
            resetQuadrants();
        }
    }

    /**
     * @return True if this node is a leaf (without child-nodes), False
     *         otherwise.
     */
    public boolean isLeaf() {
        return (quadrantNE == null);
    }

    /**
     * Collects the elements that fit the given bounds.
     * 
     * @param recursively
     *            True if elements are to be inquired recursively under all
     *            child-nodes.
     * @param bounds
     *            The bounds to check the elements on.
     * @return The elements collected.
     */
    protected Collection<ElemBase> getElems(boolean recursively, BoundsUserSpace bounds) {
        int count = elemList.size();
        ArrayListX<ElemBase> list = null;
        ViewQuadTreeNode quad = getChildQuadrant(bounds);

        if (quad != null) {
            count += quad.getElemCount(recursively);
        }

        list = new ArrayListX<ElemBase>(count);
        getElems(list, (quad == null), bounds);

        if (quad != null) {
            quad.getElems(list, recursively, bounds);
        }

        return list;
    }

    /**
     * Collects the elements that fit the given bounds.
     * 
     * @param dest
     *            Destination list collecting the elements found.
     * @param recursively
     *            True to search all child-nodes recursively for matching
     *            elements.
     * @param bounds
     *            The bounds to search the elements for.
     */
    protected void getElems(ArrayListX<ElemBase> dest, boolean recursively, BoundsUserSpace bounds) {
        if (bounds.intersects(quadrantBounds) || bounds.contains(quadrantBounds) || quadrantBounds.contains(bounds)) {

            Iterator<ElemBase> iterator = elemList.iterator();
            BoundsUserSpace b = new BoundsUserSpace();

            while (iterator.hasNext()) {
                ElemBase elem = iterator.next();
                int orderId = elem.getView().getZorder();
                elem.getBounds(b);

                if (b.intersects(bounds) || bounds.contains(b) || b.contains(bounds)) {
                    dest.set(orderId, elem);
                }
            }

            if (recursively && (!isLeaf())) {
                quadrantNE.getElems(dest, recursively, bounds);
                quadrantNW.getElems(dest, recursively, bounds);
                quadrantSE.getElems(dest, recursively, bounds);
                quadrantSW.getElems(dest, recursively, bounds);
            }
        }
    }

    /**
     * Counts the elements for this node.
     * 
     * @param recursively
     *            True if the elements should be counted recursively for all
     *            child-nodes.
     * @return The element count.
     */
    public int getElemCount(boolean recursively) {
        int count = elemList.size();

        if (recursively && !isLeaf()) {
            count += quadrantNE.getElemCount(recursively);
            count += quadrantNW.getElemCount(recursively);
            count += quadrantSE.getElemCount(recursively);
            count += quadrantSW.getElemCount(recursively);
        }

        return count;
    }

    /**
     * Returns all elements matching the given bounds.
     * 
     * @param bounds
     *            The bounds to search elements for.
     * @return The collected elements matching the given bounds.
     */
    public Collection<ElemBase> getElems(BoundsUserSpace bounds) {
        return getElems(true, bounds);
    }

    /**
     * Adds the given element to this node's quadrant structure based on the
     * element's position.
     * 
     * @param elemView
     *            The element to be added.
     */
    public void addElem(ElemBase elemView) {
        ViewQuadTreeNode quadTreeNode = null;
        BoundsUserSpace elemBounds = new BoundsUserSpace();

        elemView.getBounds(elemBounds);
        quadTreeNode = getChildQuadrant(elemBounds);
        quadTreeNode.elemList.addLast(elemView);
    }

    /**
     * Removes the given element.
     * 
     * @param elemView
     *            The element to be removed.
     */
    public void removeElem(ElemBase elemView) {
        ViewQuadTreeNode quadTreeNode = null;
        BoundsUserSpace elemBounds = new BoundsUserSpace();

        elemView.getBounds(elemBounds);
        quadTreeNode = getChildQuadrant(elemBounds);
        if (quadTreeNode == null) {
            quadTreeNode = this;
        }
        quadTreeNode.elemList.remove(elemView);
    }

    /**
     * Uninitializes this node and all its child-nodes.
     */
    public void close() {
        elemList.clear();

        if (!isLeaf()) {
            quadrantNE.close();
            quadrantNW.close();
            quadrantSW.close();
            quadrantSE.close();
        }
        parent = null;
    }

    /**
     * Dumps debug-information to console-output.
     */
    public void trace() {
        int level = 1;

        System.out.println("--- Quad-tree trace start ---");
        trace(level);
    }

    /**
     * Dumps debug information to console-output.
     * 
     * @param level
     *            The tree-level to dump.
     */
    protected void trace(int level) {
        System.out.println("Level: " + level);
        System.out.println("Root elems: " + elemList.size());
        if (!isLeaf()) {
            quadrantNE.trace(level + 1);
            quadrantNW.trace(level + 1);
            quadrantSE.trace(level + 1);
            quadrantSW.trace(level + 1);
        }
    }
}

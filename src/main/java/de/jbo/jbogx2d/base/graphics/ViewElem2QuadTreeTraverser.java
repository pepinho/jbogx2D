//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ViewElem2QuadTreeTraverser.java
// Created: 03.03.2004 - 21:22:22
//

package de.jbo.jbogx2d.base.graphics;

import de.jbo.jbogx2d.base.drawing.traversing.ElementTraverser;
import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.elements.ElemContainer;
import de.jbo.jbogx2d.base.elements.view.ElemView;

/**
 * Implements an element traverser used to initialize a quad-tree structure.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 03.03.2004: jbo created <br>
 */
public class ViewElem2QuadTreeTraverser extends ElementTraverser {
    /** Quad-tree root-node to be initialized. */
    private ViewQuadTreeNode quadTreeRoot = null;

    /** Order-id currently used for an element to set the correct z-order. */
    private int orderId = 0;

    /**
     * Creates a new instance.
     * 
     * @param quadTreeRoot
     *            The quad-tree root-node to be handled.
     */
    public ViewElem2QuadTreeTraverser(ViewQuadTreeNode quadTreeRoot) {
        super();
        this.quadTreeRoot = quadTreeRoot;
        setVisitContainerChilds(true);
        setDirectionFirstToLast(true);
    }

    /*
     * @seede.jbo.jbogx2d.base.drawing.traversing.ElementTraverser#
     * handleAfterContainerChilds(de.jbo.jbogx2d.base.elements.ElemContainer)
     */
    @Override
    public short handleAfterContainerChilds(ElemContainer container) {
        return ElementTraverser.CONTINUE;
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
     * @see
     * de.jbo.jbogx2d.base.drawing.traversing.ElementTraverser#handleElement
     * (de.jbo.jbogx2d.base.elements.ElemBase)
     */
    @Override
    public short handleElement(ElemBase elem) {
        ElemView elemView = elem.getView();

        if (elemView != null) {
            elemView.setZorder(orderId++);
            quadTreeRoot.addElem(elem);
        }

        return ElementTraverser.CONTINUE;
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

}
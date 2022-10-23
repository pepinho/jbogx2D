//
// Copyright (c) 2013 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    ElementTraverserCollectByBoundsTest.java
// Created: 22.12.2013 - 16:17:06
//
package de.jbo.jbogx2d.base.drawing.traversing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.elements.ElemContainer;
import de.jbo.jbogx2d.base.elements.ElemPolyline;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * @author Josef Baro (jbo)
 * @version 22.12.2013 jbo - created
 */
public class ElementTraverserCollectByBoundsTest {

    /**
     * Test-bounds height.
     */
    private static final double BOUNDS_HEIGHT = 210.0;

    /**
     * Test-bounds width.
     */
    private static final double BOUNDS_WIDTH = 297.0;

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.drawing.traversing.ElementTraverserCollectByBounds#init()}
     * .
     */
    @Test
    public void testInit() {
        LinkedList<ElemBase> list = new LinkedList<ElemBase>();
        BoundsUserSpace b = new BoundsUserSpace(0.0, 0.0, BOUNDS_WIDTH, BOUNDS_HEIGHT);
        ElementTraverserCollectByBounds traverser = new ElementTraverserCollectByBounds(list, b, true, true);
        assertTrue(traverser.init());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.drawing.traversing.ElementTraverserCollectByBounds#close()}
     * .
     */
    @Test
    public void testClose() {
        LinkedList<ElemBase> list = new LinkedList<ElemBase>();
        BoundsUserSpace b = new BoundsUserSpace(0.0, 0.0, BOUNDS_WIDTH, BOUNDS_HEIGHT);
        ElementTraverserCollectByBounds traverser = new ElementTraverserCollectByBounds(list, b, true, true);
        assertTrue(traverser.close());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.drawing.traversing.ElementTraverserCollectByBounds#handleBeforeContainerChilds(de.jbo.jbogx2d.base.elements.ElemContainer)}
     * .
     */
    @Test
    public void testHandleBeforeContainerChilds() {
        LinkedList<ElemBase> list = new LinkedList<ElemBase>();
        BoundsUserSpace b = new BoundsUserSpace(0.0, 0.0, BOUNDS_WIDTH, BOUNDS_HEIGHT);
        ElementTraverserCollectByBounds traverser = new ElementTraverserCollectByBounds(list, b, true, true);
        ElemContainer container = new ElemContainer();
        assertEquals(ElementTraverser.CONTINUE, traverser.handleBeforeContainerChilds(container));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.drawing.traversing.ElementTraverserCollectByBounds#handleAfterContainerChilds(de.jbo.jbogx2d.base.elements.ElemContainer)}
     * .
     */
    @Test
    public void testHandleAfterContainerChilds() {
        LinkedList<ElemBase> list = new LinkedList<ElemBase>();
        BoundsUserSpace b = new BoundsUserSpace(0.0, 0.0, BOUNDS_WIDTH, BOUNDS_HEIGHT);
        ElementTraverserCollectByBounds traverser = new ElementTraverserCollectByBounds(list, b, true, true);
        ElemContainer container = new ElemContainer();
        assertEquals(ElementTraverser.CONTINUE, traverser.handleAfterContainerChilds(container));

        traverser = new ElementTraverserCollectByBounds(list, b, false, true);
        assertEquals(ElementTraverser.STOP_CONTAINER, traverser.handleAfterContainerChilds(container));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.drawing.traversing.ElementTraverserCollectByBounds#handleElement(de.jbo.jbogx2d.base.elements.ElemBase)}
     * .
     */
    @Test
    public void testHandleElement() {
        final double point1x = 10.0;
        final double point1y = 10.0;
        final double point2x = 100.0;
        final double point2y = 100.0;
        LinkedList<ElemBase> list = new LinkedList<ElemBase>();
        BoundsUserSpace b = new BoundsUserSpace(0.0, 0.0, BOUNDS_WIDTH, BOUNDS_HEIGHT);
        ElementTraverserCollectByBounds traverser = new ElementTraverserCollectByBounds(list, b, true, true);

        PointUserSpace[] points = new PointUserSpace[2];
        points[0] = new PointUserSpace(-point1x, -point1y);
        points[1] = new PointUserSpace(point2x, point2y);
        ElemPolyline polylineIntersects = new ElemPolyline(points);
        assertEquals(ElementTraverser.CONTINUE, traverser.handleElement(polylineIntersects));
        assertTrue(list.contains(polylineIntersects));

        points[0] = new PointUserSpace(point1x, point1y);
        points[1] = new PointUserSpace(point2x, point2y);
        ElemPolyline polylineCompletelyIn = new ElemPolyline(points);
        traverser.handleElement(polylineCompletelyIn);
        assertTrue(list.contains(polylineCompletelyIn));
        assertTrue(traverser.isStoreElementsAtEnd());
        assertEquals(list.get(1), polylineCompletelyIn);
        assertEquals(list.get(0), polylineIntersects);
        list.clear();

        traverser.setStoreElementsAtEnd(false);
        traverser.handleElement(polylineIntersects);
        traverser.handleElement(polylineCompletelyIn);
        assertEquals(list.get(0), polylineCompletelyIn);
        assertEquals(list.get(1), polylineIntersects);
        list.clear();

        traverser = new ElementTraverserCollectByBounds(list, b, true, false);
        traverser.handleElement(polylineIntersects);
        traverser.handleElement(polylineCompletelyIn);
        assertFalse(list.contains(polylineIntersects));
        assertTrue(list.contains(polylineCompletelyIn));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.drawing.traversing.ElementTraverserCollectByBounds#ElementTraverserCollectByBounds(java.util.LinkedList, de.jbo.jbogx2d.base.geom.BoundsUserSpace, boolean, boolean)}
     * .
     */
    @Test
    public void testConstructor() {
        LinkedList<ElemBase> list = new LinkedList<ElemBase>();
        BoundsUserSpace b = new BoundsUserSpace(0.0, 0.0, BOUNDS_WIDTH, BOUNDS_HEIGHT);
        ElementTraverserCollectByBounds traverser = new ElementTraverserCollectByBounds(list, b, true, true);
        assertEquals(list, traverser.getElementsList());
        assertTrue(traverser.isVisitContainerChilds());
        assertTrue(traverser.isDirectionFirstToLast());
        assertFalse(traverser.isHandleContainerBeforeChilds());
    }

    /**
     * Tests consctructor with invalid bounds parameter.
     */
    @Test(expected = Exception.class)
    public void testConstructorInvalidBounds() {
        LinkedList<ElemBase> list = new LinkedList<ElemBase>();
        new ElementTraverserCollectByBounds(list, null, true, true);
    }

}

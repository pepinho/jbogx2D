//
// Copyright (c) 2014 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    ElemModelPolylineTest.java
// Created: 02.01.2014 - 12:38:18
//
package de.jbo.jbogx2d.base.elements.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.jbo.jbogx2d.base.elements.ElemPolyline;
import de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D;
import de.jbo.jbogx2d.base.geom.AffineTransformX;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * @author Josef Baro (jbo)
 * @version 02.01.2014 jbo - created
 */
public class ElemModelPolylineTest {
    /** test-coordinate. */
    private static final double X1 = 10.0;

    /** test-coordinate. */
    private static final double Y1 = 10.0;

    /** test-coordinate. */
    private static final double X2 = 10.0;

    /** test-coordinate. */
    private static final double Y2 = 20.0;

    /** test-coordinate. */
    private static final double X3 = 20.0;

    /** test-coordinate. */
    private static final double Y3 = 20.0;

    /** test-coordinate. */
    private static final double X4 = 20.0;

    /** test-coordinate. */
    private static final double Y4 = 10.0;

    /** Model to be tested. */
    private ElemModelPolyline model = null;

    /** The poly-points. */
    private PointUserSpace[] points = null;

    /**
     * @throws java.lang.Exception
     *             Possible exception.
     */
    @Before
    public void setUp() throws Exception {
        points = new PointUserSpace[4];
        for (int i = 0; i < points.length; i++) {
            points[i] = new PointUserSpace();
        }
        points[0].set(X1, Y1);
        points[1].set(X2, Y2);
        points[2].set(X3, Y3);
        points[3].set(X4, Y4);
        ElemPolyline polyline = new ElemPolyline(points);
        model = (ElemModelPolyline) polyline.getModel();
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#transform(de.jbo.jbogx2d.base.geom.AffineTransformX)}
     * .
     */
    @Test
    public void testTransform() {
        final double translateX = 10.0;
        final double translateY = 10.0;
        AffineTransformX translation = new AffineTransformX();
        translation.translate(translateX, translateY);

        model.transform(translation);
        assertTrue(model.isBoundsDirty());

        PointUserSpace point = new PointUserSpace();
        for (int i = 0; i < points.length; i++) {
            model.getPoint(i, point);
            assertEquals(points[i].x + translateX, point.x, 0.0);
            assertEquals(points[i].y + translateY, point.y, 0.0);
        }
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#getDistanceTo(de.jbo.jbogx2d.base.geom.PointUserSpace)}
     * .
     */
    @Test
    public void testGetDistanceTo() {
        final double expected = 10.0;
        PointUserSpace point = new PointUserSpace(0.0, 0.0);
        double distance = model.getDistanceTo(point);
        assertEquals(expected, distance, 0.0);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#isPointInside(de.jbo.jbogx2d.base.geom.PointUserSpace)}
     * .
     */
    @Test
    public void testIsPointInside() {
        final double diff = 0.5;
        PointUserSpace pointOutside = new PointUserSpace(0.0, 0.0);
        PointUserSpace pointInside = new PointUserSpace(X1 + diff, Y1 + diff);
        assertFalse(model.isPointInside(pointInside));
        assertFalse(model.isPointInside(pointOutside));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#intersects(de.jbo.jbogx2d.base.geom.BoundsUserSpace)}
     * .
     */
    @Test
    public void testIntersects() {
        final double width = 100.0;
        final double heightIntersects = 15.0;
        final double heightIntersectsNOT = 5.0;
        BoundsUserSpace boundsIntersectsNOT = new BoundsUserSpace(0.0, 0.0, width, heightIntersectsNOT);
        BoundsUserSpace boundsIntersects = new BoundsUserSpace(0.0, 0.0, width, heightIntersects);

        assertTrue(model.intersects(boundsIntersects));
        assertTrue(model.intersects(boundsIntersects.x, boundsIntersects.y, boundsIntersects.width, boundsIntersects.height));
        assertFalse(model.intersects(boundsIntersectsNOT));
        assertFalse(model.intersects(boundsIntersectsNOT.x, boundsIntersectsNOT.y, boundsIntersectsNOT.width, boundsIntersectsNOT.height));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#getAttribLine()}
     * .
     */
    @Test
    public void testGetAttribLine() {
        assertNotNull(model.getAttribLine());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#getAttribFill()}
     * .
     */
    @Test
    public void testGetAttribFill() {
        assertNull(model.getAttribFill());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#getAttribText()}
     * .
     */
    @Test
    public void testGetAttribText() {
        assertNull(model.getAttribText());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#getPointCount()}
     * .
     */
    @Test
    public void testGetPointCount() {
        assertEquals(points.length, model.getPointCount());
        model.removePoint(0);
        assertEquals(points.length - 1, model.getPointCount());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#setPoint(int, de.jbo.jbogx2d.base.geom.PointUserSpace)}
     * .
     */
    @Test
    public void testSetPointViaIndex() {
        final double x = 1.0;
        final double y = 1.0;
        PointUserSpace p = new PointUserSpace(x, y);
        model.setPoint(0, x, y);
        PointUserSpace pTest = new PointUserSpace();
        model.getPoint(0, pTest);
        assertEquals(p, pTest);
        model.setPoint(2, p);
        model.getPoint(2, pTest);
        assertEquals(p, pTest);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#getPoints()}.
     */
    @Test
    public void testGetPoints() {
        PointUserSpace[] pnts = model.getPoints();
        assertEquals(points.length, pnts.length);
        for (int i = 0; i < points.length; i++) {
            assertEquals(points[i], pnts[i]);
        }
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#getShape()}.
     */
    @Test
    public void testGetShape() {
        assertNotNull(model.getShape());
        assertTrue(model.getShape() instanceof Polyline2D);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#setPointCount(int)}
     * .
     */
    @Test
    public void testSetPointCount() {
        model.setPointCount(5);
        assertEquals(5, model.getPointCount());
        PointUserSpace[] pnts = model.getPoints();
        assertEquals(5, pnts.length);
        for (int i = 0; i < points.length; i++) {
            assertEquals(points[i], pnts[i]);
        }
        assertEquals(0.0, pnts[4].x, 0.0);
        assertEquals(0.0, pnts[4].y, 0.0);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#insertPoint(int, double, double)}
     * .
     */
    @Test
    public void testInsertPoint() {
        final double x = 1.0;
        final double y = 1.0;

        model.insertPoint(4, x, y);

        assertEquals(5, model.getPointCount());
        PointUserSpace[] pnts = model.getPoints();
        assertEquals(5, pnts.length);
        for (int i = 0; i < points.length; i++) {
            assertEquals(points[i], pnts[i]);
        }
        assertEquals(x, pnts[4].x, 0.0);
        assertEquals(y, pnts[4].y, 0.0);

        model.insertPoint(0, x, y);
        assertEquals(6, model.getPointCount());
        pnts = model.getPoints();
        assertEquals(6, pnts.length);
        for (int i = 1; i < 5; i++) {
            assertEquals(points[i - 1], pnts[i]);
        }
        assertEquals(x, pnts[0].x, 0.0);
        assertEquals(y, pnts[0].y, 0.0);
        assertEquals(x, pnts[5].x, 0.0);
        assertEquals(y, pnts[5].y, 0.0);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModel#getBounds(de.jbo.jbogx2d.base.geom.BoundsUserSpace)}
     * .
     */
    @Test
    public void testGetBounds() {
        BoundsUserSpace boundsExpected = new BoundsUserSpace(X1, Y1, X3 - X1, Y3 - Y1);
        BoundsUserSpace bounds = new BoundsUserSpace();
        model.getBounds(bounds);
        assertEquals(boundsExpected, bounds);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModel#isBoundsDirty()}.
     */
    @Test
    public void testIsBoundsDirty() {
        BoundsUserSpace bounds = new BoundsUserSpace();
        model.getBounds(bounds);
        assertFalse(model.isBoundsDirty());
        model.removePoint(0);
        assertTrue(model.isBoundsDirty());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModel#getName()}.
     */
    @Test
    public void testGetAndSetName() {
        final String name = "myName";
        assertNull(model.getName());
        model.setName(name);
        assertNotNull(model.getName());
        assertEquals(name, model.getName());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModel#getElement()}.
     */
    @Test
    public void testGetElement() {
        assertNotNull(model.getElement());
        assertTrue(model.getElement() instanceof ElemPolyline);
    }

}

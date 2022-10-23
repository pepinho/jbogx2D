//
// Copyright (c) 2013 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    ElemModelCircleTest.java
// Created: 25.12.2013 - 19:56:11
//
package de.jbo.jbogx2d.base.elements.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.jbo.jbogx2d.base.elements.ElemCircle;
import de.jbo.jbogx2d.base.elements.model.shapes.Circle2D;
import de.jbo.jbogx2d.base.geom.AffineTransformX;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * @author Josef Baro (jbo)
 * @version 25.12.2013 jbo - created
 */
public class ElemModelCircleTest {
    /** middle-point. */
    private static final PointUserSpace CENTER = new PointUserSpace(10.0, 10.0);

    /** radius. */
    private static final double RADIUS = 10.0;

    /** test element. */
    private ElemModelCircle model = null;

    /**
     * @throws java.lang.Exception
     *             Possible exception.
     */
    @Before
    public void setUp() throws Exception {
        ElemCircle circle = new ElemCircle(CENTER.x, CENTER.y, RADIUS);
        model = (ElemModelCircle) circle.getModel();
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelCircle#transform(de.jbo.jbogx2d.base.geom.AffineTransformX)}
     * .
     */
    @Test
    public void testTransformScale() {
        double scaleFactor = 2.0;
        AffineTransformX scale = new AffineTransformX();
        scale.scale(scaleFactor, scaleFactor);
        model.transform(scale);
        assertEquals(RADIUS * scaleFactor, model.getRadius(), 0.0);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelCircle#transform(de.jbo.jbogx2d.base.geom.AffineTransformX)}
     * .
     */
    @Test
    public void testTransformMove() {
        double translate = 2.0;
        AffineTransformX move = new AffineTransformX();
        move.translate(translate, translate);
        model.transform(move);
        assertEquals(CENTER.x + translate, model.getCenterX(), 0.0);
        assertEquals(CENTER.y + translate, model.getCenterY(), 0.0);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelCircle#getDistanceTo(de.jbo.jbogx2d.base.geom.PointUserSpace)}
     * .
     */
    @Test
    public void testGetDistanceTo() {
        final double x = 30.0;
        final double y = 30.0;
        final double distance = 10.0;
        PointUserSpace p = new PointUserSpace(x, y);
        assertEquals(distance, model.getDistanceTo(p), 0.0);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelCircle#isPointInside(de.jbo.jbogx2d.base.geom.PointUserSpace)}
     * .
     */
    @Test
    public void testIsPointInside() {
        final double xOutside = 30.0;
        final double yOutside = 30.0;
        PointUserSpace pointOutside = new PointUserSpace(xOutside, yOutside);
        final double xInside = 15.0;
        final double yInside = 15.0;
        PointUserSpace pointInside = new PointUserSpace(xInside, yInside);

        assertTrue(model.isPointInside(pointInside));
        assertFalse(model.isPointInside(pointOutside));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelCircle#intersects(double, double, double, double)}
     * .
     */
    @Test
    public void testIntersects() {
        final double x = 10.0;
        final double y = 10.0;
        final double width = 20.0;
        final double height = 20.0;
        BoundsUserSpace bounds = new BoundsUserSpace(x, y, width, height);
        assertTrue(model.intersects(bounds));
        assertTrue(model.intersects(x, y, width, height));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelCircle#intersects(double, double, double, double)}
     * .
     */
    @Test
    public void testIntersectsNOT() {
        final double x = 25.0;
        final double y = 25.0;
        final double width = 20.0;
        final double height = 20.0;
        BoundsUserSpace bounds = new BoundsUserSpace(x, y, width, height);
        assertFalse(model.intersects(bounds));
        assertFalse(model.intersects(x, y, width, height));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelCircle#setCenter(double, double)}
     * .
     */
    @Test
    public void testSetCenter() {
        model.setCenter(0.0, 0.0);
        assertEquals(new PointUserSpace(0.0, 0.0), new PointUserSpace(model.getCenterX(), model.getCenterY()));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelCircle#setRadius(double)}
     * .
     */
    @Test
    public void testSetRadius() {
        final double radius = 2.5;
        model.setRadius(radius);
        assertEquals(radius, model.getRadius(), 0.0);
    }

    /**
     * Tests {@link ElemModelCircle#getAttribText()}.
     */
    @Test
    public void testNoTextAttribute() {
        assertNull(model.getAttribText());
    }

    /**
     * Tests {@link ElemModelCircle#getShape()}.
     */
    @Test
    public void testsShape() {
        assertNotNull(model.getShape());
        assertTrue(model.getShape() instanceof Circle2D);
    }

}

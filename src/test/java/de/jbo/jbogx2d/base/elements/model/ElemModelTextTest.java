//
// Copyright (c) 2014 by jbo - Josef Baro
//
// Project: jbogx2d.base
// File:    ElemModelTextTest.java
// Created: 23.12.2014 - 09:11:23
//
package de.jbo.jbogx2d.base.elements.model;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.jbo.jbogx2d.base.Jbogx2D;
import de.jbo.jbogx2d.base.elements.ElemText;
import de.jbo.jbogx2d.base.elements.model.ElemModelText;
import de.jbo.jbogx2d.base.geom.AffineTransformX;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointUserSpace;
import de.jbo.jbogx2d.base.util.OSUtil;

/**
 * @author Josef Baro (jbo)
 * @version 23.12.2014 jbo - created
 */
public class ElemModelTextTest {

    private ElemText text = null;

    private ElemModelText model = null;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Jbogx2D.init();
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        text = new ElemText();
        text.setText(new String[] { "test" });
        model = (ElemModelText) text.getModel();
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#calculateBounds(de.jbo.jbogx2d.base.geom.BoundsUserSpace)}
     * .
     */
    @Test
    public void testCalculateBounds() {
        assumeTrue("we only execute this on Windows due to pixel-comparison", OSUtil.isWindows());
        
        BoundsUserSpace boundsExpected = new BoundsUserSpace(0.0, -1.00537109375, 1.583984375, 1.2578125);        
        BoundsUserSpace bounds = new BoundsUserSpace();
        text.getBounds(bounds);
        assertEquals(boundsExpected, bounds);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#transform(de.jbo.jbogx2d.base.geom.AffineTransformX)}
     * .
     */
    @Test
    public void testTransform() {
        PointUserSpace pointExpected = new PointUserSpace(10.0, 10.0);
        AffineTransformX transform = new AffineTransformX();
        transform.translate(10.0, 10.0);        
        model.transform(transform);
        PointUserSpace point = model.getBasePoint();
        assertEquals(pointExpected, point);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#getDistanceTo(de.jbo.jbogx2d.base.geom.PointUserSpace)}
     * .
     */
    @Test
    public void testGetDistanceTo() {
        assumeTrue("we only execute this on Windows due to pixel-comparison", OSUtil.isWindows());
        
        double expected = 8.416015625;
        PointUserSpace pointDistanceTo = new PointUserSpace(10.0, 10.0);
        double distanceTo = model.getDistanceTo(pointDistanceTo);
        assertEquals(expected, distanceTo, 0.0);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#isPointInside(de.jbo.jbogx2d.base.geom.PointUserSpace)}
     * .
     */
    @Test
    public void testIsPointInside() {
        // bounds: 0.0, -1.00537109375, 1.583984375, 1.2578125          
        assertTrue(text.isPointInside(new PointUserSpace(0.0, 0.0)));
        assertTrue(text.isPointInside(new PointUserSpace(1.58, 0.0)));
        assertTrue(text.isPointInside(new PointUserSpace(1.0, 0.2)));
        assertFalse(text.isPointInside(new PointUserSpace(-0.01, 0.0)));
        assertFalse(text.isPointInside(new PointUserSpace(0.0, 1.59)));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#intersects(double, double, double, double)}
     * .
     */
    @Test
    public void testIntersectsDoubleDoubleDoubleDouble() {
        BoundsUserSpace boundsTrue = new BoundsUserSpace(0.0, -1.1, 1.6, 1.3);
        BoundsUserSpace boundsFalse = new BoundsUserSpace(2.0, 2.0, 1.0, 1.0);
        
        assertTrue(model.intersects(boundsTrue.x, boundsTrue.y, boundsTrue.width, boundsTrue.height));
        assertFalse(model.intersects(boundsFalse.x, boundsFalse.y, boundsFalse.width, boundsFalse.height));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#intersects(de.jbo.jbogx2d.base.geom.BoundsUserSpace)}
     * .
     */
    @Test
    public void testIntersectsBoundsUserSpace() {
        BoundsUserSpace boundsTrue = new BoundsUserSpace(0.0, -1.1, 1.6, 1.3);
        BoundsUserSpace boundsFalse = new BoundsUserSpace(2.0, 2.0, 1.0, 1.0);
        
        assertTrue(model.intersects(boundsTrue));
        assertFalse(model.intersects(boundsFalse));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#getAttribLine()}.
     */
    @Test
    public void testGetAttribLine() {
        assertNull(model.getAttribLine());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#getAttribFill()}.
     */
    @Test
    public void testGetAttribFill() {
        assertNull(model.getAttribFill());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#getAttribText()}.
     */
    @Test
    public void testGetAttribText() {
        assertNotNull(model.getAttribText());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#ElemModelText(de.jbo.jbogx2d.base.elements.ElemBase)}
     * .
     */
    @Test
    public void testElemModelText() {
        assertEquals(text, model.getElement());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#getBasePoint(de.jbo.jbogx2d.base.geom.PointUserSpace)}
     * .
     */
    @Test
    public void testGetBasePointPointUserSpace() {
        PointUserSpace expected = new PointUserSpace(0.0, 0.0);
        assertEquals(expected, model.getBasePoint());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#getText()}.
     */
    @Test
    public void testGetText() {
       assertArrayEquals(new String[] {"test"}, model.getText());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#setBasePoint(de.jbo.jbogx2d.base.geom.PointUserSpace)}
     * .
     */
    @Test
    public void testSetBasePointPointUserSpace() {
        PointUserSpace expected = new PointUserSpace(10.0, 10.0);
        model.setBasePoint(10.0, 10.0);
        assertEquals(expected, model.getBasePoint());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#setBasePoint(double, double)}
     * .
     */
    @Test
    public void testSetBasePointDoubleDouble() {
        PointUserSpace expected = new PointUserSpace(10.0, 10.0);
        model.setBasePoint(10.0, 10.0);
        assertEquals(expected, model.getBasePoint());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#setText(java.lang.String[])}
     * .
     */
    @Test
    public void testSetText() {
        model.setText(new String[] {"test1", "test2"});
        assertArrayEquals(new String[] {"test1", "test2"}, model.getText());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#getBasePoint()}.
     */
    @Test
    public void testGetBasePoint() {
        PointUserSpace expected = new PointUserSpace(10.0, 10.0);
        model.setBasePoint(new PointUserSpace(10.0, 10.0));
        PointUserSpace base = new PointUserSpace();
        model.getBasePoint(base);
        assertEquals(expected, base);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#getTransformation()}
     * .
     */
    @Test
    public void testGetTransformation() {
        AffineTransformX transform = new AffineTransformX();
        transform.translate(10.0, 10.0);        
        model.transform(transform);
        assertEquals(transform, model.getTransformation());
    }

}

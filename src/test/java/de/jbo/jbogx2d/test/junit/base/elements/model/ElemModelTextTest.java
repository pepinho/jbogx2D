//
// Copyright (c) 2014 by jbo - Josef Baro
//
// Project: jbogx2d.base
// File:    ElemModelTextTest.java
// Created: 23.12.2014 - 09:11:23
//
package de.jbo.jbogx2d.test.junit.base.elements.model;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.jbo.jbogx2d.base.elements.ElemText;
import de.jbo.jbogx2d.base.elements.model.ElemModelText;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;

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
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        text = new ElemText();
        model = (ElemModelText) text.getModel();
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#calculateBounds(de.jbo.jbogx2d.base.geom.BoundsUserSpace)}
     * .
     */
    @Test
    public void testCalculateBounds() {
        text.setText(new String[] { "test" });
        BoundsUserSpace bounds = new BoundsUserSpace();
        text.getBounds(bounds);

    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#transform(de.jbo.jbogx2d.base.geom.AffineTransformX)}
     * .
     */
    @Test
    public void testTransform() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#getDistanceTo(de.jbo.jbogx2d.base.geom.PointUserSpace)}
     * .
     */
    @Test
    public void testGetDistanceTo() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#isPointInside(de.jbo.jbogx2d.base.geom.PointUserSpace)}
     * .
     */
    @Test
    public void testIsPointInside() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#intersects(double, double, double, double)}
     * .
     */
    @Test
    public void testIntersectsDoubleDoubleDoubleDouble() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#intersects(de.jbo.jbogx2d.base.geom.BoundsUserSpace)}
     * .
     */
    @Test
    public void testIntersectsBoundsUserSpace() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#getAttribLine()}.
     */
    @Test
    public void testGetAttribLine() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#getAttribFill()}.
     */
    @Test
    public void testGetAttribFill() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#getAttribText()}.
     */
    @Test
    public void testGetAttribText() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#ElemModelText(de.jbo.jbogx2d.base.elements.ElemBase)}
     * .
     */
    @Test
    public void testElemModelText() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#getBasePoint(de.jbo.jbogx2d.base.geom.PointUserSpace)}
     * .
     */
    @Test
    public void testGetBasePointPointUserSpace() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#getText()}.
     */
    @Test
    public void testGetText() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#setBasePoint(de.jbo.jbogx2d.base.geom.PointUserSpace)}
     * .
     */
    @Test
    public void testSetBasePointPointUserSpace() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#setBasePoint(double, double)}
     * .
     */
    @Test
    public void testSetBasePointDoubleDouble() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#setText(java.lang.String[])}
     * .
     */
    @Test
    public void testSetText() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#getBasePoint()}.
     */
    @Test
    public void testGetBasePoint() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelText#getTransformation()}
     * .
     */
    @Test
    public void testGetTransformation() {
        fail("Not yet implemented");
    }

}

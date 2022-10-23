//
// Copyright (c) 2013 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    AttribLineTest.java
// Created: 22.11.2013 - 20:57:54
//
package de.jbo.jbogx2d.base.attrib;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.awt.BasicStroke;
import java.awt.Color;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.jbo.jbogx2d.base.Jbogx2D;
import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.elements.ElemCircle;

/**
 * @author Josef Baro (jbo)
 * @version 22.11.2013 jbo - created
 */
public class AttribLineTest {
    /** circle radius. */
    private static final double RADIUS = 10.0;

    /** fill attribute. */
    private AttribLine line = null;

    /** element. */
    private ElemBase elem = null;

    /**
     * Sets-up the class.
     */
    @BeforeClass
    public static void setUpClass() {
        Jbogx2D.init();
    }

    /**
     * Set-up.
     */
    @Before
    public void setUp() {
        elem = new ElemCircle(0.0, 0.0, RADIUS);
        line = elem.getAttribLine();
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribLine#update(de.jbo.jbogx2d.base.elements.model.ElemModel)}
     * .
     */
    @Test
    public void testUpdate() {
        assertNull(line.getSystemColor());
        assertNull(line.getSystemStroke());

        line.setStroke((short) 2);

        line.update(elem.getModel());

        Color c = line.getSystemColor();
        assertNotNull(c);
        assertEquals(AttribColorMap.BLACK, c);

        BasicStroke stroke = line.getSystemStroke();
        assertNotNull(stroke);
        assertEquals(line.getWidth(), stroke.getLineWidth(), 0.0);
        assertArrayEquals(new float[] { 3.0f, 3.0f }, stroke.getDashArray(), 0.0f);
        assertEquals(BasicStroke.CAP_BUTT, stroke.getEndCap());
        assertEquals(BasicStroke.JOIN_BEVEL, stroke.getLineJoin());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribLine#AttribLine()}.
     */
    @Test
    public void testAttribLine() {
        assertNotNull(line);
        assertEquals(1.0f, line.getWidth(), 0.0f);
        assertEquals((short) 1, line.getColor());
        assertEquals((short) 1, line.getStroke());
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.attrib.AttribLine#getColor()}.
     */
    @Test
    public void testGetColor() {
        assertEquals((short) 1, line.getColor());
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.attrib.AttribLine#getStroke()}
     * .
     */
    @Test
    public void testGetStroke() {
        assertEquals((short) 1, line.getStroke());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribLine#getSystemColor()}.
     */
    @Test
    public void testGetSystemColor() {
        assertNull(line.getSystemColor());

        line.update(elem.getModel());

        Color c = line.getSystemColor();
        assertNotNull(c);
        assertEquals(AttribColorMap.BLACK, c);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribLine#getSystemStroke()}.
     */
    @Test
    public void testGetSystemStroke() {
        assertNull(line.getSystemStroke());

        line.setStroke((short) 2);

        line.update(elem.getModel());

        BasicStroke stroke = line.getSystemStroke();
        assertNotNull(stroke);
        assertEquals(line.getWidth(), stroke.getLineWidth(), 0.0);
        assertArrayEquals(new float[] { 3.0f, 3.0f }, stroke.getDashArray(), 0.0f);
        assertEquals(BasicStroke.CAP_BUTT, stroke.getEndCap());
        assertEquals(BasicStroke.JOIN_BEVEL, stroke.getLineJoin());
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.attrib.AttribLine#getWidth()}.
     */
    @Test
    public void testGetWidth() {
        assertEquals(1.0f, line.getWidth(), 0.0f);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribLine#setColor(short)}.
     */
    @Test
    public void testSetColor() {
        assertNull(line.getSystemColor());
        line.setColor((short) 3);
        line.update(elem.getModel());

        Color c = line.getSystemColor();
        assertNotNull(c);
        assertEquals(AttribColorMap.GREEN, c);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribLine#setStroke(short)}.
     */
    @Test
    public void testSetStroke() {
        assertNull(line.getSystemStroke());

        line.setStroke((short) 2);
        assertEquals((short) 2, line.getStroke());

        line.update(elem.getModel());

        BasicStroke stroke = line.getSystemStroke();
        assertNotNull(stroke);
        assertEquals(line.getWidth(), stroke.getLineWidth(), 0.0);
        assertArrayEquals(new float[] { 3.0f, 3.0f }, stroke.getDashArray(), 0.0f);
        assertEquals(BasicStroke.CAP_BUTT, stroke.getEndCap());
        assertEquals(BasicStroke.JOIN_BEVEL, stroke.getLineJoin());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribLine#setWidth(float)}.
     */
    @Test
    public void testSetWidth() {
        line.setWidth(2.0f);
        assertEquals(2.0f, line.getWidth(), 2.0f);
    }

}

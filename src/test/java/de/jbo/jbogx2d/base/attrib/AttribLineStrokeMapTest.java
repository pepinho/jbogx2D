//
// Copyright (c) 2013 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    AttribLineStrokeMapTest.java
// Created: 22.11.2013 - 21:17:12
//
package de.jbo.jbogx2d.base.attrib;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.BasicStroke;

import org.junit.Test;

import de.jbo.jbogx2d.base.attrib.AttribLineStroke;
import de.jbo.jbogx2d.base.attrib.AttribLineStrokeMap;

/**
 * @author Josef Baro (jbo)
 * @version 22.11.2013 jbo - created
 */
public class AttribLineStrokeMapTest {

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribLineStrokeMap#AttribLineStrokeMap()}
     * .
     */
    @Test
    public void testAttribLineStrokeMap() {
        AttribLineStrokeMap map = new AttribLineStrokeMap();
        assertNotNull(map);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribLineStrokeMap#getStroke(short, float)}
     * .
     */
    @Test
    public void testGetStroke() {
        AttribLineStrokeMap map = new AttribLineStrokeMap();
        BasicStroke stroke = map.getStroke((short) 2, 3.0f);
        assertNotNull(stroke);
        assertArrayEquals(AttribLineStroke.DEFAULT_DOTTED.getPattern(), stroke.getDashArray(), 0.0f);
        assertEquals(3.0f, stroke.getLineWidth(), 0.0f);

        final short invalidIndex = 1000;
        stroke = map.getStroke(invalidIndex, 1.0f);
        assertNotNull(stroke);
        assertArrayEquals(AttribLineStroke.DEFAULT_SOLID.getPattern(), stroke.getDashArray(), 0.0f);
        assertEquals(1.0f, stroke.getLineWidth(), 0.0f);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribLineStrokeMap#setStroke(short, float[])}
     * .
     */
    @Test
    public void testSetStroke() {
        final short id = 2;
        AttribLineStrokeMap map = new AttribLineStrokeMap();
        map.setStroke(id, AttribLineStroke.DEFAULT_DASH02_06.getPattern());
        BasicStroke stroke = map.getStroke(id, 3.0f);
        assertNotNull(stroke);
        assertArrayEquals(AttribLineStroke.DEFAULT_DASH02_06.getPattern(), stroke.getDashArray(), 0.0f);
        assertEquals(3.0f, stroke.getLineWidth(), 0.0f);

    }

}

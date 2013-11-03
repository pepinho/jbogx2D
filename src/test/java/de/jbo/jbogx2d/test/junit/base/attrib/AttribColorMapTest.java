//
// Copyright (c) 2013 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    AttribColorMapTest.java
// Created: 03.11.2013 - 16:00:33
//
package de.jbo.jbogx2d.test.junit.base.attrib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;

import org.junit.Test;

import de.jbo.jbogx2d.base.attrib.AttribColorMap;
import de.jbo.jbogx2d.base.attrib.NamedColor;

/**
 * @author Josef Baro (jbo)
 * @version 03.11.2013 jbo - created
 */
public class AttribColorMapTest {

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribColorMap#AttribColorMap()}.
     */
    @Test
    public void testAttribColorMap() {
        AttribColorMap map = new AttribColorMap();
        Color color = map.getColor(0);
        assertNotNull(color);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribColorMap#getColor(int)}.
     */
    @Test
    public void testGetColor() {
        final int tooHighIndex = 1000;
        AttribColorMap map = new AttribColorMap();
        Color color = map.getColor(0);
        assertNotNull(color);
        assertEquals(AttribColorMap.WHITE, color);
        assertEquals(AttribColorMap.PALEGOLDENROD, map.getColor(AttribColorMap.DEFAULT_COLORS_COUNT - 1));
        assertEquals(AttribColorMap.DEFAULT, map.getColor(tooHighIndex));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribColorMap#setColor(int, java.lang.String, int, int, int)}
     * .
     */
    @Test
    public void testSetColor() {
        final int index = 33;
        final int red = 24;
        final int green = 244;
        final int blue = 12;
        final String name = "myColor";
        AttribColorMap map = new AttribColorMap();
        map.setColor(index, name, red, green, blue);
        NamedColor color = (NamedColor) map.getColor(index);
        assertEquals(name, color.toString());
        assertEquals(red, color.getRed());
        assertEquals(green, color.getGreen());
        assertEquals(blue, color.getBlue());
    }

}

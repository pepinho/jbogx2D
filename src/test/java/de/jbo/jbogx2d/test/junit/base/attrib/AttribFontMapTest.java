//
// Copyright (c) 2013 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    AttribFontMapTest.java
// Created: 22.11.2013 - 20:38:44
//
package de.jbo.jbogx2d.test.junit.base.attrib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Font;

import org.junit.Test;

import de.jbo.jbogx2d.base.attrib.AttribFontMap;

/**
 * @author Josef Baro (jbo)
 * @version 22.11.2013 jbo - created
 */
public class AttribFontMapTest {

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFontMap#AttribFontMap()}.
     */
    @Test
    public void testAttribFontMap() {
        AttribFontMap map = new AttribFontMap();
        Font f = map.getFont(0);
        assertNotNull(f);
        assertEquals(AttribFontMap.DEFAULT_FONT, f);
        assertEquals("Dialog", f.getFamily());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFontMap#getFont(int)}.
     */
    @Test
    public void testGetFont() {
        AttribFontMap map = new AttribFontMap();
        Font f = map.getFont(0);
        assertNotNull(f);
        assertEquals(AttribFontMap.DEFAULT_FONT, f);
        assertEquals("Dialog", f.getFamily());

        final int invalidIndex = 10;
        f = map.getFont(invalidIndex);
        assertNotNull(f);
        assertEquals(AttribFontMap.DEFAULT_FONT, f);
        assertEquals("Dialog", f.getFamily());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFontMap#setFont(int, java.lang.String)}
     * .
     */
    @Test
    public void testSetFontIntString() {
        AttribFontMap map = new AttribFontMap();
        map.setFont(1, "Arial");
        Font f = map.getFont(1);
        assertNotNull(f);
        assertEquals("Arial", f.getName());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFontMap#setFont(int, java.awt.Font)}
     * .
     */
    @Test
    public void testSetFontIntFont() {
        final int size = 10;
        AttribFontMap map = new AttribFontMap();
        Font font = new Font("Arial", Font.BOLD, size);
        map.setFont(1, font);
        Font f = map.getFont(1);
        assertNotNull(f);
        assertEquals("Arial", f.getName());
        assertEquals(font, f);
        assertEquals(size, f.getSize());
    }

}

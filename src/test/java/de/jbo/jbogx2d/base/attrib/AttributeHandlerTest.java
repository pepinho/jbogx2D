//
// Copyright (c) 2013 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    AttributeHandlerTest.java
// Created: 24.11.2013 - 15:50:25
//
package de.jbo.jbogx2d.base.attrib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.awt.TexturePaint;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.jbo.jbogx2d.base.Jbogx2D;

/**
 * @author Josef Baro (jbo)
 * @version 24.11.2013 jbo - created
 */
public class AttributeHandlerTest {

    /** The handler being tested. */
    private static AttributeHandler handler = null;

    /**
     * @throws java.lang.Exception
     *             Possible exception.
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Jbogx2D.init();
        handler = Jbogx2D.getAttributeHandler();
    }

    /**
     * @throws java.lang.Exception
     *             Possible exception.
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        handler = null;
        Jbogx2D.close();
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttributeHandler#getColor(short)}.
     */
    @Test
    public void testGetColor() {
        final short index = 0;
        Color c = handler.getColor(index);
        assertNotNull(c);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttributeHandler#setColor(short, java.lang.String, int, int, int)}
     * .
     */
    @Test
    public void testSetColor() {
        final short index = 0;
        final int red = 255;
        final int green = 24;
        final int blue = 24;
        handler.setColor(index, "myRed", red, green, blue);
        Color c = handler.getColor(index);
        assertEquals("myRed", c.toString());
        assertEquals(red, c.getRed());
        assertEquals(green, c.getGreen());
        assertEquals(blue, c.getBlue());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttributeHandler#getTexture(short, java.awt.Color, java.awt.Color)}
     * .
     */
    @Test
    public void testGetTexture() {
        final short index = 9;
        Color foreground = Color.blue;
        Color background = Color.yellow;
        TexturePaint texture = handler.getTexture(index, foreground, background);
        assertNotNull(texture);

    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttributeHandler#setTexture(short, boolean[][])}
     * .
     */
    @Test
    public void testSetTexture() {
        short id = 0;
        boolean[][] texture = { { true, true }, { false, false } };
        handler.setTexture(id, texture);
        assertNotNull(handler.getTexture(id, Color.black, Color.white));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttributeHandler#getStroke(short, float)}
     * .
     */
    @Test
    public void testGetStroke() {
        short index = 0;
        assertNotNull(handler.getStroke(index, 2.0f));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttributeHandler#setStroke(short, float[])}
     * .
     */
    @Test
    public void testSetStroke() {
        short index = 0;
        handler.setStroke(index, new float[] { 2.0f, 2.0f });
        assertNotNull(handler.getStroke(index, 2.0f));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttributeHandler#getGradient(java.awt.Color, java.awt.Color, de.jbo.jbogx2d.base.attrib.AttribGradientType)}
     * .
     */
    @Test
    public void testGetGradient() {
        assertNotNull(handler.getGradient(Color.black, Color.white, AttribGradientType.GRADIENT_HORIZONTAL));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttributeHandler#getFont(short)}.
     */
    @Test
    public void testGetFont() {
        short index = 0;
        assertNotNull(handler.getFont(index));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttributeHandler#setFont(short, java.lang.String)}
     * .
     */
    @Test
    public void testSetFont() {
        short index = 0;
        handler.setFont(index, "Arial");
        assertNotNull(handler.getFont(index));
    }

}

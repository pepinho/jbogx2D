//
// Copyright (c) 2013 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    AttribTextTest.java
// Created: 24.11.2013 - 16:16:11
//
package de.jbo.jbogx2d.test.junit.base.attrib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.jbo.jbogx2d.base.Jbogx2D;
import de.jbo.jbogx2d.base.attrib.AttribText;
import de.jbo.jbogx2d.base.elements.ElemText;
import de.jbo.jbogx2d.base.elements.model.ElemModelText;

/**
 * @author Josef Baro (jbo)
 * @version 24.11.2013 jbo - created
 */
public class AttribTextTest {

    /** The element to be tested. */
    private ElemText text = null;

    /** The element's model. */
    private ElemModelText model = null;

    /** The attribute to be tested. */
    private AttribText attrib = null;

    /**
     * @throws java.lang.Exception
     *             Possible exception.
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Jbogx2D.init();
        Jbogx2D.getAttributeHandler().setFont((short) 1, "Courier New");
    }

    /**
     * @throws java.lang.Exception
     *             Possible exception.
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        Jbogx2D.close();
    }

    /**
     * @throws java.lang.Exception
     *             Possible exception.
     */
    @Before
    public void setUp() throws Exception {
        text = new ElemText();
        model = (ElemModelText) text.getModel();
        attrib = model.getAttribText();
    }

    /**
     * @throws java.lang.Exception
     *             Possible exception.
     */
    @After
    public void tearDown() throws Exception {
        attrib = null;
        model = null;
        text = null;
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribText#update(de.jbo.jbogx2d.base.elements.model.ElemModel)}
     * .
     */
    @Test
    public void testUpdate() {
        final float linefeed = 1.5f;
        final float size = 24.0f;
        final float sizeSystem = 1.1328125f;
        final float ascent = 0.83251953f;
        final float descent = 0.30029297f;
        final HashMap<TextAttribute, Object> systemAttributes = attrib.getSystemAttributes();
        final Font font = Jbogx2D.getAttributeHandler().getFont((short) 1);
        final AffineTransform transform = new AffineTransform();

        assertTrue(systemAttributes.isEmpty());
        assertEquals(Color.black, attrib.getSystemColor());
        assertEquals(0.0f, attrib.getSystemAscent(), 0.0f);
        assertEquals(0.0f, attrib.getSystemDescent(), 0.0f);
        assertEquals(0.0f, attrib.getSystemHeight(), 0.0f);

        systemAttributes.put(TextAttribute.TRANSFORM, transform);

        attrib.setColor((short) 3);
        attrib.setFontId((short) 1);
        attrib.setLineFeed(linefeed);
        attrib.setSize(size);
        attrib.addStyle(AttribText.STYLE_BOLD);
        attrib.addStyle(AttribText.STYLE_ITALIC);
        attrib.addStyle(AttribText.STYLE_STRIKETHROUGH);
        attrib.addStyle(AttribText.STYLE_UNDERLINE);
        attrib.setStyle(attrib.getStyle());

        attrib.update(model);

        assertEquals(Color.green, attrib.getSystemColor());
        assertEquals(sizeSystem, attrib.getSystemHeight(), 0.0f);
        assertEquals(ascent, attrib.getSystemAscent(), 0.0f);
        assertEquals(descent, attrib.getSystemDescent(), 0.0f);
        assertEquals(linefeed, attrib.getLineFeed(), 0.0);

        assertEquals(font, systemAttributes.get(TextAttribute.FONT));
        assertEquals(font.getFamily(), systemAttributes.get(TextAttribute.FAMILY));
        assertEquals(Float.valueOf(size), systemAttributes.get(TextAttribute.SIZE));
        assertEquals(TextAttribute.WEIGHT_BOLD, systemAttributes.get(TextAttribute.WEIGHT));
        assertEquals(TextAttribute.POSTURE_OBLIQUE, systemAttributes.get(TextAttribute.POSTURE));
        assertEquals(TextAttribute.UNDERLINE_ON, systemAttributes.get(TextAttribute.UNDERLINE));
        assertEquals(TextAttribute.STRIKETHROUGH_ON, systemAttributes.get(TextAttribute.STRIKETHROUGH));
        assertEquals(transform, systemAttributes.get(TextAttribute.TRANSFORM));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribText#AttribText()}.
     */
    @Test
    public void testAttribText() {
        assertNotNull(attrib);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribText#addStyle(short)}.
     */
    @Test
    public void testAddStyle() {
        attrib.addStyle(AttribText.STYLE_BOLD);

        assertTrue((attrib.getStyle() & AttribText.STYLE_BOLD) == AttribText.STYLE_BOLD);
        assertFalse((attrib.getStyle() & AttribText.STYLE_ITALIC) == AttribText.STYLE_ITALIC);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribText#removeStyle(short)}.
     */
    @Test
    public void testRemoveStyle() {
        attrib.addStyle(AttribText.STYLE_BOLD);
        attrib.addStyle(AttribText.STYLE_ITALIC);
        attrib.removeStyle(AttribText.STYLE_ITALIC);

        assertTrue((attrib.getStyle() & AttribText.STYLE_BOLD) == AttribText.STYLE_BOLD);
        assertFalse((attrib.getStyle() & AttribText.STYLE_ITALIC) == AttribText.STYLE_ITALIC);
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.attrib.AttribText#getColor()}.
     */
    @Test
    public void testGetColor() {
        attrib.setColor((short) 3);
        assertEquals((short) 3, attrib.getColor());
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.attrib.AttribText#getFontId()}
     * .
     */
    @Test
    public void testGetFontId() {
        attrib.setFontId((short) 1);
        assertEquals((short) 1, attrib.getFontId());
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.attrib.AttribText#getSize()}.
     */
    @Test
    public void testGetSize() {
        final float size = 24.0f;
        attrib.setSize(size);
        assertEquals(size, attrib.getSize(), 0.0f);
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.attrib.AttribText#getStyle()}.
     */
    @Test
    public void testGetStyle() {
        attrib.addStyle(AttribText.STYLE_BOLD);
        attrib.addStyle(AttribText.STYLE_ITALIC);
        attrib.removeStyle(AttribText.STYLE_ITALIC);

        assertTrue((attrib.getStyle() & AttribText.STYLE_BOLD) == AttribText.STYLE_BOLD);
        assertFalse((attrib.getStyle() & AttribText.STYLE_ITALIC) == AttribText.STYLE_ITALIC);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribText#getSystemAttributes()}.
     */
    @Test
    public void testGetSystemAttributes() {
        assertTrue("See test of update()", true);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribText#getSystemColor()}.
     */
    @Test
    public void testGetSystemColor() {
        assertTrue("See test of update()", true);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribText#setColor(short)}.
     */
    @Test
    public void testSetColor() {
        assertTrue("See test of update()", true);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribText#setFontId(short)}.
     */
    @Test
    public void testSetFontId() {
        assertTrue("See test of update()", true);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribText#setSize(float)}.
     */
    @Test
    public void testSetSize() {
        assertTrue("See test of update()", true);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribText#setStyle(short)}.
     */
    @Test
    public void testSetStyle() {
        assertTrue("See test of update()", true);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribText#getLineFeed()}.
     */
    @Test
    public void testGetLineFeed() {
        assertTrue("See test of update()", true);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribText#setLineFeed(double)}.
     */
    @Test
    public void testSetLineFeed() {
        assertTrue("See test of update()", true);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribText#getSystemHeight()}.
     */
    @Test
    public void testGetSystemHeight() {
        assertTrue("See test of update()", true);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribText#getSystemAscent()}.
     */
    @Test
    public void testGetSystemAscent() {
        assertTrue("See test of update()", true);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribText#getSystemDescent()}.
     */
    @Test
    public void testGetSystemDescent() {
        assertTrue("See test of update()", true);
    }

}

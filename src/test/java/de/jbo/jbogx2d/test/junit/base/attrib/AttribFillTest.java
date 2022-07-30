//
// Copyright (c) 2013 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    AttribFillTest.java
// Created: 03.11.2013 - 16:12:37
//
package de.jbo.jbogx2d.test.junit.base.attrib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.TexturePaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.jbo.jbogx2d.base.Jbogx2D;
import de.jbo.jbogx2d.base.attrib.AttribColorMap;
import de.jbo.jbogx2d.base.attrib.AttribFill;
import de.jbo.jbogx2d.base.attrib.AttribFillTextureMap;
import de.jbo.jbogx2d.base.attrib.AttribFillType;
import de.jbo.jbogx2d.base.attrib.AttribGradientType;
import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.elements.ElemCircle;
import de.jbo.jbogx2d.base.elements.model.ElemModelCircle;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;

/**
 * @author Josef Baro (jbo)
 * @version 03.11.2013 jbo - created
 */
public class AttribFillTest {

    /** circle radius. */
    private static final double RADIUS = 10.0;

    /** texture-map. */
    private static AttribFillTextureMap textureMap = null;

    /** fill attribute. */
    private AttribFill fill = null;

    /** element. */
    private ElemBase elem = null;

    /**
     * Set-up class.
     */
    @BeforeClass
    public static void setUpClass() {
        Jbogx2D.init();
        textureMap = new AttribFillTextureMap();
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
     * Set-up.
     */
    @Before
    public void setUp() {
        elem = new ElemCircle(0.0, 0.0, RADIUS);
        fill = elem.getAttribFill();
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFill#update(de.jbo.jbogx2d.base.elements.model.ElemModel)}
     * .
     */
    @Test
    public void testUpdate() {
        Color backGround = fill.getSystemColorBackground();
        Color foreGround = fill.getSystemColorForeground();
        Paint gradient = fill.getSystemGradient();
        TexturePaint texture = fill.getSystemTexture();

        assertAllNull(backGround, foreGround, gradient, texture);;

        fill.setFillType(AttribFillType.TYPE_TEXTURE);
        elem.updateAttributes();

        backGround = fill.getSystemColorBackground();
        foreGround = fill.getSystemColorForeground();
        assertDefaultColors(backGround, foreGround);

        texture = fill.getSystemTexture();
        assertTexture(textureMap, fill.getTexture(), texture.getImage(), foreGround, backGround);

        fill.setFillType(AttribFillType.TYPE_GRADIENT);
        fill.setGradientType(AttribGradientType.GRADIENT_RADIAL);
        elem.updateAttributes();

        gradient = fill.getSystemGradient();
        assertNotNull(gradient);
        assertTrue(gradient instanceof RadialGradientPaint);
        RadialGradientPaint radial = (RadialGradientPaint) gradient;
        ElemModelCircle modelCircle = (ElemModelCircle) elem.getModel();
        assertEquals(radial.getCenterPoint(), new Point2D.Double(modelCircle.getCenterX(), modelCircle.getCenterY()));
        assertEquals(radial.getRadius(), (float) modelCircle.getRadius(), 0.0);
        Color[] colors = radial.getColors();
        assertEquals(foreGround, colors[0]);
        assertEquals(backGround, colors[1]);

        BoundsUserSpace bounds = new BoundsUserSpace();
        modelCircle.getBounds(bounds);

        fill.setGradientType(AttribGradientType.GRADIENT_HORIZONTAL);
        elem.updateAttributes();

        gradient = fill.getSystemGradient();
        assertNotNull(gradient);
        assertTrue(gradient instanceof GradientPaint);
        GradientPaint gradient2 = (GradientPaint) gradient;
        assertEquals(foreGround, gradient2.getColor1());
        assertEquals(backGround, gradient2.getColor2());
        assertEquals(bounds.x, gradient2.getPoint1().getX(), 0.0);
        assertEquals(bounds.x + bounds.width, gradient2.getPoint2().getX(), 0.0);

        fill.setGradientType(AttribGradientType.GRADIENT_VERTICAL);
        elem.updateAttributes();

        gradient = fill.getSystemGradient();
        assertNotNull(gradient);
        assertTrue(gradient instanceof GradientPaint);
        gradient2 = (GradientPaint) gradient;
        assertEquals(foreGround, gradient2.getColor1());
        assertEquals(backGround, gradient2.getColor2());
        assertEquals(bounds.y, gradient2.getPoint1().getY(), 0.0);
        assertEquals(bounds.y + bounds.height, gradient2.getPoint2().getY(), 0.0);

        fill.setFillType(AttribFillType.TYPE_SOLID);
        elem.updateAttributes();

        backGround = fill.getSystemColorBackground();
        foreGround = fill.getSystemColorForeground();
        assertDefaultColors(backGround, foreGround);

        final double alpha = 0.5;
        final int alphaModifier = 255;
        fill.setAlpha(alpha);
        assertEquals(alpha, fill.getAlpha(), 0.0);
        elem.updateAttributes();
        backGround = fill.getSystemColorBackground();
        foreGround = fill.getSystemColorForeground();
        Color expectedBackGround = new Color(AttribColorMap.WHITE.getRed(), AttribColorMap.WHITE.getGreen(), AttribColorMap.WHITE.getBlue(), (int) (alphaModifier * alpha));
        Color expectedForeGround = new Color(AttribColorMap.BLACK.getRed(), AttribColorMap.BLACK.getGreen(), AttribColorMap.BLACK.getBlue(), (int) (alphaModifier * alpha));
        assertEquals(expectedBackGround, backGround);
        assertEquals(expectedForeGround, foreGround);
    }

    private void assertDefaultColors(Color backGround, Color foreGround) {
        assertEquals(AttribColorMap.WHITE, backGround);
        assertEquals(AttribColorMap.BLACK, foreGround);
    }

    private void assertAllNull(Color backGround, Color foreGround, Paint gradient, TexturePaint texture) {
        assertNull(backGround);
        assertNull(foreGround);
        assertNull(gradient);
        assertNull(texture);
    }

    /**
     * Checks the given bitmap if it matches the texture id and the given
     * colors.
     * 
     * @param map
     *            The map to be used.
     * @param texture
     *            The texture to be checked.
     * @param img
     *            The texture-image to be checked.
     * @param foreGround
     *            The foreground color.
     * @param backGround
     *            The background color.
     */
    public static void assertTexture(AttribFillTextureMap map, short texture, BufferedImage img, Color foreGround, Color backGround) {
        boolean[][] pattern = map.getTexturePattern(texture);
        for (int r = 0; r < pattern.length; r++) {
            boolean[] row = pattern[r];
            for (int c = 0; c < row.length; c++) {
                int rgb = img.getRGB(r, c);
                int rgbCheck = (row[c]) ? foreGround.getRGB() : backGround.getRGB();
                assertEquals(rgbCheck, rgb);
            }
        }
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFill#AttribFill()}.
     */
    @Test
    public void testAttribFill() {
        assertNotNull(fill);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFill#getColorBackground()}.
     */
    @Test
    public void testGetColorBackground() {
        short c = fill.getColorBackground();
        assertEquals((short) 0, c);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFill#getColorForeground()}.
     */
    @Test
    public void testGetColorForeground() {
        short c = fill.getColorForeground();
        assertEquals((short) 1, c);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFill#getFillType()}.
     */
    @Test
    public void testGetFillType() {
        AttribFillType type = fill.getFillType();
        assertEquals(AttribFillType.TYPE_SOLID, type);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFill#getSystemColorBackground()}.
     */
    @Test
    public void testGetSystemColorBackground() {
        Color backGround = fill.getSystemColorBackground();
        assertNull(backGround);
        fill.update(elem.getModel());
        backGround = fill.getSystemColorBackground();
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFill#getSystemColorForeground()}.
     */
    @Test
    public void testGetSystemColorForeground() {
        Color foreGround = fill.getSystemColorForeground();
        assertNull(foreGround);
        fill.update(elem.getModel());
        foreGround = fill.getSystemColorForeground();
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFill#getSystemTexture()}.
     */
    @Test
    public void testGetSystemTexture() {
        fill.setFillType(AttribFillType.TYPE_TEXTURE);
        fill.update(elem.getModel());

        Color backGround = fill.getSystemColorBackground();
        Color foreGround = fill.getSystemColorForeground();

        TexturePaint texture = fill.getSystemTexture();
        assertTexture(textureMap, fill.getTexture(), texture.getImage(), foreGround, backGround);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFill#getTexture()}.
     */
    @Test
    public void testGetTexture() {
        final short textureDefault = 9;
        short t = fill.getTexture();
        assertEquals(textureDefault, t);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFill#setColorBackground(short)}.
     */
    @Test
    public void testSetColorBackground() {
        final short color = 24;
        fill.setColorBackground(color);
        assertEquals(color, fill.getColorBackground());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFill#setColorForeground(short)}.
     */
    @Test
    public void testSetColorForeground() {
        final short color = 24;
        fill.setColorForeground(color);
        assertEquals(color, fill.getColorForeground());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFill#setFillType(de.jbo.jbogx2d.base.attrib.AttribFillType)}
     * .
     */
    @Test
    public void testSetFillType() {
        AttribFillType type = AttribFillType.TYPE_HOLLOW;
        fill.setFillType(type);
        assertEquals(type, fill.getFillType());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFill#setTexture(short)}.
     */
    @Test
    public void testSetTexture() {
        final short texture = 2;
        fill.setTexture(texture);
        assertEquals(texture, fill.getTexture());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFill#getGradientType()}.
     */
    @Test
    public void testGetGradientType() {
        AttribGradientType type = AttribGradientType.GRADIENT_RADIAL;
        fill.setGradientType(type);
        assertEquals(type, fill.getGradientType());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFill#getSystemGradient()}.
     */
    @Test
    public void testGetSystemGradient() {
        fill.setFillType(AttribFillType.TYPE_GRADIENT);
        fill.setGradientType(AttribGradientType.GRADIENT_HORIZONTAL);
        fill.update(elem.getModel());

        Color foreGround = fill.getSystemColorForeground();
        Color backGround = fill.getSystemColorBackground();

        BoundsUserSpace bounds = new BoundsUserSpace();
        elem.getBounds(bounds);

        GradientPaint gradient = (GradientPaint) fill.getSystemGradient();
        assertNotNull(gradient);
        assertTrue(gradient instanceof GradientPaint);
        GradientPaint gradient2 = gradient;
        assertEquals(foreGround, gradient2.getColor1());
        assertEquals(backGround, gradient2.getColor2());
        assertEquals(bounds.x, gradient2.getPoint1().getX(), 0.0);
        assertEquals(bounds.x + bounds.width, gradient2.getPoint2().getX(), 0.0);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFill#setGradientType(de.jbo.jbogx2d.base.attrib.AttribGradientType)}
     * .
     */
    @Test
    public void testSetGradientType() {
        AttribGradientType type = AttribGradientType.GRADIENT_RADIAL;
        fill.setGradientType(type);
        assertEquals(type, fill.getGradientType());
    }

}

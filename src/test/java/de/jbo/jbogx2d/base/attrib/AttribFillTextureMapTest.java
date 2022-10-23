//
// Copyright (c) 2013 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    AttribFillTextureMapTest.java
// Created: 05.11.2013 - 06:02:10
//
package de.jbo.jbogx2d.base.attrib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.Arrays;

import org.junit.Test;

import de.jbo.jbogx2d.base.attrib.AttribFillTextureMap;

/**
 * @author Josef Baro (jbo)
 * @version 05.11.2013 jbo - created
 */
public class AttribFillTextureMapTest {

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFillTextureMap#setTexture(short, boolean[][])}
     * .
     */
    @Test
    public void testSetTexture() {
        AttribFillTextureMap map = new AttribFillTextureMap();
        final short id = 24;
        boolean[][] texture = { { true, false }, { false, true } };
        map.setTexture(id, texture);
        assertTexturePattern(texture, map.getTexturePattern(id));
    }

    /**
     * Checks the 2 patterns.
     * 
     * @param expected
     *            Expected pattern.
     * @param actual
     *            Actual pattern to match against.
     */
    private void assertTexturePattern(boolean[][] expected, boolean[][] actual) {
        for (int r = 0; r < expected.length; r++) {
            boolean[] row = expected[r];
            for (int c = 0; c < row.length; c++) {
                assertEquals(row[c], actual[r][c]);
            }
        }
    }

    /**
     * Compares the 2 images.
     * 
     * @param expected
     *            Expected image.
     * @param actual
     *            Actual image.
     */
    private void assertBufferedImages(BufferedImage expected, BufferedImage actual) {
        assertEquals(expected.getWidth(), actual.getWidth());
        assertEquals(expected.getHeight(), actual.getHeight());

        Raster rasterExpected = expected.getData();
        Raster rasterActual = actual.getData();
        for (int x = rasterExpected.getMinX(); x < rasterExpected.getWidth(); x++) {
            for (int y = rasterExpected.getMinY(); y < rasterExpected.getHeight(); y++) {
                int[] pixelsExpected = rasterExpected.getPixel(x, y, (int[]) null);
                int[] pixelsActual = rasterActual.getPixel(x, y, (int[]) null);
                assertTrue(Arrays.equals(pixelsExpected, pixelsActual));
            }
        }
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.attrib.AttribFillTextureMap#getTexturePattern(short)}
     * .
     */
    @Test
    public void testGetTexturePattern() {
        Color foreground = Color.red;
        Color background = Color.yellow;
        final short indexTooHigh = 1000;
        final short indexDefault = 9;
        AttribFillTextureMap map = new AttribFillTextureMap();

        // invalid index should always deliver a default solid pattern, equal to
        // index 9

        TexturePaint texture = map.getTexture(indexTooHigh, foreground, background);
        assertNotNull(texture);
        TexturePaint defaultTexture = map.getTexture(indexDefault, foreground, background);
        assertBufferedImages(texture.getImage(), defaultTexture.getImage());

        // creation of a texture with true and false values, so that both
        // background AND foreground values are used.
        boolean[][] pattern = { { true, false }, { false, true } };
        map.setTexture((short) 1, pattern);
        texture = map.getTexture((short) 1, foreground, background);
        AttribFillTest.assertTexture(map, (short) 1, texture.getImage(), foreground, background);
    }

}

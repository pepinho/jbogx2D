//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: Jbogx2dAttributeManager.java
// Created: 29.02.2004 - 07:22:14
//

package de.jbo.jbogx2d.base.attrib;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.TexturePaint;

/**
 * Base instance handling graphical element attributes. It provides access to <br>
 * <ul>
 * <li>line strokes</li>
 * <li>fill textures</li>
 * <li>fill gradients</li>
 * <li>color mappings</li>
 * <li>font mappings</li>
 * </ul>
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 29.02.2004: jbo created <br>
 */
public class AttributeHandler {
    /** Maps the line strokes. */
    private final AttribLineStrokeMap lineStrokeMap = new AttribLineStrokeMap();

    /** Maps fill-textures and gradients. */
    private final AttribFillTextureMap fillTextureMap = new AttribFillTextureMap();

    /** Maps the available colors. */
    private final AttribColorMap colorMap = new AttribColorMap();

    /** Maps the available font-names. */
    private final AttribFontMap fontMap = new AttribFontMap();

    /**
     * Returns the Color currently mapped for the given color index.
     * 
     * @param colorIndex
     *            The color index to return the mapped Color for.
     * @return The color currently mapped under the given index.
     */
    public final synchronized Color getColor(final short colorIndex) {
        return colorMap.getColor(colorIndex);
    }

    /**
     * Sets the new values for the color with the given index.
     * 
     * @param colorIndex
     *            The index to set the values for.
     * @param colorName
     *            The name of the color to be set.
     * @param red
     *            The red-value of the color from 0 to 255.
     * @param green
     *            The green-value of the color from 0 to 255.
     * @param blue
     *            The blue-value of the color from 0 to 255.
     */
    public final synchronized void setColor(final short colorIndex, final String colorName, final int red, final int green, final int blue) {
        colorMap.setColor(colorIndex, colorName, red, green, blue);
    }

    /**
     * Returns the Texture mapped for the given index.
     * 
     * @param id
     *            The texture index to return the mapped texture for.
     * @param foreground
     *            The foreground color for the texture.
     * @param background
     *            The background color for the texture.
     * @return The texture currently mapped under the given index.
     */
    public final synchronized TexturePaint getTexture(final short id, final Color foreground, final Color background) {
        return fillTextureMap.getTexture(id, foreground, background);
    }

    /**
     * Sets the texture-pattern to be mapped for the given pattern index.
     * 
     * @param patternId
     *            The index to store the pattern under in the map.
     * @param pattern
     *            The texture pattern to be mapped.
     */
    public final synchronized void setTexture(final short patternId, final boolean[][] pattern) {
        fillTextureMap.setTexture(patternId, pattern);
    }

    /**
     * Returns the stroke for the given values.
     * 
     * @param id
     *            The index of the mapped stroke to be returned.
     * @param lineWidth
     *            The width for the stroke.
     * @return The mapped stroke is returned.
     */
    public final synchronized BasicStroke getStroke(final short id, final float lineWidth) {
        return lineStrokeMap.getStroke(id, lineWidth);
    }

    /**
     * Sets a new stroke mapping for the given index.
     * 
     * @param strokeId
     *            The index to be mapped.
     * @param strokePattern
     *            The stroke-pattern to be mapped.
     */
    public final synchronized void setStroke(final short strokeId, final float[] strokePattern) {
        lineStrokeMap.setStroke(strokeId, strokePattern);
    }

    /**
     * Returns the gradient for the given values.
     * 
     * @param color1
     *            The first color of the gradient.
     * @param color2
     *            The second color of the gradient.
     * @param gradientType
     *            The gradient-type defining the dimensions of the gradient in
     *            world coordinates.
     * @return The gradient created with the values above.
     */
    public final synchronized Paint getGradient(final Color color1, final Color color2, final AttribGradientType gradientType) {
        return fillTextureMap.getGradient(color1, color2, gradientType);
    }

    /**
     * Returns the fontcurrently mapped for the given font index.
     * 
     * @param fontIndex
     *            The font index to return the mapped font-name for.
     * @return The font currently mapped under the given index.
     */
    public final synchronized Font getFont(final short fontIndex) {
        return fontMap.getFont(fontIndex);
    }

    /**
     * Sets the new values for the font with the given index.
     * 
     * @param fontIndex
     *            The index of the value to be set in the mapping.
     * @param fontName
     *            The value to be mapped for the given index.
     */
    public final synchronized void setFont(final short fontIndex, final String fontName) {
        fontMap.setFont(fontIndex, fontName);
    }

}
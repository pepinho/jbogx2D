//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: AttribFontMap.java
// Created: 10.03.2004 - 21:56:52
//

package de.jbo.jbogx2d.base.attrib;

import java.awt.Font;

import de.jbo.jbogx2d.base.util.collection.ArrayListX;

/**
 * Implements the component mapping font-indexes with font-implementations.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 28.02.2004: jbo created <br>
 */
public class AttribFontMap {
    /** Map storing the font-mappings. */
    private final ArrayListX<Font> fontMap = new ArrayListX<Font>();

    /** The default font-name. */
    public static final String DEFAULT_FONT_FAMILY = "Default";

    /** The default font. */
    public static final Font DEFAULT_FONT = createFont(DEFAULT_FONT_FAMILY);

    /**
     * Creates a new default font-map.
     */
    public AttribFontMap() {
        super();
        initDefaults();
    }

    /**
     * Creates a plain font based on the given font family name.
     * 
     * @param fontFamily
     *            The font family to be created.
     * @return The created font.
     */
    private static Font createFont(String fontFamily) {
        Font font = new Font(fontFamily, Font.PLAIN, 1);
        return font;
    }

    /**
     * Initializes the map with default color-values.
     */
    private void initDefaults() {
        fontMap.add(DEFAULT_FONT);
    }

    /**
     * Returns the font-name currently mapped for the given font index.
     * 
     * @param fontIndex
     *            The font index to return the mapped font-name for.
     * @return The font-name currently mapped under the given index.
     */
    public final synchronized Font getFont(final int fontIndex) {
        Font font = fontMap.get(fontIndex);
        if (font == null) {
            font = DEFAULT_FONT;
        }
        return font;
    }

    /**
     * Sets the new values for the font with the given index.
     * 
     * @param fontIndex
     *            The index of the value to be set in the mapping.
     * @param fontName
     *            The value to be mapped for the given index.
     */
    public final synchronized void setFont(final int fontIndex, final String fontName) {
        Font font = createFont(fontName);
        setFont(fontIndex, font);
    }

    /**
     * Sets the new values for the font with the given index.
     * 
     * @param fontIndex
     *            The index of the value to be set in the mapping.
     * @param font
     *            The value to be mapped for the given index.
     */
    public final synchronized void setFont(final int fontIndex, final Font font) {
        fontMap.set(fontIndex, font);
    }

}
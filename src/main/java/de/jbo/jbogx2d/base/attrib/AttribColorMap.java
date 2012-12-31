//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ColorMap.java
// Created: 28.02.2004 - 18:56:52
//

package de.jbo.jbogx2d.base.attrib;

import java.awt.Color;

import de.jbo.jbogx2d.base.util.collection.ArrayListX;

/**
 * Implements the component mapping color-indexes with Color-implementations.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 28.02.2004: jbo created <br>
 */
public class AttribColorMap {
    /**
     * 
     */
    public static final int DEFAULT_COLORS_COUNT = 32;

    /**
     * 
     */
    public static final NamedColor PALEGOLDENROD = new NamedColor("palegoldenrod", 255, 215, 0);

    /**
     * 
     */
    public static final NamedColor GREYSMOKE = new NamedColor("greysmoke", 238, 232, 170);

    /**
     * 
     */
    public static final NamedColor NAVYBLUE = new NamedColor("navyblue", 0, 0, 128);

    /**
     * 
     */
    public static final NamedColor CORNFLOWERBLUE = new NamedColor("cornflowerblue", 100, 149, 237);

    /**
     * 
     */
    public static final NamedColor DEEPSKYBLUE = new NamedColor("deepskyblue", 0, 191, 255);

    /**
     * 
     */
    public static final NamedColor DARKGREEN = new NamedColor("darkgreen", 0, 100, 0);

    /**
     * 
     */
    public static final NamedColor SEAGREEN = new NamedColor("seagreen", 46, 139, 87);

    /**
     * 
     */
    public static final NamedColor LAWNGREEN = new NamedColor("lawngreen", 124, 252, 0);

    /**
     * 
     */
    public static final NamedColor RED3 = new NamedColor("red3", 205, 0, 0);

    /**
     * 
     */
    public static final NamedColor VIOLETRED = new NamedColor("violetred", 208, 32, 144);

    /**
     * 
     */
    public static final NamedColor TOMATO = new NamedColor("tomato", 255, 99, 71);

    /**
     * 
     */
    public static final NamedColor ORANGERED = new NamedColor("orangered", 255, 69, 0);

    /**
     * 
     */
    public static final NamedColor GAINSBORO = new NamedColor("gainsboro", 220, 220, 220);

    /**
     * 
     */
    public static final NamedColor WHITESMOKE = new NamedColor("whitesmoke", 245, 245, 245);

    /**
     * 
     */
    public static final NamedColor GREYDARK = new NamedColor("greydark", 102, 102, 102);

    /**
     * 
     */
    public static final NamedColor GREYMEDIUM = new NamedColor("greymedium", 127, 127, 127);

    /**
     * 
     */
    public static final NamedColor GREYLIGHT = new NamedColor("greylight", 211, 211, 211);

    /**
     * 
     */
    public static final NamedColor PINK = new NamedColor("pink", 255, 192, 203);

    /**
     * 
     */
    public static final NamedColor PURPLE = new NamedColor("purple", 160, 32, 240);

    /**
     * 
     */
    public static final NamedColor VIOLET = new NamedColor("violet", 238, 130, 238);

    /**
     * 
     */
    public static final NamedColor ORANGE = new NamedColor("orange", 255, 165, 0);

    /**
     * 
     */
    private static final NamedColor BROWN = new NamedColor("brown", 165, 42, 42);

    /**
     * 
     */
    private static final NamedColor CHOCOLATE = new NamedColor("chocolate", 210, 105, 30);

    /**
     * 
     */
    private static final NamedColor CYAN = new NamedColor("cyan", 0, 255, 255);

    /**
     * 
     */
    private static final NamedColor MAGENTA = new NamedColor("magenta", 255, 0, 255);

    /**
     * 
     */
    public static final NamedColor YELLOW = new NamedColor("yellow", 255, 255, 0);

    /**
     * 
     */
    public static final NamedColor GREY = new NamedColor("grey", 192, 192, 192);

    /**
     * 
     */
    public static final NamedColor BLUE = new NamedColor("blue", 0, 0, 255);

    /**
     * 
     */
    public static final NamedColor GREEN = new NamedColor("green", 0, 255, 0);

    /**
     * 
     */
    public static final NamedColor RED = new NamedColor("red", 255, 0, 0);

    /**
     * 
     */
    public static final NamedColor BLACK = new NamedColor("black", 0, 0, 0);

    /**
     * 
     */
    public static final NamedColor WHITE = new NamedColor("white", 255, 255, 255);

    /** The default color. */
    public static final NamedColor DEFAULT = new NamedColor("default", 0, 0, 0);

    /** Map storing the color-mappings. */
    private final ArrayListX<NamedColor> colorMap = new ArrayListX<NamedColor>(256);

    /**
     * Creates a new default color-map.
     */
    public AttribColorMap() {
        super();
        initDefaults();
    }

    /**
     * Initializes the map with default color-values.
     */
    private void initDefaults() {
        colorMap.add(WHITE);
        colorMap.add(BLACK);
        colorMap.add(RED);
        colorMap.add(GREEN);
        colorMap.add(BLUE);
        colorMap.add(GREY);
        colorMap.add(YELLOW);
        colorMap.add(MAGENTA);
        colorMap.add(CYAN);
        colorMap.add(CHOCOLATE);
        colorMap.add(BROWN);
        colorMap.add(ORANGE);
        colorMap.add(VIOLET);
        colorMap.add(PURPLE);
        colorMap.add(PINK);
        colorMap.add(GREYLIGHT);
        colorMap.add(GREYMEDIUM);
        colorMap.add(GREYDARK);
        colorMap.add(WHITESMOKE);
        colorMap.add(GAINSBORO);
        colorMap.add(ORANGERED);
        colorMap.add(TOMATO);
        colorMap.add(VIOLETRED);
        colorMap.add(RED3);
        colorMap.add(LAWNGREEN);
        colorMap.add(SEAGREEN);
        colorMap.add(DARKGREEN);
        colorMap.add(DEEPSKYBLUE);
        colorMap.add(CORNFLOWERBLUE);
        colorMap.add(NAVYBLUE);
        colorMap.add(GREYSMOKE);
        colorMap.add(PALEGOLDENROD);

        for (int i = DEFAULT_COLORS_COUNT; i < colorMap.size(); i++) {
            colorMap.set(i, DEFAULT);
        }
    }

    /**
     * Returns the Color currently mapped for the given color index.
     * 
     * @param colorIndex
     *            The color index to return the mapped Color for.
     * @return The color currently mapped under the given index.
     */
    public final synchronized Color getColor(final int colorIndex) {
        Color c = colorMap.get(colorIndex);

        if (c == null) {
            c = AttribColorMap.DEFAULT;
        }

        return c;
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
    public final synchronized void setColor(final int colorIndex, final String colorName, final int red, final int green, final int blue) {
        colorMap.set(colorIndex, new NamedColor(colorName, red, green, blue));
    }

}
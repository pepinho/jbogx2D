//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ColorMap.java
// Created: 28.02.2004 - 18:56:52
//

package de.jbo.jbogx2d.base.attrib;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import de.jbo.jbogx2d.base.util.collection.ArrayListX;

/**
 * Implements the component mapping fill-textures and providing fill-gradients.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 29.02.2004: jbo created <br>
 */
public class AttribFillTextureMap {
    /** No fill. */
    private static final boolean[][] PATTERN0 = { { false, false, false, false }, { false, false, false, false }, { false, false, false, false }, { false, false, false, false } };

    /** 1/256 fill. */
    private static final boolean[][] PATTERN1 = { { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false } };

    /** 1/64 fill. */
    private static final boolean[][] PATTERN2 = { { true, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false } };

    /** 1/32 fill. */
    private static final boolean[][] PATTERN3 = { { true, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false }, { false, false, false, false, true, false, false, false }, { false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false } };

    /** 1/16 fill. */
    private static final boolean[][] PATTERN4 = { { true, false, false, false }, { false, false, false, false }, { false, false, false, false }, { false, false, false, false } };

    /** 1/8 fill. */
    private static final boolean[][] PATTERN5 = { { true, false, false, false }, { false, false, false, false }, { false, false, true, false }, { false, false, false, false } };

    /** 1/4 fill. */
    private static final boolean[][] PATTERN6 = { { true, false, true, false }, { false, false, false, false }, { true, false, true, false }, { false, false, false, false } };

    /** 1/2 fill. */
    private static final boolean[][] PATTERN7 = { { true, false, false, false }, { true, true, false, true }, { true, false, false, false }, { true, true, false, true } };

    /** 3/4 fill. */
    private static final boolean[][] PATTERN8 = { { true, false, true, false }, { false, true, false, true }, { true, false, true, false }, { false, true, false, true } };

    /** solid fill. */
    private static final boolean[][] PATTERN9 = { { true, true, true, true }, { true, true, true, true }, { true, true, true, true }, { true, true, true, true } };

    /** Hatch s.r. */
    private static final boolean[][] PATTERN10 = { { true, false, false, false, false, false, false, false }, { false, true, false, false, false, false, false, false }, { false, false, true, false, false, false, false, false },
            { false, false, false, true, false, false, false, false }, { false, false, false, false, true, false, false, false }, { false, false, false, false, false, true, false, false }, { false, false, false, false, false, false, true, false },
            { false, false, false, false, false, false, false, true } };

    /** Hatch b.r. */
    private static final boolean[][] PATTERN11 = { { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true } };

    /** Hatch s.l. */
    private static final boolean[][] PATTERN12 = { { false, false, false, false, false, false, false, true }, { false, false, false, false, false, false, true, false }, { false, false, false, false, false, true, false, false },
            { false, false, false, false, true, false, false, false }, { false, false, false, true, false, false, false, false }, { false, false, true, false, false, false, false, false }, { false, true, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false } };

    /** Hatch b.l. */
    private static final boolean[][] PATTERN13 = { { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false } };

    /** Square sml. */
    private static final boolean[][] PATTERN14 = { { true, true, true, true, true, true, true, true }, { true, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false } };

    /** Square big. */
    private static final boolean[][] PATTERN15 = { { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
            { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false } };

    /** Edge small. */
    private static final boolean[][] PATTERN16 = { { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false } };

    /** Line hori. */
    private static final boolean[][] PATTERN17 = { { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false } };

    /** Line vert. */
    private static final boolean[][] PATTERN18 = { { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false } };

    /** Hatch cross. */
    private static final boolean[][] PATTERN19 = { { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true },
            { false, true, false, false, false, false, false, false, false, false, false, false, false, false, true, false }, { false, false, true, false, false, false, false, false, false, false, false, false, false, true, false, false },
            { false, false, false, true, false, false, false, false, false, false, false, false, true, false, false, false }, { false, false, false, false, true, false, false, false, false, false, false, true, false, false, false, false },
            { false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false }, { false, false, false, false, false, false, true, false, false, true, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, true, false, false, true, false, false, false, false, false, false }, { false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false },
            { false, false, false, false, true, false, false, false, false, false, false, true, false, false, false, false }, { false, false, false, true, false, false, false, false, false, false, false, false, true, false, false, false },
            { false, false, true, false, false, false, false, false, false, false, false, false, false, true, false, false }, { false, true, false, false, false, false, false, false, false, false, false, false, false, false, true, false },
            { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true } };

    /** Dot hollow. */
    private static final boolean[][] PATTERN20 = { { false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false } };

    /** Gras. */
    private static final boolean[][] PATTERN21 = { { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, false, false, true, false, false, false, true, false, false, false, false, false, false, false }, { false, true, false, false, true, false, false, true, false, false, false, false, false, false, false, false },
            { false, false, true, false, true, false, true, false, false, false, false, false, false, false, false, false } };

    /** Water. */
    private static final boolean[][] PATTERN22 = { { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true },
            { false, true, false, false, false, false, false, false, false, false, false, false, false, false, true, false }, { false, false, true, false, false, false, false, false, false, false, false, false, false, true, false, false },
            { false, false, false, true, true, false, false, false, false, false, false, true, true, false, false, false }, { false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false },
            { false, false, false, false, false, false, true, false, false, true, false, false, false, false, false, false }, { false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true }, { false, true, false, false, false, false, false, false, false, false, false, false, false, false, true, false },
            { false, false, true, false, false, false, false, false, false, false, false, false, false, true, false, false }, { false, false, false, true, true, false, false, false, false, false, false, true, true, false, false, false },
            { false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false }, { false, false, false, false, false, false, true, false, false, true, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false } };

    /** Brickwall. */
    private static final boolean[][] PATTERN23 = { { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true }, { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
            { true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true }, { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
            { false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false } };

    /** Line hori. */
    private static final boolean[][] PATTERN24 = { { true, true, true, true, true, true, true, true }, { false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false } };

    /** Line vert. */
    private static final boolean[][] PATTERN25 = { { true, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false }, { true, false, false, false, false, false, false, false },
            { true, false, false, false, false, false, false, false } };

    /** Pattern 26. */
    private static final boolean[][] PATTERN26 = { { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true },
            { false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false }, { false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false },
            { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false }, { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true },
            { false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false }, { false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false },
            { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false }, { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true },
            { false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false }, { false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false },
            { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false }, { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true },
            { false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false }, { false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false },
            { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false }, };

    /** Pattern 27. */
    private static final boolean[][] PATTERN27 = { { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false },
            { false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false }, { false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false },
            { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true }, { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false },
            { false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false }, { false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false },
            { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true }, { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false },
            { false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false }, { false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false },
            { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true }, { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false },
            { false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false }, { false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false },
            { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true }, };

    /** Pattern 28. */
    private static final boolean[][] PATTERN28 = { { false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true },
            { false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false }, { false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true },
            { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false }, { false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true },
            { false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false }, { false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true },
            { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false }, { false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true },
            { false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false }, { false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true },
            { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false }, { false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true },
            { false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false }, { false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true },
            { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false }, };

    /** Pattern 29. */
    private static final boolean[][] PATTERN29 = { { true, false, false, false, false, false, false, true, true, false, false, false, false, false, false, true },
            { false, true, false, false, false, false, true, false, false, true, false, false, false, false, true, false }, { false, false, true, false, false, true, false, false, false, false, true, false, false, true, false, false },
            { false, false, false, true, true, false, false, false, false, false, false, true, true, false, false, false }, { false, false, false, true, true, false, false, false, false, false, false, true, true, false, false, false },
            { false, false, true, false, false, true, false, false, false, false, true, false, false, true, false, false }, { false, true, false, false, false, false, true, false, false, true, false, false, false, false, true, false },
            { true, false, false, false, false, false, false, true, true, false, false, false, false, false, false, true }, { true, false, false, false, false, false, false, true, true, false, false, false, false, false, false, true },
            { false, true, false, false, false, false, true, false, false, true, false, false, false, false, true, false }, { false, false, true, false, false, true, false, false, false, false, true, false, false, true, false, false },
            { false, false, false, true, true, false, false, false, false, false, false, true, true, false, false, false }, { false, false, false, true, true, false, false, false, false, false, false, true, true, false, false, false },
            { false, false, true, false, false, true, false, false, false, false, true, false, false, true, false, false }, { false, true, false, false, false, false, true, false, false, true, false, false, false, false, true, false },
            { true, false, false, false, false, false, false, true, true, false, false, false, false, false, false, true }, };

    /** Pattern 30. */
    private static final boolean[][] PATTERN30 = { { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, };

    /** Pattern 31. */
    private static final boolean[][] PATTERN31 = { { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false },
            { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false }, { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false },
            { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false }, { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false },
            { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false }, { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false },
            { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false }, { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false },
            { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false }, { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false },
            { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false }, { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false },
            { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false }, { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false },
            { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false }, };

    /** Pattern 32. */
    private static final boolean[][] PATTERN32 = { { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
            { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true }, { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true },
            { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true }, { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
            { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true }, { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true },
            { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true }, { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
            { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true }, { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true },
            { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true }, { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
            { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true }, { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true },
            { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true }, };

    /** Pattern 33. */
    private static final boolean[][] PATTERN33 = { { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false },
            { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false }, };

    /** Pattern 34. */
    private static final boolean[][] PATTERN34 = { { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false },
            { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true }, { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false },
            { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true }, { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false },
            { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true }, { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false },
            { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true }, { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false },
            { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true }, { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false },
            { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true }, { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false },
            { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true }, { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false },
            { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true }, };

    /** Map storing the texture-patterns. */
    private final ArrayListX<boolean[][]> patternMap = new ArrayListX<boolean[][]>();

    /** Internal variable. */
    private final Color[] radialGradientColors = new Color[2];

    /** Internal variable. */
    private final float[] radialGradientFractions = new float[] { 0.0f, 1.0f };

    /**
     * Creates a new default texture-map.
     */
    public AttribFillTextureMap() {
        super();
        initDefaults();
    }

    /**
     * Initializes the map with default texture-values.
     */
    private void initDefaults() {
        patternMap.add(PATTERN0);
        patternMap.add(PATTERN1);
        patternMap.add(PATTERN2);
        patternMap.add(PATTERN3);
        patternMap.add(PATTERN4);
        patternMap.add(PATTERN5);
        patternMap.add(PATTERN6);
        patternMap.add(PATTERN7);
        patternMap.add(PATTERN8);
        patternMap.add(PATTERN9);
        patternMap.add(PATTERN10);
        patternMap.add(PATTERN11);
        patternMap.add(PATTERN12);
        patternMap.add(PATTERN13);
        patternMap.add(PATTERN14);
        patternMap.add(PATTERN15);
        patternMap.add(PATTERN16);
        patternMap.add(PATTERN17);
        patternMap.add(PATTERN18);
        patternMap.add(PATTERN19);
        patternMap.add(PATTERN20);
        patternMap.add(PATTERN21);
        patternMap.add(PATTERN22);
        patternMap.add(PATTERN23);
        patternMap.add(PATTERN24);
        patternMap.add(PATTERN25);
        patternMap.add(PATTERN26);
        patternMap.add(PATTERN27);
        patternMap.add(PATTERN28);
        patternMap.add(PATTERN29);
        patternMap.add(PATTERN30);
        patternMap.add(PATTERN31);
        patternMap.add(PATTERN32);
        patternMap.add(PATTERN33);
        patternMap.add(PATTERN34);
    }

    /**
     * Creates a texture with the given pattern and color values.
     * 
     * @param pattern
     *            The boolean-array defining the texture pattern.
     * @param foreground
     *            The foreground color for the texture.
     * @param background
     *            The background color for the texture.
     * @return The new texture created with the values above.
     */
    private TexturePaint createTexture(final boolean[][] pattern, final Color foreground, final Color background) {
        BufferedImage bi = null;
        TexturePaint texture = null;

        // Create a buffered image texture patch of size 4x4
        bi = new java.awt.image.BufferedImage(pattern[0].length, pattern.length, BufferedImage.TYPE_INT_ARGB);

        // Create a texture paint from the buffered image
        Rectangle r = new Rectangle(0, 0, pattern[0].length, pattern.length);
        texture = new TexturePaint(bi, r);

        bi = texture.getImage();

        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length; j++) {
                if (pattern[i][j]) {
                    bi.setRGB(j, i, foreground.getRGB());
                } else {
                    bi.setRGB(j, i, background.getRGB());
                }
            }
        }

        return texture;
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

        boolean[][] texturePattern = null;
        TexturePaint texture = null;

        texturePattern = patternMap.get(id);

        if (texturePattern == null) {
            texturePattern = AttribFillTextureMap.PATTERN9;
        }
        texture = createTexture(texturePattern, foreground, background);

        return texture;
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
        Paint gradient = null;
        if (!gradientType.equals(AttribGradientType.GRADIENT_RADIAL)) {
            gradient = new GradientPaint((float) gradientType.getX1(), (float) gradientType.getY1(), color1, (float) gradientType.getX2(), (float) gradientType.getY2(), color2, false);
        } else {
            radialGradientColors[0] = color1;
            radialGradientColors[1] = color2;
            gradient = new RadialGradientPaint((float) gradientType.getX1(), (float) gradientType.getY1(), (float) gradientType.getX2(), radialGradientFractions, radialGradientColors);
        }
        return gradient;
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
        patternMap.set(patternId, pattern);
    }

}
//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ColorMap.java
// Created: 28.02.2004 - 18:56:52
//

package de.jbo.jbogx2d.base.attrib;

import java.awt.BasicStroke;

import de.jbo.jbogx2d.base.util.collection.ArrayListX;

/**
 * Implements the component mapping pen stroke-indexes with
 * stroke-implementations.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 29.02.2004: jbo created <br>
 */
public class AttribLineStrokeMap {
    /** Default pattern. */
    private static final float[] DEFAULT_HIDDEN = { 0.0f, 10.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_SOLID = { 20.0f, 0.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DOTTED = { 3.0f, 3.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASHED = { 17.0f, 6.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASHED_DOTTED = { 9.0f, 6.0f, 3.0f, 6.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASHED_DOT_DOT = { 10.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_LONG_DASHED = { 20.0f, 10.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASH_THREE_DOTTED = { 10.0f, 4.0f, 2.0f, 4.0f, 2.0f, 4.0f, 2.0f, 4.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_TWO_DASH_TWO_DOTTED = { 10.0f, 4.0f, 10.0f, 4.0f, 2.0f, 4.0f, 2.0f, 4.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASH02_02 = { 2.0f, 2.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASH02_06 = { 2.0f, 6.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASH02_14 = { 2.0f, 14.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASH04_06 = { 4.0f, 6.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASH04_12 = { 4.0f, 12.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASH08_08 = { 8.0f, 8.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASH10_06 = { 10.0f, 6.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASH12_03 = { 12.0f, 3.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASH04_02 = { 4.0f, 2.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASH02_10 = { 2.0f, 10.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASH10_10 = { 10.0f, 10.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASH10_06_02_06 = { 10.0f, 6.0f, 2.0f, 6.0f };

    /** Default pattern. */
    private static final float[] DEFAULT_DASH06_06 = { 6.0f, 6.0f };

    /** Map storing the stroke-mappings. */
    private final ArrayListX<float[]> strokeMap = new ArrayListX<float[]>();

    /**
     * Creates a new default stroke-map.
     */
    public AttribLineStrokeMap() {
        super();
        initDefaults();
    }

    /**
     * Initializes the map with default stroke-values.
     */
    private void initDefaults() {
        strokeMap.add(DEFAULT_HIDDEN);
        strokeMap.add(DEFAULT_SOLID);
        strokeMap.add(DEFAULT_DOTTED);
        strokeMap.add(DEFAULT_DASHED);
        strokeMap.add(DEFAULT_DASHED_DOTTED);
        strokeMap.add(DEFAULT_DASHED_DOT_DOT);
        strokeMap.add(DEFAULT_LONG_DASHED);
        strokeMap.add(DEFAULT_DASH_THREE_DOTTED);
        strokeMap.add(DEFAULT_TWO_DASH_TWO_DOTTED);
        strokeMap.add(DEFAULT_DASH02_02);
        strokeMap.add(DEFAULT_DASH02_06);
        strokeMap.add(DEFAULT_DASH02_14);
        strokeMap.add(DEFAULT_DASH04_06);
        strokeMap.add(DEFAULT_DASH04_12);
        strokeMap.add(DEFAULT_DASH08_08);
        strokeMap.add(DEFAULT_DASH10_06);
        strokeMap.add(DEFAULT_DASH12_03);
        strokeMap.add(DEFAULT_DASH04_02);
        strokeMap.add(DEFAULT_DASH02_10);
        strokeMap.add(DEFAULT_DASH10_10);
        strokeMap.add(DEFAULT_DASH10_06_02_06);
        strokeMap.add(DEFAULT_DASH06_06);
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

        float[] strokePattern = null;
        BasicStroke stroke = null;

        strokePattern = strokeMap.get(id);

        if (strokePattern == null) {
            strokePattern = AttribLineStrokeMap.DEFAULT_SOLID;
        }
        stroke = new BasicStroke(lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, strokePattern, 1.0f);

        return stroke;
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
        strokeMap.set(strokeId, strokePattern);
    }

}
//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ColorMap.java
// Created: 28.02.2004 - 18:56:52
//

package de.jbo.jbogx2d.base.attrib;

import static de.jbo.jbogx2d.base.attrib.AttribLineStroke.*;
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

    /** Map storing the stroke-mappings. */
    private final ArrayListX<float[]> strokeMap = new ArrayListX<>();

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
        strokeMap.add(DEFAULT_HIDDEN.getPattern());
        strokeMap.add(DEFAULT_SOLID.getPattern());
        strokeMap.add(DEFAULT_DOTTED.getPattern());
        strokeMap.add(DEFAULT_DASHED.getPattern());
        strokeMap.add(DEFAULT_DASHED_DOTTED.getPattern());
        strokeMap.add(DEFAULT_DASHED_DOT_DOT.getPattern());
        strokeMap.add(DEFAULT_LONG_DASHED.getPattern());
        strokeMap.add(DEFAULT_DASH_THREE_DOTTED.getPattern());
        strokeMap.add(DEFAULT_TWO_DASH_TWO_DOTTED.getPattern());
        strokeMap.add(DEFAULT_DASH02_02.getPattern());
        strokeMap.add(DEFAULT_DASH02_06.getPattern());
        strokeMap.add(DEFAULT_DASH02_14.getPattern());
        strokeMap.add(DEFAULT_DASH04_06.getPattern());
        strokeMap.add(DEFAULT_DASH04_12.getPattern());
        strokeMap.add(DEFAULT_DASH08_08.getPattern());
        strokeMap.add(DEFAULT_DASH10_06.getPattern());
        strokeMap.add(DEFAULT_DASH12_03.getPattern());
        strokeMap.add(DEFAULT_DASH04_02.getPattern());
        strokeMap.add(DEFAULT_DASH02_10.getPattern());
        strokeMap.add(DEFAULT_DASH10_10.getPattern());
        strokeMap.add(DEFAULT_DASH10_06_02_06.getPattern());
        strokeMap.add(DEFAULT_DASH06_06.getPattern());
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
        return AttribLineStroke.getStroke(strokeMap.get(id), lineWidth);
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
//
// Copyright (c) 2022 by jbo - Josef Baro
// 
// Project: jbogx2d-base
// File:    AttribLineStroke.java
// Created: 16.07.2022 - 12:40:51
//
package de.jbo.jbogx2d.base.attrib;

import java.awt.BasicStroke;

public enum AttribLineStroke {
    DEFAULT_HIDDEN(new float[]{  0.0f, 10.0f }),
    DEFAULT_SOLID(new float[] { 20.0f, 0.0f }),
    DEFAULT_DOTTED(new float[] { 3.0f, 3.0f }),
    DEFAULT_DASHED(new float[] { 17.0f, 6.0f }),
    DEFAULT_DASHED_DOTTED(new float[] { 9.0f, 6.0f, 3.0f, 6.0f }),
    DEFAULT_DASHED_DOT_DOT(new float[] { 10.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f }),
    DEFAULT_LONG_DASHED(new float[] { 20.0f, 10.0f }),
    DEFAULT_DASH_THREE_DOTTED(new float[] { 10.0f, 4.0f, 2.0f, 4.0f, 2.0f, 4.0f, 2.0f, 4.0f }),
    DEFAULT_TWO_DASH_TWO_DOTTED(new float[] { 10.0f, 4.0f, 10.0f, 4.0f, 2.0f, 4.0f, 2.0f, 4.0f }),
    DEFAULT_DASH02_02(new float[] { 2.0f, 2.0f }),
    DEFAULT_DASH02_06(new float[] { 2.0f, 6.0f }),
    DEFAULT_DASH02_14(new float[] { 2.0f, 14.0f }),
    DEFAULT_DASH04_06(new float[] { 4.0f, 6.0f }),
    DEFAULT_DASH04_12(new float[] { 4.0f, 12.0f }),
    DEFAULT_DASH08_08(new float[] { 8.0f, 8.0f }),
    DEFAULT_DASH10_06(new float[] { 10.0f, 6.0f }),
    DEFAULT_DASH12_03(new float[] { 12.0f, 3.0f }),
    DEFAULT_DASH04_02(new float[] { 4.0f, 2.0f }),
    DEFAULT_DASH02_10(new float[] { 2.0f, 10.0f }),
    DEFAULT_DASH10_10(new float[] { 10.0f, 10.0f }),
    DEFAULT_DASH10_06_02_06(new float[] { 10.0f, 6.0f, 2.0f, 6.0f }),
    DEFAULT_DASH06_06(new float[] { 6.0f, 6.0f });
    
    private float[] pattern = null;
    
    private AttribLineStroke(float[] strokePattern) {
        assert(strokePattern != null && strokePattern.length > 0);
        
        pattern = strokePattern;
    }
    
    public float[] getPattern() {
        float[] copy = new float[pattern.length];
        System.arraycopy(pattern, 0, copy, 0, copy.length);
        return copy;
    }
    
    public BasicStroke getStroke(final float lineWidth) {
        return getStroke(pattern, lineWidth);
    }
    
    public static BasicStroke getStroke(float[] pattern, final float lineWidth) {
        return new BasicStroke(lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, pattern, 1.0f);
    }
   
   
}

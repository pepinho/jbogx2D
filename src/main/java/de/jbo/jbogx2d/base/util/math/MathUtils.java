//
// Copyright (c) 2009 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: MathUtils.java
// Created: 10.10.2009 - 22:16:29
//
package de.jbo.jbogx2d.base.util.math;

import java.math.BigDecimal;

/**
 * Implements mathematical util functions.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 10.10.2009 jbo - created <br>
 */
public final class MathUtils {

    /**
     * Constructor.
     */
    private MathUtils() {
        // Nothing to do
    }

    /**
     * Rounds the given double value to an integer value.
     * 
     * @param d
     *            Value to be rounded.
     * @return The rounded integer value.
     */
    public static int round(final double d) {
        return (int) Math.round(d);
    }

    /**
     * Rounds the given value with the given scale.
     * 
     * @param value
     *            Value to be rounded.
     * @param scale
     *            Scale to round with.
     * @return The rounded value.
     */
    public static double round(double value, int scale) {
        BigDecimal val = BigDecimal.valueOf(value);
        val = val.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return val.doubleValue();
    }

    /**
     * Rounds the given value with the given scale.
     * 
     * @param value
     *            Value to be rounded.
     * @param scale
     *            Scale to round with.
     * @return The rounded value.
     */
    public static String roundToString(double value, int scale) {
        BigDecimal val = BigDecimal.valueOf(value);
        val = val.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return val.toString();
    }

}

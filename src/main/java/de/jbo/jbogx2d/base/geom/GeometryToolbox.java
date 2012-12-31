//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: GeometryToolbox.java
// Created: 11.03.2004 - 19:43:49
//
package de.jbo.jbogx2d.base.geom;

/**
 * Implements geometry utility methods.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 10.10.2009 jbo - created <br>
 */
public final class GeometryToolbox {
    /** Defines the value representing PI. */
    public static final double PI = Math.PI;

    /** Defines the value representing PI divided by 4. */
    public static final double PI1_4 = PI * 0.25;

    /** Defines the value representing PI divided by 2. */
    public static final double PI1_2 = PI / 2;

    /** Defines the value representing PI multiplied by 0.75 (three-quarters). */
    public static final double PI3_4 = PI * 0.75;

    /** Defines the value representing PI multiplied by 1.25 (five-quarters). */
    public static final double PI5_4 = PI * 1.25;

    /** Defines the value representing PI multiplied by 1.5 (three-halfs). */
    public static final double PI3_2 = PI * 1.5;

    /** Defines the value representing PI multiplied with 1.75 (seven-quarters). */
    public static final double PI7_4 = PI * 1.75;

    /** Defines the value representing PI multiplied by 2. */
    public static final double PI2 = PI * 2;

    /** Defines the SQRT value multiplied by 0.5 (one-half). */
    public static final double SQRT1_2 = 0.7071067811865476;

    /** Defines the SQRT value. */
    public static final double SQRT2 = 1.4142135623730952;

    /**
     * Constant defining a return value for the bounds intersection test. <br>
     * This value indicates that the first bounds completely include the second
     * bounds area.
     */
    public static final int BOUND_OVERLAP_INCLUSION_12 = 0;

    /**
     * Constant defining a return value for the bounds intersection test. <br>
     * This value indicates that the second bounds completely include the first
     * bounds area.
     */
    public static final int BOUND_OVERLAP_INCLUSION_21 = 1;

    /**
     * Constant defining a return value for the bounds intersection test. <br>
     * This value indicates that the first and the second bounds completely
     * exclude each other and have no intersection.
     */
    public static final int BOUND_OVERLAP_EXCLUSION = 2;

    /**
     * Constant defining a return value for the bounds intersection test. <br>
     * This value indicates that the both bounds areas intersect each other
     * without one completely including the other area.
     */
    public static final int BOUND_OVERLAP_INTERSECTION = 3;

    /**
     * Constructor.
     */
    private GeometryToolbox() {

    }
}
//
// Copyright (c) 2022 by jbo - Josef Baro
// 
// Project: jbogx2d-base
// File:    MathUtilsTest.java
// Created: 09.07.2022 - 08:37:48
//
package de.jbo.jbogx2d.base.util.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class MathUtilsTest {

    @Test
    public void roundDownWithDefaultDecimalScale() {
        double decimal = 2.49999;
        int expected = 2;
        
        assertEquals("The rounded value does not match!", expected, MathUtils.round(decimal));
    }
    
    @Test
    public void roundUpWithDefaultDecimalScale() {
        double decimal = 2.5;
        int expected = 3;
        
        assertEquals("The rounded value does not match!", expected, MathUtils.round(decimal));
    }
    
    @Test
    public void roundWithSpecificDecimalScale() {
        double decimal = 2.42567;
        int scale = 3;
        double expected = 2.426;
        
        assertEquals("The rounded value does not match!", expected, MathUtils.round(decimal, scale), 0.0);
    }
    
    @Test
    public void roundWithSpecificDecimalScaleToString() {
        double decimal = 2.42567;
        int scale = 3;
        String expected = "2.426";
        
        assertEquals("The rounded value does not match!", expected, MathUtils.roundToString(decimal, scale));
    }

}

//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: WrapperLong.java
// Created: 28.02.2004 - 19:08:48
//

package de.jbo.jbogx2d.base.util.lang;

/**
 * Implements a wrapper-class for a specific simple data-type. It can be used
 * e.g. for out-parameters in method-calls.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 28.02.2004: jbo created <br>
 */
public class WrapperDouble {
    /** The wrapped value. */
    public double value = 0;

    /**
     * Creates a new instance wrapping the given value.
     * 
     * @param val
     *            The value to be wrapped.
     */
    public WrapperDouble(double val) {
        value = val;
    }

    /**
     * Creates a new empty instance.
     */
    public WrapperDouble() {
        super();
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        boolean equals = false;

        if (obj instanceof WrapperDouble) {
            equals = ((WrapperDouble) obj).value == value;
        } else {
            equals = super.equals(obj);
        }

        return equals;
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (int) value;
    }

}
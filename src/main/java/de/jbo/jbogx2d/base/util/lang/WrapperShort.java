//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: WrapperLong.java
// Created: 28.02.2004 - 19:08:48
//

package de.jbo.jbogx2d.base.util.lang;

import de.jbo.jbogx2d.base.util.pooling.IPoolableObject;

/**
 * Implements a wrapper-class for a specific simple data-type. It can be used
 * e.g. for out-parameters in method-calls.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 29.02.2004: jbo created <br>
 */
public class WrapperShort implements IPoolableObject {
    /** The wrapped value. */
    public short value = 0;

    /**
     * Creates a new instance wrapping the given value.
     * 
     * @param val
     *            The value to be wrapped.
     */
    public WrapperShort(short val) {
        value = val;
    }

    /**
     * Creates a new empty instance.
     */
    public WrapperShort() {
        super();
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        boolean equals = false;

        if (obj instanceof WrapperShort) {
            equals = ((WrapperShort) obj).value == value;
        } else {
            equals = super.equals(obj);
        }

        return equals;
    }

    /*
     * @see de.jbo.jbogx2d.base.util.pooling.PoolableObject#onActivate()
     */
    @Override
    public void onActivate() throws Exception {
        // nothing to be done
    }

    /*
     * @see de.jbo.jbogx2d.base.util.pooling.PoolableObject#onDestroy()
     */
    @Override
    public void onDestroy() throws Exception {
        // nothing to be done
    }

    /*
     * @see de.jbo.jbogx2d.base.util.pooling.PoolableObject#onPassivate()
     */
    @Override
    public void onPassivate() throws Exception {
        // nothing to be done
    }

    /*
     * @see de.jbo.jbogx2d.base.util.pooling.PoolableObject#validate()
     */
    @Override
    public boolean validate() {
        return true;
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return value;
    }

}
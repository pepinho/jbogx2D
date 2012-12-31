//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: PoolableObject.java
// Created: 28.02.2004 - 19:57:01
//

package de.jbo.jbogx2d.base.util.pooling;

/**
 * Defines the interface that all objects must implement in order to be poolable
 * within the object-pooling framework.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 28.02.2004: jbo created <br>
 */
public interface IPoolableObject {
    /**
     * This method is triggered, when the object is acitvated, before being
     * borrowed from the pool.
     * 
     * @throws Exception
     *             Error occured during operation.
     */
    void onActivate() throws Exception;

    /**
     * This method is triggered, when the object is passivated, before being
     * returned to the pool.
     * 
     * @throws Exception
     *             Error occured during operation.
     */
    void onPassivate() throws Exception;

    /**
     * This method is triggered, when before the object is being destroyed, by
     * the pool instance.
     * 
     * @throws Exception
     *             Error occured during operation.
     */
    void onDestroy() throws Exception;

    /**
     * Validates the object for the usage in the pool.
     * 
     * @return True if object is valid and can be used within the pool, False
     *         otherwise.
     */
    boolean validate();
}
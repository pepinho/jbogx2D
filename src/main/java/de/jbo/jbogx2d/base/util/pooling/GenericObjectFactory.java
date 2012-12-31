//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: GenericObjectFactory.java
// Created: 28.02.2004 - 19:55:44
//

package de.jbo.jbogx2d.base.util.pooling;

import org.apache.commons.pool.PoolableObjectFactory;

/**
 * Implements an object-factory for generic creation of objects.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 28.02.2004: jbo created <br>
 */
public class GenericObjectFactory implements PoolableObjectFactory {
    /** The class-type of objects being created. */
    private Class<? extends IPoolableObject> classType = null;

    /**
     * Creates a new instance providing objects of the given class-type.
     * 
     * @param type
     *            The type of the object being created.
     */
    public GenericObjectFactory(final Class<? extends IPoolableObject> type) {
        super();
        setClassType(type);
    }

    /**
     * @param ct
     *            The class type to be used when generating new object
     *            instances.
     */
    public final void setClassType(final Class<? extends IPoolableObject> ct) {
        classType = ct;
    }

    /**
     * Creates a new Object to be used within a pool.
     * 
     * @return The created object.
     * @throws java.lang.Exception
     *             Error occured during creation.
     * @see org.apache.commons.pool.PoolableObjectFactory#makeObject()
     */
    public final Object makeObject() throws Exception {
        Object obj = classType.newInstance();
        return obj;
    }

    /**
     * Destroys the given object.
     * 
     * @param obj
     *            The object to be destroyed.
     * @throws java.lang.Exception
     *             Error occured during operation.
     * @see org.apache.commons.pool.PoolableObjectFactory#destroyObject(java.lang.Object)
     */
    public final void destroyObject(final Object obj) throws Exception {
        ((IPoolableObject) obj).onDestroy();
    }

    /**
     * Validates the given object.
     * 
     * @param obj
     *            The object to be validated.
     * @return True if object is valid, False otherwise.
     * @see org.apache.commons.pool.PoolableObjectFactory#validateObject(java.lang.Object)
     */
    public final boolean validateObject(final Object obj) {
        return ((IPoolableObject) obj).validate();
    }

    /**
     * Activates the given object before being borrowed from the pool.
     * 
     * @param obj
     *            The object to be activated.
     * @throws java.lang.Exception
     *             Error occured during the operation.
     * @see org.apache.commons.pool.PoolableObjectFactory#activateObject(java.lang.Object)
     */
    public final void activateObject(final Object obj) throws Exception {
        ((IPoolableObject) obj).onActivate();
    }

    /**
     * Uninitializes the given object when being returned to the pool.
     * 
     * @param obj
     *            The object to be passivated.
     * @throws java.lang.Exception
     *             Error occured during the operation.
     * @see org.apache.commons.pool.PoolableObjectFactory#passivateObject(java.lang.Object)
     */
    public final void passivateObject(final Object obj) throws Exception {
        ((IPoolableObject) obj).onPassivate();
    }
}
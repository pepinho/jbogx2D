//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ObjectPoolProvider.java
// Created: 28.02.2004 - 19:52:20
//

package de.jbo.jbogx2d.base.util.pooling;

import java.util.HashMap;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

import de.jbo.jbogx2d.base.Jbogx2D;

/**
 * Implements a central class for providing global static access to several
 * object-pooling functionalities. <br>
 * ObjectPool instance can be obtained based on a given class-type for generic
 * object pooling.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 28.02.2004: jbo created <br>
 */
public final class ObjectPoolProvider {
    /** Stores the object pools provided by this class. */
    private static HashMap<Class<? extends IPoolableObject>, GenericObjectPool> objectPoolMap = new HashMap<Class<? extends IPoolableObject>, GenericObjectPool>();

    /**
     * Creates a new instance.
     */
    private ObjectPoolProvider() {

    }

    /**
     * Returns the object pool providing objects of the given class type.
     * 
     * @param classType
     *            The class type to return the pool instance for.
     * @return The object-pool responsible for the given class type.
     */
    public static ObjectPool getPool(Class<? extends IPoolableObject> classType) {
        GenericObjectPool pool = objectPoolMap.get(classType);

        if (pool == null) {
            pool = new GenericObjectPool(new GenericObjectFactory(classType));
            pool.setMaxActive(-1);
            objectPoolMap.put(classType, pool);
        }

        return pool;
    }

    /**
     * Returns the given borrowed object to the given object pool.
     * 
     * @param objPool
     *            The object pool to return the object to.
     * @param obj
     *            The object to be returned to the given pool.
     * @return True if object was returned successfully, otherwise the
     *         ErrorHandler of the base module can be checked.
     */
    public static boolean returnObject(ObjectPool objPool, Object obj) {
        boolean state = false;

        try {
            objPool.returnObject(obj);
            state = true;
        } catch (Exception e) {
            Jbogx2D.getErrorHandler().handleFatalError(e, true, true);
        }

        return state;
    }

    /**
     * Returns an object out of the given object-pool and marks it as used.
     * 
     * @param objPool
     *            The pool to be handled.
     * @return The borrowed object.
     */
    public static Object borrowObject(ObjectPool objPool) {
        Object obj = null;

        try {
            obj = objPool.borrowObject();
        } catch (Exception e) {
            Jbogx2D.getErrorHandler().handleFatalError(e, true, true);
        }

        return obj;
    }
}
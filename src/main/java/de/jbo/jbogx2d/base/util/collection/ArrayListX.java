//
// Copyright (c) 2008 by jbo - Josef Baro
//
// Project: jbogx2D
// File: ArrayListX.java
// Created: 01.08.2008 - 23:03:12
//
package de.jbo.jbogx2d.base.util.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implements an array based collection. As a difference to Java's standard
 * ArrayList, this one can set its items explicitly via indices. Therefore,
 * items can be null, if not filled correctly.
 * 
 * @param <E>
 *            Base-type to be stored.
 * @author Josef Baro (jbo)
 * @version 01.08.2008 jbo - created
 */
public class ArrayListX<E> implements Collection<E> {
    /**
     * Default size.
     */
    public static final int DEFAULT_SIZE = 10;

    /**
     * The default increase amount.
     */
    private static final int DEFAULT_INCREASE_AMOUNT = 10;

    /**
     * The data array.
     */
    private E[] data = null;

    /** The current index when using add() or remove(). */
    private int currentIndex = 0;

    /**
     * Creates a new list with a default.
     */
    public ArrayListX() {
        this(DEFAULT_SIZE);
    }

    /**
     * Creates a new array with the given size.
     * 
     * @param initialSize
     *            The size to be set.
     */
    @SuppressWarnings("unchecked")
    public ArrayListX(int initialSize) {
        data = (E[]) new Object[initialSize];
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Collection#add(java.lang.Object)
     */
    @Override
    public boolean add(E e) {
        if (currentIndex >= data.length) {
            increaseSize((currentIndex - data.length) + DEFAULT_INCREASE_AMOUNT);
        }
        data[currentIndex++] = e;
        return true;
    }

    /**
     * Sets the given item at the given index.
     * 
     * @param index
     *            The index to be set.
     * @param e
     *            The item to be set.
     * @return True if the collection was changed.
     */
    public boolean set(int index, E e) {
        if (index >= data.length) {
            increaseSize((index - data.length) + DEFAULT_INCREASE_AMOUNT);
        }
        data[index] = e;
        currentIndex = index;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Collection#addAll(java.util.Collection)
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        Iterator<? extends E> it = c.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Collection#clear()
     */
    @Override
    public void clear() {
        for (int i = 0; i < data.length; i++) {
            data[i] = null;
        }
        currentIndex = 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Collection#contains(java.lang.Object)
     */
    @Override
    public boolean contains(Object o) {
        boolean ret = false;
        if (o != null) {
            for (E item : data) {
                if ((item != null) && item.equals(o)) {
                    ret = true;
                    break;
                }
            }
        }
        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Collection#containsAll(java.util.Collection)
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        boolean ret = true;
        Iterator<?> it = c.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Collection#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        boolean ret = true;
        for (Object o : data) {
            if (o != null) {
                ret = false;
                break;
            }
        }
        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Collection#iterator()
     */
    @Override
    public Iterator<E> iterator() {
        return new IteratorArrayListX();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Collection#remove(java.lang.Object)
     */
    @Override
    public boolean remove(Object o) {
        boolean ret = false;
        int index = indexOf(o);
        if (index >= 0) {
            data[index] = null;
            currentIndex = index;
            ret = true;
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Collection#removeAll(java.util.Collection)
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean state = false;
        Iterator<?> it = c.iterator();
        while (it.hasNext()) {
            if (remove(it.next())) {
                state = true;
            }
        }
        return state;
    }

    /**
     * Evaluates the index of the given item. If it is not found, -1 is
     * returned.
     * 
     * @param item
     *            The item to be checked.
     * @return The index or -1 if not found.
     */
    public int indexOf(Object item) {
        int index = -1;

        for (int i = 0; i < data.length; i++) {
            E temp = data[i];
            if ((temp != null) && temp.equals(item)) {
                index = i;
                break;
            }
        }

        return index;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Collection#retainAll(java.util.Collection)
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean state = false;

        for (int i = 0; i < data.length; i++) {
            if (!c.contains(data[i])) {
                remove(data[i]);
                state = true;
            }
        }

        return state;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Collection#size()
     */
    @Override
    public final int size() {
        return data.length;
    }

    /**
     * 
     * @return Collection of not-null-elements in this collection.
     */
    private Collection<E> getNotNullElements() {
        Collection<E> ret = new LinkedList<E>();

        for (E obj : data) {
            if (obj != null) {
                ret.add(obj);
            }
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Collection#toArray()
     */
    @Override
    public Object[] toArray() {
        Object[] array = getNotNullElements().toArray();
        return array;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Collection#toArray(T[])
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        Collection<E> notNullElements = getNotNullElements();

        if (a.length < notNullElements.size()) {
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(notNullElements.toArray(), notNullElements.size(), a.getClass());
        }
        System.arraycopy(notNullElements.toArray(), 0, a, 0, notNullElements.size());
        if (a.length > size()) {
            for (int i = size(); i < a.length; i++) {
                a[i] = null;
            }
        }
        return a;
    }

    /**
     * Increases the size by default value. The index is set to the first new
     * position.
     * 
     * @param increaseBy
     *            Amount to increase by.
     */
    @SuppressWarnings("unchecked")
    private void increaseSize(int increaseBy) {
        E[] old = data;
        data = (E[]) new Object[old.length + increaseBy];
        System.arraycopy(old, 0, data, 0, old.length);
        currentIndex = old.length;
    }

    /**
     * Returns the item at the given index.
     * 
     * @param index
     *            The index to return the stored object at.
     * @return The stored object.
     */
    public E get(int index) {
        E ret = null;

        if ((index >= 0) && (index < data.length)) {
            ret = data[index];
        }

        return ret;
    }

    /**
     * Iterator for this collection.
     * 
     * @author Josef Baro (jbo)
     * @version 01.08.2008 jbo - created
     */
    private class IteratorArrayListX implements Iterator<E> {
        /** Current index we are traversing. */
        private int index = 0;

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#hasNext()
         */
        @Override
        public boolean hasNext() {
            return index < data.length;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#next()
         */
        @Override
        public E next() {
            E ret = null;
            if (index < data.length) {
                ret = data[index++];
            }
            return ret;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove() {
            if (index < data.length) {
                data[index] = null;
            }
        }

    }
}

//
// Copyright (c) 2013 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    ArrayListXTest.java
// Created: 02.11.2013 - 14:18:28
//
package de.jbo.jbogx2d.test.junit.base.util.collection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.Test;

import de.jbo.jbogx2d.base.util.collection.ArrayListX;

/**
 * @author Josef Baro (jbo)
 * @version 02.11.2013 jbo - created
 */
public class ArrayListXTest {

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#ArrayListX()}.
     */
    @Test
    public void testArrayListX() {
        ArrayListX<String> list = new ArrayListX<String>();
        assertEquals("Default size is incorrect.", ArrayListX.DEFAULT_SIZE, list.size());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#ArrayListX(int)}.
     */
    @Test
    public void testArrayListXInt() {
        ArrayListX<String> list = new ArrayListX<String>(5);
        assertEquals("Default size is incorrect.", 5, list.size());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#add(java.lang.Object)}
     * .
     */
    @Test
    public void testAdd() {
        final int resizeSize = 11;
        ArrayListX<String> list = new ArrayListX<String>(1);
        list.add("0");
        assertEquals("Size after 1st add() is wrong.", 1, list.size());
        list.add("1");
        assertEquals("Size after 2nd add() is wrong.", resizeSize, list.size());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#set(int, java.lang.Object)}
     * .
     */
    @Test
    public void testSet() {
        final int setIndex = 15;
        ArrayListX<String> list = new ArrayListX<String>(1);
        list.set(0, "0");
        assertEquals("Size after set(0) is wrong.", 1, list.size());
        list.set(5, "5");
        assertEquals("Size after set(5) is wrong.", setIndex, list.size());
        assertEquals("Element on index 5 is wrong.", "5", list.get(5));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#addAll(java.util.Collection)}
     * .
     */
    @Test
    public void testAddAll() {
        final int resizeSize = 11;
        ArrayListX<String> list = new ArrayListX<String>(1);
        list.add("0");
        assertEquals("Size after 1st add() is wrong.", 1, list.size());
        ArrayListX<String> list2 = new ArrayListX<String>(1);
        list2.add("2");
        list.addAll(list2);
        assertEquals("Size after addAll() is wrong.", resizeSize, list.size());
        assertEquals("Element on index 1 is wrong.", "2", list.get(1));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#clear()}.
     */
    @Test
    public void testClear() {
        ArrayListX<String> list = new ArrayListX<String>(1);
        list.add("0");
        assertEquals("Size after 1st add() is wrong.", 1, list.size());
        assertEquals("Element on index 0 is wrong.", "0", list.get(0));
        list.clear();
        assertNull("Element should be NULL after clear().", list.get(0));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#contains(java.lang.Object)}
     * .
     */
    @Test
    public void testContains() {
        ArrayListX<String> list = new ArrayListX<String>(1);
        list.add("0");
        assertTrue("Element 0 should be found by contains().", list.contains("0"));
        assertFalse("Element 1 should NOT be found by contains().", list.contains("1"));
        list.contains(null);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#containsAll(java.util.Collection)}
     * .
     */
    @Test
    public void testContainsAll() {
        ArrayListX<String> list = new ArrayListX<String>(1);
        list.add("0");
        list.add("1");
        LinkedList<String> containListComplete = new LinkedList<String>();
        containListComplete.add("1");
        containListComplete.add("0");
        assertTrue("All elements should be found via containsAll().", list.containsAll(containListComplete));
        containListComplete.add("2");
        assertFalse("Not all elements should be found via containsAll().", list.containsAll(containListComplete));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {
        ArrayListX<String> list = new ArrayListX<String>(1);
        assertTrue("Should be empty.", list.isEmpty());
        list.add("0");
        assertFalse("Should NOT be empty.", list.isEmpty());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#iterator()}.
     */
    @Test
    public void testIterator() {
        final int resizeSize = 11;
        ArrayListX<String> list = new ArrayListX<String>(1);
        list.add("0");
        list.add("1");

        Iterator<String> it = list.iterator();
        assertNotNull("Iterator should NOT be NULL.", it);
        assertTrue("Iterator should point to next element.", it.hasNext());
        assertEquals("Iterator should point to element 0", "0", it.next());
        assertTrue("Iterator should point to next element.", it.hasNext());
        assertEquals("Iterator should point to element 1", "1", it.next());
        for (int i = 2; i < resizeSize; i++) {
            assertTrue("Iterator should NOT have reached the end.", it.hasNext());
            it.next();
        }
        assertFalse("Iterator should have reached the end.", it.hasNext());
        boolean exceptionThrown = false;
        try {
            assertNull(it.next());            
        } catch (NoSuchElementException ex) {
            exceptionThrown = true;
        }
        assertTrue("Exception should have been thrown.", exceptionThrown);
        it.remove();
        it = list.iterator();
        it.remove();
        assertNull("1st element should be NULL.", list.get(0));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#remove(java.lang.Object)}
     * .
     */
    @Test
    public void testRemove() {
        ArrayListX<String> list = new ArrayListX<String>(1);
        list.add("0");
        list.add("1");
        assertTrue("Element 1 is still contained.", list.contains("1"));
        list.remove("1");
        assertFalse("Element 1 is not contained anymore.", list.contains("1"));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#removeAll(java.util.Collection)}
     * .
     */
    @Test
    public void testRemoveAll() {
        ArrayListX<String> list = new ArrayListX<String>(1);
        list.add("0");
        list.add("1");
        assertTrue("Element 1 is still contained.", list.contains("1"));
        LinkedList<String> removeList = new LinkedList<String>();
        removeList.add("1");
        boolean ret = list.removeAll(removeList);
        assertFalse("Element 1 is not contained anymore.", list.contains("1"));
        assertTrue("Return value was false.", ret);
        removeList.add("3");
        ret = list.removeAll(removeList);
        assertFalse("Return value was not false, although no element to be removed matched.", ret);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#indexOf(java.lang.Object)}
     * .
     */
    @Test
    public void testIndexOf() {
        ArrayListX<String> list = new ArrayListX<String>(1);
        list.add("0");
        list.add("1");
        assertEquals("Index of element 0 is wrong.", 0, list.indexOf("0"));
        assertEquals("Index of element 1 is wrong.", 1, list.indexOf("1"));
        assertEquals("Index of element 2 is wrong.", -1, list.indexOf("2"));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#retainAll(java.util.Collection)}
     * .
     */
    @Test
    public void testRetainAll() {
        ArrayListX<String> list = new ArrayListX<String>(1);
        list.add("0");
        list.add("1");
        LinkedList<String> retainList = new LinkedList<String>();
        retainList.add("1");
        list.retainAll(retainList);
        assertFalse("Element 0 is NOT contained anymore.", list.contains("0"));
        assertTrue("Element 1 is still contained.", list.contains("1"));
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#size()}.
     */
    @Test
    public void testSize() {
        final int resizeSize = 11;
        ArrayListX<String> list = new ArrayListX<String>(1);
        list.add("0");
        assertEquals("Size after 1st add() is wrong.", 1, list.size());
        list.add("1");
        assertEquals("Size after 2nd add() is wrong.", resizeSize, list.size());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#toArray()}.
     */
    @Test
    public void testToArray() {
        ArrayListX<String> list = new ArrayListX<String>(1);
        list.add("0");
        list.add("1");

        Object[] array = list.toArray();
        assertEquals("The array's length is wrong.", 2, array.length);
        assertArrayEquals(new Object[] { "0", "1" }, array);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#toArray(T[])}.
     */
    @Test
    public void testToArrayTArray() {
        final int lengthTooLong = 12;
        ArrayListX<String> list = new ArrayListX<String>(1);
        list.add("0");
        list.add("1");

        String[] tooShort = new String[1];
        String[] ret = list.toArray(tooShort);
        assertEquals("The returned array should be as long as the list.", 2, ret.length);
        assertArrayEquals(list.toArray(), ret);
        String[] tooLong = new String[lengthTooLong];
        ret = list.toArray(tooLong);
        assertEquals("The returned array should be as long as the list.", tooLong.length, ret.length);
        assertEquals(list.get(0), ret[0]);
        assertEquals(list.get(1), ret[1]);
        assertNull(ret[2]);

        String[] exact = new String[list.size()];
        ret = list.toArray(exact);
        assertEquals(list.get(0), ret[0]);
        assertEquals(list.get(1), ret[1]);
        assertNull(ret[2]);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.collection.ArrayListX#get(int)}.
     */
    @Test
    public void testGet() {
        final int farTooLongIndex = 99;
        ArrayListX<String> list = new ArrayListX<String>(1);
        list.add("0");
        list.add("1");
        assertEquals("0", list.get(0));
        assertEquals("1", list.get(1));
        assertNull(list.get(farTooLongIndex));
        assertNull(list.get(-1));
    }

}

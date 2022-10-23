//
// Copyright (c) 2014 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    ElemModelPolylineTest.java
// Created: 02.01.2014 - 12:38:18
//
package de.jbo.jbogx2d.base.elements.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.jbo.jbogx2d.base.elements.ElemCurvedPolyline;
import de.jbo.jbogx2d.base.elements.model.ElemModelCurvedPolyline;
import de.jbo.jbogx2d.base.elements.model.shapes.CurvedPolyline2D;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * @author Josef Baro (jbo)
 * @version 06.01.2014 jbo - created
 */
public class ElemModelCurvedPolylineTest {
    /** test-coordinate. */
    private static final double X1 = 10.0;

    /** test-coordinate. */
    private static final double Y1 = 10.0;

    /** test-coordinate. */
    private static final double X2 = 10.0;

    /** test-coordinate. */
    private static final double Y2 = 20.0;

    /** test-coordinate. */
    private static final double X3 = 20.0;

    /** test-coordinate. */
    private static final double Y3 = 20.0;

    /** test-coordinate. */
    private static final double X4 = 20.0;

    /** test-coordinate. */
    private static final double Y4 = 10.0;

    /** Model to be tested. */
    private ElemModelCurvedPolyline model = null;

    /** The poly-points. */
    private PointUserSpace[] points = null;

    /**
     * @throws java.lang.Exception
     *             Possible exception.
     */
    @Before
    public void setUp() throws Exception {
        points = new PointUserSpace[4];
        for (int i = 0; i < points.length; i++) {
            points[i] = new PointUserSpace();
        }
        points[0].set(X1, Y1);
        points[1].set(X2, Y2);
        points[2].set(X3, Y3);
        points[3].set(X4, Y4);
        ElemCurvedPolyline polyline = new ElemCurvedPolyline(points);
        model = (ElemModelCurvedPolyline) polyline.getModel();
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModelPolyline#getShape()}.
     */
    @Test
    public void testGetShape() {
        assertNotNull(model.getShape());
        assertTrue(model.getShape() instanceof CurvedPolyline2D);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.elements.model.ElemModel#getElement()}.
     */
    @Test
    public void testGetElement() {
        assertNotNull(model.getElement());
        assertTrue(model.getElement() instanceof ElemCurvedPolyline);
    }

}

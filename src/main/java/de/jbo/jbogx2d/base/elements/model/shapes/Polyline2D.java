//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ShapePolyline.java
// Created: 05.03.2004 - 21:19:31
//

package de.jbo.jbogx2d.base.elements.model.shapes;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import de.jbo.jbogx2d.base.geom.AffineTransformX;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Implements a polyline shape.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 05.03.2004: jbo created <br>
 */
public class Polyline2D implements Shape {
    /** The base-points. */
    private PointUserSpace[] points = null;

    /**
     * Creates a new instance.
     * 
     * @param p
     *            The poly-endpoints to be used.
     */
    public Polyline2D(PointUserSpace[] p) {
        this();
        setPoints(p);
    }

    /**
     * Creates a new empty instance.
     */
    public Polyline2D() {
        super();
    }

    /**
     * Creates a point-array of the given size.
     * 
     * @param size
     *            The size to create an area for.
     * @return The created area.
     */
    protected PointUserSpace[] createPoints(int size) {
        return new PointUserSpace[size];
    }

    /**
     * Creates a new point.
     * 
     * @return The new point.
     */
    protected PointUserSpace createPoint() {
        return new PointUserSpace();
    }

    /**
     * Creates a new point-array. The new array will have the size of an
     * existing old plus the <code>newPointCount</code>.
     * 
     * @param newPointCount
     *            The amount of new points to be stored.
     * @param old
     *            The old-array (if exists, otherwise can be null). If exists,
     *            its elements are copied into the new array.
     * @return The new array.
     */
    protected PointUserSpace[] makeRoom(int newPointCount, PointUserSpace[] old) {
        int oldSize = (old != null) ? old.length : 0;
        PointUserSpace[] newPoints = createPoints(oldSize + newPointCount);

        if (old != null) {
            System.arraycopy(old, 0, newPoints, 0, old.length);
        }
        for (int i = oldSize; i < newPoints.length; i++) {
            newPoints[i] = createPoint();
        }

        return newPoints;
    }

    /**
     * Sets the given points.
     * 
     * @param p
     *            Points to be set.
     */
    public void setPoints(PointUserSpace[] p) {
        points = null;

        points = makeRoom(p.length, points);

        for (int i = p.length - 1; i >= 0; i--) {
            points[i].set(p[i]);
        }
    }

    /**
     * @return The current point-count.
     */
    public int getPointCount() {
        return getPointCount(points);
    }

    /**
     * Calculate the point-count for the given array.
     * 
     * @param p
     *            The array to calculate the point-count for.
     * @return The calculcated point-count.
     */
    protected int getPointCount(PointUserSpace[] p) {
        int count = 0;

        if (p != null) {
            count = p.length;
        }

        return count;
    }

    /**
     * Sets the given coordinates for the given point-index.
     * 
     * @param index
     *            The index to set the coordinates for.
     * @param x
     *            The x-coordinate.
     * @param y
     *            The y-coordinate.
     * @return True if successful, False otherwise.
     */
    public boolean setPoint(int index, double x, double y) {
        boolean state = false;

        if (checkPointRange(index, points)) {
            points[index].x = x;
            points[index].y = y;
            state = true;
        }
        return state;
    }

    /**
     * Checks if the given index is valid for the given array.
     * 
     * @param index
     *            The index to be checked.
     * @param p
     *            The array to check the index for.
     * @return True if the index is valid, False otherwise.
     */
    protected boolean checkPointRange(int index, PointUserSpace[] p) {
        return (index >= 0) && (index < getPointCount(p));
    }

    /**
     * Gets the point for the given index.
     * 
     * @param index
     *            The index to get the point for.
     * @param point
     *            Out-param receiving the points coordinates.
     * @return True if successful, False otherwise.
     */
    public boolean getPoint(int index, PointUserSpace point) {
        boolean state = false;

        if (checkPointRange(index, points)) {
            point.set(points[index]);
            state = true;
        }
        return state;
    }

    /**
     * Inserts the given point at the given index.
     * 
     * @param index
     *            The index to insert the point at.
     * @param x
     *            The x-coordinate.
     * @param y
     *            The y-coordinate.
     * @return True if successful, False otherwise.
     */
    public boolean insertPoint(int index, double x, double y) {
        boolean state = false;
        int count = getPointCount();

        if ((count < 1) || (index >= count)) {
            points = makeRoom(1, points);
        } else {
            points = insertPoint(points, index);
        }
        points[index].setLocation(x, y);
        state = true;

        return state;
    }

    /**
     * Increases the point array by a single new entry at the given position.
     * The old values of the array are copied accordingly.
     * 
     * @param old
     *            The old-array.
     * @param index
     *            The index to insert a new point.
     * @return The new array.
     */
    protected PointUserSpace[] insertPoint(PointUserSpace[] old, int index) {
        int count = getPointCount(old);
        PointUserSpace[] newPoints = null;

        newPoints = createPoints(count + 1);

        System.arraycopy(points, 0, newPoints, 0, index);
        newPoints[index] = createPoint();
        System.arraycopy(points, index, newPoints, index + 1, points.length - index);

        return newPoints;
    }

    /**
     * Removes the given point.
     * 
     * @param index
     *            Index of the point to be removed.
     * @return True if successful, False otherwise.
     */
    public boolean removePoint(int index) {
        boolean state = false;
        PointUserSpace[] newPoints = removePoint(index, points);

        if (newPoints != points) {
            state = true;
            points = newPoints;
        }

        return state;
    }

    /**
     * Removes a point and return the reduced point array.
     * 
     * @param index
     *            Index of the point to be removed.
     * @param p
     *            The array to be reduced.
     * @return The new reduced point array.
     */
    protected PointUserSpace[] removePoint(int index, PointUserSpace[] p) {
        PointUserSpace[] newPoints = null;

        if ((index >= 0) && (index < getPointCount(p))) {
            newPoints = createPoints(p.length - 1);

            System.arraycopy(p, 0, newPoints, 0, index);
            System.arraycopy(p, index + 1, newPoints, index, newPoints.length - index);
        }
        return (newPoints != null) ? newPoints : p;
    }

    /**
     * Checks if the coordinates are contained in the shape's area.
     * 
     * @param x
     *            The x-coordinate.
     * @param y
     *            The y-coordinate.
     * @return True if the coordinate is contained in this shape's area.
     */
    public boolean contains(double x, double y) {
        return false;
    }

    /**
     * Checks if the given rectangular area is contained in this shape's area.
     * 
     * @param x
     *            The upper-left x-coordinate.
     * @param y
     *            The upper-left y-coordinate.
     * @param w
     *            The width of the rectangular area.
     * @param h
     *            The height of the rectangular area.
     * @return True if the rectangle is contained in this shape's area.
     */
    public boolean contains(double x, double y, double w, double h) {
        return false;
    }

    /**
     * Checks if the given rectangular area intersects with this shape's area.
     * 
     * @param x
     *            The upper-left x-coordinate.
     * @param y
     *            The upper-left y-coordinate.
     * @param w
     *            The width of the rectangular area.
     * @param h
     *            The height of the rectangular area.
     * @return True if the rectangle intersects this shape's area.
     * @see java.awt.Shape#intersects(double, double, double, double)
     */
    public boolean intersects(double x, double y, double w, double h) {
        Rectangle2D rect = new Rectangle2D.Double();
        rect.setFrame(x, y, w, h);
        return intersects(rect);
    }

    /**
     * @return The shape's rectangular area.
     * @see java.awt.Shape#getBounds()
     */
    public Rectangle getBounds() {
        return getBounds2D().getBounds();
    }

    /**
     * Checks if the given point is contained in this shape's area.
     * 
     * @param p
     *            The point to be checked.
     * @return True if the point is contained in this shape's area.
     * @see java.awt.Shape#contains(java.awt.geom.Point2D)
     */
    public boolean contains(Point2D p) {
        return false;
    }

    /**
     * Calculates the rectangular bounds of the given poly-point array.
     * 
     * @param points
     *            The poly-points to be calculated.
     * @param bounds
     *            Out-parameter receiving the calculated bounds.
     */
    public static void getBounds2D(PointUserSpace[] points, Rectangle2D bounds) {
        int count = points.length;

        if (count > 0) {
            double x1 = points[0].x;
            double x2 = x1;
            double y1 = points[0].y;
            double y2 = y1;

            for (int i = 1; i < count; i++) {
                double x = points[i].x;
                double y = points[i].y;

                if (x < x1) {
                    x1 = x;
                }
                if (y < y1) {
                    y1 = y;
                }
                if (x > x2) {
                    x2 = x;
                }
                if (y > y2) {
                    y2 = y;
                }
            }
            bounds.setRect(x1, y1, x2 - x1, y2 - y1);
        }
    }

    /**
     * @return The shape's rectangular bounds.
     */
    public Rectangle2D getBounds2D() {
        Rectangle2D.Double bounds = new Rectangle2D.Double();
        getBounds2D(points, bounds);
        return bounds;
    }

    /**
     * Checks if the given rectangle is contained in this shape's area.
     * 
     * @param r
     *            The rectangle to be checked.
     * @return True if this shape's area contains the given rectangle.
     * @see java.awt.Shape#contains(java.awt.geom.Rectangle2D)
     */
    public boolean contains(Rectangle2D r) {
        return false;
    }

    /**
     * Checks if the given rectangle intersects this shape's area.
     * 
     * @param r
     *            The rectangle to be checked.
     * @return True if the rectangle intersects this shape's area.
     * @see java.awt.Shape#intersects(java.awt.geom.Rectangle2D)
     */
    public boolean intersects(Rectangle2D r) {
        for (int i = 0; i < points.length - 1; i++) {
            double x1 = points[i].x;
            double y1 = points[i].y;
            double x2 = points[i + 1].x;
            double y2 = points[i + 1].y;

            if (r.intersectsLine(x1, y1, x2, y2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param at
     *            The transformation to return the path-iterator for.
     * @return The path-iterator used for rendering.
     * @see java.awt.Shape#getPathIterator(java.awt.geom.AffineTransform)
     */
    public PathIterator getPathIterator(AffineTransform at) {
        return new PathIteratorPolyline(this, at);
    }

    /**
     * @param at
     *            The transformation to return the path-iterator for.
     * @param flatness
     *            The flatness for the iterator.
     * @return The corresponding path iterator.
     * @see java.awt.Shape#getPathIterator(java.awt.geom.AffineTransform,
     *      double)
     */
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return getPathIterator(at);
    }

    /**
     * Sets the point-count of this shape.
     * 
     * @param pointCount
     *            The count to be set. The corresponding array memory is
     *            allocated. Existing points in the array are preserved.
     */
    public void setPointCount(int pointCount) {
        points = makeRoom(pointCount - getPointCount(), points);
    }

    /**
     * Transforms this shape.
     * 
     * @param transformation
     *            The transformation to be performed.
     */
    public void transform(AffineTransformX transformation) {
        if (getPointCount() > 0) {
            transformation.transform(points, 0, points, 0, points.length);
        }
    }

    /**
     * @return the points
     */
    public PointUserSpace[] getPoints() {
        return points;
    }
}
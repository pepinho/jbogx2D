package de.jbo.jbogx2d.base.geom;

import java.awt.geom.Point2D;

/**
 * Defines the base-class for point-coordinates withing the userspace-coordinate
 * system. <br>
 * A point is defined by a x- and a y-value. The origin of the coordinate system
 * is in the upper-left corner. X-coordinates move from left to right and
 * y-coordinates move from top downward. <br>
 * Angle-value are given in radians. The origin-angle is at 3 o'clock moving
 * clockwise up to 2 PI.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 27.10.2004 Josef Baro - created <br>
 */
public class PointUserSpace extends Point2D.Double {
    /**
     * 
     */
    private static final long serialVersionUID = 202982060652560593L;

    /**
     * Creates a new point defining the given x- and y-values.
     * 
     * @param x
     *            The x-value to be set.
     * @param y
     *            The y-value to be set.
     */
    public PointUserSpace(double x, double y) {
        this();
        setLocation(x, y);
    }

    /**
     * Creates a new initial point defining the x- and y-values for the origin
     * (0.0/0.0) of the coordinate system.
     */
    public PointUserSpace() {
        super(0.0, 0.0);
    }

    /**
     * Sets the given values for the coordinates of this point instance.
     * 
     * @param x
     *            The x-value to be set.
     * @param y
     *            The y-value to be set.
     */
    public void set(double x, double y) {
        setLocation(x, y);
    }

    /**
     * Checks if this point instance consists of valid coordinate values: <br>
     * <ul>
     * <li>non infinite value</li>
     * <li>a valid Number value</li>
     * </ul>
     * 
     * @return True if coordinates are valid, False otherwise.
     */
    public boolean isValid() {
        boolean isvalid = true;

        if (java.lang.Double.isInfinite(x) || java.lang.Double.isNaN(x) || java.lang.Double.isInfinite(y) || java.lang.Double.isNaN(y)) {
            isvalid = false;
        }

        return isvalid;
    }

    /**
     * Sets the values of the current point instance to the given point-values.
     * 
     * @param point
     *            The point defining the values to be set.
     */
    public void set(PointUserSpace point) {
        setLocation(point);
    }

    /**
     * Calculates the distance-vector from the current point instance to the
     * given point and stores it in the second out-parameter.
     * 
     * @param pointTo
     *            The point to calculate the distance-vector to starting at the
     *            current point instance.
     * @param diff
     *            The out-parameter receiving the distance-vector as result.
     */
    public void distanceVector(PointUserSpace pointTo, PointUserSpace diff) {
        distanceVector(this, pointTo, diff);
    }

    /**
     * Calculates the distance-vector from the first point instance to the
     * second point and stores it in the third out-parameter.
     * 
     * @param pointFrom
     *            The start-point for the distance calculation.
     * @param pointTo
     *            The end-point for the distance calculation.
     * @param diff
     *            The out-parameter receiving the distance-vector as result.
     */
    public static void distanceVector(PointUserSpace pointFrom, PointUserSpace pointTo, PointUserSpace diff) {
        diff.x = pointTo.x - pointFrom.x;
        diff.y = pointTo.y - pointFrom.y;
    }

    /**
     * Calculates the difference angle between the two given point parameters. <br>
     * <br>
     * <b>NOTE </b> <br>
     * The angle value is given in radians and starts at 3 o'clock moving
     * clockwise.
     * 
     * @param pointFromX
     *            The x-value of the start-point.
     * @param pointFromY
     *            The y-value of the start-point.
     * @param pointToX
     *            The x-value of the end-point.
     * @param pointToY
     *            The y-value of the end-point.
     * @return The difference angle between the two points.
     */
    public static double angle(double pointFromX, double pointFromY, double pointToX, double pointToY) {
        double angle = Math.atan2(pointToY - pointFromY, pointToX - pointFromX);

        if (angle < 0.0) {
            angle += GeometryToolbox.PI2;
        } else if (angle > GeometryToolbox.PI2) {
            angle -= GeometryToolbox.PI2;
        }

        return angle;
    }

    /**
     * Calculates the difference angle from the current point instance to the
     * origin of the coordinate system (0.0 / 0.0). <br>
     * <br>
     * <b>NOTE </b> <br>
     * The angle value is given in radians and starts at 3 o'clock moving
     * clockwise.
     * 
     * @return The difference angle between the two points.
     */
    public double angle() {
        return angle(x, y, 0.0, 0.0);
    }

    /**
     * Calculates the difference angle between the two given point parameters. <br>
     * <br>
     * <b>NOTE </b> <br>
     * The angle value is given in radians and starts at 3 o'clock moving
     * clockwise.
     * 
     * @param pointFrom
     *            The start-point for the calculation of the difference angle.
     * @param pointTo
     *            The end-point for the calculation of the difference angle.
     * @return The difference angle between the two points.
     */
    public static double angle(PointUserSpace pointFrom, PointUserSpace pointTo) {
        return angle(pointFrom.x, pointFrom.y, pointTo.x, pointTo.y);
    }

    /**
     * Calculates the difference angle from the current point instance to the
     * given point instance parameter. <br>
     * <br>
     * <b>NOTE </b> <br>
     * The angle value is given in radians and starts at 3 o'clock moving
     * clockwise.
     * 
     * @param pointTo
     *            The point to calculate the angle to.
     * @return The difference angle between the two points.
     */
    public double angle(PointUserSpace pointTo) {
        return angle(this, pointTo);
    }

    /**
     * Adds the given x and y values to the current point instance.
     * 
     * @param x1
     *            The x value to be added.
     * @param y1
     *            The y value to be added.
     */
    public void add(double x1, double y1) {
        this.x += x1;
        this.y += y1;
    }

    /**
     * Adds the given point to the current point instance.
     * 
     * @param p
     *            The point to be added to the current point isntance.
     */
    public void add(PointUserSpace p) {
        add(p.x, p.y);
    }

    /**
     * Adds the given distance to the current point instance at the given
     * direction angle.
     * 
     * @param distance
     *            The value to be added at the given direction angle.
     * @param angle
     *            The direction angle to add the distance value to this point
     *            instance.
     */
    public void addPolar(double distance, double angle) {
        double x1, y1;

        x1 = Math.cos(angle) * distance;
        y1 = Math.sin(angle) * distance;

        add(x1, y1);
    }
}
//
// Copyright (c) 2022 by jbo - Josef Baro
// 
// Project: jbogx2d-base
// File:    CurvedPolyUtil.java
// Created: 30.07.2022 - 10:45:58
//
package de.jbo.jbogx2d.base.elements.model.shapes;

import de.jbo.jbogx2d.base.elements.model.shapes.geom.PointUserSpaceCurved;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

public final class CurvedPolyUtil {
    /** Factor for distance of controlpoints to the poly-endpoints. */
    protected static final double CONTROL_POINT_DIST_FACTOR = 0.5;

    private CurvedPolyUtil() {

    }

    /**
     * Updates the control-points.
     * 
     * @param startIndex
     *            Start-index to update for.
     * @param stopIndex
     *            Stop-index to update for.
     * @param points
     *            Points to be updated.
     * @param count
     *            The number of points.
     * @return True if updated successfully.
     */
    public static boolean updateControlPoints(int startIndex, int stopIndex, PointUserSpace[] points, int count) {
        boolean ret = false;
        int index = startIndex;
        int newStopIndex = (stopIndex >= count) ? count - 1 : stopIndex;

        for (; index <= newStopIndex; index++) {
            updateControlPoint(index, points, count);
        }

        ret = true;

        return ret;
    }

    /**
     * Updates a specific control-point.
     * 
     * @param index
     *            The index of the point to be updated.
     * @param points
     *            Points to be updated.
     * @param count
     *            The number of points.
     */
    private static void updateControlPoint(int index, PointUserSpace[] points, int count) {
        PointUserSpaceCurved p1 = (PointUserSpaceCurved) points[index];
        PointUserSpace p2 = null;

        if (index < (count - 1)) {
            p2 = points[index + 1];
        } else {
            p2 = points[0];
        }
        double distance = p1.distance(p2);
        double angle = p1.angle(p2);
        PointUserSpace controlPoint = p1.getControlPoint();
        if (controlPoint == null) {
            controlPoint = new PointUserSpace(p1.x, p1.y);
            p1.setControlPoint(controlPoint);
        } else {
            controlPoint.set(p1.x, p1.y);
        }
        controlPoint.addPolar(distance * CONTROL_POINT_DIST_FACTOR, angle);
    }
}

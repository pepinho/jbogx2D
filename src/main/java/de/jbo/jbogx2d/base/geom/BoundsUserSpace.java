package de.jbo.jbogx2d.base.geom;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * Implements rectangular bounds in user-space units.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 13.01.2010 jbo - created <br>
 */
public class BoundsUserSpace extends Rectangle2D.Double {
    /*
     * (non-Javadoc)
     * 
     * @see java.awt.geom.Rectangle2D.Double#getHeight()
     */
    @Override
    public double getHeight() {
        return Math.abs(height);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.geom.Rectangle2D.Double#getWidth()
     */
    @Override
    public double getWidth() {
        return Math.abs(width);
    }

    /**
     * Serial id.
     */
    private static final long serialVersionUID = -1505731990528878091L;

    /** Identifies the quadrant in the north-east corner. */
    public static final int QUADRANT_NE = 1;

    /** Identifies the quadrant in the north-west corner. */
    public static final int QUADRANT_NW = 2;

    /** Identifies the quadrant in the south-west corner. */
    public static final int QUADRANT_SW = 3;

    /** Identifies the quadrant in the sourth-east corner. */
    public static final int QUADRANT_SE = 4;

    /**
     * Indicates that a given bounds rectangle intersects multiple quadrants and
     * can not be exactly referred to a single quadrant.
     */
    public static final int QUADRANT_INTERSECTION = -1;

    /**
     * Creates a new instance.
     */
    public BoundsUserSpace() {
        super();
    }

    /**
     * Creates a new instance.
     * 
     * @param x
     *            Upper-left x-coordinate.
     * @param y
     *            Upper-left y-coordinate.
     * @param w
     *            Width of the bounds.
     * @param h
     *            Height of the bounds.
     */
    public BoundsUserSpace(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    /**
     * Checks if the given boundsCheck fit into one of the boundsComplete
     * quadrants.
     * 
     * @param boundsComplete
     *            The bounds which quadrants shall be checked for match.
     * @param boundsCheck
     *            The bounds to be matched into a quadrant.
     * @return QUADRANT_INTERSECTION if no exact match to a quadrant can be
     *         found, otherwise one of the <code>QUADRANT_</code> constants is
     *         returned corresponding to the matching quadrant.
     */
    public static int testBoundsQuadrants(BoundsUserSpace boundsComplete, BoundsUserSpace boundsCheck) {
        int match = QUADRANT_INTERSECTION;
        BoundsUserSpace b = new BoundsUserSpace();

        // north-east
        boundsComplete.calcQuadrantBounds(QUADRANT_NE, b);
        if (b.contains(boundsCheck)) {
            match = QUADRANT_NE;
        } else {
            // north-west
            boundsComplete.calcQuadrantBounds(QUADRANT_NW, b);
            if (b.contains(boundsCheck)) {
                match = QUADRANT_NW;
            } else {
                // south-west
                boundsComplete.calcQuadrantBounds(QUADRANT_SW, b);
                if (b.contains(boundsCheck)) {
                    match = QUADRANT_SW;
                } else {
                    // south-east
                    boundsComplete.calcQuadrantBounds(QUADRANT_SE, b);
                    if (b.contains(boundsCheck)) {
                        match = QUADRANT_SE;
                    }
                }
            }
        }

        return match;
    }

    /**
     * Checks if the given boundsCheck fit into one of the this bounds'
     * quadrants.
     * 
     * @param boundsCheck
     *            The bounds to be matched into a quadrant.
     * @return QUADRANT_INTERSECTION if no exact match to a quadrant can be
     *         found, otherwise one of the <code>QUADRANT_</code> constants is
     *         returned corresponding to the matching quadrant.
     */
    public int testBoundsQuadrants(BoundsUserSpace boundsCheck) {
        return testBoundsQuadrants(this, boundsCheck);
    }

    /**
     * Calculates the bounds for the given quadrant.
     * 
     * @param quadrant
     *            The quadrant-id to calculate the bounds for.
     * @param b
     *            Out-parameter for the calculated quadrant-bounds.
     * @return True if successful.
     * @see #QUADRANT_NE
     * @see #QUADRANT_NW
     * @see #QUADRANT_SE
     * @see #QUADRANT_SW
     */
    public boolean calcQuadrantBounds(int quadrant, BoundsUserSpace b) {
        return calcQuadrantBounds(quadrant, this, b);
    }

    /**
     * Calculates the bounds for the given quadrant of the given complete
     * bounds.
     * 
     * @param quadrant
     *            The quadrant-id to calculate the bounds for.
     * @param boundsComplete
     *            The complete-bounds to calculate the quadrant-bounds for.
     * @param b
     *            Out-parameter for the calculated quadrant-bounds.
     * @return True if successful.
     * @see #QUADRANT_NE
     * @see #QUADRANT_NW
     * @see #QUADRANT_SE
     * @see #QUADRANT_SW
     */
    public static boolean calcQuadrantBounds(int quadrant, BoundsUserSpace boundsComplete, BoundsUserSpace b) {
        boolean state = true;
        double wHalf = boundsComplete.width / 2;
        double hHalf = boundsComplete.height / 2;

        b.width = wHalf;
        b.height = hHalf;

        switch (quadrant) {
        case QUADRANT_NE:
            b.x = boundsComplete.x + wHalf;
            b.y = boundsComplete.y;
            break;
        case QUADRANT_NW:
            b.x = boundsComplete.x;
            b.y = boundsComplete.y;
            break;
        case QUADRANT_SW:
            b.x = boundsComplete.x;
            b.y = boundsComplete.y + hHalf;
            break;
        case QUADRANT_SE:
            b.x = boundsComplete.x + wHalf;
            b.y = boundsComplete.y + hHalf;
            break;
        default:
            state = false;
        }

        return state;
    }

    /**
     * Sets the bounds coordinates.
     * 
     * @param x
     *            Upper-left x-coordinate.
     * @param y
     *            Upper-left y-coordinate.
     * @param width
     *            Width of the bounds.
     * @param height
     *            Height of the bounds.
     */
    public void set(double x, double y, double width, double height) {
        setRect(x, y, width, height);
    }

    /**
     * Sets the bounds coordinates.
     * 
     * @param b
     *            The coordinates to be set.
     */
    public void set(BoundsUserSpace b) {
        setRect(b.x, b.y, b.width, b.height);
    }

    /**
     * Matches the width and height ratio of the current bounds area to fit the
     * given bounds area.
     * 
     * @param b
     *            The area defining the width and height ratio to be matched by
     *            the current instance.
     */
    public final void matchRatio(final BoundsUserSpace b) {
        double widthHeightRatio1 = width / height;
        double widthHeightRatio2 = b.width / b.height;

        // Hoehe anpassen
        if (widthHeightRatio1 > widthHeightRatio2) {
            height = (height * widthHeightRatio1) / widthHeightRatio2;
        } else if (widthHeightRatio1 < widthHeightRatio2) {
            width = (width * widthHeightRatio2) / widthHeightRatio1;
        }
    }

    /**
     * Indicates if this instance has valid coordinate values.
     * 
     * @return True if coordinates are valid.
     */
    public boolean isValid() {
        boolean isvalid = true;

        if (java.lang.Double.isInfinite(x) || java.lang.Double.isNaN(x) || java.lang.Double.isInfinite(y) || java.lang.Double.isNaN(y) || java.lang.Double.isInfinite(width) || java.lang.Double.isNaN(width) || java.lang.Double.isInfinite(height)
                || java.lang.Double.isNaN(height)) {
            isvalid = false;
        }

        return isvalid;
    }

    /**
     * Merges this instance with the given bounds.
     * 
     * @param b
     *            The bounds to be merged.
     */
    public void merge(BoundsUserSpace b) {
        add(b);
    }

    /**
     * Returns the distance to the given point.
     * 
     * @param point
     *            Point to calculate the distance to.
     * @return The distance in user-space coordinates.
     */
    public final double getDistanceTo(PointUserSpace point) {
        int outcode = outcode(point.x, point.y);
        double distance = 0.0;
        double minDistance = java.lang.Double.MAX_VALUE;

        // Oberkante
        if ((outcode & Rectangle2D.OUT_TOP) == Rectangle2D.OUT_TOP) {
            distance = Line2D.ptLineDist(x, y, x + width, y, point.x, point.y);
            minDistance = Math.min(distance, minDistance);
        } else if ((outcode & Rectangle2D.OUT_BOTTOM) == Rectangle2D.OUT_BOTTOM) {
            distance = Line2D.ptLineDist(x, y + height, x + width, y + height, point.x, point.y);
            minDistance = Math.min(distance, minDistance);
        }

        // Rechte Kante
        if ((outcode & Rectangle2D.OUT_RIGHT) == Rectangle2D.OUT_RIGHT) {
            distance = Line2D.ptLineDist(x + width, y, x + width, y + height, point.x, point.y);
            minDistance = Math.min(distance, minDistance);
        } else if ((outcode & Rectangle2D.OUT_LEFT) == Rectangle2D.OUT_LEFT) {
            distance = Line2D.ptLineDist(x, y, x, y + height, point.x, point.y);
            minDistance = Math.min(distance, minDistance);
        }

        return minDistance;
    }

    /*
     * @see java.awt.geom.Rectangle2D#intersects(double, double, double, double)
     */
    @Override
    public boolean intersects(double x1, double y1, double w, double h) {
        return (((x1 + w) > x) && ((y1 + h) > y) && (x1 < (x + getWidth())) && (y1 < (y + getHeight())));
    }

    /*
     * @see java.awt.geom.Rectangle2D#contains(double, double, double, double)
     */
    @Override
    public boolean contains(double x1, double y1, double w, double h) {
        return ((x1 >= x) && (y1 >= y) && ((x1 + w) <= (x + getWidth())) && ((y1 + h) <= (y + getHeight())));
    }

}
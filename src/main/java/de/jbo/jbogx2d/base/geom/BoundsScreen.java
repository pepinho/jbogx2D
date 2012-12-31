package de.jbo.jbogx2d.base.geom;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Implements bounds representing screen-coordinates.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 25.01.2010 jbo - created <br>
 */
public class BoundsScreen extends Rectangle {

    /**
     * Serial id.
     */
    private static final long serialVersionUID = 1163255907251703160L;

    /**
     * Creates a new instance.
     */
    public BoundsScreen() {
        super();
    }

    /**
     * Creates a new instance.
     * 
     * @param width
     *            Width in pixels.
     * @param height
     *            Height in pixels.
     */
    public BoundsScreen(int width, int height) {
        super(width, height);
    }

    /**
     * Creates a new instance.
     * 
     * @param x
     *            Upper-left x-coordinate.
     * @param y
     *            Upper-left y-coordinate.
     * @param width
     *            Width in pixels.
     * @param height
     *            Height in pixels.
     */
    public BoundsScreen(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * Creates a new instance.
     * 
     * @param d
     *            Dimension to initialize this instance with.
     */
    public BoundsScreen(Dimension d) {
        super(d);
    }

    /**
     * Creates a new instance.
     * 
     * @param p
     *            Point to initialize this instance with.
     */
    public BoundsScreen(Point p) {
        super(p);
    }

    /**
     * Creates a new instance.
     * 
     * @param r
     *            Rectangular area to initialize this instance with.
     */
    public BoundsScreen(Rectangle r) {
        super(r);
    }

    /**
     * Creates a new instance.
     * 
     * @param p
     *            Upper-left edge.
     * @param d
     *            Width and height.
     */
    public BoundsScreen(Point p, Dimension d) {
        super(p, d);
    }

    /**
     * Initializes this instance with the given bounds.
     * 
     * @param b
     *            Bounds to be set.
     */
    public void set(BoundsScreen b) {
        setBounds(b.x, b.y, b.width, b.height);
    }

    /**
     * Initializes this instance with the given values.
     * 
     * @param x
     *            Upper-left x-coorindate.
     * @param y
     *            Upper-left y-coordinate.
     * @param width
     *            Width to be set.
     * @param height
     *            Height to be set.
     */
    public void set(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
    }

}
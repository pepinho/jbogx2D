package de.jbo.jbogx2d.base.geom;

import java.awt.Point;

/**
 * Implements a pixel-based screen-coordinate.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 25.01.2010 jbo - created <br>
 */
public class PointScreen extends Point {
    /**
     * Serial id.
     */
    private static final long serialVersionUID = 47931135396388205L;

    /**
     * Creates a new instance.
     */
    public PointScreen() {
        super();
    }

    /**
     * Creates a new instance.
     * 
     * @param x
     *            x-coordinate to be set.
     * @param y
     *            y-coordinate to be set.
     */
    public PointScreen(int x, int y) {
        super(x, y);
    }

    /**
     * Creates a new instance.
     * 
     * @param p
     *            Point to be set.
     */
    public PointScreen(PointScreen p) {
        super(p);
    }

}
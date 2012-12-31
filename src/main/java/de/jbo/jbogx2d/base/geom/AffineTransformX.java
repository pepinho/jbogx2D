package de.jbo.jbogx2d.base.geom;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Defines a matrix-transformation used for all geometric operations.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 04.01.2010 jbo - created <br>
 */
public class AffineTransformX extends AffineTransform {
    /**
     * Serial id.
     */
    private static final long serialVersionUID = 3077393537173971476L;

    /**
     * Creates a new instance.
     */
    public AffineTransformX() {
        super();
    }

    /**
     * Creates a new instance.
     * 
     * @param tx
     *            The base-transformation to be used.
     */
    public AffineTransformX(AffineTransform tx) {
        super(tx);

    }

    /**
     * Creates a new instance.
     * 
     * @param m00
     *            Transformation value to be set.
     * @param m10
     *            Transformation value to be set.
     * @param m01
     *            Transformation value to be set.
     * @param m11
     *            Transformation value to be set.
     * @param m02
     *            Transformation value to be set.
     * @param m12
     *            Transformation value to be set.
     */
    public AffineTransformX(double m00, double m10, double m01, double m11, double m02, double m12) {
        super(m00, m10, m01, m11, m02, m12);
    }

    /**
     * Creates a new instance.
     * 
     * @param flatmatrix
     *            Transformation parameters to be set.
     */
    public AffineTransformX(double[] flatmatrix) {
        super(flatmatrix);
    }

    /**
     * Creates a new instance.
     * 
     * @param m00
     *            Transformation value to be set.
     * @param m10
     *            Transformation value to be set.
     * @param m01
     *            Transformation value to be set.
     * @param m11
     *            Transformation value to be set.
     * @param m02
     *            Transformation value to be set.
     * @param m12
     *            Transformation value to be set.
     */
    public AffineTransformX(float m00, float m10, float m01, float m11, float m02, float m12) {
        super(m00, m10, m01, m11, m02, m12);
    }

    /**
     * Creates a new instance.
     * 
     * @param flatmatrix
     *            Transformation parameters to be set.
     */
    public AffineTransformX(float[] flatmatrix) {
        super(flatmatrix);
    }

    /**
     * This method defines the transformation of a window bounds area based on a
     * second bounds area defining the ratio.
     * 
     * @param b1
     *            The area of the bounds to be transformed
     * @param b2
     *            The area defining the current window bounds in
     *            world-coordinates.
     */
    public void windowTransform(BoundsUserSpace b1, BoundsUserSpace b2) {
        double m00, m01, m02, m10, m11, m12;

        m00 = b2.width / b1.width;
        m02 = b2.x - m00 * b1.x;
        m11 = b2.height / b1.height;
        m12 = b2.y - m11 * b1.y;
        m01 = 0.0;
        m10 = 0.0;

        setTransform(m00, m10, m01, m11, m02, m12);
    }

    /**
     * This method defines the transformation of a window bounds area based on a
     * second bounds area defining the view ratio.
     * 
     * @param world
     *            The area of the bounds to be transformed
     * @param view
     *            The area defining the current window bounds in
     *            screen-coordinates.
     */
    public void windowTransform(BoundsUserSpace world, BoundsScreen view) {
        double m00, m01, m02, m10, m11, m12;

        m00 = view.width / world.width;
        m02 = view.x - m00 * world.x;
        m11 = view.height / world.height;
        m12 = view.y - m11 * world.y;
        m01 = 0.0;
        m10 = 0.0;

        setTransform(m00, m10, m01, m11, m02, m12);
    }

    /**
     * This method defines the transformation of a window bounds area based on a
     * second bounds area defining the view ratio.
     * 
     * @param view
     *            The area defining the current window bounds in
     *            screen-coordinates.
     * @param world
     *            The area of the bounds to be transformed
     */
    public void windowTransform(BoundsScreen view, BoundsUserSpace world) {
        double m00, m01, m02, m10, m11, m12;

        m00 = world.width / view.width;
        m02 = world.x - m00 * view.x;
        m11 = world.height / view.height;
        m12 = world.y - m11 * view.y;
        m01 = 0.0;
        m10 = 0.0;

        setTransform(m00, m10, m01, m11, m02, m12);
    }

    /**
     * Multiplies the transformation matrix with a double value and returns the
     * resulting amount.
     * 
     * @param scalar
     *            The value to multiply the matrix with
     * @return The resulting amount is returned.
     */
    public double scalarMul(double scalar) {
        if (scalar == 0.0) {
            return (scalar);
        }

        double newScalar = scalar * GeometryToolbox.SQRT1_2;

        double abs = Point2D.distance((getScaleX() + getShearX()) * newScalar, (getScaleY() + getShearY()) * newScalar, 0.0, 0.0);

        return ((newScalar < 0.0) ? -abs : abs);
    }

    /**
     * This method initializes a scaling-transformation for the given scaling
     * factors in x- and y-direction and the given reference point.
     * 
     * @param refPnt
     *            The reference point for the scaling transformation.
     * @param sx
     *            The scaling-factor in x-direction from 0.0 to 1.0.
     * @param sy
     *            The scaling-factor in y-direction from 0.0 to 1.0.
     */
    public void setToScale(PointUserSpace refPnt, double sx, double sy) {
        setToScale(refPnt.x, refPnt.y, sx, sy);
    }

    /**
     * This method initializes a scaling-transformation for the given scaling
     * factors in x- and y-direction and the given reference point.
     * 
     * @param refX
     *            The reference point x for the scaling transformation.
     * @param refY
     *            The reference point y for the scaling transformation.
     * @param sx
     *            The scaling-factor in x-direction from 0.0 to 1.0.
     * @param sy
     *            The scaling-factor in y-direction from 0.0 to 1.0.
     */
    public void setToScale(double refX, double refY, double sx, double sy) {
        AffineTransformX m1 = new AffineTransformX();
        AffineTransformX m2 = new AffineTransformX();

        // Erzeuge Verschiebung des Fixpunktes in den Nullpunkt
        setToTranslation(-refX, -refY);

        // Erzeuge Skalierung zur x- und y-Achse
        m1.setToScale(sx, sy);
        m2.setTransform(m1);
        m2.concatenate(this);

        // Erzeuge Rueckverschiebung des Fixpunkts
        m1.setToTranslation(refX, refY);
        m1.concatenate(m2);
        setTransform(m1);
    }

    /**
     * Defines a mirror transformation.
     * 
     * @param p1
     *            First point of mirror-axis.
     * @param p2
     *            Second point of mirror-axis.
     */
    public void setToMirror(PointUserSpace p1, PointUserSpace p2) {
        setToMirror(p1.x, p1.y, p2.x, p2.y);
    }

    /**
     * Defines a mirror transformation.
     * 
     * @param x1
     *            First x-coordinate of mirror axis.
     * @param y1
     *            First y-coordinate of mirror axis.
     * @param x2
     *            Second x-coordinate of mirror-axis.
     * @param y2
     *            Second y-coordinate of mirror-axis
     */
    public void setToMirror(double x1, double y1, double x2, double y2) {
        AffineTransformX m1 = new AffineTransformX();
        AffineTransformX m2 = new AffineTransformX();

        double angle = PointUserSpace.angle(x1, y1, x2, y2);

        // Erzeuge Verschiebung des Geradenpunkts in den Nullpunkt
        m1.setToTranslation(-x1, -y1);

        // Erzeuge Rotation der Geraden auf die x-Achse
        m2.setToRotation(angle);
        setTransform(m2);
        concatenate(m1);

        // Erzeuge Spiegelung an der x-Achse
        m1.setToMirror(true, false);
        m2.setTransform(m1);
        m2.concatenate(this);

        // Erzeuge Rotation der Geraden in die urspuengliche Richtung
        setToRotation(-angle);
        m1.setTransform(this);
        m1.concatenate(m2);

        // Erzeuge Rueckverschiebung des Geradenpunkts
        m2.setToTranslation(x1, y1);
        setTransform(m2);
        concatenate(m1);
    }

    /**
     * Initializes the mirror-transformation along the x- and/or y-axis.
     * 
     * @param xAxis
     *            True to mirror along the x-axis.
     * @param yAxis
     *            True to mirror along the y-axis.
     */
    public void setToMirror(boolean xAxis, boolean yAxis) {
        setToScale((yAxis) ? -1.0 : 1.0, (xAxis) ? -1.0 : 1.0);
    }

}
//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: ElemModelText.java
// Created: 11.03.2004 - 19:15:25
//

package de.jbo.jbogx2d.base.elements.model;

import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.NoninvertibleTransformException;

import de.jbo.jbogx2d.base.Jbogx2D;
import de.jbo.jbogx2d.base.attrib.AttribFill;
import de.jbo.jbogx2d.base.attrib.AttribLine;
import de.jbo.jbogx2d.base.attrib.AttribText;
import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.elements.model.shapes.Polyline2D;
import de.jbo.jbogx2d.base.geom.AffineTransformX;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointUserSpace;

/**
 * Elements the model for text-elements.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 11.03.2004: jbo created <br>
 */
public class ElemModelText extends ElemModel {
    /** Stores the text attributes for this instance. */
    private final AttribText attribText = new AttribText();

    /** The base-point of this instance. */
    private final PointUserSpace basePoint = new PointUserSpace();

    /** The string content of this text model. */
    private String[] text = new String[] { "" };

    /** The text's transformation. */
    private final AffineTransformX transformation = new AffineTransformX();

    /**
     * Creates a new instance.
     * 
     * @param element
     *            The referenced parent element.
     */
    public ElemModelText(final ElemBase element) {
        super(element);
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModel#getAttribText()
     */
    @Override
    public AttribText getAttribText() {
        return attribText;
    }

    /*
     * @see
     * 
     * de.jbo.jbogx2d.base.elements.model.ElemModel#calculateBounds(de.jbo.jbogx2d
     * .base.geom.BoundsUserSpace)
     */
    @Override
    protected void calculateBounds(final BoundsUserSpace bounds) {
        BoundsUserSpace b = new BoundsUserSpace();
        BoundsUserSpace temp = new BoundsUserSpace();
        PointUserSpace p = new PointUserSpace(basePoint.x, basePoint.y);
        TextLayout layout = null;

        updateAttributes();
        FontRenderContext frc = new FontRenderContext(null, false, true);

        double height = attribText.getSystemHeight();
        double ascent = attribText.getSystemAscent();

        /*
         * Wir errechnen die Bounds zuerst auf Basis des gaenzlich
         * untransformierten Texts (also Waagerecht mit Original basePoint.
         */
        try {
            AffineTransformX inverse = new AffineTransformX(getTransformation());
            inverse.invert();
            inverse.transform(basePoint, p);
            height = inverse.scalarMul(height);
            ascent = inverse.scalarMul(ascent);
        } catch (NoninvertibleTransformException e) {
            Jbogx2D.getErrorHandler().handleError(e, true, false);
        }

        double x = p.x;
        double y = p.y - ascent;
        double lineFeed = attribText.getLineFeed();

        b.x = x;
        b.y = y;
        for (String element2 : text) {
            layout = new TextLayout(element2, attribText.getSystemAttributes(), frc);
            temp.setFrame(layout.getBounds());
            temp.x = x;
            temp.y = y;
            temp.height = height;
            b.merge(temp);
            y += (height * lineFeed);
        }

        /*
         * Transformation mit einbeziehen in die Bounds-Berechnung
         */
        PointUserSpace[] pus = new PointUserSpace[4];
        PointUserSpace[] trans = new PointUserSpace[4];
        pus[0] = new PointUserSpace(b.x, b.y);
        pus[1] = new PointUserSpace(b.x + b.width, b.y);
        pus[2] = new PointUserSpace(b.x + b.width, b.y + b.height);
        pus[3] = new PointUserSpace(b.x, b.y + b.height);

        for (int i = 0; i < trans.length; i++) {
            trans[i] = new PointUserSpace();
        }

        getTransformation().transform(pus, 0, trans, 0, pus.length);

        Polyline2D.getBounds2D(trans, bounds);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.ElemModel#transform(de.jbo.jbogx2d
     * .base.geom.AffineTransformX)
     */
    @Override
    public void transform(final AffineTransformX transform) {
        attribText.setSize((float) transform.scalarMul(attribText.getSize()));

        transform.transform(basePoint, basePoint);

        this.transformation.concatenate(transform);

        setBoundsDirty(true);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.ElemModel#getDistanceTo(de.jbo.jbogx2d
     * .base.geom.PointUserSpace)
     */
    @Override
    public double getDistanceTo(final PointUserSpace point) {
        BoundsUserSpace bounds = new BoundsUserSpace();
        getBounds(bounds);
        return bounds.getDistanceTo(point);        
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.ElemModel#isPointInside(de.jbo.jbogx2d
     * .base.geom.PointUserSpace)
     */
    @Override
    public boolean isPointInside(final PointUserSpace point) {
        boolean isInside = false;
        BoundsUserSpace bounds = new BoundsUserSpace();
        getBounds(bounds);
        isInside = bounds.contains(point);
        return isInside;
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModel#intersects(double,
     * double, double, double)
     */
    @Override
    public boolean intersects(final double x, final double y, final double w, final double h) {
        boolean intersects = false;
        BoundsUserSpace bounds = new BoundsUserSpace();
        getBounds(bounds);
        intersects = bounds.intersects(x, y, w, h);
        return intersects;
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.elements.model.ElemModel#intersects(de.jbo.jbogx2d
     * .base.geom.BoundsUserSpace)
     */
    @Override
    public boolean intersects(final BoundsUserSpace bounds) {
        return intersects(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModel#getAttribLine()
     */
    @Override
    public AttribLine getAttribLine() {

        return null;
    }

    /*
     * @see de.jbo.jbogx2d.base.elements.model.ElemModel#getAttribFill()
     */
    @Override
    public AttribFill getAttribFill() {

        return null;
    }

    /**
     * Returns the contents of the base-points within the given out-parameter.
     * 
     * @param point
     *            Out-parameter receiving the base-point content.
     */
    public void getBasePoint(final PointUserSpace point) {
        point.set(basePoint);
    }

    /**
     * Returns the current string content of this text model.
     * 
     * @return The text content of this model.
     */
    public String[] getText() {
        return text;
    }

    /**
     * Sets the new coordinates for this element's base point.
     * 
     * @param space
     *            The new coordinates to be set.
     */
    public void setBasePoint(final PointUserSpace space) {
        setBasePoint(space.getX(), space.getY());
    }

    /**
     * Sets the coordinates for the text's base-point.
     * 
     * @param x
     *            The x-coordinate to be set.
     * @param y
     *            The y-coordinate to be set.
     */
    public void setBasePoint(final double x, final double y) {
        basePoint.setLocation(x, y);
        setBoundsDirty(true);
    }

    /**
     * Sets the new string text content for this model instance.
     * 
     * @param string
     *            The new text to be set.
     */
    public void setText(final String[] string) {
        text = new String[string.length];
        System.arraycopy(string, 0, text, 0, text.length);
    }

    /**
     * Returns a reference to the base-point of the text element.
     * 
     * @return A direct reference to the base point.
     */
    public PointUserSpace getBasePoint() {
        return basePoint;
    }

    /**
     * @return The current transformation of the text-element.
     */
    public AffineTransformX getTransformation() {
        return transformation;
    }

}
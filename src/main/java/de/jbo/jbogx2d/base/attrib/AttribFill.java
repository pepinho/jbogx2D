//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: AttribFill.java
// Created: 29.02.2004 - 08:17:55
//

package de.jbo.jbogx2d.base.attrib;

import java.awt.Color;
import java.awt.Paint;
import java.awt.TexturePaint;

import de.jbo.jbogx2d.base.Jbogx2D;
import de.jbo.jbogx2d.base.elements.model.ElemModel;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;

/**
 * Implements the attribute for filled elements with a valid area. <br>
 * The following values can be set: <br>
 * <ul>
 * <li>The fill-type: hollow, solid, texture, gradient</li>
 * <li>The foreground color of the fill (1st color of gradient, if set)</li>
 * <li>The background color of the fill for textures (2nd color of gradient, if
 * set)</li>
 * <li>The texture-pattern for type texture.</li>
 * </ul>
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 29.02.2004: jbo created <br>
 */
public class AttribFill extends AttribBase {
    /** Stores the current fill-type. */
    private AttribFillType fillType = AttribFillType.TYPE_SOLID;

    /** Stores the current gradient type. */
    private AttribGradientType gradientType = AttribGradientType.GRADIENT_HORIZONTAL;

    /** Stores the current texture-index. */
    private short texture = 9; // solid

    /** Stores the current foreground color. */
    private short colorForeground = 1;

    /** Stores the current background color. */
    private short colorBackground = 0;

    /** The updated system foreground color. */
    private Color systemColorForeground = null;

    /** The updated system background color. */
    private Color systemColorBackground = null;

    /** The updated texture pattern. */
    private TexturePaint systemTexture = null;

    /** The updated gradient. */
    private Paint systemGradient = null;

    /**
     * Creates a new fill with the following values set: <br>
     * <ul>
     * <li>type: solid</li>
     * <li>color foreground: 1 (default black)</li>
     * <li>color background: 0 (default white)</li>
     * </ul>
     */
    public AttribFill() {
        super();
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.attrib.AttribBase#update(de.jbo.jbogx2d.base.elements
     * .model.ElemModel)
     */
    @Override
    public void update(ElemModel model) {
        systemColorForeground = Jbogx2D.getAttributeHandler().getColor(colorForeground);
        systemColorBackground = Jbogx2D.getAttributeHandler().getColor(colorBackground);

        // Type texture, so we update the system texture.
        if (fillType == AttribFillType.TYPE_TEXTURE) {
            systemTexture = Jbogx2D.getAttributeHandler().getTexture(texture, systemColorForeground, systemColorBackground);
        } else if (fillType == AttribFillType.TYPE_GRADIENT) {
            BoundsUserSpace b = new BoundsUserSpace();
            model.getBounds(b);

            if (gradientType.equals(AttribGradientType.GRADIENT_HORIZONTAL)) {
                gradientType.setX1(b.x);
                gradientType.setX2(b.x + b.width);
            } else if (gradientType.equals(AttribGradientType.GRADIENT_VERTICAL)) {
                gradientType.setY1(b.y);
                gradientType.setY2(b.y + b.height);
            } else if (gradientType.equals(AttribGradientType.GRADIENT_RADIAL)) {
                gradientType.setX1(b.getCenterX());
                gradientType.setY1(b.getCenterY());
                gradientType.setX2(b.width / 2.0);
            }
            systemGradient = Jbogx2D.getAttributeHandler().getGradient(systemColorForeground, systemColorBackground, gradientType);
        }
    }

    /**
     * Returns the background color index currently set.
     * 
     * @return The color index currently set.
     */
    public short getColorBackground() {
        return colorBackground;
    }

    /**
     * Returns the foreground color index currently set.
     * 
     * @return The color index currently set.
     */
    public short getColorForeground() {
        return colorForeground;
    }

    /**
     * Returns the currently set fill-type.
     * 
     * @return The currently set fill-type.
     */
    public AttribFillType getFillType() {
        return fillType;
    }

    /**
     * Returns the system color to the corresponding color-index. <br>
     * To ensure the correct state of this value, a call of
     * <code>update(...)</code> can be necessary.
     * 
     * @return The system color currently set.
     */
    public Color getSystemColorBackground() {
        return systemColorBackground;
    }

    /**
     * Returns the system color to the corresponding color-index. <br>
     * To ensure the correct state of this value, a call of
     * <code>update(...)</code> can be necessary.
     * 
     * @return The system color currently set.
     */
    public Color getSystemColorForeground() {
        return systemColorForeground;
    }

    /**
     * Returns the currently set system-texture used for type
     * <code>TYPE_TEXTURE</code>.<br>
     * To ensure the correct state of this value, a call of
     * <code>update(...)</code> can be necessary.
     * 
     * @return The pattern-texture currently set.
     */
    public TexturePaint getSystemTexture() {
        return systemTexture;
    }

    /**
     * Returns the currently set texture-index for type
     * <code>TYPE_TEXTURE</code>. <br>
     * 
     * @return The currently set texture-index.
     */
    public short getTexture() {
        return texture;
    }

    /**
     * Sets the color-index for the background color. <br>
     * For type <code>TYPE_SOLID</code>, this value has no effect. For type
     * <code>TYPE_TEXTURE</code> this color is set for each value of the texture
     * pattern set to "false". For Type <code>TYPE_GRADIENT</code> this color
     * serves as the second color for the gradient.
     * 
     * @param l
     *            The color to be set.
     */
    public void setColorBackground(short l) {
        colorBackground = l;
    }

    /**
     * Sets the color-index for the foreground color. <br>
     * For type <code>TYPE_SOLID</code>, this value defines the fill-color. For
     * type <code>TYPE_TEXTURE</code> this color is set for each value of the
     * texture pattern set to "true". For Type <code>TYPE_GRADIENT</code> this
     * color serves as the first color for the gradient.
     * 
     * @param l
     *            The color to be set.
     */
    public void setColorForeground(short l) {
        colorForeground = l;
    }

    /**
     * Sets the fill-type. <br>
     * The following values can be set: <br>
     * <ul>
     * <li><code>TYPE_HOLLOW</code>- No filling is painted and the element
     * remains transparent.</li>
     * <li><code>TYPE_SOLID</code>- Solid filling with current foreground color.
     * </li>
     * <li><code>TYPE_TEXTURE</code>- Texture pattern fill using currently set
     * texture-index and foreground and background color.</li>
     * <li><code>TYPE_GRADIENT</code>- Gradient fill using currently set
     * foreground and background color for gradient.</li>
     * </ul>
     * 
     * @param type
     *            The type to be set.
     */
    public void setFillType(AttribFillType type) {
        fillType = type;
    }

    /**
     * Sets the texture index to be used for type <code>TYPE_TEXTURE</code>.
     * Textures for indices are mapped by the AttributeHandler and can be set
     * there.
     * 
     * @param s
     *            The texture index to be set.
     */
    public void setTexture(short s) {
        texture = s;
    }

    /**
     * Returns the currently set gradient type.
     * 
     * @return The currently set gradient-type.
     */
    public AttribGradientType getGradientType() {
        return gradientType;
    }

    /**
     * The currently set system gradient used when type gradient is set. <br>
     * To ensure the correct state of this value, a call of
     * <code>update(...)</code>
     * 
     * @return The gradient currently set.
     */
    public Paint getSystemGradient() {
        return systemGradient;
    }

    /**
     * Sets the gradient type for this fill. One of he following gradients can
     * be set: <br>
     * <ul>
     * <li><code>GRADIENT_HORIZONTAL</code>- Horizontal gradient (default)</li>
     * <li><code>GRADIENT_VERTICAL</code>- Vertical gradient</li>
     * </ul>
     * 
     * @param type
     *            The gradient type to be used for this fill.
     */
    public void setGradientType(AttribGradientType type) {
        gradientType = type;
    }

}
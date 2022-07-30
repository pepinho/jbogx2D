//
// Copyright (c) 2004 by jbo - Josef Baro
//
// Project: jbogx2D
// File: AttribText.java
// Created: 10.03.2004 - 21:40:15
//

package de.jbo.jbogx2d.base.attrib;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;

import de.jbo.jbogx2d.base.Jbogx2D;
import de.jbo.jbogx2d.base.elements.model.ElemModel;

/**
 * Implements the base attribute structure of text elements. <br>
 * The following attributes can be set: <br>
 * <ul>
 * <li>font id corresponding to the current font mapping</li>
 * <li>font size</li>
 * <li>style bold</li>
 * <li>style italic</li>
 * <li>style underlined</li>
 * <li>style strikethrough</li>
 * <li>font color</li>
 * </ul>
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 10.03.2004: jbo created <br>
 */
public class AttribText implements AttribBase {
    /**
     * 
     */
    private static final float DEFAULT_SIZE = 12.0f;

    /** Style bit BOLD. */
    public static final short STYLE_BOLD = 0x01;

    /** Style bit ITALIC. */
    public static final short STYLE_ITALIC = 0x02;

    /** Style UNDERLINE. */
    public static final short STYLE_UNDERLINE = 0x04;

    /** Style STRIKETHROUGH. */
    public static final short STYLE_STRIKETHROUGH = 0x08;

    /** The color-index corresponding to the current color mapping. */
    private short color = 0;

    /** The size of the text in user space coordinates. */
    private float size = DEFAULT_SIZE;

    /** The font-index corresponding to the current font mapping. */
    private short fontId = 0;

    /** The style bit-field. Default is PLAIN, no style set. */
    private short style = 0;

    /** The system color used for rendering. */
    private Color systemColor = Color.BLACK;

    /** System-height. */
    private float systemHeight = 0.0f;

    /** System-ascent. */
    private float systemAscent = 0.0f;

    /** System-descent. */
    private float systemDescent = 0.0f;

    /** System-linefeed. */
    private double lineFeed = 1.0;

    /** Map containing the attributes of the text for the render engine. */
    private final Map<TextAttribute, Object> systemAttributes = new HashMap<>();

    /**
     * Creates a new instance. The following default values are used: <br>
     * <ul>
     * <li>font-id: 0 (first font in font-mapping)</li>
     * <li>font size: 12 (in user space coordinates)</li>
     * <li>style: plain (non-bold, non-italic, non-underlined,
     * non-strikethrough)</li>
     * <li>color: 0 (first color in color mapping)</li>
     * </ul>
     */
    public AttribText() {
        super();
    }

    /**
     * Adds the given style bit(s) to the style bit-field.
     * 
     * @param styleBit
     *            The style bit(s) to be added to the style bit-field.
     */
    public final void addStyle(final short styleBit) {
        style |= styleBit;
    }

    /**
     * Removes the given style bit(s) from the style bit-field.
     * 
     * @param styleBit
     *            The style bit(s) to be removed from the style bit-field.
     */
    public final void removeStyle(final short styleBit) {
        style &= (~styleBit);
    }

    /*
     * @see
     * de.jbo.jbogx2d.base.attrib.AttribBase#update(de.jbo.jbogx2d.base.elements
     * .model.ElemModel)
     */
    @Override
    public final void update(final ElemModel model) {
        AttributeHandler ah = Jbogx2D.getAttributeHandler();
        systemColor = ah.getColor(color);
        AffineTransform transform = (AffineTransform) systemAttributes.get(TextAttribute.TRANSFORM);

        systemAttributes.clear();

        // Restore transformation if exists
        if (transform != null) {
            systemAttributes.put(TextAttribute.TRANSFORM, transform);
        }

        // Set attributes
        Font font = ah.getFont(fontId);
        systemAttributes.put(TextAttribute.SIZE, Float.valueOf(size));
        systemAttributes.put(TextAttribute.FAMILY, font.getFamily());
        systemAttributes.put(TextAttribute.FONT, font);

        // styles
        if ((style & STYLE_BOLD) == STYLE_BOLD) {
            systemAttributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        }
        if ((style & STYLE_ITALIC) == STYLE_ITALIC) {
            systemAttributes.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
        }
        if ((style & STYLE_UNDERLINE) == STYLE_UNDERLINE) {
            systemAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        }
        if ((style & STYLE_STRIKETHROUGH) == STYLE_STRIKETHROUGH) {
            systemAttributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
        }

        FontRenderContext frc = new FontRenderContext(null, false, true);
        LineMetrics lm = Font.getFont(getSystemAttributes()).getLineMetrics("X", frc);
        systemHeight = lm.getHeight();
        systemAscent = lm.getAscent();
        systemDescent = lm.getDescent();
    }

    /**
     * @return The color.
     */
    public final short getColor() {
        return color;
    }

    /**
     * @return The font-id.
     */
    public final short getFontId() {
        return fontId;
    }

    /**
     * @return The size.
     */
    public final float getSize() {
        return size;
    }

    /**
     * @return The style-bits.
     */
    public final short getStyle() {
        return style;
    }

    /**
     * @return The system-attributes.
     */
    public final Map<TextAttribute, Object> getSystemAttributes() {
        return systemAttributes;
    }

    /**
     * @return The system-color.
     */
    public final Color getSystemColor() {
        return systemColor;
    }

    /**
     * @param s
     *            The color to be set.
     */
    public final void setColor(final short s) {
        color = s;
    }

    /**
     * @param s
     *            The font-id to be set.
     */
    public final void setFontId(final short s) {
        fontId = s;
    }

    /**
     * @param f
     *            The size to be set.
     */
    public final void setSize(final float f) {
        // Set attributes
        size = f;
        systemAttributes.put(TextAttribute.SIZE, Float.valueOf(size));
    }

    /**
     * @param s
     *            The style to be set.
     */
    public final void setStyle(final short s) {
        style = s;
    }

    /**
     * @return the lineFeed
     */
    public final double getLineFeed() {
        return lineFeed;
    }

    /**
     * @param lf
     *            the lineFeed to set
     */
    public final void setLineFeed(final double lf) {
        this.lineFeed = lf;
    }

    /**
     * @return the systemHeight
     */
    public final float getSystemHeight() {
        return systemHeight;
    }

    /**
     * @return the systemAscent
     */
    public final float getSystemAscent() {
        return systemAscent;
    }

    /**
     * @return the systemDescent
     */
    public final float getSystemDescent() {
        return systemDescent;
    }

}
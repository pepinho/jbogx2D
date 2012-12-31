//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: AttribFillType.java
// Created: 29.02.2004 - 08:38:51
//

package de.jbo.jbogx2d.base.attrib;

/**
 * Defines the fill-types supported by the fill-attribute.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 29.02.2004: jbo created <br>
 */
public final class AttribFillType {
    /** Fill type solid. */
    public static final AttribFillType TYPE_SOLID = new AttribFillType();

    /** Fill type hollow. */
    public static final AttribFillType TYPE_HOLLOW = new AttribFillType();

    /** Fill type gradient. */
    public static final AttribFillType TYPE_GRADIENT = new AttribFillType();

    /** Fill type texture. */
    public static final AttribFillType TYPE_TEXTURE = new AttribFillType();

    /**
     * Creates an empty instance.
     */
    private AttribFillType() {
        super();
    }
}
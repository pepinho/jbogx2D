package de.jbo.jbogx2d.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

/**
 * Extends the default border-layout specially for the graphics canvas. The
 * north-component is placed at the exact x-position as the center component.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 07.02.2010 jbo - created <br>
 */
public class CanvasBorderLayout extends BorderLayout {
    /**
     * Serial id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a border layout with the horizontal gaps between components.
     * The horizontal gap is specified by <code>hgap</code>.
     * 
     * @see #getHgap()
     * @see #setHgap(int)
     * @serial
     */
    private int hgap;

    /**
     * Constructs a border layout with the vertical gaps between components. The
     * vertical gap is specified by <code>vgap</code>.
     * 
     * @see #getVgap()
     * @see #setVgap(int)
     * @serial
     */
    private int vgap;

    /**
     * Constant to specify components location to be the north portion of the
     * border layout.
     * 
     * @serial
     * @see #getChild(String, boolean)
     * @see #addLayoutComponent
     * @see #getLayoutAlignmentX
     * @see #getLayoutAlignmentY
     * @see #removeLayoutComponent
     */
    private Component north;

    /**
     * Constant to specify components location to be the west portion of the
     * border layout.
     * 
     * @serial
     * @see #getChild(String, boolean)
     * @see #addLayoutComponent
     * @see #getLayoutAlignmentX
     * @see #getLayoutAlignmentY
     * @see #removeLayoutComponent
     */
    private Component west;

    /**
     * Constant to specify components location to be the east portion of the
     * border layout.
     * 
     * @serial
     * @see #getChild(String, boolean)
     * @see #addLayoutComponent
     * @see #getLayoutAlignmentX
     * @see #getLayoutAlignmentY
     * @see #removeLayoutComponent
     */
    private Component east;

    /**
     * Constant to specify components location to be the south portion of the
     * border layout.
     * 
     * @serial
     * @see #getChild(String, boolean)
     * @see #addLayoutComponent
     * @see #getLayoutAlignmentX
     * @see #getLayoutAlignmentY
     * @see #removeLayoutComponent
     */
    private Component south;

    /**
     * Constant to specify components location to be the center portion of the
     * border layout.
     * 
     * @serial
     * @see #getChild(String, boolean)
     * @see #addLayoutComponent
     * @see #getLayoutAlignmentX
     * @see #getLayoutAlignmentY
     * @see #removeLayoutComponent
     */
    private Component center;

    /**
     * A relative positioning constant, that can be used instead of north,
     * south, east, west or center. mixing the two types of constants can lead
     * to unpredicable results. If you use both types, the relative constants
     * will take precedence. For example, if you add components using both the
     * <code>NORTH</code> and <code>BEFORE_FIRST_LINE</code> constants in a
     * container whose orientation is <code>LEFT_TO_RIGHT</code>, only the
     * <code>BEFORE_FIRST_LINE</code> will be layed out. This will be the same
     * for lastLine, firstItem, lastItem.
     * 
     * @serial
     */
    private Component firstLine;

    /**
     * A relative positioning constant, that can be used instead of north,
     * south, east, west or center. Please read Description for firstLine.
     * 
     * @serial
     */
    private Component lastLine;

    /**
     * A relative positioning constant, that can be used instead of north,
     * south, east, west or center. Please read Description for firstLine.
     * 
     * @serial
     */
    private Component firstItem;

    /**
     * A relative positioning constant, that can be used instead of north,
     * south, east, west or center. Please read Description for firstLine.
     * 
     * @serial
     */
    private Component lastItem;

    /**
     * Constructs a new border layout with no gaps between components.
     */
    public CanvasBorderLayout() {
        this(0, 0);
    }

    /**
     * Constructs a border layout with the specified gaps between components.
     * The horizontal gap is specified by <code>hgap</code> and the vertical gap
     * is specified by <code>vgap</code>.
     * 
     * @param horGap
     *            the horizontal gap.
     * @param verGap
     *            the vertical gap.
     */
    public CanvasBorderLayout(final int horGap, final int verGap) {
        this.hgap = horGap;
        this.vgap = verGap;
    }

    /**
     * Returns the horizontal gap between components.
     * 
     * @return The horizontal gap.
     * @since JDK1.1
     */
    @Override
    public int getHgap() {
        return hgap;
    }

    /**
     * Sets the horizontal gap between components.
     * 
     * @param horGap
     *            the horizontal gap between components
     * @since JDK1.1
     */
    @Override
    public void setHgap(final int horGap) {
        this.hgap = horGap;
    }

    /**
     * Returns the vertical gap between components.
     * 
     * @return The vertical gap.
     * @since JDK1.1
     */
    @Override
    public int getVgap() {
        return vgap;
    }

    /**
     * Sets the vertical gap between components.
     * 
     * @param value
     *            the vertical gap between components
     * @since JDK1.1
     */
    @Override
    public void setVgap(final int value) {
        this.vgap = value;
    }

    /**
     * Removes the specified component from this border layout. This method is
     * called when a container calls its <code>remove</code> or
     * <code>removeAll</code> methods. Most applications do not call this method
     * directly.
     * 
     * @param comp
     *            the component to be removed.
     * @see java.awt.Container#remove(java.awt.Component)
     * @see java.awt.Container#removeAll()
     */
    @Override
    public void removeLayoutComponent(final Component comp) {
        synchronized (comp.getTreeLock()) {
            if (comp == center) {
                center = null;
            } else if (comp == north) {
                north = null;
            } else if (comp == south) {
                south = null;
            } else if (comp == east) {
                east = null;
            } else if (comp == west) {
                west = null;
            }
            if (comp == firstLine) {
                firstLine = null;
            } else if (comp == lastLine) {
                lastLine = null;
            } else if (comp == firstItem) {
                firstItem = null;
            } else if (comp == lastItem) {
                lastItem = null;
            }
        }
    }

    /**
     * Gets the component that was added using the given constraint.
     * 
     * @param constraints
     *            the desired constraint, one of <code>CENTER</code>,
     *            <code>NORTH</code>, <code>SOUTH</code>, <code>WEST</code>,
     *            <code>EAST</code>, <code>PAGE_START</code>,
     *            <code>PAGE_END</code>, <code>LINE_START</code>,
     *            <code>LINE_END</code>
     * @return the component at the given location, or <code>null</code> if the
     *         location is empty
     * @see #addLayoutComponent(java.awt.Component, java.lang.Object)
     * @since 1.5
     */
    @Override
    public Component getLayoutComponent(final Object constraints) {
        if (CENTER.equals(constraints)) {
            return center;
        } else if (NORTH.equals(constraints)) {
            return north;
        } else if (SOUTH.equals(constraints)) {
            return south;
        } else if (WEST.equals(constraints)) {
            return west;
        } else if (EAST.equals(constraints)) {
            return east;
        } else if (PAGE_START.equals(constraints)) {
            return firstLine;
        } else if (PAGE_END.equals(constraints)) {
            return lastLine;
        } else if (LINE_START.equals(constraints)) {
            return firstItem;
        } else if (LINE_END.equals(constraints)) {
            return lastItem;
        } else {
            throw new IllegalArgumentException("cannot get component: unknown constraint: " + constraints);
        }
    }

    /**
     * Returns the component that corresponds to the given constraint location
     * based on the target <code>Container</code>'s component orientation.
     * Components added with the relative constraints <code>PAGE_START</code>,
     * <code>PAGE_END</code>, <code>LINE_START</code>, and <code>LINE_END</code>
     * take precedence over components added with the explicit constraints
     * <code>NORTH</code>, <code>SOUTH</code>, <code>WEST</code>, and
     * <code>EAST</code>. The <code>Container</code>'s component orientation is
     * used to determine the location of components added with
     * <code>LINE_START</code> and <code>LINE_END</code>.
     * 
     * @param constraints
     *            the desired absolute position, one of <code>CENTER</code>,
     *            <code>NORTH</code>, <code>SOUTH</code>, <code>EAST</code>,
     *            <code>WEST</code>
     * @param target
     *            the {@code Container} used to obtain the constraint location
     *            based on the target {@code Container} 's component
     *            orientation.
     * @return the component at the given location, or <code>null</code> if the
     *         location is empty
     * @see #addLayoutComponent(java.awt.Component, java.lang.Object)
     * @since 1.5
     */
    @Override
    public Component getLayoutComponent(final Container target, final Object constraints) {
        boolean ltr = target.getComponentOrientation().isLeftToRight();
        Component result = null;

        if (NORTH.equals(constraints)) {
            result = (firstLine != null) ? firstLine : north;
        } else if (SOUTH.equals(constraints)) {
            result = (lastLine != null) ? lastLine : south;
        } else if (WEST.equals(constraints)) {
            result = ltr ? firstItem : lastItem;
            if (result == null) {
                result = west;
            }
        } else if (EAST.equals(constraints)) {
            result = ltr ? lastItem : firstItem;
            if (result == null) {
                result = east;
            }
        } else if (CENTER.equals(constraints)) {
            result = center;
        } else {
            throw new IllegalArgumentException("cannot get component: invalid constraint: " + constraints);
        }

        return result;
    }

    /**
     * Gets the constraints for the specified component.
     * 
     * @param comp
     *            the component to be queried
     * @return the constraint for the specified component, or null if component
     *         is null or is not present in this layout
     * @see #addLayoutComponent(java.awt.Component, java.lang.Object)
     * @since 1.5
     */
    @Override
    public Object getConstraints(final Component comp) {
        // fix for 6242148 : API method
        // java.awt.BorderLayout.getConstraints(null) should return null
        if (comp == null) {
            return null;
        }
        if (comp == center) {
            return CENTER;
        } else if (comp == north) {
            return NORTH;
        } else if (comp == south) {
            return SOUTH;
        } else if (comp == west) {
            return WEST;
        } else if (comp == east) {
            return EAST;
        } else if (comp == firstLine) {
            return PAGE_START;
        } else if (comp == lastLine) {
            return PAGE_END;
        } else if (comp == firstItem) {
            return LINE_START;
        } else if (comp == lastItem) {
            return LINE_END;
        }
        return null;
    }

    /**
     * Determines the minimum size of the <code>target</code> container using
     * this layout manager.
     * <p>
     * This method is called when a container calls its
     * <code>getMinimumSize</code> method. Most applications do not call this
     * method directly.
     * 
     * @param target
     *            the container in which to do the layout.
     * @return the minimum dimensions needed to lay out the subcomponents of the
     *         specified container.
     * @see java.awt.Container
     * @see java.awt.BorderLayout#preferredLayoutSize
     * @see java.awt.Container#getMinimumSize()
     */
    @Override
    public Dimension minimumLayoutSize(final Container target) {
        synchronized (target.getTreeLock()) {
            Dimension dim = new Dimension(0, 0);

            boolean ltr = target.getComponentOrientation().isLeftToRight();
            Component c = null;

            c = getChild(EAST, ltr);
            if (c != null) {
                Dimension d = c.getMinimumSize();
                dim.width += d.width + hgap;
                dim.height = Math.max(d.height, dim.height);
            }
            c = getChild(WEST, ltr);
            if (c != null) {
                Dimension d = c.getMinimumSize();
                dim.width += d.width + hgap;
                dim.height = Math.max(d.height, dim.height);
            }
            c = getChild(CENTER, ltr);
            if (c != null) {
                Dimension d = c.getMinimumSize();
                dim.width += d.width;
                dim.height = Math.max(d.height, dim.height);
            }
            c = getChild(NORTH, ltr);
            if (c != null) {
                Dimension d = c.getMinimumSize();
                dim.width = Math.max(d.width, dim.width);
                dim.height += d.height + vgap;
            }
            c = getChild(SOUTH, ltr);
            if (c != null) {
                Dimension d = c.getMinimumSize();
                dim.width = Math.max(d.width, dim.width);
                dim.height += d.height + vgap;
            }

            Insets insets = target.getInsets();
            dim.width += insets.left + insets.right;
            dim.height += insets.top + insets.bottom;

            return dim;
        }
    }

    /**
     * Determines the preferred size of the <code>target</code> container using
     * this layout manager, based on the components in the container.
     * <p>
     * Most applications do not call this method directly. This method is called
     * when a container calls its <code>getPreferredSize</code> method.
     * 
     * @param target
     *            the container in which to do the layout.
     * @return the preferred dimensions to lay out the subcomponents of the
     *         specified container.
     * @see java.awt.Container
     * @see java.awt.BorderLayout#minimumLayoutSize
     * @see java.awt.Container#getPreferredSize()
     */
    @Override
    public Dimension preferredLayoutSize(final Container target) {
        synchronized (target.getTreeLock()) {
            Dimension dim = new Dimension(0, 0);

            boolean ltr = target.getComponentOrientation().isLeftToRight();
            Component c = null;

            c = getChild(EAST, ltr);
            if (c != null) {
                Dimension d = c.getPreferredSize();
                dim.width += d.width + hgap;
                dim.height = Math.max(d.height, dim.height);
            }
            c = getChild(WEST, ltr);
            if (c != null) {
                Dimension d = c.getPreferredSize();
                dim.width += d.width + hgap;
                dim.height = Math.max(d.height, dim.height);
            }
            c = getChild(CENTER, ltr);
            if (c != null) {
                Dimension d = c.getPreferredSize();
                dim.width += d.width;
                dim.height = Math.max(d.height, dim.height);
            }
            c = getChild(NORTH, ltr);
            if (c != null) {
                Dimension d = c.getPreferredSize();
                dim.width = Math.max(d.width, dim.width);
                dim.height += d.height + vgap;
            }
            c = getChild(SOUTH, ltr);
            if (c != null) {
                Dimension d = c.getPreferredSize();
                dim.width = Math.max(d.width, dim.width);
                dim.height += d.height + vgap;
            }

            Insets insets = target.getInsets();
            dim.width += insets.left + insets.right;
            dim.height += insets.top + insets.bottom;

            return dim;
        }
    }

    /**
     * Lays out the container argument using this border layout.
     * <p>
     * This method actually reshapes the components in the specified container
     * in order to satisfy the constraints of this <code>BorderLayout</code>
     * object. The <code>NORTH</code> and <code>SOUTH</code> components, if any,
     * are placed at the top and bottom of the container, respectively. The
     * <code>WEST</code> and <code>EAST</code> components are then placed on the
     * left and right, respectively. Finally, the <code>CENTER</code> object is
     * placed in any remaining space in the middle.
     * <p>
     * Most applications do not call this method directly. This method is called
     * when a container calls its <code>doLayout</code> method.
     * 
     * @param target
     *            the container in which to do the layout.
     * @see java.awt.Container
     * @see java.awt.Container#doLayout()
     */
    @Override
    public void layoutContainer(final Container target) {
        synchronized (target.getTreeLock()) {
            Insets insets = target.getInsets();
            int top = insets.top;
            int bottom = target.getHeight() - insets.bottom;
            int left = insets.left;
            int right = target.getWidth() - insets.right;

            boolean ltr = target.getComponentOrientation().isLeftToRight();
            Component c = null;

            c = getChild(NORTH, ltr);
            if (c != null) {
                c.setSize(right - left, c.getHeight());
                Dimension d = c.getPreferredSize();
                c.setBounds(left, top, right - left, d.height);
                top += d.height + vgap;
            }
            c = getChild(SOUTH, ltr);
            if (c != null) {
                c.setSize(right - left, c.getHeight());
                Dimension d = c.getPreferredSize();
                c.setBounds(left, bottom - d.height, right - left, d.height);
                bottom -= d.height + vgap;
            }
            c = getChild(EAST, ltr);
            if (c != null) {
                c.setSize(c.getWidth(), bottom - top);
                Dimension d = c.getPreferredSize();
                c.setBounds(right - d.width, top, d.width, bottom - top);
                right -= d.width + hgap;
            }
            c = getChild(WEST, ltr);
            if (c != null) {
                c.setSize(c.getWidth(), bottom - top);
                Dimension d = c.getPreferredSize();
                c.setBounds(left, top - 1, d.width, bottom - top);
                left += d.width + hgap;
            }
            c = getChild(CENTER, ltr);
            if (c != null) {
                c.setBounds(left, top, right - left, bottom - top);
            }
            c = getChild(NORTH, ltr);
            if (c != null) {
                Dimension d = c.getPreferredSize();
                c.setBounds(left, insets.top, right - left, d.height);
            }
        }
    }

    /**
     * Get the component that corresponds to the given constraint location.
     * 
     * @param key
     *            The desired absolute position, either NORTH, SOUTH, EAST, or
     *            WEST.
     * @param ltr
     *            Is the component line direction left-to-right?
     * @return The corresponding component.
     */
    private Component getChild(final String key, final boolean ltr) {
        Component result = null;

        if (key == NORTH) {
            result = (firstLine != null) ? firstLine : north;
        } else if (key == SOUTH) {
            result = (lastLine != null) ? lastLine : south;
        } else if (key == WEST) {
            result = ltr ? firstItem : lastItem;
            if (result == null) {
                result = west;
            }
        } else if (key == EAST) {
            result = ltr ? lastItem : firstItem;
            if (result == null) {
                result = east;
            }
        } else if (key == CENTER) {
            result = center;
        }
        if ((result != null) && !result.isVisible()) {
            result = null;
        }
        return result;
    }
}

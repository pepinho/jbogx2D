package de.jbo.jbogx2d.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;

/**
 * Extends the default border-layout specially for the graphics canvas. The
 * north-component is placed at the exact x-position as the center component.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 07.02.2010 jbo - created <br>
 */
public class CanvasBorderLayout implements LayoutManager2, java.io.Serializable {
    /**
     * Serial id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default layout alignment.
     */
    private static final float DEFAULT_LAYOUT_ALIGNMENT = 0.5f;

    /**
     * Constructs a border layout with the horizontal gaps between components.
     * The horizontal gap is specified by <code>hgap</code>.
     * 
     * @see #getHgap()
     * @see #setHgap(int)
     * 
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
    private Component componentNorth;

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
    private Component componentWest;

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
    private Component componentEast;

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
    private Component componentSouth;

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
    private Component componentCenter;

    /**
     * 
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
     * The north layout constraint (top of container).
     */
    public static final String NORTH = "North";

    /**
     * The south layout constraint (bottom of container).
     */
    public static final String SOUTH = "South";

    /**
     * The east layout constraint (right side of container).
     */
    public static final String EAST = "East";

    /**
     * The west layout constraint (left side of container).
     */
    public static final String WEST = "West";

    /**
     * The center layout constraint (middle of container).
     */
    public static final String CENTER = "Center";

    /**
     * Synonym for PAGE_START. Exists for compatibility with previous versions.
     * PAGE_START is preferred.
     * 
     * @see #PAGE_START
     * @since 1.2
     */
    public static final String BEFORE_FIRST_LINE = "First";

    /**
     * Synonym for PAGE_END. Exists for compatibility with previous versions.
     * PAGE_END is preferred.
     * 
     * @see #PAGE_END
     * @since 1.2
     */
    public static final String AFTER_LAST_LINE = "Last";

    /**
     * Synonym for LINE_START. Exists for compatibility with previous versions.
     * LINE_START is preferred.
     * 
     * @see #LINE_START
     * @since 1.2
     */
    public static final String BEFORE_LINE_BEGINS = "Before";

    /**
     * Synonym for LINE_END. Exists for compatibility with previous versions.
     * LINE_END is preferred.
     * 
     * @see #LINE_END
     * @since 1.2
     */
    public static final String AFTER_LINE_ENDS = "After";

    /**
     * The component comes before the first line of the layout's content. For
     * Western, left-to-right and top-to-bottom orientations, this is equivalent
     * to NORTH.
     * 
     * @see java.awt.Component#getComponentOrientation
     * @since 1.4
     */
    public static final String PAGE_START = BEFORE_FIRST_LINE;

    /**
     * The component comes after the last line of the layout's content. For
     * Western, left-to-right and top-to-bottom orientations, this is equivalent
     * to SOUTH.
     * 
     * @see java.awt.Component#getComponentOrientation
     * @since 1.4
     */
    public static final String PAGE_END = AFTER_LAST_LINE;

    /**
     * The component goes at the beginning of the line direction for the layout.
     * For Western, left-to-right and top-to-bottom orientations, this is
     * equivalent to WEST.
     * 
     * @see java.awt.Component#getComponentOrientation
     * @since 1.4
     */
    public static final String LINE_START = BEFORE_LINE_BEGINS;

    /**
     * The component goes at the end of the line direction for the layout. For
     * Western, left-to-right and top-to-bottom orientations, this is equivalent
     * to EAST.
     * 
     * @see java.awt.Component#getComponentOrientation
     * @since 1.4
     */
    public static final String LINE_END = AFTER_LINE_ENDS;

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
    public void setHgap(final int horGap) {
        this.hgap = horGap;
    }

    /**
     * Returns the vertical gap between components.
     * 
     * @return The vertical gap.
     * @since JDK1.1
     */
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
            if (comp == componentCenter) {
                componentCenter = null;
            } else if (comp == componentNorth) {
                componentNorth = null;
            } else if (comp == componentSouth) {
                componentSouth = null;
            } else if (comp == componentEast) {
                componentEast = null;
            } else if (comp == componentWest) {
                componentWest = null;
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
    public Component getLayoutComponent(final Object constraints) {
        if (CENTER.equals(constraints)) {
            return componentCenter;
        } else if (NORTH.equals(constraints)) {
            return componentNorth;
        } else if (SOUTH.equals(constraints)) {
            return componentSouth;
        } else if (WEST.equals(constraints)) {
            return componentWest;
        } else if (EAST.equals(constraints)) {
            return componentEast;
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
    public Component getLayoutComponent(final Container target, final Object constraints) {
        boolean ltr = target.getComponentOrientation().isLeftToRight();
        Component result = null;

        if (NORTH.equals(constraints)) {
            result = getChildNorth();
        } else if (SOUTH.equals(constraints)) {
            result = getChildSouth();
        } else if (WEST.equals(constraints)) {
            result = getChildWest(ltr);
        } else if (EAST.equals(constraints)) {
            result = getChildEast(ltr);
        } else if (CENTER.equals(constraints)) {
            result = componentCenter;
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
    public Object getConstraints(final Component comp) {
        // fix for 6242148 : API method
        // java.awt.BorderLayout.getConstraints(null) should return null
        if (comp == null) {
            return null;
        }
        if (comp == componentCenter) {
            return CENTER;
        } else if (comp == componentNorth) {
            return NORTH;
        } else if (comp == componentSouth) {
            return SOUTH;
        } else if (comp == componentWest) {
            return WEST;
        } else if (comp == componentEast) {
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

        if (NORTH.equals(key)) {
            result = getChildNorth();
        } else if (SOUTH.equals(key)) {
            result = getChildSouth();
        } else if (WEST.equals(key)) {
            result = getChildWest(ltr);
        } else if (EAST.equals(key)) {
            result = getChildEast(ltr);
        } else if (CENTER.equals(key)) {
            result = componentCenter;
        }
        if ((result != null) && !result.isVisible()) {
            result = null;
        }
        return result;
    }

    private Component getChildEast(final boolean ltr) {
        Component result;
        result = ltr ? lastItem : firstItem;
        if (result == null) {
            result = componentEast;
        }
        return result;
    }

    private Component getChildWest(final boolean ltr) {
        Component result;
        result = ltr ? firstItem : lastItem;
        if (result == null) {
            result = componentWest;
        }
        return result;
    }

    private Component getChildSouth() {
        Component result;
        result = (lastLine != null) ? lastLine : componentSouth;
        return result;
    }

    private Component getChildNorth() {
        Component result;
        result = (firstLine != null) ? firstLine : componentNorth;
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.LayoutManager#addLayoutComponent(java.lang.String,
     * java.awt.Component)
     */
    @Override
    public void addLayoutComponent(String name, Component comp) {
        synchronized (comp.getTreeLock()) {
            /* Special case: treat null the same as "Center". */
            if (name == null) {
                name = CENTER;
            }

            /*
             * Assign the component to one of the known regions of the layout.
             */
            if (CENTER.equals(name)) {
                componentCenter = comp;
            } else if (NORTH.equals(name)) {
                componentNorth = comp;
            } else if (SOUTH.equals(name)) {
                componentSouth = comp;
            } else if (EAST.equals(name)) {
                componentEast = comp;
            } else if (WEST.equals(name)) {
                componentWest = comp;
            } else if (BEFORE_FIRST_LINE.equals(name)) {
                firstLine = comp;
            } else if (AFTER_LAST_LINE.equals(name)) {
                lastLine = comp;
            } else if (BEFORE_LINE_BEGINS.equals(name)) {
                firstItem = comp;
            } else if (AFTER_LINE_ENDS.equals(name)) {
                lastItem = comp;
            } else {
                throw new IllegalArgumentException("cannot add to layout: unknown constraint: " + name);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.LayoutManager2#addLayoutComponent(java.awt.Component,
     * java.lang.Object)
     */
    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        synchronized (comp.getTreeLock()) {
            if ((constraints == null) || (constraints instanceof String)) {
                addLayoutComponent((String) constraints, comp);
            } else {
                throw new IllegalArgumentException("cannot add to layout: constraint must be a string (or null)");
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.LayoutManager2#maximumLayoutSize(java.awt.Container)
     */
    @Override
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.LayoutManager2#getLayoutAlignmentX(java.awt.Container)
     */
    @Override
    public float getLayoutAlignmentX(Container target) {
        return DEFAULT_LAYOUT_ALIGNMENT;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.LayoutManager2#getLayoutAlignmentY(java.awt.Container)
     */
    @Override
    public float getLayoutAlignmentY(Container target) {
        return DEFAULT_LAYOUT_ALIGNMENT;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.LayoutManager2#invalidateLayout(java.awt.Container)
     */
    @Override
    public void invalidateLayout(Container target) {
        // ntbd
    }
}

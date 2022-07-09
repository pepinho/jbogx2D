//
// Copyright (c) 2010 by jbo - Josef Baro
//
// Project: jbogx2D
// File: MouseInteractionHandler.java
// Created: 25.01.2010 - 19:43:43
//
package de.jbo.jbogx2d.ui.interaction;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Collection;
import java.util.TreeSet;

import de.jbo.jbogx2d.base.geom.BoundsUserSpace;
import de.jbo.jbogx2d.base.geom.PointScreen;
import de.jbo.jbogx2d.base.geom.PointUserSpace;
import de.jbo.jbogx2d.base.graphics.ViewContext;
import de.jbo.jbogx2d.base.graphics.ViewContextScreen;
import de.jbo.jbogx2d.base.graphics.ViewHandler;

/**
 * Implements the mouse-interaction handler. Mouse-events on a screen-component
 * are being handled and delegated to an implementation of
 * <code>IMouseInteraction</code>.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 25.01.2010 jbo - created <br>
 */
public final class MouseInteractionHandler implements MouseListener, MouseMotionListener, MouseWheelListener {
    /** The screen view context we are handling the mouse-events for. */
    private ViewContextScreen viewScreen = null;

    /** The active mouse-interaction instance. */
    private IMouseInteraction mouseInteraction = null;

    /** The view-handler. */
    private ViewHandler viewHandler = null;

    /** List of XOR elements. */
    private final Collection<IMouseInteractionXorElement> xorElements = new TreeSet<>();

    /**
     * Creates a new instance.
     * 
     * @param view
     *            The view we are handling.
     * @param vh
     *            The view-handler.
     */
    public MouseInteractionHandler(ViewContextScreen view, ViewHandler vh) {
        viewScreen = view;
        viewHandler = vh;
        viewScreen.getScreenComponent().addMouseListener(this);
        viewScreen.getScreenComponent().addMouseMotionListener(this);
        viewScreen.getScreenComponent().addMouseWheelListener(this);
    }

    /**
     * Paints the XOR elements to the given context.
     * 
     * @param viewContext
     *            Context to render to.
     */
    public void paintXorElemens(ViewContext viewContext) {
        synchronized (xorElements) {
            for (IMouseInteractionXorElement element : xorElements) {
                element.paint(viewContext);
            }
        }
    }

    /**
     * Uninitializes this instance.
     */
    public void uninit() {
        viewScreen.getScreenComponent().removeMouseListener(this);
        viewScreen.getScreenComponent().removeMouseMotionListener(this);
        viewScreen.getScreenComponent().removeMouseWheelListener(this);
        if (mouseInteraction != null) {
            mouseInteraction.uninit();
            mouseInteraction = null;
        }
        viewScreen = null;
        viewHandler = null;
    }

    /**
     * @return The current mouse-interaction we are handling. Can be null.
     */
    public IMouseInteraction getMouseInteraction() {
        return mouseInteraction;
    }

    /**
     * Sets the active mouse-interaction. A previous interaction - if set - is
     * uninitialized.
     * 
     * @param mouseInteract
     *            Interaction to be set.
     * @see IMouseInteraction#uninit()
     */
    public void setMouseInteraction(IMouseInteraction mouseInteract) {
        if (mouseInteraction != null) {
            mouseInteraction.uninit();
        }
        mouseInteraction = mouseInteract;
        mouseInteraction.init(this, viewHandler);
    }

    /**
     * Converts the given mouse-screen coordinates to the corresponding
     * user-space coordinates.
     * 
     * @param x
     *            x-coordinate of the mouse cursor.
     * @param y
     *            y-coordinate of the mouse cursor.
     * @return The converter user-space point.
     */
    private PointUserSpace convertMousePosition(int x, int y) {
        PointUserSpace point = new PointUserSpace();
        viewScreen.transformScreenToUserSpace(new PointScreen(x, y), point);
        return point;
    }

    /**
     * Handles the given paint-request.
     * 
     * @param paintRequest
     *            The request to be handled.
     */
    private void handlePaintRequest(PaintRequest paintRequest) {
        if (paintRequest != null) {
            boolean doPaint = paintRequest.isRepaintRequested();
            if (doPaint) {
                BoundsUserSpace bounds = paintRequest.getBoundsModified();
                if (bounds == null) {
                    bounds = new BoundsUserSpace();
                    viewHandler.getVisibleUserSpace(bounds, true);
                }
                viewHandler.onDrawingModified(viewHandler.getDrawing(), bounds);
            }

            if (paintRequest.isResetXor()) {
                xorElements.removeAll(paintRequest.getXorElements());
            } else {
                xorElements.addAll(paintRequest.getXorElements());
            }
            if (doPaint || (!xorElements.isEmpty())) {
                repaint();
            }

        }
    }

    /*
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (mouseInteraction != null && e.getClickCount() == 2) {
            PaintRequest paintRequest = null;
            PointUserSpace mousePos = convertMousePosition(e.getX(), e.getY());
            int keyMask = MouseInteractionUtils.getKeyMask(e);
            switch (getButton(e)) {
            case MouseEvent.BUTTON1:
                paintRequest = mouseInteraction.handleMouseDoubleclickLeft(mousePos, keyMask);
                break;
            case MouseEvent.BUTTON2:
                paintRequest = mouseInteraction.handleMouseDoubleclickMiddle(mousePos, keyMask);
                break;
            case MouseEvent.BUTTON3:
                paintRequest = mouseInteraction.handleMouseDoubleclickRight(mousePos, keyMask);
                break;
            default:
                // nothing to do
                break;
            }
            handlePaintRequest(paintRequest);
        }
    }

    /*
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        if (mouseInteraction != null) {
            PaintRequest paintRequest = null;
            PointUserSpace mousePos = convertMousePosition(e.getX(), e.getY());
            int keyMask = MouseInteractionUtils.getKeyMask(e);
            paintRequest = mouseInteraction.handleMouseEntered(mousePos, keyMask);
            handlePaintRequest(paintRequest);
        }

    }

    /*
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
        if (mouseInteraction != null) {
            PaintRequest paintRequest = null;
            PointUserSpace mousePos = convertMousePosition(e.getX(), e.getY());
            int keyMask = MouseInteractionUtils.getKeyMask(e);
            paintRequest = mouseInteraction.handleMouseExited(mousePos, keyMask);
            handlePaintRequest(paintRequest);
        }
    }

    /*
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (mouseInteraction != null) {
            PaintRequest paintRequest = null;
            PointUserSpace mousePos = convertMousePosition(e.getX(), e.getY());
            int keyMask = MouseInteractionUtils.getKeyMask(e);
            switch (getButton(e)) {
            case MouseEvent.BUTTON1:
                paintRequest = mouseInteraction.handleMousePressedLeft(mousePos, keyMask);
                break;
            case MouseEvent.BUTTON2:
                paintRequest = mouseInteraction.handleMousePressedMiddle(mousePos, keyMask);
                break;
            case MouseEvent.BUTTON3:
                paintRequest = mouseInteraction.handleMousePressedRight(mousePos, keyMask);
                break;
            default:
                // nothing to do
                break;
            }
            handlePaintRequest(paintRequest);
        }

    }

    /*
     * @see
     * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (mouseInteraction != null) {
            PaintRequest paintRequest = null;
            PointUserSpace mousePos = convertMousePosition(e.getX(), e.getY());
            int keyMask = MouseInteractionUtils.getKeyMask(e);
            switch (getButton(e)) {
            case MouseEvent.BUTTON1:
                paintRequest = mouseInteraction.handleMouseReleasedLeft(mousePos, keyMask);
                break;
            case MouseEvent.BUTTON2:
                paintRequest = mouseInteraction.handleMouseReleasedMiddle(mousePos, keyMask);
                break;
            case MouseEvent.BUTTON3:
                paintRequest = mouseInteraction.handleMouseReleasedRight(mousePos, keyMask);
                break;
            default:
                // nothing to do
                break;
            }
            handlePaintRequest(paintRequest);
        }
    }

    /**
     * Inquires the pressed button.
     * 
     * @param e
     *            Event to be checked.
     * @return The mouse-button id.
     * @see java.awt.event.MouseEvent#BUTTON1
     * @see java.awt.event.MouseEvent#BUTTON2
     * @see java.awt.event.MouseEvent#BUTTON3
     */
    private int getButton(MouseEvent e) {
        int ret = MouseEvent.BUTTON1;
        if ((e.getModifiersEx() & InputEvent.BUTTON2_DOWN_MASK) == InputEvent.BUTTON2_DOWN_MASK) {
            ret = MouseEvent.BUTTON2;
        } else if ((e.getModifiersEx() & InputEvent.BUTTON3_DOWN_MASK) == InputEvent.BUTTON3_DOWN_MASK) {
            ret = MouseEvent.BUTTON3;
        }
        return ret;
    }

    /*
     * @see
     * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
     * )
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (mouseInteraction != null) {
            PaintRequest paintRequest = null;
            PointUserSpace mousePos = convertMousePosition(e.getX(), e.getY());
            int keyMask = MouseInteractionUtils.getKeyMask(e);
            switch (getButton(e)) {
            case MouseEvent.BUTTON1:
                paintRequest = mouseInteraction.handleMouseDragLeft(mousePos, keyMask);
                break;
            case MouseEvent.BUTTON2:
                paintRequest = mouseInteraction.handleMouseDragMiddle(mousePos, keyMask);
                break;
            case MouseEvent.BUTTON3:
                paintRequest = mouseInteraction.handleMouseDragRight(mousePos, keyMask);
                break;
            default:
                // nothing to do
                break;
            }
            handlePaintRequest(paintRequest);
        }
    }

    /*
     * @see
     * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (mouseInteraction != null) {
            PaintRequest paintRequest = null;
            PointUserSpace mousePos = convertMousePosition(e.getX(), e.getY());
            int keyMask = MouseInteractionUtils.getKeyMask(e);
            paintRequest = mouseInteraction.handleMouseMoved(mousePos, keyMask);
            handlePaintRequest(paintRequest);
        }
    }

    /*
     * @seejava.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.
     * MouseWheelEvent)
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (mouseInteraction != null) {
            PaintRequest paintRequest = null;
            PointUserSpace mousePos = convertMousePosition(e.getX(), e.getY());
            int keyMask = MouseInteractionUtils.getKeyMask(e);
            paintRequest = mouseInteraction.handleMouseWheel(mousePos, keyMask, e.getWheelRotation(), e.getScrollAmount(), e.getScrollType(), e.getUnitsToScroll());
            handlePaintRequest(paintRequest);
        }
    }

    /**
     * Repaints the corresponding view.
     */
    private void repaint() {
        viewHandler.repaint();
    }
}

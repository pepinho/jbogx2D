//
// Copyright (c) 2004 by jbo - Josef Baro
//
// Project: jbogx2D
// File: Drawing.java
// Created: 28.02.2004 - 14:49:53
//

package de.jbo.jbogx2d.base.drawing;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import de.jbo.jbogx2d.base.drawing.traversing.ElementTraverser;
import de.jbo.jbogx2d.base.elements.ElemBase;
import de.jbo.jbogx2d.base.geom.BoundsUserSpace;

/**
 * Implements the drawing object handling graphic elements.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 28.02.2004: jbo created <br>
 */
public class Drawing {
    /** The root hierarchy level of the elements tree. */
    public static final short LEVEL_ROOT = 0;

    /** The background color of the drawing. */
    private short colorBackground = 0;

    /** The user space coordinates for this instance. */
    private final BoundsUserSpace boundsUserSpace = new BoundsUserSpace(0, 0, 297, 210);

    /** List containing the drawing's layers. */
    private final LinkedList<DrawingLayer> layerList = new LinkedList<DrawingLayer>();

    /** Modification listeners. */
    private final LinkedList<IDrawingModifiedListener> modificationListeners = new LinkedList<IDrawingModifiedListener>();

    /**
     * Creates a new empty drawing with a default layer.
     */
    public Drawing() {
        addLayerFirst(DrawingLayer.NAME_DEFAULT);
    }

    /**
     * Adds a modification listener.
     * 
     * @param listener
     *            Listener to be added.
     */
    public void addModificationListener(IDrawingModifiedListener listener) {
        synchronized (modificationListeners) {
            if (!modificationListeners.contains(listener)) {
                modificationListeners.add(listener);
            }
        }
    }

    /**
     * Fires a modification event and notifies all registered listeners.
     * 
     * @param modifiedBounds
     *            The modified bounds.
     * @see #addModificationListener(IDrawingModifiedListener)
     * @see #removeModificationListener(IDrawingModifiedListener)
     */
    public void fireModificationEvent(BoundsUserSpace modifiedBounds) {
        synchronized (modificationListeners) {
            for (IDrawingModifiedListener listener : modificationListeners) {
                listener.onDrawingModified(this, modifiedBounds);
            }
        }
    }

    /**
     * Removes the given listener.
     * 
     * @param listener
     *            The listener to be removed.
     */
    public void removeModificationListener(IDrawingModifiedListener listener) {
        synchronized (modificationListeners) {
            modificationListeners.remove(listener);
        }
    }

    /**
     * Returns the user space coordinates for this instance.
     * 
     * @param us
     *            Receives the user space coordinates as an out-param.
     */
    public synchronized void getUserSpaceBounds(BoundsUserSpace us) {
        us.set(boundsUserSpace);
    }

    /**
     * @param us
     *            User-space bounds to be set.
     */
    public synchronized void setUserSpaceBounds(BoundsUserSpace us) {
        boundsUserSpace.set(us);
    }

    /**
     * Adds a new layer with the given name to the 1st position.
     * 
     * @param layerName
     *            The name to be set for the new layer.
     * @return True if the layer was added successfully.
     */
    public synchronized boolean addLayerFirst(String layerName) {
        boolean added = false;
        if (getLayer(layerName) == null) {
            layerList.addFirst(new DrawingLayer(layerName, this));
            added = true;
        }
        return added;
    }

    /**
     * Adds a new layer with the given name to the last position.
     * 
     * @param layerName
     *            The name to be set for the new layer.
     * @return True if the layer was added successfully.
     */
    public synchronized boolean addLayerLast(String layerName) {
        boolean added = false;
        if (getLayer(layerName) == null) {
            layerList.addLast(new DrawingLayer(layerName, this));
            added = true;
        }
        return added;
    }

    /**
     * Sets the layer with the given name to the given position.
     * 
     * @param layerName
     *            The name of the layer to be moved.
     * @param index
     *            The position to move the layer to.
     */
    public synchronized void setLayerPosition(String layerName, int index) {
        DrawingLayer l = getLayer(layerName);
        if (l != null) {
            removeLayer(l);
            addLayerTo(l, index);
        }
    }

    /**
     * Adds a layer with the given name to the given position.
     * 
     * @param layerName
     *            The name of the new layer.
     * @param index
     *            The position to add the layer to.
     * @return True if the layer was added successfully.
     */
    public synchronized boolean addLayerTo(String layerName, int index) {
        boolean added = false;
        if (getLayer(layerName) == null) {
            layerList.add((index < layerList.size()) ? index : layerList.size(), new DrawingLayer(layerName, this));
            added = true;
        }
        return added;
    }

    /**
     * Adds the given layer to the given position.
     * 
     * @param l
     *            The layer to be added.
     * @param index
     *            The position to add the layer to.
     * @return True if the layer was added successfully.
     */
    private synchronized boolean addLayerTo(DrawingLayer l, int index) {
        boolean added = false;
        if (getLayer(l.getName()) == null) {
            layerList.add((index < layerList.size()) ? index : layerList.size(), l);
            added = true;
        }
        return added;
    }

    /**
     * Removes the layer with the given name.
     * 
     * @param layerName
     *            The name of the layer to be removed.
     * @return True if the layer was removed successfully.
     */
    public synchronized boolean removeLayer(String layerName) {
        boolean ret = false;
        DrawingLayer l = getLayer(layerName);
        if (l != null) {
            ret = removeLayer(l);
        }
        return ret;
    }

    /**
     * Removes the given layer.
     * 
     * @param layer
     *            The layer to be removed.
     * @return True if the layer was removed successfully.
     */
    public boolean removeLayer(DrawingLayer layer) {
        boolean ret = false;

        if (layerList.size() > 1) {
            layerList.remove(layer);
            ret = true;
        }

        return ret;
    }

    /**
     * Returns the layer with the given name.
     * 
     * @param layerName
     *            The name of the layer to be returned.
     * @return The layer with the given name or null if not found.
     */
    public DrawingLayer getLayer(String layerName) {
        DrawingLayer l = null;
        Iterator<DrawingLayer> it = layerList.iterator();
        while (it.hasNext()) {
            DrawingLayer layer = it.next();
            if (layer.getName().equals(layerName)) {
                l = layer;
                break;
            }
        }
        return l;
    }

    /**
     * @return The drawing layers.
     */
    public Collection<DrawingLayer> getLayers() {
        return layerList;
    }

    /**
     * @return The color-index of the drawing's background.
     */
    public short getColorBackground() {
        return colorBackground;
    }

    /**
     * @param l
     *            The color index to be set for the drawing's background.
     */
    public void setColorBackground(short l) {
        colorBackground = l;
    }

    /**
     * Initializes the quad-tree structures for all layers and their elements.
     */
    public void initLayerQuadTrees() {
        Iterator<DrawingLayer> it = layerList.iterator();
        while (it.hasNext()) {
            DrawingLayer l = it.next();
            l.initQuadTree();
        }
    }

    /**
     * Searches all elements within the given bounds.
     * 
     * @param bounds
     *            The bounds to search the matching elements for.
     * @param visibleOnly
     *            True if only visible layers are to be searched.
     * @return The elements found.
     */
    public Collection<ElemBase> getElementsByBounds(BoundsUserSpace bounds, boolean visibleOnly) {
        LinkedList<ElemBase> list = new LinkedList<ElemBase>();
        Iterator<DrawingLayer> it = layerList.iterator();
        while (it.hasNext()) {
            DrawingLayer l = it.next();
            if (!visibleOnly || !l.isFiltered()) {
                list.addAll(l.getElementsByBounds(bounds));
            }
        }
        return list;
    }

    /**
     * Traverses the given drawing-layer.
     * 
     * @param traverser
     *            The traverser to be used.
     * @param layer
     *            The layer to be traversed.
     */
    public void traverse(ElementTraverser traverser, DrawingLayer layer) {
        short state = ElementTraverser.CONTINUE;
        ElemBase elem = null;
        LinkedList<ElemBase> elems = null;
        ListIterator<ElemBase> iterator = null;

        if (traverser.init()) {
            if (layer == null) {
                elems = new LinkedList<ElemBase>();
                Iterator<DrawingLayer> it = layerList.iterator();
                while (it.hasNext()) {
                    DrawingLayer l = it.next();
                    elems.addAll(l.getElems());
                }

            } else {
                elems = layer.getElems();
            }

            if (traverser.isDirectionFirstToLast()) {
                iterator = elems.listIterator();
                while (iterator.hasNext() && (state == ElementTraverser.CONTINUE)) {
                    elem = iterator.next();
                    state = elem.traverse(traverser);
                }
            } else {
                iterator = elems.listIterator(elems.size());
                while (iterator.hasPrevious() && (state == ElementTraverser.CONTINUE)) {
                    elem = iterator.previous();
                    state = elem.traverse(traverser);
                }
            }
            traverser.close();
        }
    }

}
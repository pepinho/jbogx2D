//
// Copyright (c) 2007 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: StopTimer.java
// Created: 09.06.2007 - 00:05:14
//
package de.jbo.jbogx2d.base.util;

/**
 * Utility class for timing of operations.
 * 
 * @author Josef Baro (jbo)
 * @version 09.06.2007 jbo - created
 */
public class StopTimer {
    /** Start-time of this instance. */
    private long startTime = 0;

    /** Stop-time of this instance. */
    private long stopTime = 0;

    /** Split-time of this instance. */
    private long splitTime = 0;

    /** Counter of the last split-time. */
    private int splitCounter = 0;

    /**
     * Creates a new instance.
     */
    public StopTimer() {
        super();
    }

    /**
     * Starts a new timing.
     */
    public void start() {
        stopTime = 0;
        splitCounter = 0;
        startTime = System.currentTimeMillis();
        splitTime = startTime;
    }

    /**
     * Update split-time and print the given message.
     * 
     * @param message
     *            Message to be printed to console.
     */
    public void split(String message) {
        stopTime = System.currentTimeMillis();
        splitCounter++;
        System.out.println("StopTimer - split (" + splitCounter + ") - " + message + " -> " + (stopTime - splitTime) + " ms");
        splitTime = stopTime;
    }

    /**
     * Stopps current timing and print the given message.
     * 
     * @param message
     *            Message to be printed to console.
     */
    public void stop(String message) {
        stopTime = System.currentTimeMillis();
        System.out.println("StopTimer - start -> stop - " + message + " -> " + (stopTime - startTime) + " ms");
    }
}

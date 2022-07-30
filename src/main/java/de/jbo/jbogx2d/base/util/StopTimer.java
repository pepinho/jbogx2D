//
// Copyright (c) 2007 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: StopTimer.java
// Created: 09.06.2007 - 00:05:14
//
package de.jbo.jbogx2d.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for timing of operations.
 * 
 * @author Josef Baro (jbo)
 * @version 09.06.2007 jbo - created
 */
public class StopTimer {
    private static final Logger LOGGER = LoggerFactory.getLogger(StopTimer.class);

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
        setStopTime(0);
        splitCounter = 0;
        setStartTime(System.currentTimeMillis());
        setSplitTime(getStartTime());
    }

    /**
     * Update split-time and print the given message.
     * 
     * @param message
     *            Message to be printed to console.
     */
    public void split(String message) {
        setStopTime(System.currentTimeMillis());
        splitCounter++;
        LOGGER.trace("StopTimer - split ({}) - {} -> {} ms", splitCounter, message, (getStopTime() - getSplitTime()));
        setSplitTime(getStopTime());
    }

    /**
     * Stopps current timing and print the given message.
     * 
     * @param message
     *            Message to be printed to console.
     */
    public void stop(String message) {
        setStopTime(System.currentTimeMillis());
        LOGGER.trace("StopTimer - start -> stop - {} -> {} ms", message, (getStopTime() - getStartTime()));
    }

    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * @param start
     *            the startTime to set
     */
    public void setStartTime(long start) {
        this.startTime = start;
    }

    /**
     * @return the stopTime
     */
    public long getStopTime() {
        return stopTime;
    }

    /**
     * @param stop
     *            the stopTime to set
     */
    public void setStopTime(long stop) {
        this.stopTime = stop;
    }

    /**
     * @return the splitTime
     */
    public long getSplitTime() {
        return splitTime;
    }

    /**
     * @param split
     *            the splitTime to set
     */
    public void setSplitTime(long split) {
        this.splitTime = split;
    }
}

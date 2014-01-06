//
// Copyright (c) 2013 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    StopTimerTest.java
// Created: 25.12.2013 - 09:56:18
//
package de.jbo.jbogx2d.test.junit.base.util;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.BeforeClass;
import org.junit.Test;

import de.jbo.jbogx2d.base.util.StopTimer;

/**
 * @author Josef Baro (jbo)
 * @version 25.12.2013 jbo - created
 */
public class StopTimerTest {

    /** The timer instance. */
    private static StopTimer timer = null;

    /** The test-stream. */
    private static TestStream stream = null;

    /**
     * @throws java.lang.Exception
     *             Possible exception.
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        timer = new StopTimer();
        stream = new TestStream();
        timer.setStream(stream);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.StopTimer#split(java.lang.String)}.
     */
    @Test
    public void testSplit() {
        timer.start();
        timer.split("Test split");
        long split1 = timer.getSplitTime();
        long stop = timer.getStopTime();
        String messageExpected = "StopTimer - split (1) - Test split -> " + (stop - split1) + " ms";
        assertEquals(messageExpected, stream.getOutput());
        timer.split("Test split 2");
        stop = timer.getStopTime();
        messageExpected = "StopTimer - split (2) - Test split 2 -> " + (stop - split1) + " ms";
        assertEquals(messageExpected, stream.getOutput());
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.util.StopTimer#stop(java.lang.String)}.
     */
    @Test
    public void testStop() {
        timer.start();
        long start = timer.getStartTime();
        timer.split("Test split");
        long split = timer.getSplitTime();
        long stop = timer.getStopTime();
        String messageExpected = "StopTimer - split (1) - Test split -> " + (stop - split) + " ms";
        assertEquals(messageExpected, stream.getOutput());
        timer.stop("Stopped");
        stop = timer.getStopTime();
        messageExpected = "StopTimer - start -> stop - Stopped -> " + (stop - start) + " ms";
        assertEquals(messageExpected, stream.getOutput());
    }

    /**
     * Test stream to check timer-outputs.
     * 
     * @author Josef Baro (jbo)
     * @version 25.12.2013 jbo - created
     */
    private static final class TestStream extends PrintStream {
        /** Holding outputs. */
        private StringBuilder output = new StringBuilder();

        /**
         * Creates new instance.
         */
        public TestStream() {
            super(new ByteArrayOutputStream());
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.io.PrintStream#println(java.lang.String)
         */
        @Override
        public void println(String x) {
            output.setLength(0);
            super.println(x);
            output.append(x);
        }

        /**
         * 
         * @return The output.
         */
        public String getOutput() {
            return output.toString();
        }
    }

}

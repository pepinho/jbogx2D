//
// Copyright (c) 2013 by jbo - Josef Baro
// 
// Project: jbogx2d.base
// File:    VersionTest.java
// Created: 03.11.2013 - 09:12:00
//
package de.jbo.jbogx2d.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import de.jbo.jbogx2d.base.util.StringUtils;

/**
 * @author Josef Baro (jbo)
 * @version 03.11.2013 jbo - created
 */
public class VersionTest {

    /** The build year long. */
    private static final int BUILD_YEAR1 = 1976;

    /** The build year short. */
    private static final int BUILD_YEAR2 = 896;

    /** The build month short. */
    private static final int BUILD_MONTH1 = Calendar.APRIL;

    /** The build month long. */
    private static final int BUILD_MONTH2 = Calendar.DECEMBER;

    /** The build day long. */
    private static final int BUILD_DAY1 = 24;

    /** The build day short. */
    private static final int BUILD_DAY2 = 5;

    /** The build hour short. */
    private static final int BUILD_HOUR1 = 4;

    /** The build hour short. */
    private static final int BUILD_HOUR2 = 11;

    /** The build minutes long. */
    private static final int BUILD_MINUTES1 = 55;

    /** The build minutes short. */
    private static final int BUILD_MINUTES2 = 7;

    /** The build seconds long. */
    private static final int BUILD_SECONDS1 = 24;

    /** The build seconds long. */
    private static final int BUILD_SECONDS2 = 5;

    /** The build main version. */
    private static final int VERSION_MAJOR = 2;

    /** The build minor version. */
    private static final int VERSION_MINOR = 3;

    /** The build bugfix version. */
    private static final int VERSION_BUGFIX = 4;

    /** The build date 1. */
    private Calendar buildDate1 = null;

    /** The build date 2. */
    private Calendar buildDate2 = null;

    /** The test version 1. */
    private Version versionDate1 = null;

    /** The test version 2. */
    private Version versionDate2 = null;

    /** The version string 1. */
    private String versionString1 = null;

    /** The version string 2. */
    private String versionString2 = null;

    /**
     * Sets up necessary test-data.
     */
    @Before
    public void setUp() {
        setUpVersion1();
        setUpVersion2();

    }

    /**
     * Setting-up version info 1.
     */
    private void setUpVersion1() {
        buildDate1 = new GregorianCalendar(BUILD_YEAR1, BUILD_MONTH1, BUILD_DAY1, BUILD_HOUR1, BUILD_MINUTES1, BUILD_SECONDS1);
        versionDate1 = new Version(VERSION_MAJOR, VERSION_MINOR, VERSION_BUGFIX, buildDate1);
        StringBuilder buf = new StringBuilder();
        fillVersions(buf);
        fillBuildDate(buf, BUILD_YEAR1, BUILD_MONTH1 + 1, BUILD_DAY1, BUILD_HOUR1, BUILD_MINUTES1, BUILD_SECONDS1);
        versionString1 = buf.toString();
    }

    /**
     * Setting-up version info 2.
     */
    private void setUpVersion2() {
        buildDate2 = new GregorianCalendar(BUILD_YEAR2, BUILD_MONTH2, BUILD_DAY2, BUILD_HOUR2, BUILD_MINUTES2, BUILD_SECONDS2);
        versionDate2 = new Version(VERSION_MAJOR, VERSION_MINOR, VERSION_BUGFIX, buildDate2);
        StringBuilder buf = new StringBuilder();
        fillVersions(buf);
        fillBuildDate(buf, BUILD_YEAR2, BUILD_MONTH2 + 1, BUILD_DAY2, BUILD_HOUR2, BUILD_MINUTES2, BUILD_SECONDS2);
        versionString2 = buf.toString();
    }

    /**
     * @param buf
     *            The buffer to be filled with the version numbers.
     */
    private void fillVersions(StringBuilder buf) {
        buf.append(VERSION_MAJOR);
        buf.append('.');
        buf.append(VERSION_MINOR);
        buf.append('.');
        buf.append(VERSION_BUGFIX);
    }

    /**
     * 
     * @param buf
     *            The buffer to be filled.
     * @param year
     *            The year.
     * @param month
     *            The month.
     * @param day
     *            The day.
     * @param hours
     *            The hours.
     * @param minutes
     *            The minutes.
     * @param seconds
     *            The seconds.
     */
    private void fillBuildDate(StringBuilder buf, int year, int month, int day, int hours, int minutes, int seconds) {
        final int length2 = 2;
        final int length4 = 4;
        buf.append(' ');
        buf.append('b');
        StringUtils.fillWithLeadingZeros(year, length4, buf);
        StringUtils.fillWithLeadingZeros(month, length2, buf);
        StringUtils.fillWithLeadingZeros(day, length2, buf);
        StringUtils.fillWithLeadingZeros(hours, length2, buf);
        StringUtils.fillWithLeadingZeros(minutes, length2, buf);
        StringUtils.fillWithLeadingZeros(seconds, length2, buf);
    }

    /**
     * Test method for
     * {@link de.jbo.jbogx2d.base.Version#Version(int, int, int, java.util.Calendar)}
     * .
     */
    @Test
    public void testVersion() {
        assertNotNull(versionDate1);
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.Version#getVersionMajor()}.
     */
    @Test
    public void testGetVersionMajor() {
        assertEquals("Major version doesn't match.", VERSION_MAJOR, versionDate1.getVersionMajor());
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.Version#getVersionMinor()}.
     */
    @Test
    public void testGetVersionMinor() {
        assertEquals("Minor version doesn't match.", VERSION_MINOR, versionDate1.getVersionMinor());
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.Version#getVersionBugfix()}.
     */
    @Test
    public void testGetVersionBugfix() {
        assertEquals("Bugfix version doesn't match.", VERSION_BUGFIX, versionDate1.getVersionBugfix());
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.Version#getVersionBuildDate()}
     * .
     */
    @Test
    public void testGetVersionBuildDate() {
        assertEquals("Build date doesn't match.", buildDate1, versionDate1.getVersionBuildDate());
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.Version#getVersionString()}.
     */
    @Test
    public void testGetVersionString() {
        assertEquals("The version string doesn't match.", versionString1, versionDate1.getVersionString());
        assertEquals("The version string doesn't match.", versionString2, versionDate2.getVersionString());
    }

    /**
     * Test method for {@link de.jbo.jbogx2d.base.Version#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("The version string doesn't match.", versionString1, versionDate1.toString());
        assertEquals("The version string doesn't match.", versionString2, versionDate2.toString());
    }

    /**
     * Test method for {@link Version#getVersionFromBuild(Class)}.
     */
    @Test
    public void testGetVersionFromBuildClass() {
        Version version = Version.getVersionFromBuild(VersionTest.class);
        assertNotNull(version);
        assertEquals(versionDate1, version);
    }

    /**
     * Test method for {@link Version#equals()}.
     */
    @Test
    public void testEquals() {
        Version v = new Version("5.5.6", "b2000100523010101");
        assertNotEquals(v, versionDate1);
        assertNotEquals(v, null);
    }

    /**
     * Test method for {@link Version#hashCode()}.
     */
    @Test
    public void testHashCode() {
        Version v = new Version("5.5.6", "b2000100523010101");
        Version v2 = new Version("5.5.6", "b2000100523010101");
        assertEquals(v.hashCode(), v2.hashCode());
    }

}

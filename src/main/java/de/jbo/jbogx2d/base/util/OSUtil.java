//
// Copyright (c) 2022 by jbo - Josef Baro
// 
// Project: jbogx2d-base
// File:    OSUtil.java
// Created: 09.07.2022 - 10:09:41
//
package de.jbo.jbogx2d.base.util;

public class OSUtil {
    private static final String WINDOWS = "Windows";
    private static final String OS = System.getProperty("os.name");
    
    private OSUtil() {
        // ntbd
    }
    
    public static boolean isWindows() {
        return OS.contains(WINDOWS);
    }
}

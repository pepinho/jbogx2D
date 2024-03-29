//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: TestApplication.java
// Created: 29.02.2004 - 17:28:41
//

package de.jbo.jbogx2d.ui.test;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import de.jbo.jbogx2d.base.Jbogx2D;

/**
 * Impelements a test-application.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 29.02.2004: jbo created <br>
 */
public final class TestApplication {
    /**
     * Height of the frame.
     */
    private static final int HEIGHT = 600;

    /**
     * Width of the frame.
     */
    private static final int WIDTH = 800;

    /**
     * Creates a new instance.
     */
    private TestApplication() {

    }

    /**
     * Application's main-method.
     * 
     * @param args
     *            Command-line arguments.
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
                e1.printStackTrace();
            }  

            Jbogx2D.init();

            JFrame frame = new JFrame("jbogx2D Version " + Jbogx2D.getVersion());
            final JGraphicsPanel panel = new JGraphicsPanel();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel buttonsPanel = new JPanel();
            frame.getContentPane().add(buttonsPanel, BorderLayout.NORTH);

            JButton buttonZoomIn = new JButton("Zoom +");
            buttonZoomIn.addActionListener(evt -> panel.zoomPlus());
            buttonsPanel.add(buttonZoomIn);
            JButton buttonZoomOut = new JButton("Zoom -");
            buttonZoomOut.addActionListener(evt -> panel.zoomMinus());
            buttonsPanel.add(buttonZoomOut);

            JButton buttonZoomOriginal = new JButton("Zoom 1:1");
            buttonZoomOriginal.addActionListener(evt -> panel.zoomOriginal());
            buttonsPanel.add(buttonZoomOriginal);

            final JCheckBox checkAntiAliasing = new JCheckBox("anti-alias");
            checkAntiAliasing.addItemListener(e -> panel.setAntiAliasing(checkAntiAliasing.isSelected()));
            buttonsPanel.add(checkAntiAliasing);

            frame.getContentPane().add(panel, BorderLayout.CENTER);

            frame.setSize(WIDTH, HEIGHT);
            frame.setVisible(true);
        });

    }
}
//
// Copyright (c) 2004 by jbo - Josef Baro
// 
// Project: jbogx2D
// File: TestApplication.java
// Created: 29.02.2004 - 17:28:41
//

package de.jbo.jbogx2d.test.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.jbo.jbogx2d.base.Jbogx2D;

/**
 * Impelements a test-application.
 * 
 * @author Josef Baro (jbo) <br>
 * @version 1.0 29.02.2004: jbo created <br>
 */
public final class TestApplication {
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
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();
                }

                Jbogx2D.init();

                JFrame frame = new JFrame("jbogx2D Version " + Jbogx2D.getVersion());
                final JGraphicsPanel panel = new JGraphicsPanel();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel buttonsPanel = new JPanel();
                frame.getContentPane().add(buttonsPanel, BorderLayout.NORTH);

                JButton buttonZoomIn = new JButton("Zoom +");
                buttonZoomIn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        panel.zoomPlus();
                    }
                });
                buttonsPanel.add(buttonZoomIn);
                JButton buttonZoomOut = new JButton("Zoom -");
                buttonZoomOut.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        panel.zoomMinus();
                    }
                });
                buttonsPanel.add(buttonZoomOut);

                JButton buttonZoomOriginal = new JButton("Zoom 1:1");
                buttonZoomOriginal.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        panel.zoomOriginal();
                    }
                });
                buttonsPanel.add(buttonZoomOriginal);

                final JCheckBox checkAntiAliasing = new JCheckBox("anti-alias");
                checkAntiAliasing.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        panel.setAntiAliasing(checkAntiAliasing.isSelected());
                    }
                });
                buttonsPanel.add(checkAntiAliasing);

                frame.getContentPane().add(panel, BorderLayout.CENTER);

                frame.setSize(800, 600);
                frame.setVisible(true);
            }
        });

    }
}
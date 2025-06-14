package org.yrs512.respiratory.gui;

import org.yrs512.respiratory.gui.global.RespiratoryManagementSystem;

import javax.swing.*;

/**
 * 启动类
 *
 * @author <a href="mailto:harvey.blocks@outlook.com">Harvey Blocks</a>
 * @version 1.0
 * @date 2025-06-13 23:22
 */
public class RespiratoryApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RespiratoryManagementSystem app = new RespiratoryManagementSystem();
            app.setVisible(true);
        });
    }
}

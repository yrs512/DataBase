// LoginPanelHandler.java（工具类形式）
package org.example.interFace;

import org.example.AllergyManagementSystem;
import org.example.panel.LoginPanelFactory;

import javax.swing.JPanel;
import java.awt.*;

public class LoginPanelHandler {

    public static void showLoginPanel(AllergyManagementSystem frame) {
        JPanel loginPanel = LoginPanelFactory.createLoginPanel(frame);

        // 替换主内容面板的内容
        JPanel mainPanel = (JPanel) frame.getContentPane();
        Component centerComponent = ((BorderLayout) mainPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        mainPanel.remove(centerComponent);
        mainPanel.add(loginPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}

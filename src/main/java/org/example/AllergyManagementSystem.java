package org.example;

import org.example.healthcare.BalanceQuery;
import org.example.healthcare.Pay;
import org.example.interFace.BackgroundPanel;
import org.example.interFace.LoginPanelHandler;
import org.example.interFace.UIComponentInitializer;
import org.example.login.LoginForm;
import org.example.login.UserHandler;
import org.example.panel.HealthcareBalancePanelFactory;
import org.example.panel.ProfilePanelFactory;
import org.harvey.respiratory.TestServerHandlerRegister;
import org.harvey.respiratory.handler.UserSecurityHandler;
import org.example.interFace.ButtonFactory;
import org.example.panel.LoginPanelFactory;

import javax.swing.*;
import java.awt.*;

public class AllergyManagementSystem extends JFrame {
    private JTabbedPane tabbedPane;
    private UserHandler userHandler;
    private Image backgroundImage;
    private boolean hasLogin;

    public AllergyManagementSystem() {
        setTitle("过敏性疾病管理系统");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 居中显示

        // 加载背景图片 (图片放在项目的resources文件夹中)
        try {
            backgroundImage = new ImageIcon(getClass().getResource("/background.jpg")).getImage();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "背景图片加载失败: " + e.getMessage());
            backgroundImage = null;
        }

        userHandler = new UserHandler();
        UIComponentInitializer.initComponents(this);
    }
    private final static  TestServerHandlerRegister REGISTER = new TestServerHandlerRegister();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AllergyManagementSystem app = new AllergyManagementSystem();
            app.setVisible(true);
        });
    }
}
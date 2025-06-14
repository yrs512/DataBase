package org.example;

import org.example.interFace.BackgroundPanel;
import org.example.interFace.LoginPanelHandler;
import org.example.interFace.UIComponentInitializer;
import org.example.login.LoginForm;
import org.example.login.UserHandler;
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

    // 新增的显示登录面板方法
    LoginPanelFactory loginPanelFactory = new LoginPanelFactory();

    // 创建带统一边框的面板
    private JPanel createStyledPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 统一内边距
        panel.setOpaque(false);
        return panel;
    }
    private final static  TestServerHandlerRegister REGISTER = new TestServerHandlerRegister();

    JPanel loginPanel = LoginPanelFactory.createLoginPanel(this);
    ProfilePanelFactory profilePanelFactory = new ProfilePanelFactory();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AllergyManagementSystem app = new AllergyManagementSystem();
            app.setVisible(true);
        });
    }
}
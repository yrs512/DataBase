package org.yrs512.respiratory.gui.global;

import org.harvey.respiratory.client.ServerHandlerRegister;
import org.harvey.respiratory.client.ServerHandlerRegisterFactory;
import org.yrs512.respiratory.gui.page.LoginPanelBuilder;
import org.yrs512.respiratory.gui.page.ProfilePanelBuilder;

import javax.swing.*;
import java.awt.*;

/**
 * 主类，继承自 JFrame
 *
 * @author <a href="mailto:harvey.blocks@outlook.com">Harvey Blocks</a>
 * @version 1.0
 * @date 2025-06-13 23:22
 */
public class RespiratoryManagementSystem extends JFrame {
    private boolean hasLogin;
    private ButtonPainter buttonPainter;
    Image backgroundImage;

    private final static ServerHandlerRegister REGISTER = ServerHandlerRegisterFactory.TEST;

    public RespiratoryManagementSystem() {
        setTitle("过敏性疾病管理系统");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 居中显示

        loadBackgroundImage();
        buttonPainter = new ButtonPainter();
        initComponents();
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = new ImageIcon(getClass().getResource("/background.jpg")).getImage();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "背景图片加载失败: " + e.getMessage());
            backgroundImage = null;
        }
    }

    // 初始化组件
    private void initComponents() {
        JPanel mainPanel = new PanelCreator(buttonPainter, this).createMainPanel();
        setContentPane(mainPanel);
    }

    // 显示登录面板
    public void showLoginPanel() {
        JPanel loginPanel = new LoginPanelBuilder(buttonPainter, this).createLoginPanel();
        replaceMainContentPanel(loginPanel);
    }

    // 显示个人信息面板
    public void showProfilePanel() {
        JPanel profilePanel = new ProfilePanelBuilder(buttonPainter, this).createProfilePanel();
        replaceMainContentPanel(profilePanel);
    }

    // 返回主面板
    public void returnToMainPanel() {
        JPanel mainPanel = (JPanel) getContentPane();
        Component centerComponent = ((BorderLayout) mainPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        mainPanel.remove(centerComponent);

        JPanel welcomePanel = createWelcomePanel();
        mainPanel.add(welcomePanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createWelcomePanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setOpaque(false);
        JLabel welcomeLabel = new JLabel("欢迎使用过敏性疾病管理系统", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("微软雅黑", Font.BOLD, 48));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
        return welcomePanel;
    }

    private void replaceMainContentPanel(JPanel newPanel) {
        JPanel mainPanel = (JPanel) getContentPane();
        Component centerComponent = ((BorderLayout) mainPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        mainPanel.remove(centerComponent);
        mainPanel.add(newPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}




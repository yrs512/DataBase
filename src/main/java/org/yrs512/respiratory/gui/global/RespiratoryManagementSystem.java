package org.yrs512.respiratory.gui.global;

import lombok.Getter;
import org.yrs512.respiratory.gui.page.LoginPanelBuilder;
import org.yrs512.respiratory.gui.page.ProfilePanelBuilder;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * 主类，继承自 JFrame
 *
 * @author <a href="mailto:harvey.blocks@outlook.com">Harvey Blocks</a>
 * @version 1.0
 * @date 2025-06-13 23:22
 */
public class RespiratoryManagementSystem extends JFrame {
    private static final String BACKGROUND_JPG = "/background.jpg";
    private final ButtonPainter buttonPainter;
    @Getter
    private  Image backgroundImage;

    public RespiratoryManagementSystem() {
        super.setTitle("过敏性疾病管理系统");
        super.setSize(1200, 800);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null); // 居中显示
        loadBackgroundImage(BACKGROUND_JPG);
        buttonPainter = new ButtonPainter();
        initComponents();
    }

    private void loadBackgroundImage(String path) {
        try {
            URL resource = getClass().getResource(path);
            if (resource == null) {
                throw new IllegalStateException("no image");
            }
            backgroundImage = new ImageIcon(resource).getImage();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "背景图片加载失败: " + e.getMessage());
            backgroundImage = null;
        }
    }

    // 初始化组件
    private void initComponents() {
        JPanel mainPanel = new PanelCreator(buttonPainter, this).createMainPanel();
        super.setContentPane(mainPanel);
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

    /**
     * 显示登录面板
     */
    public void showLoginPanel() {
        JPanel loginPanel = new LoginPanelBuilder(buttonPainter, this).createLoginPanel();
        replaceMainContentPanel(loginPanel);
    }

    /**
     * 显示个人信息面板
     */
    public void showProfilePanel() {
        JPanel profilePanel = new ProfilePanelBuilder(buttonPainter, this).createProfilePanel();
        replaceMainContentPanel(profilePanel);
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




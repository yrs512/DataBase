package org.yrs512.respiratory.gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author <a href="mailto:harvey.blocks@outlook.com">Harvey Blocks</a>
 * @version 1.0
 * @date 2025-06-11 00:14
 */ // 面板创建器类
public class PanelCreator {
    private ButtonPainter buttonPainter;
    private RespiratoryManagementSystem system;

    public PanelCreator(ButtonPainter buttonPainter, RespiratoryManagementSystem system) {
        this.buttonPainter = buttonPainter;
        this.system = system;
    }

    public JPanel createMainPanel() {
        BackgroundPanel backgroundPanel = createBackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = createTabbedPane();
        JPanel buttonPanel = createButtonPanel();
        JPanel mainContentPanel = createMainContentPanel();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(mainContentPanel, BorderLayout.CENTER);

        setTabbedPaneStyle(tabbedPane);
        return mainPanel;
    }

    private BackgroundPanel createBackgroundPanel() {
        BackgroundPanel panel = new BackgroundPanel();
        panel.setBackground(new Color(70, 130, 180));
        panel.setOpaque(true);
        return panel;
    }

    private JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false);
        return tabbedPane;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(buttonPainter.initButton("用户登录", e -> system.showLoginPanel()));
        buttonPanel.add(buttonPainter.initButton("用户信息", e -> system.showProfilePanel()));
        return buttonPanel;
    }

    private JPanel createMainContentPanel() {
        JPanel mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.setOpaque(false);

        JLabel welcomeLabel = createWelcomeLabel();
        mainContentPanel.add(welcomeLabel, BorderLayout.CENTER);

        return mainContentPanel;
    }

    private JLabel createWelcomeLabel() {
        JLabel welcomeLabel = new JLabel("欢迎使用过敏性疾病管理系统", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("微软雅黑", Font.BOLD, 48));
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        return welcomeLabel;
    }

    private void setTabbedPaneStyle(JTabbedPane tabbedPane) {
        tabbedPane.setFont(new Font("微软雅黑", Font.BOLD, 18));
        tabbedPane.setForeground(Color.WHITE);
    }
}

package org.example.interFace;

import org.example.AllergyManagementSystem;
import org.example.interFace.LoginPanelHandler;
import org.example.CustomDialog;
import org.example.login.LoginForm;
import org.example.panel.HealthcarePanelHandler;

import org.harvey.respiratory.handler.UserSecurityHandler;

import javax.swing.*;
import java.awt.*;

import static org.example.panel.ProfilePanelFactory.showProfilePanel;

public class UIComponentInitializer {

    public static void initComponents(AllergyManagementSystem frame) {
        // 使用纯色蓝色背景面板
        BackgroundPanel backgroundPanel = new BackgroundPanel(); // 不使用图片
        backgroundPanel.setBackground(new Color(70, 130, 180)); // 深蓝色
        backgroundPanel.setOpaque(true); // 设置为不透明才能显示颜色
        backgroundPanel.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false); // 使标签页透明

        // 创建一个带透明背景的面板来放置按钮
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.setOpaque(false);

        // 添加登录按钮
        JButton loginButton = ButtonFactory.createStyledButton("用户登录");
        loginButton.setPreferredSize(new Dimension(120, 40));
        loginButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        loginButton.addActionListener(e -> LoginPanelHandler.showLoginPanel(frame));
        buttonPanel.add(loginButton);

        // 添加个人信息按钮
        JButton profileButton = ButtonFactory.createStyledButton("个人信息");
        profileButton.setPreferredSize(new Dimension(120, 40));
        profileButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        profileButton.addActionListener(e -> showProfilePanel(frame));
        buttonPanel.add(profileButton);

        // 添加医保按钮
        JButton healthcareButton = ButtonFactory.createStyledButton("医保");
        healthcareButton.setPreferredSize(new Dimension(120, 40));
        healthcareButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        healthcareButton.addActionListener(e -> HealthcarePanelHandler.showHealthcarePanel(frame));
        buttonPanel.add(healthcareButton);

        // 主内容面板
        JPanel mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.setOpaque(false);

        // 欢迎标签
        JLabel welcomeLabel = new JLabel("欢迎使用过敏性疾病管理系统", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("微软雅黑", Font.BOLD, 48));
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        mainContentPanel.add(welcomeLabel, BorderLayout.CENTER);

        // 更新主面板布局
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(mainContentPanel, BorderLayout.CENTER);
        frame.setContentPane(mainPanel); // 更新内容面板

        // 设置标签页字体和颜色
        tabbedPane.setFont(new Font("微软雅黑", Font.BOLD, 18));
        tabbedPane.setForeground(Color.WHITE);
    }

}

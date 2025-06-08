package org.example.interFace;

import javax.swing.*;
import java.awt.*;

public class ComponentInitializer {

    private final AllergyManagementSystem frame;

    public ComponentInitializer(AllergyManagementSystem frame) {
        this.frame = frame;
    }

    public void initComponents() {
        // 使用纯色蓝色背景面板
        BackgroundPanel backgroundPanel = new BackgroundPanel(); // 不使用图片
        backgroundPanel.setBackground(new Color(70, 130, 180)); // 深蓝色
        backgroundPanel.setOpaque(true); // 设置为不透明才能显示颜色
        backgroundPanel.setLayout(new BorderLayout());

        frame.tabbedPane = new JTabbedPane();
        frame.tabbedPane.setOpaque(false); // 使标签页透明

        // 创建一个带透明背景的面板来放置按钮
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.setOpaque(false);

        // 添加登录按钮
        JButton loginButton = createStyledButton("用户登录");
        loginButton.setPreferredSize(new Dimension(120, 40));
        loginButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        loginButton.addActionListener(e -> frame.showLoginPanel());
        buttonPanel.add(loginButton);

        // 添加个人信息按钮
        JButton profileButton = createStyledButton("个人信息");
        profileButton.setPreferredSize(new Dimension(120, 40));
        profileButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        profileButton.addActionListener(e -> frame.showProfilePanel());
        buttonPanel.add(profileButton);

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
        frame.tabbedPane.setFont(new Font("微软雅黑", Font.BOLD, 18));
        frame.tabbedPane.setForeground(Color.WHITE);
    }

    // 将createStyledButton移到这里或保留原位置（视情况而定）
    public static JButton createStyledButton(String text) {
        return AllergyManagementSystem.createStyledButton(text); // 复用静态方法
    }
}

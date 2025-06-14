// LoginPanelFactory.java
package org.example.panel;

import org.example.AllergyManagementSystem;
import org.example.interFace.BackgroundPanel;
import org.example.interFace.ButtonFactory;
import org.example.CustomDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanelFactory {

    /**
     * 创建登录面板（包含密码登录、验证码登录、注册）
     * @param frame 主窗口实例
     * @return 构建好的 JPanel 登录面板
     */
    public static JPanel createLoginPanel(AllergyManagementSystem frame) {
        // 单独加载登录界面背景图（假设放在 resources/background.jpg）
        Image loginBackground = new ImageIcon(frame.getClass().getResource("/background.jpg")).getImage();
        BackgroundPanel panel = new BackgroundPanel(loginBackground);
        panel.setLayout(new CardLayout());

        // 密码登录面板
        JPanel passwordLoginPanel = createStyledPanel(panel, frame);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 标题标签 - 密码登录
        JLabel titleLabel1 = new JLabel("密码登录", SwingConstants.CENTER);
        titleLabel1.setFont(new Font("微软雅黑", Font.BOLD, 48));
        titleLabel1.setForeground(new Color(255, 255, 255));
        titleLabel1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        passwordLoginPanel.add(titleLabel1, gbc);

        // 电话号码输入
        JLabel phoneLabel1 = new JLabel("电话号码:");
        JTextField phoneField1 = new JTextField(20);
        phoneLabel1.setFont(new Font("微软雅黑", Font.BOLD, 18));
        phoneLabel1.setForeground(Color.WHITE);
        phoneLabel1.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        passwordLoginPanel.add(phoneLabel1, gbc);
        gbc.gridx = 1;
        passwordLoginPanel.add(phoneField1, gbc);

        // 密码输入
        JLabel passwordLabel = new JLabel("密码:");
        JPasswordField passwordField = new JPasswordField(20);
        passwordLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 2;
        passwordLoginPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        passwordLoginPanel.add(passwordField, gbc);

        // 登录按钮
        JButton loginButton1 = ButtonFactory.createStyledButton("登录");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        passwordLoginPanel.add(loginButton1, gbc);

        // 添加切换按钮
        JButton toggleButton = ButtonFactory.createStyledButton("切换为验证码登录");
        toggleButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) panel.getLayout();
            cl.show(panel, "code");
        });
        gbc.gridy = 4;
        passwordLoginPanel.add(toggleButton, gbc);

        // 注册按钮（在密码登录页显示）
        JButton registerButton1 = ButtonFactory.createStyledButton("注册");
        gbc.gridy = 5;
        passwordLoginPanel.add(registerButton1, gbc);

        // 验证码登录面板
        JPanel codeLoginPanel = createStyledPanel(panel, frame);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(10, 10, 10, 10);
        gbc2.fill = GridBagConstraints.HORIZONTAL;

        // 标题标签 - 验证码登录
        JLabel titleLabel2 = new JLabel("验证码登录", SwingConstants.CENTER);
        titleLabel2.setFont(new Font("微软雅黑", Font.BOLD, 48));
        titleLabel2.setForeground(new Color(255, 255, 255));
        titleLabel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.gridwidth = 2;
        codeLoginPanel.add(titleLabel2, gbc2);

        // 电话号码输入
        JLabel phoneLabel2 = new JLabel("电话号码:");
        JTextField phoneField2 = new JTextField(20);
        phoneLabel2.setFont(new Font("微软雅黑", Font.BOLD, 18));
        phoneLabel2.setForeground(Color.WHITE);
        phoneLabel2.setBackground(new Color(0, 0, 0, 50));
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 1;
        codeLoginPanel.add(phoneLabel2, gbc2);
        gbc2.gridx = 1;
        codeLoginPanel.add(phoneField2, gbc2);

        // 验证码输入和获取按钮
        JLabel codeLabel = new JLabel("验证码:");
        JTextField codeField = new JTextField(20);
        codeLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        codeLabel.setForeground(Color.WHITE);
        codeLabel.setBackground(new Color(0, 0, 0, 50));
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        gbc2.gridwidth = 1;
        codeLoginPanel.add(codeLabel, gbc2);
        gbc2.gridx = 1;
        codeLoginPanel.add(codeLabel, gbc2);
        JButton getCodeButton = ButtonFactory.createStyledButton("获取验证码");

        // 创建中间面板用于水平排列验证码输入和按钮
        JPanel codeInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        codeInputPanel.setOpaque(false);
        codeInputPanel.add(codeLabel);
        codeInputPanel.add(codeField);
        codeInputPanel.add(getCodeButton);

        gbc2.gridx = 0;
        gbc2.gridy = 2;
        gbc2.gridwidth = 2;
        codeLoginPanel.add(codeInputPanel, gbc2);

        // 登录按钮
        JButton loginButton2 = ButtonFactory.createStyledButton("登录");
        gbc2.gridx = 0;
        gbc2.gridy = 3;
        gbc2.gridwidth = 2;
        gbc2.insets = new Insets(10, 10, 10, 10);
        codeLoginPanel.add(loginButton2, gbc2);

        // 切换按钮
        JButton toggleButton2 = ButtonFactory.createStyledButton("切换为密码登录");
        toggleButton2.addActionListener(e -> {
            CardLayout cl = (CardLayout) panel.getLayout();
            cl.show(panel, "password");
        });
        gbc2.gridy = 4;
        codeLoginPanel.add(toggleButton2, gbc2);

        // 注册按钮
        JButton registerButton2 = ButtonFactory.createStyledButton("注册");
        gbc2.gridy = 5;
        codeLoginPanel.add(registerButton2, gbc2);

        // 添加到主面板card layout中
        panel.add(passwordLoginPanel, "password");
        panel.add(codeLoginPanel, "code");

        // 获取验证码按钮事件处理
        getCodeButton.addActionListener(e -> {
            String phone = phoneField2.getText();
            if (phone == null || phone.trim().isEmpty()) {
                new CustomDialog(frame, "请先输入电话号码!").setVisible(true);
                return;
            }
            if (!phone.matches("\\d{11}")) {
                new CustomDialog(frame, "请输入正确的11位电话号码").setVisible(true);
                return;
            }
        });

        // 密码登录按钮事件处理
        loginButton1.addActionListener(e -> {
            String phone = phoneField1.getText();
            String password = new String(passwordField.getPassword());
            if (phone == null || phone.trim().isEmpty()) {
                new CustomDialog(frame, "请输入电话号码!").setVisible(true);
                return;
            }
            if (!phone.matches("\\d{11}")) {
                new CustomDialog(frame, "请输入正确的11位电话号码").setVisible(true);
                return;
            }
            if (password == null || password.trim().isEmpty()) {
                new CustomDialog(frame, "请输入密码!").setVisible(true);
                return;
            }
            if (!password.matches("^[a-zA-Z0-9_]{4,32}$")) {
                new CustomDialog(frame, "密码格式不正确，必须是4~32位的字母数字下划线").setVisible(true);
                return;
            }

            // 可以添加实际登录逻辑
        });

        // 验证码登录按钮事件处理
        loginButton2.addActionListener(e -> {
            String phone = phoneField2.getText();
            String code = codeField.getText();
            if (phone == null || phone.trim().isEmpty()) {
                new CustomDialog(frame, "请输入电话号码!").setVisible(true);
                return;
            }
            if (!phone.matches("\\d{11}")) {
                new CustomDialog(frame, "请输入正确的11位电话号码").setVisible(true);
                return;
            }
            if (code == null || code.trim().isEmpty()) {
                new CustomDialog(frame, "请输入验证码!").setVisible(true);
                return;
            }
            if (!code.matches("\\d{6}")) {
                new CustomDialog(frame, "请输入正确的6位验证码!").setVisible(true);
                return;
            }
        });

        // 注册页面
        JPanel registerFormPanel = createRegisterPanel(panel, frame);
        panel.add(registerFormPanel, "register");

        // 注册按钮事件绑定
        registerButton1.addActionListener(e -> {
            CardLayout cl = (CardLayout) panel.getLayout();
            cl.show(panel, "register");
        });

        registerButton2.addActionListener(e -> {
            CardLayout cl = (CardLayout) panel.getLayout();
            cl.show(panel, "register");
        });

        // 设置整体布局结构
        JPanel container = new JPanel(new BorderLayout());
        container.add(panel, BorderLayout.CENTER);

        return container;
    }

    private static JPanel createStyledPanel(JComponent parent, AllergyManagementSystem frame) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setOpaque(false);
        return panel;
    }

    private static JPanel createRegisterPanel(JComponent parent, AllergyManagementSystem frame) {
        JPanel registerFormPanel = createStyledPanel(parent, frame);
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.insets = new Insets(10, 10, 10, 10);
        gbc3.fill = GridBagConstraints.HORIZONTAL;

        // 标题
        JLabel titleLabel3 = new JLabel("用户注册", SwingConstants.CENTER);
        titleLabel3.setFont(new Font("微软雅黑", Font.BOLD, 48));
        titleLabel3.setForeground(new Color(255, 255, 255));
        titleLabel3.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        gbc3.gridwidth = 2;
        registerFormPanel.add(titleLabel3, gbc3);

        // 姓名输入
        JLabel nameLabel = new JLabel("姓 名:");
        JTextField nameField = new JTextField(20);
        nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBackground(new Color(0, 0, 0, 50));
        gbc3.gridx = 0;
        gbc3.gridy = 1;
        gbc3.gridwidth = 1;
        registerFormPanel.add(nameLabel, gbc3);
        gbc3.gridx = 1;
        registerFormPanel.add(nameField, gbc3);

        // 电话号码输入
        JLabel phoneLabel3 = new JLabel("电话号码:");
        JTextField phoneField3 = new JTextField(20);
        phoneLabel3.setFont(new Font("微软雅黑", Font.BOLD, 18));
        phoneLabel3.setForeground(Color.WHITE);
        phoneLabel3.setBackground(new Color(0, 0, 0, 50));
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        registerFormPanel.add(phoneLabel3, gbc3);
        gbc3.gridx = 1;
        registerFormPanel.add(phoneField3, gbc3);

        // 密码输入
        JLabel passwordLabel3 = new JLabel("密码:");
        JPasswordField passwordField3 = new JPasswordField(20);
        passwordLabel3.setFont(new Font("微软雅黑", Font.BOLD, 18));
        passwordLabel3.setForeground(Color.WHITE);
        passwordLabel3.setBackground(new Color(0, 0, 0, 50));
        gbc3.gridx = 0;
        gbc3.gridy = 3;
        registerFormPanel.add(passwordLabel3, gbc3);
        gbc3.gridx = 1;
        registerFormPanel.add(passwordField3, gbc3);

        // 验证码输入和获取按钮
        JLabel codeLabel3 = new JLabel("验证码:");
        JTextField codeField3 = new JTextField(20);
        codeLabel3.setFont(new Font("微软雅黑", Font.BOLD, 18));
        codeLabel3.setForeground(Color.WHITE);
        codeLabel3.setBackground(new Color(0, 0, 0, 50));
        JButton getCodeButton3 = ButtonFactory.createStyledButton("获取验证码");

        JPanel codeInputPanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        codeInputPanel3.setOpaque(false);
        codeInputPanel3.add(codeLabel3);
        codeInputPanel3.add(codeField3);
        codeInputPanel3.add(getCodeButton3);

        gbc3.gridx = 0;
        gbc3.gridy = 4;
        gbc3.gridwidth = 2;
        registerFormPanel.add(codeInputPanel3, gbc3);

        // 提交注册按钮
        JButton registerButton3 = ButtonFactory.createStyledButton("提交注册");
        gbc3.gridy = 5;
        registerFormPanel.add(registerButton3, gbc3);

        // 返回按钮
        JButton backButton = ButtonFactory.createStyledButton("返回登录");
        gbc3.gridy = 6;
        registerFormPanel.add(backButton, gbc3);

        // 注册按钮事件处理
        registerButton3.addActionListener(e -> {
            String phone = phoneField3.getText();
            String password = new String(passwordField3.getPassword());
            // 注册逻辑...
        });

        // 返回登录按钮事件
        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) parent.getParent().getLayout();
            cl.show(parent.getParent(), "password");
        });

        return registerFormPanel;
    }
}

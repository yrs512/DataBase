package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllergyManagementSystem extends JFrame {
    private JTabbedPane tabbedPane;
    private UserHandler userHandler;
    private Image backgroundImage;

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
        initComponents();
    }

    private void initComponents() {
        // 使用纯色蓝色背景面板
        BackgroundPanel backgroundPanel = new BackgroundPanel(); // 不使用图片
        backgroundPanel.setBackground(new Color(70, 130, 180)); // 深蓝色
        backgroundPanel.setOpaque(true); // 设置为不透明才能显示颜色
        backgroundPanel.setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false); // 使标签页透明

        // 添加用户登录面板
        JPanel loginPanel = new BackgroundPanel(new Color(0, 0,0, 80)); // 半透明黑色背景
        loginPanel.setLayout(new CardLayout());

        JPanel loginContent = createLoginPanel(); // 获取你原本创建的登录界面内容
        loginPanel.add(loginContent, BorderLayout.CENTER);

        tabbedPane.addTab("用户登录", loginPanel);

        backgroundPanel.add(tabbedPane, BorderLayout.CENTER);
        setContentPane(backgroundPanel); // 设置内容面板为我们的背景面板

        // 设置标签页字体和颜色
        tabbedPane.setFont(new Font("微软雅黑", Font.BOLD, 18));
        tabbedPane.setForeground(Color.WHITE);

        // 可以添加其他功能模块面板
        // tabbedPane.addTab("其他模块", otherPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("微软雅黑", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 123, 255)); // 蓝色背景
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        button.setContentAreaFilled(false); // 关闭默认填充
        button.setOpaque(false); // 使用自定义绘制
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // 自定义绘制圆角按钮
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                AbstractButton b = (AbstractButton) c;
                ButtonModel model = b.getModel();

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = c.getWidth();
                int height = c.getHeight();

                if (model.isArmed()) {
                    g2.setColor(new Color(0, 150, 255)); // 按下时颜色变浅
                } else {
                    g2.setColor(new Color(0, 123, 255)); // 正常状态颜色
                }

                g2.fillRoundRect(0, 0, width - 1, height - 1, 20, 20); // 圆角矩形

                // 绘制文字
                g2.setColor(Color.WHITE);
                g2.setFont(button.getFont());
                FontMetrics fm = g2.getFontMetrics();
                int tw = fm.stringWidth(b.getText());
                int th = fm.getHeight();
                int tx = (width - tw) / 2;
                int ty = (height + th) / 2 - fm.getDescent();
                g2.drawString(b.getText(), tx, ty);

                g2.dispose();
            }
        });

        // 鼠标悬停变色
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 150, 255));
                button.repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 123, 255));
                button.repaint();
            }
        });

        return button;
    }

    // 创建带统一边框的面板
    private JPanel createStyledPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 统一内边距
        panel.setOpaque(false);
        return panel;
    }

    private JPanel createLoginPanel() {
        // 单独加载登录界面背景图（假设放在 resources/login_background.jpg）
        Image loginBackground = new ImageIcon(getClass().getResource("/background.jpg")).getImage();
        BackgroundPanel panel = new BackgroundPanel(loginBackground);
        panel.setLayout(new CardLayout());

        // 密码登录面板
        JPanel passwordLoginPanel = createStyledPanel();
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
        phoneLabel1.setFont(new Font("微软雅黑", Font.BOLD, 18));//字体大小
        phoneLabel1.setForeground(Color.WHITE); // 设置文字颜色为白色
        phoneLabel1.setBackground(new Color(0, 0, 0, 50)); // 可选：半透明背景增强可读性
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        passwordLoginPanel.add(phoneLabel1, gbc);
        gbc.gridx = 1;
        passwordLoginPanel.add(phoneField1, gbc);

        // 密码输入
        JLabel passwordLabel = new JLabel("密码:");
        JPasswordField passwordField = new JPasswordField(20);
        passwordLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));//字体大小
        passwordLabel.setForeground(Color.WHITE); // 设置文字颜色为白色
        passwordLabel.setBackground(new Color(0, 0, 0, 50)); // 可选：半透明背景增强可读性
        gbc.gridx = 0;
        gbc.gridy = 2;
        passwordLoginPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        passwordLoginPanel.add(passwordField, gbc);

        // 登录按钮
        JButton loginButton1 =createStyledButton("登录");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        passwordLoginPanel.add(loginButton1, gbc);

        // 添加切换按钮
        JButton toggleButton =createStyledButton("切换为验证码登录");
        toggleButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) panel.getLayout();
            cl.show(panel, "code");
        });
        gbc.gridy = 4;
        passwordLoginPanel.add(toggleButton, gbc);

        // 注册按钮（在密码登录页显示）
        JButton registerButton1 =createStyledButton("注册");
        gbc.gridy = 5;
        passwordLoginPanel.add(registerButton1, gbc);


        // 结果展示区域
        JTextArea resultArea1 = new JTextArea(6, 30);
        resultArea1.setEditable(false);
        JScrollPane scrollPane1 = new JScrollPane(resultArea1);
        gbc.gridy = 7;
        passwordLoginPanel.add(scrollPane1, gbc);


        // 验证码登录面板
        JPanel codeLoginPanel = createStyledPanel();
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
        phoneLabel2.setFont(new Font("微软雅黑", Font.BOLD, 18));//字体大小
        phoneLabel2.setForeground(Color.WHITE); // 设置文字颜色为白色
        phoneLabel2.setBackground(new Color(0, 0, 0, 50)); // 可选：半透明背景增强可读性
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 1;
        codeLoginPanel.add(phoneLabel2, gbc2);
        gbc2.gridx = 1;
        codeLoginPanel.add(phoneField2, gbc2);

        // 验证码输入和获取按钮
        JLabel codeLabel = new JLabel("验证码:");
        JTextField codeField = new JTextField(20);
        codeLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));//字体大小
        codeLabel.setForeground(Color.WHITE); // 设置文字颜色为白色
        codeLabel.setBackground(new Color(0, 0, 0, 50)); // 可选：半透明背景增强可读性
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        gbc2.gridwidth = 1;
        codeLoginPanel.add(codeLabel, gbc2);
        gbc2.gridx = 1;
        codeLoginPanel.add(codeLabel, gbc2);
        JButton getCodeButton = createStyledButton("获取验证码");

        // 创建一个中间面板用于水平排列验证码输入和按钮
        JPanel codeInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        codeInputPanel.setOpaque(false); // 保持透明以适配背景
        codeInputPanel.add(codeLabel);
        codeInputPanel.add(codeField);
        codeInputPanel.add(getCodeButton);

        // 将组合面板添加到 gbc 布局中
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        gbc2.gridwidth = 2; // 占据两列以居中
        codeLoginPanel.add(codeInputPanel, gbc2);

        // 登录按钮
        JButton loginButton2 = createStyledButton("登录");
        gbc2.gridx = 0;
        gbc2.gridy = 3;
        gbc2.gridwidth = 2;
        gbc2.insets = new Insets(10, 10, 10, 10);
        codeLoginPanel.add(loginButton2, gbc2);

        // 添加切换按钮
        JButton toggleButton2 = createStyledButton("切换为密码登录");
        toggleButton2.addActionListener(e -> {
            CardLayout cl = (CardLayout) panel.getLayout();
            cl.show(panel, "password");
        });
        gbc2.gridy = 4;
        codeLoginPanel.add(toggleButton2, gbc2);

        // 注册按钮
        JButton registerButton2 = createStyledButton("注册");
        gbc2.gridy = 5;
        codeLoginPanel.add(registerButton2, gbc2);


        // 结果展示区域
        JTextArea resultArea2 = new JTextArea(6, 30);
        resultArea2.setEditable(false);
        JScrollPane scrollPane2 = new JScrollPane(resultArea2);
        gbc2.gridy = 7;
        codeLoginPanel.add(scrollPane2, gbc2);

        // 添加到主面板card layout中
        panel.add(passwordLoginPanel, "password");
        panel.add(codeLoginPanel, "code");

        // 设置整体布局结构
        JPanel container = new JPanel(new BorderLayout());
        container.add(panel, BorderLayout.CENTER);


        // 获取验证码按钮事件处理
        getCodeButton.addActionListener(e -> {
            String phone = phoneField2.getText();
            if (phone == null || phone.trim().isEmpty()) {
                resultArea2.setText("请先输入电话号码");
                return;
            }
            // 模拟发送验证码
            JOptionPane.showMessageDialog(panel, "验证码已发送至：" + phone);
        });

        // 密码登录按钮事件处理
        loginButton1.addActionListener(e -> {
            String phone = phoneField1.getText();
            String password = new String(passwordField.getPassword());

            String response = userHandler.login(null, password, phone);
            resultArea1.setText(response);
        });

        // 验证码登录按钮事件处理
        loginButton2.addActionListener(e -> {
            String phone = phoneField2.getText();
            String code = codeField.getText();

            String response = userHandler.login(code, null, phone);
            resultArea2.setText(response);
        });

        // 注册页面（与密码登录界面一致）
        JPanel registerFormPanel = createStyledPanel();
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.insets = new Insets(10, 10, 10, 10);
        gbc3.fill = GridBagConstraints.HORIZONTAL;

        // 标题标签 - 注册
        JLabel titleLabel3 = new JLabel("用户注册", SwingConstants.CENTER);
        titleLabel3.setFont(new Font("微软雅黑", Font.BOLD, 48));
        titleLabel3.setForeground(new Color(255, 255, 255));
        titleLabel3.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        gbc3.gridwidth = 2;
        registerFormPanel.add(titleLabel3, gbc3);

        // 电话号码输入
        JLabel phoneLabel3 = new JLabel("电话号码:");
        JTextField phoneField3 = new JTextField(20);
        phoneLabel3.setFont(new Font("微软雅黑", Font.BOLD, 18));//字体大小
        phoneLabel3.setForeground(Color.WHITE); // 设置文字颜色为白色
        phoneLabel3.setBackground(new Color(0, 0, 0, 50)); // 可选：半透明背景增强可读性
        gbc3.gridx = 0;
        gbc3.gridy = 1;
        gbc3.gridwidth = 1;
        registerFormPanel.add(phoneLabel3, gbc3);
        gbc3.gridx = 1;
        registerFormPanel.add(phoneField3, gbc3);

        // 密码输入
        JLabel passwordLabel3 = new JLabel("密码:");
        JPasswordField passwordField3 = new JPasswordField(20);
        passwordLabel3.setFont(new Font("微软雅黑", Font.BOLD, 18));//字体大小
        passwordLabel3.setForeground(Color.WHITE); // 设置文字颜色为白色
        passwordLabel3.setBackground(new Color(0, 0, 0, 50)); // 可选：半透明背景增强可读性
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        gbc3.gridwidth = 1;
        registerFormPanel.add(passwordLabel3, gbc3);
        gbc3.gridx = 1;
        registerFormPanel.add(passwordField3, gbc3);

        // 验证码输入和获取按钮
        JLabel codeLabel3 = new JLabel("验证码:");
        JTextField codeField3 = new JTextField(20);
        codeLabel3.setFont(new Font("微软雅黑", Font.BOLD, 18));//字体大小
        codeLabel3.setForeground(Color.WHITE); // 设置文字颜色为白色
        codeLabel3.setBackground(new Color(0, 0, 0, 50)); // 可选：半透明背景增强可读性
        JButton getCodeButton3 = createStyledButton("获取验证码");

        // 创建一个中间面板用于水平排列验证码输入和按钮
        JPanel codeInputPanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        codeInputPanel3.setOpaque(false); // 保持透明以适配背景
        codeInputPanel3.add(codeLabel3);
        codeInputPanel3.add(codeField3);
        codeInputPanel3.add(getCodeButton3);

        gbc3.gridx = 0;
        gbc3.gridy = 3;
        gbc3.gridwidth = 2;
        registerFormPanel.add(codeInputPanel3, gbc3);

        // 注册按钮
        JButton registerButton3 = createStyledButton("提交注册");
        gbc3.gridy = 4;
        registerFormPanel.add(registerButton3, gbc3);

        // 返回按钮
        JButton backButton = createStyledButton("返回登录");
        gbc3.gridy = 5;
        registerFormPanel.add(backButton, gbc3);

        // 结果展示区域 - 注册页
        JTextArea resultArea3 = new JTextArea(6, 30); // 高度为6行，宽度为30字符
        resultArea3.setEditable(false); // 不允许编辑
        resultArea3.setOpaque(false); // 设置为透明以适配背景
        resultArea3.setForeground(Color.WHITE); // 白色文字增强可读性
        resultArea3.setBackground(new Color(0, 0, 0, 80)); // 半透明黑色背景
        resultArea3.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 50), 1)); // 淡白边框

        JScrollPane scrollPane3 = new JScrollPane(resultArea3);
        gbc3.gridy = 6; // 放在“返回登录”按钮下方
        registerFormPanel.add(scrollPane3, gbc3);



        // 添加到主面板card layout中
        panel.add(registerFormPanel, "register");


// 注册按钮事件处理
        registerButton3.addActionListener(e -> {
            String phone = phoneField3.getText();
            String password = new String(passwordField3.getPassword());

            String response = userHandler.register(phone, password);
            resultArea3.setText(response); // 将结果输出到文本框
        });
        registerButton2.addActionListener(e -> {
            String phone = phoneField2.getText();
            String password = ""; // 验证码登录时不填密码

            String response = userHandler.register(phone, password);
            resultArea2.setText(response);
        });
        registerButton1.addActionListener(e -> {
            CardLayout cl = (CardLayout) panel.getLayout();
            cl.show(panel, "register");
        });
        registerButton2.addActionListener(e -> {
            CardLayout cl = (CardLayout) panel.getLayout();
            cl.show(panel, "register");
        });
        // 返回登录按钮事件
        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) panel.getLayout();
            cl.show(panel, "password"); // 切换回密码登录页
            // 清空注册页结果展示框
            resultArea3.setText("");
        });


        return container;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AllergyManagementSystem app = new AllergyManagementSystem();
            app.setVisible(true);
        });
    }
}
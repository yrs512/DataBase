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
        // 使用自定义背景的面板作为内容面板
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false); // 使标签页透明

        // 添加用户登录面板
        JPanel loginPanel = createLoginPanel();
        loginPanel.setOpaque(false); // 使面板透明
        tabbedPane.addTab("用户登录", loginPanel);

        // 可以添加其他功能模块面板
        // tabbedPane.addTab("其他模块", otherPanel);

        backgroundPanel.add(tabbedPane, BorderLayout.CENTER);
        setContentPane(backgroundPanel); // 设置内容面板为我们的背景面板

        // 设置标签页字体和颜色
        tabbedPane.setFont(new Font("微软雅黑", Font.BOLD, 16));
        tabbedPane.setForeground(Color.WHITE);
    }


    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 电话号码标签和输入框
        JLabel phoneLabel = new JLabel("电话号码:");
        JTextField phoneField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        // 密码标签和输入框
        JLabel passwordLabel = new JLabel("密码:");
        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // 验证码标签和输入框
        JLabel codeLabel = new JLabel("验证码:");
        JTextField codeField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(codeLabel, gbc);
        gbc.gridx = 1;
        panel.add(codeField, gbc);

        // 登录按钮
        JButton loginButton = new JButton("登录");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        // 结果展示区域
        JTextArea resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        // 登录按钮事件处理
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phone = phoneField.getText();
                String password = new String(passwordField.getPassword());
                String code = codeField.getText();

                // 调用UserHandler的登录方法
                String response = userHandler.login(code, password, phone);

                // 显示结果
                resultArea.setText(response);
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AllergyManagementSystem app = new AllergyManagementSystem();
            app.setVisible(true);
        });
    }
}
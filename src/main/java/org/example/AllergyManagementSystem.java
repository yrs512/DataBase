package org.example;

import org.example.login.LoginForm;
import org.example.login.UserHandler;
import org.example.message.PeopleMessage;

import javax.swing.*;
import java.awt.*;

public class AllergyManagementSystem extends JFrame {
    private JTabbedPane tabbedPane;
    private UserHandler userHandler;
    private Image backgroundImage;
//    public static final HttpClientManager MANAGER = new HttpClientManager();
//    public static final HttpRequestBuilder REQUEST_BUILDER = new HttpRequestBuilder();
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

        // 创建一个带透明背景的面板来放置按钮
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.setOpaque(false);

        // 添加登录按钮
        JButton loginButton = createStyledButton("用户登录");
        loginButton.setPreferredSize(new Dimension(120, 40));
        loginButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        loginButton.addActionListener(e -> showLoginPanel());
        buttonPanel.add(loginButton);

        // 添加个人信息按钮
        JButton profileButton = createStyledButton("个人信息");
        profileButton.setPreferredSize(new Dimension(120, 40));
        profileButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        profileButton.addActionListener(e -> showProfilePanel());
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
        setContentPane(mainPanel); // 更新内容面板

        // 设置标签页字体和颜色
        tabbedPane.setFont(new Font("微软雅黑", Font.BOLD, 18));
        tabbedPane.setForeground(Color.WHITE);
    }

    // 新增的显示登录面板方法
    private void showLoginPanel() {
        // 创建登录面板
        JPanel loginPanel = createLoginPanel();

        // 替换主内容面板的内容
        JPanel mainPanel = (JPanel) getContentPane();
        Component centerComponent = ((BorderLayout) mainPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        mainPanel.remove(centerComponent);
        mainPanel.add(loginPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static JButton createStyledButton(String text) {
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
        // 单独加载登录界面背景图（假设放在 resources/background.jpg）
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
        JButton loginButton1 = createStyledButton("登录");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        passwordLoginPanel.add(loginButton1, gbc);

        // 添加切换按钮
        JButton toggleButton = createStyledButton("切换为验证码登录");
        toggleButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) panel.getLayout();
            cl.show(panel, "code");
        });
        gbc.gridy = 4;
        passwordLoginPanel.add(toggleButton, gbc);

        // 注册按钮（在密码登录页显示）
        JButton registerButton1 = createStyledButton("注册");
        gbc.gridy = 5;
        passwordLoginPanel.add(registerButton1, gbc);

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

        // 添加到主面板card layout中
        panel.add(passwordLoginPanel, "password");
        panel.add(codeLoginPanel, "code");

        // 设置整体布局结构
        JPanel container = new JPanel(new BorderLayout());
        container.add(panel, BorderLayout.CENTER);

        // 获取验证码按钮事件处理
        getCodeButton.addActionListener(e -> {

            String phone = phoneField2.getText();
            LoginForm LoginFormCode = new LoginForm(null, null, phone);
            // 校验电话号码是否为空
            if (phone == null || phone.trim().isEmpty()) {
                new CustomDialog(AllergyManagementSystem.this, "请先输入电话号码!").setVisible(true);
                return;
            }

            // 校验电话号码是否为 11位数字
            if (phone == null || !phone.matches("\\d{11}")) {
                new CustomDialog(AllergyManagementSystem.this, "请输入正确的11位电话号码").setVisible(true);
                return;
            }
//            NullPlaceholder response = null;
//            try {
//                response = userHandler.login(LoginFormCode);
//            } catch (InterruptedException | URISyntaxException ex) {
//                new CustomDialog(AllergyManagementSystem.this, "request send error： " + ex.getMessage()).setVisible(true);
//            }
//
//            if (response != null ) {
//                new CustomDialog(this, "登录成功").setVisible(true);
//                this.hasLogin= true;
//            } else {
//                new CustomDialog(this, "登录失败，请检查账号或密码").setVisible(true);
//            }
        });

        // 密码登录按钮事件处理
        loginButton1.addActionListener(e -> {

            String phone = phoneField1.getText();
            String password = new String(passwordField.getPassword());
            LoginForm LoginFormPassword = new LoginForm(null, password, phone);
//            LoginResponse response = userHandler.login(LoginFormPassword);

            // 校验电话号码是否为空
            if (phone == null || phone.trim().isEmpty()) {
                new CustomDialog(AllergyManagementSystem.this, "请输入电话号码!").setVisible(true);
                return;
            }

            // 校验电话号码是否为 11 位数字
            if (phone == null || !phone.matches("\\d{11}")) {
                new CustomDialog(AllergyManagementSystem.this, "请输入正确的11位电话号码").setVisible(true);
                return;
            }

            // 校验密码是否为空
            if (password == null || password.trim().isEmpty()) {
                new CustomDialog(AllergyManagementSystem.this, "请输入密码!").setVisible(true);
                return;
            }

            // 校验密码是否为4~32位字母数字下划线
            if (password == null || !password.matches("^[a-zA-Z0-9_]{4,32}$")) {
                new CustomDialog(AllergyManagementSystem.this, "密码格式不正确，必须是4~32位的字母数字下划线").setVisible(true);
                return;
            }

        });

        // 验证码登录按钮事件处理
        loginButton2.addActionListener(e -> {
            String phone = phoneField2.getText();
            String code = codeField.getText();
            LoginForm LoginFormCode = new LoginForm(code, null, phone);
//            LoginResponse response = userHandler.login(LoginFormCode);

            // 校验电话号码是否为空
            if (phone == null || phone.trim().isEmpty()) {
                new CustomDialog(AllergyManagementSystem.this, "请输入电话号码!").setVisible(true);
                return;
            }

            // 校验电话号码是否为 11 位数字
            if (phone == null || !phone.matches("\\d{11}")) {
                new CustomDialog(AllergyManagementSystem.this, "请输入正确的11位电话号码").setVisible(true);
                return;
            }

            // 校验验证码是否为空
            if (code == null || code.trim().isEmpty()) {
                new CustomDialog(AllergyManagementSystem.this, "请输入验证码!").setVisible(true);
                return;
            }

            // 校验验证码是否为6位数字
            if (!code.matches("\\d{6}")) {
                new CustomDialog(AllergyManagementSystem.this, "请输入正确的6位验证码!").setVisible(true);
                return;
            }

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

        // 姓名输入
        JLabel nameLabel = new JLabel("姓 名:");
        JTextField nameField = new JTextField(20);
        nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 18)); // 字体大小
        nameLabel.setForeground(Color.WHITE); // 设置文字颜色为白色
        nameLabel.setBackground(new Color(0, 0, 0, 50)); // 半透明背景增强可读性
        gbc3.gridx = 0;
        gbc3.gridy = 1;
        gbc3.gridwidth = 1;
        registerFormPanel.add(nameLabel, gbc3);
        gbc3.gridx = 1;
        registerFormPanel.add(nameField, gbc3);

        // 电话号码输入
        JLabel phoneLabel3 = new JLabel("电话号码:");
        JTextField phoneField3 = new JTextField(20);
        phoneLabel3.setFont(new Font("微软雅黑", Font.BOLD, 18)); // 字体大小
        phoneLabel3.setForeground(Color.WHITE); // 设置文字颜色为白色
        phoneLabel3.setBackground(new Color(0, 0, 0, 50)); // 可选：半透明背景增强可读性
        gbc3.gridx = 0;
        gbc3.gridy = 2;
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
        gbc3.gridy = 3;
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
        gbc3.gridy = 4;
        gbc3.gridwidth = 2;
        registerFormPanel.add(codeInputPanel3, gbc3);

        // 注册按钮
        JButton registerButton3 = createStyledButton("提交注册");
        gbc3.gridy = 5;
        registerFormPanel.add(registerButton3, gbc3);

        // 返回按钮
        JButton backButton = createStyledButton("返回登录");
        gbc3.gridy = 6;
        registerFormPanel.add(backButton, gbc3);

        // 添加到主面板card layout中
        panel.add(registerFormPanel, "register");

        // 注册按钮事件处理
        registerButton3.addActionListener(e -> {
            String phone = phoneField3.getText();
            String password = new String(passwordField3.getPassword());

//            RegisterForm registerFormPhone= new RegisterForm(name, null, phone);
//            NullPlaceholder response = null;
//            try {
//                response = userHandler.register(registerFormPhone);
//
//            } catch (InterruptedException | URISyntaxException ex) {
//                new CustomDialog(AllergyManagementSystem.this, "request send error： " + ex.getMessage()).setVisible(true);
//            }

        });

        //  注册按钮事件（密码登录界面）
        registerButton1.addActionListener(e -> {
            CardLayout cl = (CardLayout) panel.getLayout();
            cl.show(panel, "register");
        });

        //  注册按钮事件（验证码登录界面）
        registerButton2.addActionListener(e -> {
            CardLayout cl = (CardLayout) panel.getLayout();
            cl.show(panel, "register");
        });

        // 返回登录按钮事件
        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) panel.getLayout();
            cl.show(panel, "password"); // 切换回密码登录页
        });

        return container;
    }

    private void showProfilePanel() {
        // 加载背景图片（假设你有一张名为"background.jpg"的图片放在resources目录下）
        Image profileBackground = new ImageIcon(getClass().getResource("/background.jpg")).getImage();

        // 使用支持背景图的面板
        BackgroundPanel profilePanel = new BackgroundPanel(profileBackground);
        profilePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;

        // 标题
        JLabel titleLabel = new JLabel("个人信息", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 48));
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        profilePanel.add(titleLabel, gbc);

        // 定义统一的输入框宽度
        int textFieldColumns = 20; // 统一的列数

        // 姓名
        JLabel nameLabel = new JLabel("姓 名:");
        JTextField nameField = new JTextField(textFieldColumns);  // 使用统一列数
        nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        profilePanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        profilePanel.add(nameField, gbc);

        // 出生日期
        JLabel birthDateLabel = new JLabel("出生日期:");
        JTextField birthDateField = new JTextField(textFieldColumns);  // 使用统一列数
        birthDateLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        birthDateLabel.setForeground(Color.WHITE);
        birthDateLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 2;
        profilePanel.add(birthDateLabel, gbc);
        gbc.gridx = 1;
        profilePanel.add(birthDateField, gbc);

        // 性别
        JLabel sexLabel = new JLabel("性 别:");
        JRadioButton maleRadio = new JRadioButton("男");
        JRadioButton femaleRadio = new JRadioButton("女");
        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(maleRadio);
        sexGroup.add(femaleRadio);
        JPanel sexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        sexPanel.setOpaque(false);
        sexPanel.add(maleRadio);
        sexPanel.add(femaleRadio);
        sexLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        sexLabel.setForeground(Color.WHITE);
        sexLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 3;
        profilePanel.add(sexLabel, gbc);
        gbc.gridx = 1;
        profilePanel.add(sexPanel, gbc);

        // 身份证号
        JLabel idLabel = new JLabel("身份证号:");
        JTextField idField = new JTextField(textFieldColumns);  // 使用统一列数
        idLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        idLabel.setForeground(Color.WHITE);
        idLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 4;
        profilePanel.add(idLabel, gbc);
        gbc.gridx = 1;
        profilePanel.add(idField, gbc);

        // 地址
        JLabel addressLabel = new JLabel("家庭地址:");
        JTextField addressField = new JTextField(textFieldColumns);  // 使用统一列数
        addressLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        addressLabel.setForeground(Color.WHITE);
        addressLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 5;
        profilePanel.add(addressLabel, gbc);
        gbc.gridx = 1;
        profilePanel.add(addressField, gbc);

        // 手机号码
        JLabel phoneLabel = new JLabel("手机号码:");
        JTextField phoneField = new JTextField(textFieldColumns);  // 使用统一列数
        phoneLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 6;
        profilePanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        profilePanel.add(phoneField, gbc);

        // 医保ID
        JLabel healthcareLabel = new JLabel("医保ID:");
        JTextField healthcareField = new JTextField(textFieldColumns);  // 使用统一列数
        healthcareLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        healthcareLabel.setForeground(Color.WHITE);
        healthcareLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 7;
        profilePanel.add(healthcareLabel, gbc);
        gbc.gridx = 1;
        profilePanel.add(healthcareField, gbc);

        // 身高
        JLabel heightLabel = new JLabel("身 高:");
        JTextField heightField = new JTextField(textFieldColumns);  // 使用统一列数
        heightLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        heightLabel.setForeground(Color.WHITE);
        heightLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 8;
        profilePanel.add(heightLabel, gbc);
        gbc.gridx = 1;
        profilePanel.add(heightField, gbc);

        // 体重
        JLabel weightLabel = new JLabel("体 重:");
        JTextField weightField = new JTextField(textFieldColumns);  // 使用统一列数
        weightLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        weightLabel.setForeground(Color.WHITE);
        weightLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 9;
        profilePanel.add(weightLabel, gbc);
        gbc.gridx = 1;
        profilePanel.add(weightField, gbc);

        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        // 保存按钮
        JButton saveButton = createStyledButton("保存");
        saveButton.addActionListener(e -> {
            // 处理保存逻辑
            String name = nameField.getText();
            String birthDateStr = birthDateField.getText();
            boolean sex = maleRadio.isSelected();
            String identityCardId = idField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String healthcareId = healthcareField.getText();

            // 验证输入
            if (name.isEmpty()) {
                new CustomDialog(this, "请输入姓名!").setVisible(true);
                return;
            }

            if (!identityCardId.matches("^\\d{18}$")) {
                new CustomDialog(this, "请输入正确的18位身份证号码!").setVisible(true);
                return;
            }

            if (!phone.matches("^\\d{11}$")) {
                new CustomDialog(this, "请输入正确的11位手机号码!").setVisible(true);
                return;
            }

            try {
                // 这里可以添加保存到服务器的逻辑
                new CustomDialog(this, "信息保存成功!").setVisible(true);
            } catch (Exception ex) {
                new CustomDialog(this, "输入数据格式不正确!" + ex.getMessage()).setVisible(true);
            }
        });

        // 返回按钮
        JButton backButton = createStyledButton("返回");
        backButton.addActionListener(e -> {
            // 返回主界面
            JPanel mainPanel = (JPanel) getContentPane();
            Component centerComponent = ((BorderLayout) mainPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
            mainPanel.remove(centerComponent);

            // 重新创建并添加欢迎面板
            JPanel welcomePanel = new JPanel(new BorderLayout());
            welcomePanel.setOpaque(false);
            JLabel welcomeLabel = new JLabel("欢迎使用过敏性疾病管理系统", SwingConstants.CENTER);
            welcomeLabel.setFont(new Font("微软雅黑", Font.BOLD, 48));
            welcomeLabel.setForeground(Color.WHITE);
            welcomeLabel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
            welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

            mainPanel.add(welcomePanel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        profilePanel.add(buttonPanel, gbc);

        // 替换主内容面板的内容
        JPanel mainPanel = (JPanel) getContentPane();
        Component centerComponent = ((BorderLayout) mainPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        mainPanel.remove(centerComponent);
        mainPanel.add(profilePanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AllergyManagementSystem app = new AllergyManagementSystem();
            app.setVisible(true);
        });
    }
}
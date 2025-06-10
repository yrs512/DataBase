package org.yrs512.respiratory.gui.page;

import org.harvey.respiratory.client.ServerHandlerRegister;
import org.harvey.respiratory.client.handler.UserSecurityHandler;
import org.harvey.respiratory.client.pojo.dto.LoginFormDto;
import org.yrs512.respiratory.gui.BackgroundPanel;
import org.yrs512.respiratory.gui.ButtonPainter;
import org.yrs512.respiratory.gui.CustomDialog;
import org.yrs512.respiratory.gui.RespiratoryManagementSystem;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author <a href="mailto:harvey.blocks@outlook.com">Harvey Blocks</a>
 * @version 1.0
 * @date 2025-06-11 00:13
 */ // 登录面板构建器类
public class LoginPanelBuilder {
    private ButtonPainter buttonPainter;
    private ServerHandlerRegister register;
    private RespiratoryManagementSystem system;

    public LoginPanelBuilder(
            ButtonPainter buttonPainter,
            ServerHandlerRegister register,
            RespiratoryManagementSystem system) {
        this.buttonPainter = buttonPainter;
        this.register = register;
        this.system = system;
    }

    public JPanel createLoginPanel() {
        Image loginBackground = new ImageIcon(getClass().getResource("/background.jpg")).getImage();
        BackgroundPanel panel = new BackgroundPanel(loginBackground);
        panel.setLayout(new CardLayout());

        JPanel passwordLoginPanel = createPasswordLoginPanel();
        JPanel codeLoginPanel = createCodeLoginPanel();
        JPanel registerFormPanel = createRegisterFormPanel();

        panel.add(passwordLoginPanel, "password");
        panel.add(codeLoginPanel, "code");
        panel.add(registerFormPanel, "register");

        JPanel container = new JPanel(new BorderLayout());
        container.add(panel, BorderLayout.CENTER);

        return container;
    }

    private JPanel createPasswordLoginPanel() {
        JPanel passwordLoginPanel = createStyledPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addPasswordLoginTitle(passwordLoginPanel, gbc);
        addPhoneNumberInput(passwordLoginPanel, gbc);
        addPasswordInput(passwordLoginPanel, gbc);
        addPasswordLoginButton(passwordLoginPanel, gbc);
        addSwitchToCodeLoginButton(passwordLoginPanel, gbc);
        addPasswordLoginRegisterButton(passwordLoginPanel, gbc);

        return passwordLoginPanel;
    }

    private void addPasswordLoginTitle(JPanel panel, GridBagConstraints gbc) {
        JLabel passwordLoginTitleLabel = new JLabel("密码登录", SwingConstants.CENTER);
        passwordLoginTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, 48));
        passwordLoginTitleLabel.setForeground(new Color(255, 255, 255));
        passwordLoginTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(passwordLoginTitleLabel, gbc);
    }

    private void addPhoneNumberInput(JPanel panel, GridBagConstraints gbc) {
        JTextField phoneNumberField = new JTextField(20);
        JLabel phoneNumberLabel = new JLabel("电话号码:");
        phoneNumberLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        phoneNumberLabel.setForeground(Color.WHITE);
        phoneNumberLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(phoneNumberLabel, gbc);
        gbc.gridx = 1;
        panel.add(phoneNumberField, gbc);
    }

    private void addPasswordInput(JPanel panel, GridBagConstraints gbc) {
        JLabel passwordLabel = new JLabel("密码:");
        JPasswordField passwordField = new JPasswordField(20);
        passwordLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);
    }

    private void addPasswordLoginButton(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        JButton passwordLoginButton = buttonPainter.createStyledButton("登录");
        panel.add(passwordLoginButton, gbc);
        passwordLoginButton.addActionListener(e -> {
            String phone = getPhoneNumber(panel);
            String password = getPassword(panel);

            LoginFormDto loginFormDto = new LoginFormDto(phone, null, password);

            UserSecurityHandler handler = register.get(UserSecurityHandler.class);
            handler.login(loginFormDto);

            if (!validatePhoneNumber(phone)) {
                return;
            }

            if (!validatePassword(password)) {
                return;
            }
        });
    }

    private String getPhoneNumber(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                return ((JTextField) component).getText();
            }
        }
        return null;
    }

    private String getPassword(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JPasswordField) {
                return new String(((JPasswordField) component).getPassword());
            }
        }
        return null;
    }

    private boolean validatePhoneNumber(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            new CustomDialog(system, "请输入电话号码!").setVisible(true);
            return false;
        }

        if (!phone.matches("\\d{11}")) {
            new CustomDialog(system, "请输入正确的11位电话号码").setVisible(true);
            return false;
        }
        return true;
    }

    private boolean validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            new CustomDialog(system, "请输入密码!").setVisible(true);
            return false;
        }

        if (!password.matches("^[a-zA-Z0-9_]{4,32}$")) {
            new CustomDialog(
                    system, "密码格式不正确，必须是4~32位的字母数字下划线").setVisible(
                    true);
            return false;
        }
        return true;
    }

    private void addSwitchToCodeLoginButton(JPanel panel, GridBagConstraints gbc) {
        JButton switchToCodeLoginButton = buttonPainter.createStyledButton("切换为验证码登录");
        switchToCodeLoginButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) ((BackgroundPanel) panel.getParent()).getLayout();
            cl.show((BackgroundPanel) panel.getParent(), "code");
        });
        gbc.gridy = 4;
        panel.add(switchToCodeLoginButton, gbc);
    }

    private void addPasswordLoginRegisterButton(JPanel panel, GridBagConstraints gbc) {
        JButton passwordLoginRegisterButton = buttonPainter.createStyledButton("注册");
        gbc.gridy = 5;
        panel.add(passwordLoginRegisterButton, gbc);
        passwordLoginRegisterButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) ((BackgroundPanel) panel.getParent()).getLayout();
            cl.show((BackgroundPanel) panel.getParent(), "register");
        });
    }

    private JPanel createCodeLoginPanel() {
        JPanel codeLoginPanel = createStyledPanel();
        GridBagConstraints gbcCode = new GridBagConstraints();
        gbcCode.insets = new Insets(10, 10, 10, 10);
        gbcCode.fill = GridBagConstraints.HORIZONTAL;

        addCodeLoginTitle(codeLoginPanel, gbcCode);
        addCodeLoginPhoneNumberInput(codeLoginPanel, gbcCode);
        addCodeInputAndGetButton(codeLoginPanel, gbcCode);
        addCodeLoginButton(codeLoginPanel, gbcCode);
        addSwitchToPasswordLoginButton(codeLoginPanel, gbcCode);
        addCodeLoginRegisterButton(codeLoginPanel, gbcCode);

        return codeLoginPanel;
    }

    private void addCodeLoginTitle(JPanel panel, GridBagConstraints gbc) {
        JLabel codeLoginTitleLabel = new JLabel("验证码登录", SwingConstants.CENTER);
        codeLoginTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, 48));
        codeLoginTitleLabel.setForeground(new Color(255, 255, 255));
        codeLoginTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(codeLoginTitleLabel, gbc);
    }

    private void addCodeLoginPhoneNumberInput(JPanel panel, GridBagConstraints gbc) {
        JTextField codeLoginPhoneNumberField = new JTextField(20);
        JLabel codeLoginPhoneNumberLabel = new JLabel("电话号码:");
        codeLoginPhoneNumberLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        codeLoginPhoneNumberLabel.setForeground(Color.WHITE);
        codeLoginPhoneNumberLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(codeLoginPhoneNumberLabel, gbc);
        gbc.gridx = 1;
        panel.add(codeLoginPhoneNumberField, gbc);
    }

    private void addCodeInputAndGetButton(JPanel panel, GridBagConstraints gbc) {
        JLabel codeLabel = new JLabel("验证码:");
        JTextField codeField = new JTextField(20);
        codeLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        codeLabel.setForeground(Color.WHITE);
        codeLabel.setBackground(new Color(0, 0, 0, 50));
        JButton getCodeButton = buttonPainter.createStyledButton("获取验证码");

        JPanel codeInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        codeInputPanel.setOpaque(false);
        codeInputPanel.add(codeLabel);
        codeInputPanel.add(codeField);
        codeInputPanel.add(getCodeButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(codeInputPanel, gbc);

        getCodeButton.addActionListener(e -> {
            String phone = getCodeLoginPhoneNumber(panel);
            LoginFormDto LoginFormCode = new LoginFormDto(null, null, phone);

            if (!validatePhoneNumber(phone)) {
                return;
            }
        });
    }

    private String getCodeLoginPhoneNumber(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                return ((JTextField) component).getText();
            }
        }
        return null;
    }

    private void addCodeLoginButton(JPanel panel, GridBagConstraints gbc) {
        JButton codeLoginButton = buttonPainter.createStyledButton("登录");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(codeLoginButton, gbc);

        codeLoginButton.addActionListener(e -> {
            String phone = getCodeLoginPhoneNumber(panel);
            String code = getCode(panel);
            LoginFormDto LoginFormCode = new LoginFormDto(code, null, phone);

            if (!validatePhoneNumber(phone)) {
                return;
            }

            if (!validateCode(code)) {
                return;
            }
        });
    }

    private String getCode(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField && component.getName() == null) {
                return ((JTextField) component).getText();
            }
        }
        return null;
    }

    private boolean validateCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            new CustomDialog(system, "请输入验证码!").setVisible(true);
            return false;
        }

        if (!code.matches("\\d{6}")) {
            new CustomDialog(system, "请输入正确的6位验证码!").setVisible(true);
            return false;
        }
        return true;
    }

    private void addSwitchToPasswordLoginButton(JPanel panel, GridBagConstraints gbc) {
        JButton switchToPasswordLoginButton = buttonPainter.createStyledButton("切换为密码登录");
        switchToPasswordLoginButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) ((BackgroundPanel) panel.getParent()).getLayout();
            cl.show((BackgroundPanel) panel.getParent(), "password");
        });
        gbc.gridy = 4;
        panel.add(switchToPasswordLoginButton, gbc);
    }

    private void addCodeLoginRegisterButton(JPanel panel, GridBagConstraints gbc) {
        JButton codeLoginRegisterButton = buttonPainter.createStyledButton("注册");
        gbc.gridy = 5;
        panel.add(codeLoginRegisterButton, gbc);
        codeLoginRegisterButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) ((BackgroundPanel) panel.getParent()).getLayout();
            cl.show((BackgroundPanel) panel.getParent(), "register");
        });
    }

    private JPanel createRegisterFormPanel() {
        JPanel registerFormPanel = createStyledPanel();
        GridBagConstraints gbcRegister = new GridBagConstraints();
        gbcRegister.insets = new Insets(10, 10, 10, 10);
        gbcRegister.fill = GridBagConstraints.HORIZONTAL;

        addRegisterTitle(registerFormPanel, gbcRegister);
        addNameInput(registerFormPanel, gbcRegister);
        addRegisterPhoneNumberInput(registerFormPanel, gbcRegister);
        addRegisterPasswordInput(registerFormPanel, gbcRegister);
        addRegisterCodeInputAndGetButton(registerFormPanel, gbcRegister);
        addRegisterSubmitButton(registerFormPanel, gbcRegister);
        addBackToLoginButton(registerFormPanel, gbcRegister);

        return registerFormPanel;
    }

    private void addRegisterTitle(JPanel panel, GridBagConstraints gbc) {
        JLabel registerTitleLabel = new JLabel("用户注册", SwingConstants.CENTER);
        registerTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, 48));
        registerTitleLabel.setForeground(new Color(255, 255, 255));
        registerTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(registerTitleLabel, gbc);
    }

    private void addNameInput(JPanel panel, GridBagConstraints gbc) {
        JLabel nameLabel = new JLabel("姓 名:");
        JTextField nameField = new JTextField(20);
        nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);
    }

    private void addRegisterPhoneNumberInput(JPanel panel, GridBagConstraints gbc) {
        JLabel registerPhoneNumberLabel = new JLabel("电话号码:");
        JTextField registerPhoneNumberField = new JTextField(20);
        registerPhoneNumberLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        registerPhoneNumberLabel.setForeground(Color.WHITE);
        registerPhoneNumberLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(registerPhoneNumberLabel, gbc);
        gbc.gridx = 1;
        panel.add(registerPhoneNumberField, gbc);
    }

    private void addRegisterPasswordInput(JPanel panel, GridBagConstraints gbc) {
        JLabel registerPasswordLabel = new JLabel("密码:");
        JPasswordField registerPasswordField = new JPasswordField(20);
        registerPasswordLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        registerPasswordLabel.setForeground(Color.WHITE);
        registerPasswordLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(registerPasswordLabel, gbc);
        gbc.gridx = 1;
        panel.add(registerPasswordField, gbc);
    }

    private void addRegisterCodeInputAndGetButton(JPanel panel, GridBagConstraints gbc) {
        JLabel registerCodeLabel = new JLabel("验证码:");
        JTextField registerCodeField = new JTextField(20);
        registerCodeLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        registerCodeLabel.setForeground(Color.WHITE);
        registerCodeLabel.setBackground(new Color(0, 0, 0, 50));
        JButton registerGetCodeButton = buttonPainter.createStyledButton("获取验证码");

        JPanel registerCodeInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        registerCodeInputPanel.setOpaque(false);
        registerCodeInputPanel.add(registerCodeLabel);
        registerCodeInputPanel.add(registerCodeField);
        registerCodeInputPanel.add(registerGetCodeButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(registerCodeInputPanel, gbc);
    }

    private void addRegisterSubmitButton(JPanel panel, GridBagConstraints gbc) {
        JButton registerSubmitButton = buttonPainter.createStyledButton("提交注册");
        gbc.gridy = 5;
        panel.add(registerSubmitButton, gbc);

        registerSubmitButton.addActionListener(e -> {
            String phone = getRegisterPhoneNumber(panel);
            String password = getRegisterPassword(panel);
        });
    }

    private String getRegisterPhoneNumber(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField && component.getName() == null) {
                return ((JTextField) component).getText();
            }
        }
        return null;
    }

    private String getRegisterPassword(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JPasswordField) {
                return new String(((JPasswordField) component).getPassword());
            }
        }
        return null;
    }

    private void addBackToLoginButton(JPanel panel, GridBagConstraints gbc) {
        JButton backToLoginButton = buttonPainter.createStyledButton("返回登录");
        gbc.gridy = 6;
        panel.add(backToLoginButton, gbc);
        backToLoginButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) ((BackgroundPanel) panel.getParent()).getLayout();
            cl.show((BackgroundPanel) panel.getParent(), "password");
        });
    }

    private JPanel createStyledPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setOpaque(false);
        return panel;
    }
}

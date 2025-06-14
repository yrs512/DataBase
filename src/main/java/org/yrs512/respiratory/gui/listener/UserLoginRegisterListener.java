package org.yrs512.respiratory.gui.listener;

import org.harvey.respiratory.client.ServerHandlerRegister;
import org.harvey.respiratory.client.ServerHandlerRegisterFactory;
import org.harvey.respiratory.client.handler.UserSecurityHandler;
import org.harvey.respiratory.client.pojo.dto.LoginFormDto;
import org.yrs512.respiratory.gui.global.CustomDialog;
import org.yrs512.respiratory.gui.global.RespiratoryManagementSystem;

import javax.swing.*;
import java.awt.*;

/**
 * 用户登录和注册的监听器
 *
 * @author <a href="mailto:harvey.blocks@outlook.com">Harvey Blocks</a>
 * @version 1.0
 * @date 2025-06-13 23:07
 */
public class UserLoginRegisterListener {
    private RespiratoryManagementSystem system;
    private final ServerHandlerRegister register = ServerHandlerRegisterFactory.TEST;

    public UserLoginRegisterListener(RespiratoryManagementSystem system) {
        this.system = system;
    }


    public void listenPasswordLogin(JPanel panel) {
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

    public void listenCodeLogin(JPanel panel) {
        String phone = getCodeLoginPhoneNumber(panel);
        String code = getCode(panel);
        LoginFormDto LoginFormCode = new LoginFormDto(code, null, phone);

        if (!validatePhoneNumber(phone)) {
            return;
        }
        // TODO
        if (!validateCode(code)) {
            return;
        }
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

    private String getCode(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField && component.getName() == null) {
                return ((JTextField) component).getText();
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

    private boolean validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            new CustomDialog(system, "请输入密码!").setVisible(true);
            return false;
        }

        if (!password.matches("^[a-zA-Z0-9_]{4,32}$")) {
            new CustomDialog(system, "密码格式不正确，必须是4~32位的字母数字下划线").setVisible(true);
            return false;
        }
        return true;
    }

    public void listenGetCode(JPanel panel) {
        String phone = getCodeLoginPhoneNumber(panel);
        LoginFormDto LoginFormCode = new LoginFormDto(null, null, phone);
        // TODO
        if (!validatePhoneNumber(phone)) {
            return;
        }
    }

    public void listenRegister(JPanel panel){
        String phone = getRegisterPhoneNumber(panel);
        String password = getRegisterPassword(panel);
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
}

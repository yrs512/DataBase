package org.yrs512.respiratory.gui.page;

import org.yrs512.respiratory.gui.global.BackgroundPanel;
import org.yrs512.respiratory.gui.global.ButtonPainter;
import org.yrs512.respiratory.gui.global.CustomDialog;
import org.yrs512.respiratory.gui.global.RespiratoryManagementSystem;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author <a href="mailto:harvey.blocks@outlook.com">Harvey Blocks</a>
 * @version 1.0
 * @date 2025-06-11 00:14
 */ // 个人信息面板构建器类
public class ProfilePanelBuilder {
    private ButtonPainter buttonPainter;
    private RespiratoryManagementSystem system;

    public ProfilePanelBuilder(ButtonPainter buttonPainter, RespiratoryManagementSystem system) {
        this.buttonPainter = buttonPainter;
        this.system = system;
    }

    public JPanel createProfilePanel() {
        Image profileBackground = new ImageIcon(getClass().getResource("/background.jpg")).getImage();
        BackgroundPanel profilePanel = new BackgroundPanel(profileBackground);
        profilePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;

        addProfileTitle(profilePanel, gbc);
        addNameInputToProfile(profilePanel, gbc);
        addBirthDateInput(profilePanel, gbc);
        addSexInput(profilePanel, gbc);
        addIdInput(profilePanel, gbc);
        addAddressInput(profilePanel, gbc);
        addPhoneNumberInputToProfile(profilePanel, gbc);
        addHealthcareInput(profilePanel, gbc);
        addHeightInput(profilePanel, gbc);
        addWeightInput(profilePanel, gbc);
        addButtonPanelToProfile(profilePanel, gbc);

        return profilePanel;
    }

    private void addProfileTitle(JPanel panel, GridBagConstraints gbc) {
        JLabel profileTitleLabel = new JLabel("个人信息", SwingConstants.CENTER);
        profileTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, 48));
        profileTitleLabel.setForeground(new Color(255, 255, 255));
        profileTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(profileTitleLabel, gbc);
    }

    private void addNameInputToProfile(JPanel panel, GridBagConstraints gbc) {
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

    private void addBirthDateInput(JPanel panel, GridBagConstraints gbc) {
        JLabel birthDateLabel = new JLabel("出生日期:");
        JTextField birthDateField = new JTextField(20);
        birthDateLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        birthDateLabel.setForeground(Color.WHITE);
        birthDateLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(birthDateLabel, gbc);
        gbc.gridx = 1;
        panel.add(birthDateField, gbc);
    }

    private void addSexInput(JPanel panel, GridBagConstraints gbc) {
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
        panel.add(sexLabel, gbc);
        gbc.gridx = 1;
        panel.add(sexPanel, gbc);
    }

    private void addIdInput(JPanel panel, GridBagConstraints gbc) {
        JLabel idLabel = new JLabel("身份证号:");
        JTextField idField = new JTextField(20);
        idLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        idLabel.setForeground(Color.WHITE);
        idLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(idLabel, gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);
    }

    private void addAddressInput(JPanel panel, GridBagConstraints gbc) {
        JLabel addressLabel = new JLabel("家庭地址:");
        JTextField addressField = new JTextField(20);
        addressLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        addressLabel.setForeground(Color.WHITE);
        addressLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(addressLabel, gbc);
        gbc.gridx = 1;
        panel.add(addressField, gbc);
    }

    private void addPhoneNumberInputToProfile(JPanel panel, GridBagConstraints gbc) {
        JTextField phoneNumberField = new JTextField(20);
        JLabel phoneNumberLabel = new JLabel("手机号码:");
        phoneNumberLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        phoneNumberLabel.setForeground(Color.WHITE);
        phoneNumberLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(phoneNumberLabel, gbc);
        gbc.gridx = 1;
        panel.add(phoneNumberField, gbc);
    }

    private void addHealthcareInput(JPanel panel, GridBagConstraints gbc) {
        JLabel healthcareLabel = new JLabel("医保ID:");
        JTextField healthcareField = new JTextField(20);
        healthcareLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        healthcareLabel.setForeground(Color.WHITE);
        healthcareLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(healthcareLabel, gbc);
        gbc.gridx = 1;
        panel.add(healthcareField, gbc);
    }

    private void addHeightInput(JPanel panel, GridBagConstraints gbc) {
        JLabel heightLabel = new JLabel("身 高:");
        JTextField heightField = new JTextField(20);
        heightLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        heightLabel.setForeground(Color.WHITE);
        heightLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(heightLabel, gbc);
        gbc.gridx = 1;
        panel.add(heightField, gbc);
    }

    private void addWeightInput(JPanel panel, GridBagConstraints gbc) {
        JLabel weightLabel = new JLabel("体 重:");
        JTextField weightField = new JTextField(20);
        weightLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        weightLabel.setForeground(Color.WHITE);
        weightLabel.setBackground(new Color(0, 0, 0, 50));
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(weightLabel, gbc);
        gbc.gridx = 1;
        panel.add(weightField, gbc);
    }

    private void addButtonPanelToProfile(JPanel panel, GridBagConstraints gbc) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        JButton saveButton = buttonPainter.createStyledButton("保存");
        saveButton.addActionListener(e -> {
            String name = getName(panel);
            String birthDateStr = getBirthDate(panel);
            boolean sex = isMaleSelected(panel);
            String identityCardId = getId(panel);
            String address = getAddress(panel);
            String phone = getPhoneNumberFromProfile(panel);
            String healthcareId = getHealthcareId(panel);

            if (!validateName(name)) {
                return;
            }

            if (!validateIdentityCardId(identityCardId)) {
                return;
            }

            if (!validatePhoneNumber(phone)) {
                return;
            }

            try {
                new CustomDialog(system, "信息保存成功!").setVisible(true);
            } catch (Exception ex) {
                new CustomDialog(system, "输入数据格式不正确!" + ex.getMessage()).setVisible(true);
            }
        });

        JButton backButton = buttonPainter.createStyledButton("返回");
        backButton.addActionListener(e -> {
            system.returnToMainPanel();
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
    }

    private String getName(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField && component.getName() == null) {
                return ((JTextField) component).getText();
            }
        }
        return null;
    }

    private String getBirthDate(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField && component.getName() == null) {
                return ((JTextField) component).getText();
            }
        }
        return null;
    }

    private boolean isMaleSelected(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JRadioButton && ((JRadioButton) component).getText().equals("男")) {
                return ((JRadioButton) component).isSelected();
            }
        }
        return false;
    }

    private String getId(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField && component.getName() == null) {
                return ((JTextField) component).getText();
            }
        }
        return null;
    }

    private String getAddress(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField && component.getName() == null) {
                return ((JTextField) component).getText();
            }
        }
        return null;
    }

    private String getPhoneNumberFromProfile(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField && component.getName() == null) {
                return ((JTextField) component).getText();
            }
        }
        return null;
    }

    private String getHealthcareId(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField && component.getName() == null) {
                return ((JTextField) component).getText();
            }
        }
        return null;
    }

    private boolean validateName(String name) {
        if (name.isEmpty()) {
            new CustomDialog(system, "请输入姓名!").setVisible(true);
            return false;
        }
        return true;
    }

    private boolean validateIdentityCardId(String identityCardId) {
        if (!identityCardId.matches("^\\d{18}$")) {
            new CustomDialog(system, "请输入正确的18位身份证号码!").setVisible(true);
            return false;
        }
        return true;
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
}

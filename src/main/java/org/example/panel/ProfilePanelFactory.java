// ProfilePanelFactory.java
package org.example.panel;

import org.example.AllergyManagementSystem;
import org.example.CustomDialog;
import org.example.interFace.BackgroundPanel;
import org.example.interFace.ButtonFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePanelFactory {

    /**
     * 显示个人信息面板
     * @param frame 主窗口实例
     */
    public static void showProfilePanel(AllergyManagementSystem frame) {
        // 加载背景图片（假设你有一张名为"background.jpg"的图片放在resources目录下）
        Image profileBackground = new ImageIcon(frame.getClass().getResource("/background.jpg")).getImage();

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
        JButton saveButton = ButtonFactory.createStyledButton("保存");
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String birthDateStr = birthDateField.getText();
            boolean sex = maleRadio.isSelected();
            String identityCardId = idField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String healthcareId = healthcareField.getText();

            if (name.isEmpty()) {
                new CustomDialog(frame, "请输入姓名!").setVisible(true);
                return;
            }

            if (!identityCardId.matches("^\\d{18}$")) {
                new CustomDialog(frame, "请输入正确的18位身份证号码!").setVisible(true);
                return;
            }

            if (!phone.matches("^\\d{11}$")) {
                new CustomDialog(frame, "请输入正确的11位手机号码!").setVisible(true);
                return;
            }

            try {
                new CustomDialog(frame, "信息保存成功!").setVisible(true);
            } catch (Exception ex) {
                new CustomDialog(frame, "输入数据格式不正确!" + ex.getMessage()).setVisible(true);
            }
        });

        // 返回按钮
        JButton backButton = ButtonFactory.createStyledButton("返回");
        backButton.addActionListener(e -> {
            // 返回主界面
            JPanel mainPanel = (JPanel) frame.getContentPane();
            Component centerComponent = ((BorderLayout) mainPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
            mainPanel.remove(centerComponent);

            // 重新创建并添加欢迎面板
            JPanel welcomePanel = new JPanel(new BorderLayout());
            welcomePanel.setOpaque(false);
            JLabel welcomeLabel = new JLabel("欢迎使用过敏性疾病管理系统", SwingConstants.CENTER);
            welcomeLabel.setFont(new Font("微软雅黑", Font.BOLD, 48));
            welcomeLabel.setForeground(Color.BLACK);
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
        JPanel mainPanel = (JPanel) frame.getContentPane();
        Component centerComponent = ((BorderLayout) mainPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        mainPanel.remove(centerComponent);
        mainPanel.add(profilePanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}

// HealthcareRechargePanelFactory.java
package org.example.panel;

import org.example.AllergyManagementSystem;
import org.example.CustomDialog;
import org.example.interFace.BackgroundPanel;
import org.example.interFace.ButtonFactory;
import org.example.interFace.StylePanelFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HealthcareRechargePanelFactory {

    /**
     * 创建医保充值信息面板
     * @param frame 主窗口实例
     * @return 构建好的 JPanel 面板
     */
    public static JPanel createHealthcareRechargePanel(AllergyManagementSystem frame) {
        JPanel panel = StylePanelFactory.createStyledPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 标题
        JLabel titleLabel = new JLabel("医保账户充值", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // 充值依据选择
        JLabel basisLabel = new JLabel("充值依据:");
        basisLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        basisLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(basisLabel, gbc);

        JComboBox<String> basisCombo = new JComboBox<>(new String[]{"医保号", "病患号", "身份证号"});
        basisCombo.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(basisCombo, gbc);

        // 输入框
        JLabel inputLabel = new JLabel("输入号码:");
        inputLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        inputLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(inputLabel, gbc);

        JTextField inputField = new JTextField(20);
        inputField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(inputField, gbc);

        // 充值金额
        JLabel amountLabel = new JLabel("充值金额(元):");
        amountLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        amountLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(amountLabel, gbc);

        JTextField amountField = new JTextField(20);
        amountField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(amountField, gbc);

        // 充值按钮
        JButton rechargeButton = ButtonFactory.createStyledButton("确认充值");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(rechargeButton, gbc);

        // 充值按钮事件
        rechargeButton.addActionListener(e -> {
            String basis = (String) basisCombo.getSelectedItem();
            String number = inputField.getText().trim();
            String amountStr = amountField.getText().trim();

            if (number.isEmpty()) {
                new CustomDialog(frame, "请输入充值号码!").setVisible(true);
                return;
            }

            if (amountStr.isEmpty()) {
                new CustomDialog(frame, "请输入充值金额!").setVisible(true);
                return;
            }

            try {
                double amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    new CustomDialog(frame, "充值金额必须大于0!").setVisible(true);
                    return;
                }

                // 模拟充值成功
                new CustomDialog(frame, "充值成功: " + amount + "元").setVisible(true);
                amountField.setText("");
            } catch (NumberFormatException ex) {
                new CustomDialog(frame, "请输入有效的金额数字!").setVisible(true);
            }
        });

        return panel;
    }
}

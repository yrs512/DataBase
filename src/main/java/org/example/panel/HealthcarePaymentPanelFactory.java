// HealthcarePaymentPanelFactory.java
package org.example.panel;

import org.example.AllergyManagementSystem;
import org.example.CustomDialog;
import org.example.healthcare.Pay;
import org.example.interFace.BackgroundPanel;
import org.example.interFace.ButtonFactory;
import org.example.interFace.StylePanelFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HealthcarePaymentPanelFactory {

    /**
     * 创建医保支付信息面板
     * @param frame 主窗口实例
     * @return 构建好的 JPanel 面板
     */
    public static JPanel createHealthcarePaymentPanel(AllergyManagementSystem frame) {
        JPanel panel = StylePanelFactory.createStyledPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 标题
        JLabel titleLabel = new JLabel("医保账户付款", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // 医保号输入
        JLabel idLabel = new JLabel("医保号:");
        idLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        idLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(idLabel, gbc);

        JTextField idField = new JTextField(20);
        idField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(idField, gbc);

        // 付款金额
        JLabel amountLabel = new JLabel("付款金额(元):");
        amountLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        amountLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(amountLabel, gbc);

        JTextField amountField = new JTextField(20);
        amountField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(amountField, gbc);

        // 付款按钮
        JButton paymentButton = ButtonFactory.createStyledButton("确认付款");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(paymentButton, gbc);

        // 状态提示标签
        JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        statusLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(statusLabel, gbc);

        // 付款按钮事件
        paymentButton.addActionListener(e -> {
            String healthcareIdStr = idField.getText().trim();
            String amountStr = amountField.getText().trim();

            if (healthcareIdStr.isEmpty()) {
                statusLabel.setText("请输入医保号!");
                return;
            }

            if (amountStr.isEmpty()) {
                statusLabel.setText("请输入付款金额!");
                return;
            }

            try {
                int healthcareId = Integer.parseInt(healthcareIdStr);
                double amount = Double.parseDouble(amountStr);

                if (amount <= 0) {
                    statusLabel.setText("付款金额必须大于0!");
                    return;
                }

                // 转换为分
                long amountFen = (long)(amount * 100);

                // 调用付款服务
                Pay payService = new Pay();
                Pay.PaymentResult result = payService.pay(amountFen);

                if (result.getCode() == 200) {
                    statusLabel.setText("付款成功: " + amount + "元");
                    amountField.setText("");
                } else {
                    statusLabel.setText("付款失败: " + result.getMsg());
                }
            } catch (NumberFormatException ex) {
                statusLabel.setText("请输入有效的数字!");
            }
        });

        return panel;
    }
}

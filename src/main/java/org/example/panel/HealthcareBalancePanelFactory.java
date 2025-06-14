// HealthcareBalancePanelFactory.java
package org.example.panel;

import org.example.AllergyManagementSystem;
import org.example.healthcare.BalanceQuery;
import org.example.interFace.BackgroundPanel;
import org.example.interFace.ButtonFactory;
import org.example.interFace.StylePanelFactory;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HealthcareBalancePanelFactory {

    /**
     * 创建医保余额信息面板
     * @param frame 主窗口实例
     * @return 构建好的 JPanel 面板
     */
    public static JPanel createHealthcareBalancePanel(AllergyManagementSystem frame) {
        JPanel panel = StylePanelFactory.createStyledPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 标题
        JLabel titleLabel = new JLabel("医保余额查询", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // 医保号输入
        JLabel inputLabel = new JLabel("医保号:");
        inputLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        inputLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(inputLabel, gbc);

        JTextField inputField = new JTextField(20);
        inputField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(inputField, gbc);

        // 查询按钮
        JButton queryButton = ButtonFactory.createStyledButton("查询余额");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(queryButton, gbc);

        // 状态提示标签
        JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        statusLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(statusLabel, gbc);

        // 余额显示面板（初始隐藏）
        JPanel balancePanel = new JPanel(new GridBagLayout());
        balancePanel.setOpaque(false);
        GridBagConstraints gbcBalance = new GridBagConstraints();
        gbcBalance.insets = new Insets(5, 5, 5, 5);
        gbcBalance.fill = GridBagConstraints.HORIZONTAL;

        JLabel resultLabel = new JLabel("当前余额: ");
        resultLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        resultLabel.setForeground(Color.WHITE);
        gbcBalance.gridx = 0;
        gbcBalance.gridy = 0;
        balancePanel.add(resultLabel, gbcBalance);

        JLabel balanceLabel = new JLabel("0.00 元");
        balanceLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        balanceLabel.setForeground(new Color(255, 215, 0));
        gbcBalance.gridx = 1;
        balancePanel.add(balanceLabel, gbcBalance);

        balancePanel.setVisible(false); // 初始隐藏
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(balancePanel, gbc);

        // 查询按钮事件
        queryButton.addActionListener(e -> {
            String healthcareIdStr = inputField.getText().trim();

            if (healthcareIdStr.isEmpty()) {
                statusLabel.setText("请输入医保号!");
                balancePanel.setVisible(false);
                return;
            }

            try {
                statusLabel.setText("查询中...");
                int healthcareId = Integer.parseInt(healthcareIdStr);
                BalanceQuery balanceQuery = new BalanceQuery();
                BalanceQuery.QueryResult result = balanceQuery.queryByPatientId(healthcareId);

                if (result.getCode() == 200) {
                    double balanceYuan = result.getData() / 100.0;
                    balanceLabel.setText(String.format("%.2f 元", balanceYuan));
                    balancePanel.setVisible(true);
                    statusLabel.setText("查询成功");
                } else {
                    balancePanel.setVisible(false);
                    statusLabel.setText("查询失败: " + result.getMsg());
                }
            } catch (NumberFormatException ex) {
                balancePanel.setVisible(false);
                statusLabel.setText("医保号必须为数字!");
            }
        });

        return panel;
    }
}

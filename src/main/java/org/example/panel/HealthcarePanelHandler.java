// HealthcarePanelHandler.java
package org.example.panel;

import org.example.AllergyManagementSystem;
import org.example.interFace.BackgroundPanel;
import org.example.interFace.ButtonFactory;

import javax.swing.*;
import java.awt.*;

public class HealthcarePanelHandler {

    /**
     * 显示医保信息面板
     * @param frame 主窗口实例
     */
    public static void showHealthcarePanel(AllergyManagementSystem frame) {
        // 加载背景图片
        Image healthcareBackground = new ImageIcon(HealthcarePanelHandler.class.getResource("/background.jpg")).getImage();
        BackgroundPanel healthcarePanel = new BackgroundPanel(healthcareBackground);
        healthcarePanel.setLayout(new BorderLayout());

        // 创建标签页
        JTabbedPane healthcareTabbedPane = new JTabbedPane();
        healthcareTabbedPane.setFont(new Font("微软雅黑", Font.BOLD, 18));
        healthcareTabbedPane.setForeground(Color.WHITE);
        healthcareTabbedPane.setOpaque(false);

        // 1. 查询余额面板
        JPanel balancePanel = HealthcareBalancePanelFactory.createHealthcareBalancePanel(frame);
        healthcareTabbedPane.addTab("查询余额", balancePanel);

        // 2. 充值面板
        JPanel rechargePanel = HealthcareRechargePanelFactory.createHealthcareRechargePanel(frame);
        healthcareTabbedPane.addTab("医保充值", rechargePanel);

        // 3. 付款面板
        JPanel paymentPanel = HealthcarePaymentPanelFactory.createHealthcarePaymentPanel(frame);
        healthcareTabbedPane.addTab("医保付款", paymentPanel);

        // 返回按钮
        JButton backButton = ButtonFactory.createStyledButton("返回主界面");
        backButton.addActionListener(e -> {
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

        // 主面板布局
        JPanel mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.setOpaque(false);
        mainContentPanel.add(healthcareTabbedPane, BorderLayout.CENTER);
        mainContentPanel.add(backButton, BorderLayout.SOUTH);

        healthcarePanel.add(mainContentPanel, BorderLayout.CENTER);

        // 替换主内容面板的内容
        JPanel mainPanel = (JPanel) frame.getContentPane();
        Component centerComponent = ((BorderLayout) mainPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        mainPanel.remove(centerComponent);
        mainPanel.add(healthcarePanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}

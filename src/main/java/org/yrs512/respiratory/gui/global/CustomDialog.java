package org.yrs512.respiratory.gui.global;


import javax.swing.*;
import java.awt.*;
/**
 * 用户面板
 *
 * @author <a href="mailto:harvey.blocks@outlook.com">Harvey Blocks</a>
 * @version 1.0
 * @date 2025-06-11 00:14
 */
public class CustomDialog extends JDialog {
    public ButtonPainter buttonPainter = new ButtonPainter();
    public CustomDialog(JFrame parent, String message) {
        super(parent, "提示", true);
        setSize(400, 200);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel panel = new BackgroundPanel(new Color(70, 130, 180));
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("<html><div style='text-align:center;'>" + message + "</div></html>");
        label.setFont(new Font("微软雅黑", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton okButton = buttonPainter.createStyledButton("确定");
        okButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(okButton);

        panel.add(label, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }
}

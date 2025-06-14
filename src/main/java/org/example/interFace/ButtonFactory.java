// ButtonFactory.java
package org.example.interFace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonFactory {

    /**
     * 创建统一风格的按钮
     * @param text 按钮文字
     * @return 样式化的 JButton
     */
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
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(0, 150, 255));
                button.repaint();
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(0, 123, 255));
                button.repaint();
            }
        });

        return button;
    }
}

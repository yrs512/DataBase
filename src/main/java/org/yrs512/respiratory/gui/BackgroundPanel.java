package org.yrs512.respiratory.gui;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;
    private float overlayAlpha = 0.3f; // 调整遮罩透明度

    public BackgroundPanel() {
        this((Image) null); // 表示不使用图片
    }

    public BackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        setLayout(new BorderLayout());
    }

    public BackgroundPanel(Color backgroundColor) {
        this.backgroundImage = null;
        this.setOpaque(true);
        this.setBackground(backgroundColor);
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage == null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(getBackground());
            g2.fillRect(0, 0, getWidth(), getHeight());
            return;
        }
        // 1. 绘制原始背景（更高质量渲染）
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR
        );
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // 2. 使用更浅的遮罩（降低alpha值）
        g2d.setColor(new Color(0, 0, 0, (int) (255 * overlayAlpha)));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // 3. 添加微妙的渐变效果增强背景
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(0, 0, 0, 50),
                0, getHeight(), new Color(0, 0, 0, 100)
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    /**
     * 添加方法动态调整遮罩透明度
     */
    public void setOverlayAlpha(float alpha) {
        this.overlayAlpha = Math.min(1, Math.max(0, alpha));
        repaint();
    }
}
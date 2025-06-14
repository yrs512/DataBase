// StylePanelFactory.java
package org.example.interFace;

import javax.swing.*;
import java.awt.*;

public class StylePanelFactory {

    /**
     * 创建带统一边框的透明面板
     * @return 构建好的 JPanel 面板
     */
    public static JPanel createStyledPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 统一内边距
        panel.setOpaque(false);
        return panel;
    }
}

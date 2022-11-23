package org.codeme.thinking.in.java.thread.battle.city;

import javax.swing.*;

/**
 * 坦克大战 示例
 * <p>创建时间: 2022/11/22 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class BattleCityDemo extends JFrame {

    private Battlefield battlefield = null;

    public BattleCityDemo() {
        // 初始化战场面板
        battlefield = new Battlefield();
        battlefield.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        // 将面板放入 Frame 中
        this.add(battlefield);
        // 设置 Frame 属性 （大小、可见性）
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setVisible(true);
        // 当点击窗口 X 时程序退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 添加事件监听器
        this.addKeyListener(battlefield);
        // JFrame在屏幕居中
        this.setLocationRelativeTo(null);
        Thread battleFieldThread = new Thread(battlefield);
        battleFieldThread.start();
    }

    public static void main(String[] args) {
        new BattleCityDemo();
    }

}

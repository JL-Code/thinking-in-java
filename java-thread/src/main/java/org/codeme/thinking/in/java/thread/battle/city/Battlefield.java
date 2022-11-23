package org.codeme.thinking.in.java.thread.battle.city;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 战场
 * <p>创建时间: 2022/11/23 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class Battlefield extends JPanel implements KeyListener, Runnable {

//     -Player player;
//    -List<EnemyTank> enemyTanks;
//    -List<DefaultSell> shells;
//
//    +void drawTank();//绘制坦克
//    +void drawShell();//绘制炮弹

    private Player player = null;
    private List<AbstractTank> tanks = new CopyOnWriteArrayList<>();

    public Battlefield() {

        player = new Player(300, 300);
        System.out.println(getWidth());
        System.out.println(getHeight());
        System.out.println(getX());
        System.out.println(getY());
        System.out.println(getSize());

        EnemyTank tank1 = new EnemyTank(500, 300);
        EnemyTank tank2 = new EnemyTank(200, 389);
        EnemyTank tank3 = new EnemyTank(300, 400);

        tanks.add(player);
        tanks.add(tank1);
        tanks.add(tank2);
        tanks.add(tank3);

        // 初始 AI 坦克
        for (int i = 0; i < tanks.size(); i++) {
            new Thread(tanks.get(i)).start();
        }
    }

    /**
     * 绘制坦克
     *
     * @param g
     */
    public void drawTank(Graphics g) {
        for (AbstractTank tank : tanks) {
            g.setColor(tank.getColor());
            g.fill3DRect(tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight(), false); // 方块当做坦克
        }
    }

    /**
     * 绘制炮弹
     *
     * @param g
     */
    public void drawShell(Graphics g) {
        for (AbstractTank tank : tanks) {
            for (AbstractShell shell : tank.getShells()) {
                if (shell.isLive()) {
                    g.setColor(Color.RED);
                    g.fill3DRect(shell.getX(), shell.getY(), shell.getWidth(), shell.getHeight(), false);
                } else {
                    tank.getShells().remove(shell);
                }
            }
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT); // 填充矩形，默认黑色

        drawTank(g);

        drawShell(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("KeyEvent keyPressed: " + e.getKeyChar());
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.setDirection(Direction.UP);
            player.move();
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            player.setDirection(Direction.DOWN);
            player.move();
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            player.setDirection(Direction.LEFT);
            player.move();
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            player.setDirection(Direction.RIGHT);
            player.move();
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            player.fire();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("KeyEvent keyReleased: " + e.getKeyChar());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("KeyEvent keyTyped: " + e.getKeyChar());
    }

    /**
     * 开启一个线程用于每 500ms 刷新一次画面
     */
    @Override
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(Constants.REFRESH_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

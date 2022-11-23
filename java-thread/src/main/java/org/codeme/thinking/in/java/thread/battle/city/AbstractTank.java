package org.codeme.thinking.in.java.thread.battle.city;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 坦克
 * <p>创建时间: 2022/11/22 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public abstract class AbstractTank implements Runnable {

    public AbstractTank(int x, int y) {
        this(x, y, TankType.AI);
    }

    public AbstractTank(int x, int y, TankType tankType) {
        setX(x);
        setY(y);
        setType(tankType);
    }

    private Color color;
    private int x;
    private int y;
    private int width = 80;
    private int height = 80;
    private int speed = 3;
    private Direction direction = Direction.UP;
    private TankType type;
    private List<AbstractShell> shells = new CopyOnWriteArrayList<>();
    private boolean live;

    /**
     * 开火
     */
    public void fire() {
        System.out.println("开火(一发炮弹就是一个线程)");
        DefaultShell shell = new DefaultShell();
        int space = 10;
        int x = 0;
        int y = 0;
        shell.setLive(true);
        shell.setDirection(getDirection());

        // 根据不同的方向，计算炮弹起始坐标
        switch (direction) {
            case UP:
                x = getX() + (getWidth() - shell.getWidth()) / 2;
                y = getY() - space;
                break;
            case DOWN:
                x = getX() + (getWidth() - shell.getWidth()) / 2;
                y = getY() + getHeight() + space;
                break;
            case LEFT:
                x = getX() - space;
                y = getY() + (getHeight() - shell.getHeight()) / 2;
                break;
            case RIGHT:
                x = getX() + getWidth() + space;
                y = getY() + (getHeight() - shell.getHeight()) / 2;
                break;
        }
        System.out.println("x: " + x + " y: " + y);
        shell.setX(x);
        shell.setY(y);


        Thread shellThread = new Thread(() -> {
            while (shell.isLive()) {
                shell.move();
                try {
                    Thread.sleep(Constants.SHELL_MOVE_SLEEP);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        shellThread.start();

        getShells().add(shell);
    }

    /**
     * 移动
     */
    public void move() {
        int nextX = getX(), nextY = getY();
        switch (getDirection()) {
            case UP:
                nextY = (getY() - getSpeed());
                break;
            case DOWN:
                nextY = (getY() + getSpeed());
                break;
            case LEFT:
                nextX = (getX() - getSpeed());
                break;
            case RIGHT:
                nextX = (getX() + getSpeed());
                break;
        }

        //  TODO: 战场边界保护
        if (nextY <= 0) {
            // 向上越界
            this.setDirection(Direction.DOWN);
            nextY += getSpeed();
        } else if (nextX >= Constants.SCREEN_WIDTH - getWidth()) {
            // 向右越界
            this.setDirection(Direction.LEFT);
            nextX -= getSpeed();
        } else if (nextX <= 0) {
            // 向左越界
            this.setDirection(Direction.RIGHT);
            nextX += getSpeed();
        } else if (nextY >= Constants.SCREEN_HEIGHT - getHeight()) {
            // 向下越界
            this.setDirection(Direction.UP);
            nextY -= getSpeed();
        }

        setX(nextX);
        setY(nextY);

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public List<AbstractShell> getShells() {
        return shells;
    }

    public void setShells(List<AbstractShell> shells) {
        this.shells = shells;
    }

    public TankType getType() {
        return type;
    }

    public void setType(TankType type) {
        this.type = type;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }


    @Override
    public void run() {
    }
}

package org.codeme.thinking.in.java.thread.battle.city;

import java.awt.*;

/**
 * 炮弹
 * <p>创建时间: 2022/11/22 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public abstract class AbstractShell {
//    -Direction direction;
//   -int x;
//   -int y;
//   -int width;
//   -int height;
//   -boolean live;
//   -String owner;
//   +AbstractSell build();//构建炮弹

    private int x;
    private int y;
    private int width = 8;
    private int height = 8;

    private int speed = 20;

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

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private boolean live;
    private Direction direction;

    public void move() {
        switch (direction) {
            case UP:
                setY(getY() - getSpeed());
                break;
            case DOWN:
                setY(getY() + getSpeed());
                break;
            case LEFT:
                setX(getX() - getSpeed());
                break;
            case RIGHT:
                setX(getX() + getSpeed());
                break;
        }
//
//        System.out.printf("炮弹：%s 坐标：x(%d), y(%d) 大小：宽：%d 高：%d \n", Thread.currentThread().getName(),
//                getX(), getY(), getWidth(), getHeight());

        // 边界碰撞检测
        if (getX() >= Constants.SCREEN_WIDTH || getX() <= 0 || getY() >= Constants.SCREEN_HEIGHT
                || getY() <= 0) {
            setLive(false);
            System.out.printf("炮弹: %s 已经消亡... \n", Thread.currentThread().getName());
        }
    }


}

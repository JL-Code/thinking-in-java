package org.codeme.thinking.in.java.thread.battle.city;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * TODO
 * <p>创建时间: 2022/11/23 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class EnemyTank extends AbstractTank {
    EnemyTank(int x, int y) {
        super(x, y);
        setColor(Color.RED);
        setLive(true);
    }

    public void verify(int nextX, int nextY) {
        //  TODO: 战场边界保护
        if (nextY <= 0) {
            // 向上越界
            this.setDirection(Direction.DOWN);
            nextY += getSpeed();
        } else if (nextY >= Constants.SCREEN_WIDTH - getWidth()) {
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
    }

    @Override
    public void run() {
        // AI 坦克可以自由移动
        Random random = new Random();
        while (this.isLive()) {
            // 在某一个方向移动 30 步
            for (int i = 0; i < 30; i++) {
                this.move();
                // 坦克休眠 50ms
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            Direction value = Direction.values()[random.nextInt(Direction.values().length)];
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            System.out.printf("%s, 方向：%s 时间：%s \n", Thread.currentThread().getName(), value, dateTime.format(formatter));
            // 随机改变方向
            this.setDirection(value);

            // 随机开火
            if (random.nextBoolean()) {
//                for (int i = 0; i < random.nextInt(5); i++) {
//                    this.fire();
//                    try {
//                        Thread.sleep(150);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
                this.fire();
            }

        }
    }
}

package org.codeme.thinking.in.java.thread.battle.city;

import java.awt.*;

/**
 * 玩家
 * <p>创建时间: 2022/11/23 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class Player extends AbstractTank {
    Player(int x, int y) {
        super(x, y, TankType.PLAYER);
        setColor(Color.ORANGE);
    }
}

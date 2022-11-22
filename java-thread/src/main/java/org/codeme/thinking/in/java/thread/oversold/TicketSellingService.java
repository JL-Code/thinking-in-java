package org.codeme.thinking.in.java.thread.oversold;

import java.util.ArrayList;
import java.util.List;

/**
 * 收票服务
 * <p>创建时间: 2022/11/22 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class TicketSellingService implements Runnable {
    private final List<Integer> tickets = new ArrayList<>();

    public TicketSellingService() {
        for (int i = 1; i < 50; i++) {
            tickets.add(i);
        }
    }

    /**
     * 售票
     */
    public void sell() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (tickets.size() == 0) {
                    break;
                }
                Integer removed = tickets.remove(tickets.size() - 1);
                System.out.printf("窗口：%s，状态：%s, 卖出了票 %d，还剩：%d 张票 \n", Thread.currentThread().getName(),
                        Thread.currentThread().getState(), removed, tickets.size());
                Thread.sleep(500);
            }
        }
        System.out.println("票已经售完！");
    }

    @Override
    public void run() {
        try {
            sell();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

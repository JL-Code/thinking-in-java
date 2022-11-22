package org.codeme.thinking.in.java.thread.communication;

import java.util.ArrayList;
import java.util.List;

/**
 * 餐厅
 * <p>创建时间: 2022/11/22 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class Restaurant {
    public int getCount() {
        return count;
    }

    public List<String> getOrders() {
        return orders;
    }

    public List<String> getFoods() {
        return foods;
    }

    private int count = 5;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    private boolean loop = true;

    private List<String> orders = new ArrayList<>();
    private List<String> foods = new ArrayList<>();

    /**
     * 点餐
     */
    public void order() throws InterruptedException {
        synchronized (this) {
            orders.add("食物-" + count);
            count--;
            System.out.printf("%s 已经点餐，等待厨师烹饪中...\n", Thread.currentThread().getName());
            // 释放共享资源的锁
            wait();
            // 等待其他方法调用 notify()，以恢复继续执行。
            eat();
        }
    }

    /**
     * 烹饪
     */
    public void cook() throws InterruptedException {
        // 让顾客线程先走
        Thread.sleep(1000);
        while (loop) {
            synchronized (this) {
                if (orders.size() == 0) {
//                    System.out.printf("%s 饭店没有订单... \n", Thread.currentThread().getName());
                    continue;
                }
                String food = orders.remove(0);
                System.out.printf("%s 烹饪：%s 中... \n", Thread.currentThread().getName(), food);
                foods.add(food);
                Thread.sleep(500);
                System.out.printf("%s 烹饪完毕，通知顾客取餐...\n", Thread.currentThread().getName());
                notify();
            }
        }
        System.out.printf("%s 没有订单了，厨师下班\n", Thread.currentThread().getName());
    }

    public void eat() throws InterruptedException {
        if (foods.size() == 0) {
            System.out.printf("%s 饭店没有食物... \n", Thread.currentThread().getName());
            return;
        }
        String food = foods.remove(0);
        System.out.printf("%s 顾客已取餐，正在在吃：%s \n", Thread.currentThread().getName(), food);
        Thread.sleep(500);

        System.out.printf("=================== %s 顾客已吃完 %s. =================== \n", Thread.currentThread().getName(), food);

        if (getCount() > 0) {
            System.out.printf("%s 顾客没有吃饱，继续下单.\n", Thread.currentThread().getName());
            order();
        } else {
            setLoop(false);
            System.out.printf("%s 顾客吃饱了，停止下单.\n", Thread.currentThread().getName());
        }
    }
}

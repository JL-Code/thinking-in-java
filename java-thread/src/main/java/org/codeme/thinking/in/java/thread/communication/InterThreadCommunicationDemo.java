package org.codeme.thinking.in.java.thread.communication;

/**
 * 线程间通信示例
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @ref https://developer.huawei.com/consumer/cn/forum/
 * <p>创建时间: 2022/11/22 </p>
 * @since
 */
public class InterThreadCommunicationDemo {


    public static void main(String[] args) throws InterruptedException {

        Restaurant restaurant = new Restaurant();

        Thread chef = new Thread(() -> {
            try {
                restaurant.cook();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        chef.setName("厨师：");
        Thread chef2 = new Thread(() -> {
            try {
                restaurant.cook();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        chef2.setName("厨师2：");

        Thread customer = new Thread(() -> {
            try {
                restaurant.order();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        customer.setName("顾客：");

        /**
         * 问题现象：
         * Thread-1 已经点餐，等待厨师烹饪中...
         * Thread-2 已经点餐，等待厨师烹饪中...
         * Thread-0 烹饪：食物-5 中...
         * Thread-0 烹饪完毕，通知顾客取餐...
         * Thread-1 顾客在吃食物 食物-5
         * 问题：顾客吃的不是自己点的餐，期望顾客吃自己点的餐
         */

        customer.start();
        chef.start();
        chef2.start();

    }
}

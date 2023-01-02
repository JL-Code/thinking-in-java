package org.codeme.thinking.in.java.design.strategy;

import java.util.Arrays;

/**
 * 策略模式示例
 * <p>创建时间: 2022/11/25 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class StrategyPatternDemo {

    public static void main(String[] args) {
        int[] arr = {10, 5, 2, 3};
        Sorter sorter = new Sorter();
        sorter.sort(arr);

        for (int j : arr) System.out.println(j);
    }
}

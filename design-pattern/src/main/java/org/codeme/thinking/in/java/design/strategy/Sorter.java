package org.codeme.thinking.in.java.design.strategy;

/**
 * 策略模式示例：排序器
 * 用于数组进行按照 Comparator 进行排序
 * <p>创建时间: 2022/11/25 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class Sorter {

    /**
     * int [] 类型的排序算法
     *
     * @param arr
     */
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < arr.length; j++) {
                minPos = arr[j] < arr[minPos] ? j : minPos;
            }
            swap(arr, i, minPos);
        }
    }

    /**
     * 数组元素交换方法
     *
     * @param arr
     * @param i
     * @param j
     */
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

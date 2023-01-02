package org.example.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>创建时间: 2021/3/31 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class MemoryAllocationTest {
    public static void main(String[] args) {

        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;

        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
    }
}

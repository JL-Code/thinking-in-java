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

        List<Test> tests = new ArrayList<>();
        Test test = new Test();
        for (int i = 0; i < 5; i++) {
            test.setName("name:" + i);
            tests.add(test);
        }
    }
}

package org.example.basic.collection;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>创建时间: 2021/10/26 </p>
 * 取两个集合List的交集、补集、并集、差集的几种方式
 * 参考：https://blog.csdn.net/whxjason/article/details/105506787
 * <p>
 * 交集 = intersection
 * 并集 = union
 * 补集 = complement
 * 析取 = disjunction
 * 减去 = subtract
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class CollectionOperationTest {


    @Test
    void testCollectionOperation() {
        // Given
        Set<String> setA = new HashSet<>();
        setA.add("1");
        setA.add("2");
        setA.add("3");
        setA.add("5");
        Set<String> setB = new HashSet<>();
        setB.add("1");
        setB.add("2");
        setB.add("3");
        setB.add("4");
        setB.add("6");
        // When
        // setA、setB 交集

        // 交集
        Collection<String> intersection = CollectionUtils.intersection(setA, setB);
        // 补集
        Collection<String> disjunction = CollectionUtils.disjunction(setA, setB);
        // 并集(默认去重)
        Collection<String> union = CollectionUtils.union(setA, setB);
        // A 的补集(相对于 A、B组成的全集)
        Collection<String> subtractA = CollectionUtils.subtract(setB, setA);
        // B 的补集(相对于 A、B组成的全集)
        Collection<String> subtractB = CollectionUtils.subtract(setA, setB);

//        setA.retainAll(setB);
        // Then
//        System.out.println(setA);
        System.out.println(intersection);
        System.out.println(disjunction);
        System.out.println(union);
        System.out.println(subtractA);
        System.out.println(subtractB);
    }
}
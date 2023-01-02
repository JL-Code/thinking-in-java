package org.codeme.thinking.in.java.design.strategy;

/**
 * TODO
 * <p>创建时间: 2022/11/25 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2);
}

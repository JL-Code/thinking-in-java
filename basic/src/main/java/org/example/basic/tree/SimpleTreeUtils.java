package org.example.basic.tree;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>创建时间: 2021/5/17 </p>
 * <p>
 * 可优化项：
 * 1.
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class SimpleTreeUtils {

    public static List<FunctionModule> buildTree(List<FunctionModule> modules) {
        // 1. 根据给定的条件找出根节点（可能找到多个符合条件的节点）
        FunctionModule rootNode = modules.stream()
                .filter(m -> StringUtils.isEmpty(m.getParentId()))
                .findFirst().get();

        lookupChildren(rootNode, modules);

        return Arrays.asList(rootNode);
    }

    private static void lookupChildren(FunctionModule node, List<FunctionModule> dataSource) {
        // 找到节点的子节点
        List<FunctionModule> children = dataSource
                .stream()
                .filter(m -> m.getParentId().equals(node.getId()))
                .collect(Collectors.toList());

        // 遍历子节点
        children.forEach(c -> {
            // 查找出节点的子节点
            List<FunctionModule> collection = dataSource
                    .stream()
                    .filter(m -> m.getParentId().equals(c.getId()))
                    .collect(Collectors.toList());

            // 当 collection 不为空集合时递归调用 lookupChildren
            if (collection.size() != 0) {
                // 继续调用 lookupChildren 方法查找子节点
                lookupChildren(c, dataSource);
            }

            // 为节点 children 字段赋值
            c.setChildren(collection);
        });

        //  为节点的 children 字段赋值
        node.setChildren(children);
    }
}

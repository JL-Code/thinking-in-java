package org.example.basic.tree.parentnotation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>创建时间: 2021/5/17 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class TreeUtils {

    /**
     * 私有构造函数
     */
    private TreeUtils() {
    }

    // region 构建树

    /**
     * 递归构建一颗树
     *
     * @param list               数据源
     * @param rootPredicate      根节点断言条件
     * @param filiationPredicate 父子节点关系断言条件, BiFunction<ChildNode, ParentNode, Boolean>
     * @param comparator         排序比较器
     * @param <TNode>            节点的类型参数
     * @return 返回一个由树组成的集合（当集合中返回多条记录时，返回的不再是一棵树而是一片森林）。
     */
    public static <TNode extends TreeNode>
    List<TNode> build(List<? extends TNode> list,
                      Function<TNode, Boolean> rootPredicate,
                      BiFunction<TNode, TNode, Boolean> filiationPredicate,
                      Comparator<TNode> comparator) {
        return build(list, rootPredicate, filiationPredicate, comparator, null);
    }

    /**
     * 递归构建一颗树
     *
     * @param list               数据源
     * @param rootPredicate      根节点断言条件
     * @param filiationPredicate 父子节点关系断言条件, BiFunction<ChildNode, ParentNode, Boolean>
     * @param comparator         排序比较器
     * @param consumer           每次循环时调用
     * @param <TNode>            节点的类型参数
     * @return 返回一个由树组成的集合（当集合中返回多条记录时，返回的不再是一棵树而是一片森林）。
     */
    public static <TNode extends TreeNode>
    List<TNode> build(List<? extends TNode> list,
                      Function<TNode, Boolean> rootPredicate,
                      BiFunction<TNode, TNode, Boolean> filiationPredicate,
                      Comparator<TNode> comparator,
                      BiConsumer<TNode, TNode> consumer) {

        List<TNode> nodes = new ArrayList<>();

        List<TNode> rootNodes;

        if (comparator != null) {
            rootNodes = list.stream()
                    .filter(rootPredicate::apply)
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } else {
            rootNodes = list.stream()
                    .filter(rootPredicate::apply)
                    .collect(Collectors.toList());
        }

        rootNodes.forEach(rootNode -> {
            recursion(rootNode, list, filiationPredicate, comparator, consumer);
            nodes.add(rootNode);
        });

        return nodes;
    }


    /**
     * 【采用迭代】
     * 将树节点从层级结构拍平为线性结构
     *
     * @param nodes   具有层级结构的树节点集合
     * @param <TNode> 树节点的数据类型
     * @return 具有线性结构的树节点结合
     */
    public static <TNode extends TreeNode> List<TNode>
    flatten(List<TNode> nodes) {
        List<TNode> flatNodes = new ArrayList<>();

        for (TNode node : nodes) {

            flatNodes.add(node);

            if (node.getChildren() == null) {
                continue;
            }

            List<TNode> dynamicNodes = new ArrayList<>(node.getChildren());

            for (int i = 0; i < dynamicNodes.size(); i++) {
                flatNodes.add(dynamicNodes.get(i));
                if (dynamicNodes.get(i).getChildren() != null) {
                    dynamicNodes.addAll(dynamicNodes.get(i).getChildren());
                }
            }

        }

        return flatNodes;
    }

    // endregion


    // region 辅助方法

    /**
     * 递归调用
     *
     * @param node
     * @param list
     * @param filiationPredicate
     * @param comparator
     * @param consumer
     * @param <TNode>
     */
    private static <TNode extends TreeNode>
    void recursion(TNode node,
                   List<? extends TNode> list,
                   BiFunction<TNode, TNode, Boolean> filiationPredicate,
                   Comparator<TNode> comparator,
                   BiConsumer<TNode, TNode> consumer) {

        // 找到节点的子节点
        List<TNode> children = lookupChildren(node, list, filiationPredicate, comparator);

        // 遍历子节点
        children.forEach(cNode -> {
            // 调用映射函数
            if (consumer != null) {
                consumer.accept(cNode, node);
            }

            // 查找出节点的子节点
            List<TNode> collection = lookupChildren(cNode, list, filiationPredicate, comparator);

            // 当 collection 不为空集合时递归调用 lookupChildren
            if (collection.size() != 0) {
                // 递归调用 lookupChildren 查找当前节点的子节点
                recursion(cNode, list, filiationPredicate, comparator, consumer);
            }

            // 为节点 children 字段赋值
            cNode.setChildren(collection);
        });

        //  为节点的 children 字段赋值
        node.setChildren(children);
    }

    /**
     * 查找子节点集合
     *
     * @param node
     * @param list
     * @param filiationPredicate
     * @param comparator
     * @param <TNode>
     * @return
     */
    private static <TNode extends TreeNode>
    List<TNode> lookupChildren(TNode node,
                               List<? extends TNode> list,
                               BiFunction<TNode, TNode, Boolean> filiationPredicate,
                               Comparator<TNode> comparator) {

        if (comparator == null) {
            return list
                    .stream()
                    .filter(child -> filiationPredicate.apply(child, node))
                    .collect(Collectors.toList());
        }

        return list
                .stream()
                .filter(child -> filiationPredicate.apply(child, node))
                .sorted(comparator)
                .collect(Collectors.toList());
    }
    // endregion

}

package org.example.basic.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>创建时间: 2021/5/17 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public abstract class TreeUtils {

    /**
     * 构建一颗树
     *
     * @param list               数据源
     * @param rootPredicate      根节点断言条件
     * @param filiationPredicate 父子节点关系断言条件, BiFunction<ChildNode, ParentNode, Boolean>
     * @param <TKey>             节点标识的类型参数
     * @param <TNode>            节点的类型参数
     * @return 返回一个由树组成的集合（当集合中返回多条记录时，返回的不再是一棵树而是一片森林）。
     */
    public static <TKey extends Serializable, TNode extends AbstractTreeNode<TKey, TNode>>
    List<TNode> build(List<TNode> list,
                      Function<TNode, Boolean> rootPredicate,
                      BiFunction<TNode, TNode, Boolean> filiationPredicate) {
        List<TNode> treeList = new ArrayList<>();

        List<TNode> rootNodes = list.stream()
                .filter(rootPredicate::apply)
                .collect(Collectors.toList());

        rootNodes.forEach(rootNode -> {
            recursion(rootNode, list, filiationPredicate);
            treeList.add(rootNode);
        });

        return treeList;
    }

    /**
     * 构建一颗树
     *
     * @param list               数据源
     * @param rootPredicate      根节点断言条件
     * @param filiationPredicate 父子节点关系断言条件, BiFunction<ChildNode, ParentNode, Boolean>
     * @param comparator         排序比较器
     * @param <TKey>             节点标识的类型参数
     * @param <TNode>            节点的类型参数
     * @return 返回一个由树组成的集合（当集合中返回多条记录时，返回的不再是一棵树而是一片森林）。
     */
    public static <TKey extends Serializable, TNode extends AbstractTreeNode<TKey, TNode>>
    List<TNode> build(List<TNode> list,
                      Function<TNode, Boolean> rootPredicate,
                      BiFunction<TNode, TNode, Boolean> filiationPredicate,
                      Comparator<? super TNode> comparator) {
        List<TNode> treeList = new ArrayList<>();

        List<TNode> rootNodes = list.stream()
                .filter(rootPredicate::apply)
                .sorted(comparator)
                .collect(Collectors.toList());

        rootNodes.forEach(rootNode -> {
            recursion(rootNode, list, filiationPredicate, comparator);
            treeList.add(rootNode);
        });

        return treeList;
    }

    private static <TKey extends Serializable, TNode extends AbstractTreeNode<TKey, TNode>>
    void recursion(TNode node,
                   List<TNode> list,
                   BiFunction<TNode, TNode, Boolean> filiationPredicate) {
        // 找到节点的子节点
        List<TNode> children = lookupChildren(node, list, filiationPredicate);

        // 遍历子节点
        children.forEach(cNode -> {
            // 查找出节点的子节点

            List<TNode> collection = lookupChildren(cNode, list, filiationPredicate);

            // 当 collection 不为空集合时递归调用 lookupChildren
            if (collection.size() != 0) {
                // 递归调用 lookupChildren 查找当前节点的子节点
                recursion(cNode, list, filiationPredicate);
            }

            // 为节点 children 字段赋值
            cNode.setChildren(collection);
        });

        //  为节点的 children 字段赋值
        node.setChildren(children);
    }

    private static <TKey extends Serializable, TNode extends AbstractTreeNode<TKey, TNode>>
    void recursion(TNode node,
                   List<TNode> list,
                   BiFunction<TNode, TNode, Boolean> filiationPredicate,
                   Comparator<? super TNode> comparator) {
        // 找到节点的子节点
        List<TNode> children = lookupChildren(node, list, filiationPredicate, comparator);

        // 遍历子节点
        children.forEach(cNode -> {
            // 查找出节点的子节点

            List<TNode> collection = lookupChildren(cNode, list, filiationPredicate, comparator);

            // 当 collection 不为空集合时递归调用 lookupChildren
            if (collection.size() != 0) {
                // 递归调用 lookupChildren 查找当前节点的子节点
                recursion(cNode, list, filiationPredicate, comparator);
            }

            // 为节点 children 字段赋值
            cNode.setChildren(collection);
        });

        //  为节点的 children 字段赋值
        node.setChildren(children);
    }

    private static <TKey extends Serializable, TNode extends AbstractTreeNode<TKey, TNode>>
    List<TNode> lookupChildren(TNode node,
                               List<TNode> list,
                               BiFunction<TNode, TNode, Boolean> filiationPredicate) {

        return list
                .stream()
                .filter(child -> filiationPredicate.apply(child, node))
                .collect(Collectors.toList());
    }

    private static <TKey extends Serializable, TNode extends AbstractTreeNode<TKey, TNode>>
    List<TNode> lookupChildren(TNode node,
                               List<TNode> list,
                               BiFunction<TNode, TNode, Boolean> filiationPredicate,
                               Comparator<? super TNode> comparator) {

        return list
                .stream()
                .filter(child -> filiationPredicate.apply(child, node))
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}

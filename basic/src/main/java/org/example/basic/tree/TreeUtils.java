package org.example.basic.tree;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
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


    private TreeUtils() {

    }

    // region 构建树

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
        return build(list, rootPredicate, filiationPredicate, null);
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
        return build(list, rootPredicate, filiationPredicate, comparator, null);
    }

    public static <TKey extends Serializable, TNode extends AbstractTreeNode<TKey, TNode>>
    List<TNode> build(List<TNode> list,
                      Function<TNode, Boolean> rootPredicate,
                      BiFunction<TNode, TNode, Boolean> filiationPredicate,
                      Comparator<? super TNode> comparator,
                      BiConsumer<TNode, TNode> mapConsumer) {
        List<TNode> treeList = new ArrayList<>();

        // TODO: 待优化，查找根也可以纳入通用查找
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
            recursion(rootNode, list, filiationPredicate, comparator, mapConsumer);
            treeList.add(rootNode);
        });


        return treeList;
    }

    // endregion

    /**
     * 将树节点从层级结构拍平为线性结构
     *
     * @param nodes
     * @param <TKey>
     * @param <TNode>
     * @return
     */
    public static <TKey extends Serializable, TNode extends AbstractTreeNode<TKey, TNode>> List<TNode>
    flatten(List<? extends TNode> nodes) {
        List<TNode> flatNodes = new ArrayList<>();

        for (TNode node : nodes) {
            flatNodes.add(node);

            List<TNode> nexts = node.getChildren();

            for (int i = 0; i < nexts.size(); i++) {
                flatNodes.add(nexts.get(i));
                if (nexts.get(i).getChildren() != null) {
                    nexts.addAll(nexts.get(i).getChildren());
                }
            }
        }

        return flatNodes;
    }

    /**
     * @param nodes
     * @param <TKey>
     * @param <TNode>
     * @return
     */
    public static <TKey extends Serializable, TNode extends AbstractTreeNode<TKey, TNode>>
    List<TNode> flatten(List<TNode> nodes, Class<TNode> clazz) {

        List<TNode> result = new ArrayList<>();

        for (TNode node : nodes) {

        }

        return result;
    }

    // region 辅助方法

    private static <TKey extends Serializable, TNode extends AbstractTreeNode<TKey, TNode>>
    void recursion(TNode node,
                   List<TNode> list,
                   BiFunction<TNode, TNode, Boolean> filiationPredicate) {
        recursion(node, list, filiationPredicate, null, null);
    }

    private static <TKey extends Serializable, TNode extends AbstractTreeNode<TKey, TNode>>
    void recursion(TNode node,
                   List<TNode> list,
                   BiFunction<TNode, TNode, Boolean> filiationPredicate,
                   Comparator<? super TNode> comparator) {
        recursion(node, list, filiationPredicate, comparator, null);
    }

    private static <TKey extends Serializable, TNode extends AbstractTreeNode<TKey, TNode>>
    void recursion(TNode node,
                   List<TNode> list,
                   BiFunction<TNode, TNode, Boolean> filiationPredicate,
                   Comparator<? super TNode> comparator,
                   BiConsumer<TNode, TNode> mapConsumer) {

        // 找到节点的子节点
        List<TNode> children = lookupChildren(node, list, filiationPredicate, comparator);

        // 遍历子节点
        children.forEach(cNode -> {
            // 调用映射函数
            if (mapConsumer != null) {
                mapConsumer.accept(cNode, node);
            }

            // 查找出节点的子节点
            List<TNode> collection = lookupChildren(cNode, list, filiationPredicate, comparator);

            // 当 collection 不为空集合时递归调用 lookupChildren
            if (collection.size() != 0) {
                // 递归调用 lookupChildren 查找当前节点的子节点
                recursion(cNode, list, filiationPredicate, comparator, mapConsumer);
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

        return lookupChildren(node, list, filiationPredicate, null);
    }

    private static <TKey extends Serializable, TNode extends AbstractTreeNode<TKey, TNode>>
    List<TNode> lookupChildren(TNode node,
                               List<TNode> list,
                               BiFunction<TNode, TNode, Boolean> filiationPredicate,
                               Comparator<? super TNode> comparator) {

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

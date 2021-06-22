package org.example.basic.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>创建时间: 2021/5/27 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface TreeNode<TKey, T extends TreeNode> {

    TKey getId();

    void setId(TKey id);

    String getName();

    void setName(String name);

    Integer getLevel();

    void setLevel(Integer level);

    List<T> getChildren();

    void setChildren(List<T> nodes);
}

@Data
class Node implements TreeNode<String, Node>, Comparator<Node> {
    private String id;
    private String name;
    private Integer level;
    private List<Node> children;

    @Override
    public int compare(Node o1, Node o2) {
        return o1.getId().compareTo(o2.getId());
    }
}

@EqualsAndHashCode(callSuper = true)
@Data
class SimpleNode extends Node {
    private String simple;
    private String parentId;
    private String code;
}

@EqualsAndHashCode(callSuper = true)
@Data
class SimpleNode2 extends SimpleNode {
    private Integer sn;
}

class TreeUtils2 {

    public static <TNode extends TreeNode>
    List<TNode> build(List<TNode> list,
                      Function<TNode, Boolean> rootPredicate,
                      BiFunction<TNode, TNode, Boolean> filiationPredicate,
                      Comparator<? super TNode> comparator,
                      BiConsumer<TNode, TNode> consumer) {

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
            recursion(rootNode, list, filiationPredicate, comparator, consumer);
            treeList.add(rootNode);
        });


        return treeList;
    }


    // region 辅助方法

    private static <TNode extends TreeNode>
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


    private static <TNode extends TreeNode> List<TNode> lookupChildren(TNode node,
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

}

class Main {

    public static void main(String[] args) {
        SimpleNode2 node1 = new SimpleNode2();
        node1.setId(UUID.randomUUID().toString());
        node1.setParentId("");
        node1.setLevel(1);
        node1.setCode("A");
        node1.setSimple("A");
        node1.setSn(1);

        SimpleNode2 node2 = new SimpleNode2();
        node2.setId(UUID.randomUUID().toString());
        node2.setParentId(node1.getId());
        node2.setLevel(1);
        node2.setSn(3);
        node2.setCode("B");
        node2.setSimple("B");

        SimpleNode2 node3 = new SimpleNode2();
        node3.setId(UUID.randomUUID().toString());
        node3.setParentId(node1.getId());
        node3.setLevel(1);
        node3.setSn(2);
        node3.setCode("C");
        node3.setSimple("C");

        List<SimpleNode2> list = Arrays.asList(node1, node2, node3);

        List<SimpleNode2> nodes = TreeUtils2.build(list,
                n -> StringUtils.isEmpty(n.getParentId()),
                (c, p) -> p.getId().equals(c.getParentId()),
                Comparator.comparing(SimpleNode2::getSn),
                (prev, next) -> {
                    System.out.println(prev instanceof SimpleNode2);
                });

        System.out.println(nodes);
    }
}
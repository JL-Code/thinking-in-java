//package org.example.basic.tree;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.function.BiConsumer;
//import java.util.function.BiFunction;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//public class TreeBuilder {
//
//
//    public static <TNode>
//    List<TNode> build(List<TNode> list,
//                      Function<TNode, Boolean> rootPredicate,
//                      BiFunction<TNode, TNode, Boolean> filiationPredicate,
//                      Comparator<? super TNode> comparator,
//                      BiConsumer<TNode, TNode> mapConsumer) {
//        List<TNode> treeList = new ArrayList<>();
//
//        // TODO: 待优化，查找根也可以纳入通用查找
//        List<TNode> rootNodes;
//
//        if (comparator != null) {
//            rootNodes = list.stream()
//                    .filter(rootPredicate::apply)
//                    .sorted(comparator)
//                    .collect(Collectors.toList());
//        } else {
//            rootNodes = list.stream()
//                    .filter(rootPredicate::apply)
//                    .collect(Collectors.toList());
//        }
//
//        rootNodes.forEach(rootNode -> {
//            recursion(rootNode, list, filiationPredicate, comparator, mapConsumer);
//            treeList.add(rootNode);
//        });
//
//
//        return treeList;
//    }
//
//
//    // region 辅助方法
//
//    private static <TNode>
//    void recursion(TNode node,
//                   List<TNode> list,
//                   BiFunction<TNode, TNode, Boolean> filiationPredicate,
//                   Comparator<? super TNode> comparator,
//                   BiConsumer<TNode, TNode> mapConsumer) {
//
//        // 找到节点的子节点
//        List<TNode> children = lookupChildren(node, list, filiationPredicate, comparator);
//
//        // 遍历子节点
//        children.forEach(cNode -> {
//            // 调用映射函数
//            if (mapConsumer != null) {
//                mapConsumer.accept(cNode, node);
//            }
//
//            // 查找出节点的子节点
//            List<TNode> collection = lookupChildren(cNode, list, filiationPredicate, comparator);
//
//            // 当 collection 不为空集合时递归调用 lookupChildren
//            if (collection.size() != 0) {
//                // 递归调用 lookupChildren 查找当前节点的子节点
//                recursion(cNode, list, filiationPredicate, comparator, mapConsumer);
//            }
//
//            // 为节点 children 字段赋值
//            cNode.setChildren(collection);
//        });
//
//        //  为节点的 children 字段赋值
//        node.setChildren(children);
//    }
//
//
//    private static <TNode> List<TNode> lookupChildren(TNode node,
//                                                      List<TNode> list,
//                                                      BiFunction<TNode, TNode, Boolean> filiationPredicate,
//                                                      Comparator<? super TNode> comparator) {
//
//        if (comparator == null) {
//            return list
//                    .stream()
//                    .filter(child -> filiationPredicate.apply(child, node))
//                    .collect(Collectors.toList());
//        }
//
//        return list
//                .stream()
//                .filter(child -> filiationPredicate.apply(child, node))
//                .sorted(comparator)
//                .collect(Collectors.toList());
//    }
//    // endregion
//}
//

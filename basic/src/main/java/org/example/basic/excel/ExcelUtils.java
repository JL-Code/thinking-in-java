package org.example.basic.excel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * <p>创建时间: 2021/8/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class ExcelUtils {


    /**
     * 数结构 转为 二维数组
     */

    public static List<List<String>> treeToEasyExcelHead(HeadTree<HeadTreeNode> tree) throws JsonProcessingException {

        return depthFirstSearch(tree.getNodes());
    }


    public static HeadTree<HeadTreeNode> buildTree(Integer degree, Integer depth) throws JsonProcessingException {

        Integer childrenDegree = degree;

//        Integer childrenDegree = new Random().nextInt(degree);
//
//        if (childrenDegree == 0) {
//            childrenDegree = degree;
//        }

        HeadTree<HeadTreeNode> tree = new HeadTree<>();
        tree.setDepth(depth);
        tree.setDegree(degree);


        HeadTreeNode root = new HeadTreeNode();
        root.setName("A");
        root.setLevel(1);
        root.setDepth(0);
        root.setDegree(childrenDegree);

        recursionCall(root, childrenDegree, depth);

        tree.setNodes(Collections.singletonList(root));

        return tree;
    }

    private static void recursionCall(HeadTreeNode parentNode, Integer degree, Integer depth) {

        parentNode.setLeaf(true);

        if (degree == 0) {
            // 叶子节点的高度为 0
            parentNode.setHeight(0);
            return;
        }
        if (depth == 0) {
            return;
        }

        parentNode.setDegree(degree);

        for (int i = 0; i < degree; i++) {

            HeadTreeNode node = new HeadTreeNode();

            node.setName(parentNode.getName() + "." + Math.addExact(i, 1));
            node.setLevel(parentNode.getLevel() + 1);
            node.setDepth(parentNode.getDepth() + 1);
            node.setDegree(0);

            if (parentNode.getChildren() == null) {
                parentNode.setChildren(new ArrayList<>());
                parentNode.setLeaf(false);
            }

            parentNode.getChildren().add(node);

        }

        depth--;

        for (HeadTreeNode node : parentNode.getChildren()) {
//            Integer childrenDegree = new Random().nextInt(degree);
            Integer childrenDegree = degree;
            recursionCall(node, childrenDegree, depth);
        }


    }

    private static List<List<String>> depthFirstSearch(List<HeadTreeNode> nodes) throws JsonProcessingException {

        List<List<String>> head = new ArrayList<>();

        List<HeadTreeNode> main = new ArrayList<>();

        List<String> path;

        for (HeadTreeNode node : nodes) {

            main.addAll(node.getChildren());

            while (main.size() > 0) {

                // 从集合中删除第一个元素并返回
                HeadTreeNode removed = main.remove(0);
                path = new ArrayList<>();
                path.add(node.getName());
                path.add(removed.getName());

                List<HeadTreeNode> candidate = new ArrayList<>();

                candidate.addAll(removed.getChildren() == null ? new ArrayList<>() : removed.getChildren());


                if (removed.isLeaf()) {
                    // 路径末尾时将该路径添加到路径集合中。
                    head.add(new ArrayList<>(path));
                    // 叶子节点时，清空最后一个节点为第二条路径准备前置路径数据。
                    path.remove(path.size() - 1);
                    continue;
                }

                while (candidate.size() > 0) {
                    HeadTreeNode candidateRemoved = candidate.remove(0);
                    path.add(candidateRemoved.getName());
                    if (candidateRemoved.isLeaf()) {
                        // 路径末尾时将该路径添加到路径集合中。
                        head.add(new ArrayList<>(path));
                        // 叶子节点时，清空最后一个节点为第二条路径准备前置路径数据。
                        path.remove(path.size() - 1);
                    }
                    else{

                    }
                }

            }

        }

        System.out.println(new ObjectMapper().writeValueAsString(nodes));
        System.out.println(new ObjectMapper().writeValueAsString(head));

        return head;
    }


}

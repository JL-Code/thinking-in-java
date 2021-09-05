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

//        Integer childrenDegree = degree;

        Integer childrenDegree = new Random().nextInt(degree);

        if (childrenDegree == 0) {
            childrenDegree = degree;
        }

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
        List<String> fragments = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            HeadTreeNode node = nodes.get(i);
            listPathViaDfs(node, fragments, head);
        }

        System.out.println(String.format("head: %s", new ObjectMapper().writeValueAsString(head)));

        return head;
    }

    /**
     * 通过深度优先搜索得到路径信息
     *
     * @param node          节点
     * @param pathFragments 单个路径的片段集合
     * @param paths         路径集合
     */
    public static void listPathViaDfs(HeadTreeNode node, List<String> pathFragments, List<List<String>> paths) {

        boolean lastOne = false;

        pathFragments.add(node.getName());

        // 递归出口
        if (node.getChildren() == null || node.getChildren().size() == 0) {
            // 单条路径遍历完成
            paths.add(new ArrayList<>(pathFragments));
            System.out.println(String.format("node name: %s", node.getName()));
            System.out.println(String.format("path remove before: %s", pathFragments));
            pathFragments.remove(pathFragments.size() - 1);
            System.out.println(String.format("lastOne: %s", lastOne));
            if (lastOne) {
                pathFragments.remove(pathFragments.size() - 1);
            }
            System.out.println(String.format("path remove after: %s", pathFragments));
            System.out.println("============================分隔线============================");
            return;
        }

        for (int i = 0; i < node.getChildren().size(); i++) {
            HeadTreeNode child = node.getChildren().get(i);
            // 方法入栈，继续递归遍历子集节点直到找到叶子节点
            listPathViaDfs(child, pathFragments, paths);
            lastOne = i == node.getChildren().size() - 1;
            // 当节点是父节点中最后一个子节点时，从路径片段中移除父级路径片段
            if (lastOne) {
                pathFragments.remove(pathFragments.size() - 1);
            }
        }
    }
}

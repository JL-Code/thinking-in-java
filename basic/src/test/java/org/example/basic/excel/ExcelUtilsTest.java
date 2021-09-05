package org.example.basic.excel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2021/8/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class ExcelUtilsTest {

    @Test
    void treeToEasyExcelHead() throws JsonProcessingException {
        Integer degree = 2;
        Integer depth = 3;

        HeadTree<HeadTreeNode> headTree = ExcelUtils.buildTree(degree, depth);
        List<List<String>> head = ExcelUtils.treeToEasyExcelHead(headTree);

        System.out.println(String.format("path size: %s", head.size()));

//        assertEquals(Math.pow(degree, depth), head.size());
    }

    @Test
    void buildTree() {
    }

    @Test
    void listPathViaDfs() throws JsonProcessingException {
        List<String> pathFragments = new ArrayList<>();
        List<List<String>> paths = new ArrayList<>();

//        List<HeadTreeNode> nodes = buildTreeNodes();
        List<HeadTreeNode> nodes = buildTreeNodes2();

        HeadTreeNode root = nodes.get(0);

        ExcelUtils.listPathViaDfs(root, pathFragments, paths);

        System.out.println(new ObjectMapper().writeValueAsString(paths));

        assertEquals(8, paths.size());
    }

    @Test
    void listPathViaDfs_dynamicBuildTree() throws JsonProcessingException {
        Integer degree = 5;
        Integer depth = 4;

        HeadTree<HeadTreeNode> headTree = ExcelUtils.buildTree(degree, depth);

        List<String> pathFragments = new ArrayList<>();
        List<List<String>> paths = new ArrayList<>();
        List<HeadTreeNode> nodes = headTree.getNodes();

        System.out.println(new ObjectMapper().writeValueAsString(nodes));

        HeadTreeNode root = nodes.get(0);

        ExcelUtils.listPathViaDfs(root, pathFragments, paths);

        System.out.println(new ObjectMapper().writeValueAsString(paths));

//        assertEquals(Math.pow(degree, depth), paths.size());
//
//        for (List<String> path : paths) {
//            assertEquals(depth + 1, path.size());
//        }
    }

    private List<HeadTreeNode> buildTreeNodes() {
        List<HeadTreeNode> nodes = new ArrayList<>();

        HeadTreeNode node1 = new HeadTreeNode();
        HeadTreeNode node2 = new HeadTreeNode();
        HeadTreeNode node3 = new HeadTreeNode();
        HeadTreeNode node4 = new HeadTreeNode();

        HeadTreeNode node5 = new HeadTreeNode();
        HeadTreeNode node6 = new HeadTreeNode();

        HeadTreeNode node7 = new HeadTreeNode();

        HeadTreeNode node8 = new HeadTreeNode();
        HeadTreeNode node9 = new HeadTreeNode();
        HeadTreeNode node10 = new HeadTreeNode();

        HeadTreeNode node11 = new HeadTreeNode();
        HeadTreeNode node12 = new HeadTreeNode();

        node1.setName("A");
        node2.setName("A.1");
        node3.setName("A.2");
        node4.setName("A.3");

        node5.setName("A.1.1");
        node6.setName("A.1.2");

        node7.setName("A.2.1");

        node8.setName("A.3.1");
        node9.setName("A.3.1.1");
        node10.setName("A.3.1.2");

        node11.setName("A.3.1.2.1");
        node12.setName("A.3.1.2.2");


        node1.setChildren(Arrays.asList(node2, node3, node4));
        node2.setChildren(Arrays.asList(node5, node6));
        node3.setChildren(Collections.singletonList(node7));
        node4.setChildren(Collections.singletonList(node8));
        node8.setChildren(Arrays.asList(node9, node10));
        node10.setChildren(Arrays.asList(node11, node12));

        nodes.add(node1);

        return nodes;
    }

    private List<HeadTreeNode> buildTreeNodes2() {
        List<HeadTreeNode> nodes = new ArrayList<>();

        HeadTreeNode node1 = new HeadTreeNode();
        HeadTreeNode node2 = new HeadTreeNode();
        HeadTreeNode node3 = new HeadTreeNode();
        HeadTreeNode node4 = new HeadTreeNode();

        HeadTreeNode node5 = new HeadTreeNode();
        HeadTreeNode node6 = new HeadTreeNode();

        HeadTreeNode node7 = new HeadTreeNode();

        HeadTreeNode node8 = new HeadTreeNode();
        HeadTreeNode node9 = new HeadTreeNode();
        HeadTreeNode node10 = new HeadTreeNode();

        HeadTreeNode node11 = new HeadTreeNode();
        HeadTreeNode node12 = new HeadTreeNode();
        HeadTreeNode node13 = new HeadTreeNode();
        HeadTreeNode node14 = new HeadTreeNode();
        HeadTreeNode node15 = new HeadTreeNode();


        node1.setName("A");
        node2.setName("A.1");
        node3.setName("A.2");


        node5.setName("A.1.1");
        node6.setName("A.1.2");

        node7.setName("A.2.1");
        node4.setName("A.2.2");

        node8.setName("A.1.1.1");
        node9.setName("A.1.1.2");

        node10.setName("A.1.2.1");
        node11.setName("A.1.2.2");

        node12.setName("A.2.1.1");
        node13.setName("A.2.1.2");

        node14.setName("A.2.2.1");
        node15.setName("A.2.2.2");

        node1.setChildren(Arrays.asList(node2, node3));

        node2.setChildren(Arrays.asList(node5, node6));
        node3.setChildren(Arrays.asList(node7, node4));

        node4.setChildren(Arrays.asList(node14, node15));
        node7.setChildren(Arrays.asList(node12, node13));

        node5.setChildren(Arrays.asList(node8, node9));
        node6.setChildren(Arrays.asList(node10, node11));

        nodes.add(node1);

        return nodes;
    }
}

package org.example.basic.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

/**
 * <p>创建时间: 2021/6/19 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SimpleTreeNode extends TreeNode {
    private String remark;

    public static void main(String[] args) {

        SimpleTreeNode node = new SimpleTreeNode();
        node.setId("1");
        SimpleTreeNode node2 = new SimpleTreeNode();
        node2.setId("2");

        List<SimpleTreeNode> nodes = Arrays.asList(node, node2);

//        TreeUtils.recursion1(node, nodes, (c, p) -> c.getInnerParentId().equals(p.getId()));
    }
}

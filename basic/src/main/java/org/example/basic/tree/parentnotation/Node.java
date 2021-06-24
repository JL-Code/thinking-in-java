package org.example.basic.tree.parentnotation;

import lombok.Data;

import java.util.Comparator;
import java.util.List;

@Data
public class Node implements TreeNode<String, Node>, Comparator<Node> {
    private String id;
    private String name;
    private Integer level;
    private List<Node> children;

    @Override
    public int compare(Node o1, Node o2) {
        return o1.getId().compareTo(o2.getId());
    }
}

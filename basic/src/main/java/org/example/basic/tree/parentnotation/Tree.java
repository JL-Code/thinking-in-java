package org.example.basic.tree.parentnotation;

import lombok.Data;

import java.util.List;


@Data
public class Tree {
    private List<Node> nodes;
    private int count;

    public void build() {

    /**
     *         数组索引	data	parent
     *         0	A	-1
     *         1	B	0
     *         2	C	0
     *         3	D	0
     *         4	E	1
     *         5	F	3
     *         6	G	3
     *         7	H	4
     *         8	I	4
     *         9	J	4
     *         10	K	6
     *  由此可见，只要用一个数组节点来保存树里的每个节点，并让每个节点都记录它的父节点在数组中的索引位置即可
     */

        Node<String> nodeA = new Node<>("A", -1);

    }

    public void getDepth() {

    }

    public void getRoot() {

    }

    public void getParent(Node node) {

    }
}

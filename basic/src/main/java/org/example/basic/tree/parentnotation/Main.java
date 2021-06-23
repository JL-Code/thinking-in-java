package org.example.basic.tree.parentnotation;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class Main {

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

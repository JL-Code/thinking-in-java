package org.example.basic.tree.parentnotation;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SimpleNode2 extends SimpleNode {
    private Integer sn;
}

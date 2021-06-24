package org.example.basic.tree.parentnotation;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SimpleNode extends Node {
    private String simple;
    private String parentId;
    private String code;
}

package org.example.basic.tree.parentnotation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Node<T> {
    private T data;
    private int parent;
}

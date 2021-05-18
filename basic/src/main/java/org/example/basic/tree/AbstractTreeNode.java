package org.example.basic.tree;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>创建时间: 2021/5/17 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
public abstract class AbstractTreeNode<TKey extends Serializable, T extends AbstractTreeNode> {
    private TKey id;
    private String name;
    private List<T> children;
}

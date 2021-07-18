package org.example.basic.tree.parentnotation;

import java.util.List;

/**
 * 树节点接口
 *
 * <p>创建时间: 2021/5/27 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface TreeNode<TKey, T extends TreeNode> {

    TKey getId();

    void setId(TKey id);

    String getName();

    void setName(String name);

    Integer getLevel();

    void setLevel(Integer level);

    List<T> getChildren();

    void setChildren(List<T> nodes);
}


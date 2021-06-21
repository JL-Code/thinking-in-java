package org.example.basic.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>创建时间: 2021/5/27 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TreeNode extends AbstractTreeNode<String, TreeNode> {
    private Integer serialNumber;
}

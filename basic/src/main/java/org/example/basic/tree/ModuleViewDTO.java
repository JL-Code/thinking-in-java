package org.example.basic.tree;

import lombok.Data;

/**
 * <p>创建时间: 2021/5/17 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
public class ModuleViewDTO extends AbstractTreeNode<String, ModuleViewDTO> {
    private String parentId;
    private String status;
    private String category;
    private Boolean defaulted;
    private Integer hierarchy;
    private String remark;
}

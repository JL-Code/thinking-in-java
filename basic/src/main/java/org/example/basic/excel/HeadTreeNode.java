package org.example.basic.excel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * <p>创建时间: 2021/8/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
public class HeadTreeNode {

    public HeadTreeNode() {
        this.height = -1;
    }

    private Integer level;
    private Integer height;
    private Integer depth;
    private Integer degree;
    private String name;
    private boolean isLeaf;

    /**
     * 序列化时，忽略值为 NULL 的字段。
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<HeadTreeNode> children;
}

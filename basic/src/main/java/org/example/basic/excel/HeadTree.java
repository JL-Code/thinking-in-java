package org.example.basic.excel;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>创建时间: 2021/8/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
public class HeadTree<T> {

    public HeadTree() {
        this.level = 0;
        this.height = 0;
        this.depth = 0;
        this.degree = 0;
        this.nodes = new ArrayList<>();
    }

    private Integer level;
    private Integer height;
    private Integer depth;
    private Integer degree;
    private List<T> nodes;
}

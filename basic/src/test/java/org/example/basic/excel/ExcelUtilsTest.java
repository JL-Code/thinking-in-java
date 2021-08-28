package org.example.basic.excel;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2021/8/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class ExcelUtilsTest {

    @Test
    void treeToEasyExcelHead() throws JsonProcessingException {
        HeadTree<HeadTreeNode> headTree = ExcelUtils.buildTree(2, 2);
        ExcelUtils.treeToEasyExcelHead(headTree);
    }

    @Test
    void buildTree() {
    }
}
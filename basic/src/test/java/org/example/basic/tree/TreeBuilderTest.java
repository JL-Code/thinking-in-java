package org.example.basic.tree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>创建时间: 2021/5/17 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class TreeBuilderTest {

    List<TestTreeNode> dataSource = new ArrayList<>();

    @Data
    public class TestTreeNode extends AbstractTreeNode<String, TestTreeNode> {
        private String parentId;
        private String status;
        private String category;
        private Boolean defaulted;
        private Integer level;
        private String remark;
    }

    @BeforeEach
    public void initData() {

        TestTreeNode module1 = new TestTreeNode();
        module1.setId("A");
        module1.setParentId("");
        module1.setName("root");

        TestTreeNode module_a_1 = new TestTreeNode();
        module_a_1.setId("A1");
        module_a_1.setParentId("A");
        module_a_1.setName("A1");

        TestTreeNode module_a_1_1 = new TestTreeNode();
        module_a_1_1.setId("A1.1");
        module_a_1_1.setParentId("A1");
        module_a_1_1.setName("A1.1");

        TestTreeNode module_a_2 = new TestTreeNode();
        module_a_2.setId("A2");
        module_a_2.setParentId("A");
        module_a_2.setName("A2");

        dataSource.add(module1);
        dataSource.add(module_a_1);
        dataSource.add(module_a_1_1);
        dataSource.add(module_a_2);
    }

    @Test
    void build() throws JsonProcessingException {


        List<TestTreeNode> treeList = OldTreeUtils.build(dataSource,
                node -> StringUtils.isEmpty(node.getParentId()),
                (cnode, pnode) -> cnode.getParentId().equals(pnode.getId()), null,
                (cnode, pnode) -> {
                    if (pnode.getLevel() == null) {
                        pnode.setLevel(0);
                    }
                    cnode.setLevel(pnode.getLevel() + 1);
                });

        String json = new ObjectMapper().writeValueAsString(treeList);

        System.out.println(new ObjectMapper().writeValueAsString(dataSource));

        System.out.println(json);
    }

    @Test
    void flatten() {
        List<TestTreeNode> treeList = OldTreeUtils.build(dataSource,
                node -> StringUtils.isEmpty(node.getParentId()),
                (cnode, pnode) -> cnode.getParentId().equals(pnode.getId()),
                null,
                (cnode, pnode) -> {
                    if (pnode.getLevel() == null) {
                        pnode.setLevel(0);
                    }
                    cnode.setLevel(pnode.getLevel() + 1);
                });

        List<TestTreeNode> flatten = OldTreeUtils.flatten(treeList);

        assertEquals(4, flatten.size());
    }
}
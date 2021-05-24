package org.example.basic.tree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.deploy.panel.TreeBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>创建时间: 2021/5/17 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class TreeBuilderTest {

    @Test
    void build() throws JsonProcessingException {
        //Given
        List<ModuleViewDTO> dataSource = new ArrayList<>();

        ModuleViewDTO module1 = new ModuleViewDTO();
        module1.setId("A");
        module1.setParentId("");
        module1.setName("root");

        ModuleViewDTO module_a_1 = new ModuleViewDTO();
        module_a_1.setId("A1");
        module_a_1.setParentId("A");
        module_a_1.setName("A1");

        ModuleViewDTO module_a_1_1 = new ModuleViewDTO();
        module_a_1_1.setId("A1.1");
        module_a_1_1.setParentId("A1");
        module_a_1_1.setName("A1.1");

        ModuleViewDTO module_a_2 = new ModuleViewDTO();
        module_a_2.setId("A2");
        module_a_2.setParentId("A");
        module_a_2.setName("A2");

        dataSource.add(module1);
        dataSource.add(module_a_1);
        dataSource.add(module_a_1_1);
        dataSource.add(module_a_2);

        List<ModuleViewDTO> treeList = TreeUtils.build(dataSource,
                node -> StringUtils.isEmpty(node.getParentId()),
                (cnode, pnode) -> cnode.getParentId().equals(pnode.getId()));

        String json = new ObjectMapper().writeValueAsString(treeList);

        System.out.println(json);
    }
}
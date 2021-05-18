package org.example.basic.tree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>创建时间: 2021/5/17 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class SimpleTreeUtilsTest {

    @Test
    void buildTree() throws JsonProcessingException {
        //Given
        List<FunctionModule> dataSource = new ArrayList<>();

        FunctionModule module1 = new FunctionModule();
        module1.setId("A");
        module1.setParentId("");
        module1.setName("root");

        FunctionModule module_a_1 = new FunctionModule();
        module_a_1.setId("A1");
        module_a_1.setParentId("A");
        module_a_1.setName("A1");
        FunctionModule module_a_1_1 = new FunctionModule();
        module_a_1_1.setId("A1.1");
        module_a_1_1.setParentId("A1");
        module_a_1_1.setName("A1.1");

        FunctionModule module_a_2 = new FunctionModule();
        module_a_2.setId("A2");
        module_a_2.setParentId("A");
        module_a_2.setName("A2");

        dataSource.add(module1);
        dataSource.add(module_a_1);
        dataSource.add(module_a_1_1);
        dataSource.add(module_a_2);

        List<FunctionModule> tree = SimpleTreeUtils.buildTree(dataSource);
        String json = new ObjectMapper().writeValueAsString(tree);
        System.out.println(json);

    }
}
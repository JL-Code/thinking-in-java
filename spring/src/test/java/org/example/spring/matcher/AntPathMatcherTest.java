package org.example.spring.matcher;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.util.AntPathMatcher;

import java.util.HashMap;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/8 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class AntPathMatcherTest {

    @Test
    public void testMatches() {
//        new AntPathMatcher().match("/{corporationName}_{vendorName}/{shopName}_with_{retailer_name}?g={goodsId}","/corporation 1 _ vendor 2/shop 1_with_retailer 2?g=good1")
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean matched = antPathMatcher.match("/api/org/organization/{orgId}", "/api/org/organization/999");

        Assertions.assertEquals(true, matched);
    }

    /**
     * 测试 URI 模板变量提取
     */
    @Test
    public void testExtractUriTemplateVariables() {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        HashMap<String, String> map = (HashMap<String, String>) antPathMatcher.extractUriTemplateVariables("/api/org/organization/{orgId}",
                "/api/org" +
                        "/organization" +
                        "/999");
        System.out.println(map);
        Assertions.assertEquals(1, map.size());
    }

    @ParameterizedTest
    @CsvSource({
            "/api/org/Organization/{orgId},/api/org/organizAtion/999",
            "/api/org/organization/,/api/org/organization/"})
    public void testMutilParamMathces(String input, String expected) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean matched = antPathMatcher.match(input, expected);
        Assertions.assertEquals(true, matched);
    }
}
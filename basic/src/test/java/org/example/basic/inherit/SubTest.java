package org.example.basic.inherit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2020/7/20 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class SubTest {
    @Test
    public void testSubOverrideBaseMethodByCallRule() {

        Base base = new Sub();

        base.outputClassName();
    }
}
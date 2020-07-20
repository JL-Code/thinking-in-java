package org.example.basic.inner;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LocalInnerClassParentTest {
    @Test
    public void testLocalInnerClassParent() {
        LocalInnerClassParent parent = new LocalInnerClassParent();
        Object build = parent.build(true);
        Assertions.assertNotNull(build);
    }
}
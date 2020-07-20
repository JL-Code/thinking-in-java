package org.example.basic.inner.address;


import org.junit.jupiter.api.Test;

class OuterTest {

    @Test
    public void testLocalInnerClass() {

        Outer outer = new Outer();
        outer.getAddress().getProvince();
    }

}
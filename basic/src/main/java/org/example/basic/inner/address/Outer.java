package org.example.basic.inner.address;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class Outer {
    private String outerClassName = "outer";
    private static String outerStaticParam = "静态参数";

    public IAddress getAddress() {
        // 局部内部类（ getAddress 方法内）
        IAddress address = new IAddress() {
            private String province = "重庆市";
            private String city = "重庆市";
            private String county = "南岸区";
            public String getProvince() {
                return province;
            }
            public String getCity() {
                return city;
            }
            public String getCounty() {
                return county;
            }
            @Override
            public void testParentVariableAccess() {
                System.out.println(outerClassName);
                System.out.println(outerStaticParam);
            }
        };
        return address;
    }
}
package org.example.basic.inner.address;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class Outer {
    public IAddress getAddress() {

        // 局部内部类（ getAddress 方法内）
        class Address implements IAddress {
            private String province;
            private String city;
            private String county;
            public Address(String province, String city, String county) {
                this.province = province;
                this.city = city;
                this.county = county;
            }
            public String getProvince() {
                return province;
            }
            public String getCity() {
                return city;
            }
            public String getCounty() {
                return county;
            }
        }
        return new Address("北京", "北京", "丰台区");

    }

    public IAddress getAddress(boolean _default) {
        if (_default) {
            class Address1 implements IAddress {
                private String province;
                private String city;
                private String county;
                public Address1(String province, String city, String county) {
                    this.province = province;
                    this.city = city;
                    this.county = county;
                }
                public String getProvince() {
                    return province;
                }
                public String getCity() {
                    return city;
                }
                public String getCounty() {
                    return county;
                }
            }
            return new Address1("重庆","重庆","南岸区");
        }
        return null;
    }

}

package org.example.basic.inner.address;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface IAddress {
    public String getProvince();

    public String getCity();

    public String getCounty();

    class AnonymityClassTest {
        private String province = "北京";
        private String city = "北京";
        private String county = "石景山区";

        void test() {
            IAddress address = new IAddress() {
                public String getProvince() {
                    return province;
                }

                public String getCity() {
                    return city;
                }

                public String getCounty() {
                    return county;
                }
            };

            System.out.println(address.getCity());
        }
    }
}

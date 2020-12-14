package org.example.springboot.property;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>创建时间: 2020/8/1 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = OAuth2ClientProperties.class)
//@TestPropertySource(value = "classpath:application.yml")
@SpringBootTest
class OAuth2ClientPropertiesTest {

    @Autowired
    private DingtalkAppProperties dingtalkAppProperties;
    @Autowired
    private WeWorkAppProperties weWorkAppProperties;
    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Test
    public void testOAuth2ClientPropertiesViaYmlFill() {
        System.out.println(dingtalkAppProperties);
        System.out.println(weWorkAppProperties);
        Assertions.assertTrue(dingtalkAppProperties.getCorpId() != null);
        Assertions.assertTrue(weWorkAppProperties.getCorpId() != null);
        Assertions.assertTrue(oAuth2ClientProperties.getClient() != null);
    }
}
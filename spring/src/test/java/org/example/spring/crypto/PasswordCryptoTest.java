package org.example.spring.crypto;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2020/8/3 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class PasswordCryptoTest {

    @Test
    public void testEncrypt() {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String encrypted = bcryptPasswordEncoder.encode("cdb");
        System.out.println(encrypted);
    }
}
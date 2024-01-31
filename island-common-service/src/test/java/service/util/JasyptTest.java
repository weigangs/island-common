package service.util;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author nicholas
 * @date 2023/05/04 21:27
 */
public class JasyptTest {
    private BasicTextEncryptor basicTextEncryptor = null;
    private String SECRET = "";
    private String encryptedText = null;

    @BeforeEach
    public void initEle() {
        basicTextEncryptor = new BasicTextEncryptor();
        SECRET = "";
        basicTextEncryptor.setPassword(SECRET);
    }

    @Test
    public void encryptText(){
        String plainText = "";
        encryptedText = basicTextEncryptor.encrypt(plainText);
        System.out.println(encryptedText);
    }

    @Test
    public void decryptText(){
        String encryptText = "/2QAK/J/5K8J39XRJDf0/0BIdxQEazIDCu2oVD9sNGwZxzUHXV/fngaazttBoT/qsxNv3M6VM2xvY1FTNVnlZXhwqRSp+Ms76IXKu80xK0s=";
        String plainText = basicTextEncryptor.decrypt(encryptText);
        System.out.println(plainText);
    }
}

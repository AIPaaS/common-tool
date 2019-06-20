package com.ai.paas.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 证书工具类
 * 
 * @author douxiaofeng
 *
 */
public class CertUtil {
    private static final Logger log = LoggerFactory.getLogger(CertUtil.class);

    private static final String PKCS12 = "PKCS12";
    private static final String X509 = "X.509";
    private static final String SHA1RSA = "SHA1withRSA";

    private static PrivateKey loadPrivateKey(String pfxFile, String pfxPwd, String privateKeyPwd) {
        try (InputStream bis = new FileInputStream(pfxFile)) {
            KeyStore store = KeyStore.getInstance(PKCS12);
            store.load(bis, pfxPwd.toCharArray());
            String alias = store.aliases().nextElement();
            return (PrivateKey) store.getKey(alias, privateKeyPwd.toCharArray());
        } catch (Exception e) {
            log.error("load private key failed.", e);
            return null;
        }
    }

    private static PublicKey loadPublicKey(String cerFile) {
        try (InputStream bis = new FileInputStream(cerFile)) {
            CertificateFactory cf = CertificateFactory.getInstance(X509);
            Certificate cert = cf.generateCertificate(bis);
            return cert.getPublicKey();
        } catch (Exception e) {
            log.error("load public key failed.", e);
            return null;
        }
    }

    /**
     * 对普通文本文字使用私钥进行签名
     * 
     * @param plainText     要签名的文本
     * @param charset       私钥编码
     * @param pfxFile       私钥文件
     * @param pfxPwd        keystore的密码
     * @param privateKeyPwd 私钥密码
     * @return
     */
    public static String sign(String plainText, String charset, String pfxFile, String pfxPwd, String privateKeyPwd) {
        try {
            Signature signature = Signature.getInstance(SHA1RSA);
            signature.initSign(loadPrivateKey(pfxFile, pfxPwd, privateKeyPwd));
            if (charset != null && charset.length() > 0) {
                signature.update(plainText.getBytes(charset));
            } else {
                signature.update(plainText.getBytes());
            }
            byte[] sign = signature.sign();
            return new String(AsciiUtil.hex2Ascii(sign));
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 对普通文本文字使用私钥进行签名
     * 
     * @param plainText     要签名文本
     * @param pfxFile       私钥文件
     * @param pfxPwd        私钥密码
     * @param privateKeyPwd
     * @return
     */
    public static String sign(String plainText, String pfxFile, String pfxPwd, String privateKeyPwd) {
        return sign(plainText, null, pfxFile, pfxPwd, privateKeyPwd);
    }

    /**
     * 验证签名是否正确
     * 
     * @param sign      签名后的文本
     * @param plainText 普通文本
     * @param cerFile   公钥证书文件
     * @return
     */
    public boolean verifySignature(String sign, String plainText, String cerFile) {
        try {
            PublicKey key = loadPublicKey(cerFile);
            byte[] signArray = AsciiUtil.ascii2Hex(sign.getBytes());
            Signature signature = Signature.getInstance(SHA1RSA);
            signature.initVerify(key);
            signature.update(plainText.getBytes(StandardCharsets.UTF_8));
            return signature.verify(signArray);
        } catch (Exception e) {
            log.error("", e);
        }
        return false;
    }

    /**
     * 验证签名是否正确
     * 
     * @param sign
     * @param plainText
     * @param cerFile
     * @param charset
     * @return
     */
    public static boolean verifySignature(String sign, String plainText, String cerFile, String charset) {
        Assert.notNull(sign, plainText, cerFile);
        try {
            PublicKey key = loadPublicKey(cerFile);
            byte[] signArray = AsciiUtil.ascii2Hex(sign.getBytes());
            Signature signature = Signature.getInstance(SHA1RSA);
            signature.initVerify(key);
            signature.update(plainText.getBytes(charset));
            return signature.verify(signArray);
        } catch (Exception e) {
            log.error("", e);
        }
        return false;
    }

    public static void main(String[] args) {
        String strSendData = "test date";
        String sign = CertUtil.sign(strSendData, "GBK", "/Volumes/HD/Downloads/weg_private_key.pfx", "aipay123456",
                "aipay654321");
        log.info(sign);
        boolean ret = CertUtil.verifySignature(sign, strSendData, "/Volumes/HD/Downloads/weg_public_key.cer", "GBK");
        log.info("{}", ret);
    }
}

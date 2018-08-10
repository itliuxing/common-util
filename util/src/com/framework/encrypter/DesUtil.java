package com.framework.encrypter;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/****
 * *
 * 类名称：		DesUtil   
 * 类描述：   		DES 加密		只能用于同一平台使用，因为内部调用的jdk解码器可能会不相同
 * 创建人：		liuxing   
 * 创建时间：		2014-11-26 下午5:34:15   
 * 修改人：		liuxing   编撰
 * 修改时间：		2014-11-26 下午5:34:15   
 * 修改备注：   
 * @version
 */
public class DesUtil {

	private final static String DES = "DES";
	 
    public static void main(String[] args) throws Exception {
        String data = "123 456";
        String key = "123qweqeqw" ; //KeyUtil.genKeyUUID() ;
        String encodeed = encrypt( data,key ) ;
        System.err.println( encodeed );		//加密后 --- 加密时已经有锁锁住
        encodeed = encrypt( encodeed ,key ) ;
        System.err.println( encodeed );		//加密后,加密后再加一层 --- 加密时已经有锁锁住
        String codeing = decrypt(encodeed, key ) ;
        System.err.println( codeing );		//有锁时 --- 解密后，没有锁或者锁错误,任务数据执行错误
        codeing =decrypt(  codeing,key ) ;
        System.err.println( codeing );		//解锁时再解一层 --- 解密后，没有锁或者锁错误,任务数据执行错误
    }
     
    /**
     * Description 根据键值进行加密
     * @param data 
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        @SuppressWarnings("restriction")
		String strs = new BASE64Encoder().encode(bt);
        return strs;
    }
 
    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws IOException,
            Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf,key.getBytes());
        return new String(bt);
    }
 
    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom(); 
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key); 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks); 
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES); 
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }
     
     
    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom(); 
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key); 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }
	
}

package com.bao;

import java.security.Key; 
import javax.crypto.Cipher; 
import javax.crypto.spec.SecretKeySpec; 
 

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptDecrypt {
	
	public EncryptDecrypt(){
		
	}
	
	
	
	private String AESTYPE ="AES/ECB/PKCS5Padding"; 
	 
    public  String encrypt(String keyStr, String plainText) { 
        byte[] encrypt = null; 
        try{ 
            Key key = generateKey(keyStr); 
            Cipher cipher = Cipher.getInstance(AESTYPE); 
            cipher.init(Cipher.ENCRYPT_MODE, key); 
            encrypt = cipher.doFinal(plainText.getBytes());     
        }catch(Exception e){ 
            e.printStackTrace(); 
        }
        BASE64Encoder enc=new BASE64Encoder();
  	  //String 转换后的string=enc.encode(byte数组);
        return new String(enc.encode(encrypt)); 
    } 
 
    public  String decrypt(String keyStr, String encryptData) {
        byte[] decrypt = null; 
        try{ 
            Key key = generateKey(keyStr); 
            Cipher cipher = Cipher.getInstance(AESTYPE); 
            cipher.init(Cipher.DECRYPT_MODE, key); 
            BASE64Decoder dec=new BASE64Decoder(); 
            decrypt = cipher.doFinal(dec.decodeBuffer(encryptData)); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 
        return new String(decrypt).trim(); 
    } 
 
    private  Key generateKey(String key)throws Exception{ 
        try{            
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES"); 
            return keySpec; 
        }catch(Exception e){ 
            e.printStackTrace(); 
            throw e; 
        } 
 
    } 
	
	
	
	
	
	/*
	
	*//** 
	 * 加密 
	 *  
	 * @param content 需要加密的内容 
	 * @param password  加密密码 
	 * @return 
	 *//*  
	public byte[] encrypt(String content, String password) {  
	        try {             
	                KeyGenerator kgen = KeyGenerator.getInstance("AES");  
	                kgen.init(128, new SecureRandom(password.getBytes()));  
	                SecretKey secretKey = kgen.generateKey();  
	                byte[] enCodeFormat = secretKey.getEncoded();  
	                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
	                Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
	                byte[] byteContent = content.getBytes("utf-8");  
	                cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化   
	                byte[] result = cipher.doFinal(byteContent);  
	                return result; // 加密   
	        } catch (NoSuchAlgorithmException e) {  
	                e.printStackTrace();  
	        } catch (NoSuchPaddingException e) {  
	                e.printStackTrace();  
	        } catch (InvalidKeyException e) {  
	                e.printStackTrace();  
	        } catch (UnsupportedEncodingException e) {  
	                e.printStackTrace();  
	        } catch (IllegalBlockSizeException e) {  
	                e.printStackTrace();  
	        } catch (BadPaddingException e) {  
	                e.printStackTrace();  
	        }  
	        return null;  
	}  
	
	*//**解密 
	 * @param content  待解密内容 
	 * @param password 解密密钥 
	 * @return 
	 *//*  
	public byte[] decrypt(byte[] content, String password) {  
	        try {  
	                 KeyGenerator kgen = KeyGenerator.getInstance("AES");  
	                 kgen.init(128, new SecureRandom(password.getBytes()));  
	                 SecretKey secretKey = kgen.generateKey();  
	                 byte[] enCodeFormat = secretKey.getEncoded();  
	                 SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");              
	                 Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
	                cipher.init(Cipher.DECRYPT_MODE, key);// 初始化   
	                byte[] result = cipher.doFinal(content);  
	                return result; // 加密   
	        } catch (NoSuchAlgorithmException e) {  
	                e.printStackTrace();  
	        } catch (NoSuchPaddingException e) {  
	                e.printStackTrace();  
	        } catch (InvalidKeyException e) {  
	                e.printStackTrace();  
	        } catch (IllegalBlockSizeException e) {  
	                e.printStackTrace();  
	        } catch (BadPaddingException e) {  
	                e.printStackTrace();  
	        }  
	        return null;  
	}  
	
	
	
	*//**将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 *//*  
	public static String parseByte2HexStr(byte buf[]) {  
	        StringBuffer sb = new StringBuffer();  
	        for (int i = 0; i < buf.length; i++) {  
	                String hex = Integer.toHexString(buf[i] & 0xFF);  
	                if (hex.length() == 1) {  
	                        hex = '0' + hex;  
	                }  
	                sb.append(hex.toUpperCase());  
	        }  
	        return sb.toString();  
	}  
	
	
	*//**将16进制转换为二进制 
	 * @param hexStr 
	 * @return 
	 *//*  
	public static byte[] parseHexStr2Byte(String hexStr) {  
	        if (hexStr.length() < 1)  
	                return null;  
	        byte[] result = new byte[hexStr.length()/2];  
	        for (int i = 0;i< hexStr.length()/2; i++) {  
	                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
	                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
	                result[i] = (byte) (high * 16 + low);  
	        }  
	        return result;  
	}  
	
	public String ByteToString(byte[] arg0){
	BASE64Encoder enc=new BASE64Encoder();
	  //String 转换后的string=enc.encode(byte数组);
	 return enc.encode(arg0);
	}
	//将string转换回来成为byte数组：  

	public byte[] StringToByte(String arg){
	  BASE64Decoder dec=new BASE64Decoder(); 
	  byte[] byte_from_string = null;
	  try {
	   //byte数组 = dec.decodeBuffer(转换后的string);
		   byte_from_string  =  dec.decodeBuffer(arg);
	  } catch (IOException e1) {
	   e1.printStackTrace();
	  }
	  return byte_from_string;
	}
	*/
}

package com.jzelda.util;

import java.security.SecureRandom;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class MySecretKey {
    /*
    生成密鑰物件及可見字元編碼base64密鑰
    */
    
    public static SecretKey getKey(){
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        KeyGenerator keygen = null;
        try{
            keygen = KeyGenerator.getInstance("DES");
            SecureRandom rand = new SecureRandom();
            keygen.init(rand);            
        } catch(Exception e){
            
        }
        
        return keygen != null? keygen.generateKey() : null;
    }
    
    public static String getBase64Key(){
        String base64 = null;
        SecretKey key = getKey();
        if(key != null){
            byte[] key_byte = key.getEncoded();
            base64 =DatatypeConverter.printBase64Binary(key_byte);
        }
        
        return base64;
    }
    
    public static SecretKey revertKey(String key){
        byte[] key_byte = DatatypeConverter.parseBase64Binary(key);
        
        return new SecretKeySpec(key_byte, "DES");        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{        
        //return encode64;
        System.out.println(getBase64Key());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jzelda.util;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author engin
 */
public class MyDecrypt {
    public static String decrypt(String crypt, String key){
        String revert = null;
        
        SecretKey orikey = MySecretKey.revertKey(key);
        byte[] decode64 = DatatypeConverter.parseBase64Binary(crypt);
        try{
            Cipher c = Cipher.getInstance("DES");
            c.init(Cipher.DECRYPT_MODE, orikey);
            byte[] oridata = c.doFinal(decode64);
            revert = new String(oridata);
        } catch(Exception e){
            
        }
        
        return revert;
    }
    
    public static void main(String[] args){
        if(args.length != 2){
            System.out.println("args are incorrent.");
            return;
        }
        
        SecretKey key = null;
        String outContent = null;
        try(BufferedReader reader = new BufferedReader( new FileReader(args[1]))){
            String content = reader.readLine();
            if(content != null){
                outContent = decrypt(args[0], content);
                /*
                key = MySecretKey.revertKey(content);
                
                byte[] decode64 = DatatypeConverter.parseBase64Binary(args[0]);
                
                Cipher c = Cipher.getInstance("DES");
                c.init(Cipher.DECRYPT_MODE, key);
                byte[] crypt = c.doFinal(decode64);
                outContent = new String(crypt);
                */
                //outContent = DatatypeConverter.printBase64Binary(crypt);
            }
        } catch(Exception e){
            System.out.println("can`t decrypt.");
        }
        
        System.out.println(outContent);
    }
    
    
    
    
}

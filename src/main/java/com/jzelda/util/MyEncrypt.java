/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jzelda.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author engin
 */
public class MyEncrypt {
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
                key = MySecretKey.revertKey(content);
                
                Cipher c = Cipher.getInstance("DES");
                c.init(Cipher.ENCRYPT_MODE, key);
                byte[] crypt = c.doFinal(args[0].getBytes());
                outContent = DatatypeConverter.printBase64Binary(crypt);
            }
        } catch(Exception e){
            System.out.println("can`t encrypt.");
        }
        
        System.out.println(outContent);
    }
    
}

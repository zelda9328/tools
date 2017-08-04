/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jzelda.util;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.security.Security;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.io.File;

/**
 *
 * @author engin
 */
public class MysqlProperty {
    public final String user;
    public final String passwd;
    public final String host;
    public final String dbName;
    public final String connectArgs;

    
    public MysqlProperty(Object o, String path){
        Properties props = new Properties();
        
        String user = "", passwd="";
        String host ="", dbName ="", connectArgs ="";
        
        try(InputStream is = o.getClass().getResourceAsStream(path);                
                InputStreamReader keyReader
                        = new InputStreamReader(MysqlProperty.class.getResourceAsStream("/secret/key"));
                BufferedReader reader = new BufferedReader(keyReader) ){
            
            props.loadFromXML(is);
            String encrypt = reader.readLine();

            host = props.getProperty("host");
            dbName = props.getProperty("dbname");
            user = props.getProperty("user");            
            String base64Passwd = props.getProperty("passwd");
            passwd = com.jzelda.util.MyDecrypt.decrypt(base64Passwd, encrypt);
            connectArgs = String.format("jdbc:mysql://%s/%s?characterEncoding=utf8"
                    + "&generateSimpleParameterMetadata=true"
                    + "&useServerPrepStmts=true", host,dbName);            
        } catch(Exception e){
            System.out.println("read config file error");
        }
        
        this.user = user;
        this.passwd = passwd;
        this.host = host;
        this.dbName = dbName;
        this.connectArgs = connectArgs;
    }
    
    
    
    public static void main(String[] args) throws Exception{        
        String classpath = System.getProperty("java.class.path");
String[] classpathEntries = classpath.split(File.pathSeparator);
for(String s : classpathEntries){
    System.out.println(s);
}

 
    }
}
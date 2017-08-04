/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jzelda.util;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author engin
 */
public class MySecretKeyTest {
    
    public MySecretKeyTest() {
    }
    
    
    @BeforeClass
    public static void setUpClass() {        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    byte[] testKey;
    @Before
    public void setUp() {
        testKey = MySecretKey.getKey().getEncoded();
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of revertKey method, of class MySecretKey.
     */
    @Test
    public void testRevertKey() {
        System.out.println("revertKey");
        String key = DatatypeConverter.printBase64Binary(testKey);
        
        SecretKey result = MySecretKey.revertKey(key);
        //byte[] a = new byte[]{0x0a,0x0c,0x0a,0x0c,0x0a,0x0c,0x0a,0x0c};
        //assertArrayEquals(testKey, a);
        Assert.assertArrayEquals(testKey, result.getEncoded());
        //assertEquals(testKey, result.getEncoded());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}

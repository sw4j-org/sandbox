/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sw4j.sandbox;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author uwe
 */
public class DummyNGTest {

    public DummyNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test
    public void testSomeMethod() {
        Dummy dummy = new Dummy();
//        Assert.fail("Fail to test email sending");
    }

    @Test
    public void testMethodTF() {
        Dummy dummy = new Dummy();
        Assert.assertEquals(dummy.method(true, false), 1);
    }

    @Test
    public void testMethodFT() {
        Dummy dummy = new Dummy();
        Assert.assertEquals(dummy.method(false, true), 2);
    }

    @Test
    public void testMethodTT() {
        Dummy dummy = new Dummy();
        Assert.assertEquals(dummy.method(true, true), 3);
    }

    @Test
    public void testMethodFF() {
        Dummy dummy = new Dummy();
        Assert.assertEquals(dummy.method(false, false), 0);
    }

}

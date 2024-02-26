package com.phonebook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        // if [Login] link is not present
        if (!isElementPresent(By.cssSelector("[href='/login']"))) {
            // click on [Sign out] button
            click(By.xpath("//button[.='Sign Out']"));
        }
    }

    @Test
    public void createExistedAccountNegativeTest() {
        //click on Login
        click(By.cssSelector("[href='/login']"));
        //enter email
        type(By.name("email"), "test1@ts.com");
        //enter password
        type(By.name("password"), "Test1234$");
        //click on the Registration button
        click(By.name("registration"));
        //assert Sigh Out button exists
//        Assert.assertTrue(isElementPresent(By.xpath("//button[.='Sign Out']")));
        Assert.assertTrue(isAlertAppears());

    }


}
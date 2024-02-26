package com.phonebook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondition() {
        // if [Login] link is not present
        if (!isElementPresent(By.cssSelector("[href='/login']"))) {
            // click on [Sign out] button
            click(By.xpath("//button[.='Sign Out']"));
        }
    }

    @Test
    public void loginPositiveTest() {
        //click on Login
        click(By.cssSelector("[href='/login']"));
        //enter email
        type(By.name("email"), "test1@ts.com");
        //enter password
        type(By.name("password"), "Test1234$");
        // click on login button
        click(By.name("login"));
        // assert sign out button exists
        Assert.assertTrue(isElementPresent(By.xpath("//button[.='Sign Out']")));
    }


}

package com.phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondition() {
        if (!isLoginLinkPresent()) {
            clickOnSighOutButton();
        }
    }

    @Test
    public void loginPositiveTest() {
        clickOnLoginLink();
        fillLoginRegisterForm(new User()
                .setEmail("test1@ts.com")
                .setPassword("Test1234$"));
        clickOnLoginButton();
        Assert.assertTrue(isSignOutButtonPresent());
    }

    @Test
    public void loginNegativeTestWithoutEmail() {
        clickOnLoginLink();
        fillLoginRegisterForm(new User()
                .setPassword("Test1234$"));
        clickOnLoginButton();
        Assert.assertTrue(isAlertAppears());
    }
}

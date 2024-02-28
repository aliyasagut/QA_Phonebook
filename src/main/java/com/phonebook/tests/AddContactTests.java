package com.phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase{

    @BeforeMethod
    public void precondition() {
        if (!isLoginLinkPresent()) {
            clickOnSighOutButton();
        }
        clickOnLoginLink();
        fillLoginRegisterForm(new User()
                .setEmail("test1@ts.com")
                .setPassword("Test1234$"));
        clickOnLoginButton();
    }

    @Test
    public void addContactPositiveTest() {
        Contact adam = new Contact()
                .setName("Adam")
                .setLastname("Karl")
                .setPhone("1234567890")
                .setEmail("Adam@karl.ad")
                .setAddress("Munich")
                .setDescription("Test description");
        clickOnAddLink();
        fillContactForm(adam);
        clickOnSaveButton();
        Assert.assertTrue(isContactCreatedByText(adam.getName()));
    }

    @AfterMethod
    public void postCondition() {
        removeContact();
    }
}

package com.phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase {

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
        clickOnAddLink();
        fillContactForm(new Contact()
                .setName("Peter")
                .setLastname("Karl")
                .setPhone("1234567890")
                .setEmail("Adam@karl.ad")
                .setAddress("Munich")
                .setDescription("Test description"));
        clickOnSaveButton();
    }

    @Test
    public void removeContactTest() {
        int sizeBefore = sizeOfContacts();
        removeContact();
        pause(500);
        int sizeAfter = sizeOfContacts();
        // assert contact is deleted by size
        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }
}

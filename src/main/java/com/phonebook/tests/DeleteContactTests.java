package com.phonebook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase {

    // precondition
    @BeforeMethod
    public void precondition() {
        // if [Login] link is not present
        if (!isElementPresent(By.cssSelector("[href='/login']"))) {
            // click on [Sign out] button
            click(By.xpath("//button[.='Sign Out']"));
        }
        //click on Login
        click(By.cssSelector("[href='/login']"));
        //enter email
        type(By.name("email"), "test1@ts.com");
        //enter password
        type(By.name("password"), "Test1234$");
        // click on login button
        click(By.name("login"));
        // click on link add
        click(By.cssSelector("[href='/add']"));
        // enter name
        type(By.cssSelector("input:nth-child(1)"), "Peter");
        // enter lastname
        type(By.cssSelector("input:nth-child(2)"), "Karl");
        // enter phone
        type(By.cssSelector("input:nth-child(3)"), "1234567890");
        // enter email
        type(By.cssSelector("input:nth-child(4)"), "Adam@karl.ad");
        // enter address
        type(By.cssSelector("input:nth-child(5)"), "Munich");
        // enter description
        type(By.cssSelector("input:nth-child(6)"), "Test description");
        // click on Save
        click(By.cssSelector(".add_form__2rsm2 button"));
    }

    @Test
    public void removeContactTest() {
        int sizeBefore = sizeOfContacts();
        // click on the card
        click(By.cssSelector(".contact-item_card__2SOIM"));
        // click on remove button
        click(By.xpath("//button[.='Remove']"));
        pause(500);
        int sizeAfter = sizeOfContacts();
        // assert contact is deleted by size
        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int sizeOfContacts() {
        if (isElementPresent(By.cssSelector(".contact-item_card__2SOIM"))) {
            return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        }
        return 0;
    }
}

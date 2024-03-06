package com.phonebook.tests;

import com.phonebook.fw.DataProviders;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase{

    @BeforeMethod
    public void precondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSighOutButton();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("test1@ts.com")
                .setPassword("Test1234$"));
        app.getUser().clickOnLoginButton();
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
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(adam);
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreatedByText(adam.getName()));
    }

    @AfterMethod
    public void postCondition() {
        app.getContact().removeContact();
    }

    @Test(dataProvider = "addContact", dataProviderClass = DataProviders.class)
    public void addContactPositiveTestFromDataProvider(String name, String lastname, String phone, String email, String address, String description) {
        Contact adam = new Contact()
                .setName(name)
                .setLastname(lastname)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description);
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(adam);
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreatedByText(name));
    }

    @Test(dataProvider = "addContactFromCsv", dataProviderClass = DataProviders.class)
    public void addContactPositiveTestFromDataProviderWithFile(Contact contact) {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(contact);
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreatedByText(contact.getName()));
    }
}

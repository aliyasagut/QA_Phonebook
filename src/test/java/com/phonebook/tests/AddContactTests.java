package com.phonebook.tests;

import com.phonebook.models.Contact;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    @DataProvider
    public Iterator<Object[]> addContact() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Oliver", "Kan", "1234567890", "kan@kan.kan", "Berlin", "test description"});
        list.add(new Object[]{"Oliver1", "Kan1", "12234567890", "kan1@kan.kan", "Berlin1", "test description1"});
        list.add(new Object[]{"Oliver2", "Kan2", "111234567890", "kan2@kan.kan", "Berlin2", "test description2"});

        return list.iterator();
    }

    @Test(dataProvider = "addContact")
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

    @DataProvider
    public Iterator<Object[]> addContactFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.csv")));

        String line = reader.readLine();

        while (line != null) {

            String[] split = line.split(",");
            list.add(new Object[]{new Contact().setName(split[0]).setLastname(split[1]).setPhone(split[2])
                    .setEmail(split[3]).setAddress(split[4]).setDescription(split[5])});
            line = reader.readLine();
        }

        return list.iterator();
    }

    @Test(dataProvider = "addContactFromCsv")
    public void addContactPositiveTestFromDataProviderWithFile(Contact contact) {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(contact);
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreatedByText(contact.getName()));
    }
}

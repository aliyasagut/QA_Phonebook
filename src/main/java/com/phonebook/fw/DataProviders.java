package com.phonebook.fw;

import com.phonebook.models.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider
    public Iterator<Object[]> addContact() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Oliver", "Kan", "1234567890", "kan@kan.kan", "Berlin", "test description"});
        list.add(new Object[]{"Oliver1", "Kan1", "12234567890", "kan1@kan.kan", "Berlin1", "test description1"});
        list.add(new Object[]{"Oliver2", "Kan2", "111234567890", "kan2@kan.kan", "Berlin2", "test description2"});

        return list.iterator();
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
}

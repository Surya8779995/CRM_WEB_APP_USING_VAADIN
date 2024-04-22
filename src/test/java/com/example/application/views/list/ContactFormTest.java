package com.example.application.views.list;

import com.example.application.data.Company;
import com.example.application.data.Contact;
import com.example.application.data.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactFormTest {
    private List<Company> companies;
    private List<Status> statuses;
    private Contact marcUsher;
    private Company company1;
    private Company company2;
    private Status status1;
    private Status status2;

    @BeforeEach
    public  void  setupData()
    {
        companies = new ArrayList<>();
        company1=new Company();
        company1.setName("Conestoga College");
        company2=new Company();
        company2.setName("IT Company");
        companies.add(company1);
        companies.add(company2);

        statuses=new ArrayList<>();
        status1=new Status();
        status1.setName("Intern");
        status2=new Status();
        status2.setName("Coop");
        statuses.add(status1);
        statuses.add(status2);

        marcUsher=new Contact();
        marcUsher.setFirstName("Surya");
        marcUsher.setLastName("S");
        marcUsher.setEmail("Surya@gmail.com");
        marcUsher.setStatus(status1);
        marcUsher.setCompany(company2);
    }
    @Test
    public  void formFieldsPopulated()
    {
        ContactForm form=new ContactForm(companies,statuses);
        form.setContact(marcUsher);
        assertEquals("Surya",form.firstName.getValue());
        assertEquals("S",form.lastName.getValue());
        assertEquals("Surya@gmail.com",form.email.getValue());
        assertEquals(company2,form.company.getValue());
        assertEquals(status1,form.status.getValue());
    }
    @Test
    public void saveEventHasCorrectValues()
    {
        ContactForm form=new ContactForm(companies,statuses);
        Contact contact=new Contact();
        form.setContact(contact);
        form.firstName.setValue("John");
        form.lastName.setValue("Doe");
        form.email.setValue("john@doe.com");
        form.company.setValue(company1);
        form.status.setValue(status2);

        AtomicReference<Contact> savedContactRef=new AtomicReference<>(null);
        form.addSaveListener(e->{
            savedContactRef.set(e.getContact());
        });
        form.save.click();
        Contact savedConatct=savedContactRef.get();

        assertEquals("John",savedConatct.getFirstName());
        assertEquals("Doe",savedConatct.getLastName());
        assertEquals("john@doe.com",savedConatct.getEmail());
        assertEquals(company1,savedConatct.getCompany());
        assertEquals(status2,savedConatct.getStatus());
    }
}

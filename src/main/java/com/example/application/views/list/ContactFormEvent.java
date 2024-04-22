package com.example.application.views.list;

import com.example.application.data.Contact;
import com.vaadin.flow.component.ComponentEvent;

// Events
public abstract class ContactFormEvent extends ComponentEvent<ContactForm> {
    private Contact contact;

    protected ContactFormEvent(ContactForm source, Contact contact) {
        super(source, false);
        this.contact = contact;
    }
  public Contact getContact() {
    return contact;
  }

}

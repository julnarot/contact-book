package com.julnarot.contactbook.contact;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service // provide for dinamixcalll instance
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }


    public void addNewContact(Contact contact) {
        Optional<Contact> contactOptional = contactRepository.findContactByEmail(contact.getEmail());
        if (contactOptional.isPresent()) {
            throw new IllegalStateException("Email is exist");
        }
        contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        boolean exist = contactRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException("Student with id [" + id + "] does not exist");
        }
        contactRepository.deleteById(id);
    }

    @Transactional
    public void updateContact(Long contactId, String name, String email) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(()-> new IllegalStateException("Student with id [" + contactId+ "] does not exist"));
        if (name != null && name.length() > 0 && !Objects.equals(contact.getName(), name)) {
            contact.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(contact.getEmail(), email)) {
            Optional<Contact> contactOptional = contactRepository.findContactByEmail(email);
            if (contactOptional.isPresent()) {
                throw new IllegalStateException("Email is exist");
            }
            contact.setEmail(email);
        }
    }
}

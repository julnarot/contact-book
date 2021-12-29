package com.julnarot.contactbook.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository
        extends JpaRepository<Contact, Long> {

    @Query("SELECT c FROM Contact c where c.email = ?1")
Optional<Contact> findContactByEmail(String email);
}

package com.julnarot.contactbook.contact;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class ContactConfig {
    @Bean
    CommandLineRunner commandLineRunner(ContactRepository contactRepository) {
        return args -> {
            Contact raul = new Contact(
                    "raul",
                    "986834850",
                    "julnarot@gmail.com",
                    LocalDate.of(1991, Month.JULY, 19)
            );
            Contact elizabeth = new Contact(
                    "Sam",
                    "93245345",
                    "eli@gmail.com",
                    LocalDate.of(2000, Month.JULY, 13)
            );

            contactRepository.saveAll(List.of(raul, elizabeth));
        };
    }
}

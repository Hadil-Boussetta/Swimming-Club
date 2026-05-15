package com.swimclub.backend.config;

import com.swimclub.backend.model.*;
import com.swimclub.backend.repository.SwimmerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Seeds the database with sample swimmers on startup (only if table is empty).
 * Matches the data displayed in the Angular frontend mockup.
 * Active by default; disable with spring profile "prod".
 */
@Component
@Profile("!prod")
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final SwimmerRepository swimmerRepository;

    public DataInitializer(SwimmerRepository swimmerRepository) {
        this.swimmerRepository = swimmerRepository;
    }

    @Override
    public void run(String... args) {
        if (swimmerRepository.count() > 0) {
            log.info("Database already seeded – skipping initialization.");
            return;
        }

        log.info("Seeding database with sample swimmers...");

        List<Swimmer> sampleSwimmers = List.of(
                Swimmer.builder()
                        .firstName("Alice")
                        .lastName("Freeman")
                        .email("alice.f@example.com")
                        .phoneNumber("+33 6 12 34 56 78")
                        .dateOfBirth(LocalDate.of(2000, 3, 15))
                        .squad(Squad.ELITE)
                        .mainStroke(MainStroke.FREESTYLE)
                        .status(SwimmerStatus.ACTIVE)
                        .photoUrl("https://i.pravatar.cc/150?img=1")
                        .build(),

                Swimmer.builder()
                        .firstName("John")
                        .lastName("Peterson")
                        .email("john.p@example.com")
                        .phoneNumber("+33 6 98 76 54 32")
                        .dateOfBirth(LocalDate.of(2002, 7, 22))
                        .squad(Squad.DEVELOPMENT)
                        .mainStroke(MainStroke.BREASTSTROKE)
                        .status(SwimmerStatus.ACTIVE)
                        .photoUrl("https://i.pravatar.cc/150?img=13")
                        .build(),

                Swimmer.builder()
                        .firstName("Martha")
                        .lastName("Steward")
                        .email("martha@example.com")
                        .phoneNumber("+33 6 11 22 33 44")
                        .dateOfBirth(LocalDate.of(2005, 11, 8))
                        .squad(Squad.JUNIOR)
                        .mainStroke(MainStroke.BACKSTROKE)
                        .status(SwimmerStatus.INJURED)
                        .photoUrl("https://i.pravatar.cc/150?img=4")
                        .build(),

                Swimmer.builder()
                        .firstName("Jake")
                        .lastName("Peralta")
                        .email("jake.p@example.com")
                        .phoneNumber("+33 6 55 66 77 88")
                        .dateOfBirth(LocalDate.of(1999, 1, 10))
                        .squad(Squad.ELITE)
                        .mainStroke(MainStroke.FREESTYLE)
                        .status(SwimmerStatus.ACTIVE)
                        .photoUrl("https://i.pravatar.cc/150?img=11")
                        .build(),

                Swimmer.builder()
                        .firstName("Rosa")
                        .lastName("Diaz")
                        .email("rosa.d@example.com")
                        .phoneNumber("+33 6 44 33 22 11")
                        .dateOfBirth(LocalDate.of(2001, 5, 30))
                        .squad(Squad.ELITE)
                        .mainStroke(MainStroke.MEDLEY)
                        .status(SwimmerStatus.ACTIVE)
                        .photoUrl("https://i.pravatar.cc/150?img=5")
                        .build(),

                Swimmer.builder()
                        .firstName("Terry")
                        .lastName("Jeffords")
                        .email("terry.j@example.com")
                        .phoneNumber("+33 6 99 88 77 66")
                        .dateOfBirth(LocalDate.of(1998, 9, 14))
                        .squad(Squad.ELITE)
                        .mainStroke(MainStroke.BUTTERFLY)
                        .status(SwimmerStatus.ACTIVE)
                        .photoUrl("https://i.pravatar.cc/150?img=12")
                        .build(),

                Swimmer.builder()
                        .firstName("Emma")
                        .lastName("Wilson")
                        .email("emma.w@example.com")
                        .phoneNumber("+33 6 77 88 99 00")
                        .dateOfBirth(LocalDate.of(2004, 2, 19))
                        .squad(Squad.JUNIOR)
                        .mainStroke(MainStroke.FREESTYLE)
                        .status(SwimmerStatus.ACTIVE)
                        .photoUrl("https://i.pravatar.cc/150?img=9")
                        .build(),

                Swimmer.builder()
                        .firstName("Mark")
                        .lastName("Davis")
                        .email("mark.d@example.com")
                        .phoneNumber("+33 6 33 44 55 66")
                        .dateOfBirth(LocalDate.of(2003, 6, 25))
                        .squad(Squad.DEVELOPMENT)
                        .mainStroke(MainStroke.BUTTERFLY)
                        .status(SwimmerStatus.INACTIVE)
                        .photoUrl("https://i.pravatar.cc/150?img=8")
                        .build()
        );

        swimmerRepository.saveAll(sampleSwimmers);
        log.info("Successfully seeded {} swimmers.", sampleSwimmers.size());
    }
}

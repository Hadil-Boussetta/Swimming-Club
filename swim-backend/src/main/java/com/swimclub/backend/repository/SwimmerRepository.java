package com.swimclub.backend.repository;

import com.swimclub.backend.model.Squad;
import com.swimclub.backend.model.Swimmer;
import com.swimclub.backend.model.SwimmerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for Swimmer entity.
 * Provides CRUD operations + custom query methods.
 */
@Repository
public interface SwimmerRepository extends JpaRepository<Swimmer, Long> {

    /**
     * Find all swimmers belonging to a specific squad.
     */
    List<Swimmer> findBySquad(Squad squad);

    /**
     * Find all swimmers with a specific status.
     */
    List<Swimmer> findByStatus(SwimmerStatus status);

    /**
     * Find all swimmers by squad AND status.
     */
    List<Swimmer> findBySquadAndStatus(Squad squad, SwimmerStatus status);

    /**
     * Find a swimmer by email (unique).
     */
    Optional<Swimmer> findByEmail(String email);

    /**
     * Check if a swimmer with this email already exists.
     */
    boolean existsByEmail(String email);

    /**
     * Search swimmers by first or last name (case-insensitive).
     */
    List<Swimmer> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName);

    /**
     * Count swimmers by status.
     */
    long countByStatus(SwimmerStatus status);

    /**
     * Count swimmers by squad.
     */
    long countBySquad(Squad squad);
}

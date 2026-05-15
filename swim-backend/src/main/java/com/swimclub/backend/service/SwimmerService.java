package com.swimclub.backend.service;

import com.swimclub.backend.dto.SwimmerRequest;
import com.swimclub.backend.dto.SwimmerResponse;
import com.swimclub.backend.exception.DuplicateResourceException;
import com.swimclub.backend.exception.ResourceNotFoundException;
import com.swimclub.backend.model.*;
import com.swimclub.backend.repository.SwimmerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service layer for Swimmer business logic.
 */
@Service
public class SwimmerService {

    private final SwimmerRepository swimmerRepository;

    public SwimmerService(SwimmerRepository swimmerRepository) {
        this.swimmerRepository = swimmerRepository;
    }

    // ──────────────────────────── CRUD ────────────────────────────

    /**
     * Get all swimmers, with optional filtering by squad and/or status.
     */
    @Transactional(readOnly = true)
    public List<SwimmerResponse> getAllSwimmers(Squad squad, SwimmerStatus status) {
        List<Swimmer> swimmers;

        if (squad != null && status != null) {
            swimmers = swimmerRepository.findBySquadAndStatus(squad, status);
        } else if (squad != null) {
            swimmers = swimmerRepository.findBySquad(squad);
        } else if (status != null) {
            swimmers = swimmerRepository.findByStatus(status);
        } else {
            swimmers = swimmerRepository.findAll();
        }

        return swimmers.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get a single swimmer by ID.
     */
    @Transactional(readOnly = true)
    public SwimmerResponse getSwimmerById(Long id) {
        Swimmer swimmer = swimmerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Swimmer", id));
        return toResponse(swimmer);
    }

    /**
     * Create a new swimmer.
     */
    @Transactional
    public SwimmerResponse createSwimmer(SwimmerRequest request) {
        if (swimmerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    "A swimmer with email '" + request.getEmail() + "' already exists");
        }

        Swimmer swimmer = toEntity(request);
        Swimmer saved = swimmerRepository.save(swimmer);
        return toResponse(saved);
    }

    /**
     * Update an existing swimmer.
     */
    @Transactional
    public SwimmerResponse updateSwimmer(Long id, SwimmerRequest request) {
        Swimmer existing = swimmerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Swimmer", id));

        if (!existing.getEmail().equals(request.getEmail())
                && swimmerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    "A swimmer with email '" + request.getEmail() + "' already exists");
        }

        existing.setFirstName(request.getFirstName());
        existing.setLastName(request.getLastName());
        existing.setEmail(request.getEmail());
        existing.setPhoneNumber(request.getPhoneNumber());
        existing.setDateOfBirth(request.getDateOfBirth());
        existing.setSquad(request.getSquad());
        existing.setMainStroke(request.getMainStroke());
        existing.setStatus(request.getStatus());
        existing.setPhotoUrl(request.getPhotoUrl());

        Swimmer updated = swimmerRepository.save(existing);
        return toResponse(updated);
    }

    /**
     * Delete a swimmer by ID.
     */
    @Transactional
    public void deleteSwimmer(Long id) {
        if (!swimmerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Swimmer", id);
        }
        swimmerRepository.deleteById(id);
    }

    // ──────────────────────────── SEARCH ────────────────────────────

    /**
     * Search swimmers by name (case-insensitive, partial match).
     */
    @Transactional(readOnly = true)
    public List<SwimmerResponse> searchSwimmers(String query) {
        return swimmerRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ──────────────────────────── STATISTICS ────────────────────────────

    /**
     * Get statistics for the dashboard (total count, count by status, count by squad).
     */
    @Transactional(readOnly = true)
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();

        stats.put("total", swimmerRepository.count());
        stats.put("active", swimmerRepository.countByStatus(SwimmerStatus.ACTIVE));
        stats.put("inactive", swimmerRepository.countByStatus(SwimmerStatus.INACTIVE));
        stats.put("injured", swimmerRepository.countByStatus(SwimmerStatus.INJURED));
        stats.put("elite", swimmerRepository.countBySquad(Squad.ELITE));
        stats.put("development", swimmerRepository.countBySquad(Squad.DEVELOPMENT));
        stats.put("junior", swimmerRepository.countBySquad(Squad.JUNIOR));

        return stats;
    }

    // ──────────────────────────── MAPPERS ────────────────────────────

    private SwimmerResponse toResponse(Swimmer swimmer) {
        return SwimmerResponse.builder()
                .id(swimmer.getId())
                .firstName(swimmer.getFirstName())
                .lastName(swimmer.getLastName())
                .email(swimmer.getEmail())
                .phoneNumber(swimmer.getPhoneNumber())
                .dateOfBirth(swimmer.getDateOfBirth())
                .squad(swimmer.getSquad())
                .mainStroke(swimmer.getMainStroke())
                .status(swimmer.getStatus())
                .photoUrl(swimmer.getPhotoUrl())
                .createdAt(swimmer.getCreatedAt())
                .updatedAt(swimmer.getUpdatedAt())
                .build();
    }

    private Swimmer toEntity(SwimmerRequest request) {
        return Swimmer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .dateOfBirth(request.getDateOfBirth())
                .squad(request.getSquad())
                .mainStroke(request.getMainStroke())
                .status(request.getStatus())
                .photoUrl(request.getPhotoUrl())
                .build();
    }
}

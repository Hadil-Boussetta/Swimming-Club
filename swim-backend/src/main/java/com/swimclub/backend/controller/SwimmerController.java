package com.swimclub.backend.controller;

import com.swimclub.backend.dto.SwimmerRequest;
import com.swimclub.backend.dto.SwimmerResponse;
import com.swimclub.backend.model.Squad;
import com.swimclub.backend.model.SwimmerStatus;
import com.swimclub.backend.service.SwimmerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller for Swimmer CRUD operations.
 * Base path: /api/swimmers
 */
@RestController
@RequestMapping("/api/swimmers")
@CrossOrigin(origins = "http://localhost:4200")
public class SwimmerController {

    private final SwimmerService swimmerService;

    public SwimmerController(SwimmerService swimmerService) {
        this.swimmerService = swimmerService;
    }

    // ──────────────────── GET all (with optional filters) ────────────────────

    /**
     * GET /api/swimmers
     * Optional query params: ?squad=ELITE&status=ACTIVE
     */
    @GetMapping
    public ResponseEntity<List<SwimmerResponse>> getAllSwimmers(
            @RequestParam(required = false) Squad squad,
            @RequestParam(required = false) SwimmerStatus status) {
        List<SwimmerResponse> swimmers = swimmerService.getAllSwimmers(squad, status);
        return ResponseEntity.ok(swimmers);
    }

    // ──────────────────── GET by ID ────────────────────

    /**
     * GET /api/swimmers/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<SwimmerResponse> getSwimmerById(@PathVariable Long id) {
        SwimmerResponse swimmer = swimmerService.getSwimmerById(id);
        return ResponseEntity.ok(swimmer);
    }

    // ──────────────────── POST create ────────────────────

    /**
     * POST /api/swimmers
     */
    @PostMapping
    public ResponseEntity<SwimmerResponse> createSwimmer(@Valid @RequestBody SwimmerRequest request) {
        SwimmerResponse created = swimmerService.createSwimmer(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ──────────────────── PUT update ────────────────────

    /**
     * PUT /api/swimmers/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<SwimmerResponse> updateSwimmer(
            @PathVariable Long id,
            @Valid @RequestBody SwimmerRequest request) {
        SwimmerResponse updated = swimmerService.updateSwimmer(id, request);
        return ResponseEntity.ok(updated);
    }

    // ──────────────────── DELETE ────────────────────

    /**
     * DELETE /api/swimmers/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSwimmer(@PathVariable Long id) {
        swimmerService.deleteSwimmer(id);
        return ResponseEntity.noContent().build();
    }

    // ──────────────────── SEARCH ────────────────────

    /**
     * GET /api/swimmers/search?q=alice
     */
    @GetMapping("/search")
    public ResponseEntity<List<SwimmerResponse>> searchSwimmers(@RequestParam String q) {
        List<SwimmerResponse> results = swimmerService.searchSwimmers(q);
        return ResponseEntity.ok(results);
    }

    // ──────────────────── STATISTICS ────────────────────

    /**
     * GET /api/swimmers/stats
     * Returns counts for the dashboard cards.
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStatistics() {
        Map<String, Object> stats = swimmerService.getStatistics();
        return ResponseEntity.ok(stats);
    }
}

package com.swimclub.backend.dto;

import com.swimclub.backend.model.MainStroke;
import com.swimclub.backend.model.Squad;
import com.swimclub.backend.model.SwimmerStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for returning Swimmer data to the frontend.
 */
public class SwimmerResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Squad squad;
    private MainStroke mainStroke;
    private SwimmerStatus status;
    private String photoUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ── Constructors ──

    public SwimmerResponse() {}

    public SwimmerResponse(Long id, String firstName, String lastName, String email,
                           String phoneNumber, LocalDate dateOfBirth, Squad squad,
                           MainStroke mainStroke, SwimmerStatus status, String photoUrl,
                           LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.squad = squad;
        this.mainStroke = mainStroke;
        this.status = status;
        this.photoUrl = photoUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ── Getters & Setters ──

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public Squad getSquad() { return squad; }
    public void setSquad(Squad squad) { this.squad = squad; }

    public MainStroke getMainStroke() { return mainStroke; }
    public void setMainStroke(MainStroke mainStroke) { this.mainStroke = mainStroke; }

    public SwimmerStatus getStatus() { return status; }
    public void setStatus(SwimmerStatus status) { this.status = status; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    // ── Builder ──

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private LocalDate dateOfBirth;
        private Squad squad;
        private MainStroke mainStroke;
        private SwimmerStatus status;
        private String photoUrl;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder firstName(String firstName) { this.firstName = firstName; return this; }
        public Builder lastName(String lastName) { this.lastName = lastName; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public Builder dateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; return this; }
        public Builder squad(Squad squad) { this.squad = squad; return this; }
        public Builder mainStroke(MainStroke mainStroke) { this.mainStroke = mainStroke; return this; }
        public Builder status(SwimmerStatus status) { this.status = status; return this; }
        public Builder photoUrl(String photoUrl) { this.photoUrl = photoUrl; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public SwimmerResponse build() {
            return new SwimmerResponse(id, firstName, lastName, email, phoneNumber,
                    dateOfBirth, squad, mainStroke, status, photoUrl, createdAt, updatedAt);
        }
    }
}

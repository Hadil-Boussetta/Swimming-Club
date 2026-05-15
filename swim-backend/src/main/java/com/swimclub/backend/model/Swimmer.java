package com.swimclub.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * JPA Entity representing a Swimmer in the club.
 * Maps to the "swimmers" table in PostgreSQL.
 */
@Entity
@Table(name = "swimmers")
public class Swimmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotNull(message = "Squad is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Squad squad;

    @NotNull(message = "Main stroke is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "main_stroke", nullable = false)
    private MainStroke mainStroke;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SwimmerStatus status;

    @Column(name = "photo_url")
    private String photoUrl;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ── Constructors ──

    public Swimmer() {}

    public Swimmer(Long id, String firstName, String lastName, String email,
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

        public Swimmer build() {
            Swimmer s = new Swimmer();
            s.id = this.id;
            s.firstName = this.firstName;
            s.lastName = this.lastName;
            s.email = this.email;
            s.phoneNumber = this.phoneNumber;
            s.dateOfBirth = this.dateOfBirth;
            s.squad = this.squad;
            s.mainStroke = this.mainStroke;
            s.status = this.status;
            s.photoUrl = this.photoUrl;
            return s;
        }
    }
}

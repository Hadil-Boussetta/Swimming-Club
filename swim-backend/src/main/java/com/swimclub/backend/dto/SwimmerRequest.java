package com.swimclub.backend.dto;

import com.swimclub.backend.model.MainStroke;
import com.swimclub.backend.model.Squad;
import com.swimclub.backend.model.SwimmerStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * DTO for creating or updating a Swimmer.
 */
public class SwimmerRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    @NotNull(message = "Squad is required")
    private Squad squad;

    @NotNull(message = "Main stroke is required")
    private MainStroke mainStroke;

    @NotNull(message = "Status is required")
    private SwimmerStatus status;

    private String photoUrl;

    // ── Constructors ──

    public SwimmerRequest() {}

    public SwimmerRequest(String firstName, String lastName, String email,
                          String phoneNumber, LocalDate dateOfBirth, Squad squad,
                          MainStroke mainStroke, SwimmerStatus status, String photoUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.squad = squad;
        this.mainStroke = mainStroke;
        this.status = status;
        this.photoUrl = photoUrl;
    }

    // ── Getters & Setters ──

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
}

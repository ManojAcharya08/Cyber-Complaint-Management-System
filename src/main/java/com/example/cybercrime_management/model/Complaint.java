package com.example.cybercrime_management.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "caseId")
    private Long caseId;

    @Column(nullable = false)
    private String type;

    @Column(length = 1000, nullable = false)
    private String description;

    @Column(name = "date_reported", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateReported;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String area;

    private String evidence;

    // Use the converter for custom DB value mapping
    @Convert(converter = StatusConverter.class)
    @Column(nullable = false)
    private Status status = Status.NEW;

    public enum Status {
        NEW("New"),
        UNDER_INVESTIGATION("Under Investigation"),
        SOLVED("Solved"),
        CLOSED("Closed");

        private final String dbValue;

        Status(String dbValue) { this.dbValue = dbValue; }

        @JsonValue
        public String getDbValue() { return dbValue; }

        @JsonCreator
        public static Status fromDbValue(String value) {
            for (Status s : values()) {
                if (s.dbValue.equalsIgnoreCase(value) || s.name().equalsIgnoreCase(value)) return s;
            }
            throw new IllegalArgumentException("Unknown status: " + value);
        }
    }

    public Complaint() {}

    @PrePersist
    public void prePersist() {
        if (this.dateReported == null) {
            this.dateReported = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = Status.NEW;
        }
    }

    // --- Getters and Setters ---

    public Long getCaseId() { return caseId; }
    public void setCaseId(Long caseId) { this.caseId = caseId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDateReported() { return dateReported; }
    public void setDateReported(LocalDateTime dateReported) { this.dateReported = dateReported; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getEvidence() { return evidence; }
    public void setEvidence(String evidence) { this.evidence = evidence; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    // --- equals and hashCode ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complaint complaint = (Complaint) o;
        return Objects.equals(caseId, complaint.caseId);
    }

    @Override
    public int hashCode() {
        return caseId != null ? caseId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "caseId=" + caseId +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", dateReported=" + dateReported +
                ", location='" + location + '\'' +
                ", area='" + area + '\'' +
                ", evidence='" + evidence + '\'' +
                ", status=" + status +
                '}';
    }
}

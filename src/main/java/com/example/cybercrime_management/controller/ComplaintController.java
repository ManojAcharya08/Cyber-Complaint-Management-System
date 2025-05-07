package com.example.cybercrime_management.controller;

import com.example.cybercrime_management.model.Complaint;
import com.example.cybercrime_management.model.Complaint.Status;
import com.example.cybercrime_management.repository.ComplaintRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "*")
public class ComplaintController {

    private final ComplaintRepository complaintRepository;

    public ComplaintController(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    // File a new complaint
    @PostMapping
    public ResponseEntity<Complaint> fileComplaint(@RequestBody Complaint complaint) {
        if (complaint.getType() == null || complaint.getDescription() == null ||
            complaint.getLocation() == null || complaint.getArea() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        complaint.setStatus(Status.NEW);
        complaint.setDateReported(LocalDateTime.now());
        Complaint savedComplaint = complaintRepository.save(complaint);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComplaint);
    }

    // Get all complaints
    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        List<Complaint> complaints = complaintRepository.findAll();
        return ResponseEntity.ok(complaints);
    }

    // Get a complaint by ID
    @GetMapping("/{caseId}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable("caseId") Long caseId) {
        Optional<Complaint> complaintOpt = complaintRepository.findById(caseId);
        return complaintOpt.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update status of a complaint
    @PatchMapping("/{caseId}/status")
    public ResponseEntity<String> updateComplaintStatus(
            @PathVariable("caseId") Long caseId,
            @RequestBody Map<String, String> body) {
        Optional<Complaint> complaintOpt = complaintRepository.findById(caseId);
        if (complaintOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Complaint not found");
        }
        String statusStr = body.get("status");
        if (statusStr == null) {
            return ResponseEntity.badRequest().body("Missing 'status' field");
        }
        try {
            // Use the fromDbValue method for flexible/case-insensitive mapping
            Status newStatus = Status.fromDbValue(statusStr.trim());
            Complaint complaint = complaintOpt.get();
            complaint.setStatus(newStatus);
            complaintRepository.save(complaint);
            return ResponseEntity.ok("Status updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid status value: " + statusStr);
        }
    }

    // Update a full complaint (optional, for more advanced editing)
    @PutMapping("/{caseId}")
    public ResponseEntity<Complaint> updateComplaint(
            @PathVariable("caseId") Long caseId,
            @RequestBody Complaint updatedComplaint) {
        Optional<Complaint> complaintOpt = complaintRepository.findById(caseId);
        if (complaintOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Complaint complaint = complaintOpt.get();
        // Only update fields you want to allow to be changed
        complaint.setType(updatedComplaint.getType());
        complaint.setDescription(updatedComplaint.getDescription());
        complaint.setLocation(updatedComplaint.getLocation());
        complaint.setArea(updatedComplaint.getArea());
        complaint.setEvidence(updatedComplaint.getEvidence());
        // Don't update status/dateReported here (use PATCH endpoint for status)
        complaintRepository.save(complaint);
        return ResponseEntity.ok(complaint);
    }

    // Delete a complaint
    @DeleteMapping("/{caseId}")
    public ResponseEntity<?> deleteComplaint(@PathVariable("caseId") Long caseId) {
        if (!complaintRepository.existsById(caseId)) {
            return ResponseEntity.notFound().build();
        }
        complaintRepository.deleteById(caseId);
        return ResponseEntity.ok("Deleted successfully");
    }
}

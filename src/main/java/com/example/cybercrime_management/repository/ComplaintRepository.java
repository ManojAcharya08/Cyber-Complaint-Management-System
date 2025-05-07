package com.example.cybercrime_management.repository;

import com.example.cybercrime_management.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}
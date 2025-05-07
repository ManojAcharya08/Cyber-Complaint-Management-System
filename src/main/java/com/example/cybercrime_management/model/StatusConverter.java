package com.example.cybercrime_management.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Complaint.Status, String> {

    @Override
    public String convertToDatabaseColumn(Complaint.Status status) {
        return status != null ? status.getDbValue() : null;
    }

    @Override
    public Complaint.Status convertToEntityAttribute(String dbValue) {
        return dbValue != null ? Complaint.Status.fromDbValue(dbValue) : null;
    }
}

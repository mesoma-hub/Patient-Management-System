package org.patientmanagement.patientservice.mapper;

import org.patientmanagement.patientservice.dto.PatientRequestDTO;
import org.patientmanagement.patientservice.dto.PatientResponseDTO;
import org.patientmanagement.patientservice.model.Patient;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        if (patient != null) {
            patientResponseDTO.setEmail(patient.getEmail());
            patientResponseDTO.setName(patient.getName());
            patientResponseDTO.setAddress(patient.getAddress());
            patientResponseDTO.setDateOfBirth(patient.getDateOfBirth().toString());
            patientResponseDTO.setId(patient.getId().toString());
            return patientResponseDTO;
        }
        return null;
    }

    public static Patient toModel(PatientRequestDTO patientRequestDTO) {
        if (patientRequestDTO == null) {
            return null;
        }

        Patient patient = new Patient();
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setName(patientRequestDTO.getName());
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        return patient;
    }
}

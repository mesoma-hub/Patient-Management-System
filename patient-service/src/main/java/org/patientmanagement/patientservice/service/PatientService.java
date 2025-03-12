package org.patientmanagement.patientservice.service;

import org.patientmanagement.patientservice.dto.PatientRequestDTO;
import org.patientmanagement.patientservice.dto.PatientResponseDTO;
import org.patientmanagement.patientservice.model.Patient;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    List<PatientResponseDTO> getPatients();
    PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);
    PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO);
    void deletePatient(UUID id);
}

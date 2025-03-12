package org.patientmanagement.patientservice.service.impl;

import org.patientmanagement.patientservice.dto.PatientRequestDTO;
import org.patientmanagement.patientservice.dto.PatientResponseDTO;
import org.patientmanagement.patientservice.exception.EmailAlreadyExistsException;
import org.patientmanagement.patientservice.exception.PatientNotFoundException;
import org.patientmanagement.patientservice.mapper.PatientMapper;
import org.patientmanagement.patientservice.model.Patient;
import org.patientmanagement.patientservice.repository.PatientRepository;
import org.patientmanagement.patientservice.service.PatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = this.patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO)
                .toList();
    }

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (this.patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A Patient with this email already exists: " +
                    patientRequestDTO.getEmail());
        }
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));

        return PatientMapper.toDTO(newPatient);
    }

    @Override
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {

        Patient patientToUpdate = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with id: " + id +
                        " does not exist"));

        /*Check if there's another patient with the same email in the database but with a different id*/
        if (this.patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A Patient with this email already exists: " +
                    patientRequestDTO.getEmail());
        }

        patientToUpdate.setEmail(patientRequestDTO.getEmail());
        patientToUpdate.setName(patientRequestDTO.getName());
        patientToUpdate.setAddress(patientRequestDTO.getAddress());
        patientToUpdate.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        return PatientMapper.toDTO(patientRepository.save(patientToUpdate));
    }

    @Override
    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}

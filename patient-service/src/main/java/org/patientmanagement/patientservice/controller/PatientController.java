package org.patientmanagement.patientservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.patientmanagement.patientservice.dto.validators.CreatePatientValidationGroup;
import org.springframework.validation.annotation.Validated;
import org.patientmanagement.patientservice.dto.PatientRequestDTO;
import org.patientmanagement.patientservice.dto.PatientResponseDTO;
import jakarta.validation.groups.Default;
import org.patientmanagement.patientservice.service.PatientService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing Patients")
public class PatientController {
    private final PatientService patientService;
    private static final String BASE = "http://localhost:4000/";
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }

    @PostMapping
    @Operation(summary = "Create a Patient")
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO responseDTO = this.patientService.createPatient(patientRequestDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", BASE + "patients/" + responseDTO.getId());
        return new ResponseEntity<>(responseDTO, httpHeaders, HttpStatus.CREATED);
    }
    /*@Validated({Default.class}): Validates using all defaults specified in the PatientRequestDTO class*/

    @PutMapping("/{id}")
    @Operation(summary = "Update a Patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,
                                                            @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", BASE + "patients/" + id);
        return new ResponseEntity<>(patientResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}

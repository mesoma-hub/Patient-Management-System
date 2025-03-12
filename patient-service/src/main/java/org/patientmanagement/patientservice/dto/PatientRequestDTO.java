package org.patientmanagement.patientservice.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.patientmanagement.patientservice.dto.validators.CreatePatientValidationGroup;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class PatientRequestDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Date of birth field is required")
    private String dateOfBirth;

    /*Using a validation group*/
    @NotBlank(groups = CreatePatientValidationGroup.class, message = "Registered Date is required")
    private String registeredDate;

}

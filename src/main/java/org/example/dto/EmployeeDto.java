package org.example.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class EmployeeDto {
    @NotBlank
    private String fio;

    @NotBlank
    @Size(min = 12, max = 12)
    private String iin;

    @Email
    private String email;

    @Pattern(regexp = "^[+0-9() -]{7,20}$", message = "invalid phone format")
    private String phone;

    private LocalDate birthDate;
    private LocalDate employedAt;
    private LocalDate terminatedAt;

    private String status; // ACTIVE, ON_LEAVE, TERMINATED

    private UUID departmentId;
    private UUID positionId;

    private String grade;

    private String employmentType; // FULL, PART

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal salaryBase;
    private String salaryCurrency;

    private String workSchedule;

    private UUID managerId;

    public String getFio() { return fio; }
    public void setFio(String fio) { this.fio = fio; }
    public String getIin() { return iin; }
    public void setIin(String iin) { this.iin = iin; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public LocalDate getEmployedAt() { return employedAt; }
    public void setEmployedAt(LocalDate employedAt) { this.employedAt = employedAt; }
    public LocalDate getTerminatedAt() { return terminatedAt; }
    public void setTerminatedAt(LocalDate terminatedAt) { this.terminatedAt = terminatedAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public UUID getDepartmentId() { return departmentId; }
    public void setDepartmentId(UUID departmentId) { this.departmentId = departmentId; }
    public UUID getPositionId() { return positionId; }
    public void setPositionId(UUID positionId) { this.positionId = positionId; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public String getEmploymentType() { return employmentType; }
    public void setEmploymentType(String employmentType) { this.employmentType = employmentType; }
    public BigDecimal getSalaryBase() { return salaryBase; }
    public void setSalaryBase(BigDecimal salaryBase) { this.salaryBase = salaryBase; }
    public String getSalaryCurrency() { return salaryCurrency; }
    public void setSalaryCurrency(String salaryCurrency) { this.salaryCurrency = salaryCurrency; }
    public String getWorkSchedule() { return workSchedule; }
    public void setWorkSchedule(String workSchedule) { this.workSchedule = workSchedule; }
    public UUID getManagerId() { return managerId; }
    public void setManagerId(UUID managerId) { this.managerId = managerId; }
}

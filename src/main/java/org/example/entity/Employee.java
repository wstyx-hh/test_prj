package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Employee {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String fio;

    @Column(unique = true)
    private String iin;

    @Column(unique = true)
    private String email;

    private String phone;

    private LocalDate birthDate;
    private LocalDate employedAt;
    private LocalDate terminatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    private String grade;

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    private BigDecimal salaryBase;
    private String salaryCurrency;

    private String workSchedule;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    public enum Status {
        ACTIVE, ON_LEAVE, TERMINATED
    }

    public enum EmploymentType {
        FULL, PART
    }

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = Status.ACTIVE;
        }
        if (employmentType == null) {
            employmentType = EmploymentType.FULL;
        }
        if (department == null) {
            department = Department.builder().name("Default Department").code(UUID.randomUUID().toString().substring(0, 8)).build();
        }
        if (position == null) {
            position = Position.builder().name("Default Position").code(UUID.randomUUID().toString().substring(0, 8)).build();
        }
    }
}

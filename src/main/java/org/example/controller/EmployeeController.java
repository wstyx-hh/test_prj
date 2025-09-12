package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Employee;
import org.example.entity.Department;
import org.example.entity.Position;
import org.example.repository.EmployeeRepository;
import org.example.repository.DepartmentRepository;
import org.example.repository.PositionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository repository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    @GetMapping
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable UUID id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        if (employee.getDepartment() == null) {
            Department defaultDept = departmentRepository.findByCode("DEF_DEPT")
                    .orElseThrow(() -> new RuntimeException("Default Department not found"));
            employee.setDepartment(defaultDept);
        }

        if (employee.getPosition() == null) {
            Position defaultPos = positionRepository.findByCode("DEF_POS")
                    .orElseThrow(() -> new RuntimeException("Default Position not found"));
            employee.setPosition(defaultPos);
        }

        return repository.save(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable UUID id, @RequestBody Employee updated) {
        return repository.findById(id)
                .map(emp -> {
                    if (updated.getFio() != null) emp.setFio(updated.getFio());
                    if (updated.getIin() != null) emp.setIin(updated.getIin());
                    if (updated.getEmail() != null) emp.setEmail(updated.getEmail());
                    if (updated.getPhone() != null) emp.setPhone(updated.getPhone());
                    if (updated.getBirthDate() != null) emp.setBirthDate(updated.getBirthDate());
                    if (updated.getEmployedAt() != null) emp.setEmployedAt(updated.getEmployedAt());
                    if (updated.getTerminatedAt() != null) emp.setTerminatedAt(updated.getTerminatedAt());
                    if (updated.getStatus() != null) emp.setStatus(updated.getStatus());
                    if (updated.getDepartment() != null) emp.setDepartment(updated.getDepartment());
                    if (updated.getPosition() != null) emp.setPosition(updated.getPosition());
                    if (updated.getGrade() != null) emp.setGrade(updated.getGrade());
                    if (updated.getEmploymentType() != null) emp.setEmploymentType(updated.getEmploymentType());
                    if (updated.getSalaryBase() != null) emp.setSalaryBase(updated.getSalaryBase());
                    if (updated.getSalaryCurrency() != null) emp.setSalaryCurrency(updated.getSalaryCurrency());
                    if (updated.getWorkSchedule() != null) emp.setWorkSchedule(updated.getWorkSchedule());
                    if (updated.getManager() != null) emp.setManager(updated.getManager());

                    return ResponseEntity.ok(repository.save(emp));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return repository.findById(id)
                .map(emp -> {
                    repository.delete(emp);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

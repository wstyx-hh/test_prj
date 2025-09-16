package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.EmployeeDto;
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
    public Employee create(@Valid @RequestBody EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setFio(dto.getFio());
        employee.setIin(dto.getIin());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setBirthDate(dto.getBirthDate());
        employee.setEmployedAt(dto.getEmployedAt());
        employee.setTerminatedAt(dto.getTerminatedAt());
        if (dto.getStatus() != null) {
            employee.setStatus(Employee.Status.valueOf(dto.getStatus()));
        }
        if (dto.getDepartmentId() != null) {
            Department dep = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            employee.setDepartment(dep);
        }
        if (dto.getPositionId() != null) {
            Position pos = positionRepository.findById(dto.getPositionId())
                    .orElseThrow(() -> new RuntimeException("Position not found"));
            employee.setPosition(pos);
        }
        employee.setGrade(dto.getGrade());
        if (dto.getEmploymentType() != null) {
            employee.setEmploymentType(Employee.EmploymentType.valueOf(dto.getEmploymentType()));
        }
        employee.setSalaryBase(dto.getSalaryBase());
        employee.setSalaryCurrency(dto.getSalaryCurrency());
        employee.setWorkSchedule(dto.getWorkSchedule());
        if (dto.getManagerId() != null) {
            Employee manager = repository.findById(dto.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found"));
            employee.setManager(manager);
        }

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
    public ResponseEntity<Employee> update(@PathVariable UUID id, @Valid @RequestBody EmployeeDto dto) {
        return repository.findById(id)
                .map(emp -> {
                    if (dto.getFio() != null) emp.setFio(dto.getFio());
                    if (dto.getIin() != null) emp.setIin(dto.getIin());
                    if (dto.getEmail() != null) emp.setEmail(dto.getEmail());
                    if (dto.getPhone() != null) emp.setPhone(dto.getPhone());
                    if (dto.getBirthDate() != null) emp.setBirthDate(dto.getBirthDate());
                    if (dto.getEmployedAt() != null) emp.setEmployedAt(dto.getEmployedAt());
                    if (dto.getTerminatedAt() != null) emp.setTerminatedAt(dto.getTerminatedAt());
                    if (dto.getStatus() != null) emp.setStatus(Employee.Status.valueOf(dto.getStatus()));
                    if (dto.getDepartmentId() != null) {
                        Department dep = departmentRepository.findById(dto.getDepartmentId())
                                .orElseThrow(() -> new RuntimeException("Department not found"));
                        emp.setDepartment(dep);
                    }
                    if (dto.getPositionId() != null) {
                        Position pos = positionRepository.findById(dto.getPositionId())
                                .orElseThrow(() -> new RuntimeException("Position not found"));
                        emp.setPosition(pos);
                    }
                    if (dto.getGrade() != null) emp.setGrade(dto.getGrade());
                    if (dto.getEmploymentType() != null) emp.setEmploymentType(Employee.EmploymentType.valueOf(dto.getEmploymentType()));
                    if (dto.getSalaryBase() != null) emp.setSalaryBase(dto.getSalaryBase());
                    if (dto.getSalaryCurrency() != null) emp.setSalaryCurrency(dto.getSalaryCurrency());
                    if (dto.getWorkSchedule() != null) emp.setWorkSchedule(dto.getWorkSchedule());
                    if (dto.getManagerId() != null) {
                        Employee manager = repository.findById(dto.getManagerId())
                                .orElseThrow(() -> new RuntimeException("Manager not found"));
                        emp.setManager(manager);
                    }

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

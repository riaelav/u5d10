package valeriapagliarini.u5d10.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import valeriapagliarini.u5d10.entities.Employee;
import valeriapagliarini.u5d10.exceptions.BadRequestException;
import valeriapagliarini.u5d10.exceptions.NotFoundException;
import valeriapagliarini.u5d10.payloads.EmployeeDTO;
import valeriapagliarini.u5d10.repositories.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(EmployeeDTO payload) {
        this.employeeRepository.findByEmail(payload.email())
                .ifPresent(employee -> {
                    throw new BadRequestException("Email" + employee.getEmail() + "already used");
                });
        Employee employee = new Employee(payload.username(), payload.firstName(), payload.lastName(), payload.email());
        employee.setProfileImage("https://ui-avatars.com/api/?name=" + payload.firstName() + "+" + payload.lastName());
        Employee savedEmployee = this.employeeRepository.save(employee);
        return savedEmployee;
    }


    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long employeeId) {
        return this.employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException(employeeId));
    }

    public Employee findByIdAndUpdate(Long employeeId, EmployeeDTO payload) {
        Employee found = this.findById(employeeId);
        if (!found.getEmail().equals(payload.email()))
            this.employeeRepository.findByEmail(payload.email()).ifPresent(employee -> {
                throw new BadRequestException("Email" + employee.getEmail() + "already used");
            });
        found.setUsername(payload.username());
        found.setFirstName(payload.firstName());
        found.setLastName(payload.lastName());
        found.setEmail(payload.email());
        found.setProfileImage("https://ui-avatars.com/api/?name=" + payload.firstName() + "+" + payload.lastName());

        Employee modifiedEmployee = this.employeeRepository.save(found);
        return modifiedEmployee;
    }

    public void findByIdAndDelete(Long employeeId) {
        Employee found = this.findById(employeeId);
        this.employeeRepository.delete(found);
    }


}

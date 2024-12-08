package tech.reliab.course.toropchida.springlabyurov.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.toropchida.springlabyurov.entity.Employee;
import tech.reliab.course.toropchida.springlabyurov.repository.BankRepository;
import tech.reliab.course.toropchida.springlabyurov.repository.EmployeeRepository;
import tech.reliab.course.toropchida.springlabyurov.service.EmployeeService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.beans.BeanUtils.copyProperties;
import static tech.reliab.course.toropchida.springlabyurov.utils.Utils.getNullPropertyNames;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    public final EmployeeRepository employeeRepository;
    public final BankRepository bankRepository;

    public Employee createEmployee(Employee employee) {
        bankRepository.incrementNumberEmployeesByBankId(employee.getBankId());

        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() { return employeeRepository.findAll(); }

    public Optional<Employee> getEmployee(Long id) { return employeeRepository.findById(id); }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Optional<Employee> Employee = employeeRepository.findById(id);

        if (Employee.isPresent()) {
            Employee existingIntern = Employee.get();
            String[] ignore = getNullPropertyNames(existingIntern);
            copyProperties(employeeDetails, existingIntern, getNullPropertyNames(employeeDetails));
            System.out.println(Arrays.toString(ignore));

            existingIntern.setId(employeeDetails.getId());
            return employeeRepository.save(existingIntern);
        }

        return null;
    }

    public void deleteEmployee(Long id) { employeeRepository.deleteById(id); }

    public void deleteAllEmployees() { employeeRepository.deleteAll(); }
}
package spring_course.spring_boot_data_jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_course.spring_boot_data_jpa.DAO.EmployeeRepository;
import spring_course.spring_boot_data_jpa.entity.Employee;

import java.util.List;
import java.util.Optional;
// удаляем @Transactional, теперь его функцию будет выполнять Spring Boot Repository (хотя мы явно это не указываем)
// т.к. с приходом Spring Boot необходимость в аннотации@EnableTransactionManager отпала.
// Теперь это перешло в ответственность Spring Boot. При добавлении в проект зависимость spring-boot-starter,
// то подтягивается транзитивная зависимость - spring-boot-autoconfigure, который содержит в файле spring.factories список авто-конфигураций. (нажать на поиск с лупой)
@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Employee employee = null;
        Optional<Employee> optional = employeeRepository.findById(id);//класс Optional может содержать объект, а может содержать null
        if (optional.isPresent()) {//возвращает ответ, существует ли искомый объект или нет, в виде Boolean.ifPresent() - позволяет выполнить какое-то действие, если объект не пустой.
            employee = optional.get();
        }
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAllByName(String name) {
        List<Employee> employees = employeeRepository.findAllByName(name);
        return employees;
    }

}

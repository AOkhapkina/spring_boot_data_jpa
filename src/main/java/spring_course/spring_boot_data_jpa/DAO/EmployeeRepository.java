package spring_course.spring_boot_data_jpa.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_course.spring_boot_data_jpa.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public List<Employee> findAllByName(String name);
}

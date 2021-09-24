package spring_course.spring_boot_data_jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring_course.spring_boot_data_jpa.entity.Employee;
import spring_course.spring_boot_data_jpa.service.EmployeeService;

import java.util.List;

//@PathVariable для частей пути (например, / person / {id})
//@RequestParam предназначен для параметров запроса GET (например, / person? Name = "Bob").
//@RequestBody - это фактическое тело запроса.

@RestController
@RequestMapping("/api")
public class RESTController {

    @Autowired
    public EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
//теперь при указании в url браузера /api/employees будет срабатывать метод showAllEmployees() и возвращает список всех работников,
//Spring с помощью Jackson конвертирует этот список в JSON и уже в теле HTTP response возвращает список сотрудников в формате JSON
    }

    @GetMapping("/employees/{id}")//
    public Employee getEmployee(@PathVariable int id) {// @PathVariable - читает переменную с указанным значением
        Employee employee = employeeService.getEmployee(id);

        return employee;
    }

    @PostMapping("/employees")
    // мы отправляем запрос в формате json, а получаем ответ в виде объекта java благодаря jackson
    public Employee addEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
        //если мы направляем запрос без id(=null), то автоматически присваивается новое id и сохраняется объект с id,
        // т.к. в теле метода employeeService.saveEmployee(employee)
        // выполняется метод session.saveOrUpdate(employee) из EmployeeDAOImpl
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;

    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        employeeService.deleteEmployee(id);
        return "The employee with the ID = " + id + " was deleted";
    }

    @GetMapping("/employees/name/{name}")
    public List<Employee> showAllEmployeesByName(@PathVariable String name){
//        List<Employee> employees = employeeService.findAllByName(name);
//        return employees;
        return employeeService.findAllByName(name);
    }
}

package spring_course.spring_boot_data_jpa.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_course.spring_boot_data_jpa.entity.Employee;

import java.util.List;

//методы больше не прописываем, используем дефолтные findAll, findById, save, deleteById методы из Spring Boot,
//поэтому удаляем EmployeeDAOImpl и переименовываем EmployeeDAO в EmployeeRepository, наследуемся от JpaRepository,
// JpaRepository<T, ID> в дженериках указываем с каким классом работаем и тип Primary Key (в дженериках не может быть примитивных типов, используем обертки - wrapper class)

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
// если мы хотим создать доп. методы, то есть огромное количество готовых шаблонов для создания нового метода,
// главное правильно их называть, тогда среда сама подскажет названия методов + указать корректные параметры (тип и переменн)
// прописывать тело метода не нужно (оно уже прописано под капотом), реализовывать его отсюда также не нужно
// но метод добавляем в EmployeeService и имплементируем в EmployeeServiceImpl
// Добавляем корректную реализацию нового метода в RESTController с указанием корректного URL
    public List<Employee> findAllByName(String name);
}

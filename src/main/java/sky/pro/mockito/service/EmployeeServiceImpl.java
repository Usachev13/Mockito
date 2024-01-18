package sky.pro.mockito.service;

import org.springframework.stereotype.Service;
import sky.pro.mockito.exception.EmployeeAlreadyAddedException;
import sky.pro.mockito.exception.EmployeeInvalidInputException;
import sky.pro.mockito.exception.EmployeeNotFoundException;
import sky.pro.mockito.model.Employee;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
private final Map<String, Employee> employees = new HashMap<>(Map.of(
            "Александр Пушкин",
            new Employee("Александр", "Пушкин", 1, 60000),
            "Михаил Лермонтов",
            new Employee("Михаил", "Лермонтов", 1, 70000),
            "Александр Блок",
            new Employee("Александр", "Блок", 1, 80000),
            "Сергей Есенин",
            new Employee("Сергей", "Есенин", 2, 90000),
            "Владимир Маяковский",
            new Employee("Владимир", "Маяковский", 2, 100000),
            "Булат Окуджава",
            new Employee("Булат", "Окуджава", 2, 110000),
            "Иосиф Бродский",
            new Employee("Иосиф", "Бродский", 3, 120000),
            "Дмитрий Быков",
            new Employee("Дмитрий", "Быков", 3, 130000),
            "Алишер Моргенштерн",
            new Employee("Алишер", "Моргенштерн", 3, 140000)
    )
    );

    private void checkIsAlfa(String name, String surname) {
        if (!(isAlpha(surname) && isAlpha(name))) {
            throw new EmployeeInvalidInputException("Не правильно введены данные");
        }
    }

    @Override
    public Employee add(String name, String surname, int department, int salary) {
        Employee employee = new Employee(name, surname, department, salary);
        checkIsAlfa(name, surname);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);

        return employee;
    }

    @Override
    public Employee remove(String name, String surname, int department, int salary) {
        Employee employee = new Employee(name, surname, salary, department);
        checkIsAlfa(name, surname);
        String key = getKey(name, surname);
        if (employees.containsKey(key)) {
            employees.remove(key);

        }
        throw new EmployeeNotFoundException();
    }

    public Employee find(String name, String surname, int department, int salary) {
        Employee employee = new Employee(name, surname, salary, department);
        checkIsAlfa(name, surname);
        String key = getKey(name, surname);
        if (employees.containsKey(key)) {
            return employees.get(key);
        }
        throw new EmployeeNotFoundException("Человек не найден");

    }

    @Override
    public Map<String, Employee> getMap() {
        return employees;
    }
    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
    @Override
    public List<Employee> list(){
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployee(String name, String surname){
        return employees.get(getKey(name, surname));
    }

    public String getKey(String name, String surname) {
        return name + " " + surname;
    }
}


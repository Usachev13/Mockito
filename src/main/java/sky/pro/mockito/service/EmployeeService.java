package sky.pro.mockito.service;

import sky.pro.mockito.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Employee add(String name, String surname, int department, int salary);

    Employee remove(String name, String surname, int department, int salary);

    Employee find(String name, String surname, int department, int salary);

    Map<String, Employee> getMap();

    Collection<Employee> findAll();

    List<Employee> list();
}
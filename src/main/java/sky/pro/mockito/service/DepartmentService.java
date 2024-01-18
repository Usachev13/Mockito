package sky.pro.mockito.service;

import sky.pro.mockito.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentService {

    public Employee maxSalary(int department);

    public Employee minSalary(int department);

    public List<Employee> findAllDepartment(Integer department);

    public Map<Integer, List<Employee>> getAll();

    public int getSum(int department);

    public List<Employee>getAllByDepartment(int department);

}

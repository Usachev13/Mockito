package sky.pro.mockito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.mockito.model.Employee;
import sky.pro.mockito.service.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService service;


    @GetMapping("{id}/salary/max")
    public Employee maxSalary(@PathVariable("id") int departmentId) {
        return service.maxSalary(departmentId);
    }

    @GetMapping("{id}/salary/min")
    public Employee minSalary(@PathVariable("id") int departmentId) {
        return service.minSalary(departmentId);
    }
    @GetMapping("{id}/salary/sum")
    public int getSum(@PathVariable("id") int departmentId){
        return service.getSum(departmentId);
    }

    @GetMapping("{id}/employees")
    public List<Employee>getAllByDepartment(@PathVariable("id") int departmentId){
        return service.getAllByDepartment(departmentId);
    }
    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getEmployees(){
        return service.getAll();
    }

}


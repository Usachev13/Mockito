package sky.pro.mockito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.mockito.model.Employee;
import sky.pro.mockito.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/add")
    public Employee add(@RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("department") int department, @RequestParam("salary") int salary


    ) {
        return employeeService.add(name, surname, department, salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("department") int department, @RequestParam("salary") int salary) {
        return employeeService.remove(name, surname, department, salary);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("name") String name, @RequestParam("surname") String surname,@RequestParam("department") int department, @RequestParam("salary") int salary) {
        return employeeService.find(name, surname,department, salary);
    }

}

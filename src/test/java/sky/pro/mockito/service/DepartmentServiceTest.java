package sky.pro.mockito.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.mockito.exception.EmployeeNotFoundException;
import sky.pro.mockito.model.Employee;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    private static final List<Employee> LIST = List.of(
            new Employee("Александр", "Пушкин", 1, 60000),
            new Employee("Михаил", "Лермонтов", 1, 70000),
            new Employee("Сергей", "Есенин", 2, 90000)
    );

    @Mock
    private EmployeeService serviceMock;
    private DepartmentService out;

    @BeforeEach
    void setUp() {
        out = new DepartmentServiceImpl ( serviceMock);

    }

    @Test
    void maxTest(){
        when(serviceMock.list()).thenReturn(LIST);
        assertEquals((out.maxSalary(1)), LIST.get(1));
        assertTrue((out.maxSalary(1).getSalary())== 70000);
    }

    @Test
    void getEmployees() {
        when(serviceMock.list()).thenReturn(LIST);

        List<Employee> expected = List.of(
                new Employee("Михаил", "Лермонтов", 1, 70000),
                new Employee("Сергей", "Есенин", 2, 90000)
        );
        List<Employee> result = out.getAllByDepartment(2);
        assertEquals(expected.size(), result.size());
        assertIterableEquals(expected, result);
    }

    @Test
    void shouldReturnSumSalaryCorrectly() {
        when(serviceMock.list()).thenReturn(LIST);
        int expected = 560000;
        int result = out.getSum(1);
        assertEquals(expected, result);
        assertTrue((out.getSum(1))== 130000);


    }

    @Test
    void shouldTrowEmployeeNotFoundExceptionOnSalaryMin() {

        assertThrows(EmployeeNotFoundException.class, () -> out.minSalary(2));
    }

    @Test
    void shouldReturnMinSalaryCorrectly() {
        when(serviceMock.list()).thenReturn(LIST);
        assertEquals((out.minSalary(1)), LIST.get(1));
        assertTrue((out.minSalary(1).getSalary())== 60000);


    }

    @Test
    void shouldTrowEmployeeNotFoundExceptionOnSalaryMax() {

        assertThrows(EmployeeNotFoundException.class, () -> out.maxSalary(2));
    }

    @Test
    void shouldReturnMaxSalaryCorrectly() {
        when(serviceMock.list()).thenReturn(LIST);
        assertEquals((out.maxSalary(2)), LIST.get(2));

    }


    @Test
    void testGetEmployees() {
        when(serviceMock.findAll()).thenReturn(LIST);

        Map<Integer, List<Employee>> result = out.getAll();
        List<Employee> res1 = result.get(2);
        List<Employee> exp1 = List.of(
                new Employee("Михаил", "Лермонтов", 1, 70000),
                new Employee("Сергей", "Есенин", 2, 90000)
        );
        Assertions.assertEquals(exp1.size(), res1.size());
        assertIterableEquals(exp1, res1);


        List<Employee> res2 = result.get(1);
        List<Employee> exp2 = List.of(
                new Employee("Сергей", "Есенин", 2, 90000)
        );
        Assertions.assertEquals(exp2.size(), res2.size());
        assertIterableEquals(exp2, res2);

    }


}

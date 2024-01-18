package sky.pro.mockito.service;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sky.pro.mockito.exception.EmployeeAlreadyAddedException;
import sky.pro.mockito.exception.EmployeeInvalidInputException;
import sky.pro.mockito.exception.EmployeeNotFoundException;
import sky.pro.mockito.model.Employee;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class EmployeeServiceTest {
    private EmployeeService out;

    private final Employee FIRST_EMPLOYEE = new Employee(
            "Михаил", "Булгаков", 2,90000);
    private final Employee SECOND_EMPLOYEE = new Employee(
            "Аркадий", "Стругацкий",2, 100000);

    @BeforeEach
    void setUp() {
        out = new EmployeeServiceImpl();
    }

    @Test
    void shouldAddCorrectEmployee() {
        Employee add = out.add(
                FIRST_EMPLOYEE.getName(),
                FIRST_EMPLOYEE.getSurname(),
                FIRST_EMPLOYEE.getDepartment(),
                FIRST_EMPLOYEE.getSalary());
        assertEquals(FIRST_EMPLOYEE, add);
        assertEquals(1, out.list().size());
    }


    @Test
    void shouldThrowEmployeeAlreadyAddedException() {
        out.add(
                FIRST_EMPLOYEE.getName(),
                FIRST_EMPLOYEE.getSurname(),
                FIRST_EMPLOYEE.getDepartment(),
                FIRST_EMPLOYEE.getSalary());
        Assertions.assertThrows(
                EmployeeAlreadyAddedException.class,
                () -> out.add(
                        FIRST_EMPLOYEE.getName(),
                        FIRST_EMPLOYEE.getSurname(),
                        FIRST_EMPLOYEE.getDepartment(),
                        FIRST_EMPLOYEE.getSalary())
        );
    }

    @Test
    void shouldEmployeeInvalidInputException() {
        out.add("5", "Тургенев", 2, 250000);
        Assertions.assertThrows(EmployeeInvalidInputException.class,
                () -> out.add("5", "Тургенев", 2, 250000));
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionOnRemove() {
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> out.remove(
                        SECOND_EMPLOYEE.getName(),
                        SECOND_EMPLOYEE.getSurname(),
                        SECOND_EMPLOYEE.getDepartment(),
                        SECOND_EMPLOYEE.getSalary())
        );

    }
    @Test
    void shouldRemoveCorrectly(){
        out.add("Лев", "Толстой", 1, 80000);
        assertEquals(1, out.list().size());
        out.remove("Лев", "Толстой", 1, 80000);
        assertEquals(0, out.list().size());
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionOnFind() {
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> out.find(
                        SECOND_EMPLOYEE.getName(),
                        SECOND_EMPLOYEE.getSurname(),
                        SECOND_EMPLOYEE.getDepartment(),
                        SECOND_EMPLOYEE.getSalary())
        );
    }
    @Test
    void shouldFindCorrectly(){
        out.add("Лев", "Толстой", 2, 80000);
        Employee expected = new Employee("Лев", "Толстой", 2, 80000);
        Employee result = out.find("Лев", "Толстой", 2, 80000);
        assertEquals(expected, result);
    }
}

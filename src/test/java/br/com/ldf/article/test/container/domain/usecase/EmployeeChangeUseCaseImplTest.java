package br.com.ldf.article.test.container.domain.usecase;

import br.com.ldf.article.test.container.domain.model.Employee;
import br.com.ldf.article.test.container.domain.provider.EmployeeProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeChangeUseCaseImplTest {

    @Mock
    private EmployeeProvider employeeProvider;

    @InjectMocks
    private EmployeeChangeUseCaseImpl employeeChangeUseCase;

    @Test
    void shouldCreateEmployeeSuccessfully() {
        Employee employee = new Employee(null, "John Doe", new BigDecimal("10000"));
        when(employeeProvider.save(any(Employee.class))).thenReturn(employee);

        Employee result = employeeChangeUseCase.create(employee);

        assertEquals(employee, result);
        verify(employeeProvider, times(1)).save(employee);
    }

    @Test
    void shouldUpdateEmployeeSuccessfully() {
        Employee employee = new Employee(null, "John Doe", new BigDecimal("10000"));
        when(employeeProvider.update(anyLong(), any(Employee.class))).thenReturn(employee);

        Employee result = employeeChangeUseCase.update(1L, employee);

        assertEquals(employee, result);
        verify(employeeProvider, times(1)).update(1L, employee);
    }

    @Test
    void shouldDeleteEmployeeSuccessfully() {
        doNothing().when(employeeProvider).delete(anyLong());

        employeeChangeUseCase.delete(1L);

        verify(employeeProvider, times(1)).delete(1L);
    }
}

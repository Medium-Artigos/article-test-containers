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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeSearchUseCaseImplTest {

    @Mock
    private EmployeeProvider employeeProvider;

    @InjectMocks
    private EmployeeSearchUseCaseImpl employeeSearchUseCase;

    @Test
    void getByIdTest() {
        Employee employee = new Employee(null, "John Doe", new BigDecimal("10000"));
        when(employeeProvider.getById(anyLong())).thenReturn(employee);

        Employee result = employeeSearchUseCase.getById(1L);

        assertEquals(employee, result);
        verify(employeeProvider, times(1)).getById(1L);
    }

    @Test
    void getByNameTest() {
        Employee employee = new Employee(null, "John Doe", new BigDecimal("10000"));
        when(employeeProvider.getByName(anyString())).thenReturn(employee);

        Employee result = employeeSearchUseCase.getByName("John Doe");

        assertEquals(employee, result);
        verify(employeeProvider, times(1)).getByName("John Doe");
    }
}

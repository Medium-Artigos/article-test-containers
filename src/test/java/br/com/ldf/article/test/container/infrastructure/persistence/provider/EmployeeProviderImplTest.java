package br.com.ldf.article.test.container.infrastructure.persistence.provider;

import br.com.ldf.article.test.container.domain.exceptions.EntityNotFoundException;
import br.com.ldf.article.test.container.domain.model.Employee;
import br.com.ldf.article.test.container.infrastructure.persistence.entity.EmployeeEntity;
import br.com.ldf.article.test.container.infrastructure.persistence.mapper.EmployeeInfrastructureMapper;
import br.com.ldf.article.test.container.infrastructure.persistence.mapper.EmployeeInfrastructureMapperImpl;
import br.com.ldf.article.test.container.infrastructure.persistence.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeProviderImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Spy
    private EmployeeInfrastructureMapper mapper = new EmployeeInfrastructureMapperImpl();

    @InjectMocks
    private EmployeeProviderImpl employeeProvider;

    private Employee employee;
    private EmployeeEntity employeeEntity;

    @BeforeEach
    void setUp() {
        employee = new Employee(1L, "John Doe", new BigDecimal("10000"));
        employeeEntity = new EmployeeEntity(1L, "John Doe", new BigDecimal("10000"));
    }

    @Test
    void shouldCreateNewEmployeeSuccessfully() {
        when(employeeRepository.save(employeeEntity)).thenReturn(employeeEntity);

        Employee result = employeeProvider.save(employee);

        assertEquals(employee, result);
        verify(employeeRepository, times(1)).save(employeeEntity);
    }

    @Test
    void shouldUpdateEmployeeSuccessfully() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employeeEntity));
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employeeEntity);

        Employee result = employeeProvider.update(1L, employee);

        assertEquals(employee, result);
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(employeeEntity);
    }

    @Test
    void shouldThrowExceptionWhenUpdateEmployeeThatDoesNotExist() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> employeeProvider.update(1L, employee));
    }

    @Test
    void shouldDeleteEmployeeSuccessfully() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employeeEntity));
        doNothing().when(employeeRepository).delete(employeeEntity);

        employeeProvider.delete(1L);

        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).delete(employeeEntity);
    }

    @Test
    void shouldThrowExceptionWhenDeleteEmployeeThatDoesNotExist() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> employeeProvider.delete(1L));
    }

    @Test
    void shouldGetByNameSuccessfully() {
        when(employeeRepository.findByName("John Doe")).thenReturn(Optional.of(employeeEntity));

        Employee result = employeeProvider.getByName("John Doe");

        assertEquals(employee, result);
        verify(employeeRepository, times(1)).findByName("John Doe");
    }

    @Test
    void shouldThrowExceptionWhenGetByNameEmployeeThatDoesNotExist() {
        when(employeeRepository.findByName("John Doe")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> employeeProvider.getByName("John Doe"));
    }

    @Test
    void shouldGetByIdSuccessfully() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employeeEntity));

        Employee result = employeeProvider.getById(1L);

        assertEquals(employee, result);
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenGetByIdEmployeeThatDoesNotExist() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> employeeProvider.getById(1L));
    }
}

package br.com.ldf.article.test.container.application;

import br.com.ldf.article.test.container.application.api.EmployeeController;
import br.com.ldf.article.test.container.application.mapper.EmployeeApplicationMapper;
import br.com.ldf.article.test.container.application.mapper.EmployeeApplicationMapperImpl;
import br.com.ldf.article.test.container.application.usecase.EmployeeChangeUseCase;
import br.com.ldf.article.test.container.application.usecase.EmployeeSearchUseCase;
import br.com.ldf.article.test.container.domain.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeChangeUseCase employeeChangeUseCase;

    @Mock
    private EmployeeSearchUseCase employeeSearchUseCase;

    @Spy
    private EmployeeApplicationMapper mapper = new EmployeeApplicationMapperImpl();

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @Test
    void testGetById() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        Employee employee = new Employee(1L, "John Doe", new BigDecimal("10000"));
        when(employeeSearchUseCase.getById(1L)).thenReturn(employee);

        mockMvc.perform(get(EmployeeController.EMPLOYEES_API_PATH + "/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.salary").value(new BigDecimal("10000")));
    }

    @Test
    void testCreate() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        Employee employee = new Employee(1L, "John Doe", new BigDecimal("10000"));
        when(employeeChangeUseCase.create(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post(EmployeeController.EMPLOYEES_API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                 {
                                  "name" : "John Doe",
                                  "salary" : 10000
                                 }
                                """))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdate() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        mockMvc.perform(put(EmployeeController.EMPLOYEES_API_PATH + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                 {
                                  "name" : "John Doe",
                                  "salary" : 50000
                                 }
                                """))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        mockMvc.perform(delete(EmployeeController.EMPLOYEES_API_PATH + "/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}

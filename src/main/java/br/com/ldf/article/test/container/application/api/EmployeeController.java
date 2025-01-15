package br.com.ldf.article.test.container.application.api;

import br.com.ldf.article.test.container.application.dto.EmployeeRequest;
import br.com.ldf.article.test.container.application.mapper.EmployeeApplicationMapper;
import br.com.ldf.article.test.container.application.usecase.EmployeeChangeUseCase;
import br.com.ldf.article.test.container.application.usecase.EmployeeSearchUseCase;
import br.com.ldf.article.test.container.domain.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(EmployeeController.EMPLOYEES_API_PATH)
@RequiredArgsConstructor
public class EmployeeController {

    public static final String EMPLOYEES_API_PATH = "/api/employees";

    private final EmployeeChangeUseCase employeeChangeUseCase;
    private final EmployeeSearchUseCase employeeSearchUseCase;
    private final EmployeeApplicationMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Employee>> getAll(Pageable pageable) {
        return ResponseEntity.ok(employeeSearchUseCase.getAll(pageable));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeSearchUseCase.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Validated EmployeeRequest request) {
        var employee = employeeChangeUseCase.create(mapper.mapToModel(request));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Validated EmployeeRequest request) {
        employeeChangeUseCase.update(id, mapper.mapToModel(request));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeChangeUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}

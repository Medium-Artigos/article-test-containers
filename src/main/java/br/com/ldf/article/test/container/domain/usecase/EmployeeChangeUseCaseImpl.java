package br.com.ldf.article.test.container.domain.usecase;

import br.com.ldf.article.test.container.application.usecase.EmployeeChangeUseCase;
import br.com.ldf.article.test.container.domain.model.Employee;
import br.com.ldf.article.test.container.domain.provider.EmployeeProvider;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeChangeUseCaseImpl implements EmployeeChangeUseCase {

    EmployeeProvider employeeProvider;

    @Override
    public Employee create(Employee employee) {
        return employeeProvider.save(employee);
    }

    @Override
    public Employee update(Long id, Employee employee) {
        return employeeProvider.update(id, employee);
    }

    @Override
    public void delete(Long id) {
        employeeProvider.delete(id);
    }
}

package br.com.ldf.article.test.container.domain.usecase;

import br.com.ldf.article.test.container.application.usecase.EmployeeSearchUseCase;
import br.com.ldf.article.test.container.domain.model.Employee;
import br.com.ldf.article.test.container.domain.provider.EmployeeProvider;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeSearchUseCaseImpl implements EmployeeSearchUseCase {

    EmployeeProvider employeeProvider;

    @Override
    public Employee getById(Long id) {
        return employeeProvider.getById(id);
    }

    @Override
    public Page<Employee> getAll(Pageable pageable) {
        return employeeProvider.getAll(pageable);
    }
}

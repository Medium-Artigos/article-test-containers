package br.com.ldf.article.test.container.application.usecase;

import br.com.ldf.article.test.container.domain.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeSearchUseCase {
    Employee getById(Long id);
    Page<Employee> getAll(Pageable pageable);
}

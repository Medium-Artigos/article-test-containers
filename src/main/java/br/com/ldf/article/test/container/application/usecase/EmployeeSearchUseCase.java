package br.com.ldf.article.test.container.application.usecase;

import br.com.ldf.article.test.container.domain.model.Employee;

public interface EmployeeSearchUseCase {
    Employee getById(Long id);

    Employee getByName(String name);
}

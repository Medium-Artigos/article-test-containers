package br.com.ldf.article.test.container.application.usecase;

import br.com.ldf.article.test.container.domain.model.Employee;

public interface EmployeeChangeUseCase {

    Employee create(Employee employee);
    Employee update(Long id, Employee employee);
    void delete(Long id);

}

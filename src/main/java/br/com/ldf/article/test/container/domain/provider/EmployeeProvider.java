package br.com.ldf.article.test.container.domain.provider;

import br.com.ldf.article.test.container.domain.model.Employee;

public interface EmployeeProvider {
    Employee getById(Long id);

    Employee getByName(String name);

    Employee save(Employee employee);

    Employee update(Long id, Employee employee);

    void delete(Long id);
}

package br.com.ldf.article.test.container.domain.provider;

import br.com.ldf.article.test.container.domain.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeProvider {
    Page<Employee> getAll(Pageable pageable);
    Employee getById(Long id);
    Employee save(Employee employee);
    Employee update(Long id, Employee employee);
    void delete(Long id);
}

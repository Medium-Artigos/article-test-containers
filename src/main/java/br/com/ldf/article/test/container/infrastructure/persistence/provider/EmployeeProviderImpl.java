package br.com.ldf.article.test.container.infrastructure.persistence.provider;

import br.com.ldf.article.test.container.domain.exceptions.EntityNotFoundException;
import br.com.ldf.article.test.container.domain.model.Employee;
import br.com.ldf.article.test.container.domain.provider.EmployeeProvider;
import br.com.ldf.article.test.container.infrastructure.persistence.mapper.EmployeeInfrastructureMapper;
import br.com.ldf.article.test.container.infrastructure.persistence.repository.EmployeeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmployeeProviderImpl implements EmployeeProvider {

    EmployeeRepository repository;
    EmployeeInfrastructureMapper mapper;

    @Override
    public Employee getById(Long id) {
        return repository.findById(id)
                .map(mapper::toModel)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    }

    @Override
    public Employee getByName(String name) {
        return repository.findByName(name)
                .map(mapper::toModel)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    }

    @Override
    public Employee save(Employee employee) {
        var employeePersisted = repository.save(mapper.toEntity(employee));
        return mapper.toModel(employeePersisted);
    }

    @Override
    public Employee update(Long id, Employee employee) {
        return repository.findById(id)
                .map(entity -> {
                    var employeePersisted = repository.save(mapper.toEntity(employee));
                    return mapper.toModel(employeePersisted);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    }

    @Override
    public void delete(Long id) {
        repository.findById(id).ifPresentOrElse(repository::delete,
                () -> {
                    throw new EntityNotFoundException("Employee not found");
                });
    }
}

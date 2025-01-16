package br.com.ldf.article.test.container.infrastructure.persistence.mapper;

import br.com.ldf.article.test.container.domain.model.Employee;
import br.com.ldf.article.test.container.infrastructure.persistence.entity.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeInfrastructureMapper {

    Employee toModel(EmployeeEntity employeeEntity);

    EmployeeEntity toEntity(Employee employee);
}
